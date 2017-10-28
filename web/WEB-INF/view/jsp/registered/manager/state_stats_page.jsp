<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="State stats" scope="page"/>
<%@include file="/WEB-INF/view/jspf/common/head.jspf" %>
<body>

<%-- Menu --%>
<ul>
    <li><a href="/controller?command=toMainPage"><fmt:message key="page.lang.main"/></a></li>
    <%@include file="/WEB-INF/view/jspf/registrated/client/personal_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/registrated/manager/manage_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/registrated/admin/admin_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/language_menu.jspf" %>
    <%@include file="/WEB-INF/view/jspf/common/logout_menu.jspf" %>
</ul>
<hr/>

<c:if test="${not empty requestScope.stateCounters}">
    <table id="info-table">
        <tr>
            <th><fmt:message key="page.lang.state.name"/></th>
            <th><fmt:message key="page.lang.state.count"/></th>
        </tr>
        <c:forEach items="${requestScope.stateCounters}" var="stateCunter">
            <tr>
                <td>${stateCunter.state}</td>
                <td>${stateCunter.count}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<%-- Check permission --%>
<u:permit role="manager"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
