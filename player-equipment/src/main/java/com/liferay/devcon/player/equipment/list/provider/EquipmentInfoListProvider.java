/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.devcon.player.equipment.list.provider;

import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;
import com.liferay.devcon.player.equipment.entity.Ball;
import com.liferay.devcon.player.equipment.entity.Equipment;
import com.liferay.devcon.player.equipment.entity.Uniform;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = InfoListProvider.class)
public class EquipmentInfoListProvider implements InfoListProvider<Equipment> {

	@Override
	public String getLabel(Locale locale) {
		return "Football Player Equipment";
	}

	public List<Equipment> getInfoList(InfoListProviderContext infoListProviderContext) {
		return getInfoList(infoListProviderContext, null, null);
	}

	public List<Equipment> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		Sort sort) {

		List<Equipment> equipment = new ArrayList<>();

		equipment.add(new Ball());
		equipment.add(new Uniform());

		return equipment;
	}

	public int getInfoListCount(InfoListProviderContext infoListProviderContext) {
		return 2;
	}

}