<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <link rel="stylesheet" href="<c:url value='/css/style.css'/>" type="text/css"/>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
  <title>${title}</title>
  <link rel="icon" href="<c:url value='/pictures/logo.png'/>" type="image/x-icon">
</head>
<body data-spy="scroll" data-target="#scrollspyTest" data-offset="1">
<jsp:include page="/WEB-INF/tiles/login.jsp" />
<jsp:include page="/WEB-INF/tiles/user-edit.jsp" />
<jsp:include page="/WEB-INF/tiles/subject-add.jsp" />
  <div class="container-fluid">
    <jsp:include page="/WEB-INF/tiles/menu.jsp" />
    <jsp:include page="/WEB-INF/tiles/messages.jsp" />
