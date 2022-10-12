<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 12/10/2022
  Time: 00:32
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
  <article class="container">
    <form action="buscar-home" class="d-flex">
      <input class="form-control me-sm-2" type="text" name="buscar" placeholder="Buscá tu libro...">
      <button class="btn btn-primary my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    <div class="grid  mt-2 ">
      <div class="row gap-3 justify-content-center">

        <c:forEach items="${librosEnNovedad}" var="libro">

          <div class="card col-3 " style="width: 225px">
            <div class="card-body">
              <a class="text-decoration-none" href="libro/${libro.id}">
                <h4 class="card-title text-uppercase text-center">${libro.titulo}</h4>
                <img class="text-center w-100" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="${libro.titulo}-imagen">
              </a>
              <p class="card-text my-1">${libro.autor}</p>
              <h4 class="card-text">$${libro.precioDeVenta},00</h4>
            </div>
          </div>

        </c:forEach>

      </div>
    </div>

    <div class="d-grid">
      <div class="m-auto my-3 justify-content-center">
        <ul class="pagination">
          <li class="page-item disabled">
            <a class="page-link" href="#">&laquo;</a>
          </li>
          <li class="page-item active">
            <a class="page-link" href="#">1</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">2</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">3</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">4</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">5</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">&raquo;</a>
          </li>
        </ul>
      </div>
    </div>
  </article>
</main>


<%@include file="common html/footer.html"%>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>