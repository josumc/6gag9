<%@page import="com.txurdi.fct.servlet.model.PublicacionImage"%>
<%@page import="com.txurdi.fct.jpa.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>
<%@ page import="java.util.List" %>

<%
	Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
	
	List<PublicacionImage> publicaciones = (List<PublicacionImage>) request.getSession().getAttribute("publicaciones");
%>

<!DOCTYPE html>
<html lang="es">
<head>
   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="./ui-statics/css/custom-index.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./ui-statics/css/bootstrap.min.css">
    <link rel="stylesheet" href="./ui-statics/css/upload.css">
    <script type="text/javascript" src="./ui-statics/js/bootstrap.bundle.min.js"></script>
	<script src="./ui-statics/js/validaciones-form.js"></script>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.js"></script>
    <title>Inicio</title>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-light fixed-top bg-light border-bottom border-dark">
        <div class="container-fluid">
            <a class="navbar-brand m-auto" href="./Inicio"><img src="./ui-statics/img/logo.png" width="64" height="64"></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active ms-2" aria-current="page" href="./RandomPublicacion"><img src="./ui-statics/img/random.png" width="40" height="40">Random</a>
                    </li>
                </ul>
                <form class="d-flex" action="./Search" method="get">
                    <input class="form-control me-2 ms-2" name="descripcion" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
 				<%
                	if(usuario != null && usuario.getId() != 0) { %>
                		<a class="btn btn-primary my-2 ms-2" href="./Cuenta" role="button">
	                    	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
	                    		<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
	                        	<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
	                    	</svg>
	                    	Mi cuenta
                		</a>
                		<button type="button" class="btn btn-primary my-2 ms-2 modalTrigger" data-bs-toggle="modal" data-bs-target="#exampleModal" >
    						<svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" fill="currentColor" class="bi bi-upload" viewBox="0 0 16 16">
  								<path d="M.5 9.9a.5.5 0 0 1 .5.5v2.5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2.5a.5.5 0 0 1 1 0v2.5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2v-2.5a.5.5 0 0 1 .5-.5z"/>
  								<path d="M7.646 1.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 2.707V11.5a.5.5 0 0 1-1 0V2.707L5.354 4.854a.5.5 0 1 1-.708-.708l3-3z"/>
							</svg>
							Subir meme
   						</button>
                	<%	} else { %>
                		<a class="btn btn-primary my-2 ms-2" href="./Login" role="button">Iniciar/Registrarse</a>
                	<%} %>

            </div>
        </div>
    </nav>
    <div class="form-row"><br><br><br><br></div>
   	<!-- Publicaciones -->
    <% if(publicaciones == null || publicaciones.size() < 1) {%>
    	<div class="container d-flex align-items-center justify-content-center">
    		<div class="form-row"><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></div>
    		<img src="./ui-statics/img/error_404.png">
    		<h1>NO HAY IMAGENES DISPONIBLES</h1>
    	</div>	
    <% } else {
    	for(PublicacionImage publicacion : publicaciones) {
    %>	
    	<form action="./LikePage" method="post">
    		<div class="container d-flex align-items-center justify-content-center">
	            <div class="col-md-6 px-0">
	                <fieldset class="border p-2">
	                    <div class="text-center">
	                        <img src="data:image/jpeg;base64,<%=publicacion.getImageDecoded()%>" class="img-fluid">
	                    </div>
	                    <div class="d-flex">
	                        <div class="p-2">
	                        	<% if(usuario != null) { %>	
	                        		<% if(publicacion.getLike() == 1) { %>
	                        		<button type="submit"  class="btn btn-light">
	                        			<svg name="unlike" xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-heart-fill" viewBox="0 0 16 16">
	                                		<path fill-rule="evenodd" d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"/>
	                            		</svg>
	                            	</button>
	                            	<input type="text" class="hidden" name="id_like" value="<%= publicacion.getId_like()%>">
	                            	<input type="text" class="hidden" name="method_like" value="si">
	                        	<%} else { %>
	                        		<button type="submit" class="btn btn-light">
	                            		<svg name="like" xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
	                                		<path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
	                            		</svg>
	                            	</button>
	                            	<input type="text" class="hidden" name="method_like" value="no">
	                            <%} %>
	                            <input type="text" name="id_publicacion" class="hidden" value="<%=publicacion.getId()%>"/>
	                        </div>
	                        	<%} %>
	                    </div>
	                    <p><%=publicacion.getDescripcion()%></p>
	                </fieldset>
	            </div>
			</div>
    	</form>
    	<div class="form-row"><br><br></div>
    <%}
    } %> 
   
	<svg onclick="topFunction()" id="btnToTop"  xmlns="http://www.w3.org/2000/svg" width="64" height="64" fill="currentColor" class="bi bi-arrow-up-circle-fill" viewBox="0 0 16 16">
  		<path d="M16 8A8 8 0 1 0 0 8a8 8 0 0 0 16 0zm-7.5 3.5a.5.5 0 0 1-1 0V5.707L5.354 7.854a.5.5 0 1 1-.708-.708l3-3a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1-.708.708L8.5 5.707V11.5z"/>
	</svg>
	<!-- MODAL UPLOAD DIALOG -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<h5 class="modal-title" id="exampleModalLabel">Subir meme</h5>
	        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      		</div>
	      		<div class="modal-body">
	        		<form id="formUpload" action="./Upload" method="post">
	          			<div class="mb-3 text-center">
	            			<div id="holder" style="" id="holder" class="holder_default text-center">
	          					<img src="" id="image_droped" name="droped" width="200" style="border: 3px dashed #7A97FC;" class=" hidden"/>
	          					<input type="text" class="form-control hidden" name="dropedText" id="getImagesrc">
	        				</div>
	          			</div>
	          			<div class="mb-3">
	            			<label for="message-text" class="col-form-label">Descripcion:</label>
	            			<textarea class="form-control" name="description" id="message-text" required></textarea>
	            			
	          			</div>
	          			<div class="modal-footer">
	        				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	        				<button type="submit" class="btn btn-primary" id="btnSendUpload">Subir</button>
	      				</div>
	        		</form>
	      		</div>
	      		
	    	</div>
	  	</div>
	</div>
    <script src="./ui-statics/js/btn-actions.js"></script>
    <script type="text/javascript" src="./ui-statics/js/upload.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.0/dist/sweetalert2.all.min.js"></script>
</body>
</html>