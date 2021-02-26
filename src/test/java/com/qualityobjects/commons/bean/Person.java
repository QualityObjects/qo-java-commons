package com.qualityobjects.commons.bean;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class Person {
	private String firstName;
	private String lastName;
	private int age;
	private String genre;
	private String dni;
	private double height;
	private double weight;
	private LocalDate birthDate;

	@Data
	@EqualsAndHashCode(callSuper = false)
	public static class Child extends Person {
		private String school;
	}
}
