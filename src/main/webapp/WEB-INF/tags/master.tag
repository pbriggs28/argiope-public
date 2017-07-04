<%@tag description="Overall Page template" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>


<html>
<head>
	<title><jsp:invoke fragment="title"></jsp:invoke></title>
	<!-- Favicon -->
<!-- 	<link rel="icon" type="image/png" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}images/favicon/favicon-blule-black-diamond-32x32.png"> -->
	
	<!-- Generic CSS Includes -->
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/tags/css-reset.css">
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/tags/default.css">
	
	<!-- Page Specific CSS Includes -->
	<jsp:invoke fragment="styles"></jsp:invoke>
	
	<!-- Tag CSS Includes (Should match the jsp includes below -->
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/templates/leftnav.css">
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/templates/header.css">
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/templates/footer.css">
</head>
<body>
	<div id="master-tag">
		<%@include file="/WEB-INF/views/templates/header.jsp" %>
		<%@include file="/WEB-INF/views/templates/leftnav.jsp" %>
		<div id="body" class="main">
			<jsp:doBody/>
		</div>
		<%@include file="/WEB-INF/views/templates/footer.jsp" %>
	</div>
	<jsp:invoke fragment="scripts"></jsp:invoke>
</body>
</html>