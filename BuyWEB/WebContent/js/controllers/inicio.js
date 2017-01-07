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
    $scope.comprar = function(_item) {
        _item.ocultar = true;
        $scope.carrito.push(_item);
        carritoDeCompras.productos = $scope.carrito;
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
