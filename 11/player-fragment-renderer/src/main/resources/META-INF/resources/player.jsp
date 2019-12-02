<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />

<%
String name = (String)request.getAttribute("name");
String color = (String)request.getAttribute("color");
String blogPostUrl = (String)request.getAttribute("blogPostUrl");
%>

<div style="background-color: <%= color %>;">
    <span class=""><%= name %></span>

    <c:if test="<%= blogPostUrl != null %>">
        <div class="mt-2">
            <a href="<%= blogPostUrl %>">Blog post about <%= name %></a>
        </div>
    </c:if>
</div>