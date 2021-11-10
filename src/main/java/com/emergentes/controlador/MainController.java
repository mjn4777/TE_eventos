package com.emergentes.controlador;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            int id;
            op = (request.getParameter("op") != null) ? 
                    request.getParameter("op") : "list";
            ArrayList<Seminario> lista = new ArrayList<Seminario>();
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (op.equals("list")) {
                String sql = "select * from seminarios";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Seminario prod = new Seminario();
                    prod.setId(rs.getInt("id"));
                    prod.setTitulo(rs.getString("titulo"));
                    prod.setExpositor(rs.getString("expositor"));
                    prod.setFecha(rs.getString("fecha"));
                    prod.setHora(rs.getString("hora"));
                    prod.setCupo(rs.getInt("cupo"));
                    lista.add(prod);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (op.equals("nuevo")) {
                Seminario li = new Seminario();
                System.out.println(li.toString());
                request.setAttribute("op", op);
                request.setAttribute("prod", li);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
            }
            if (op.equals("eliminar")) {
                id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from seminarios where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
            if (op.equals("editar")) {
                request.setAttribute("op", op);
                Seminario prod1 = new Seminario();
                id = Integer.parseInt(request.getParameter("id"));
                try {
                    ps = conn.prepareStatement("select * from seminarios where id=?");
                    ps.setInt(1, id);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        prod1.setId(rs.getInt("id"));
                        prod1.setTitulo(rs.getString("titulo"));
                        prod1.setExpositor(rs.getString("expositor"));
                        prod1.setFecha(rs.getString("fecha"));
                        prod1.setHora(rs.getString("hora"));
                        prod1.setCupo(rs.getInt("cupo"));

                    }
                    request.setAttribute("prod", prod1);
                    request.getRequestDispatcher("editar.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println("ERROR AL CONECTAR" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Valor de ID" + id);
            String titulo = request.getParameter("titulo");
            String expositor = request.getParameter("expositor");
            String fecha = request.getParameter("fecha");
            String hora = request.getParameter("hora");                         
            int cupo = Integer.parseInt(request.getParameter("cupo"));
            Seminario prod = new Seminario();
            
            prod.setId(id);
            prod.setTitulo(titulo);
            prod.setExpositor(expositor);
            prod.setFecha(fecha);
            prod.setHora(hora);
            prod.setCupo(cupo);
                       
            
            ConexionDB canal = new ConexionDB();
            Connection conn = canal.conectar();
            PreparedStatement ps;
            ResultSet rs;
            if (id == 0) {
                String sql = "Insert into seminarios(titulo,expositor,fecha,hora,cupo) values(?,?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, prod.getTitulo());
                ps.setString(2, prod.getExpositor());
                ps.setString(3, prod.getFecha());
                ps.setString(4, prod.getHora());
                ps.setInt(5, prod.getCupo());
                ps.executeUpdate();
            } else {
                String sql1 = "UPDATE seminarios SET titulo=?,expositor=?,fecha=?,hora=?,cupo=? where id=?";
                try {
                    ps = conn.prepareStatement(sql1);
                    ps.setString(1, prod.getTitulo());
                    ps.setString(2, prod.getExpositor());
                    ps.setString(3, prod.getFecha());
                    ps.setString(4, prod.getHora());
                                                       
                    ps.setInt(3, prod.getCupo());
                    ps.setInt(4, prod.getId());
                    ps.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect("MainController");
        } catch (SQLException ex) {
            System.out.println("Error en SQL" + ex.getMessage());
        }
    }
}
