<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>My Books</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="container-sm">
    <c:if test="${librosComprados.isEmpty() == true}">
        <p class="alert alert-info text-center my-3">No compraste libros aún</p>
    </c:if>

    <c:if test="${librosComprados.isEmpty() == false}">
        <c:forEach items="${librosComprados}" var="libro">
            <div class="d-flex w-50 flex-column container-fluid p-2 justify-content-center border-bottom my-2">
                <div class="d-flex w-100">
                    <a href="#">
                        <img src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="" class="p1"
                             style="width: 6em; height: 10em; margin-right: 1em;">
                    </a>
                    <div class="d-flex flex-column">
                        <a href="#">
                            <h5 class="py-2">${libro.titulo}</h5>
                        </a>
                        <p class="text-muted">Por: ${libro.autor}</p>
                        <div>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star text-warning"></span>
                            <span class="fa fa-star"></span>
                            <span class="text-danger">(3)</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</main>

</body>
</html>
