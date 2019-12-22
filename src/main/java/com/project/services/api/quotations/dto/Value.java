package com.project.services.api.quotations.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Value {

	private Long id;
	private String quote;

	public Value() {
	}

	public Long getId() {
		return id;
	}

	public Value setId(Long id) {
		this.id = id;
		return this;
	}

	public String getQuote() {
		return quote;
	}

	public Value setQuote(String quote) {
		this.quote = quote;
		return this;
	}

	@Override
	public String toString() {
		return "Value{" +
				"id=" + id +
				", quote='" + quote + '\'' +
				'}';
	}
}