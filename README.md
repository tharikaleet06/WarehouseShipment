# WarehouseShipment

A simple Java-based Warehouse Shipment Management System developed using Core Java concepts.
The application helps manage shipments and dispatch operations efficiently through a console-based interface.

---

# Technologies Used

* Java
* Eclipse IDE
* JDBC
* Oracle Database
* Exception Handling
* Object-Oriented Programming (OOP)

---

# Features

* Add new shipment details
* View shipment details
* View all shipments
* Update available shipment quantity
* Delete shipment records
* Generate dispatch ID
* Record shipment dispatch
* Cancel dispatch
* Remove dispatch records
* Validate shipment availability
* Handle shipment and dispatch exceptions

---

# Project Structure

```text id="l4p7dx"
WarehouseShipment/
│
├── src/com/warehouse
│   │
│   ├── app
│   │   └── WarehouseMain.java
│   │
│   ├── bean
│   │   ├── Dispatch.java
│   │   └── Shipment.java
│   │
│   ├── dao
│   │   ├── DispatchDAO.java
│   │   └── ShipmentDAO.java
│   │
│   ├── service
│   │   └── DispatchService.java
│   │
│   └── util
│       ├── DBUtil.java
│       ├── DispatchActiveException.java
│       ├── ShipmentUnavailableException.java
│       └── ValidationException.java
```

---

# DAO Functionalities

## DispatchDAO

* Generate dispatch ID
* Record dispatch details
* Remove dispatch records

## ShipmentDAO

* Find shipment details
* View all shipments
* Insert shipment details
* Update available quantity
* Delete shipment records

---

# Service Functionalities

## DispatchService

* View shipment details
* View all shipments
* Add new shipment
* Remove shipment
* Dispatch shipment
* Cancel dispatch

---

# Exception Handling

The project includes custom exceptions for handling errors:

* DispatchActiveException
* ShipmentUnavailableException
* ValidationException

---

# How to Run the Project

1. Import project into Eclipse IDE
2. Configure Oracle Database connection in `DBUtil.java`
3. Open `WarehouseMain.java`
4. Run the Java application
5. Use console options to perform operations

---

# Sample Operations

* Add Shipment
* View Shipment
* View All Shipments
* Dispatch Shipment
* Cancel Dispatch
* Delete Shipment

---

# Author

Tharika P
