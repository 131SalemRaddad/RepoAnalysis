package com.proj2.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class Override {
	@Embedded
	private Info info;
}