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
    <form:form action="${pageContext.request.contextPath}/admin/actualizarLibro"  method="post" modelAttribute="libro" enctype="multipart/form-data">
        <fieldset>
            <legend>"${libro.titulo}"</legend>

            <form:input path="id" type="hidden" id="id" value="${libro.id}" />
            <form:input path="imagen.id" type="hidden" id="imagen.id" value="${libro.imagen.id}" />

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
                <label for="sinopsis" class="form-label mt-4">Sinopsis</label>
                <form:textarea class="form-control" id="sinopsis" path="sinopsis" />
            </div>
            <div class="form-group">
                <form:label path="tipoDeObra" class="form-label mt-4">Tipo de Obra</form:label>
                <form:select class="form-select" path="tipoDeObra">
                    <option>Novela</option>
                    <option>Manga</option>
                    <option>Comic Americano</option>
                    <option>Cuento</option>
                    <option>Antologia</option>
                </form:select>
            </div>
            <div class="form-group">
                <form:label path="genero" class="form-label mt-4">Géneros</form:label>
                <form:select class="form-select" path="genero">
                    <option>Terror</option>
                    <option>Fantasia</option>
                    <option>Historia</option>
                    <option>Ciencia Ficcion</option>
                </form:select>
            </div>
            <div class="form-group">
                <form:label class="col-form-label mt-4" path="agnoDeImpresion">Año de impresión</form:label>
                <form:input type="number" class="form-control" path="agnoDeImpresion" />
            </div>
            <div class="form-group">
                <form:label class="col-form-label mt-4" path="ISBN">ISBN</form:label>
                <form:input type="text" class="form-control" placeholder="ISBN" path="ISBN" />
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="precioDeVenta">Precio</label>
                <form:input type="number" class="form-control"  id="precioDeVenta" path="precioDeVenta" />
            </div>
            <div class="form-group">
                <form:label path="aLaVenta" class="col-form-label mt-4">¿Poner a la venta?</form:label>
                <div class="form-check">
                    <form:radiobutton class="form-check-input" path="ALaVenta" value="true" />
                    <form:label class="form-check-label" path="aLaVenta">Si</form:label>
                </div>
                <div class="form-check">
                    <form:radiobutton class="form-check-input" path="ALaVenta" value="false" />
                    <form:label class="form-check-label" path="aLaVenta">No</form:label>
                </div>
            </div>
            <button type="submit" class="btn btn-primary mt-4">Actualizar</button>
        </fieldset>
    </form:form>
</article>

<%@include file="common html/footer.html" %>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
