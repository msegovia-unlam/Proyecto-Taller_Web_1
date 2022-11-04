<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <title>${usuario.nombre}</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="container mt-5">
    <c:if test="${not empty msjFollow}">
        <p class="alert alert-info text-center my-3">${msjFollow} ${usuario.nombre}!</p>
    </c:if>
    <div class="row">
        <div class="col-4 bg-dark text-center py-2">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" viewBox="0 0 24 24"
                 stroke-width="1.5" stroke="#fff" fill="none" stroke-linecap="round"
                 stroke-linejoin="round" style="width: 40%">
                <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                <circle cx="12" cy="12" r="9"/>
                <circle cx="12" cy="10" r="3"/>
                <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855"/>
            </svg>
        </div>
        <div class="col-8">
            <p style="font-size: 1.6em" class="px-3 border-bottom border-dark">${usuario.nombre}</p>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/red-social/seguir/${usuario.id}" method="post">
        <button type="submit" class="btn btn-info px-5 my-4">Seguir</button>
    </form>
</main>

</body>
</html>
