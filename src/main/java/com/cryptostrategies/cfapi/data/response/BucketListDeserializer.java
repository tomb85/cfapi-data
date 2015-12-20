package com.cryptostrategies.cfapi.data.response;

import java.lang.reflect.Type;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class BucketListDeserializer implements JsonDeserializer<List<Bucket>> {

	private static final Gson GSON = new GsonBuilder().create();
	private static final Type TYPE = new TypeToken<List<List<Double>>>() {}.getType();
	private static final int PRICE = 0;
	private static final int SIZE = 1;

	@Override
	public List<Bucket> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		List<List<Double>> entries = GSON.fromJson(json.getAsString(), TYPE);
		List<Bucket> buckets = Lists.newArrayList();
		for (List<Double> entry : entries) {
			double price = entry.get(PRICE);
			double size = entry.get(SIZE);
			buckets.add(new Bucket(price, size));
		}
		return buckets;
	}
}
