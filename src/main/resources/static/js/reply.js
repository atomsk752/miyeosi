var replyManager = (function(){
		
		var getAll = function(obj, callback){
			console.log("get All....");
			
			$.getJSON('/replies/'+obj, callback);
		};
		
		var add = function(obj, callback){
			console.log("add....");
			
			$.ajax({
				type:'post',
				url: '/replies/'+obj.pno,
				data:JSON.stringify(obj),
				dataType:'json',
				beforeSend: function(xhr){
					xhr.setRequestHeader(obj.header, obj.token);
				},
				contentType: "application/json",
				success:callback
			});
		};
		
		var update = function(obj, callback){
			console.log("update....");
			
			$.ajax({
				type:'put',
				url: '/replies/'+obj.pno,
				data:JSON.stringify(obj),
				dataType:'json',
				beforeSend: function(xhr){
					xhr.setRequestHeader(obj.header, obj.token);
				},
				contentType: "application/json",
				success:callback
			});  
		};
		
		var remove = function(obj, callback){
			console.log("remove....");
			
			$.ajax({
				type:'delete',
				url: '/replies/'+ obj.pno+"/" + obj.rno,
				dataType: 'json',
				beforeSend: function(xhr){
					xhr.setRequestHeader(obj.header, obj.token);
				},
				contentType: "application/json",
				success:callback
			});
		};
		
		return {
			getAll:getAll,
			add:add,
			update:update,
			remove:remove
		}
	})();
