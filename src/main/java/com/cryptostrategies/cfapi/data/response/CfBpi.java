package com.cryptostrategies.cfapi.data.response;

import com.google.gson.annotations.SerializedName;

public final class CfBpi extends MarketDataResponse {

	@SerializedName("cf-bpi")
	private double cfbpi;

	public CfBpi(String result, double cfbpi) {
		super(result);
		this.cfbpi = cfbpi;
	}

	public double getCfbpi() {
		return cfbpi;
	}
}