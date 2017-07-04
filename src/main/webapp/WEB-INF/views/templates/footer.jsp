<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="footer-template">
	<h1>Footer</h1>
	<table>
		<tr>
			<td>&copy;2016 Argiope</td>
		</tr>
	</table>
	<div id="" class="${argiopeConstantTestElement.CLASS_AUTOM_TEST_ELEMS}">
		<!-- Elements required for automation testing scripts -->
		
		<!-- User authentication status -->
		<c:choose>
			<c:when test="${user != null}">
				<div class="${argiopeConstantTestElement.CLASS_STATUS_USER_AUTH_TRUE}"></div>
			</c:when>
			<c:otherwise>
				<div class="${argiopeConstantTestElement.CLASS_STATUS_USER_AUTH_FALSE}"></div>
			</c:otherwise>
		</c:choose>
		
		<%-- EVERYTHING RELATING TO IP ADDRESS BLOCKING MUST BE DISGUISED OR NOT VISIBLE IN PAGE SOURCE (use JSP comment) --%>
		<c:set var="ipBlockedStatus" value="${sessionScope[argiopeConstantSecurity.RESP_ATTR_IP_BLOCKED]}"/>
		<c:set var="numFailedLoginAttempts" value="${sessionScope[argiopeConstantSecurity.RESP_ATTR_NUM_FAILED_LOGIN_ATTEMPTS]}"/>
		<c:set var="ipBlockResetStatus" value="${YcArkxP7jC5RdoU7pVmE}"/>
		<%-- TODO: Find out what the correct syntax is for ${someVar is empty} --%>
		<c:if test="${ipBlockedStatus == '' or ipBlockedStatus == null}">
			<c:set var="ipBlockedStatus" value="false"/>
		</c:if>
		<c:if test="${numFailedLoginAttempts == '' or numFailedLoginAttempts == null}">
			<c:set var="numFailedLoginAttempts" value="0"/>
		</c:if>
		<c:if test="${ipBlockResetStatus == '' or ipBlockResetStatus == null}">
			<c:set var="ipBlockResetStatus" value="false"/>
		</c:if>
		
		<%-- Print the ENCRYPTED value of IP_BLOCKED status (true/false) --%>
		<div class="hide ${argiopeConstantTestElement.CLASS_STATUS_IP_BLOCKED}">${ipBlockedStatus}</div>
		
		<%-- Print the ENCRYPTED value of numFailedLoginAttempts --%>
		<div class="hide ${argiopeConstantTestElement.CLASS_STATUS_NUM_FAILED_LOGIN_ATTEMPTS}">${numFailedLoginAttempts}</div>
		
		<%-- If the IP_block has just been reset print true, else print false --%>
		<div class="hide ${argiopeConstantTestElement.CLASS_STATUS_IP_BLOCKING_RESET}">${ipBlockResetStatus}</div>
<%-- 		<div class="hide ${argiopeConstantTestElement.CLASS_STATUS_IP_BLOCKING_RESET}">${requestScope[argiopeConstantSecurity.RESP_ATTR_IP_BLOCK_RESET]}</div> --%>
	</div>
</div>