<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>${libro.titulo}</title>
    <%@include file="common html/bootstrap.html" %>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<%@include file="common html/nav-bar.jsp" %>

<c:if test="${msj != null}">
    <p class="alert alert-success text-center my-3">${msj}</p>
</c:if>

<main class="container">

    <article class="card-body mb-3">
        <c:if test="${not empty libroAgregado}">
            <p class="alert alert-success text-center my-3">${libroAgregado} <a href="${pageContext.request.contextPath}/carrito" class="alert-link">Ver carrito</a> </p>
        </c:if>
        <c:if test="${not empty libroNoAgregado}">
            <p class="alert alert-info text-center my-3">${libroNoAgregado} <a href="${pageContext.request.contextPath}/login" class="alert-link">iniciar sesion</a> </p>
        </c:if>
        <h2 class="col-sm-12 my-4  text-uppercase">${libro.titulo}</h2>
        <div class="d-flex gap-5">
            <img class="w-25" src="${pageContext.request.contextPath}/img/${libro.imagen.id}.jpg" alt="${libro.titulo}-imagen">
            <div>
                <div class="card">
                    <div class="card-body">
                        <p class="mb-0">Autor: ${libro.autor}</p>
                        <p class="my-1">Editorial: ${libro.editorial}</p>
                        <h4 >$${libro.precioDeVenta},00</h4>
                        <div class="grid">
                            <form action="${pageContext.request.contextPath}/comprar/${libro.id}">
                                <button class="btn btn-primary rounded-pill mb-2" type="submit">Comprar Ahora</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/agregar-a-carrito/${libro.id}" method="POST">
                                <button href="" class="btn btn-success rounded-pill" type="submit">Agregar al carrito</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/calificar/${libro.id}" method="POST">
                                <label for="calificacion" class="form-label">¿Ya leíste este libro? ¡Rankealo!</label>
                                <input type="range" class="form-range" min="1" max="5" id="calificacion" name="calificacion">
                                <div class="clearfix">
                                    <strong class="float-start">1</strong>
                                    <strong class="float-end">5</strong>
                                </div>
                                <div class="d-grid gap-2 col-6 mx-auto">
                                    <button class="btn btn-warning btn-sm rounded-pill" type="submit">Califica</button>
                                </div>
                            </form>

                            <%
                                int calificacion = (int)pageContext.findAttribute("calificacion");

                                for(int i=0; i< calificacion; i++){
                                    out.print("<span class='fa fa-star text-warning'></span>");
                                }

                                for(int i=0; i< 5-calificacion; i++){
                                    out.print("<span class='fa fa-star'></span>");
                                }
                            %>
                            <span>(${usuariosCalificacion})</span>
                            <c:if test="${calificacion == 0}">
                                <br><span class="text-warning">Sin calificar</span>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </article>

        <article class="mb-3 col-12">
            <h2 class="mt-4">Sinopsis</h2>
            <p class="w-75">${libro.sinopsis}</p>
            <h3>Detalles del libro</h3>
            <p>Numero de páginas: ${libro.numeroDePaginas}</p>
            <p>Edición: ${libro.editorial}</p>
            <p>ISBN: ${libro.ISBN}</p>
        </article>

    </div>

    <article class="mt-4">
        <h2>Libros del mismo autor</h2>
        <div class="d-flex flex-row flex-nowrap overflow-auto">
            <c:forEach items="${relacionados}" var="relacionado">
                <c:if test="${relacionado != libro}">
                    <div class="card-body m-2 col-3">
                        <a class="text-decoration-none" href="${relacionado.id}" title="${relacionado.titulo}">
                            <h4 class="card-title text-uppercase text-center text-truncate">${relacionado.titulo}</h4>
                            <img class="text-center" style="width: 15em;height: 25em;"
                                 src="${pageContext.request.contextPath}/img/${relacionado.imagen.id}.jpg"
                                 alt="${relacionado.titulo}-imagen">
                        </a>
                        <p class="card-text my-1">${relacionado.autor}</p>
                        <h4 class="card-text">$${relacionado.precioDeVenta},00</h4>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </article>

    <article class="mt-4">
        <h2>Libros que podrian interesarte</h2>
        <div class="d-flex flex-row flex-nowrap overflow-auto">
            <c:forEach items="${relacionadosPorGenero}" var="relacionado">
                <c:if test="${relacionado != libro}">
                    <div class="card-body m-2 col-3">
                        <a class="text-decoration-none" href="${relacionado.id}">
                            <h4 class="card-title text-uppercase text-center text-truncate">${relacionado.titulo}</h4>
                            <img class="text-center" style="width: 15em;height: 25em;"
                                 src="${pageContext.request.contextPath}/img/${relacionado.imagen.id}.jpg"
                                 alt="${relacionado.titulo}-imagen">
                        </a>
                        <p class="card-text my-1">${relacionado.autor}</p>
                        <h4 class="card-text">$${relacionado.precioDeVenta},00</h4>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </article>

</main>

<%@include file="common html/footer.html" %>

<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function mostrarCalificacion(){
        x = document.getElementById("calificacion").value;
        alert(x)
    }
</script>
</body>
</html>
