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
		<div class="TODO">
			<form:form method="POST" action="${argiopeConstantUrl.REQ_MAP_RESET_IP_BLOCKING}" class="${argiopeConstantTestElement.CLASS_FORM_RESET_IP_BLOCKING}">
				<input type="hidden" 
					class="${argiopeConstantTestElement.CLASS_INPUT_RESET_IP_BLOCKING}"
					name="formInputValue"
					value=""
				 />
			</form:form>
		</div>
	</jsp:body>
</t:login>
