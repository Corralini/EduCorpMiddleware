package com.educorp.eduinteractive.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class ValueObjet {

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
