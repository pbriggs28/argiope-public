<%@page contentType="text/html" pageEncoding="UTF-8" import="com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:master>
	<jsp:attribute name="title">Home</jsp:attribute>
	<jsp:attribute name="styles"></jsp:attribute>
	<jsp:body>
		<div class="${argiopeConstantTestElement.CLASS_PAGE_INDEX}">
       		<h1>This is the homepage!</h1>
       		<c:if test="${not empty loginSuccessMsg}">
				<div class="${argiopeConstantTestElement.CLASS_LOGIN_SUCCESS}">
					<h2>Successfully Logged In</h2>
				</div>
       		</c:if>
       </div>
	</jsp:body>
</t:master>