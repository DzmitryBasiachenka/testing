<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="Test" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
  <div class="alert alert-dark text-center" role="alert">
   <h1 class="text-danger">Страница тест</h1>
  </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
