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

package com.liferay.devcon.player.equipment.renderer;

import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererTracker;
import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.taglib.servlet.PipingServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.devcon.player.equipment.entity.Equipment;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = EquipmentRendererUtil.class)
public class EquipmentRendererUtil {

	public String render(
			Equipment equipment, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) 
		throws Exception {

		InfoItemRenderer infoItemRenderer = _infoItemRendererTracker.getInfoItemRenderer("equipment");

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			httpServletResponse, unsyncStringWriter);

		infoItemRenderer.render(equipment, httpServletRequest, pipingServletResponse);

		return unsyncStringWriter.toString();
    }

	@Reference
	private InfoItemRendererTracker _infoItemRendererTracker;

}