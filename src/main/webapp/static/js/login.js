// function initPage() {
//     document.getElementById("alert-message").hidden = true;
// }
//
// function showAlertMessage(message) {
//     document.getElementById("alert-message").hidden = false;
//     document.getElementById("match-message").innerHTML = message;
// }

function login() {
    let login = document.getElementById("username-input").value;
    let password = document.getElementById("password-input").value;
    const user = {"username": login, "password": password};
    let payload = JSON.stringify(user);
    let request = createPostRequest("/loginUser", payload);
    // request.onload = function () {
    //     if (request.status == 200) {
    //         window.location.href = "/";
    //     } else showAlertMessage("Unable to login, because provided user doesn't exists")
    // }
}

function createPostRequest(url, payload) {
    let xhr = new XMLHttpRequest();
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(payload);
    return xhr;
}