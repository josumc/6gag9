function registerUser() {
	let email = document.getElementById('inputEmail');
	let pwd = document.getElementById('passwordInput');
	let pwdC = document.getElementById('passwordInputConfirm');

	if (pwd.value === '' || pwdC.value === '' || email.value === '') {
		document.getElementById('feedback').innerHTML = 'Rellena todos los campos';
		return false;
	}

	if (pwd.value.lenght < 8) {
		document.getElementById('feedback').innerHTML = 'Las contrase\361as debe ser mayor a 8';
		return false;
	}

	if (pwd.value != pwdC.value) {
		document.getElementById('feedback').innerHTML = 'Las contrase\361as no coinciden';
		return false;
	}

	var Usuario = new Object();
	Usuario.email = CryptoJS.MD5(email.value).toString();
	Usuario.password = CryptoJS.MD5(pwd.value).toString();

	fetch("./Register", { method: "POST", body: JSON.stringify(Usuario), redirect: 'follow' }).then(result => {
		if (result.status == 301) {
			alert("Su cuenta esta creada, esta a la espera de la validacion por uno de nuestros administradores");
			location.href = './Login';
		}

	}).catch(err => {
		// if any error occured, then catch it here
		console.error(err);
	});
}

/**
	En caso de que la entrada se haya añadido correctamente
 */
function dialogAfertRegisterOk() {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: 'Operacion exitosa',
		text: "Tu cuenta ha sido creada con exito",
		icon: 'success',
		showCancelButton: false,
		confirmButtonText: 'Ir a la pagina principal',
		reverseButtons: true,
		allowOutsideClick: false
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = "./Login";
		}
	})
}


/**
	En caso de que la entrada sea duplicada
 */
function dialogAfterRegisterDuplicated() {
	Swal.fire(
		'Error',
		'Este usuario ya existe',
		'warning',
		{
			allowOutsideClick: false
		}
	)
}

/**
	En caso de que haya ocurrido un error en el proceso
 */
function dialogAfterRegisterError() {
	Swal.fire(
		'Ops....',
		'Algo ha ocurrido en el proceso, intentelo de nuevo o espere un momento.',
		'info'
	)
}

function dialogAfterLoginError() {
	Swal.fire({
		icon: 'warning',
		title: 'Ops....',
		text: 'Datos incorrectos o el usuario no ha sido validado por un administrador',
		allowOutsideClick: false,
  		allowEscapeKey: false
	});
}

/**
	En caso de que la contraseña haya sido cambiada con exito
 */
function dialogAfterPasswordChange() {
	const swalWithBootstrapButtons = Swal.mixin({
		customClass: {
			confirmButton: 'btn btn-success'
		},
		buttonsStyling: false
	})

	swalWithBootstrapButtons.fire({
		title: 'Operacion exitosa',
		text: "Tu contrase\361a ha sido cambiada con exito",
		icon: 'success',
		allowOutsideClick: false,
  		allowEscapeKey: false,
		showCancelButton: false,
		confirmButtonText: 'Ir a mi cuenta',
		reverseButtons: true,
		allowOutsideClick: false
	}).then((result) => {
		if (result.isConfirmed) {
			window.location.href = "./../Cuenta";
		}
	})
}

/**
	En caso de que no se rellenen todos los campos o requisitos
 */
function dialogAfterRegisterNoFill() {
	Swal.fire({
		icon: 'warning',
		title: 'Cumple los requisitos',
		text: 'Porfavor rellena todos los campos. \nLa contrase\361a debe tener un minimo de 6 caracteres',
		allowOutsideClick: false,
  		allowEscapeKey: false
	});
}

/**
	Solo para imagenes
 */
function dialogAfterUploadImage() {
	Swal.fire({
		icon: 'warning',
		title: 'Solo imagenes',
		text: 'Solo se admiten jpg con tama\361o inferior a 1MB.',
		allowOutsideClick: false,
  		allowEscapeKey: false
	});
}

/**
	En caso de que no se rellenen todos los campos o requisitos
 */
function dialogAfterUploadNoFill() {
	Swal.fire({
		icon: 'warning',
		title: 'Cumple los requisitos',
		text: 'Porfavor rellena todos los campos.',
		allowOutsideClick: false,
  		allowEscapeKey: false
	});
}

/**
	En caso de que las contraseñas no coincidan
 */
 function dialogAfterRegisterNoEquals() {
	Swal.fire({
		icon: 'warning',
		title: 'Contrase\361a no coincide',
		text: 'La contrase\361a anterior debe coincidir'
	});
}

function loginUser() {
	let email = document.getElementById('inputEmail');
	let pwd = document.getElementById('passwordInput');

	if (pwd.value.lenght < 8) {
		document.getElementById('feedback').innerHTML = 'Las contrase\361as debe ser mayor a 8';
		return false;
	}

	var Usuario = new Object();
	Usuario.email = CryptoJS.MD5(email.value).toString();
	Usuario.password = CryptoJS.MD5(pwd.value).toString();

	fetch("./Login", {
		method: "POST",
		body: JSON.stringify(Usuario)
	}).then(result => {
		if (result.status == 301) {
			location.href = './Inicio';
		}

	}).catch(err => {
		// if any error occured, then catch it here
		console.error(err);
	});
}

