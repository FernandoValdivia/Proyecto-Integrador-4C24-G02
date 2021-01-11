function eliminar(id){
	swal({
	  title: "Estas seguro de liminar?",
	  text: "Una vez eliminado, no podrá recuperar este producto!",
	  icon: "warning",
	  buttons: true,
	  dangerMode: true,
	})
	.then((OK) => {
	  if (OK) {
	  $.ajax({
	  		url:"/eliminar/"+id,
	  		success: function(res) {
	  			console.log(res);
	  		},
	  	});
	    swal("Eliminado! Tu archivo ha sido eliminado con éxito!", {
	      icon: "success",
	    }).then((ok)=>{
	    	if(ok){
	    		location.href="/listar";
	    		}
	    	});
	  } else {
	    swal("Tu archivo esta seguro!");
	  }
	});
}