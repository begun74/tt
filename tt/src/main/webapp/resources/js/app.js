
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
		dataType : 'json',
		success : function() 
		{
			alert(data);
		},
		error : function(e) {
			//alert("ERROR: addToCompare()", e);
			display(e);
		}
	});
}
