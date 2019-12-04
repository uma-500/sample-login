/**
 * 
 */

$(document).ready(function() {
		console.log("document ready");
		/* $("#loginForm").submit(function(event) {
			event.preventDefault();
			alert("button clicked");
		}); */
		$("#loginForm").on('submit', function(e) {
			var name = $("#username").val();
			var password = $("#uPassword").val();
			var pwgRegex = /^([a-zA-Z0-9_-]){5,}$/;
			/*if(pwgRegex.test(password) == false)
			{
			    return false;
			}*/
			
			var isValidForm=false;
			if (name == "") {
				$("#validUserName").text("user name should not be null");
				// isValidForm=false;
			}else{
				$("#validUserName").text("");
				isValidForm=true;
			}
			if (pwgRegex.test(password) == false) {
				console.log("pwdRegex satisfied");
				$("#passowrdMsg").text("Password should have atleast 5 characters");
				isValidForm=false;
			}else{
				$("#passowrdMsg").text("");
				isValidForm=true;
			}
			if(!isValidForm){
				      e.preventDefault();
				      return false;
			}
		
		});
		$("#username").blur(function(e) {
			console.log(e.target);
			if (!e.target.value)
				$("#validUserName").text("user name should not be null");
			else
				$("#validUserName").text("");
		})
	});






	/*$(document).ready(function() {
      $("#loginForm").submit(function(event){
    	  event.preventDefault();
    	  alert("button clicked");
      });
      $("#submitButton").click(function(e){
    	  e.preventDefault();
    	  debugger;
    	  console.log("Submit button clicked");
      })
   });*/
	
	
	
	
	/*$(document).ready(function() {
		   $("#loginForm).validate({
		      "rules": {
		         "userName": 'required',
		         "uPassword": {
		            "required": true,
		            "email": true,//add an email rule that will ensure the value entered is valid email id.
		            "maxlength": 255,
		         },
		      }
		   });
		});
	submitHandler: function(form) {
		   form.submit();
		}*/
	
	