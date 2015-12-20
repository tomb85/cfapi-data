package com.cryptostrategies.cfapi.data.response;

import java.util.List;

public final class Contracts extends MarketDataResponse {

	private List<Contract> contracts;

	public Contracts(String result, List<Contract> contracts) {
		super(result);
		this.contracts = contracts;
	}

	public List<Contract> getContracts() {
		return contracts;
	}
}