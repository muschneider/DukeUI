

function openConfigFile(fileName) {
	$('#modalDukeConfigFiles').modal('hide');
    $.ajax({
		type: "GET",
		url: "/rest/datasource/"+fileName,
		cache: false,
		dataType: 'json',
		success: function(data){

            var tHeader = '';
			$.each(data.header, function(index, element) {
                tHeader += '<th>'+element+'</th>';
            });
            $("#dsHeader").html(tHeader);

            var tData = '';
			$.each(data.records, function(index, element) {
                tData += '<tr>';
                $.each(element, function(fieldName, fieldValue) {
                    tData += '<td>'+fieldValue+'</td>';
                });
                tData += '</tr>';
            });
            $("#dsData").html(tData);

            $("#dataSource").show();
		}

    });
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
