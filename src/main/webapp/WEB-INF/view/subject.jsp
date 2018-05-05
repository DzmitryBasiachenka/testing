<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="Subject" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
  <div class="container-fluide">
    <div class="row">
      <div class="col-4"></div>
      <div class="col-4">
        <ul class="list-group">
          <button class="btn btn-dark" type="button" data-toggle="modal" data-target="#subjectAdd" data-whatever="@mdo">
            <h4>Добавить предмет</h4>
          </button>
          <h3 class="card-header">Список предметов:</h3>
          <c:set var="i" scope="request" value="${0}" />
            <c:forEach var="subject" items="${subjects}">
              <li class="list-group-item align-middle" data-toggle="tooltip" data-placement="right" title="${subject.subjectName}">
                <h5 class="alert-link"><c:out value="${i=i+1}" />.&nbsp;${subject.subjectName}</h5>
              </li>
            </c:forEach>
        </ul>
      </div>
      <div class="col-4"></div>
    </div>
  </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
