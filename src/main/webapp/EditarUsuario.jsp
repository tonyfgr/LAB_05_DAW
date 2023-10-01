<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    <title>Editar Usuario</title>
</head>
<body>
    <div class="container mt-5">
        <h1 class="display-4 text-center">Editar Usuario</h1>
        <form action="Controlador" method="post">
            <input type="hidden" name="accion" value="guardarEdicion">
            <input type="hidden" name="codigo" value="${codigoDelUsuario}">
            <div class="mb-3">
                <label for="nombre" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="nombre" name="nombre" value="<c:out value='${nombreDelUsuario}' />">
            </div>
            <div class="mb-3">
                <label for="clave" class="form-label">Clave:</label>
                <input type="text" class="form-control" id="clave" name="clave" value="<c:out value='${claveDelUsuario}' />">
            </div>
            <div class="mb-3">
                <label for="estado" class="form-label">Estado:</label>
                <select class="form-select" id="estado" name="estado">
                    <option value="A" <c:if test="${estadoDelUsuario eq 'A'}">selected</c:if>>A</option>
                    <option value="X" <c:if test="${estadoDelUsuario eq 'X'}">selected</c:if>>X</option>
                </select>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-primary">Guardar</button>
                <a href="jstl_sql_a.jsp" class="btn btn-secondary">Cancelar</a>
            </div>
        </form>
    </div>
</body>
</html>


