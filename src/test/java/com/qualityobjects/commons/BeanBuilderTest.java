package com.qualityobjects.commons;

import com.qualityobjects.commons.bean.Person;
import com.qualityobjects.commons.exception.ClassNotInstantiatedException;
import com.qualityobjects.commons.utils.BeanBuilder;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BeanBuilderTest {
	
	@Test
	void beanBuilderExceptionsTest() {
		final BeanBuilder<Person> bb = BeanBuilder.builder(Person.class);
		
		assertThrows("Atributo no debe existir", ClassNotInstantiatedException.class, () -> bb.fillRandomAtts(List.of("NO_EXISTE")));
		assertThrows("Atributo no debe existir", ClassNotInstantiatedException.class, () -> bb.set("MISSING", 33).build());
		
	}
	
	@Test
	void beanBuilderFunctionsTest() {
		// Copy bean from null bean
		Person bean = new Person();
		bean.setFirstName("John");
		bean.setLastName("Smith");
		bean.setAge(34);
		
		Person p = BeanBuilder.builder(Person.class)
				.copyFrom(bean)
				.build();
		assertNotNull(p);
		assertEquals(bean.getFirstName(), p.getFirstName());
		assertEquals(bean.getLastName(), p.getLastName());
		assertTrue(p.getAge() == bean.getAge());
		
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
		BeanBuilder<Person> bb = BeanBuilder.builder(Person.class)
				.setAll(data);
		assertNotNull(bb);
		assertEquals("Manuel", bb.build().getFirstName());

		
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

