<%--
  Created by IntelliJ IDEA.
  User: Lucas
  Date: 21/10/2022
  Time: 01:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html class="bg-light">
<head>
    <title>YouBook</title>
</head>
<body>

<header>
  <nav class=" bg-dark d-flex justify-content-around py-1 align-items-center">
    <ul class="d-flex gap-5 my-0">
      <li class="list-unstyled text-white">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/red-social">Logo</a>
      </li>
      <li class="list-unstyled text-white">
        <a class="nav-link active" href="#">
          Home
        </a>
      </li>
      <li class="list-unstyled text-white">
        <a class="nav-link" href="#">MyBooks</a>
      </li>
    </ul>
    <form class="d-flex my-1">
      <input class="form-control me-sm-2" type="text" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    <div>
      <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="54"
           height="54" viewBox="0 0 24 24" stroke-width="1.5" stroke="#fff" fill="none" stroke-linecap="round"
           stroke-linejoin="round">
        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
        <circle cx="12" cy="12" r="9" />
        <circle cx="12" cy="10" r="3" />
        <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
      </svg>
    </div>
  </nav>


</header>

</body>
</html>