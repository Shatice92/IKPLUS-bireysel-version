package org.hatice.ikplus.exception;

public class IKPlusException extends RuntimeException {
	private ErrorType errorType;
	
	public IKPlusException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}
	public ErrorType getErrorType() {
		return errorType;
	}
}