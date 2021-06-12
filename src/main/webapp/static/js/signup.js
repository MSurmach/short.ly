function isMatched() {
    let password = document.getElementById("password").value;
    let confirmPassword = document.getElementById("confirm-password").value;
    let message = document.getElementById("match-message");
    if (password == confirmPassword) {
        message.innerHTML = "Matching";
        message.style.color = "green";
    } else {
        message.innerHTML = "Not matching";
        message.style.color = "red";
    }
}

function signUp() {
    let login = document.getElementById("login-input").value;
    let password = document.getElementById("password-input").value;
    const user = {"login": login, "password": password};
    let payload = JSON.stringify(user);
    let request = createPostRequest("/signUser", payload);
    request.onload = function () {
        if (request.status == 200) {
            //window.location.href = "/";
        } else alert("Wrong response!")
    }
}

function createPostRequest(url, payload) {
    let xhr = new XMLHttpRequest();
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(payload);
    return xhr;
}