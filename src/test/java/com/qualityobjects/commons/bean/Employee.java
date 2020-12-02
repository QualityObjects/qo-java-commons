package com.qualityobjects.commons.bean;

import lombok.Data;

@Data
public class Employee extends Person {
    private String job;
    private int nif;

}
