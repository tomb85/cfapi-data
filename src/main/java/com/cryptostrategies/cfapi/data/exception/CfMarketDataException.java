package com.cryptostrategies.cfapi.data.exception;

public final class CfMarketDataException extends Exception {

		private static final long serialVersionUID = 1L;

		public CfMarketDataException(String message) {
			super(message);
		}

		public CfMarketDataException(Throwable throwable) {
			super(throwable);
		}

		public CfMarketDataException(String message, Throwable throwable) {
			super(message, throwable);
		}

}
