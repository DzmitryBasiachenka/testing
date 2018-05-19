<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.edit.data" var="editData"/>
<fmt:message key="t.login" var="login"/>
<fmt:message key="t.email" var="email"/>
<fmt:message key="t.first.name" var="firstName"/>
<fmt:message key="t.last.name" var="lastName"/>
<fmt:message key="t.new.password" var="newPassword"/>
<fmt:message key="t.confirm.password" var="confirmPassword"/>
<fmt:message key="t.role" var="role"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.save" var="save"/>

<div class="modal fade" id="userEdit" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h4 class="text-success">${userSession.login}</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="container">
        <form action="<c:url value='/user/edit'/>" method="POST">
          <div class="modal-body">
            <h4 class="text-center">${editData}</h4>
            <hr>
            <div class="form-group">
              <label><h4>${login}</h4></label>
              <input type="text" name="login" class="form-control" value=${userSession.login} readonly>
            </div>
            <div class="form-group">
              <label><h4>${email}</h4></label>
              <input type="email" name="email" class="form-control" value=${userSession.email} maxlength="32" required>
            </div>
            <div class="form-group">
              <label><h4>${firstName}</h4></label>
              <input type="text" name="firstName" class="form-control" value=${userSession.firstName} maxlength="64" required>
            </div>
            <div class="form-group">
              <label><h4>${lastName}</h4></label>
              <input type="text" name="lastName" class="form-control" value=${userSession.lastName} maxlength="64" required>
            </div>
            <div class="form-group">
              <label><h4>${newPassword}</h4></label>
              <input type="password" name="newPassword" class="form-control" maxlength="128">
            </div>
            <div class="form-group">
              <label><h4>${confirmPassword}</h4></label>
              <input type="password" name="confirmPassword" class="form-control" maxlength="128">
            </div>
            <div class="form-group">
              <label><h4>${role}</h4></label>
              <c:forEach var="role" items="${userSession.roles}">
                <input type="text" name="lastName" class="form-control" value=${role.roleName} readonly>
                <p></p>
              </c:forEach>
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
