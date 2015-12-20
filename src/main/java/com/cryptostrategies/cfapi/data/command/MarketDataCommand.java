package com.cryptostrategies.cfapi.data.command;

@FunctionalInterface
public interface MarketDataCommand<T> {

	T execute(MarketDataCriteria criteria) throws Exception;

}
