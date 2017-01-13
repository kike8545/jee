miAppAngular
			.controller('confirmacion', function($scope, $http,	carritoDeCompras, userService) {

	$scope.total = carritoDeCompras.total;
	$scope.productosCarrito = carritoDeCompras.cars;

	
	$scope.pagar = function() {/** Ésta funcion inserta los productos que el usuario "pagó" en historial compras*/
		
	
        var javaObject = new Object();
        
        javaObject.producto = $scope.productosCarrito;/** cargo los datos de los productos a pagar*/
        javaObject.user = userService.userData;/** cargo los datos del usuario por medio del servicio*/
    	
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
