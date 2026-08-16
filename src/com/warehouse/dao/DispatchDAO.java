package com.warehouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.warehouse.bean.Dispatch;
import com.warehouse.util.DBUtil;

public class DispatchDAO {
public int generateDispatchID() {
	int id=81001;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("SELECT NVL(MAX(DISPATCH_ID),81000)+1 FROM DISPATCH_TBL");
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			id=rs.getInt(1);
		}
		rs.close();
		ps.close();
		con.close();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return id;
}
public boolean recordDispatch(Dispatch dispatch) {
	boolean flag=false;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO DISPATCH_TBL VALUES(?,?,?,?,?,?)");
		ps.setInt(1,dispatch.getDispatchID());
		ps.setString(2,dispatch.getShipmentID());
		ps.setString(3,dispatch.getDestination());
		ps.setInt(4,dispatch.getQuantityDispatched());
		ps.setDate(5,dispatch.getDispatchDate());
		ps.setString(6,dispatch.getStatus());
		int rows=ps.executeUpdate();
		if(rows>0) {
			flag=true;
		}
		con.commit();
		ps.close();
		con.close();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return flag;
}
public boolean removeDispatch(int dispatchID) {
	boolean flag=false;
	try {
		Connection con=DBUtil.getDBConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE DISPATCH_TBL SET STATUS='CANCELLED' WHERE DISPATCH_ID=?");
		ps.setInt(1, dispatchID);
		int rows=ps.executeUpdate();
		if(rows>0) {
			flag=true;}
		con.commit();
		ps.close();
		con.close();
		}
	catch(Exception e) {
		e.printStackTrace();
	}
	return flag;
}
}
