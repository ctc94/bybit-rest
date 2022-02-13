package com.bybit.controller;

import org.springframework.lang.Nullable;

/**
 * Enumeration of HTTP status series.
 * <p>
 * Retrievable via {@link HttpStatus#series()}.
 */
public enum Symbols {

	BTCUSDT("BTCUSDT"), XRPUSDT("XRPUSDT"), SPELLUSDT("SPELLUSDT"), DOGEUSDT("DOGEUSDT");

	private final String value;

	Symbols(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
	public static Symbols get(String value) {
		Symbols symbols = resolve(value);
		if (symbols == null) {
			throw new IllegalArgumentException("No matching constant for [" + value + "]");
		}
		return symbols;
	}
	
	@Nullable
	public static Symbols resolve(String value) {
		for (Symbols symbols : values()) {
			
			if (symbols.value.equals(value)) {
				return symbols;
			}
		}
		return null;
	}
}