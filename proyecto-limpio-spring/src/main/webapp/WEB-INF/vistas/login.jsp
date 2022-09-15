<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <%@include file="common html/bootstrap.html" %>
</head>
<body>

<%@include file="common html/nav-bar.html"%>

<article class="container w-25 p-5">
    <form action="">
        <fieldset class="pb-2">
            <legend>Login</legend>
            <div class="form-group">
                <div class="form-floating mb-3">
                    <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com"
                           name="email">
                    <label for="floatingInput">Email address</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                           name="password">
                    <label for="floatingPassword">Password</label>
                </div>
            </div>
        </fieldset>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</article>

<%@include file="common html/footer.html" %>

<script src="js/bootstrap.min.js" type="text/javascript"></script>
</body>

</html>