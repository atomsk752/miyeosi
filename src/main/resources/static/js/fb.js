// Initialize Firebase
var config = {
    apiKey: "AIzaSyAJV5H9lCZjjecpQzLq_xJijFw5BZUZzz4",
    authDomain: "exex1-a7068.firebaseapp.com",
    databaseURL: "https://exex1-a7068.firebaseio.com",
    projectId: "exex1-a7068",
    storageBucket: "exex1-a7068.appspot.com",
    messagingSenderId: "961860192261"
};
firebase.initializeApp(config);

var courseService = (function(){
	
    // 코스 불러오기
    function readCourse(key, callback){
    	
    	firebase.database().ref().child('courses/'+key).once("value").then(function(data){
    		var result = data.val();
    		callback(result);
    	});
    	
    }
	
	return {
		readCourse : readCourse
	}
	
})();