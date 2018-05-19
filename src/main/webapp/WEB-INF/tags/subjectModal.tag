<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.choose.subject.examination" var="subjectExamination"/>
<fmt:message key="t.subject" var="subject"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.save" var="save"/>

<div class="modal fade" id="subjectChoice" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4>${subjectExamination}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/examination/list'/>" method="POST">
          <div class="modal-body">
            <div class="form-group row">
              <label class="col-4 col-form-label">${subject}</label>
              <div class="col-8">
                <select class="form-control" name="subjectSelect">
                  <c:forEach var="subject" items="${subjects}">
                    <option>${subject.subjectName}</option>
                  </c:forEach>
                </select>
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
