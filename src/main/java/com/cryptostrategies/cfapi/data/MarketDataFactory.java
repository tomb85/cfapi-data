package com.cryptostrategies.cfapi.data;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.http.impl.client.HttpClientBuilder;

import com.cryptostrategies.cfapi.data.command.CfBpiCommand;
import com.cryptostrategies.cfapi.data.command.ContractsCommand;
import com.cryptostrategies.cfapi.data.command.CummulativeBidAskCommand;
import com.cryptostrategies.cfapi.data.command.TickerCommand;
import com.cryptostrategies.cfapi.data.command.VolatilityCommand;
import com.cryptostrategies.cfapi.data.response.Bucket;
import com.cryptostrategies.cfapi.data.response.BucketListDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public final class MarketDataFactory {

	private static final HttpRequestExecutor REQUEST_EXECUTOR = new HttpRequestExecutorImpl(HttpClientBuilder.create()
			.build());

	private static final MarketDataImpl MARKET_DATA = new MarketDataImpl();

	private static final Gson GSON = getGson();

	static {
		MARKET_DATA.addCommand(new CfBpiCommand(REQUEST_EXECUTOR, GSON));
		MARKET_DATA.addCommand(new ContractsCommand(REQUEST_EXECUTOR, GSON));
		MARKET_DATA.addCommand(new TickerCommand(REQUEST_EXECUTOR, GSON));
		MARKET_DATA.addCommand(new VolatilityCommand(REQUEST_EXECUTOR, GSON));
		MARKET_DATA.addCommand(new CummulativeBidAskCommand(REQUEST_EXECUTOR, GSON));
	}

	public static MarketData createMarkedData() {
		return MARKET_DATA;
	}

	private static Gson getGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Type type = new TypeToken<List<Bucket>>() {}.getType();
		gsonBuilder.registerTypeAdapter(type, new BucketListDeserializer());
		return gsonBuilder.create();
	}
}