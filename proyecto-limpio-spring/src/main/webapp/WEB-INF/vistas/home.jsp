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



<div id="carouselExampleIndicators" class="carousel slide mb-4" data-bs-ride="true">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://tap-multimedia-1113.nyc3.digitaloceanspaces.com/slider/4295/large/CUSPIDE_BANN_Este_dolor.jpg"
                 class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="https://tap-multimedia-1113.nyc3.digitaloceanspaces.com/slider/4320/large/Banner_C_spide_Decididas_1200x300.jpg"
                 class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="https://tap-multimedia-1113.nyc3.digitaloceanspaces.com/slider/4330/large/Oyhanarte_RosiesBlossoms_BannerCuspide_1200x300.jpg"
                 class="d-block w-100" alt="...">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
            data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>



<article class="container">
    <form class="d-flex ">
        <input class="form-control me-sm-2" type="text" placeholder="Buscá tu libro...">
        <button class="btn btn-primary my-2 my-sm-0" type="submit">Buscar</button>
    </form>
    <div class="grid  mt-2 ">
        <div class="row gap-3 justify-content-center">

            <c:forEach items="${librosALaVenta}" var="libro">

                <div class="card col-3 " style="width: 225px">
                    <div class="card-body text-center">
                        <h4 class="card-title">${libro.titulo}</h4>
                        <img width="100px" src="img/libro1.jpg" alt="">
                        <p class="card-text">Autor: ${libro.autor}</p>
                        <p class="card-text">Género: ${libro.genero}</p>
                        <p class="card-text">Num. de paginas: ${libro.numeroDePaginas}</p>
                        <a href="#" class="card-link ">Comprar</a>
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

<%@include file="common html/footer.html"%>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>