<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 12/10/2022
  Time: 01:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <%@include file="common html/bootstrap.html" %>
  <title>Home</title>
</head>
<body>

<%@include file="common html/nav-bar.jsp"%>

<main>
  <table class="table table-hover my-3 container">
    <thead >
    <tr>
      <th scope="col">Libro</th>
      <th scope="col">Titulo</th>
      <th scope="col">Cantidad</th>
      <th scope="col">Precio</th>
      <th scope="col">Quitar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="libro" items="${librosEnNovedad}">
      <tr>
        <td><img class="text-center w-25" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="${libro.titulo}-imagen"></td>
        <td>${libro.titulo}</td>
        <td><input class="w-25" type="number" min="1" max="99"></td>
        <td>$${libro.precioDeVenta}.00</td>
        <td class="d-flex gap-2">
          <form:form  action="${pageContext.request.contextPath}/admin/borrar-libro/${libro.id}">
            <button type="submit" class="btn btn-danger">
              <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-trash" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#ffffff" fill="none" stroke-linecap="round" stroke-linejoin="round">
                <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                <line x1="4" y1="7" x2="20" y2="7" />
                <line x1="10" y1="11" x2="10" y2="17" />
                <line x1="14" y1="11" x2="14" y2="17" />
                <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12" />
                <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3" />
              </svg>
            </button>
          </form:form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-end me-5 my-3">
    <button type="button" class="btn btn-primary ">Comprar</button>
  </div>
</main>


<%@include file="common html/footer.html"%>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
