/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.registrousuariosweb.controlador;

import com.ejemplo.registrousuariosweb.dao.UsuarioDAO;
import com.ejemplo.registrousuariosweb.modelo.Usuario;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
   UsuarioDAO dao = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion == null) accion = "listar";

        switch (accion) {
            case "crear":
                request.getRequestDispatcher("usuarios/crear.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Usuario usuarioEditar = dao.obtener(idEditar);
                request.setAttribute("usuario", usuarioEditar);
                request.getRequestDispatcher("usuarios/editar.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                dao.eliminar(idEliminar);
                response.sendRedirect("UsuarioServlet?accion=listar");
                break;

            case "listar":
            default:
                List<Usuario> lista = dao.listar();
                request.setAttribute("usuarios", lista);
                request.getRequestDispatcher("usuarios/listar.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if ("insertar".equals(accion)) {
            Usuario u = new Usuario();
            u.setNombre(request.getParameter("nombre"));
            u.setCorreo(request.getParameter("correo"));
            dao.insertar(u);
            response.sendRedirect("UsuarioServlet?accion=listar");

        } else if ("actualizar".equals(accion)) {
            Usuario u = new Usuario();
            u.setId(Integer.parseInt(request.getParameter("id")));
            u.setNombre(request.getParameter("nombre"));
            u.setCorreo(request.getParameter("correo"));
            dao.actualizar(u);
            response.sendRedirect("UsuarioServlet?accion=listar");
        }
    }
}
