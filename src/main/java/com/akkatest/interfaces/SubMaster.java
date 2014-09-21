package com.akkatest.interfaces;

import com.akkatest.enums.FurnityreType;

/**
 * Интерфейс для аутсорсинговой компании(для работы со своими мастерами)
 */
public interface SubMaster {
	void work(final SubFurnitureFactory sf,final FurnitureFactory factory,final FurnityreType type);
}
