
function stoppedTypingLogin(){
    if(document.getElementById("username").value!='' && document.getElementById("password").value!=''){
        document.getElementById('login_btn').disabled=false
    }
    else{
        document.getElementById('login_btn').disabled=true
    }
}
function allowLocation() {
    if(document.getElementById("location").checked==true)
        document.getElementById('btn').disabled=false
    else
        document.getElementById('reg_btn').disabled=true
}
function myFunction() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}
function getLocation() {
    if(document.getElementById('location').checked==false) {
        alert('Please enable the location');
    }
    else {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition,showError);
        }
    }
}
function showPosition(position) {
    document.getElementById('latitude').value=position.coords.latitude;
    document.getElementById('longitude').value=position.coords.longitude;
}
function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
        alert("User denied the request for Geolocation.");
        break;
    case error.POSITION_UNAVAILABLE:
        alert("Location information is unavailable.");
        break;
    case error.TIMEOUT:
        alert("The request to get user location timed out.");
        break;
    case error.UNKNOWN_ERROR:
        alert("An unknown error occurred.");
        break;
    }
}