package com.qualityobjects.commons;

import com.qualityobjects.commons.bean.Person;
import com.qualityobjects.commons.utils.BeanBuilder;

import lombok.Data;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BeanBuilderTest {
	
	private Person bean;
	private BeanBuilder bb;
	
	@Test
	void beanBuilderExceptionsTest() {
		// RuntimeException caught in the .fillRandomAtts() method for instantiating a class that does not exist
		List<String> listAtts = new ArrayList<String>();
		listAtts.add("firstName");
		
		final BeanBuilder bb = BeanBuilder.builder(Employee.class);
		
		assertThrows(RuntimeException.class, () -> bb.fillRandomAtts(listAtts));
		
		// RuntimeException caught in the .createRandomBean() method for instantiating a class that does not exist
		assertThrows(RuntimeException.class, () -> bb.createRandomBean());
		
		// RuntimeException caught in the .build() method for instantiating a class that does not exist
		assertThrows(RuntimeException.class, () -> bb.build());
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

@Ignore
class Employee extends Person {
    private String job;
    private int nif;

}
