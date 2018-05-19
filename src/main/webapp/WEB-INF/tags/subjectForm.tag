<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.new.subject" var="newSubject"/>
<fmt:message key="t.subject" var="subject"/>
<fmt:message key="t.subject.placeholder" var="subjectPlaceholder"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.save" var="save"/>

<div class="modal fade" id="subjectAdd" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>${newSubject}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/subject/add'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label class="col-4 col-form-label">${subject}</label>
              <div class="col-8">
                <input type="text" name="subjectName" class="form-control" placeholder="${subjectPlaceholder}" maxlength="64" required>
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
