const url = location.origin + "/autenticacao";

const login = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form"));

    if ((data.get("email") !== "") && (data.get("password") !== "")) {
        $.ajax({
            "method": "POST",
            "url": url + "/login",
            "data": {
                "email": data.get("email"),
                "password": data.get("password")
            },
            "beforeSend": function() {
                $("#loader img").show();
            },
            "complete": function() {
                $("#loader img").hide();
            },
            "success": function (resp) {
                if (!resp) {
                    location.href = "/";
                } else {
                    alert(resp);
                }
            }
        });
    } else {
        alert("Preencha todos os campos!");
    }
};

