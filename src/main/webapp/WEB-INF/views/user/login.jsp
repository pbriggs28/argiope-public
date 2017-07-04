<%@page contentType="text/html" pageEncoding="UTF-8" import="com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:login>
	<jsp:attribute name="title">Login</jsp:attribute>
	<jsp:attribute name="styles">
		<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/pages/login.css">
	</jsp:attribute>
	<jsp:body>
		<div class="${argiopeConstantTestElement.CLASS_PAGE_LOGIN}">
			<form:form method="POST" action="${argiopeConstantUrl.REQ_MAP_LOGIN}" id="form-user">
				<div id="form-user-top">
					<div class="form-user-field-container">
						<form:label path="username">Username:</form:label>
						<form:input path="username" cssClass="${argiopeConstantTestElement.CLASS_INPUT_LOGIN_FORM_USERNAME}" />
					</div>
					<div class="form-user-field-container">
						<form:label path="password">Password:</form:label>
						<form:password path="password" cssClass="${argiopeConstantTestElement.CLASS_INPUT_LOGIN_FORM_PASSWORD}" />
					</div>
				</div>
				<div id="form-user-bottom">
					<div>
						<!-- Error messages -->
						<c:choose>
							<c:when test="${sessionScope[argiopeConstantSecurity.RESP_ATTR_IP_BLOCKED] == true}">
								<p id="form-user-error-msg" class="${argiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM_IP_BLOCKED}">Please contact your system administrator.</p>
							</c:when>
							<c:when test="${not empty loginError}">
								<p id="form-user-error-msg" class="${argiopeConstantTestElement.CLASS_ERROR_LOGIN_FORM}">Login error: ${loginError}</p>
							</c:when>
							<c:when test="${not empty logoutSuccessMsg}">
	       						<p id="form-user-success-msg" class="${argiopeConstantTestElement.CLASS_SUCCESS_LOGOUT}">${logoutSuccessMsg}</p>
							</c:when>
						</c:choose>
					</div>
					<input type="submit" value="Submit" id="form-user-button" class="button-style-1"/>
				</div>
				<!-- CSRF token automatically placed at end of login form if enabled -->
			</form:form>
		</div>
	</jsp:body>
</t:login>
