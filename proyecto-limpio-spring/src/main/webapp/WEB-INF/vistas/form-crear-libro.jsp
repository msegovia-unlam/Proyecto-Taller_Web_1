<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <%@include file="common html/bootstrap.html" %>
    <title>Crear libro</title>
</head>
<body>

<%@include file="common html/nav-bar.html" %>

<article class="container w-50 p-5">
    <form:form modelAttribute="libro" action="validar-libro-nuevo">
        <fieldset>
            <legend>Crear un libro</legend>

            <div class="form-group">
                <form:label class="col-form-label mt-4" path="titulo">Título del libro</form:label>
                <form:input type="text" class="form-control" placeholder="Título del libro" path="titulo" />
            </div>
            <div class="form-group">
                <form:label path="autor" class="form-label mt-4">Nombre del autor</form:label>
                <form:input type="text" class="form-control" path="autor" />
            </div>
            <div class="form-group">
                <form:label class="col-form-label mt-4" path="numeroDePaginas">Cantidad de paginas</form:label>
                <form:input type="number" class="form-control" path="numeroDePaginas" />
            </div>
            <div class="form-group">
                <form:label path="editorial" class="form-label mt-4">Nombre de la editorial</form:label>
                <form:input type="text" class="form-control" path="editorial" />
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
                <form:label path="precioDeVenta" class="col-form-label mt-4">Precio de Venta</form:label>
                <form:input type="number" class="form-control" path="precioDeVenta" />
            </div>
            <div class="form-group">
                <form:label path="aLaVenta" class="col-form-label mt-4">¿Poner a la venta?</form:label>
                <div class="form-check">
                    <form:radiobutton class="form-check-input" path="aLaVenta" value="true" />
                    <form:label class="form-check-label" path="aLaVenta">Si</form:label>
                </div>
                <div class="form-check">
                    <form:radiobutton class="form-check-input" path="aLaVenta" value="false" />
                    <form:label class="form-check-label" path="aLaVenta">No</form:label>
                </div>
            </div>

            <button type="submit" class="btn btn-primary mt-4">Crear</button>
        </fieldset>
    </form:form>
</article>

<%@include file="common html/footer.html" %>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
