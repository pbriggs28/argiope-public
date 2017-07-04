<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<t:master>
	<jsp:attribute name="title">Access Denied</jsp:attribute>
	<jsp:attribute name="styles">
		<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/pages/403.css">
	</jsp:attribute>
	<jsp:attribute name="scripts"></jsp:attribute>
	<jsp:body>
		<div id="access-denied-page-header" class="">
			<h2>Access Denied!</h2>
       </div>
	</jsp:body>
</t:master>