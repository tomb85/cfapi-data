package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.CfBpi;
import com.google.gson.Gson;

public class CfBpiCommand extends AbstractMarketDataCommand<CfBpi> {

	public CfBpiCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		super(requestExecutor, gson);
	}

	private static final String PATH = "cfbpi";

	@Override
	protected String getUrlPath(MarketDataCriteria criteria) {
		return PATH;
	}

	@Override
	protected Class<CfBpi> getClassForConversion() {
		return CfBpi.class;
	}
}