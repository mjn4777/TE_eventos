<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%List<Seminario> lista = (List<Seminario>) request.getAttribute("lista");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border ="1">
            <th>SEGUNDO PARCIAL TEM - 742
                
                <h2 style="color:#FF0000">Nombre: Mario Jordan Nina</h2>
                <h2 style="color:#FF0000">CI: 6904512</h2>
            </th>

        </table>
        <h1>REGISTRO DE SEMINARIOS</h1>
        <p><a href="MainController?op=nuevo">NUEVO SEMINARIO</a></p>
        <table border="3">
            <tr>
                <TH>ID</TH>
                <TH>Titulo</TH>
                <TH>Expositor</TH>
                <TH>Fecha</TH>
                <TH>Hora</TH>
                <TH>Cupos</TH>
                <TH></TH>
                <TH></TH>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.hora}</td>
                    <td>${item.cupo}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}"
                           onclick="return(confirm('Esta seguro ?'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
