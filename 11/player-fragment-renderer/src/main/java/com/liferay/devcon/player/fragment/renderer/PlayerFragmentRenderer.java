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

package com.liferay.devcon.player.collection.contributor;

import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.fragment.util.FragmentEntryConfigUtil;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.FragmentRenderer;
import com.liferay.fragment.renderer.FragmentRendererContext;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;	
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = FragmentRenderer.class)
public class PlayerFragmentRenderer implements FragmentRenderer {

	@Override
	public String getCollectionKey() {
		return "players";
	}

	@Override
	public String getConfiguration(
		FragmentRendererContext fragmentRendererContext) {

		return JSONUtil.put(
			"fieldSets",
			JSONUtil.putAll(
				JSONUtil.put(
					"fields",
					JSONUtil.putAll(
						JSONUtil.put(
							"label", "Name"
						).put(
							"name", "name"
						).put(
							"type", "text"
						).put(
							"defaultValue", "Dynamically rendered Player"
						),
						JSONUtil.put(
							"label", "Color"
						).put(
							"name", "color"
						).put(
							"type", "colorPalette"
						).put(
							"defaultValue",
							JSONUtil.put(
								"cssClass", "primary"
							).put(
								"rgbValue", "rgb(11, 95, 255);"
							)
						),
						JSONUtil.put(
							"label", "Blog Post"
						).put(
							"name", "blogPost"
						).put(
							"type", "itemSelector"
						).put(
							"typeOptions",
							JSONUtil.put(
								"enableSelectTemplate", true
							)
						))))
		).toString();
	}

	@Override
	public String getLabel(Locale locale) {
		return "Player Renderer";
	}

	@Override
	public void render(
		FragmentRendererContext fragmentRendererContext,
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse)
	throws IOException {

		FragmentEntryLink fragmentEntryLink = 
			fragmentRendererContext.getFragmentEntryLink();

		JSONObject configuration = null;
		
		try {
			configuration = 
				FragmentEntryConfigUtil.getConfigurationJSONObject(
					getConfiguration(fragmentRendererContext), 
					fragmentEntryLink.getEditableValues(),
					fragmentRendererContext.getSegmentsExperienceIds());

			String name = configuration.getString("name");
			JSONObject color = configuration.getJSONObject("color");
			JSONObject blogPost = configuration.getJSONObject("blogPost");

			httpServletRequest.setAttribute("name", name);
			httpServletRequest.setAttribute("color", color.getString("rgbValue"));

			if (blogPost.getLong("entryId", 0) > 0) {
				ThemeDisplay themeDisplay = (ThemeDisplay)httpServletRequest.getAttribute(
					WebKeys.THEME_DISPLAY);

				StringBundler sb = new StringBundler(4);

				sb.append(themeDisplay.getPathMain());
				sb.append("/blogs/find_entry?entryId=");
				sb.append(blogPost.getLong("entryId"));
				sb.append("&showAllEntries=0");

				httpServletRequest.setAttribute("blogPostUrl", sb.toString());
			}
		}
		catch (Exception e) {
			throw new IOException(e);
		}

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, 
			httpServletResponse, "/player.jsp");
	}

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.devcon.player.fragment.renderer)"
	)
	private ServletContext _servletContext;

}