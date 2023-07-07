
function navigate(event) {
    console.log(loginButton);
    if (event.target.id == "REGISTER") {
        document.getElementById("REGISTER").addEventListener("click", (event) => {
            event.preventDefault()

            const username = document.getElementById("reg-username").value;
            const password = document.getElementById("reg-password").value;

            if (validateLoginForm(username, password)) {
                window.location.href = "http://localhost:4200"
            } else {
                alert("Please enter valid login data!")
            }

        })
    } else if (event.target.id == "goBack") {
        document.getElementById("goBack").addEventListener("click", (event) => {
            event.preventDefault();
            console.log("here");
            window.location.href = "../CoreHTML/main_page.html"
        })
    }

}

function validateLoginForm(username, password) {
    let usernameValidation = false;
    let passwordValidation = false;
  
    const usernamePattern = /^[a-zA-Z0-9_-]{3,16}$/;
    const passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{2,}$/;
  
    if (username) {
      usernameValidation = usernamePattern.test(username);
    }
  
    if (password) {
      passwordValidation = passwordPattern.test(password);
    }
  
    return usernameValidation && passwordValidation;
  }
  





