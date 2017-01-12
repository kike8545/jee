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
   
    $scope.agregar = function(_item) {
        
      if ($scope.carrito.length !== 0) {  //si no hay productos, agrega uno
    	 
    	  posProducto = $scope.carrito.indexOf(_item);	//verifica la posicion del nuevo producto para agregar al carrito
          	if (posProducto !== -1) {	//si es diferente a -1 es porque el producto no habia sido agregado del carrito
          		carritoDeCompras.cars[posProducto].cant += 1; // agrega en el capo cantidad un producto mas del mismo elegido
          		carritoDeCompras.cars[posProducto].preci = _item.precio * carritoDeCompras.cars[posProducto].cant;
          		
          		
  			}else{
  				$scope.carrito.push(_item);//si no existe lo agrega al carrito
  				
  				carritoDeCompras.cars.push({"cant" : _item.cantidad,
					"categor": _item.categoria,
					"idProd": _item.idProducto,
					"nomProd":_item.nombreProducto,
					"preci":_item.precio
					});
  				$scope.productosCarrito.push({"cant" : _item.cantidad,
					"categor": _item.categoria,
					"idProd": _item.idProducto,
					"nomProd":_item.nombreProducto,
					"preci":_item.precio
					});
  			}
          	
           
      }else{
		$scope.carrito.push(_item);
		carritoDeCompras.cars.push({"cant" : _item.cantidad,
									"categor": _item.categoria,
									"idProd": _item.idProducto,
									"nomProd":_item.nombreProducto,
									"preci":_item.precio
									});
		
		$scope.productosCarrito.push({"cant" : _item.cantidad,
			"categor": _item.categoria,
			"idProd": _item.idProducto,
			"nomProd":_item.nombreProducto,
			"preci":_item.precio
			});
	  }
      
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
