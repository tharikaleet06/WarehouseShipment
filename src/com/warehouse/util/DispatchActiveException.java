package com.warehouse.util;

public class DispatchActiveException extends Exception{
private String message;
public DispatchActiveException(String message) {
	this.message=message;
}
@Override
public String toString() {
	return "DispatchActiveException: "+ message;
}
}
