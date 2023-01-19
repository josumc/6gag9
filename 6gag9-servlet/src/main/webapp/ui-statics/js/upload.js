let blob;
let descripcion;
//----------App.js---------------------//
$(document).ready(function() {
	//Drag and drop
	var holder = document.getElementById('holder');
	holder.ondragover = function() { this.className = 'hover'; return false; };

	holder.ondrop = function(e) {
		e.preventDefault();
		if (e.dataTransfer.files[0].type == "image/jpeg" && e.dataTransfer.files[0].size < 500000) {
			this.className = 'hidden';
			
			var file = e.dataTransfer.files[0];
			var reader = new FileReader();

			reader.onload = function(event) {
				document.getElementById('image_droped').className = 'visible'
				$('#image_droped').attr('src', event.target.result);
				document.getElementById("getImagesrc").value = document.getElementById("image_droped").src;
			}
			reader.readAsDataURL(file);
		} else {
			dialogAfterUploadImage();
		}
	};


	//formulario
	document.getElementById("btnSendUpload").addEventListener('click', function() {
		blob = document.getElementById("image_droped");

		descripcion = document.getElementById("message-text").value;

		if (blob === null || descripcion.length < 8) {
			dialogAfterUploadNoFill();
			return;
		}
	});
});


