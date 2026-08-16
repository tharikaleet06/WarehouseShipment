package com.warehouse.service;

import java.util.List;

import com.warehouse.bean.Dispatch;
import com.warehouse.bean.Shipment;
import com.warehouse.dao.DispatchDAO;
import com.warehouse.dao.ShipmentDAO;
import com.warehouse.util.DispatchActiveException;
import com.warehouse.util.ShipmentUnavailableException;
import com.warehouse.util.ValidationException;

public class DispatchService {
ShipmentDAO shipmentDAO=new ShipmentDAO();
DispatchDAO dispatchDAO=new DispatchDAO();

public Shipment viewShipmentDetails(String shipmentID) throws ValidationException {
	if(shipmentID==null||shipmentID.trim().isEmpty()) {
		throw new ValidationException("invalid Shipment ID");
	}
	return shipmentDAO.findShipment(shipmentID);
}

public List<Shipment> viewAllShipment(){
	return shipmentDAO.viewAllShipment();
}

public boolean addNewShipment (Shipment shipment) throws ValidationException {
	if(shipment==null) {
		throw new ValidationException("Shipment cnnot be null");
	}
	if(shipment.getShipmentID()==null||shipment.getShipmentID().trim().isEmpty()) {
		throw new ValidationException("Shipment ID required");
	}
	if (shipment.getItemDescription()==null||shipment.getItemDescription().trim().isEmpty()) {
		throw new ValidationException("Item Description required");
	}
	if(shipment.getTotalQuantity()<=0) {
		throw new ValidationException("Total Quantity must be greater than 0");
	}
	Shipment existing=shipmentDAO.findShipment(shipment.getShipmentID());
	if(existing!=null) {
	 throw new ValidationException("Shipment ID already exists");
	}
	return shipmentDAO.insertShipment(shipment);
}

public boolean removeShipment(String shipmentID) throws ValidationException,DispatchActiveException{
	if(shipmentID==null||shipmentID.trim().isEmpty()) {
		throw new ValidationException("Invalid Shipment ID");
	}
	return shipmentDAO.deleteShipment(shipmentID);
}

public boolean dispatchShipment(String shipmentID,String destination,int quantity) throws ValidationException,ShipmentUnavailableException{
if(shipmentID==null||shipmentID.trim().isEmpty()) {
	throw new ValidationException("Shipment ID required");
}
if(destination==null||destination.trim().isEmpty()) {
	throw new ValidationException("Destination required");
}
if(quantity<=0) {
	throw new ValidationException("Quantity must be greater than 0");
}
Shipment shipment=shipmentDAO.findShipment(shipmentID);
if(shipment==null) {
	return false;
}
if(quantity>shipment.getAvailableQuantity()) {
	throw new ShipmentUnavailableException("Insufficient stock available");
}
int newAvailable=shipment.getAvailableQuantity()-quantity;
boolean updated=shipmentDAO.updateAvailableQuantity(shipmentID, newAvailable);
if(!updated)
	return false;
Dispatch dispatch=new Dispatch();
dispatch.setDispatchID(dispatchDAO.generateDispatchID());
dispatch.setShipmentID(shipmentID);
dispatch.setDestination(destination);
dispatch.setQuantityDispatched(quantity);
dispatch.setDispatchDate(new java.sql.Date(System.currentTimeMillis()));
dispatch.setStatus("ACTIVE");
return dispatchDAO.recordDispatch(dispatch);
}

public boolean cancelDispatch(int dispatchID) throws ValidationException{
	if(dispatchID<=0) {
		throw new ValidationException("Invalid Dispatch ID");
	}
	return dispatchDAO.removeDispatch(dispatchID);
}
}
