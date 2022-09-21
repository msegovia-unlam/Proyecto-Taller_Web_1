<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.html" %>

<article class="container w-50 p-5">
    <form>
        <fieldset>
            <legend>Crear un libro</legend>

            <div class="form-group">
                <label class="col-form-label mt-4" for="titulo">Título del libro</label>
                <input type="text" class="form-control" placeholder="Título del libro" id="titulo">
            </div>
            <div class="form-group">
                <label for="autor-nombre" class="form-label mt-4">Nombre del autor</label>
                <input list="autores-lista" class="form-control" id="autor-nombre">
                <datalist id="autores-lista">
                    <option value="Un Autor">Un Autor</option>
                </datalist>
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="cantidad-paginas">Cantidad de paginas</label>
                <input type="number" class="form-control" id="cantidad-paginas">
            </div>
            <div class="form-group">
                <label for="editorial-nombre" class="form-label mt-4">Nombre de la editorial</label>
                <input list="editoriales-lista" class="form-control" id="editorial-nombre">
                <datalist id="editoriales-lista">
                    <option value="Editorial">Editorial</option>
                </datalist>
            </div>
            <div class="form-group">
                <label for="tipo-obra" class="form-label mt-4">Tipo de Obra</label>
                <select class="form-select" id="tipo-obra">
                    <option>Novela</option>
                    <option>Manga</option>
                    <option>Comic Americano</option>
                    <option>Cuento</option>
                    <option>Antologia</option>
                </select>
            </div>
            <div class="form-group">
                <label for="generos" class="form-label mt-4">Géneros</label>
                <select multiple="" class="form-select" id="generos">
                    <option>Terror</option>
                    <option>Fantasia</option>
                    <option>Historia</option>
                    <option>Ciencia Ficcion</option>
                </select>
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="año-impresion">Año de impresión</label>
                <input type="number" class="form-control" id="año-impresion">
            </div>
            <div class="form-group">
                <label class="col-form-label mt-4" for="isbn">ISBN</label>
                <input type="text" class="form-control" placeholder="ISBN" id="isbn">
            </div>

            <button type="submit" class="btn btn-primary mt-4">Crear</button>
        </fieldset>
    </form>
</article>

<%@include file="common html/footer.html" %>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>
