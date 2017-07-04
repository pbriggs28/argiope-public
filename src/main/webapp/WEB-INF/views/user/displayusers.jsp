<%@page contentType="text/html" pageEncoding="UTF-8" import="com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:master>
	<jsp:attribute name="title">Display Users</jsp:attribute>
	<jsp:attribute name="styles">
		<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/pages/display-users.css">
	</jsp:attribute>
	<jsp:body>
		<div id="displayusers" class="${argiopeConstantTestElement.CLASS_PAGE_DISPLAY_USER}">
			<h1>Users</h1>
			<div id="table-display-users-wrapper">
				<table id="table-display-users" class="${argiopeConstantTestElement.CLASS_TABLE_DISPLAY_USERS}">
					<tr>
						<th>Username</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Delete</th>
					</tr>
					<c:forEach var="userResult" items="${listUsers}">
					    <tr>      
					        <td class="${argiopeConstantTestElement.CLASS_TEXT_DISPLAY_USERS_USERNAME}">${userResult.username}</td>
					        <td>${userResult.firstName}</td>
					        <td>${userResult.lastName}</td>
					        <td id="table-col-delete-user">
					        	<a href="${argiopeConstantUrl.REQ_MAP_DELETE_USER}?id=${userResult.id}&username=${userResult.username}">
					        		<img src="${argiopeConstantUrl.DOMAIN_PATH_STATIC}images/icons/user/icon-user-delete.png" id="button-display-users-delete" class="${argiopeConstantTestElement.CLASS_BUTTON_DISPLAY_USERS_DELETE}"/>
					        	</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<c:if test="${not empty deleteUserError}">
				<div id="error-delete-user" class="${argiopeConstantTestElement.CLASS_ERROR_DELETE_USER}">${deleteUserError}</div>
			</c:if>
			<c:if test="${not empty deleteUserSuccess}">
				<div id="success-delete-user" class="${argiopeConstantTestElement.CLASS_SUCCESS_DELETE_USER}">${deleteUserSuccess}</div>
			</c:if>
		</div>
	</jsp:body>
</t:master>
