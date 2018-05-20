<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<%@ attribute name="title" required="false" rtexprvalue="true" type="java.lang.String" %>
<%@ attribute name="titleKey" required="false" rtexprvalue="true" type="java.lang.String" %>

<c:if test="${not empty titleKey}">
    <fmt:message key="${titleKey}" var="title" />
</c:if>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
      <link rel="stylesheet" href="<c:url value='/css/bootstrap.min.css'/>"/>
      <link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"/>
      <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">

      <link rel="icon" href="<c:url value='/pictures/logo.png'/>" type="image/x-icon">

      <title>${title}</title>
    </head>
    <body data-spy="scroll" data-target="#scrollspyTest" data-offset="1" class="bg-light">

        <s:loginForm />
        <s:userEditForm />
        <s:subjectForm />
        <s:testForm />
        <s:subjectModal />
        <s:questionNewModal />

        <div class="container-fluid">

            <s:menu />
            <s:messages />

            <jsp:doBody />
        </div>

        <script src="<c:url value='/js/jquery-3.2.1.min.js' />"></script>
        <script src="<c:url value='/js/bootstrap.min.js' />"></script>

        <script>
          $(function () {
            $('[data-toggle="tooltip"]').tooltip()
          })
        </script>

        <script>
          (function() {
            'use strict';
            window.addEventListener('load', function() {
              var forms = document.getElementsByClassName('needs-validation');
              var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                  if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                  }
                  form.classList.add('was-validated');
                }, false);
              });
            }, false);
          })();
        </script>
    </body>
</html>
