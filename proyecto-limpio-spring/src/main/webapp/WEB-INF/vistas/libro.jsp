
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <title>Title</title>
</head>
<body>

<div class="card">
    <div class="card-heading">
        <nav class="navbar navbar-dark" style="background-color: #E95420">
            <div class="container-fluid">
                <a class="navbar-brand">LibrosApp</a>
                <form class="d-flex my-auto" role="search">
                    <input class="form-control me-2" type="search" placeholder="Buscar libro" aria-label="Search">
                    <button class="btn btn-secondary" type="submit">Buscar</button>
                </form>
            </div>
        </nav>
    </div>

    <div class="card-body">
        <div class="container">
            <div class="row">
                <div class="btn-group col-12" role="group" aria-label="Basic example">
                    <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Libros</button>
                    <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Novedades</button>
                    <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Top 100</button>
                    <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Comics y Manga</button>
                    <button type="button" class="btn text-light me-1 shadow" style="background-color: #772953">Locales</button>
                    <button type="button" class="btn text-light shadow" style="background-color: #772953">Guia de Compra</button>
                </div>
            </div>
            <div class="row">
                <div class="col-12 text-center my-4"><h4>El señor de los anillos: La comunidad del anillo</h4></div>
            </div>
            <div class="row">
                <div class="col-sm-6">
                    <div class="card mx-auto" style="width: 18rem;">
                        <img src="https://images.cdn3.buscalibre.com/fit-in/360x360/4d/0b/4d0b1fe621b2ae13392695c8e8e5b80a.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <p class="card-text">La Comunidad del Anillo es el primero de los tres volúmenes que forman la novela El Señor de los Anillos, secuela de El hobbit, del escritor británico J. R. R. Tolkien.</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="card text-end" style="width: 22rem; height: auto; background-color: #e7e7e7">
                        <div class="card-body">
                            <h4 class="card-title">$4.200</h4>
                            <p class="card-text">Hasta <strong>6 coutas sin interés de $700,00</strong></p>


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
    <div class="card-footer">
        <nav class="navbar bg-light">
            <div class="container-fluid">
                <a class="navbar-brand">Taller Web 1</a>
                <form class="d-flex" role="search">
                    <a href="#"><i class="bi bi-instagram mx-2" style="font-size: 1.5rem;"></i></a>
                    <a href="#"><i class="bi bi-whatsapp mx-2" style="font-size: 1.5rem;"></i></a>
                    <a href="#"><i class="bi bi-facebook mx-2" style="font-size: 1.5rem;"></i></a>
                </form>
            </div>
        </nav>
    </div>
</div>


</body>
</html>
