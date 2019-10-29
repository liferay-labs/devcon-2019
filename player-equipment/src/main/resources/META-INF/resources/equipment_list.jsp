<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%@ page import="com.liferay.devcon.player.equipment.entity.Equipment"%>
<%@ page import="com.liferay.devcon.player.equipment.renderer.EquipmentRendererUtil"%>
<%@ page import="java.util.List"%>

<%
EquipmentRendererUtil equipmentRendererUtil = (EquipmentRendererUtil)request.getAttribute("equipmentRendererUtil");
List<Equipment> equipment = (List<Equipment>)request.getAttribute("equipment");
%>

<%
for (Equipment equipmentItem : equipment) {
%>
    <%= equipmentRendererUtil.render(equipmentItem, request, response) %>
<%
}
%>