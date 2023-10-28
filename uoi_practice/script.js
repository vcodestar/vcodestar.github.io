
(function () {
    emailjs.init("MF2WDnfZ_io6tdiYG");
})();

function sendmail() {

    let fullName = document.getElementById("name").value;
    let userEmail = document.getElementById("email").value;
    let userMessage = document.getElementById("message").value;
    
        var contactParams = {
            
            from_name: fullName,
            to_email: userEmail,
            message: userMessage
            
        };

        console.log("Sending email to :", contactParams.to_email);
        emailjs.send('service_vc4real', 'template_bea9gba', contactParams).then(function (res) {})
}