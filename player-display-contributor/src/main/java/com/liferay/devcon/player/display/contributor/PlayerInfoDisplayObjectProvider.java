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

package com.liferay.devcon.player.display.contributor;

import com.liferay.devcon.player.entity.Player;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Locale;

/**
 * @author Pavel Savinov
 */
public class PlayerInfoDisplayObjectProvider
	implements InfoDisplayObjectProvider<Player> {

	public PlayerInfoDisplayObjectProvider(Player player)
		throws PortalException {

		_player = player;
	}

	@Override
	public long getClassNameId() {
		return PortalUtil.getClassNameId(Player.class);
	}

	@Override
	public long getClassPK() {
		return _player.getId();
	}

	@Override
	public long getClassTypeId() {
		return 0;
	}

	@Override
	public String getDescription(Locale locale) {
		return _player.getDescription();
	}

	@Override
	public Player getDisplayObject() {
		return _player;
	}

	@Override
	public long getGroupId() {
		return _player.getGroupId();
	}

	@Override
	public String getKeywords(Locale locale) {
		return "";
	}

	@Override
	public String getTitle(Locale locale) {
		return _player.getName();
	}

	@Override
	public String getURLTitle(Locale locale) {
		return _player.getUrl();
	}

	private final Player _player;

}