<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.test" var="testCommon"/>
<fmt:message key="t.edit.test" var="editTest"/>
<fmt:message key="t.test.name" var="testNameCommon"/>
<fmt:message key="t.subject" var="subjectCommon"/>
<fmt:message key="t.questions" var="questionsCommon"/>
<fmt:message key="t.choose.question.edit" var="chooseQuestionEdit"/>
<fmt:message key="t.new.question" var="newQuestion"/>
<fmt:message key="t.cancel" var="cancel"/>
<fmt:message key="t.save" var="save"/>

<s:html title="${test.testName}">
 <div class="container">
     <div class="row">
       <div class="col-1"></div>

       <div class="col-10 border border-secondary rounded bg-light pt-3">
         <h4 class="text-center pb-2">${editTest}: ${test.testName}</h4>
         <form action="<c:url value='/test/edit/${test.id}'/>" method="POST">
           <div class="form-group row">
             <label class="col-2 col-form-label">${testNameCommon}</label>
             <div class="col-10">
               <input type="text" class="form-control" name="testName" value="${test.testName}" maxlength="64" required>
             </div>
           </div>

           <div class="form-group row">
             <label class="col-2 col-form-label">${subjectCommon}</label>
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
             <label class="col-2 col-form-label">${questionsCommon}</label>
               <div class="col-10">
                 <select class="form-control" name="questionSelect">
                   <option selected>${chooseQuestionEdit}</option>
                   <c:forEach var="question" items="${test.questions}">
                     <option>${question.questionName}</option>
                   </c:forEach>
                 </select>
                 <a class="badge badge-light" data-toggle="modal" data-target="#newQuestion" data-whatever="@mdo" role="button">${newQuestion}</a>
               </div>
           </div>

           <div class="row">
             <div class="col text-right mb-3">
               <a class="btn btn-secondary mr-sm-3" href="<c:url value='/test/list'/>" role="button">${cancel}</a>
               <button type="submit" class="btn btn-dark">${save}</button>
             </div>
           </div>
         </form>
       </div>

       <div class="col-1"></div>
     </div>
   </div>
</s:html>
