<%@page import="java.util.Base64"%>
<%@page import="com.txurdi.fct.jpa.model.Publicacion"%>
<%@page import="com.txurdi.fct.servlet.model.PublicacionImage"%>
<%@page import="java.util.List"%>
<%@page import="com.txurdi.fct.jpa.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	List<Publicacion> publicaciones = (List<Publicacion>) request.getSession().getAttribute("publicaciones");

%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./../ui-statics/css/bootstrap.min.css">
    <script src="./../ui-statics/js/bootstrap.bundle.min.js"></script>
    <title>Mis publicaciones</title>
</head>
<body>
	<div class="ms-auto p-2">
		<div class="ms-auto p-2">
			<button class="btn btn-outline-danger" onclick="location.href='./../Inicio'" >
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
  					<path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
				</svg>
				Inicio
			</button>
			<button type="button" class="btn btn-outline-success" onclick="location.href='./../Cuenta';">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-grid-3x3" viewBox="0 0 16 16">
  					<path d="M0 1.5A1.5 1.5 0 0 1 1.5 0h13A1.5 1.5 0 0 1 16 1.5v13a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 14.5v-13zM1.5 1a.5.5 0 0 0-.5.5V5h4V1H1.5zM5 6H1v4h4V6zm1 4h4V6H6v4zm-1 1H1v3.5a.5.5 0 0 0 .5.5H5v-4zm1 0v4h4v-4H6zm5 0v4h3.5a.5.5 0 0 0 .5-.5V11h-4zm0-1h4V6h-4v4zm0-5h4V1.5a.5.5 0 0 0-.5-.5H11v4zm-1 0V1H6v4h4z"/>
				</svg>
				Cuenta
			</button>
		</div>
    </div>
    <!-- Publicaciones -->
    <% if(publicaciones == null || publicaciones.size() < 1) {%>
    	<div class="container d-flex align-items-center justify-content-center">
    		<div class="form-row"><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></div>
    		<img src="./../ui-statics/img/error_404.png">
    		<h1>NO HAY IMAGENES DISPONIBLES</h1>
    	</div>	
    <% } else {
    	for(Publicacion publicacion : publicaciones) {
    %>	
    	<form action="./LikePage" method="post">
    		<div class="container d-flex align-items-center justify-content-center">
	            <div class="col-md-6 px-0">
	                <fieldset class="border p-2">
	                    <div class="text-center">
	                        <img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(publicacion.getFoto())%>" class="img-fluid">
	                    </div>
	                    <p><%=publicacion.getDescripcion()%></p>
	                </fieldset>
	            </div>
			</div>
    	</form>
    	<div class="form-row"><br><br></div>
    <%}
    } %> 
</body>
</html>