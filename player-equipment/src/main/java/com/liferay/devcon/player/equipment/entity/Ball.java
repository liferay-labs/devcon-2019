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
public class Ball implements Equipment {

   public String getType() {
       return "ball";
   }

   public String getName() {
       return "Wolrd Cup 2018 Ball";
   }

   public Map<String, String> getProperties() {
       Map<String, String> properties = new HashMap<>();

       properties.put("color", "red");
       properties.put("imageUrl", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Soccer_ball.svg/1000px-Soccer_ball.svg.png");
       properties.put("height", "64px");
       properties.put("width", "64px;");

       return properties;
   }

}