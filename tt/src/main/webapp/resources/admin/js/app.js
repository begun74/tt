var errAjax = 'Error connect to AJAX server!';

function delObject(clazz,id,act)
{
	if(confirm('Удалить запись?'))location.href='admin/delObject?id='+id+'&clazz='+clazz+'&act='+act;
}

function delPhotoFile(code, file_number)
{
	if(confirm('Удалить фото?'))
	{
		
		$.ajax({
			type : "GET",
			url : "delPhotoFile?code="+code+"&file_number="+file_number,
			success : function(data) 
			{
				if(data === 'OK')
				{
					$('#photoNomenclature'+file_number).hide();
					$('#btn-delete'+file_number).hide();
				}
			},
			error : function(e) {
				alert(errAjax);
				display(e);
			}
		});

	}
}

function checkAllCols(amount){
	
    $('#allCols').click(function(){
    	
    	if($('#allCols').attr("checked"))
    	{
	    	for (i = 0; i <= amount; i++) {
	            $('#col'+i).attr("checked","checked");
	        }
    	}
    	else 
    	{
	    	for (i = 0; i <= amount; i++) {
	            $('#col'+i).attr("checked","");
	        }
    	}
	});

}


function clearErrors() {

	$.ajax({
		type : "GET",
		url : "clearErrors",
		timeout : 100000,
		success : function() 
		{
			/*$("#compareItems").text(data.allItems);*/
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
}
	
function checkboxAny(flag,clazz)
{
	/* flag - boolean */
	//alert(flag,clazz);
	if(flag)
		$('.'+clazz).attr("checked","checked");
	else
		$('.'+clazz).attr("checked","");

}


