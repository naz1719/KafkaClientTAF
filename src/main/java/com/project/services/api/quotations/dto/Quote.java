package com.project.services.api.quotations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

	private String type;
	private Value value;

	public Quote() {
	}

	public String getType() {
		return type;
	}

	public Quote setType(String type) {
		this.type = type;
		return this;
	}

	public Value getValue() {
		return value;
	}

	public Quote setValue(Value value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return "Quote{" +
				"type='" + type + '\'' +
				", value=" + value +
				'}';
	}
}