<%@page import="com.txurdi.fct.jpa.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	
	//En caso de no tener una sesion iniciada	
	if(usuario == null) {
		response.sendRedirect("./Inicio");
	}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="./../ui-statics/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./../ui-statics/css/bootstrap.min.css">
    <script src="./../ui-statics/js/validaciones-form.js"></script>
    <title>Cambiar contraseña</title>
</head>
<body>
	<!-- Validation -->
	<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
	<br>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-2">
            <img src="./../ui-statics/img/logo.png" class="img-fluid mx-auto">
    	</div>
    </div>
    <br>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-6">
    		<form method="post" action="ChangePassword">
				<div class="form-group">
			    	<label for="actualPassword">Contraseña actual</label>
			    	<input type="password" name="inputActualPassword" class="form-control" id="actualPassword" placeholder="Contraseña actual">
			  	</div>
			  	<br>
				<div class="form-group">
			    	<label for="newPassword">Nueva contraseña</label>
			    	<input type="password" name="inputNewPassword" class="form-control" id="newPassword" placeholder="Nueva contraseña">
			  	</div>
			  	<br>
			  	<div class="form-group">
			    	<label for="newPasswordC">Confirma la nueva contraseña</label>
			    	<input type="password" name="inputNewConfirmPassword" class="form-control" id="newPasswordC" placeholder="Confirmar nueva contraseña">
			  	</div>
			  	<br>
			  	<div class="col text-center">
			  		<button type="submit" class="btn btn-primary">Actualizar</button>
			  	</div>
			</form>
			<br>
			<div class="col text-center">
				<button type="submit" class="btn btn-danger" onclick="location.href='./../Cuenta';">Cancelar</button>
			</div>
    	</div>
    </div>
    <!-- DIALOG -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.0/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript">
 	 	var status = document.getElementById("status").value;
    	if(status == "success") {
    		dialogAfterPasswordChange();
    	} else if(status == "fill") {
    		dialogAfterRegisterNoFill();
    	} else if(status == "error") {
    		dialogAfterRegisterError();
    	} else if(status == "equals") {
    		dialogAfterRegisterNoEquals();
    	}
    </script>
    <!-- Eliminacion de atributo -->
	<% 
		request.removeAttribute("success");
	%>
</body>
</html>