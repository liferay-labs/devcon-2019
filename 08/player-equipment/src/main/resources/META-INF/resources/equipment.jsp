<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%@ page import="java.util.Map"%>

<%
String name = (String)request.getAttribute("name");
Map<String, String> properties = (Map<String, String>)request.getAttribute("properties");
%>

<div>
    <h3><%= name %></h3>
    <hr>
    <img src='<%= properties.get("imageUrl") %>' 
         style='background-color: <%= properties.get("color") %>; height: <%= properties.get("height") %>; width: <%= properties.get("width") %>;'>
</div>