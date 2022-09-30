<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.jsp" %>

<div class="card-body">
    <div class="container p-5">
        <div class="row">
            <div class="btn-group col-12" role="group" aria-label="Basic example">
                <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Libros
                </button>
                <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">
                    Novedades
                </button>
                <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Top 100
                </button>
                <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Comics y
                    Manga
                </button>
                <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Locales
                </button>
                <button type="button" class="btn text-light shadow" style="background-color: #772953">Guia de
                    Compra
                </button>
            </div>
        </div>
        <div class="row">
            <div class="col-12 text-center my-4"><h4>${libro.titulo}</h4></div>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="card mx-auto" style="width: 18rem;">
                    <img src="https://images.cdn3.buscalibre.com/fit-in/360x360/4d/0b/4d0b1fe621b2ae13392695c8e8e5b80a.jpg"
                         class="card-img-top" alt="...">
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card text-end" style="width: 22rem; height: auto; background-color: #e7e7e7">
                    <div class="card-body">
                        <h4 class="card-title">$${libro.precioDeVenta}</h4>
                        <div class="d-grid gap-2">
                            <button class="btn btn-primary rounded-pill" type="button">Comprar Ahora</button>
                            <button class="btn btn-success rounded-pill" type="button">Agregar al carrito</button>
                        </div>
                    </div>
                </div>
                <br><br>
                <div class="row" style="width: 22rem;">
                    <label for="customRange2" class="form-label">¿Ya leíste este libro? ¡Rankealo!</label>
                    <input type="range" class="form-range" min="1" max="5" id="customRange2">
                    <div class="clearfix">
                        <strong class="float-start">1</strong>
                        <strong class="float-end">5</strong>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="common html/footer.html" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>
