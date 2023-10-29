
(function () {
    emailjs.init("MF2WDnfZ_io6tdiYG");
})();

function sendmail() {

    let fullName = document.getElementById("name").value;
    let userEmail = document.getElementById("email").value;
    let userMessage = document.getElementById("message").value;
    let btn = document.getElementById("btn");
    
        var contactParams = {
            
            from_name: fullName,
            to_email: userEmail,
            message: userMessage
            
        };

        console.log("Sending email to :", contactParams.to_email);
        emailjs.send('service_vc4real', 'template_bea9gba', contactParams)
        .then(function (res) {
            // Clear input fields after sending the email
            document.getElementById("name").value = "";
            document.getElementById("email").value = "";
            document.getElementById("message").value = "";

            btn.style.display = "none";
            btn.disabled = true;

            // Show the success message
            document.getElementById("successMessage").style.display = "block";
        });
}