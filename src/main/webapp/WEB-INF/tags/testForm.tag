<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.new.test" var="newTest"/>
<fmt:message key="t.test.name" var="testName"/>
<fmt:message key="t.test.name.placeholder" var="testNamePlaceholder"/>
<fmt:message key="t.subject" var="subject"/>
<fmt:message key="t.new.subject" var="newSubject"/>
<fmt:message key="t.count.questions" var="countQuestions"/>
<fmt:message key="t.count.questions.placeholder" var="countQuestionsPlaceholder"/>
<fmt:message key="t.from.1.to.100" var="from1To100"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.save" var="save"/>

<div class="modal fade" id="testAdd" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>${newTest}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/test/add'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label class="col-4 col-form-label">${testName}</label>
              <div class="col-8">
                <input type="text" name="testName" class="form-control" placeholder="${testNamePlaceholder}" maxlength="64" required>
              </div>
            </div>
            <div class="form-group row">
              <label class="col-4 col-form-label">${subject}</label>
              <div class="col-8">
                <select class="form-control" name="subjectSelect">
                  <c:forEach var="subject" items="${subjects}">
                    <option>${subject.subjectName}</option>
                  </c:forEach>
                </select>
                <a class="badge badge-light" href="<c:url value='/subject'/>">${newSubject}</a>
              </div>
            </div>
            <div class="form-group row">
              <label class="col-4 col-form-label">${countQuestions}</label>
              <div class="col-8">
                <input type="text" name="countQuestions" class="form-control" placeholder="${countQuestionsPlaceholder}" required>
                <small class="form-text text-muted">${from1To100}</small>
              </div>
            </div>
          </div>

          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>
            <button type="submit" class="btn btn-dark">${save}</button>
          </div>
        </form>
      </div>

    </div>
  </div>
</div>
