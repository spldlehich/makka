package com.akkatest.factory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.akkatest.interfaces.FurnitureFactory;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.japi.Creator;

@Controller
public class Order {
	final static ActorSystem actorSystem = ActorSystem.create("actor-system");
	final static FurnitureFactory factory =
			(FurnitureFactory) TypedActor.get(actorSystem).typedActorOf(
					new TypedProps<FurnitureFactoryImpl>(FurnitureFactory.class,
							new Creator<FurnitureFactoryImpl>() {
						public FurnitureFactoryImpl create(){
							return new FurnitureFactoryImpl(actorSystem);
						}
					}),"factory");
	@RequestMapping(value = "/order/{name}", method = RequestMethod.GET)
	public String hello(@PathVariable String name,ModelMap model) {
		if (("chair".equals(name)) || "table".equals(name)){
			factory.requestFurniture(name);
			model.addAttribute("msg", name);
		} else
			model.addAttribute("msg", "Furniture not found");
		return "order";
	}
}

