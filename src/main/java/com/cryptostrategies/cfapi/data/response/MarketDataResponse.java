package com.cryptostrategies.cfapi.data.response;

abstract class MarketDataResponse {

	private String result;

	public MarketDataResponse(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
