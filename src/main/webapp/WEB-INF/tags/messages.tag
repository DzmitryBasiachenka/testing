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
</div>
