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

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.model.Company;
import com.liferay.info.pagination.Pagination;
import com.liferay.info.sort.Sort;
import com.liferay.info.list.provider.InfoListProvider;
import com.liferay.info.list.provider.InfoListProviderContext;

import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = InfoListProvider.class)
public class CompanyAssetInfoListProvider implements InfoListProvider<AssetEntry> {

	@Override
	public String getLabel(Locale locale) {
		return "Football Company Assets";
	}

	public List<AssetEntry> getInfoList(InfoListProviderContext infoListProviderContext) {
		return getInfoList(infoListProviderContext, Pagination.of(-1, -1), null);
	}

	public List<AssetEntry> getInfoList(
		InfoListProviderContext infoListProviderContext, Pagination pagination,
		Sort sort) {

		Company company = infoListProviderContext.getCompany();

		return _assetEntryLocalService.getCompanyEntries(
			company.getCompanyId(), pagination.getStart(), pagination.getEnd());
	}

	public int getInfoListCount(InfoListProviderContext infoListProviderContext) {
		Company company = infoListProviderContext.getCompany();

		return _assetEntryLocalService.getCompanyEntriesCount(
			company.getCompanyId());
	}

	@Reference
	private AssetEntryLocalService _assetEntryLocalService;

}