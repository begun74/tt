

function delObject(clazz,id,act)
{
	if(confirm('Удалить запись?'))location.href='admin/delObject?id='+id+'&clazz='+clazz+'&act='+act;
}

function delPhotoFile(code, file_number)
{
	if(confirm('Удалить фото?'))
	{
		//$('#photoNomenclature'+file_number).hide();
		
		
		$('<img/>', {
			
			id: 'photoNomenclature'+file_number,
			src:    '/pics/products/'+code+'/S/'+code+'_S_'+file_number+'.jpg',
			error:  function(e){
				  $( this ).attr( "src", 'resources/images/products/nopicture.jpg' );
				  $('#btn-delete'+file_number).hide();
			}
			  
		}).appendTo($('div_pn'));

		//alert('show');
		
		//$('#photoNomenclature'+file_number).show();
		//$('#btn-delete'+file_number).show();

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


