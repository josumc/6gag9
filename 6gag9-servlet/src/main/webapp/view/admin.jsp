<%@page import="com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger"%>
<%@page import="com.txurdi.fct.jpa.model.Publicacion"%>
<%@page import="java.util.List"%>
<%@page import="com.txurdi.fct.jpa.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	List<Publicacion> publicaciones = (List<Publicacion>) request.getSession().getAttribute("publicaciones");
	List<Usuario> usuarios = (List<Usuario>) request.getSession().getAttribute("usuarios");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./../ui-statics/css/bootstrap.min.css">
    <script src="./../ui-statics/js/bootstrap.bundle.min.js"></script>
    <title>Administrador</title>
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
		<br>
		<h1>Publicaciones para admitir</h1>
    </div>
    
    <%if(publicaciones.size() > 0) { %>
   		<table class="table">
    		<thead class="thead-dark">
    			<tr>
    				<td>#</td>
    				<td>Imagen</td>
    				<td>Estado</td>
    				<td>Usuario</td>
    			</tr>
    		</thead>
    		<tbody>
   		<%for(Publicacion publicacion: publicaciones) { %>
	    		<tr>
	    			<th><%=publicacion.getId() %></th>
	    			<th><a href="./../Busqueda?id_publicacion=<%=publicacion.getId()%>">Click aqui para ver</a></th>
	    			<th><%=publicacion.getEstado() %></th>
	    			<th><%=publicacion.getId_usuario().getEmail() %></th>
	    			<th>
	    				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="rellenarModalPublicacion(<%=publicacion.getId()%>)">Editar</button>
	    			</th>
	    			<th>
	    				<form action="./Admin" method="post">
	    					<input type="text" class="hidden" hidden value="delete" name="action">
	    					<button type="submit" class="btn btn-danger" name="btnWithId" value="<%=publicacion.getId()%>">Eliminar</button>
	    				</form>
	    			</th>
	    		</tr>
    	<% } %>
    		</tbody>
    	</table>
    <%} else if(publicaciones.size() == 0) { %>
    	<div class="container d-flex align-middle justify-content-center">
    		<img src="./../ui-statics/img/nothing.png" width="400">
    	</div>
    <%} %>
    
    <div class="ms-auto p-2">
		<br>
		<h1>Usuarios para admitir</h1>
    </div>
    <%if(usuarios.size() > 0) { %>
   		<table class="table">
    		<thead class="thead-dark">
    			<tr>
    				<td>#</td>
    				<td>Usuario</td>
    				<td>Estado</td>
    			</tr>
    		</thead>
    		<tbody>
   		<%for(Usuario usuario: usuarios) { %>
	    		<tr>
	    			<th><%=usuario.getId() %></th>
	    			<th><%=usuario.getEmail() %></th>
	    			<th><%=usuario.getEstado() %></th>
	    			<th>
	    				<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" onClick="rellenarModalUsuario(<%=usuario.getId()%>)">Editar</button>
	    			</th>
	    			<th>
	    				<form action="./Admin" method="post">
	    					<input type="text" class="hidden" hidden value="deleteUsuario" name="action">
	    					<button type="submit" class="btn btn-danger" name="btnWithId" value="<%=usuario.getId()%>">Eliminar</button>
	    				</form>
	    			</th>
	    		</tr>
    	<% } %>
    		</tbody>
    	</table>
    <%} else if(usuarios.size() == 0) { %>
    	<div class="container d-flex align-middle justify-content-center">
    		<img src="./../ui-statics/img/nothing.png" width="400">
    	</div>
    <%} %>
    
    <!-- MODAL -->
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLabel"></h5>
        			<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      			</div>
      			<div class="modal-body">
        			<form id="formPublicacion" method="POST" action="./Admin">
          				<div class="mb-3">
            				<label for="recipient-name" id="idLabel" class="col-form-label">Id de la publicacion</label>
            				<input type="text" name="idPublicacion" class="form-control" id="idP" readonly>
          				</div>
          				<div class="mb-3">
            				<select class="form-select" name="estado" aria-label="Default select example">
  								<option value="<%=DefaultEnumInteger.DEFECTO.getValue() %>" selected>No validado</option>
								<option value="<%=DefaultEnumInteger.VALIDO.getValue() %>">Validar</option>
							</select>
          				</div>
          				<br>
          				<div class="modal-footer">
	          				<button type="submit" class="btn btn-primary">Actualizar</button>
        					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        					<input type="text" id="actionButton" hidden class="hidden" value="editUsuario" name="action">
      					</div>
        			</form>
      			</div>
    		</div>
  		</div>
	</div>
	
	<script type="text/javascript">
		function rellenarModalPublicacion(id) {
			document.getElementById("formPublicacion").reset();
			
			document.getElementById("exampleModalLabel").innerHTML = "Modificar estado de publicacion";
			document.getElementById("idLabel").innerHTML = "Id de la publicacion"
			document.getElementById("actionButton").value ="edit";
			
			document.getElementById("idP").value = id;
		}
		
		function rellenarModalUsuario(id) {
			document.getElementById("formPublicacion").reset();
			
			document.getElementById("exampleModalLabel").innerHTML = "Modificar estado de usuario";
			document.getElementById("idLabel").innerHTML = "Id del usuario"
				document.getElementById("actionButton").value ="editUsuario";
			document.getElementById("idP").value = id;
		}
	</script>
</body>
</html>