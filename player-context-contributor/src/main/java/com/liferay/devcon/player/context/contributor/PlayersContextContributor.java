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

package com.liferay.devcon.player.context.contributor;

import com.liferay.portal.kernel.template.TemplateContextContributor;

import com.liferay.devcon.player.equipment.entity.Ball;
import com.liferay.devcon.player.equipment.entity.Equipment;
import com.liferay.devcon.player.equipment.entity.Uniform;
import com.liferay.devcon.player.equipment.renderer.EquipmentRendererUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	property = "type=" + TemplateContextContributor.TYPE_GLOBAL,
	service = TemplateContextContributor.class
)
public class PlayersContextContributor implements TemplateContextContributor 	{

	@Override
	public void prepare(
		Map<String, Object> contextObjects,
		HttpServletRequest httpServletRequest) {

		contextObjects.put("random", new Random());

		List<Equipment> equipment = new ArrayList<>();

		equipment.add(new Ball());
		equipment.add(new Uniform());

		contextObjects.put("equipment", equipment);
		contextObjects.put(
			"equipmentRendererUtil", _equipmentRendererUtil);
	}

	@Reference
	private EquipmentRendererUtil _equipmentRendererUtil;

}