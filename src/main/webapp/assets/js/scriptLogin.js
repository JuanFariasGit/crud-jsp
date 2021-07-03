const url = location.origin + "/Login";

const login = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form"));

    if ((data.get("email") !== "") && (data.get("password") !== "")) {
        $.ajax({
            "method": "POST",
            "url": url,
            "data": {
                "email": data.get("email"),
                "password": data.get("password")
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

