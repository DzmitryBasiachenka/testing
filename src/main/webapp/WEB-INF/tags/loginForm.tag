<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.login" var="login"/>
<fmt:message key="t.password" var="password"/>
<fmt:message key="t.close" var="close"/>
<fmt:message key="t.menu.sign.in" var="signIn"/>

<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h5>Авторизация</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <form action="<c:url value='/login'/>" method="POST">
        <div class="modal-body">
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">${login}</label>
            <input type="text" name="login" class="form-control"  maxlength="32" required>
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">${password}</label>
            <input type="password" name="password" class="form-control"  maxlength="128" required>
          </div>
        </div>

        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">${close}</button>
          <button type="submit" class="btn btn-dark">${signIn}</button>
        </div>
      </form>

    </div>
  </div>
</div>
