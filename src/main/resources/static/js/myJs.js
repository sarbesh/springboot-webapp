$(document).ready(function() {
	// alert("loaded");
	
	var xhr = new XMLHttpRequest();
	var singupUrl = "http://localhost:8080/api/register";
	var loginUrl = "http://localhost:8080/api/login";
	var searchUrl = "http://localhost:8080/api/search";
	var detailsUrl = "http://localhost:8080/api/employee/{id}";

	$("#login-show").click(function() {
		$("#signupDiv").toggle();
		$("#loginDiv").toggle();
		// alert("login-show");
	});

	$("#create-show").click(function() {
		$("#loginDiv").toggle();
		$("#signupDiv").toggle();
		// alert("signup-show");
	});
	
	$("#register").click(function() {
		var fName = $("#firstName").val();
		var lName = $("#lastName").val();
		var em = $("#email").val();
		var gnd = $("#gender").val();
		var desg = $("#designation").val();
		var exp = $("#experience").val();
		var pass = $("#password").val();
		var cPass = $("#cPassword").val();
		var employee = {
				password: pass,
				passwordConfirm: cPass
   		};
		var employeeInfo = {
				firstName:fName,
	   			lastName:lName,
	   			email:em,
   				gender:gnd,
   				designation:desg,
   				experience:exp
   		};
		var obj = {
				"employee": employee,
				"employeeInfo": employeeInfo
		};
		var eData = JSON.stringify(obj);
		console.log(eData);
		alert(eData);
		$.ajax({
            url: singupUrl,
            type:"POST",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: eData,
            cache: false,
            timeout: 500000,
            success: function(data) {
            	alert(data);
            	
            }
        });
		$("#loginDiv").toggle();
		$("#signupDiv").toggle();
	});
});