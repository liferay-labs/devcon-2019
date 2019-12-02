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

import com.liferay.asset.display.page.constants.AssetDisplayPageConstants;
import com.liferay.asset.display.page.model.AssetDisplayPageEntry;
import com.liferay.asset.display.page.service.AssetDisplayPageEntryLocalService;
import com.liferay.devcon.player.entity.Player;
import com.liferay.info.display.contributor.InfoDisplayContributor;
import com.liferay.info.display.contributor.InfoDisplayField;
import com.liferay.info.display.contributor.InfoDisplayObjectProvider;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.Portal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = InfoDisplayContributor.class)
public class PlayerInfoDisplayContributor
	implements InfoDisplayContributor<Player> {

	@Activate
	public void activate() throws Exception {
		ServiceContext serviceContext = new ServiceContext();

		Company company = _companyLocalService.getCompanyByWebId(
			"liferay.com");

		Group group = _groupLocalService.getGroup(
			company.getCompanyId(), GroupConstants.GUEST);

		_PLAYERS.put(1L, new Player(
			1L, "Player 1", "Player 1 description", "player-1", 
			group.getGroupId()));
		_PLAYERS.put(2L, new Player(
			2L, "Player 2", "Player 2 description", "player-2", 
			group.getGroupId()));

		for (Player player : _PLAYERS.values()) {
			AssetDisplayPageEntry assetDisplayPageEntry = 
				_assetDisplayPageEntryLocalService.fetchAssetDisplayPageEntry(
					player.getGroupId(), _portal.getClassNameId(Player.class),
					player.getId());

			if (assetDisplayPageEntry != null) {
				continue;
			}

			_assetDisplayPageEntryLocalService.addAssetDisplayPageEntry(
				group.getCreatorUserId(), player.getGroupId(), 
				_portal.getClassNameId(Player.class),player.getId(), 0L, 
				AssetDisplayPageConstants.TYPE_DEFAULT, serviceContext);
		}
	}

	@Override
	public String getClassName() {
		return Player.class.getName();
	}

	@Override
	public Set<InfoDisplayField> getInfoDisplayFields(
			long classTypeId, Locale locale)
		throws PortalException {

		Set<InfoDisplayField> infoDisplayFields = new HashSet<>();

		infoDisplayFields.add(new InfoDisplayField("name", "Name", "text"));

		return infoDisplayFields;
	}

	@Override
	public Map<String, Object> getInfoDisplayFieldsValues(
			Player player, Locale locale)
		throws PortalException {

		Map<String, Object> infoDisplayFieldValues = new HashMap<>();

		infoDisplayFieldValues.put("name", player.getName());
	
		return infoDisplayFieldValues;
	}

	@Override
	public InfoDisplayObjectProvider getInfoDisplayObjectProvider(long classPK)
		throws PortalException {

		Player player = _PLAYERS.get(classPK);

		return new PlayerInfoDisplayObjectProvider(player);
	}

	@Override
	public InfoDisplayObjectProvider<Player>
			getInfoDisplayObjectProvider(long groupId, String urlTitle)
		throws PortalException {

		Player player = null;
		
		for (Player existingPlayer : _PLAYERS.values()) {
			if (existingPlayer.getUrl().equals(urlTitle) && 
				existingPlayer.getGroupId() == groupId) {

				player = existingPlayer;

				break;
			}
		}
		
		return new PlayerInfoDisplayObjectProvider(player);
	}

	@Override
	public String getInfoURLSeparator() {
		return "/player/";
	}

	@Override
	public String getLabel(Locale locale) {
		return "Football Player";
	}

	private static final Map<Long, Player> _PLAYERS = new HashMap<Long, Player>();

	@Reference
	private AssetDisplayPageEntryLocalService _assetDisplayPageEntryLocalService;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private Portal _portal;

	private static final Log _log = LogFactoryUtil.getLog(
		PlayerInfoDisplayContributor.class);

}