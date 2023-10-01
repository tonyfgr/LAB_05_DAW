<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Resultado</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css"/>
</head>
<body>
<div class="container mt-5">
    <div style="width: 450px; text-align: center; margin: 50px auto;">
        <h1>Resultado de la Operación</h1>
        <p>El resultado de la operación es: <c:out value="${resultado}"/></p>
        <a class="btn btn-primary btn-lg" href="FormOperaciones.jsp" role="button">Volver</a>
    </div>
</div>
</body>
</html>

