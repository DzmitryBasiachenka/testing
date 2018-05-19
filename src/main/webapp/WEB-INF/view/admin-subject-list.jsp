<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.subject.list" var="subjectList"/>
<fmt:message key="t.subjects" var="subjectsCommon"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.edit" var="edit"/>
<fmt:message key="t.delete" var="delete"/>

<s:html title="${subjectList}">
<div class="row">
  <div class="col-2"></div>
  <div class="col-8 mt-1">
    <h2 class="text-center pb-1">${subjectsCommon}</h2>
    <table class="table table-striped" style="table-layout: fixed;">

      <thead class="thead-dark">
        <tr>
          <th scope="col" class="align-middle text-center" width="10%">№</th>
          <th scope="col" class="align-middle" width="75%">${subjectCommon}</th>
          <th scope="col" class="align-middle" width="15%"></th>
        </tr>
      </thead>

      <tbody class="table-bordered">
        <c:set var="i" scope="request" value="${0}" />
        <c:forEach var="subject" items="${subjects}">
          <tr>
            <th class="align-middle text-center" scope="row"><c:out value="${i=i+1}" /></th>
            <td class="align-middle">${subject.subjectName}</td>

            <td class="text-center align-middle text-center">
              <a class="btn btn-light" data-toggle="tooltip" data-placement="top" title="${edit}" href="<c:url value='/admin/subject/${subject.id}'/>" role="button">
                <i class="far fa-edit"></i>
              </a>
              <a class="btn btn-light ml-3" data-toggle="tooltip" data-placement="top" title="${delete}" href="<c:url value='/admin/subject/delete/${subject.id}'/>" role="button">
                <i class="fas fa-trash-alt"></i>
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>

    </table>
  </div>
  <div class="col-2"></div>
</div>

</s:html>
