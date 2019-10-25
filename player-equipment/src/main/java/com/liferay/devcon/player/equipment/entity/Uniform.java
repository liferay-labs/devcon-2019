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

package com.liferay.devcon.player.equipment.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavel Savinov
 */
public class Uniform implements Equipment {

   public String getType() {
       return "uniform";
   }

   public String getName() {
       return "Wolrd Cup 2018 Uniform";
   }

   public Map<String, String> getProperties() {
       Map<String, String> properties = new HashMap<>();

       properties.put("color", "blue");
       properties.put("imageUrl", "https://upload.wikimedia.org/wikipedia/commons/8/8f/Soccer_Jersey_White-Azure_%28slash%29.png");
       properties.put("height", "200px");
       properties.put("width", "300px");

       return properties;
   }

}