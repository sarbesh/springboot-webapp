$(document).ready(function() {
	alert("loaded");
	
	var xhr = new XMLHttpRequest();
	var singupurl = "http://localhost:8080/api/register";

	$("#login-show").click(function() {
		$("#signupDiv").toggle();
		$("#loginDiv").toggle();
		alert("login-show");
	});

	$("#create-show").click(function() {
		$("#loginDiv").toggle();
		$("#signupDiv").toggle();
		alert("signup-show");
	});
});