<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<fmt:setBundle basename="messages"/>
<fmt:message key="t.menu.examination" var="examination"/>
<fmt:message key="t.to.start.testing" var="toStartTesting"/>
<fmt:message key="t.start" var="start"/>

<s:html title="${examination}">
  <div class="conteiner-fluide">
    <div class="row p-3"></div>
    <div class="row">
      <div class="col text-center mt-5 mb-3">
        <h1 class="text-secondary">${toStartTesting}</h1>
      </div>
    </div>
    <div class="row mt-3">
      <div class="col text-center">
        <button class="btn btn-light rounded-circle"  type="button" data-toggle="modal" data-target="#subjectChoice"
          data-whatever="@mdo" data-toggle="tooltip" data-placement="top" title="${start}">
          <i class="far fa-play-circle fa-9x"></i>
        </button>
      </div>
    </div>
  </div>
</s:html>