package com.proj2.demo.model;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Params {
	private String k;
	private String v;
}