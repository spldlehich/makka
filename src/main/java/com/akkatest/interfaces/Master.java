package com.akkatest.interfaces;

/**
 * Интерфейс для мастера, который работает на фабрике
 */
public interface Master {

	/**
	 * собирает единицу мебели
	 */
	void work(FurnitureFactory fc, Furniture furniture);
}
