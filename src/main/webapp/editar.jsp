<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Seminario prod = (Seminario) request.getAttribute("prod");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> <c:if test="${requestScope.op=='nuevo'}" var="res" scope="request">
                REGISTRAR
            </c:if>
            <c:if test="${requestScope.op=='editar'}" var="res" scope="request">
                Editar
            </c:if>
            NUEVO SEMINARIO
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${prod.id}">
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${prod.titulo}"></td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text"  name="expositor" value="${prod.expositor}"></td>
                </tr>
                <tr>

                <tr>
                    <td>Fecha</td>
                    <td><input type="text" name="fecha" value="${prod.fecha}"></td>
                </tr>
                
                <tr>
                    <td>Hora</td>
                    <td><input type="text" name="hora" value="${prod.hora}"></td>
                </tr>





                <td>Cupo</td>
                <td><input type="number" name="cupo" value="${prod.cupo}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="opg" value="${requestScope.op}"/>
                        <input type="hidden" name="op" value="grabar"/>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Enviar"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
