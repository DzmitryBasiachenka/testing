<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.sign.up" var="signUp"/>
<fmt:message key="t.login" var="loginCommon"/>
<fmt:message key="t.please.choose.login" var="chooseLogin"/>
<fmt:message key="t.email" var="emailCommon"/>
<fmt:message key="t.email.example" var="emailExample"/>
<fmt:message key="t.first.name" var="firstNameCommon"/>
<fmt:message key="t.please.choose.first.name" var="chooseFirstName"/>
<fmt:message key="t.last.name" var="lastNameCommon"/>
<fmt:message key="t.please.choose.last.name" var="chooseLastName"/>
<fmt:message key="t.password" var="passwordCommon"/>
<fmt:message key="t.please.choose.password" var="choosePassword"/>
<fmt:message key="t.confirm.password" var="confirmPassword"/>
<fmt:message key="t.please.choose.confirm.password" var="chooseConfirmPassword"/>
<fmt:message key="t.role" var="roleCommon"/>
<fmt:message key="t.cancel" var="cancel"/>
<fmt:message key="t.save" var="save"/>

<s:html title="${signUp}">
<div class="container">
  <div class="row">
    <div class="col-2"></div>

    <div class="col-8 border border-secondary bg-light mb-2">
      <form action="<c:url value='/user/add'/>" method="POST" class="needs-validation mt-3" novalidate>
        <h3 class="text-center">${signUp}</h3></label>
        <hr>
        <div class="text-center">

          <c:if test="${dataSaved != null}">
            <h5 class="text-success"><fmt:message key="${dataSaved}"/></h5>
          </c:if>

          <c:if test="${loginExists != null}">
            <h5 class="text-danger"><fmt:message key="${loginExists}"/></h5>
          </c:if>

          <c:if test="${emailExists != null}">
            <h5 class="text-danger"><fmt:message key="${emailExists}"/></h5>
          </c:if>

          <c:if test="${emailWrong != null}">
            <h5 class="text-danger"><fmt:message key="${emailWrong}"/></h5>
          </c:if>

          <c:if test="${passwordsNotEquals != null}">
            <h5 class="text-danger"><fmt:message key="${passwordsNotEquals}"/></h5>
          </c:if>

          <c:if test="${userEmpty != null}">
            <h5 class="text-danger"><fmt:message key="${userEmpty}"/></h5>
          </c:if>

        </div>

          <div class="form-group">
            <label for="validationLogin"><h4>${loginCommon}</h4></label>
            <input type="text" name="login" class="form-control" id="validationLogin" maxlength="32" required>
            <div class="invalid-feedback">
              ${chooseLogin}
            </div>
          </div>

          <div class="form-group">
            <label for="validationLogin"><h4>${emailCommon}</h4></label>
            <input type="email" name="email" class="form-control" id="validationEmail" maxlength="32" required>
            <div class="invalid-feedback">
              ${emailExample} root@gmail.com
            </div>
          </div>

          <div class="form-group">
            <label for="validationFirstName"><h4>${firstNameCommon}</h4></label>
            <input type="text" name="firstName" class="form-control" id="validationFirstName" maxlength="64" required>
            <div class="invalid-feedback">
              ${chooseFirstName}
            </div>
          </div>

          <div class="form-group">
            <label for="validationLastName"><h4>${lastNameCommon}</h4></label>
            <input type="text" name="lastName" class="form-control" id="validationLastName" maxlength="64" required>
            <div class="invalid-feedback">
              ${chooseLastName}
            </div>
          </div>

          <div class="form-group">
            <label for="validationPassword"><h4>${passwordCommon}</h4></label>
            <input type="password" name="password" class="form-control" id="validationPassword" maxlength="128" required>
            <div class="invalid-feedback">
              ${choosePassword}
            </div>
          </div>

          <div class="form-group">
            <label for="validationConfirmPassword"><h4>${confirmPassword}</h4></label>
            <input type="password" name="confirmPassword" class="form-control" id="validationConfirmPassword" maxlength="128" required>
            <div class="invalid-feedback">
              ${chooseConfirmPassword}
            </div>
          </div>

          <div class="form-group">
            <label for="validationRole"><h4>${roleCommon}</h4></label>
            <input type="text" class="form-control" id="validationRole" value="User" readonly>
          </div>

          <div class="text-right mb-3">
            <a class="btn btn-secondary mr-sm-3" href="<c:url value='/'/>" role="button">${cancel}</a>
            <button class="btn btn-dark" type="submit">${save}</button>
          </div>

        </form>
        </div>
      <div class="col-2"></div>
    </div>
  </div>
</s:html>
