function initPage() {
    document.getElementById("alert-message").hidden = true;
}

function showAlertMessage(message) {
    document.getElementById("alert-message").hidden = false;
    document.getElementById("match-message").innerHTML = message;
}

function isMatched() {
    let password = document.getElementById("password-input").value;
    let confirmPassword = document.getElementById("confirm-password-input").value;
    if (password == confirmPassword) {
        showAlertMessage("Your password is matching");
    } else {
        showAlertMessage("Your password is not matching");
    }
}

function signUp() {
    let login = document.getElementById("username-input").value;
    let password = document.getElementById("password-input").value;
    const user = {"username": login, "password": password};
    let payload = JSON.stringify(user);
    let request = createPostRequest("/signUser", payload);
    request.onload = function () {
        if (request.status == 200) {
            window.location.href = "/login";
        } else showAlertMessage("Unable to sign, because provided username already exists")
    }
}

function createPostRequest(url, payload) {
    let xhr = new XMLHttpRequest();
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(payload);
    return xhr;
}