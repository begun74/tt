
function increment(obj, value) {
	
	alert(obj+'  '+value);
}

function decrement(obj) {
	return --obj.value;
}


var Product = {
		id: 0,
		localItemName: '',
		
		getDetail: function () {
				
				var data = {};
				data['id'] = Product.id;
				
				$.ajax({
					type : "GET",
					url : "productDetail?id="+Product.id,
					//timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=utf-8',
					success : function(data) 
					{
						$(".size_info").empty();
						Product.tailsArr = [];
						for(var i=0;i< data.length; ++i) {
							$(".size_info").append("<li>"+data[i].size+" - "+data[i].amountTail+" "+Product.localItemName+"</li> ");
							//Product.tailsArr.push(data[i]);
						}
					},
					error : function(e) {
						
						display(e);
					}
				});
	
		},

		toOrder: function(size, amount) {

				var data = {};
				data['id'] = Product.id;

				$.ajax({
					type : "GET",
					url : "toOrder?id="+Product.id+"&size="+size+"&amount="+amount,
					//timeout : 10000,
					data : JSON.stringify(data),
					contentType: 'application/json; charset=utf-8',
					success : function(data) 
					{
						Product.getDetail();
						/*$('.add_product_alert').show();*/
					},
					error : function(e) {
						
						display(e);
					}
				});
		}
};



function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   //alert(charCode);
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}

function isNumberKeyDouble(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode;
   //alert(charCode);
   if (charCode > 31 && (charCode < 48 || charCode > 57) && charCode != 46 )
      return false;

   return true;
}

function isNumberValue(value)
{
	var re = /[0-9999]/g;
	
	if (!re.test(value) || value==0)
	{	
	      return false;
	}

   return true;
}