<%@ page import="ar.edu.unlam.tallerweb1.domain.Votacion.VotacionesTotales" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    #encuesta{
        display: none;
    }
</style>
<script>

    function abrirEncuesta(){
        document.getElementById('encuesta').style.display = "block";
    }
    function cerrarEncuesta(){
        document.getElementById('encuesta').style.display = "none";
    }
</script>

<body>

<%@include file="../common html/headerRedSocial.jsp" %>

<main class="contenedor">

    <article >
        <form:form modelAttribute="publicacion" class="flex text-end p-2"
                    action="${pageContext.request.contextPath}/red-social/agregar-publicacion">
            <div class="d-flex mx-auto justify-content-center border-bottom mb-2">
                <div>
                    <img class="rounded-circle" style="width:54px"
                         src="${pageContext.request.contextPath}/img/${usuario.imagen.id}.jpg" alt="foto-perfil">
                </div>
                <div class="form-group p-3">
                <form:textarea path="publicacion" rows="5" cols="50" class="bg-opacity-50"
                               maxlength="280"
                          placeholder="En que estas pensando?"/>
                    <div id="encuesta">
                        <div class="form-group my-2">
                            <fieldset>
                                <form:input path="encuesta.opcion1" class="form-control" maxlength="25" type="text"
                                            placeholder="Opción 1"/>
                            </fieldset>
                        </div>
                        <div class="form-group">
                            <fieldset>
                                <form:input path="encuesta.opcion2" class="form-control" maxlength="25" type="text"
                                            placeholder="Opción 2"/>
                            </fieldset>
                        </div>
                        <div class="form-group my-2">
                            <fieldset>
                                <form:input path="encuesta.opcion3" class="form-control" maxlength="25" type="text"
                                            placeholder="Opción 3"/>
                            </fieldset>
                        </div>
                        <div class="form-group">
                            <fieldset>
                                <form:input path="encuesta.opcion4" class="form-control" maxlength="25" type="text"
                                            placeholder="Opción 4"/>
                            </fieldset>
                        </div>
                        <input class="btn btn-dark my-2" onclick="cerrarEncuesta()" value="Cerrar encuesta">
                    </div>
                </div>
            </div>
            <input  class="btn btn-dark" onclick="abrirEncuesta()" value="Crear encuesta">
            <input id="publicar"  type="submit" class="btn btn-dark" value="Publicar">
        </form:form>
    </article>

    <c:forEach items="${publicaciones}" var="publicacion">
        <article class="flex" >
            <div class="d-flex border mb-2">
                <div class="mt-2">
                    <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="54"
                         height="54" viewBox="0 0 24 24" stroke-width="1.5" stroke="#000" fill="none" stroke-linecap="round"
                         stroke-linejoin="round">
                        <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                        <circle cx="12" cy="12" r="9" />
                        <circle cx="12" cy="10" r="3" />
                        <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                    </svg>
                </div>
                <div class="form-group p-3 mt-2 col-9 text-center">
                <p>${publicacion.publicacion}</p>
                    <c:if test="${not empty publicacion.encuesta.opcion1}">
                    <div>
                        <form action="${pageContext.request.contextPath}/red-social/votar" method="post">

                            <input type="hidden" name="encuestaId" value="${publicacion.encuesta.id}">


                                 <input name="encuesta" id="opcion1"  type="radio"
                                        value="${publicacion.encuesta.opcion1}"/>
                                 <label class="mx-2" for="opcion1">${publicacion.encuesta.opcion1}</label>
                            <br>
                                 <input name="encuesta" id="opcion2"  type="radio"
                                        value="${publicacion.encuesta.opcion2}"/>
                                 <label class="mx-2" for="opcion2">${publicacion.encuesta.opcion2}</label>
                                 <br>
                             <c:if test="${not empty publicacion.encuesta.opcion3}">
                                 <input name="encuesta" id="opcion3" type="radio"
                                        value="${publicacion.encuesta.opcion3}"/>
                                 <label class="mx-2" for="opcion3">${publicacion.encuesta.opcion3}</label>
                                 <br>
                             </c:if>
                             <c:if test="${not empty publicacion.encuesta.opcion4}">
                                 <input name="encuesta" id="opcion4" type="radio" value="${publicacion.encuesta.opcion4}"/>
                                 <label class="mx-2" for="opcion4">${publicacion.encuesta.opcion4}</label>
                             </c:if>
                            <input id="votar"  type="submit" class="btn btn-dark btn-sm rounded-pill" value="Votar">
                        </form>
                    </div>

                        <div class="mt-2 justify-content-center">

                            <c:forEach var="entry" items="${porcentaje}">
                                <c:out value="${entry.key.libro}"/>
                                <div class="progress">
                                <div class="progress-bar progress-bar-striped" role="progressbar" style="width: ${entry.value}%;" aria-valuenow="${entry.value}" aria-valuemin="0" aria-valuemax="100"></div>
                                </div>
                            </c:forEach>
                            <%
