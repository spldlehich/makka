package com.akkatest.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.akkatest.enums.FurnityreType;

public class Furniture {
	private FurnityreType type = FurnityreType.table;
	private List<Details> details = new ArrayList<Details>();
	private double price = 0;
	public FurnityreType getType() {
		return type;
	}
	public void setType(FurnityreType type) {
		this.type = type;
	}
	public List<Details> getDetails() {
		return details;
	}
	public void setDetails(List<Details> details) {
		this.details = details;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
