<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../common html/bootstrap.html" %>
    <title>Title</title>
</head>
<style>
    textarea{
        resize: none;
        outline: none;
        border: none;
    }
    article {
        border: 1px solid ;
        border-top: none;
    }


    .contenedor{
        max-width: 675px;
        margin: 0 auto;
    }
</style>
<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="contenedor mt-5">
    <div class="row">
        <div class="col-4 bg-dark text-center py-2">
                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="54"
                     height="54" viewBox="0 0 24 24" stroke-width="1.5" stroke="#fff" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <circle cx="12" cy="12" r="9" />
                    <circle cx="12" cy="10" r="3" />
                    <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                </svg>
            <br>
            <a href="${pageContext.request.contextPath}/red-social/modificar-perfil/${usuario.id}">editar perfil</a>
        </div>
        <div class="col-8">
            <table class="table  table-striped table-hover table-bordered table-sm" >
                <thead  class="thead-dark"  style=" text-align: center;">
                </thead>
                <tbody>
                <tr>
                    <td class="font-weight-bold text-left">Usuario: </td>
                    <td class="font-weight-bold text-left">${usuario.nombre}</td>
                </tr>
                <tr>
                    <td class="font-weight-bold text-left">Estado: </td>
                    <td class="font-weight-bold text-left">
                        <c:choose>
                            <c:when test="${usuario.activo.equals(true)}">
                                Activo
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="font-weight-bold text-left">Rol: </td>
                    <td class="font-weight-bold text-left">${usuario.rol}</td>
                </tr>
                <tr>
                    <td class="font-weight-bold text-left">E-Mail: </td>
                    <td class="font-weight-bold text-left">${usuario.email}</td>
                </tr>
                </tbody>

            </table>
        </div>
        <form action="${pageContext.request.contextPath}/cerrar-sesion">
            <button class="btn btn-secondary my-2 my-sm-0 mx-3" name="cerrarSesion" type="submit">Cerrar Sesion
            </button>
        </form>
    </div>
</main>

</body>
</html>
