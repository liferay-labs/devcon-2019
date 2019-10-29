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

package com.liferay.devcon.player.equipment.list.renderer;

import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.devcon.player.equipment.entity.Equipment;
import com.liferay.devcon.player.equipment.renderer.EquipmentRendererUtil;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = InfoListRenderer.class)
public class EquipmentInfoListRenderer implements InfoListRenderer<Equipment> {

	public String getKey() {
		return "equipment";
	}

	public void render(
		List<Equipment> equipment, HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		RequestDispatcher requestDispatcher = _servletContext.getRequestDispatcher(
			"/equipment_list.jsp");
			
		httpServletRequest.setAttribute("equipment", equipment);
		httpServletRequest.setAttribute("equipmentRendererUtil", _equipmentRendererUtil);

		try {
			requestDispatcher.include(httpServletRequest, httpServletResponse);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.devcon.player.equipment)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	@Reference
	private EquipmentRendererUtil _equipmentRendererUtil;

	private ServletContext _servletContext;

}