<%-- 
    Document   : crear
    Created on : 6/08/2025, 10:02:16 a. m.
    Author     : Familia Carabali M
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Crear Usuario</title>
</head>
<body>
    <h1>Crear nuevo usuario</h1>

    <form action="UsuarioServlet" method="post">
        <input type="hidden" name="accion" value="insertar" />

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" id="nombre" required /><br><br>

        <label for="correo">Correo:</label>
        <input type="email" name="correo" id="correo" required /><br><br>

        <input type="submit" value="Guardar" />
        <a href="UsuarioServlet?accion=listar">Cancelar</a>
    </form>
</body>
</html>
