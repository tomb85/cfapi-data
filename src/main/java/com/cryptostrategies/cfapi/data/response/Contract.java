package com.cryptostrategies.cfapi.data.response;

public final class Contract {

	private String unit;
	private String tradeable;
	private String lastTradingDayAndTime;
	private int contractSize;
	private double tickSize;
	private boolean suspended;

	public Contract(String unit, String tradeable, String lastTradingDayAndTime, int contractSize, double tickSize, boolean suspended) {
		this.unit = unit;
		this.tradeable = tradeable;
		this.lastTradingDayAndTime = lastTradingDayAndTime;
		this.contractSize = contractSize;
		this.tickSize = tickSize;
		this.suspended = suspended;
	}

	public String getUnit() {
		return unit;
	}

	public String getTradable() {
		return tradeable;
	}

	public String getLastTradingDayAndTime() {
		return lastTradingDayAndTime;
	}

	public int getContractSize() {
		return contractSize;
	}

	public double getTickSize() {
		return tickSize;
	}

	public boolean isSuspended() {
		return suspended;
	}
}
