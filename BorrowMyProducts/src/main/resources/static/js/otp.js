function stoppedTyping(){
    if(document.getElementById("otp").value!=''){
        document.getElementById('btn').disabled=false
    }
    else{
        document.getElementById('btn').disabled=true
    }
}
let timerOn = true;
function timer(remaining) {
    var m = Math.floor(remaining / 60);
    var s = remaining % 60;
    m = m < 10 ? '0' + m : m;
    s = s < 10 ? '0' + s : s;
    document.getElementById('timer').innerHTML = m + ':' + s;
    remaining -= 1;
    if(remaining >= 0 && timerOn) {
        setTimeout(function() {
            timer(remaining);
        }, 1000);
        return;
    }
    if(!timerOn) {
        return;
    }
    alert('Timeout for otp');
}
timer(120);