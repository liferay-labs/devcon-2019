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
import com.liferay.info.list.renderer.InfoListRendererTracker;
import com.liferay.devcon.player.equipment.entity.Equipment;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = EquipmentInfoListRendererUtil.class)
public class EquipmentInfoListRendererUtil {

	public String render(
			List<Equipment> equipment, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) 
		throws Exception{	

		InfoListRenderer infoListRenderer = _infoListRendererTracker.getInfoListRenderer(
			"equipment");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		infoListRenderer.render(equipment, httpServletRequest, pipingServletResponse);

		return unsyncStringWriter.toString();
	}
	

	@Reference
	private InfoListRendererTracker _infoListRendererTracker;

}