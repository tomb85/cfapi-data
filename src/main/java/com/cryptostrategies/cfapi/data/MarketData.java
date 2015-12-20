package com.cryptostrategies.cfapi.data;

import com.cryptostrategies.cfapi.data.exception.CfMarketDataException;
import com.cryptostrategies.cfapi.data.response.CfBpi;
import com.cryptostrategies.cfapi.data.response.Contracts;
import com.cryptostrategies.cfapi.data.response.CummulativeBidAsk;
import com.cryptostrategies.cfapi.data.response.Ticker;
import com.cryptostrategies.cfapi.data.response.Volatility;

public interface MarketData {

	Contracts getContracts() throws CfMarketDataException;

	Ticker getTicker(String tradeable) throws CfMarketDataException;

	CfBpi getCfBpi() throws CfMarketDataException;

	Volatility getVolatility() throws CfMarketDataException;

	CummulativeBidAsk getCummulativeBidAsk(String tradeable) throws CfMarketDataException;
}
