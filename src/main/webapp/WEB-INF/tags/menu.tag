<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.main" var="main"/>
<fmt:message key="t.menu.administrator" var="administrator"/>
<fmt:message key="t.menu.test" var="test"/>
<fmt:message key="t.menu.subject" var="subject"/>
<fmt:message key="t.menu.examination" var="examination"/>
<fmt:message key="t.menu.statistics" var="statistics"/>
<fmt:message key="t.menu.about" var="about"/>
<fmt:message key="t.menu.profile" var="profile"/>
<fmt:message key="t.menu.log.out" var="logOut"/>
<fmt:message key="t.menu.sign.up" var="signUp"/>
<fmt:message key="t.menu.sign.in" var="signIn"/>

<c:if test="${userSession == null}">
<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <a class="navbar-brand">
    <img src="<c:url value='/pictures/logo.png'/>" width="40" height="40" alt="">
  </a>
  <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item"><a class="nav-link"  href="/testing/">${main}</a></li>
        <li class="nav-item"><a class="nav-link"  href="/testing/about">${about}</a></li>
      </ul>
      <a class="btn btn-outline-secondary mr-sm-2" href="<c:url value='/registration'/>" role="button">${signUp}</a>
      <button class="btn btn-outline-secondary" type="button" data-toggle="modal" data-target="#login" data-whatever="@mdo">${signIn}</button>
  </div>
</nav>
</c:if>

<c:if test="${userSession != null}">
  <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggler" aria-controls="navbarToggler" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand">
      <img src="<c:url value='/pictures/logo.png'/>" width="40" height="40" alt="">
    </a>
    <div class="collapse navbar-collapse" id="navbarToggler">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item"><a class="nav-link"  href="/testing/">${main}</a></li>

        <c:set var="admin" scope="request" value="Admin" />
        <c:forEach var="role" items="${userSession.roles}">
          <c:if test="${role.roleName eq admin}">
            <li class="nav-item"><a class="nav-link"  href="/testing/admin">${administrator}</a></li>
          </c:if>
        </c:forEach>

        <c:set var="tutor" scope="request" value="Tutor" />
        <c:forEach var="role" items="${userSession.roles}">
          <c:if test="${role.roleName eq tutor}">
            <li class="nav-item"><a class="nav-link"  href="/testing/test/list">${test}</a></li>
            <li class="nav-item"><a class="nav-link"  href="/testing/subject">${subject}</a></li>
          </c:if>
        </c:forEach>

        <c:set var="student" scope="request" value="Student" />
        <c:forEach var="role" items="${userSession.roles}">
          <c:if test="${role.roleName eq student}">
            <li class="nav-item"><a class="nav-link"  href="/testing/examination">${examination}</a></li>
            <li class="nav-item"><a class="nav-link"  href="/testing/statistics/list">${statistics}</a></li>
          </c:if>
        </c:forEach>

        <li class="nav-item"><a class="nav-link"  href="/testing/about">${about}</a></li>
      </ul>
      <a class="btn btn-outline-success mr-sm-2" href="<c:url value='/profile'/>" role="button">${profile} ${userSession.getLogin()}</a>
      <form action="<c:url value='/logout'/>" method="GET">
        <button type="submit" class="btn btn-outline-secondary">${logOut}</button>
      </form>
    </div>
  </nav>
</c:if>
