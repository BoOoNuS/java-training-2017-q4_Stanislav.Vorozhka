<%@include file="/WEB-INF/view/jspf/common/page_info.jspf" %>
<%@include file="/WEB-INF/view/jspf/common/teglib.jspf" %>
<html>
<c:set var="title" value="Orders" scope="page"/>
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

<%-- Orders output --%>
<c:if test="${not empty requestScope.orders}">
    <table class="page-element" id="info-table">
        <tr>
            <th><fmt:message key="page.lang.order.number"/></th>
            <th><fmt:message key="page.lang.client"/></th>
            <th><fmt:message key="page.lang.car"/></th>
            <th><fmt:message key="page.lang.driver"/></th>
            <th><fmt:message key="page.lang.term"/></th>
            <th><fmt:message key="page.lang.state"/></th>
            <th><fmt:message key="page.lang.penalty"/></th>
        </tr>
        <c:forEach items="${requestScope.orders}" var="order">
            <tr>
                <td>${order.number}</td>
                <td>
                    <fmt:message key="page.lang.login"/> - ${order.user.login}<br/>
                    <fmt:message key="page.lang.full.name"/> - ${order.user.fullName}<br/>
                    <fmt:message key="page.lang.passport"/> - ${order.user.passport}<br/>
                    <fmt:message key="page.lang.blocked"/> - ${order.user.blocked}
                </td>

                <td>
                    <fmt:message key="page.lang.class"/> - ${order.car.clazz}<br/>
                    <fmt:message key="page.lang.mark"/> - ${order.car.mark}<br/>
                    <fmt:message key="page.lang.name"/> - ${order.car.name}<br/>
                    <fmt:message key="page.lang.cost"/> - ${order.car.cost}
                </td>

                <td>
                    <c:if test="${order.driver}">
                        <fmt:message key="page.lang.with.driver"/>
                    </c:if>

                    <c:if test="${!order.driver}">
                        <fmt:message key="page.lang.without.driver"/>
                    </c:if>
                </td>

                <td>${order.term}</td>
                <td>${order.state}</td>

                <c:if test="${not empty order.penalty.cause}">

                    <td>
                        <fmt:message key="page.lang.cause"/> - ${order.penalty.cause}<br/>
                        <fmt:message key="page.lang.cost"/> - ${order.penalty.cost}
                    </td>
                </c:if>

                <c:if test="${empty order.penalty.cause}">
                    <td><fmt:message key="page.lang.have.no.penalty"/></td>
                </c:if>

                <c:if test="${order.state.name == 'expectation'}">
                    <td>
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="confirmOrder">
                            <input type="hidden" name="orderNumber" value="${order.number}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.confirm"/>">
                        </form>
                    </td>

                    <td>
                        <form action="/controller">
                            <input type="hidden" name="command" value="toRejectOrder">
                            <input type="hidden" name="orderNumber" value="${order.number}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.reject"/>">
                        </form>
                    </td>
                </c:if>

                <c:if test="${order.state.name == 'paid'}">
                    <td>
                        <form action="/controller" method="post">
                            <input type="hidden" name="command" value="acceptCar">
                            <input type="hidden" name="orderNumber" value="${order.number}">
                            <input type="hidden" name="carId" value="${order.car.id}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.accept"/>">
                        </form>
                    </td>

                    <td>
                        <form action="/controller">
                            <input type="hidden" name="command" value="toSetPenalty">
                            <input type="hidden" name="orderNumber" value="${order.number}">
                            <input type="submit" class="btn btn-sm btn-primary"
                                   value="<fmt:message key="page.lang.penalty"/>">
                        </form>
                    </td>
                </c:if>
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
