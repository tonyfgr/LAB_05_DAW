<%-- 
    Document   : jstl_core_b
    Created on : 24/09/2023, 07:44:25 PM
    Author     : Tony
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Tabla de Multiplicar</h1>
            <c:set var="tabla" value="5" />
            Tabla del <c:out value="${tabla}"/>
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Operación</th>
                        <th scope="col">Resultado</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="contador" begin="1" end="10">
                        <tr scope='row'><th> <c:out value="${contador}"/> </th>
                            <td> <c:out value="${tabla} * ${contador}"/> </td>
                            <c:set var="producto" value="${contador*tabla}"/>
                            <td> <c:out value="${producto}"/> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
