package com.akkatest.interfaces;

import com.akkatest.enums.FurnityreType;

/**
 * Интерфейс для компании, которая на аутсорсе
 */
public interface SubFurnitureFactory {

	/**
	 * Регистрация заказа.
	 */
	void registerFurniture(FurnityreType type, FurnitureFactory factory);

	/**
	 * Принимает детали в соответствии с заказом и отдает фабрике.
	 */
	void acceptDetails(Details details, FurnitureFactory factory);
}
