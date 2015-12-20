package com.cryptostrategies.cfapi.data.response;

public final class Volatility extends MarketDataResponse {

	private double volatility;

	public Volatility(String result, double volatility) {
		super(result);
		this.volatility = volatility;
	}

	public double getVolatility() {
		return volatility;
	}
}