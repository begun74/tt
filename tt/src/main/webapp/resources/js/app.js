
function increment(obj, value) {
	
	alert(obj+'  '+value);
}

function decrement(obj) {
	return --obj.value;
}


function productDetail(id) {

	
	var data = {};
	data['id'] = id;
	
	$.ajax({
		type : "GET",
		url : "productDetail?id="+id,
		timeout : 100000,
		data : JSON.stringify(data),
		contentType: 'application/json; charset=utf-8',
		success : function(data) 
		{
			$(".size_info").empty();
			
			for(var i=0;i< data.length; ++i)
	    	{
				$(".size_info").append("<li>"+data[i].size+" - "+data[i].amountTail+"</li>");
	    		
	    	}
			
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
	
	//setInterval(productDetail,5000);
}
