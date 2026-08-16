package com.warehouse.util;

public class ShipmentUnavailableException extends Exception {
private String message;
public ShipmentUnavailableException(String message) {
	this.message=message;
}
@Override
public String toString() {
	return "ShipmentUnavailableException: "+ message;
}
}
