<%-- 
    Document   : Principal
    Created on : 07-02-2020, 12:00:53 AM
    Author     : DELL 1
--%>

<%@page import="Model.Usuarios"%><!--Modelo usuario para guardar los datos que se utilizaron en Controller.java-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Me permite usar las sessiones deben pegar esto donde necesiten comprobar session-->
<%@page session="true"%>

<%
    // Obtengo la session que cree en el Controlador.java
    HttpSession sessionActiva = request.getSession();
    // Verifico que la variable tiene valores para evitar el nullPointerException
    if(sessionActiva.getAttribute("datosUsuario")!= null){
       // Guardo los valores de la session en un objeto Usuario 
       Usuarios usuarioLogeado = (Usuarios) sessionActiva.getAttribute("datosUsuario");
    }else{
        // Session nulla reenvio a index.jsp para que inicie session
        response.sendRedirect("index.jsp");
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <link href="css/estilos.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        
       <title>Control de Inventario</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Incluimos la vista parcial la cual contiene las url de las librerias  correspondientes a estilos y js -->
<%@include file="WEB-INF/Vistas-Parciales/css-js.jspf" %>

    
 <!-- Vistar parciales de la parte superior de nuestra aplicación -->
<%@include file="WEB-INF/Vistas-Parciales/encabezado2.jspf"%>
         
        <div class="container mt-4">
            <h1>Bienvenid@ al Sistema <strong>${nombre}</strong> es un placer que estes con nosotros...</h1>
        </div>
        
        
        <!-- Vistar parciales de la parte inferior de nuestra aplicación -->
<%@include file="WEB-INF/Vistas-Parciales/pie.jspf" %>
        
<br><br>
        
     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
    </body>
</html>
