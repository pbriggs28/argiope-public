<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:master>
	<jsp:attribute name="title">Admin</jsp:attribute>
	<jsp:attribute name="styles">
		<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/pages/admin.css">
	</jsp:attribute>
	<jsp:attribute name="scripts"></jsp:attribute>
	<jsp:body>
		<div id="admin-page-header" class="">
			<h2>Admin Page!</h2>
       </div>
	</jsp:body>
</t:master>