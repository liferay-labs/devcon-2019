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

 package com.liferay.devcon.player.entity;

 /**
 * @author Pavel Savinov
 */
 public class Player {

    public Player(
        long id, String name, String description,
        String url, long groupId) {

        _id = id;
        _name = name;
        _description = description;
        _url = url;
        _groupId = groupId;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public String getUrl() {
        return _url;
    }

    public long getGroupId() {
        return _groupId;
    }

    private final long _id;
    private final String _name;
    private final String _description;
    private final String _url;
    private final long _groupId;

 }