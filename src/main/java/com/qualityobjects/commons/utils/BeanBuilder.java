package com.qualityobjects.commons.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.WordUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qualityobjects.commons.exception.ClassNotInstantiatedException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bean builder to create easily beans for testing
 * 
 * @author Siroco Team [siroco@qualityobjects.com]
 * @since 1.1.0
 */
@Data
@RequiredArgsConstructor(staticName = "builder")
public class BeanBuilder<T> {

  private static final Logger LOG = LoggerFactory.getLogger(BeanBuilder.class);
  private static final String INSTANCE_ERROR = "Class can not be instantiated: %s";
  private static final String CREATE_INSTANCE_ERROR = "Error creating instance of: %s";

  private final Class<T> beanClass;
  private final Map<String, Object> data = new HashMap<>();

  public BeanBuilder<T> set(String field, Object value) {
    data.put(field, value);
    return this;
  }

  public BeanBuilder<T> setAll(Map<String, Object> values) {
    data.putAll(values);
    return this;
  }

  public BeanBuilder<T> fillRandomAtts(List<String> listaAtributos) {

    try {
      Method getterField;

      EasyRandomParameters parameters = new EasyRandomParameters().seed(123L).objectPoolSize(100).randomizationDepth(3)
          .stringLengthRange(5, 50).collectionSizeRange(1, 10).scanClasspathForConcreteTypes(true)
          .ignoreRandomizationErrors(true).excludeField(FieldPredicates.ofType(Blob.class));

      EasyRandom generator = new EasyRandom(parameters);

      T bean = generator.nextObject(beanClass);

      for (String atributo : listaAtributos) {
        getterField = beanClass.getMethod("get".concat(WordUtils.capitalize(atributo)));

        data.put(atributo, getterField.invoke(beanClass.cast(bean)));

      }

      return this;

    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      LOG.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

  public T createRandomBean() {

    try {
      EasyRandomParameters parameters = new EasyRandomParameters().seed(123L).objectPoolSize(100).randomizationDepth(3)
          .stringLengthRange(5, 50).collectionSizeRange(1, 10).scanClasspathForConcreteTypes(true)
          .ignoreRandomizationErrors(true).excludeField(FieldPredicates.ofType(Blob.class));

      EasyRandom generator = new EasyRandom(parameters);

      return generator.nextObject(beanClass);

    } catch (IllegalArgumentException | SecurityException e) {
      LOG.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

  public BeanBuilder<T> copyFrom(T origin) {

    if (origin != null) {
      for (Field field : beanClass.getDeclaredFields()) {
        field.setAccessible(true);
        try {
          Object val = field.get(origin);
          if (val != null) {
            this.set(field.getName(), val);
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          // Ignored on purpose
        }
      }
    }

    return this;
  }

  public T build() {

    try {
      T bean = beanClass.getConstructor().newInstance();

      for (Map.Entry<String, Object> fields : this.data.entrySet()) {
        String fieldName = fields.getKey();
        Field field = beanClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(bean, data.get(fieldName));
      }

      return bean;
    } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
      LOG.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

}
