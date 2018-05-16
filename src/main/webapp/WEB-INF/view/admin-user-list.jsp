<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="s" %>

<s:html title="User list">
  <div class="col mt-1">
      <h2 class="text-center pb-1">Пользователи</h2>
      <table class="table table-striped" style="table-layout: fixed;">

        <thead class="thead-dark">
          <tr>
            <th scope="col" class="align-middle text-center" width="5%">№</th>
            <th scope="col" class="align-middle" width="15%">Логин</th>
            <th scope="col" class="align-middle" width="15%">Почтовый ящик</th>
            <th scope="col" class="align-middle" width="15%">Имя</th>
            <th scope="col" class="align-middle" width="15%">Фамилия</th>
            <th scope="col" class="align-middle" width="10%">Права</th>
            <th scope="col" class="align-middle" width="10%"></th>
          </tr>
        </thead>

        <tbody class="table-bordered">
          <c:set var="i" scope="request" value="${0}" />
          <c:forEach var="user" items="${users}">
            <tr>
              <th class="align-middle text-center" scope="row"><c:out value="${i=i+1}" /></th>
              <td class="align-middle">${user.login}</td>
              <td class="align-middle">${user.email}</td>
              <td class="align-middle">${user.firstName}</td>
              <td class="align-middle">${user.lastName}</td>
              <td class="align-middle">
                <c:forEach var="role" items="${user.roles}">
                  ${role.roleName}&nbsp;
                </c:forEach>
              </td>
              <td class="text-center align-middle">
                <a class="btn btn-light m-2" data-toggle="tooltip" data-placement="top" title="Edit" href="<c:url value='/admin/user/edit/${user.id}'/>" role="button">
                  <i class="far fa-edit"></i>
                </a>
                <a class="btn btn-light m-2" data-toggle="tooltip" data-placement="top" title="Delete" href="<c:url value='/admin/user/delete/${user.id}'/>" role="button">
                  <i class="fas fa-trash-alt"></i>
                </a>
              </td>
            </tr>
          </c:forEach>
        </tbody>

      </table>
    </div>
</s:html>
