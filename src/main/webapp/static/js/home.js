function generateShortLink() {
    let originalLink = document.getElementById("original-link-input").value;
    let xhr = new XMLHttpRequest();
    let url = "/generate";
    xhr.open("post", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let data = JSON.stringify({original: originalLink});
    xhr.send(data);
    document.getElementById("original-link-input").value = "";
    xhr.onload = function () {
        if (xhr.status !== 200) {
            console.log("Wrong response")
        } else {
            let serverResponseData = JSON.parse(xhr.responseText);
            let shortLink = serverResponseData.link;
            let shortLinkResult = document.getElementById("short-link-result");
            let a = document.createElement("a");
            a.href = shortLink;
            a.innerHTML = a.href;
            shortLinkResult.innerHTML = '';
            shortLinkResult.appendChild(a);
            document.getElementById("short-link-place").hidden = false;
        }
    }
}

function reloadRecords(page, element) {
    location.href = `/profile/links?page=${page}&records=${element.value}`;
}

function reloadPage() {
    window.location.reload();
}

function deleteLink(shortname) {
    let xhr = new XMLHttpRequest();
    let url = "/delete/" + shortname;
    xhr.open("delete", url, true);
    xhr.send(null);
    reloadPage();
}

function copyToClipboard(link) {
    let a = document.createElement("a");
    a.href = link;
    navigator.clipboard.writeText(a.href).then(function () {
        alert("copy was completed: " + a.href);
    }, function () {
        alert("copy was not completed")
    });
}

let editableInput;
let renamePanel;
let previousShortname;

function editLinkName(shortname) {
    activateRenamePanel(false);
    let inputs = document.getElementsByClassName("rename-input");
    for (let input of inputs) {
        if (input.value == shortname) editableInput = input;
    }
    renamePanel = editableInput.parentElement;
    previousShortname = editableInput.value;
    editableInput.autofocus;
    activateRenamePanel(true);
}

function submitRenaming() {
    let newShortname = editableInput.value;
    activateRenamePanel(false);
    let xhr = new XMLHttpRequest();
    let url = "/rename"
    xhr.open("put", url, true);
    xhr.setRequestHeader("Content-Type", "application/json");
    let data = JSON.stringify({"old_shortname": previousShortname, "shortname": newShortname});
    xhr.send(data);
    xhr.onload = function () {
        if (xhr.status !== 200) {
            alert("Something Wrong")
        } else {
            activateRenamePanel(false);
            reloadPage();
        }
    }
}


function rejectRename() {
    editableInput.value = previousShortname;
    activateRenamePanel(false);
}

function activateRenamePanel(trigger) {
    if (editableInput != null) {
        switch (trigger) {
            case true:
                renamePanel.hidden = false;
                break;
            case false:
                renamePanel.hidden = true;
                break;
        }
    }
}
