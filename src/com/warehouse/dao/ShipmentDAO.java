package com.warehouse.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.warehouse.bean.Shipment;
import com.warehouse.util.DBUtil;

public class ShipmentDAO {
public Shipment	findShipment(String shipmentID) {
	Shipment shipment=null;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement
				("SELECT * FROM SHIPMENT_TBL WHERE SHIPMENT_ID=?");
		ps.setString(1, shipmentID);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			shipment=new Shipment();
			shipment.setShipmentID(rs.getString(1));
			shipment.setItemDescription(rs.getString(2));
			shipment.setTotalQuantity(rs.getInt(3));
			shipment.setAvailableQuantity(rs.getInt(4));
			shipment.setReceivedDate(rs.getDate(5));
		}
		rs.close();
		ps.close();
		con.close();
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return shipment;
}

public List<Shipment> viewAllShipment(){
	List<Shipment> list=new ArrayList<>();
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps= con.prepareStatement("SELECT *FROM SHIPMENT_TBL");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Shipment shipment=new Shipment();
			shipment.setShipmentID(rs.getString(1));
			shipment.setItemDescription(rs.getString(2));
			shipment.setTotalQuantity(rs.getInt(3));
			shipment.setAvailableQuantity(rs.getInt(4));
			shipment.setReceivedDate(rs.getDate(5));
			list.add(shipment);
		}
		rs.close();
		ps.close();
		con.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return list;
	}

public boolean insertShipment(Shipment shipment) {
	boolean flag=false;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO SHIPMENT_TBL VALUES(?,?,?,?,?)");
		ps.setString(1,shipment.getShipmentID());
		ps.setString(2, shipment.getItemDescription());
		ps.setInt(3,shipment.getTotalQuantity());
		ps.setInt(4,shipment.getAvailableQuantity());
		ps.setDate(5,shipment.getReceivedDate());
		int rows=ps.executeUpdate();
		if(rows>0)
			flag=true;
		con.commit();
		ps.close();
		con.close();
		
	}
	catch (Exception e){
		e.printStackTrace();
	}
	return flag;
}

public boolean updateAvailableQuantity(String shipmentID,int newCount) {
	boolean flag=false;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE SHIPMENT_TBL SET AVAILABLE_QUANTITY=? WHERE SHIPMENT_ID=?");
		ps.setInt(1, newCount);
		ps.setString(2, shipmentID);
		int rows=ps.executeUpdate();
		if(rows>0)
			flag=true;
		con.commit();
		ps.close();
		con.close();
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}
public boolean deleteShipment(String shipmentID) {
	boolean flag=false;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("DELETE FROM SHIPMENT_TBL WHERE SHIPMENT_ID=?");
		ps.setString(1, shipmentID);
		int rows=ps.executeUpdate();
		if(rows>0)
			flag=true;
		con.commit();
		ps.close();
		con.close();
		
			}
	catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}
}
