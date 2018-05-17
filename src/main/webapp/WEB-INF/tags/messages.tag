<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="border border-danger text-center">
  <c:if test="${wrongUserMessage != null}">
    <h5 class="text-danger">${wrongUserMessage}</h5>
  </c:if>
  <c:if test="${subjectExists != null}">
    <h5 class="text-danger te">${subjectExists}</h5>
  </c:if>
  <c:if test="${subjectEmpty != null}">
    <h5 class="text-danger">${subjectEmpty}</h5>
  </c:if>
  <c:if test="${numberNotMatch != null}">
    <h5 class="text-danger">${numberNotMatch}</h5>
  </c:if>
  <c:if test="${testEmpty != null}">
    <h5 class="text-danger">${testEmpty}</h5>
  </c:if>
  <c:if test="${testSaved != null}">
    <h5 class="text-success">${testSaved}</h5>
  </c:if>
  <c:if test="${testDeleted != null}">
    <h5 class="text-success">${testDeleted}</h5>
  </c:if>
  <c:if test="${testNameEmpty != null}">
    <h5 class="text-danger">${testNameEmpty}</h5>
  </c:if>
  <c:if test="${testEdited != null}">
    <h5 class="text-success">${testEdited}</h5>
  </c:if>
  <c:if test="${questionEmpty != null}">
    <h5 class="text-danger">${questionEmpty}</h5>
  </c:if>
  <c:if test="${questionEdited != null}">
    <h5 class="text-success">${questionEdited}</h5>
  </c:if>
  <c:if test="${testPassed != null}">
    <h5 class="text-success">${testPassed}</h5>
  </c:if>
  <c:if test="${questionSaved != null}">
    <h5 class="text-success">${questionSaved}</h5>
  </c:if>
  <c:if test="${statisticsDeleted != null}">
    <h5 class="text-success">${statisticsDeleted}</h5>
  </c:if>
  <c:if test="${userDeleted != null}">
    <h5 class="text-success">${userDeleted}</h5>
  </c:if>
  <c:if test="${questionDeleted != null}">
    <h5 class="text-success">${questionDeleted}</h5>
  </c:if>
  <c:if test="${roleDeleted != null}">
    <h5 class="text-success">${roleDeleted}</h5>
  </c:if>
  <c:if test="${roleAdded != null}">
    <h5 class="text-success">${roleAdded}</h5>
  </c:if>
  <c:if test="${roleExists != null}">
    <h5 class="text-danger">${roleExists}</h5>
  </c:if>
  <c:if test="${subjectUpdated != null}">
    <h5 class="text-success">${subjectUpdated}</h5>
  </c:if>
  <c:if test="${subjectDeleted != null}">
    <h5 class="text-success">${subjectDeleted}</h5>
  </c:if>
</div>
