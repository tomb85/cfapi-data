package com.cryptostrategies.cfapi.data;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Charsets;

class HttpRequestExecutorImpl implements HttpRequestExecutor {

	private CloseableHttpClient client;

	public HttpRequestExecutorImpl(CloseableHttpClient client) {
		this.client = client;
	}

	@Override
	public String execute(String url) throws Exception {
		HttpGet request = new HttpGet(url);
		request.addHeader("accept", "application/json");
		request.addHeader("Content-Type", "charset=UTF-8");
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, Charsets.UTF_8);
	}
}