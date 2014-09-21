package com.akkatest.factory;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.akkatest.interfaces.Details;
import com.akkatest.interfaces.Furniture;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.Master;

/**
 * Реализайия интерфейса Master
 */
public class MasterImpl implements Master {
	private double myPrice = 10;
	private int timeSleep = 100;
	public MasterImpl(){
		Context initCtx1;
		try {
			initCtx1 = new InitialContext();
			Context envCtx = (Context)initCtx1.lookup("java:comp/env");
			timeSleep = (Integer) envCtx.lookup("timeoutMaster");
			myPrice = (Double) envCtx.lookup("priceMaster");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void work(FurnitureFactory fc, Furniture furniture) {
		try {
			Thread.sleep(timeSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		double price = 0;
		List<Details> details = furniture.getDetails();
		for (int i = 0; i < details.size(); ++i){
			price += details.get(i).getCount()*details.get(i).getPrice();
		}
		price += myPrice;
		furniture.setPrice(price);
		fc.doneFurniture(furniture);
	}

}
