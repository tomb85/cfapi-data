package com.cryptostrategies.cfapi.data.response;

import java.util.List;

public class CummulativeBidAsk extends MarketDataResponse {

	public CummulativeBidAsk(String result, List<Bucket> cumulatedBids, List<Bucket> cumulatedAsks) {
		super(result);
		this.cumulatedBids = cumulatedBids;
		this.cumulatedAsks = cumulatedAsks;
	}

	private List<Bucket> cumulatedBids;
	private List<Bucket> cumulatedAsks;

	public List<Bucket> getCumulatedBids() {
		return cumulatedBids;
	}

	public List<Bucket> getCumulatedAsks() {
		return cumulatedAsks;
	}
}