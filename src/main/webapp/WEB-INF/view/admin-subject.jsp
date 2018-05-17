<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Subject">
 <div class="container">
   <div class="row mt-5"></div>
   <div class="row">
     <div class="col-3"></div>

     <div class="col-6 border p-3">
       <form action="<c:url value='/admin/subject/edit/${subject.id}'/>" method="POST">
       <h3 class="text-center">Редактировать предмет</h3>
       <hr>

       <div class="form-group">
         <label><h4>Предмет</h4></label>
         <input type="text" class="form-control" name="subjectInput" value=${subject.subjectName} required>
         <div class="invalid-feedback">
           Please choose subject.
         </div>
       </div>

       <div class="row pt-2">
         <div class="col text-right mb-3">
           <a class="btn btn-secondary mr-2" href="<c:url value='/admin/subject/list'/>" role="button">Отмена</a>
           <button type="submit" class="btn btn-dark" name="subjectCurrentName" value="${subject.subjectName}">Сохранить</button>
         </div>
       </div>
     </div>

     <div class="col-3"></div>
   </div>
 </div>
</s:html>
