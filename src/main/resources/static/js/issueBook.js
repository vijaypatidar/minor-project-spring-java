var token;
function id(id) {
    return document.getElementById(id);
}

// function that validate input and highlight if not filled
function validate(eid) {
    var element = id(eid);
    if (element.innerText == "") {
        // change border color to red 
        element.style.borderColor = "red";
        element.focus();
        console.log("invalid");
        return false;
    }
    else {
        // change border color to normal 
        element.style.borderColor = "#90A4AE";
        console.log("valid ", element.value);
        return true;
    }
}

function issueBook() {
    if (!validate("userID")) {
        return;
    }
    if (!validate("bookID")) {
        return;
    }

    const userID = id("userID").innerText.trim();
    const bookID = id("bookID").innerText.trim();
    
    
    // check user in database with http request
    var xhttp = new XMLHttpRequest();
    // send post request to login url
    xhttp.open("POST", "issueBook", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            if(this.status==401){
                alert("wrong email or password")
            }else
               handleRes(this.responseText);
        }
    };
    xhttp.send(`userID=${userID}&token=${token}&bookID=${bookID}`);

}

function submitBook() {
    if (!validate("userID")) {
        return;
    }
    if (!validate("bookID")) {
        return;
    }

    const userID = id("userID").innerText.trim();
    const bookID = id("bookID").innerText.trim();
    
    
    // check user in database with http request
    var xhttp = new XMLHttpRequest();
    // send post request to login url
    xhttp.open("POST", "submitBook", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4) {
            if(this.status==401){
                alert("wrong email or password")
            }else
               handleRes(this.responseText);
        }
    };
    xhttp.send(`userID=${userID}&token=${token}&bookID=${bookID}`);

}

function handleRes(result) {
    if (result == "done") {
        alert("success");
    } else {
        alert(result);
    }
}


window.onload = function () {
    const url_string = window.location.href;
    let url = new URL(url_string);
    token = url.searchParams.get("AccessToken");
    console.log(token);
}