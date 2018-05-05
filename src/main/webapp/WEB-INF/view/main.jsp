<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="Main">
  <div class="alert alert-secondary mb-0" role="alert">
    <h4 class="text-center">Добро пожаловать на ресурс "Тестирование"</h4>
  </div>
  <div id="carouselIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselIndicators" data-slide-to="0" class="active"></li>
      <li data-target="#carouselIndicators" data-slide-to="1"></li>
      <li data-target="#carouselIndicators" data-slide-to="2"></li>
      <li data-target="#carouselIndicators" data-slide-to="3"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img class="d-block w-100" src="<c:url value='/pictures/tests.jpg' />" alt="First slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="<c:url value='/pictures/tests1.jpg' />" alt="Second slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="<c:url value='/pictures/tests2.jpg' />" alt="Second slide">
      </div>
      <div class="carousel-item">
        <img class="d-block w-100" src="<c:url value='/pictures/tests3.jpg' />" alt="Third slide">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselIndicators" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselIndicators" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</s:html>