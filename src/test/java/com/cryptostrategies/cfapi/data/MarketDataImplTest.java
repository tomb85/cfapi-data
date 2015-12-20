package com.cryptostrategies.cfapi.data;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.cryptostrategies.cfapi.data.command.CfBpiCommand;
import com.cryptostrategies.cfapi.data.command.ContractsCommand;
import com.cryptostrategies.cfapi.data.command.CummulativeBidAskCommand;
import com.cryptostrategies.cfapi.data.command.MarketDataCriteria;
import com.cryptostrategies.cfapi.data.command.TickerCommand;
import com.cryptostrategies.cfapi.data.command.VolatilityCommand;
import com.cryptostrategies.cfapi.data.exception.CfMarketDataException;

public class MarketDataImplTest {

	private static final String ANY_TRADEABLE = "any";

	private MarketDataImpl marketData = new MarketDataImpl();

	@Test(expected = CfMarketDataException.class)
	public void contractsCommandThrowingExceptionShouldRethrowCfMDE() throws Exception {
		ContractsCommand command = mock(ContractsCommand.class);
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(command.execute(criteria)).thenThrow(new Exception());
		marketData.addCommand(command);
		marketData.getContracts();
	}

	@Test(expected = CfMarketDataException.class)
	public void tickerCommandThrowingExceptionShouldRethrowCfMDE() throws Exception {
		TickerCommand command = mock(TickerCommand.class);
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(command.execute(criteria)).thenThrow(new Exception());
		marketData.addCommand(command);
		marketData.getTicker(ANY_TRADEABLE);
	}

	@Test(expected = CfMarketDataException.class)
	public void cfBpiCommandThrowingExceptionShouldRethrowCfMDE() throws Exception {
		CfBpiCommand command = mock(CfBpiCommand.class);
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(command.execute(criteria)).thenThrow(new Exception());
		marketData.addCommand(command);
		marketData.getCfBpi();
	}

	@Test(expected = CfMarketDataException.class)
	public void volatilityCommandThrowingExceptionShouldRethrowCfMDE() throws Exception {
		VolatilityCommand command = mock(VolatilityCommand.class);
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(command.execute(criteria)).thenThrow(new Exception());
		marketData.addCommand(command);
		marketData.getVolatility();
	}

	@Test(expected = CfMarketDataException.class)
	public void cummulativeBidAskCommandThrowingExceptionShouldRethrowCfMDE() throws Exception {
		CummulativeBidAskCommand command = mock(CummulativeBidAskCommand.class);
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(command.execute(criteria)).thenThrow(new Exception());
		marketData.addCommand(command);
		marketData.getCummulativeBidAsk(ANY_TRADEABLE);
	}
}