
function increment(obj, value) {
	
	alert(obj+'  '+value);
}

function decrement(obj) {
	return --obj.value;
}


var PD = {
		id: 0,
		tailsArr: [],
		
		productDetail: function productDetail() {
				
				var data = {};
				data['id'] = PD.id;
				
				$.ajax({
					type : "GET",
					url : "productDetail?id="+PD.id,
					//timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=utf-8',
					success : function(data) 
					{
						$(".size_info").empty();
						PD.tailsArr = [];
						for(var i=0;i< data.length; ++i) {
							$(".size_info").append("<li>"+data[i].size+" - "+data[i].amountTail+"</li>");
							PD.tailsArr.push(data[i]);
						}
					},
					error : function(e) {
						//alert("ERROR: addToCompare()", e);
						display(e);
					}
				});
	
		}
};
