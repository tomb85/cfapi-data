package com.cryptostrategies.cfapi.data.command;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.CummulativeBidAsk;
import com.google.gson.Gson;

public class CummulativeBidAskCommand extends AbstractMarketDataCommand<CummulativeBidAsk> {

	private static final String PATH = "cumulativebidask?tradeable=%s&unit=USD";

	public CummulativeBidAskCommand(HttpRequestExecutor requestExecutor, Gson gson) {
		super(requestExecutor, gson);
	}

	@Override
	protected String getUrlPath(MarketDataCriteria criteria) {
		String tradeable = criteria.getTradeable();
		return String.format(PATH, tradeable);
	}

	@Override
	protected Class<CummulativeBidAsk> getClassForConversion() {
		return CummulativeBidAsk.class;
	}
}