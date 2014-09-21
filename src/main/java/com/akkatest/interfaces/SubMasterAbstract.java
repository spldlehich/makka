package com.akkatest.interfaces;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.akkatest.factory.FurnitureFactoryImpl;

/**
 * Абстракный класс с функцией производства частей мебели
 */
public abstract class SubMasterAbstract implements SubMaster {
	protected double myPrice = 5 ;
	protected final double mult = 1.05 ; // + 5%
	protected int sleepTime = 50 ;
	public SubMasterAbstract(){
		Context initCtx1;
		try {
			initCtx1 = new InitialContext();
			Context envCtx = (Context)initCtx1.lookup("java:comp/env");
			sleepTime = (Integer) envCtx.lookup("timeoutSubMaster");
			myPrice = (Double) envCtx.lookup("startPriceSubMaster");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void compile(){
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
