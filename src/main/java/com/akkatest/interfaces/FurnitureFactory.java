package com.akkatest.interfaces;
/**
 * Интерфейс фабрики по производству мебели.
 */
public interface FurnitureFactory {

	/**
	 * Заказ мебели
	 */
	void requestFurniture(String name);

	/**
	 * регистрация единицы мебели от суб-подрядчика для дальнейшей сборки
	 */
	void registerFurniture(Furniture furniture);

	/**
	 * регистрация единицы мебели для выставления счетов
	 */
	void doneFurniture(Furniture furniture);
}

