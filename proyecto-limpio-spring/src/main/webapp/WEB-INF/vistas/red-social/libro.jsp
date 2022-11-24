<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        .estrella {
            font-size: 1.3em;
        }
    </style>
    <title>${libro.titulo}</title>
</head>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="container w-75 my-4 d-flex">
    <div>
        <img src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="" style="width: 17em">
        <div class="my-3">
            <form action="${pageContext.request.contextPath}/libro/${libro.id}">
                <button type="submit" class="btn btn-primary px-5">Comprar</button>
            </form>
        </div>
    </div>
    <div class="px-4">
        <h2 class="">${libro.titulo}</h2>
        <span>${libro.autor}</span>

        <br>

        <div style="font-size: 1.5em">
        <%
            int calificacion = (int)pageContext.findAttribute("calificacion");

            for(int i=0; i< calificacion; i++){
                out.print("<span class='fa fa-star text-warning'></span>");
            }

            for(int i=0; i< 5-calificacion; i++){
                out.print("<span class='fa fa-star'></span>");
            }
        %>
        </div>
        <span>(${usuariosCalificacion})</span>
        <c:if test="${calificacion == 0}">
            <br><span class="text-warning">Sin calificar</span>
        </c:if>

        <div>
            <p>${libro.sinopsis}</p>
        </div>
        <div>
            <span class="text-danger text-decoration-underline">${libro.genero}</span>
            <br>
            <span>Numero de paginas: ${libro.numeroDePaginas}</span>
            <br>
            <span>ISBN: ${libro.ISBN}</span>
            <br>
            <span>Editorial: ${libro.editorial}</span>
            <br>
            <span>Tipo de obra: ${libro.tipoDeObra}</span>
            <br>
            <span>Impreso en el año: ${libro.agnoDeImpresion}</span>
        </div>
    </div>
</main>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
</body>
</html>
