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
    if (!validate("bookISBN")) {
        return;
    }

    const bookTitle = id("bookTitle").innerText.trim();
    const bookAuthors = id("bookAuthors").innerText.trim();
    const bookISBN = id("bookISBN").innerText.trim();


    // check user in database with http request
    const http = new XMLHttpRequest();
    // send post request to login url
    http.open("POST", "api/book", true);
    http.setRequestHeader("Content-type", "application/json");
    http.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            handleRes(this.responseText);
        }else if (this.readyState === 4 &&this.status===403){
            alert("Access denied")
        }
    };
    let data = {};
    data.title = bookTitle;
    data.available = 0;
    data.quality = 0;
    data.isbn = bookISBN;
    data.reviews = [];
    data.authors = bookAuthors;
    http.send(JSON.stringify(data));

}

function handleRes(result) {
    console.log(result);
    alert("book added");
}
