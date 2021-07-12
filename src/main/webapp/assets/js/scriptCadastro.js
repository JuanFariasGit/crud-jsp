const url = location.origin + "/usuarios";

const addUser = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form"));
    const phones = createListObject(document.querySelectorAll(".numero").length);

    if ((data.get("name") !== "") && (data.get("email") !== "") && (data.get("password") !== "")) {
        $.ajax({
            "method": "POST",
            "url": url + "/add",
            "data": {
                "name": data.get("name"),
                "email": data.get("email"),
                "password": data.get("password"),
                "phones": JSON.stringify(phones)
            },
            "beforeSend": function() {
                $("#loader img").show();
            },
            "complete": function() {
                $("#loader img").hide();
            },
            "success": function (resp) {
                alert(resp);
            }
        });
    } else {
        alert("Preencha todos os campos!");
    }
};