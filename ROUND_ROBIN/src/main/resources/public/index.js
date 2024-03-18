document.getElementById("myForm").addEventListener("submit", function(event) {
        event.preventDefault();
        let nameVar = document.getElementById("message").value;
        const xhttp = new XMLHttpRequest();
        xhttp.onload = function() {
            let respMsg = this.responseText;
            let card = '<div class="card">' +
                '<h2>Mensaje</h2>' +
                '<p>' + respMsg + '</p>' +
                '</div>';
            document.getElementById("getrespmsg").innerHTML = card;
        }
        xhttp.open("GET", "/log?value=" + encodeURIComponent(nameVar), true);
        xhttp.send();
    });