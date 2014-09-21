package com.akkatest.factory;

import java.util.LinkedList;
import java.util.List;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;

import com.akkatest.enums.Detail;
import com.akkatest.enums.FurnityreType;
import com.akkatest.interfaces.Details;
import com.akkatest.interfaces.Furniture;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.SubFurnitureFactory;
import com.akkatest.interfaces.SubMaster;

/**
 * Реализация SubFurnitureFactory
 */
public class SubFurnitureFactoryImpl implements SubFurnitureFactory {
	private  SubMaster master1 = null;
	private  SubMaster master2 = null;
	private  SubMaster master3 = null;
	private List<Details> detailsLeg = new LinkedList<Details>();
	private List<Details> detailsLongLeg = new LinkedList<Details>();
	private List<Details> detailsBack = new LinkedList<Details>();
	private List<Details> detailsSurfaces = new LinkedList<Details>();
	ActorSystem myActorSystem = null;

	public SubFurnitureFactoryImpl(final ActorSystem actorSystem){
		master1 =
				(SubMaster) TypedActor.get(actorSystem).typedActorOf(
						new TypedProps<Master1>(SubMaster.class,Master1.class));
		master2 =
				(SubMaster) TypedActor.get(actorSystem).typedActorOf(
						new TypedProps<Master2>(SubMaster.class,Master2.class));
		master3 =
				(SubMaster) TypedActor.get(actorSystem).typedActorOf(
						new TypedProps<Master3>(SubMaster.class,Master3.class));
		myActorSystem = actorSystem;
	}

	@Override
	public void registerFurniture(final FurnityreType type,final FurnitureFactory factory) {
		master1.work(TypedActor.<SubFurnitureFactoryImpl>self(), factory, type);
		master2.work(TypedActor.<SubFurnitureFactoryImpl>self(), factory, type);
		master3.work(TypedActor.<SubFurnitureFactoryImpl>self(), factory, type);
	}

	@Override
	public void acceptDetails(final Details details,final FurnitureFactory factory) {
		final Detail detail = details.getType();
		switch (detail) {
		case surfaces:
			detailsSurfaces.add(details);
			break;
		case leg:
			detailsLeg.add(details);
			break;
		case longLeg:
			detailsLongLeg.add(details);
			break;
		case back:
			detailsBack.add(details);
			break;
		default:
			throw new RuntimeException("Type not found");
		}
		if ((detailsSurfaces.size() > 0) && (detailsLongLeg.size() > 0)){
			Furniture furniture = new Furniture();
			furniture.setType(FurnityreType.table);
			furniture.getDetails().add(detailsSurfaces.get(0));
			furniture.getDetails().add(detailsLongLeg.get(0));
			detailsSurfaces.remove(0);
			detailsLongLeg.remove(0);
			factory.registerFurniture(furniture);
		}
		if ((detailsLeg.size() > 0) && (detailsBack.size() > 0)){
			Furniture furniture = new Furniture();
			furniture.setType(FurnityreType.chair);
			furniture.getDetails().add(detailsLeg.get(0));
			furniture.getDetails().add(detailsBack.get(0));
			detailsLeg.remove(0);
			detailsBack.remove(0);
			factory.registerFurniture(furniture);
		}
	}
}
