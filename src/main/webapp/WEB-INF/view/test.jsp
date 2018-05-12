<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="${test.testName}">
 <div class="container">
     <div class="row">
       <div class="col-1"></div>

       <div class="col-10 border border-secondary rounded bg-light pt-3">
         <h4 class="text-center pb-2">Редактировать тест <label class="text-success">${test.testName}</label></h4>
         <form action="<c:url value='/test/edit/${test.id}'/>" method="POST">
           <div class="form-group row">
             <label for="inputTestName" class="col-2 col-form-label">Имя теста</label>
             <div class="col-10">
               <input type="text" class="form-control" id="inputTestName" name="testName" value="${test.testName}" required>
             </div>
           </div>

           <div class="form-group row">
             <label class="col-2 col-form-label">Предмет</label>
               <div class="col-10">
                 <select class="form-control" name="subjectSelect">
                   <option selected>${test.subject.subjectName}</option>
                   <c:forEach var="subjectEdit" items="${subjects}">
                     <c:if test="${subjectEdit.subjectName != test.subject.subjectName}">
                       <option>${subjectEdit.subjectName}</option>
                     </c:if>
                   </c:forEach>
                 </select>
             </div>
           </div>

           <div class="form-group row">
             <label class="col-2 col-form-label">Вопросы</label>
               <div class="col-10">
                 <select class="form-control" name="questionSelect">
                   <option selected>Выберите вопрос для редактирования</option>
                   <c:forEach var="question" items="${test.questions}">
                     <option>${question.questionName}</option>
                   </c:forEach>
                 </select>
               </div>
           </div>

           <div class="row">
             <div class="col text-right mb-3">
               <a class="btn btn-secondary mr-sm-3" href="<c:url value='/test/list'/>" role="button">Отмена</a>
               <button type="submit" class="btn btn-dark">Сохранить</button>
             </div>
           </div>
         </form>
       </div>

       <div class="col-1"></div>
     </div>
   </div>
</s:html>
