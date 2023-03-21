package org.model;

public class Order extends Product {
	private int OrderID;
	private int uId;
	private int quantity;
	private String date;
	public Order(int orderID, int uId, int quantity, String date) {
		super();
		OrderID = orderID;
		this.uId = uId;
		this.quantity = quantity;
		this.date = date;
	}
	public Order() {
		super();
	}
	public Order(int uId, int quantity, String date) {
		super();
		this.uId = uId;
		this.quantity = quantity;
		this.date = date;
	}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Order [OrderID=" + OrderID + ", uId=" + uId + ", quantity=" + quantity + ", name=" + date + "]";
	}
	
	

}
