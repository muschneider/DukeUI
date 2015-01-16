function openConfigModal() {
	
	$.ajax({
		type: "GET",
		url: "rest/configs",
		cache: false,
		dataType: 'json',
		success: function(data){
			var htmlItens = '';
			$.each(data.files, function(index, element) {
				htmlItens += element + "<br>";
	        });			
			
			$('#idfilesTopOpen').html(htmlItens);
		    $('#modalDukeConfigFiles').modal({
		  	  keyboard: false
		  	})    
		}
	});	
	
}
