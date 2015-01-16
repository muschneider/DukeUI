

function openConfigFile(fileName) {
	$('#modalDukeConfigFiles').modal('hide');
	console.log(fileName);
}


function openConfigModal() {
	$.ajax({
		type: "GET",
		url: "rest/configs",
		cache: false,
		dataType: 'json',
		success: function(data){
			var htmlItens = '';
			$.each(data.files, function(index, element) {
				htmlItens += "<a href='#' class='list-group-item' onclick='openConfigFile(\""+element+"\")'>"+element+"</a>\n";
	        });			
			
			$('#idfilesTopOpen').html(htmlItens);
		    $('#modalDukeConfigFiles').modal({
		  	  keyboard: false
		  	})    
		}
	});	
	
}
