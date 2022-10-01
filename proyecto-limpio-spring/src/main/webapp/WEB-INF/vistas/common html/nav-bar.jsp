<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Logo</a>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="">Libros
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Novedades</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Top 100</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Comics y manga</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Locales</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">Guia de compra</a>
        </li>
      </ul>
      <form action="login">
        <button class="btn btn-secondary my-2 my-sm-0" name="inisiarSesion" type="submit">Iniciar Sesión</button>
      </form>
      <form action="registro ">
        <button class="btn btn-secondary my-2 my-sm-0 mx-3" name="registrarse" type="submit">Registrarse</button>
      </form>
    </div>
  </div>
</nav>