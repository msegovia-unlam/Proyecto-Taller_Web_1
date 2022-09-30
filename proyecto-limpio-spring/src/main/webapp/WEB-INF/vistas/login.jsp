<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.jsp"%>

<c:if test="${not empty error}">
    <p class="alert alert-danger text-center my-3">${error}</p>
</c:if>
<article class="container w-50 pt-3">
    <form:form modelAttribute="datosLogin" action = "validar-login" method="POST">
        <fieldset class="pb-2">
            <legend>Login</legend>
            <div class="form-group">
                <div class="form-floating mb-3">
                    <form:input path="email" type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
                           name="email"/>
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <form:input path="password" type="password" class="form-control" id="floatingPassword" placeholder="Password"
                           name="password"/>
                    <label for="floatingPassword">Password</label>
                </div>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary">Submit</button>
        <span>${error}</span>
    </form:form>
</article>

<%@include file="common html/footer.html" %>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>