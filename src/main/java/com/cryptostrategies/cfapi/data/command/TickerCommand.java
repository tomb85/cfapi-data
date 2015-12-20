package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.Ticker;
import com.google.gson.Gson;

public class TickerCommand extends AbstractMarketDataCommand<Ticker> {

	private static final String PATH = "ticker?tradeable=%s&unit=USD";

	public TickerCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		super(requestExecutor, gson);
	}

	@Override
	protected String getUrlPath(MarketDataCriteria criteria) {
		String tradeable = criteria.getTradeable();
		if (tradeable == null) {
			throw new NullPointerException("Tradeable cannot be null");
		}
		return String.format(PATH, tradeable);
	}

	@Override
	protected Class<Ticker> getClassForConversion() {
		return Ticker.class;
	}
}