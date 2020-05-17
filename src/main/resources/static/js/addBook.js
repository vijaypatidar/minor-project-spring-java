let token;

function id(id) {
    return document.getElementById(id);
}

// function that validate input and highlight if not filled
function validate(eid) {
    const element = id(eid);
    if (element.innerText === "") {
        // change border color to red 
        element.style.borderColor = "red";
        element.focus();
        console.log("invalid");
        return false;
    } else {
        // change border color to normal 
        element.style.borderColor = "#90A4AE";
        console.log("valid ", element.value);
        return true;
    }
}

function addBook() {
    if (!validate("bookTitle")) {
        return;
    }
    if (!validate("bookAuthors")) {
        return;
    }
    if (!validate("bookPublication")) {
        return;
    }
    if (!validate("bookISBN")) {
        return;
    }

    const bookTitle = id("bookTitle").innerText.trim();
    const bookAuthors = id("bookAuthors").innerText.trim();
    const bookPublication = id("bookPublication").innerText.trim();
    const bookISBN = id("bookISBN").innerText.trim();


    // check user in database with http request
    const http = new XMLHttpRequest();
    // send post request to login url
    http.open("POST", "/api/book/addBook", true);
    http.setRequestHeader("Content-type", "application/json");
    http.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 401) {
                alert("wrong email or password")
            } else
                handleRes(this.responseText);
        }
    };
    http.send(`title=${bookTitle}&authors=${bookAuthors}&ISBN=${bookISBN}&quantity=0&available=0`);

}

function handleRes(result) {
    console.log(result);
    if (result === "done") {
        alert("book added");
    } else {
        alert("wrong email or password");
    }
}


window.onload = function () {
    const url_string = window.location.href;
    let url = new URL(url_string);
    token = url.searchParams.get("AccessToken");
    console.log(token);
}