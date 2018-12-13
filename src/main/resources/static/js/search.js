var searchService = (function(){
	
	function autoComplete(callback,error){
		console.log("autoComplete...........");
		
		$.ajax({
            type: 'post',
            url: "/autocomplete",
            dataType: "json",
            success: function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	};
	
	function searchPoint(keyword,callback,error){
		console.log("searchPoint...............");
		
		$.ajax({
            type: 'get',
            url: "/searchPoint/" + keyword,
            success: function(result,status,xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr,status,er){
				if(error){
					error(er);
				}
			}
		});
	};
	
	return {
		autoComplete : autoComplete,
		searchPoint : searchPoint
	}
	
})();