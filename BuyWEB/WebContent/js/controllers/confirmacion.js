miAppAngular
			.controller('confirmacion', function($scope, $http,	carritoDeCompras, userService) {

	$scope.total = carritoDeCompras.total;
	$scope.productos = carritoDeCompras.productos;

	
	$scope.pagar = function() {// Ésta funcion inserta los productos que el usuario "pago" en historial compras
		
		//productosPagar = $scope.productos[0];
		//user = userService.userData; //se accede al objeto userData con los datos del usuario
		
        var javaObject = new Object();
    	//javaObject = user; 
       
        
        javaObject.producto = $scope.productos;// cargo los datos de los productos a pagar
        javaObject.user = userService.userData;// cargo los datos del usuario por medio del servicio
    	
        javaObject = JSON.stringify(javaObject);
    	$http({                
            method: 'POST',
            url: '../BuyRestWEB/HistorialComprasService/saveHistorialComprasByUsuario',
            dataType: 'json',
            data: ObjecttoParams({params: javaObject}),
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
        
    	}).success(function(data){
             $scope.productos = data;
        
    	}).error(function(error,status){
            console.log(error);  
        });

	};
});
