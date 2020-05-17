function id(id) {
    return document.getElementById(id);
}
// function that validate input and highlight if not filled
function validate(eid) {
    const element = id(eid);
    if (element.value === "") {
        // change border color to red
        element.style.borderColor = "red";
        element.focus();
        console.log("invalid");
        return false;
    }
    else {
        // change border color to normal
        element.style.borderColor = "#dadce0";
        console.log("valid ", element.value);
        return true;
    }
}

function login() {
    if (!validate("email")) {
        return;
    }
    if (!validate("password")) {
        return;
    }

    id("btnLogin").style.color = "#1a73e8";
    id("btnLogin").style.backgroundColor = "#ffffff";

    // get email and password from input tags
    const email = id("email").value;
    const pass = id("password").value;

    // check user in database with http request
    const xhttp = new XMLHttpRequest();
    // send post request to login url
    xhttp.open("POST", "/login", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4) {
            const data = JSON.parse(this.responseText);
            if (this.status===401) {
                alert("wrong email or password")
            } else if (data.status === "done") {
                console.log(data);
            }
        }
    };
    xhttp.send(`username=${email}&password=${pass}&role=admin`);
}
