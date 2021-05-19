package com.qualityobjects.commons.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.text.WordUtils;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.jeasy.random.util.ReflectionUtils;

import com.qualityobjects.commons.exception.ClassNotInstantiatedException;
import com.qualityobjects.commons.exception.QORuntimeException;

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
@Slf4j
@Data
@RequiredArgsConstructor(staticName = "builder")
public class BeanBuilder<T> {

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
        getterField = beanClass.getMethod("get" + WordUtils.capitalize(atributo));

        data.put(atributo, getterField.invoke(beanClass.cast(bean)));

      }

      return this;

    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
        | SecurityException e) {
      log.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

  public T createRandomBean() {

    try {
      EasyRandomParameters parameters = new EasyRandomParameters().seed(123L).objectPoolSize(100).randomizationDepth(3)
          .stringLengthRange(5, 50).collectionSizeRange(1, 10).scanClasspathForConcreteTypes(true)
          .ignoreRandomizationErrors(true).excludeField(FieldPredicates.ofType(Blob.class));

      EasyRandom generator = new EasyRandom(parameters);

      T bean = generator.nextObject(beanClass);
      // Using current setted values to overwrite random values
      populateCurrentFields(bean);
      return bean;

    } catch (IllegalArgumentException | SecurityException e) {
      log.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
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

  private void populateCurrentFields(T bean) {
    try {
      for (Map.Entry<String, Object> fieldData : this.data.entrySet()) {
        String fieldName = fieldData.getKey();

        if (fieldData.getValue() == null) {
          continue;
        }
        Field field = this.getField(bean.getClass(), fieldName);
        ReflectionUtils.setProperty(bean, field, fieldData.getValue());
      }
    } catch (SecurityException | IllegalAccessException | IllegalArgumentException  e) {
      log.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

  private Field getField(Class<?> beanClass, String fieldName) {
    try {
      return beanClass.getDeclaredField(fieldName);
    } catch (NoSuchFieldException ex) {
      if (!beanClass.getSuperclass().equals(Object.class)) {
        return this.getField(beanClass.getSuperclass(), fieldName);
      }
      throw new QORuntimeException(String.format("No field found for att: %s.%s", beanClass.getSimpleName(), fieldName));
    }
  }

  public T build() {

    try {
      T bean = beanClass.getConstructor().newInstance();

      populateCurrentFields(bean);

      return bean;
    } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
        | InvocationTargetException | NoSuchMethodException e) {
      log.error(String.format(CREATE_INSTANCE_ERROR, this.beanClass) + ". " + e);
      throw new ClassNotInstantiatedException(String.format(INSTANCE_ERROR, this.beanClass));
    }
  }

}
