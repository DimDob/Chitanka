let loginButton = document.getElementById('login-button')
let registerButton = document.getElementById("register-button")

let userIcon = document.getElementById('userIcon')
let popupContainer = document.getElementById('popup-container')



let loginBtnClicked = false
let registerBtnClicked = false


function loginClicked () { //checks whether login button in popUp is clicked and triggers login() if true.
  loginBtnClicked = true
  login()
}
function registerClicked () { //checks whether register button in popUp is clicked and triggers register() if true 
  registerBtnClicked = true
  register()
}

function login() { //ternary operator, returns true if login button is clicked and goToLoginPage is triggered.
  loginBtnClicked ? goToLoginPage() : null;
}

function register() { //ternary operator, returns true if register button is clicked and goToRegisterPage is triggered. 
  registerBtnClicked ? gotoRegisterPage() : null;
}

userIcon.addEventListener("click", (event) => { //Toggles the popUp bubble when user icon is clicked.
  event.preventDefault()
  popupContainer.classList.toggle('visible') ? popupContainer.style.display = 'block' : popupContainer.style.display = 'none';
})

loginButton.addEventListener('click', () => {
  window.location.href = "../CoreHTML/login_page.html"
})

registerButton.addEventListener("click", () =>{
  window.location.href = "../CoreHTML/register_page.html"
})