//                                Integer votosTotales = 0;
//                                List<VotacionesTotales> votos = (List<VotacionesTotales>)pageContext.findAttribute("votaciones");

//                                for (Map.Entry<VotacionesTotales, Integer> ) {
//                                       votosTotales += voto.getCantidadVotos();
//                                }


//                                for (VotacionesTotales voto : votos ) {
//                                    Integer porcentaje = 0;
//
//                                    porcentaje = voto.getCantidadVotos()*100/votosTotales;
//
//                                    out.print("<div class=\"progress\">");
//                                    out.print("<div class=\"progress-bar progress-bar-striped\" role=\"progressbar\" style=\"width: " + porcentaje + ";\" aria-valuenow=\" " + porcentaje + "\" aria-valuemin=\"0\" aria-valuemax=\"100\"></div>");
//                                    out.print("</div>");
//                                }

                            %>

<%--                           <c:forEach var="votacion" items="${votaciones}">--%>
<%--                               <p>${votacion.libro}: (${votacion.cantidadVotos})</p>--%>
<%--                           </c:forEach>--%>
                        </div>
                    </c:if>
                </div>

            </div>
            <div class="d-flex justify-content-around my-1">
                <form action="">
                    <button class="btn" type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-message-dots" width="36"
                             height="36" viewBox="0 0 24 24" stroke-width="1.5" stroke="#772953" fill="none"
                             stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M4 21v-13a3 3 0 0 1 3 -3h10a3 3 0 0 1 3 3v6a3 3 0 0 1 -3 3h-9l-4 4" />
                            <line x1="12" y1="11" x2="12" y2="11.01" />
                            <line x1="8" y1="11" x2="8" y2="11.01" />
                            <line x1="16" y1="11" x2="16" y2="11.01" />
                        </svg>
                    </button>
                </form>
                <form action="">
                    <button class="btn" type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-git-compare" width="36"
                             height="36" viewBox="0 0 24 24" stroke-width="1.5" stroke="#772953" fill="none"
                             stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <circle cx="6" cy="6" r="2" />
                            <circle cx="18" cy="18" r="2" />
                            <path d="M11 6h5a2 2 0 0 1 2 2v8" />
                            <polyline points="14 9 11 6 14 3" />
                            <path d="M13 18h-5a2 2 0 0 1 -2 -2v-8" />
                            <polyline points="10 15 13 18 10 21" />
                        </svg>
                    </button>
                </form>
                <form action="">
                    <button class="btn" type="submit">
                        <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-heart" width="36" height="36"
                             viewBox="0 0 24 24" stroke-width="1.5" stroke="#772953" fill="none" stroke-linecap="round" stroke-linejoin="round">
                            <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                            <path d="M19.5 13.572l-7.5 7.428l-7.5 -7.428m0 0a5 5 0 1 1 7.5 -6.566a5 5 0 1 1 7.5 6.572" />
                        </svg>
                    </button>
                </form>

            </div>
        </article>
    </c:forEach>

</main>

</body>
</html>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">

    var markers = [
        <c:forEach var="votacion" items="${votaciones}">
        ['<c:out value="${votacion.libro}" />',
            <c:out value="${votacion.cantidadVotos}" />, //double without apostrophe
        </c:forEach>   ]];





    // Load google charts
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    // Draw the chart and set the chart values
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Libros', 'Cantidad de votos'],

            for( i = 0; i < markers.length; i++ ) {

                [markers[i], markers[i][i]],

            } //for end

            ['Work', 8],
            ['Friends', 8],
            ['Eat', 8],
            ['Sleep', 8]

        ]);

        // Optional; add a title and set the width and height of the chart
        var options = {'width':250, 'height':200};

        // Display the chart inside the <div> element with id="piechart"
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
    }
</script>