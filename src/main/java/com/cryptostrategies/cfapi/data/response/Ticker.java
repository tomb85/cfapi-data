package com.cryptostrategies.cfapi.data.response;

public final class Ticker extends MarketDataResponse {

	private double last;
	private double bid;
	private double ask;

	public Ticker(String result, double last, double bid, double ask) {
		super(result);
		this.last = last;
		this.bid = bid;
		this.ask = ask;
	}

	public double getLast() {
		return last;
	}

	public double getBid() {
		return bid;
	}

	public double getAsk() {
		return ask;
	}
}