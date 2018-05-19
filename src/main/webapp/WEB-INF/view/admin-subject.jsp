<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.edit.subject" var="editSubject"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.cancel" var="cancel"/>
<fmt:message key="t.save" var="save"/>

<s:html title="${subject.subjectName}">
 <div class="container">
   <div class="row mt-5"></div>
   <div class="row">
     <div class="col-3"></div>

     <div class="col-6 border p-3">
       <form action="<c:url value='/admin/subject/edit/${subject.id}'/>" method="POST">
       <h3 class="text-center">${editSubject}</h3>
       <hr>

       <div class="form-group">
         <label><h4>${subjectCommon}</h4></label>
         <input type="text" class="form-control" name="subjectInput" value=${subject.subjectName} required>
       </div>

       <div class="row pt-2">
         <div class="col text-right mb-3">
           <a class="btn btn-secondary mr-2" href="<c:url value='/admin/subject/list'/>" role="button">${cancel}</a>
           <button type="submit" class="btn btn-dark" name="subjectCurrentName" value="${subject.subjectName}">${save}</button>
         </div>
       </div>
     </div>

     <div class="col-3"></div>
   </div>
 </div>
</s:html>
