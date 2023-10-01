<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="webjars/bootstrap/5.1.0/css/bootstrap.min.css" type="text/css" />
    <title>Calculadora</title>
</head>
<body>
    <div class="container mt-5">
        <form name="form" action="Controlador" method="post">
            <div style="width: 250px; text-align: center; margin: 50px auto;">
                <h1>Calculadora de Operaciones</h1>
                <div class="form-group">
                    <label for="numero1">Número 1:</label>
                    <input type="number" name="numero1" id="numero1" required>
                </div>
                <div class="form-group">
                    <label for="numero2">Número 2:</label>
                    <input type="number" name="numero2" id="numero2" required>
                </div>
                <div class="form-group">
                    <label for="operacion">Operación:</label>
                    <select name="operacion" class="form-control" id="operacion">
                        <option value="suma">Suma</option>
                        <option value="resta">Resta</option>
                        <option value="multiplicacion">Multiplicación</option>
                        <option value="division">División</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary" name="accion" value="calcular">Calcular</button>
            </div>
        </form>
    </div>
</body>
</html>


