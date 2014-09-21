package com.akkatest.factory;

import com.akkatest.enums.Detail;
import com.akkatest.enums.FurnityreType;
import com.akkatest.interfaces.Details;
import com.akkatest.interfaces.FurnitureFactory;
import com.akkatest.interfaces.SubFurnitureFactory;
import com.akkatest.interfaces.SubMasterAbstract;

/**
 * Мастер аутсорсинговой компании собирающий спинки
 */
public class Master2  extends SubMasterAbstract {

	@Override
	public void work(final SubFurnitureFactory sf,final FurnitureFactory factory,final FurnityreType type) {
		if (FurnityreType.chair.equals(type)){
			compile();
			Details details = new Details();
			details.setCount(1);
			details.setPrice(myPrice);
			details.setPrice(10);
			details.setType(Detail.back);
			sf.acceptDetails(details, factory);
			myPrice *= mult;
		}
	}

}
