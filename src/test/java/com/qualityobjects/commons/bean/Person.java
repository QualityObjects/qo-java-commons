package com.qualityobjects.commons.bean;

import lombok.Data;

@Data
public class Person {
	private String firstName;
	private String lastName;
	private int age;
	private String genre;
	private String dni;
	private double height;
	private double weight;
	private String address;
	
	public Person() {
	}
	
}
