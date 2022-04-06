'use strict'

multipleUploadForm.addEventListener('submit', function (event) {

    const inputLink = document.getElementById('inputLink');
    const shortLink = document.getElementById('shortLink');

    event.preventDefault();

    const link = inputLink.value;
    console.log(link);
    let formData = new FormData();
    formData.append("Q", link);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/full");
    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log(xhr.responseText);
            const response = JSON.parse(xhr.responseText);
            console.log(response.link);
            shortLink.value = response.link;
        } else {
            shortLink.value = "ERROR. Try again later";
        }
    }
    xhr.send(formData);
    console.log("send ");
}, true);

function myFunction() {
    const copyText = document.getElementById("shortLink");
    copyText.select();
    document.execCommand("copy");
}