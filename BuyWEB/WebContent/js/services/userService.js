miAppAngular.service('userService', function() {
	/*
	  this.userData = function(){ // Se escribe un usuario de la BD manualmente, aqui deberia validarse segun el inicio de sesion
	  return [
	          {"idUsuario":1, "mail":"prueba@prueba.com", "nombre":"Fulano de tal", "pass" : "123" }
	          ];
	   };*/
	 
	this.userData = {
		"idUsuario" : 1,
		"mail" : "prueba@prueba.com",
		"nombre" : "Fulano de tal",
		"pass" : "123"
	};
});