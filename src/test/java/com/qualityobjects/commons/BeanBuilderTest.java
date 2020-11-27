package com.qualityobjects.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.qualityobjects.commons.bean.Person;
import com.qualityobjects.commons.utils.BeanBuilder;

@ExtendWith(MockitoExtension.class)
class BeanBuilderTest {
	
	private Person bean;
	private Employee errorBean;
	private BeanBuilder bb;
	
	@Test
	void beanBuilderExceptionsTest() {
		// IllegalAccessException caught in the .fillRandomAtts() method for instantiating a class that does not exist
		List<String> listAtts = new ArrayList<String>();
		listAtts.add("firstName");
		Assertions.assertThrows(RuntimeException.class, () -> {
			errorBean = BeanBuilder.builder(Employee.class)
					.fillRandomAtts(listAtts)
					.build();
		});
		
		// IllegalAccessException caught in the .createRandomBean() method for instantiating a class that does not exist
		Assertions.assertThrows(RuntimeException.class, () -> {
			errorBean = BeanBuilder.builder(Employee.class)
					.createRandomBean();
		});
		
		// IllegalAccessException caught in the .build() method for instantiating a class that does not exist
		Assertions.assertThrows(RuntimeException.class, () -> {
			errorBean = BeanBuilder.builder(Employee.class).build();
		});
	}
	
	@Test
	void beanBuilderFunctionsTest() {
		// Copy bean from null bean
		Person p = BeanBuilder.builder(Person.class)
				.copyFrom(bean)
				.build();
		assertNotNull(p);
		
		// Fill random value of a specific attribute
		List<String> listAtts = new ArrayList<String>();
		listAtts.add("firstName");
		bean = BeanBuilder.builder(Person.class)
				.fillRandomAtts(listAtts)
				.build();
		assertNotNull(bean);
		
		// Copy bean from not null bean
		p = BeanBuilder.builder(Person.class)
				.copyFrom(bean)
				.build();
		assertNotNull(p);
		
		// Fill random value of all attributes
		listAtts.add("lastName");
		listAtts.add("age");
		listAtts.add("genre");
		listAtts.add("dni");
		listAtts.add("height");
		listAtts.add("weight");
		listAtts.add("address");
		bean = BeanBuilder.builder(Person.class)
				.fillRandomAtts(listAtts)
				.build();
		assertNotNull(bean);
		
		// Set a set of attributes
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("firstName", "Manuel");
		data.put("lastName", "Gómez");
		bb = BeanBuilder.builder(Person.class)
				.setAll(data);
		assertNotNull(bb);
		
		// Information about the data of a BeanBuilder instance
		String values = bb.toString();
		
		// Create random bean
		p = BeanBuilder.builder(Person.class)
				.createRandomBean();
		assertNotNull(p);
		
		// Set test bean values
		p.setFirstName("María");
		p.setLastName("Hernández");
		p.setAge(45);
		p.setGenre("M");
		p.setDni("12767554M");
		p.setHeight(1.60);
		p.setWeight(65.4);
		p.setAddress("c/ Alcalá, 20");
	}

}

class Employee extends Person {
	private String job;
	private int nif;
	
}
