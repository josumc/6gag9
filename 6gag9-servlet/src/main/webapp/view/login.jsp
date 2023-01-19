<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./ui-statics/css/bootstrap.min.css">
    <script src="./ui-statics/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/3.1.9-1/crypto-js.js"></script>
    <script src="./ui-statics/js/validaciones-form.js"></script>
    <title>Iniciar sesion</title>
</head>
<body>
	<!-- Validation -->
	<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
	<div class="ms-auto p-2">
		<div class="ms-auto p-2">
			<button class="btn btn-outline-danger" onclick="location.href='./Inicio'" >
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-house" viewBox="0 0 16 16">
  					<path d="M8.707 1.5a1 1 0 0 0-1.414 0L.646 8.146a.5.5 0 0 0 .708.708L2 8.207V13.5A1.5 1.5 0 0 0 3.5 15h9a1.5 1.5 0 0 0 1.5-1.5V8.207l.646.647a.5.5 0 0 0 .708-.708L13 5.793V2.5a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1.293L8.707 1.5ZM13 7.207V13.5a.5.5 0 0 1-.5.5h-9a.5.5 0 0 1-.5-.5V7.207l5-5 5 5Z"/>
				</svg>
				Inicio
			</button>
		</div>
	</div>
    <br>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-2">
            <img src="./ui-statics/img/logo.png" class="img-fluid mx-auto">
        </div>
    </div>
    <div class="container d-flex align-items-center justify-content-center">
        <div class="form-group col-md-6">
            <form method="post" action="./Login" onsubmit="return loginUser()">
                <div class="form-group">
                    <label for="emailInput">Direccion email</label>
                    <input type="email" class="form-control" id="inputEmail" name="email" aria-describedby="emailHelp" placeholder="ejemplo@ejemplo.com">
                </div>
                <br>
                <div class="form-group">
                    <label for="passwordInput">Password</label>
                    <input type="password" class="form-control" id="passwordInput" name="password" placeholder="Introduzca una contraseña" minlength="8">
                </div>
                <br>
                <div class="col text-center">
                    <button type="submit" class="btn btn-primary">Iniciar sesion</button>
                </div>
            </form>
            <br>
            <div class="col text-center">
                <label for="btnBackLogin">Aun no estas registrado?</label>
                <br>
                <input type="button" class="btn btn-primary" value="Registrarse" onclick="location.href='./Register';">
            </div>
        </div>
    </div>
    <!-- DIALOG -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.0/dist/sweetalert2.all.min.js"></script>
    <script type="text/javascript">
	    var status = document.getElementById("status").value;
		
		if(status == "failed") {
			dialogAfterLoginError();
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