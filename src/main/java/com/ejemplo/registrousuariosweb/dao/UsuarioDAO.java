/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.registrousuariosweb.dao;

import com.ejemplo.registrousuariosweb.modelo.Usuario;
import com.ejemplo.registrousuariosweb.util.ConexionBD;

import java.sql.*;
import java.util.*;


public class UsuarioDAO {
    Connection conn = ConexionBD.getConexion();

    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Usuario(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insertar(Usuario u) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO usuarios (nombre, correo) VALUES (?, ?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario obtener(int id) {
        Usuario u = new Usuario();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setCorreo(rs.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public void actualizar(Usuario u) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE usuarios SET nombre=?, correo=? WHERE id=?");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getCorreo());
            ps.setInt(3, u.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM usuarios WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
