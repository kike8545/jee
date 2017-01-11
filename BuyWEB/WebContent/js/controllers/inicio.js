miAppAngular.controller('inicio', function($scope, $location, $http, carritoDeCompras) {
	
	var javaObject = new Object();
	
	javaObject = JSON.stringify(javaObject);
	
	$http({                
        method: 'POST',
        url: '../BuyRestWEB/ProductoService/findAll',
        dataType: 'json',
        data: ObjecttoParams({params: javaObject}),
        headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' }
    
	}).success(function(data){
         $scope.productos = data;
    
	}).error(function(error,status){
        console.log(error);  
    });

   
    $scope.carrito = [];
   
    $scope.agregar = function(_item) {
        _item.ocultar = true;
        _item.cantidad = 1; //agrego un atributo cantidad en 1 al producto, cuando lo agrega al carrito
      if ($scope.carrito.length !==0) {  //si no hay productos, agrega uno
    	 
    	  posProducto = $scope.carrito.indexOf(_item);	//verifica la posicion del nuevo producto para agregar al carrito
          	if (posProducto !== -1) {	//si es diferente a -1 es porque el producto no habia sido agregado del carrito
          		$scope.carrito[posProducto].cantidad += 1; // agrega en el capo cantidad un producto mas del mismo elegido
          		$scope.carrito[posProducto].precio *= $scope.carrito[posProducto].cantidad; //multiplica la cantidad por el valor de la unidad
          		console.log(carritoDeCompras.productos.indexOf($scope.carrito[posProducto]));
          		if (carritoDeCompras.productos.indexOf($scope.carrito[posProducto]) === -1) {
          			carritoDeCompras.productos = $scope.carrito[posProducto];
				}else{
					carritoDeCompras.productos[posProducto].cantidad += 1;
					carritoDeCompras.productos[posProducto].precio *= carritoDeCompras.productos[posProducto].cantidad;
				}
          		
          		//console.log("cant. para " + $scope.carrito[posProducto].nombreProducto +"  =  "+ $scope.carrito[posProducto].cantidad + " total = " + $scope.carrito[posProducto].precio);
  			}else{
  				$scope.carrito.push(_item);//si no existe lo agrega al carrito
  			}
          	
           
      }else{
		$scope.carrito.push(_item);
	  }
        
        
        //carritoDeCompras.productos = $scope.carrito;
    };
    
    $scope.total = function (){
        var total = 0;
        for (item of $scope.carrito) {
            total += item.precio;
        }
        carritoDeCompras.total = total;
        return total;
    }

    $scope.finalizar = function() {
        $location.path("/confirmacion");
    };
});
 
function ObjecttoParams(obj) {
    var p = [];
    for ( var key in obj) {
        p.push(key + '=' + encodeURIComponent(obj[key]));
    }
    return p.join('&');
};
