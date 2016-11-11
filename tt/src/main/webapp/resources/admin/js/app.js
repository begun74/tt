

function delObject(id,clazz,act)
{
	if(confirm('Delete record?'))location.href='admin/delObject?id='+id+'&clazz='+clazz+'&act='+act;
}

function checkAllCols(amount){
	
    $('#allCols').click(function(){
    	
		
		for (i = 0; i <= amount; i++) {
            $('#col'+i).attr("checked","checked");
           
        }
	});

}