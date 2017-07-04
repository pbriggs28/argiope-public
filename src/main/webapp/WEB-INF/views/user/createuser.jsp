<%@page contentType="text/html" pageEncoding="UTF-8" import="com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<t:master>
	<jsp:attribute name="title">Create User</jsp:attribute>
	<jsp:attribute name="styles">
		<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/pages/create-user.css">
	</jsp:attribute>
	<jsp:attribute name="scripts"></jsp:attribute>
	<jsp:body>
		<div class="${argiopeConstantTestElement.CLASS_PAGE_CREATE_USER}">
				<form:form method="POST" action="${argiopeConstantUrl.REQ_MAP_CREATE_USER}" id="form-user">
					<div id="form-user-top">
						<div class="form-user-field-container">
							<form:label path="firstName">First Name:</form:label>
							<form:input path="firstName" id="" class="input ${argiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_FIRST_NAME}" />
						</div>
						<div class="form-user-field-container">
							<form:label path="lastName">Last Name:</form:label>
							<form:input path="lastName" id="" class="${argiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_LAST_NAME}" />
						</div>
						<div class="form-user-field-container">
							<form:label path="username">Username:</form:label>
							<form:input path="username" class="${argiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_USERNAME}" />
						</div>
						<div class="form-user-field-container">
							<form:label path="password">Password:</form:label>
							<form:password path="password" class="${argiopeConstantTestElement.CLASS_INPUT_CREATE_USER_FORM_PASSWORD}" />
						</div>
						<div id="form-user-bottom">
							<c:if test="${not empty createUserError}">
								<div class="${argiopeConstantTestElement.CLASS_ERROR_CREATE_USER_FORM}">
									<p id="form-user-error-msg">Error creating new user: ${createUserError}</p>
								</div>
							</c:if>
							<c:if test="${not empty createUserSuccess}">
								<div class="${argiopeConstantTestElement.CLASS_SUCCESS_CREATE_USER}">
									<p id="form-user-success-msg">${createUserSuccess}</p>
								</div>
							</c:if>
							<input type="submit" value="Create" id="form-user-button"/>
						</div>
					</div>
				</form:form>
		</div>
	</jsp:body>
</t:master>