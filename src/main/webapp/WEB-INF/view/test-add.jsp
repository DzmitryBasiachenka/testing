<%@ page language="java"
contentType="text/html; charset=utf-8"
pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="title" scope="request" value="New test" />
<jsp:include page="/WEB-INF/tiles/header.jsp" />
<div class="container">
    <div class="row">
      <div class="col-1"></div>
      <div class="col-10 border border-secondary">

  <form class="mt-3">
    <div class="form-group row">
      <label for="inputTestName" class="col-sm-2 col-form-label">Имя теста</label>
      <div class="col-sm-10">
        <input type="text" class="form-control" id="inputTestName" placeholder="Test">
      </div>
    </div>
    <div class="form-group row">
      <label for="inputSubject" class="col-sm-2 col-form-label">Предмет</label>
      <div class="col-10">
      <select id="inputSubject" class="form-control">
        <option selected>Subjects...</option>
        <option>...</option>
      </select>
      </div>
    </div>
    <div class="form-group row">
      <label for="inputQuestionName" class="col-sm-2 col-form-label">Вопрос</label>
      <div class="col-10">
        <input type="text" class="form-control" id="inputQuestionName" placeholder="Question">
        <div class="col-12">

          <!--<div class="form-group row">
          <div class="col-5">
            <div class="form-check">
                      <input class="form-check-input" type="checkbox" id="gridCheck1">
                      <label class="form-check-label" for="gridCheck1">
                        Example checkbox
                      </label>
                    </div>
                   <input type="text" class="form-control mt-1" placeholder="Answer choice">
                   </div>
           </div>-->

          <input type="text" class="form-control mt-1" placeholder="Answer">
          <input type="text" class="form-control mt-1" placeholder="Answer">
          <input type="text" class="form-control mt-1" placeholder="Answer">
          <input type="text" class="form-control mt-1" placeholder="Answer">
        </div>
      </div>
    </div>

    <div class="form-group row">
      <div class="col-sm-2">Checkbox</div>
      <div class="col-sm-10">
        <div class="form-check">
          <input class="form-check-input" type="checkbox" id="gridCheck1">
          <label class="form-check-label" for="gridCheck1">
            Example checkbox
          </label>
        </div>
      </div>
    </div>
    <div class="form-group row">
      <div class="col-sm-10">
        <button type="submit" class="btn btn-primary">Sign in</button>
      </div>
    </div>
  </form>

   </div>
   <div class="col-1"></div>
   </div>
   </div>
<jsp:include page="/WEB-INF/tiles/footer.jsp" />
