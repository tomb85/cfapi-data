package com.cryptostrategies.cfapi.data.response;

public class Bucket {

	private double price;
	private double size;

	public Bucket(double price, double size) {
		super();
		this.price = price;
		this.size = size;
	}

	public double getPrice() {
		return price;
	}

	public double getSize() {
		return size;
	}
}