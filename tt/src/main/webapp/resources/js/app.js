
function increment(obj, value) {
	
	alert(obj+'  '+value);
}

function decrement(obj) {
	return --obj.value;
}


var Product = {
		id: 0,
		result: 0,

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

				var arrData = {};
				arrData['id'] = Product.id;
				
				
				var jqXHR = $.ajax({
					type : "GET",
					url : "toOrder?id="+Product.id+"&size="+size+"&amount="+amount,
					//timeout : 10000,
					async: false,
					dataType: 'json',
					data : JSON.stringify(arrData),
					contentType: 'application/json; charset=utf-8',
					success : function(data) {
						
					},
					error : function(e) {
						display(e);
					}
				});
				
				return jqXHR.responseText;
		},

		delOrder: function(id) {

			var data = {};
			

			$.ajax({
				type : "GET",
				url : "delOrder?id="+id,
				//timeout : 10000,
				data : JSON.stringify(data),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					
					/*$('.add_product_alert').show();*/
				},
				error : function(e) {
					
					display(e);
				}
			});
		}

};


var Order = {
		id: 0,
		
		getOrderItems: function(id,row) {
			var arrData = {};
			arrData['id'] = id;
			
			$.ajax({
				type : "GET",
				url : "getOrderItems?id="+id,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					Order.displayOrderItems(data,row);
				},
				error : function(e) {
					
					display(e);
				}
			});
		},
		
		displayOrderItems: function(arrData, row) {
			var numCols = 1;
			
			
			$('.tableDisplayOrderItems').empty();
			
			$.each(arrData, function(i) {
				  if(!(i%numCols)) tRow = $('<tr>');
				  
				  var destr_date = arrData[i].destruction_date != null?new Date(arrData[i].destruction_date):"&nbsp;&nbsp;В работе";
				  

				  tCell = $('<td>').html(arrData[i].code);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].name);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].model);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].article);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].size);
				  tRow.append(tCell);
				  tCell = $('<td>').html(arrData[i].amount);
				  tRow.append(tCell);
				  tCell = $('<td>').html('<input type="checkbox" class="statusOrderItem">'+destr_date+'</input>');
				  tRow.append(tCell);				  

				  $('.tableDisplayOrderItems').append(tRow);
			});
			
			//alert('row - ' +row);
			
		},
		
		closeOrder: function(id) {
			var arrData = {};
			arrData['id'] = id;
			
			$.ajax({
				type : "GET",
				url : "closeOrder?id="+id,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					//alert('Заказ №' +data['id'] + ' выполнен!')
					window.location.reload();
				},
				error : function(e) {
					
					display(e);
				}
			});
		},
		
		setReadyOrderItem: function(id_orderItem) {
			var arrData = {};
			arrData['id'] = id;
			
			$.ajax({
				type : "GET",
				url : "closeOrder?id="+id,
				//timeout : 10000,
				data : JSON.stringify(arrData),
				contentType: 'application/json; charset=utf-8',
				success : function(data) 
				{
					//alert('Заказ №' +data['id'] + ' выполнен!')
					window.location.reload();
				},
				error : function(e) {
					
					display(e);
				}
			});
		}
}


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