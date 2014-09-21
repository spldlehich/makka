package com.akkatest.factory;

import com.akkatest.enums.Detail;
import com.akkatest.enums.FurnityreType;
import com.akkatest.interfaces.Details;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.SubFurnitureFactory;
import com.akkatest.interfaces.SubMasterAbstract;

/**
 * Мастер аутсорсинговой компании собирающий поверхность стола
 */
public class Master3  extends SubMasterAbstract {

	@Override
	public void work(final SubFurnitureFactory sf,final FurnitureFactory factory,final FurnityreType type) {
		if (FurnityreType.table.equals(type)){
			compile();
			Details details = new Details();
			details.setCount(1);
			details.setPrice(myPrice);
			details.setPrice(10);
			details.setType(Detail.surfaces);
			sf.acceptDetails(details, factory);
			myPrice *= mult;
		}
	}

}
