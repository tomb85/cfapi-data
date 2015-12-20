package com.cryptostrategies.cfapi.data;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.cryptostrategies.cfapi.data.exception.CfMarketDataException;
import com.cryptostrategies.cfapi.data.response.CfBpi;
import com.cryptostrategies.cfapi.data.response.Contract;
import com.cryptostrategies.cfapi.data.response.Contracts;
import com.cryptostrategies.cfapi.data.response.CummulativeBidAsk;
import com.cryptostrategies.cfapi.data.response.Ticker;
import com.cryptostrategies.cfapi.data.response.Volatility;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

@RunWith(JUnitParamsRunner.class)
public class MarketDataIT {

	private static final String SUCCESS = "success";

	private MarketData marketData = MarketDataFactory.createMarkedData();

	@Test
	public void testGetCfBpi() throws CfMarketDataException {
		CfBpi cfBpi = marketData.getCfBpi();
		assertThat(cfBpi, is(notNullValue()));
		assertThat(cfBpi.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(cfBpi.getCfbpi(), is(greaterThan(0d)));
	}

	@Test
	public void testGetContracts() throws CfMarketDataException {
		Contracts contracts = marketData.getContracts();
		assertThat(contracts, is(notNullValue()));
		assertThat(contracts.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(contracts.getContracts(), is(notNullValue()));
		assertThat(contracts.getContracts(), is(not(empty())));
		Contract contract = contracts.getContracts().iterator().next();
		assertThat(contract.getContractSize(), is(greaterThan(0)));
		assertThat(contract.getLastTradingDayAndTime(), is(notNullValue()));
		assertThat(contract.getTickSize(), is(greaterThan(0d)));
		assertThat(contract.getTradable(), is(notNullValue()));
		assertThat(contract.getUnit(), is(notNullValue()));
	}

	@Test
	@FileParameters("classpath:valid_contracts.csv")
	public void testGetTickerForValidContract(String tradeable) throws CfMarketDataException {
		Ticker ticker = marketData.getTicker(tradeable);
		assertThat(ticker, is(not(nullValue())));
		assertThat(ticker.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(ticker.getAsk() > 0, is(true));
		assertThat(ticker.getBid() > 0, is(true));
		assertThat(ticker.getLast() > 0, is(true));
	}

	@Test
	@FileParameters("classpath:invalid_contracts.csv")
	public void testGetTickerForInvalidContract(String tradeable) throws CfMarketDataException {
		Ticker ticker = marketData.getTicker(tradeable);
		assertThat(ticker.getResult(), is(not(equalToIgnoringCase(SUCCESS))));
	}

	@Test
	@FileParameters("classpath:valid_contracts.csv")
	public void testGetCummulativeBidAskForValidContract(String tradeable) throws CfMarketDataException {
		CummulativeBidAsk cummulativeBidAsk = marketData.getCummulativeBidAsk(tradeable);
		assertThat(cummulativeBidAsk, is(not(nullValue())));
		assertThat(cummulativeBidAsk.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(cummulativeBidAsk.getCumulatedBids().size(), is(greaterThan(0)));
		assertThat(cummulativeBidAsk.getCumulatedAsks().size(), is(greaterThan(0)));
	}

	@Test
	@FileParameters("classpath:invalid_contracts.csv")
	public void testGetCummulativeBidAskForInvalidContract(String tradeable) throws CfMarketDataException {
		CummulativeBidAsk cummulativeBidAsk = marketData.getCummulativeBidAsk(tradeable);
		assertThat(cummulativeBidAsk.getResult(), is(not(equalToIgnoringCase(SUCCESS))));
	}

	@Test
	public void testGetVolatility() throws CfMarketDataException {
		Volatility volatility = marketData.getVolatility();
		assertThat(volatility.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(volatility.getVolatility(), is(greaterThan(0.0)));
	}
}
