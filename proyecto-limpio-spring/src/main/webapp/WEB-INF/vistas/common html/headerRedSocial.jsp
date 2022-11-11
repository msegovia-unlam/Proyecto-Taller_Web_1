<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <nav class=" bg-dark d-flex justify-content-around py-1 align-items-center">
    <ul class="d-flex gap-5 my-0 justify-content-center">
      <li class="list-unstyled text-white">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/red-social/">Logo</a>
      </li>
      <li class="list-unstyled text-white">
        <a class="nav-link active" href="${pageContext.request.contextPath}/red-social/">
          Home
        </a>
      </li>
      <li class="list-unstyled text-white">
        <a class="nav-link" href="${pageContext.request.contextPath}/red-social//my-books">MyBooks</a>
      </li>
      <li class="list-unstyled text-white btn btn-primary">
        <a class="nav-link" href="${pageContext.request.contextPath}/">Ir a la tienda</a>
      </li>
    </ul>
    <form class="d-flex my-1" method="get" action="${pageContext.request.contextPath}/red-social/busqueda">
      <input class="form-control me-sm-2" type="text" placeholder="Buscar" name="busquedaLibro">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    <div>

      <a class="nav-link" href="${pageContext.request.contextPath}/red-social/perfil">

        <img class="rounded-circle " style="width:54px"
             src="${pageContext.request.contextPath}/img/${usuario.imagen.id}.jpg"
             alt="foto-perfil">
      </a>
    </div>
  </nav>


</header>
