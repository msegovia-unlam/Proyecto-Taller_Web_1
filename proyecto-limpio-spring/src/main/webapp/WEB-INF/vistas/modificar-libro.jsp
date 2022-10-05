<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <%@include file="common html/bootstrap.html" %>
    <title>Modificar libro</title>
</head>
<body>

<%@include file="common html/nav-bar.jsp" %>

<article class="container w-50 p-5">
    <form:form action="${pageContext.request.contextPath}/actualizarLibro"  method="post" modelAttribute="libro" enctype="multipart/form-data">
        <fieldset>
            <legend>"${libro.titulo}"</legend>

            <form:input path="id" type="hidden" id="id" value="${libro.id}" />

            <div class="form-group">
                <label class="col-form-label mt-4" for="titulo">Título del libro</label>
                <form:input type="text" class="form-control"  id="titulo" path="titulo" />
            </div>
            <div class="form-group">
                <label for="autor" class="form-label mt-4">Nombre del autor</label>
                <form:input type="text" class="form-control"  id="autor"  path="autor"/>
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="cantidad-paginas">Cantidad de paginas</label>
                <form:input type="number" class="form-control" id="cantidad-paginas"  path="numeroDePaginas"/>
            </div>
            <div class="form-group">
                <label for="editorial" class="form-label mt-4">Nombre de la editorial</label>
                <form:input type="text" class="form-control" id="editorial" path="editorial" />
            </div>
            <div class="form-group">
                <label for="resenia" class="form-label mt-4">Reseña</label>
                <form:textarea class="form-control" id="resenia" path="sinopsis" />
            </div>
            <div>

            </div>
            <div class="form-group">
                <label for="Aventura" class="form-label mt-4">Género</label>
                <div class="form-check" id="Aventura">
                    <form:radiobutton class="form-check-input" value="aventura" id="aventura" path="genero" />
                    <label class="form-check-label" for="Aventura">
                        Aventura
                    </label>
                </div>
                <div class="form-check" id="CFiccion">
                    <form:radiobutton class="form-check-input" value="ciencia ficcion" id="cienciaFiccion" path="genero" />
                    <label class="form-check-label" for="CFiccion">
                        Ciencia Ficcion
                    </label>
                </div>
                <div class="form-check" id="Policial">
                    <form:radiobutton class="form-check-input" value="policial" id="policial" path="genero" />
                    <label class="form-check-label" for="Policial">
                        Policial
                    </label>
                </div>
                <div class="form-check" id="Terror">
                    <form:radiobutton class="form-check-input" value="terror" id="terror" path="genero" />
                    <label class="form-check-label" for="Terror">
                        Terror
                    </label>
                </div>
            </div>

            <div class="form-group">
                <label class="col-form-label mt-4" for="precioDeVenta">Precio</label>
                <form:input type="number" class="form-control"  id="precioDeVenta" path="precioDeVenta" />
            </div>

            <button type="submit" class="btn btn-primary mt-4">Actualizar</button>
        </fieldset>
    </form:form>
</article>

<%@include file="common html/footer.html" %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
</body>

</html>
