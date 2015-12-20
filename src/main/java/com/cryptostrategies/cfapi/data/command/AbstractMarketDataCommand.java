package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.google.gson.Gson;

public abstract class AbstractMarketDataCommand<T> implements MarketDataCommand<T> {

	private static final String BASE_PATH = "https://www.cryptofacilities.com/derivatives/api";

	private HttpRequestExecutor requestExecutor;
	private Gson gson;

	public AbstractMarketDataCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		this.requestExecutor = requestExecutor;
		this.gson = gson;
	}

	@Override
	public T execute(MarketDataCriteria criteria) throws Exception {
		String url = BASE_PATH + "/" + getUrlPath(criteria);
		String json = requestExecutor.execute(url);
		return gson.fromJson(json, getClassForConversion());
	}

	protected abstract String getUrlPath(MarketDataCriteria criteria);

	protected abstract Class<T> getClassForConversion();
}