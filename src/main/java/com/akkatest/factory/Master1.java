package com.akkatest.factory;

import com.akkatest.enums.Detail;
import com.akkatest.enums.FurnityreType;
import com.akkatest.interfaces.Details;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.SubFurnitureFactory;
import com.akkatest.interfaces.SubMasterAbstract;

/**
 * Мастер аутсорсинговой компании собирающий ножки
 */
public class Master1 extends SubMasterAbstract {
	@Override
	public void work(final SubFurnitureFactory sf,final FurnitureFactory factory,final FurnityreType type) {
		compile();
		Details details = new Details();
		details.setCount(4);
		if (FurnityreType.table.equals(type)){
			details.setPrice(2*myPrice);
			details.setType(Detail.longLeg);
		} else {
			details.setPrice(myPrice);
			details.setType(Detail.leg);
		}
		myPrice *= mult;
		sf.acceptDetails(details, factory);
	}

}
