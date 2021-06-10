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
            alert("Wrong response")
        } else {
            let serverResponseData = JSON.parse(xhr.responseText);
            let shortLink = serverResponseData.link;
            let shortLinkResult = document.getElementById("short-link-result");
            shortLinkResult.innerHTML = `Your short link is here: <a href='${shortLink}'>${shortLink}</a>`;
        }
    }
}