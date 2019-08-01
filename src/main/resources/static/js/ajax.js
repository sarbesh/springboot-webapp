var singupurl = "http://localhost:8080/api/register";

$('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

var xml = new XMLHttpRequest();

function signUp(){
   xhr.open("POST", singupurl);
   xhr.setRequestHeader("Content-Type","application/json");
   var input = document.getElementById("register-form").getElementByTagname("input");
   var employee = {
   			password:input[6].value;
   			passwordConfirm:input[7].value;
   		}
   var employeeInfo = {
   			firstName:input[0].value;
	   		lastName:input[1].value;
   			email:input[5].value;
   			gender:input[2].value;
   			designation:input[3].value;
   			experience:input[4].value;
   		}
   var obj = {
   		"employee": employee;
   		"employeeInfo": employeeInfo;
	};
	xhr.send(json.stringify(obj));
	console.log(json.stringify(obj))
	return false;
}

//xmlhttp.onreadystatechange = function()
//{
//    if (this.readyState == 4 && this.status == 200)
//    {
//        //Use parse() method to convert JSON string to JSON object
//        var responseJsonObj = JSON.parse(this.responseText);
// 
//        ('.greeting-id').append(data.id);
//        ('.greeting-content').append(data.content);
//        console.log( responseJsonObj.id );
//        console.log( responseJsonObj.context );
//    }
//};
// 
//xmlhttp.open("GET", "http://rest-service.guides.spring.io/greeting", true);
// 
//xmlhttp.send();