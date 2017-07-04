/*jslint browser: true*/
// test
//Variables
var formCenter = document.getElementById("form-center");
var formError = document.getElementById("error");

var inputFields = $("#form-center-input")
for (inputField in inputFields) {
	if(inputFields.hasOwnProperty(inputField)){
	   console.info("input field: ",inputField);
	   inputField.addEventListener("focus", clearText, false);
	   inputField.addEventListener("blur", resetText, false);
    }
}

//var firstName = formCenter.firstName;
//var lastName = formCenter.lastName;
//var username = formCenter.username;
//var password = formCenter.password;
//
//firstName.defaultText = firstName.value;
//lastName.defaultText = lastName.value;
//username.defaultText = username.value;
//password.defaultText = password.value;

//var createAccountFormFieldContainerArray = document.getElementsByClassName("field-container");
////var createAccountFormFieldContainerFirstName = createAccountFormFieldContainerArray[0];
//var createAccountFormFieldContainerLastName = createAccountFormFieldContainerArray[1];
//var createAccountFormFieldContainerUsername = createAccountFormFieldContainerArray[2];
//var createAccountFormFieldContainerPassword = createAccountFormFieldContainerArray[3];
//
////Event Listeners
//
//firstName.addEventListener("focus", clearText, false);
//lastName.addEventListener("focus", clearText, false);
//username.addEventListener("focus", clearText, false);
//password.addEventListener("focus", clearText, false);
//
//firstName.addEventListener("blur", resetText, false);
//lastName.addEventListener("blur", resetText, false);
//username.addEventListener("blur", resetText, false);
//password.addEventListener("blur", resetText, false);
//
//firstName.addEventListener("focus", function(){
//	unhide(createAccountFormFieldContainerLastName);
//}, false);
//lastName.addEventListener("focus", function(){
//	unhide(createAccountFormFieldContainerUsername);
//}, false);
//username.addEventListener("focus", function(){
//	unhide(createAccountFormFieldContainerPassword);
//}, false);


//Functions
function clearText(){
	formError.innerHTML = "";
	this.style.borderColor = ""; //Reset the red border if set
	if(this.value == this.defaultText){
		//This MUST use .value not .getAttribute
		this.value = "";
	}
}
function resetText(){
	formError.innerHTML = "";
	if(this.value === ""){
		this.value = this.defaultText;
	}
}

formCenter.onsubmit = function(){
	var allFieldsComplete = true;
	if(firstName.value == firstName.defaultText || firstName.value === ""){
		firstName.style.borderColor = "red";
		allFieldsComplete = false;
	}if(lastName.value == lastName.defaultText || lastName.value === ""){
		lastName.style.borderColor = "red";
		allFieldsComplete = false;
	}if(username.value == username.defaultText || username.value === ""){
		username.style.borderColor = "red";
		allFieldsComplete = false;
	}if(password.value == password.defaultText || password.value === ""){
		password.style.borderColor = "red";
		allFieldsComplete = false;
	}
	
	if(allFieldsComplete === true){
		return true;
	}else if(allFieldsComplete === false){
		formError.innerHTML = "*Please complete all fields!";
		return false;
	}
};




////Hiding Fields
//createAccountFormFieldContainerLastName.style.display = "none";
//createAccountFormFieldContainerUsername.style.display = "none";
//createAccountFormFieldContainerPassword.style.display = "none";


function unhide(field){
	field.style.display = "";
}