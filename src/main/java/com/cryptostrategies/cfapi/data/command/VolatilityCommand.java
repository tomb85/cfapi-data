package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.Volatility;
import com.google.gson.Gson;

public class VolatilityCommand extends AbstractMarketDataCommand<Volatility> {

	private static final String PATH = "volatility";

	public VolatilityCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		super(requestExecutor, gson);
	}

	@Override
	protected String getUrlPath(MarketDataCriteria criteria) {
		return PATH;
	}

	@Override
	protected Class<Volatility> getClassForConversion() {
		return Volatility.class;
	}
}