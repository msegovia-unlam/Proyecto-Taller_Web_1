<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.jsp"%>

<main class="container">

    <c:if test="${not empty mensajeExitoso}">
        <p class="alert alert-success text-center my-3">${mensajeExitoso}</p>
    </c:if>
    <c:if test="${not empty mensajeError}">
        <p class="alert alert-danger text-center my-3">${mensajeError}</p>
    </c:if>
    <c:if test="${not empty libroCreado}">
        <p class="alert alert-success text-center my-3">${libroCreado}</p>
    </c:if>
    <c:if test="${not empty libroNoCreado}">
        <p class="alert alert-danger text-center my-3">${libroNoCreado}</p>
    </c:if>
    <form action="${pageContext.request.contextPath}/admin/buscar" class="d-flex my-3">
        <input class="form-control me-sm-2" type="text" name="buscar" placeholder="Buscar libro...">
        <button class="btn btn-primary my-2" type="submit">Buscar</button>
    </form>
    <div class="flex text-end">
        <a class="btn btn-success mb-2" href="${pageContext.request.contextPath}/admin/crear-libro">
            Agregar Libro
        </a>
    </div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th scope="col">Título</th>
            <th scope="col">Autor</th>
            <th scope="col">Editorial</th>
            <th scope="col">Precio</th>
            <th scope="col">A la venta</th>
            <th scope="col">Novedad</th>
            <th scope="col">Cantidad en stock</th>
            <th scope="col">Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="libro" items="${datosLibro}">
            <tr>
                    <td>${libro.titulo}</td>
                    <td>${libro.autor}</td>
                    <td>${libro.editorial}</td>
                    <td>$${libro.precioDeVenta}.00</td>
                    <td>
                        <c:if test="${libro.ALaVenta}">
                            <div class="d-flex gap-2">
                                <div >
                                    <span class=" py-3 h-75 badge bg-success">En venta</span>
                                </div>
                                <form:form action="${pageContext.request.contextPath}/admin/cambiar-estado-venta/${libro.id}">
                                    <button type="submit" class="btn btn-warning" >
                                        Cambiar
                                    </button>
                                </form:form>
                            </div>
                        </c:if>
                        <c:if test="${!libro.ALaVenta}">
                            <div class="d-flex gap-2">
                                <div >
                                    <span class="py-3 h-75 badge bg-danger">No está en venta</span>
                                </div>
                                <form:form action="${pageContext.request.contextPath}/admin/cambiar-estado-venta/${libro.id}">
                                    <button type="submit" class="btn btn-warning" >
                                        Cambiar
                                    </button>
                                </form:form>
                            </div>
                        </c:if>
                    </td>

<%--                    NOVEDAD     --%>
                    <td>
                        <c:if test="${libro.novedad}">
                            <div class="d-flex gap-2">
                                <div >
                                    <span class=" py-3 h-75 badge bg-success">Es novedad</span>
                                </div>
                                <form:form action="${pageContext.request.contextPath}/admin/cambiar-estado-novedad/${libro.id}">
                                    <button type="submit" class="btn btn-warning" >
                                        Cambiar
                                    </button>
                                </form:form>
                            </div>
                        </c:if>
                        <c:if test="${!libro.novedad}">
                            <div class="d-flex gap-2">
                                <div >
                                    <span class="py-3 h-75 badge bg-danger">No es es novedad</span>
                                </div>
                                <form:form action="${pageContext.request.contextPath}/admin/cambiar-estado-novedad/${libro.id}">
                                    <button type="submit" class="btn btn-warning" >
                                        Cambiar
                                    </button>
                                </form:form>
                            </div>
                        </c:if>
                    </td>
                    <td>${libro.cantidadEnStock}</td>
                    <td class="d-flex gap-2">
                        <form:form  action="${pageContext.request.contextPath}/admin/modificar-libro/${libro.id}">
                            <button type="submit" class="btn btn-info" >
                            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-edit" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#ffffff" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                                <path d="M9 7h-3a2 2 0 0 0 -2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2 -2v-3" />
                                <path d="M9 15h3l8.5 -8.5a1.5 1.5 0 0 0 -3 -3l-8.5 8.5v3" />
                                <line x1="16" y1="5" x2="19" y2="8" />
                            </svg>
                            </button>
                        </form:form>
                        <form:form  action="${pageContext.request.contextPath}/admin/borrar-libro/${libro.id}">
                            <button type="submit" class="btn btn-danger">
                                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-trash" width="24" height="24" viewBox="0 0 24 24" stroke-width="1.5" stroke="#ffffff" fill="none" stroke-linecap="round" stroke-linejoin="round">
                                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                                    <line x1="4" y1="7" x2="20" y2="7" />
                                    <line x1="10" y1="11" x2="10" y2="17" />
                                    <line x1="14" y1="11" x2="14" y2="17" />
                                    <path d="M5 7l1 12a2 2 0 0 0 2 2h8a2 2 0 0 0 2 -2l1 -12" />
                                    <path d="M9 7v-3a1 1 0 0 1 1 -1h4a1 1 0 0 1 1 1v3" />
                                </svg>
                            </button>
                        </form:form>
                    </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</main>



</body>
</html>
