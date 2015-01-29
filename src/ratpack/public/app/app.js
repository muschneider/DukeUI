function openConfigFile(fileName) {
	$('#modalDukeConfigFiles').modal('hide');
    $.ajax({
		type: "GET",
		url: "/rest/datasource/"+fileName,
		cache: false,
		dataType: 'json',
		success: function(data){
			$('#dsInput').dataTable( {
				"searching": false,
				"lengthChange": false,
				"data": data.data,
				"columns": data.header
			});

            $("#dataSource").show();
		}

    });
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
