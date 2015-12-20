package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.Contracts;
import com.google.gson.Gson;

public class ContractsCommand extends AbstractMarketDataCommand<Contracts> {

	private static final String PATH = "contracts";

	public ContractsCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		super(requestExecutor, gson);
	}

	@Override
	protected String getUrlPath(MarketDataCriteria criteria) {
		return PATH;
	}

	@Override
	protected Class<Contracts> getClassForConversion() {
		return Contracts.class;
	}
}