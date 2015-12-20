package com.cryptostrategies.cfapi.data.command;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.cryptostrategies.cfapi.data.HttpRequestExecutor;
import com.cryptostrategies.cfapi.data.response.Ticker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RunWith(MockitoJUnitRunner.class)
public class TickerCommandTest {

	private static final String FULL_TICKER_URL = "https://www.cryptofacilities.com/derivatives/api/ticker?tradeable=F-XBT:USD-Dec15-W4&unit=USD";
	private static final String EXPECTED_URL = "ticker?tradeable=F-XBT:USD-Dec15-W4&unit=USD";
	private static final String TICKER_JSON = "{\n 'result' : 'success',\n 'last' : 443.76,\n 'ask' : 449.58,\n 'bid' : 447.16\n }";
	private static final String ANY_TRADEABLE = "F-XBT:USD-Dec15-W4";
	private static final String SUCCESS = "success";

	private HttpRequestExecutor requestExecutor;
	private TickerCommand command;

	@Before
	public void setup() {
		requestExecutor = mock(HttpRequestExecutor.class);
		Gson gson = new GsonBuilder().create();
		command = new TickerCommand(requestExecutor, gson);
	}

	@Test
	public void passingTradeableToGetUrlPathShoulReturnPath() {
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(criteria.getTradeable()).thenReturn(ANY_TRADEABLE);
		assertThat(command.getUrlPath(criteria), is(equalToIgnoringCase(EXPECTED_URL)));
	}

	@Test(expected = NullPointerException.class)
	public void passingCriteriaWithNoTradableToGetUrlPathShouldThrowNPE() {
		MarketDataCriteria criteria = new MarketDataCriteria();
		command.getUrlPath(criteria);
	}

	@Test(expected = NullPointerException.class)
	public void passingNullCriteriaToGetUrlPathShouldThrowNPE() {
		command.getUrlPath(null);
	}

	@Test
	public void shouldReturnTicker() throws Exception {
		MarketDataCriteria criteria = mock(MarketDataCriteria.class);
		when(requestExecutor.execute(FULL_TICKER_URL)).thenReturn(TICKER_JSON);
		when(criteria.getTradeable()).thenReturn(ANY_TRADEABLE);
		Ticker ticker = command.execute(criteria);
		assertThat(ticker.getResult(), is(equalToIgnoringCase(SUCCESS)));
		assertThat(ticker.getBid(), is(equalTo(447.16)));
		assertThat(ticker.getAsk(), is(equalTo(449.58)));
		assertThat(ticker.getLast(), is(equalTo(443.76)));
	}
}