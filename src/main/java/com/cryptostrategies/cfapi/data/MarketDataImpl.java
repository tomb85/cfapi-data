package com.cryptostrategies.cfapi.data;

import java.util.Map;

import com.cryptostrategies.cfapi.data.command.CfBpiCommand;
import com.cryptostrategies.cfapi.data.command.ContractsCommand;
import com.cryptostrategies.cfapi.data.command.CummulativeBidAskCommand;
import com.cryptostrategies.cfapi.data.command.MarketDataCommand;
import com.cryptostrategies.cfapi.data.command.MarketDataCriteria;
import com.cryptostrategies.cfapi.data.command.TickerCommand;
import com.cryptostrategies.cfapi.data.command.VolatilityCommand;
import com.cryptostrategies.cfapi.data.exception.CfMarketDataException;
import com.cryptostrategies.cfapi.data.response.CfBpi;
import com.cryptostrategies.cfapi.data.response.Contracts;
import com.cryptostrategies.cfapi.data.response.CummulativeBidAsk;
import com.cryptostrategies.cfapi.data.response.Ticker;
import com.cryptostrategies.cfapi.data.response.Volatility;
import com.google.common.collect.Maps;

class MarketDataImpl implements MarketData {

	private static final MarketDataCriteria EMPTY_CRITERIA = new MarketDataCriteria();

	private Map<Class<?>, MarketDataCommand<?>> commands = Maps.newHashMap();

	public <T> void addCommand(MarketDataCommand<T> command) {
		commands.put(command.getClass(), command);
	}

	@Override
	public Contracts getContracts() throws CfMarketDataException {
		try {
			return (Contracts) commands.get(ContractsCommand.class).execute(EMPTY_CRITERIA);
		} catch (Exception e) {
			throw new CfMarketDataException("getContracts", e);
		}
	}

	@Override
	public Ticker getTicker(String tradeable) throws CfMarketDataException {
		try {
			MarketDataCriteria criteria = new MarketDataCriteria();
			criteria.setTradeable(tradeable);
			return (Ticker) commands.get(TickerCommand.class).execute(criteria);
		} catch (Exception e) {
			throw new CfMarketDataException("getTicker", e);
		}
	}

	@Override
	public CummulativeBidAsk getCummulativeBidAsk(String tradeable) throws CfMarketDataException {
		try {
			MarketDataCriteria criteria = new MarketDataCriteria();
			criteria.setTradeable(tradeable);
			Object command = commands.get(CummulativeBidAskCommand.class);
			CummulativeBidAsk result = (CummulativeBidAsk) commands.get(CummulativeBidAskCommand.class)
					.execute(criteria);
			if (result.getCumulatedBids().isEmpty() || result.getCumulatedAsks().isEmpty()) {
				return new CummulativeBidAsk("error", result.getCumulatedBids(), result.getCumulatedAsks());
			}
			return result;
		} catch (Exception e) {
			throw new CfMarketDataException("getCummulativeBidAsk", e);
		}
	}

	@Override
	public CfBpi getCfBpi() throws CfMarketDataException {
		try {
			return (CfBpi) commands.get(CfBpiCommand.class).execute(EMPTY_CRITERIA);
		} catch (Exception e) {
			throw new CfMarketDataException("getCfBpi", e);
		}
	}

	@Override
	public Volatility getVolatility() throws CfMarketDataException {
		try {
			return (Volatility) commands.get(VolatilityCommand.class).execute(EMPTY_CRITERIA);
		} catch (Exception e) {
			throw new CfMarketDataException("getVolatility", e);
		}
	}
}