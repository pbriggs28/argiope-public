<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="header-template">
	<div id="top-right-nav">
		<div id="top-right-nav-username">
			<a href="#">${user.username}</a>
			<br/>
			<br/>
			<div>First name: ${user.firstName}</div>
			<div>Last name: ${user.lastName}</div>
			<div>Roles:</div>
			<table>
				<c:forEach var="authority" items="${user.authorities}">
					<tr>
						<td>&nbsp;&nbsp;${authority.name}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<form id="logout-form" action="${argiopeConstantUrl.REQ_MAP_LOGOUT}" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		</form>
		<div id="top-right-nav-logout">
			<a href="javascript:formSubmit()" id="link-logout" class="${argiopeConstantTestElement.CLASS_BUTTON_LOGOUT}">
				<img src="${argiopeConstantUrl.DOMAIN_PATH_STATIC}images/icons/icon-logout.png">
			</a>
		</div>
		<div id="top-right-nav-settings">
			<a href="#">
				<img src="${argiopeConstantUrl.DOMAIN_PATH_STATIC}images/icons/icon-gear.png">
			</a>
		</div>
	</div>
	<script type="text/javascript">
		function formSubmit() {
			document.getElementById("logout-form").submit();
		}
	</script>
</div>