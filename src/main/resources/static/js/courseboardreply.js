var replyManager = (function(){
		
		var getAll = function(obj, callback){
			console.log("get All....");
			
			$.getJSON('/courseboardreplies/'+obj, callback);
		};
		
		var add = function(obj, callback){
			console.log("add....");
			
			$.ajax({
				type:'post',
				url: '/courseboardreplies/'+obj.coursebno,
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
				url: '/courseboardreplies/'+obj.coursebno,
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
				url: '/courseboardreplies/'+ obj.coursebno+"/" + obj.courserno,
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
