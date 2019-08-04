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
});