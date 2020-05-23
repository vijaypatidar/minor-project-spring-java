function id(id) {
    return document.getElementById(id);
}

window.onload = function () {
    const http = new XMLHttpRequest();
    // send post request to login url
    http.open("GET", "/api/book", true);
    http.setRequestHeader("Content-type", "application/json");
    http.onreadystatechange = function () {
        if (this.readyState === 4) {
            if (this.status === 401) {
                alert("wrong email or password")
            } else {
                console.log(this.responseText)
                const data = JSON.parse(this.responseText);
                show(data);
            }
        }
    };
    http.send();
}


function show(data) {
    let con = "";
    data.forEach(element => {
        const bookTitle = element.title;
        const bookAuthors = element.authors;
        const bookISBN = element.isbn;
        const bookQuantity = element.quantity;
        const BookAvailable = element.available;
    
    
        con += `<div class="container" style="display:inline-block;"><p style="font-family:Stardos Stencil,serif">` +
            `${bookTitle}</p><table><tr><td>Book Authors</td><td>${bookAuthors}</td></tr>` +
            `<tr><td>Book ISBN</td><td>${bookISBN}</td></tr><tr><td>Book Quantity</td><td>` +
            `${bookQuantity}</td></tr><tr><td>Book Available</td><td>${BookAvailable}</td></tr></table></div>`
    });

    id("body").innerHTML = con;
}