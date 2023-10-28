
(function () {
    emailjs.init("MF2WDnfZ_io6tdiYG");
})();

function sendmail() {

    let fullName = document.getElementById("name").value;
    let userEmail = document.getElementById("email").value;
    let userMessage = document.getElementById("message").value;
    
        var contactParams = {
            
            from_name: fullName,
            from_email: userEmail,
            message: userMessage
            
        };

        emailjs.send('service_vc4real', 'template_bea9gba', contactParams).then(function (res) {})
}