<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./ui-statics/css/bootstrap.min.css">
    <script src="./ui-statics/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>
    <script src="./ui-statics/js/validaciones-form.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <title>Registro</title>
</head>
<body>
	<!-- Validation -->
	<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
    <br>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-2">
            <img src="./ui-statics/img/logo.png" class="img-fluid mx-auto">
        	
        </div>
    </div>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-6">
            <form method="post" action="Register" >
                <div class="form-group">
                    <label for="emailInput">Direccion email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" aria-describedby="emailHelp" placeholder="ejemplo@ejemplo.com">
                </div>
                <br>
                <div class="form-group">
                    <label for="passwordInput">Password</label>
                     <input type="password" class="form-control" id="passwordInput" name="password" placeholder="Introduzca una contraseña">
                </div>
                <br>
                <div class="form-group">
                    <label for="passwordInputConfirm">Password</label>
                    <input type="password" class="form-control" id="passwordInputConfirm" name="passwordC" placeholder="Repita una contraseña">
                </div>
                <br>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary">Registrarse</button>
                </div>
            </form>
            <br>
            <div class="col text-center">
                <label for="btnBackLogin">¿Estas ya registrado?</label>
                <br>
                <input type="button" class="btn btn-primary" value="Iniciar sesion" onclick="location.href='./Login';">
            </div>
        </div>
    </div>
    <!-- DIALOG -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.0/dist/sweetalert2.all.min.js"></script>
	<script type="text/javascript">
		var status = document.getElementById("status").value;
		
		if(status == "success") {
			dialogAfertRegisterOk();
		} else if(status == "duplicado") {
			dialogAfterRegisterDuplicated();
		} else if(status == "failed") {
			dialogAfterRegisterError();
		} else if(status == "fill") {
			dialogAfterRegisterNoFill();
		}
		
	</script>
	<!-- Eliminacion de atributo -->
	<% 
		request.removeAttribute("success");
	%>
</body>
</html>