package com.akkatest.interfaces;

import com.akkatest.enums.Detail;

public class Details {
	private Detail type = Detail.leg;
	private int count = 0;
	private double price = 0;
	public Detail getType() {
		return type;
	}
	public void setType(Detail type) {
		this.type = type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
