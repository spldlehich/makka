package com.akkatest.factory;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.japi.Creator;

import com.akkatest.enums.FurnityreType;
import com.akkatest.interfaces.Furniture;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.Master;
import com.akkatest.interfaces.SubFurnitureFactory;

/**
 * Реализация интерфейса
 */
public class FurnitureFactoryImpl implements FurnitureFactory{
	private static final Logger LOGGER = LoggerFactory.getLogger(FurnitureFactoryImpl.class);
	private SubFurnitureFactory subFactory = null;
	private List<Master> listMasters = new ArrayList<Master>();
	private static int K = 5;
	private int currentMaster = 0;
	private double totalPrice = 0;
	private long totalTable = 0;
	private long totalChair = 0;
	public FurnitureFactoryImpl(final ActorSystem actorSystem){
		try {
			Context initCtx1 = new InitialContext();
			Context envCtx = (Context)initCtx1.lookup("java:comp/env");
			K = (Integer) envCtx.lookup("masterFactoryK");
			subFactory =
					(SubFurnitureFactory) TypedActor.get(actorSystem).typedActorOf(
							new TypedProps<SubFurnitureFactoryImpl>(SubFurnitureFactory.class,
									new Creator<SubFurnitureFactoryImpl>() {
								public SubFurnitureFactoryImpl create(){
									return new SubFurnitureFactoryImpl(actorSystem);
								}
							}),"sub");
			for (int i = 0; i < K; ++i){
				listMasters.add((Master) TypedActor.get(actorSystem).typedActorOf(
						new TypedProps<MasterImpl>(Master.class,MasterImpl.class))	);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void requestFurniture(final String name) {
		FurnityreType type;
		if ("table".equals(name))
			type = FurnityreType.table;
		else
			type = FurnityreType.chair;
		if (subFactory != null)
			subFactory.registerFurniture(type,TypedActor.<FurnitureFactoryImpl>self());
	}

	@Override
	public void registerFurniture(final Furniture furniture) {
		listMasters.get(currentMaster).work(TypedActor.<FurnitureFactoryImpl>self(), furniture);
		++currentMaster;
		if (currentMaster >= K)
			currentMaster %= K;
	}

	@Override
	public void doneFurniture(final Furniture furniture) {
		if (FurnityreType.table.equals(furniture.getType()))
			++totalTable;
		else
			++totalChair;
		totalPrice += furniture.getPrice();
		LOGGER.info(": Price = " + String.format("%.2f",furniture.getPrice()) +
				", total table = " + totalTable +
				", total chair = " + totalChair +
				", total price = " + String.format("%.2f",totalPrice));
	}

}
