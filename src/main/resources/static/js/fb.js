// Initialize Firebase
var config = {
  apiKey: "AIzaSyCgzLno3i5zPxKzaE5-9qXrt_FZWCwK8tE",
  authDomain: "travel-simulator.firebaseapp.com",
  databaseURL: "https://travel-simulator.firebaseio.com",
  projectId: "travel-simulator",
  storageBucket: "travel-simulator.appspot.com",
  messagingSenderId: "105538800439"
};
firebase.initializeApp(config);

var courseService = (function(){
	
    // 코스 불러오기
    function readCourse(key, title, callback){
    	
    	firebase.database().ref().child('courses/'+key +'/'+ title).once("value").then(function(data){
    		var result = data.val();
    		callback(result);
    	});
    	
    }
	
	return {
		readCourse : readCourse
	}
	
})();