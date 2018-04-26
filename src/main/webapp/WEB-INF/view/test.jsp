<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="Test" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
    <div class="container-fluid">
      <div class="row">
        <nav class="col-4 bg-light" id="scrollspyTest">
          <ul class="list-group list-position">
          <div class="btn-group">
            <button type="button" class="btn btn-dark dropdown-toggle btn-lg btn-block" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Настройка
            </button>
            <div class="dropdown-menu dropdown-menu-right">
              <button class="dropdown-item" type="button" data-toggle="modal" data-target="#add-article" data-whatever="@mdo">Добавить</button>
              <button class="dropdown-item" type="button">Редактировать</button>
              <button class="dropdown-item" type="button">Удалить</button>
            </div>
          </div>
          <h4 class="card-header">Список тестов:</h4>
          <c:forEach var="test" items="${tests}">
            <a class="list-group-item text-dark alert-link" href="#${test.id}">${test.id}</a>
          </c:forEach>
          </ul>
        </nav>
        <div class="col-8 bg-light">
        <c:forEach var="test" items="${tests}">
          <div id="${test.id}">
            <h3>${test.testName}</h3>
            <p>${test.subject.subjectName}
            Откроется диалоговое окно «Смена размера изображения», в котором в разделе «Размер изображения» вам нужно изменить размер длины или ширины
            (в нашем случаи обоих параметров).

            Для примера я изменю ширину рисунка до 1000 пикселей, а ширина у меня автоматически изменилась до 800 пикселей. Чтобы значение автоматически
             не изменялось – нажмите на кнопку «Сохранить пропорции». Визуально она из себя представляет два звенья цепочки, которые соединены между собой
              третьим звеньям.
             При нажатии последнее пропадет, и числа не будут автоматически подстраиваться. На рисунке ниже эта кнопка указана стрелочкой.
             Откроется диалоговое окно «Смена размера изображения», в котором в разделе «Размер изображения» вам нужно изменить размер длины или ширины
                         (в нашем случаи обоих параметров).

                         Для примера я изменю ширину рисунка до 1000 пикселей, а ширина у меня автоматически изменилась до 800 пикселей. Чтобы значение автоматически
                          не изменялось – нажмите на кнопку «Сохранить пропорции». Визуально она из себя представляет два звенья цепочки, которые соединены между собой
                           третьим звеньям.
                          При нажатии последнее пропадет, и числа не будут автоматически подстраиваться. На рисунке ниже эта кнопка указана стрелочкой.
                          Откроется диалоговое окно «Смена размера изображения», в котором в разделе «Размер изображения» вам нужно изменить размер длины или ширины
                                      (в нашем случаи обоих параметров).

                                      Для примера я изменю ширину рисунка до 1000 пикселей, а ширина у меня автоматически изменилась до 800 пикселей. Чтобы значение автоматически
                                       не изменялось – нажмите на кнопку «Сохранить пропорции». Визуально она из себя представляет два звенья цепочки, которые соединены между собой
                                        третьим звеньям.
                                       При нажатии последнее пропадет, и числа не будут автоматически подстраиваться. На рисунке ниже эта кнопка указана стрелочкой.</p>
          </div>
        </c:forEach>
        </div>
      </div>
    </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
