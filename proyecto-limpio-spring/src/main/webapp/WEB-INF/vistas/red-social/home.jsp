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

<main class="contenedor">
    <article class="flex" >
        <div class="d-flex justify-content-center border mb-2">
            <div>
                <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-user-circle" width="54"
                     height="54" viewBox="0 0 24 24" stroke-width="1.5" stroke="#000" fill="none" stroke-linecap="round"
                     stroke-linejoin="round">
                    <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
                    <circle cx="12" cy="12" r="9" />
                    <circle cx="12" cy="10" r="3" />
                    <path d="M6.168 18.849a4 4 0 0 1 3.832 -2.849h4a4 4 0 0 1 3.834 2.855" />
                </svg>
            </div>
            <div class="form-group p-3">
            <textarea rows="5" cols="50" class="bg-opacity-50" maxlength="280"
                      placeholder="En que estas pensando?"></textarea>
            </div>
        </div>
        <form class="d-flex justify-content-end me-5" action="">
            <button type="submit" class="btn btn-dark">Publicar</button>
        </form>
    </article>
    <article class="flex" >
        <div class="d-flex justify-content-center border mb-2">
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
            <div class="form-group p-3 mt-2">
            <p>Esto es un texto de prueba. La idea es escribir una publicación de prueba para ver como se ve</p>
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
    <article class="flex" >
        <div class="d-flex justify-content-center border mb-2">
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
            <div class="form-group p-3 mt-2">
                <p>Estoy más emocionada por terminar el libro que llevo un mes leyendo que terminar mi licenciatura.</p>
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
    <article class="flex" >
        <div class="d-flex justify-content-center border mb-2">
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
            <div class="form-group p-3 mt-2">
                <p>
                    "Most men, it seems to me, do not care for Nature and would sell their share in all her beauty, as long as they may live, for a stated sum - many for a glass of rum. Thank God, men cannot as yet fly, and lay waste the sky as well as the earth !"

                    Henry David Thoreau
                    Journal
                </p>
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


</main>

</body>
</html>
