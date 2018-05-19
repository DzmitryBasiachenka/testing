<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="messages"/>

<div class="border border-danger text-center">

  <c:if test="${wrongUser != null}">
    <h5 class="text-danger"><fmt:message key="${wrongUser}"/></h5>
  </c:if>

  <c:if test="${subjectExists != null}">
    <h5 class="text-danger"><fmt:message key="${subjectExists}"/></h5>
  </c:if>

  <c:if test="${subjectSaved != null}">
    <h5 class="text-success"><fmt:message key="${subjectSaved}"/></h5>
  </c:if>

  <c:if test="${subjectEmpty != null}">
    <h5 class="text-danger"><fmt:message key="${subjectEmpty}"/></h5>
  </c:if>

  <c:if test="${numberNotMatch != null}">
    <h5 class="text-danger"><fmt:message key="${numberNotMatch}"/></h5>
  </c:if>

  <c:if test="${testEmpty != null}">
    <h5 class="text-danger"><fmt:message key="${testEmpty}"/></h5>
  </c:if>

  <c:if test="${testSaved != null}">
    <h5 class="text-success"><fmt:message key="${testSaved}"/></h5>
  </c:if>

  <c:if test="${testDeleted != null}">
    <h5 class="text-success"><fmt:message key="${testDeleted}"/></h5>
  </c:if>

  <c:if test="${testNameEmpty != null}">
    <h5 class="text-danger"><fmt:message key="${testNameEmpty}"/></h5>
  </c:if>

  <c:if test="${testEdited != null}">
    <h5 class="text-success"><fmt:message key="${testEdited}"/></h5>
  </c:if>

  <c:if test="${questionEmpty != null}">
    <h5 class="text-danger"><fmt:message key="${questionEmpty}"/></h5>
  </c:if>

  <c:if test="${questionEdited != null}">
    <h5 class="text-success"><fmt:message key="${questionEdited}"/></h5>
  </c:if>

  <c:if test="${testPassed != null}">
    <h5 class="text-success"><fmt:message key="${testPassed}"/></h5>
  </c:if>

  <c:if test="${questionSaved != null}">
    <h5 class="text-success"><fmt:message key="${questionSaved}"/></h5>
  </c:if>

  <c:if test="${statisticsDeleted != null}">
    <h5 class="text-success"><fmt:message key="${statisticsDeleted}"/></h5>
  </c:if>

  <c:if test="${userDeleted != null}">
    <h5 class="text-success"><fmt:message key="${userDeleted}"/></h5>
  </c:if>

  <c:if test="${questionDeleted != null}">
    <h5 class="text-success"><fmt:message key="${questionDeleted}"/></h5>
  </c:if>

  <c:if test="${roleDeleted != null}">
    <h5 class="text-success"><fmt:message key="${roleDeleted}"/></h5>
  </c:if>

  <c:if test="${roleAdded != null}">
    <h5 class="text-success"><fmt:message key="${roleAdded}"/></h5>
  </c:if>

  <c:if test="${roleExists != null}">
    <h5 class="text-danger"><fmt:message key="${roleExists}"/></h5>
  </c:if>

  <c:if test="${subjectUpdated != null}">
    <h5 class="text-success"><fmt:message key="${subjectUpdated}"/></h5>
  </c:if>

  <c:if test="${subjectDeleted != null}">
    <h5 class="text-success"><fmt:message key="${subjectDeleted}"/></h5>
  </c:if>

  <c:if test="${accountDeleted != null}">
    <h5 class="text-success"><fmt:message key="${accountDeleted}"/></h5>
  </c:if>

</div>
