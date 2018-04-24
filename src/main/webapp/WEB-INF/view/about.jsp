<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="About" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
 <div class="jumbotron">
   <h1 class="display-7">О ресурсе</h1>
   <hr>
   <p class="lead">Администрация сайта приветствует вас! На данном ресурсе у вас есть возможность проходить тестирование, по
     имеющимся в системе тестам, а так же создавать свои тесты и вопросы к ним. Для того, чтобы создавать тесты, вам необходимо
     получить права "Тьютор". Статистика о прохождении тестов доступна для просмотра в личном кабинете.
   <hr>
   <p>Версия v1.0/2018</p>
 </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
