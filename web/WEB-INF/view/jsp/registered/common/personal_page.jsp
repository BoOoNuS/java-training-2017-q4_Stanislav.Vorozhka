<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Personal" scope="page"/>
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

<div class="page-element">
    <fmt:message key="page.lang.role"/>: ${sessionScope.user.role.name}<br/>
    <fmt:message key="page.lang.full.name"/>: ${sessionScope.user.fullName}<br/>

    <%-- Check passport --%>
    <c:if test="${not empty sessionScope.user.passport}">
        <fmt:message key="page.lang.passport"/>: ${sessionScope.user.passport}<br/><br/>
    </c:if>

    <c:if test="${empty sessionScope.user.passport}">
        <br/>
        <form action="/controller" method="post">
            <input type="hidden" name="command" value="setPassport">
            <fmt:message key="page.lang.passport"/><br/>
            <input name="passport" minlength="8" maxlength="8">
            <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.set"/>"><br/>
        </form>
    </c:if>

    <form action="/controller">
        <input type="hidden" name="command" value="toEditUser">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.edit"/>"><br/>
    </form>

    <form action="/controller">
        <input type="hidden" name="command" value="toClientOrders">
        <input type="submit" class="btn btn-sm btn-primary" value="<fmt:message key="page.lang.orders"/>">
    </form>
</div>

<%-- Check permission --%>
<u:permit role="client"/>

<%-- Footer --%>
<%@include file="/WEB-INF/view/jspf/common/footer.jspf" %>
</body>
</html>
