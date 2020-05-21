function stoppedTyping(){
    if(document.getElementById("email").value!='' && document.getElementById("password").value!='' && document.getElementById("firstName").value!='' && document.getElementById("confirmPassword").value!='' && document.getElementById("phoneNo").value!='' && document.getElementById("lastName").value!='' && document.getElementById("doorNo").value!='' && document.getElementById("city").value!='' && document.getElementById("state").value!='' && document.getElementById("country").value!='' && document.getElementById("pincode").value!='' && document.getElementById("streetNo").value!=''&&document.getElementById('location').checked==true){
        document.getElementById('reg_btn').disabled=false
    }
    else{
        document.getElementById('reg_btn').disabled=true
    }
}
function myFunction() {
  var x = document.getElementById("password");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}
function myFunction1() {
  var x = document.getElementById("confirmPassword");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}