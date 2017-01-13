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
    $scope.productosCarrito = [];
    /** se crea este nuevo array para poder modificar los nombres de los campos,
     *  los cuales generan conflicto en el momento de modificar sus valores, 
     *  pues cambian en toda la aplicacion que contenga ese nombre */
    $scope.agregar = function(_item) {
        
      if ($scope.carrito.length !== 0) {/** si hay productos, no agrega uno y valida cual es para modificar su cantidad y precio a pagar */
    	 
    	  posProducto = $scope.carrito.indexOf(_item);	/** verifica la posicion del nuevo producto para agregar al carrito */
          	
    	  if (posProducto !== -1) {/**si es diferente igual a -1 es porque el producto habia sido agregado del carrito */
          		
          		carritoDeCompras.cars[posProducto].cant += 1; /** agrega en el capo cantidad un producto mas del mismo elegido */
          		
          		/** aumenta valor del producto escogido multiplicando el precio por la cantidad*/
          		carritoDeCompras.cars[posProducto].preci = _item.precio * carritoDeCompras.cars[posProducto].cant;
          		
          		
  			}else{
  				$scope.carrito.push(_item);/**si no está producto lo agrega al carrito*/
  				
  				carritoDeCompras.cars.push({"cant" : _item.cantidad, /**si no existe lo agrega al servicio carritoDeCompras*/
					"categor": _item.categoria,
					"idProd": _item.idProducto,
					"nomProd":_item.nombreProducto,
					"preci":_item.precio
					});
  				
  			}
          	
           
      }else{/**si el producto no existe el carrito agrega uno, al igual que en el servicio carritoDeCompras*/
		$scope.carrito.push(_item);
		carritoDeCompras.cars.push({"cant" : _item.cantidad,
									"categor": _item.categoria,
									"idProd": _item.idProducto,
									"nomProd":_item.nombreProducto,
									"preci":_item.precio
									});
		
		
	  }
      $scope.productosCarrito = carritoDeCompras.cars; /**envia los datos para ser visualizados en el front */
    };
    
    $scope.total = function (){
        var total = 0;
        for (item of carritoDeCompras.cars) {
            total += item.preci;
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
