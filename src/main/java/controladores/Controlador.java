/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost/test?useSSL=false&serverTimezone=UTC";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "123456";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tabla = request.getParameter("tabla");
        request.setAttribute("tabla", tabla);

        String accion = request.getParameter("accion");

        if ("editar".equals(accion)) {
            int codigoUsuario = 0;
            try {
                codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
            } catch (NumberFormatException e) {
                // Manejar el error de conversión a número aquí si es necesario
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }

            String nombreDelUsuario = "";
            String claveDelUsuario = "";
            String estadoDelUsuario = "";

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("SELECT * FROM t_usuarios WHERE codigo = ?")) {
                stmt.setInt(1, codigoUsuario);
                ResultSet result = stmt.executeQuery();
                if (result.next()) {
                    nombreDelUsuario = result.getString("nombre");
                    claveDelUsuario = result.getString("clave");
                    estadoDelUsuario = result.getString("estado");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejar errores de base de datos aquí, como redirigir a una página de error.
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }

            request.setAttribute("codigoDelUsuario", codigoUsuario);
            request.setAttribute("nombreDelUsuario", nombreDelUsuario);
            request.setAttribute("claveDelUsuario", claveDelUsuario);
            request.setAttribute("estadoDelUsuario", estadoDelUsuario);

            request.getRequestDispatcher("EditarUsuario.jsp").forward(request, response);
        } else if ("eliminar".equals(accion)) {
            int codigoUsuario = 0;
            try {
                codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
            } catch (NumberFormatException e) {
                // Manejar el error de conversión a número aquí si es necesario
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }

            if (eliminarUsuario(codigoUsuario)) {
                response.sendRedirect("jstl_sql_a.jsp");
            } else {
                // Manejar el error si no se pudo eliminar
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
            }
        } else if ("guardarEdicion".equals(accion)) {
            int codigoUsuario = 0;
            try {
                codigoUsuario = Integer.parseInt(request.getParameter("codigo"));
            } catch (NumberFormatException e) {
                // Manejar el error de conversión a número aquí si es necesario
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                return;
            }

            String nuevoNombre = request.getParameter("nombre");
            String nuevaClave = request.getParameter("clave");
            String nuevoEstado = request.getParameter("estado");

            if (actualizarUsuario(codigoUsuario, nuevoNombre, nuevaClave, nuevoEstado)) {
                response.sendRedirect("jstl_sql_a.jsp"); // Redirigir a la página después de guardar
            } else {
                // Manejar el error si no se pudo guardar
                response.sendRedirect("error.jsp"); // Redirigir a una página de error
            }
        } 
        
        else if ("calcular".equals(accion)) {
                    // Obtener los valores del formulario
                    double numero1 = Double.parseDouble(request.getParameter("numero1"));
                    double numero2 = Double.parseDouble(request.getParameter("numero2"));
                    String operacion = request.getParameter("operacion");

                    // Realizar la operación seleccionada
                    double resultado = 0.0;
                    switch (operacion) {
                        case "suma":
                            resultado = numero1 + numero2;
                            break;
                        case "resta":
                            resultado = numero1 - numero2;
                            break;
                        case "multiplicacion":
                            resultado = numero1 * numero2;
                            break;
                        case "division":
                            if (numero2 != 0) {
                                resultado = numero1 / numero2;
                            } else {
                                // Manejar la división por cero aquí, si es necesario
                                response.sendRedirect("error.jsp"); // Redirigir a una página de error
                                return;
                            }
                            break;
                        default:
                            // Manejar la operación no válida aquí, si es necesario
                            response.sendRedirect("error.jsp"); // Redirigir a una página de error
                            return;
                    }

                    // Establecer el resultado como atributo de solicitud
                    request.setAttribute("resultado", resultado);

                    // Redirigir a la página de resultados
                    request.getRequestDispatcher("Resultado.jsp").forward(request, response);
                }
        
        else {
            
            request.getRequestDispatcher("jstl_recibir.jsp").forward(request, response);
        }
    }

    private boolean eliminarUsuario(int codigoUsuario) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM t_usuarios WHERE codigo = ?")) {
            stmt.setInt(1, codigoUsuario);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la eliminación fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar errores de eliminación aquí, como redirigir a una página de error.
            return false; // Retorna false si hubo un error
        }
    }

    private boolean actualizarUsuario(int codigoUsuario, String nuevoNombre, String nuevaClave, String nuevoEstado) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("UPDATE t_usuarios SET nombre=?, clave=?, estado=? WHERE codigo=?")) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaClave);
            stmt.setString(3, nuevoEstado);
            stmt.setInt(4, codigoUsuario);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0; // Retorna true si la actualización fue exitosa
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de errores en la base de datos
            return false; // Retorna false si hubo un error
        }
    }

    // Resto del código (doGet, doPost, getServletInfo) sin cambios...

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            processRequest(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
