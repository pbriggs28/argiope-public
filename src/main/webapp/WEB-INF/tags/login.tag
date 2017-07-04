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
	
	<!-- Tag CSS Includes (Should match the jsp includes below -->
	<link rel="stylesheet" type="text/css" href="${argiopeConstantUrl.DOMAIN_PATH_STATIC}styles/templates/footer.css">
	
	<!-- Page Specific CSS Includes -->
	<jsp:invoke fragment="styles"></jsp:invoke>
</head>
<body>
	<div id="login-tag">
<!-- 		<div id="body" class="main"> -->
			<jsp:doBody/>
<!-- 		</div> -->
		<%@include file="/WEB-INF/views/templates/footer.jsp" %>
	</div>
	<jsp:invoke fragment="scripts"></jsp:invoke>
</body>
</html>