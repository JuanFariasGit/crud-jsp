const url = location.origin + "/usuarios";

const tabela = $("#tabela").DataTable({
    "responsive": true,
    "autoWidth": false,
    "order": [0, "desc"],
    "ajax": {
        "method": "POST",
        "url": url,
        "data": {"action":"list"}
    },
    "columns": [
        {"data": "nome"},
        {"data": "email"},
        {"data": "senha"},
        {"data": "telefones"},
        {"data": "excluir"}
    ],
    "lengthMenu": [5, 10, 15],
    "pagingType": "full_numbers",
    "language": {
        "infoFiltered": "(filtrado do total de _MAX_ registros)",
        "infoEmpty": "Mostrando 0 até 0 de 0 registros",
        "zeroRecords": "Nenhum registro correspondente encontrado",
        "loadingRecords": "Carregando...",
        "lengthMenu": "_MENU_ registros por página",
        "info": "Mostrando _START_ até _END_ de _TOTAL_ registros",
        "search": "Pesquisar",
        "paginate": {
            "first": "",
            "last": "",
            "next": "&#10095;",
            "previous": "&#10094;"
        }
    }
});

const closeModalForm = () => {
    $("#modal-form").modal("hide");
    $("#modal-form").find("#form input[name=id]").remove();
    $("#modal-form").find("#form").off("submit", addUser);
    $("#modal-form").find("#form").off("submit", editUser);
    $("#modal-form").find("#telefones").html("");
    $("#modal-form").find("#form").trigger("reset");
};

const openModalFormAdd = () => {
    $("#modal-form").find(".modal-header").html("ADICIONAR USUÁRIO");
    $("#modal-form").find("#btn-submit").html("ADICIONAR");
    $("#modal-form").find("#form").on("submit", addUser);
    $("#modal-form").modal("show");
};

const addUser = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form"));
    const phones = createListObject(document.querySelectorAll(".numero").length);

    if ((data.get("name") !== "") && (data.get("email") !== "") && (data.get("password") !== "")) {

        $.ajax({
            "method": "POST",
            "url": url,
            "data": {
                "action": "add",
                "name": data.get("name"),
                "email": data.get("email"),
                "password": data.get("password"),
                "phones": JSON.stringify(phones)
            },
            "success": function (resp) {
                closeModalForm();
                tabela.ajax.reload();
            }
        });
    } else {
        alert("Preencha todos os campos");
    }
};

const openModalFormEdit = (id) => {
    const name = $("#" + id).find("td:eq(0)").text();
    const email = $("#" + id).find("td:eq(1)").text();
    const dataPhones = Array.from($("#" + id).find("div"));

    let elem = "";
    for (let i = 0; i < dataPhones.length; i++) {
        if (dataPhones[i].innerText.indexOf("DDD") !== -1) {
            elem += "<li onclick=\"delTelefone(this)\"><i class=\"text-danger my-2 far fa-trash-alt fa-lg\" style=\"cursor:pointer\"></i><div class=\"ddd\">" +
                    dataPhones[i].innerText + "</div>";
        } else if (dataPhones[i].innerText.indexOf("Numero") !== -1) {
            elem += "<div class=\"numero\">" + dataPhones[i].innerText + "</div>";
        } else if (dataPhones[i].innerText.indexOf("Tipo") !== -1) {
            elem += "<div class=\"tipo\">" + dataPhones[i].innerText + "</div></li>";
        }
    }

    $('#modal-form').find('.modal-header').html('EDITAR USUÁRIO');
    $('#modal-form').find('form').prepend('<input type="hidden" name="id">');
    $('#modal-form').find('form input[name="id"]').val(id);
    $('#modal-form').find('form input[name="name"]').val(name);
    $('#modal-form').find('form input[name="email"]').val(email);
    $('#modal-form').find('#telefones').html(elem);
    $("#modal-form").find("form").on("submit", editUser);
    $('#modal-form').find("#btn-submit").html("SALVAR");
    $('#modal-form').modal('show');
};

const editUser = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form"));
    const phones = createListObject(document.querySelectorAll(".numero").length);

    $.ajax({
        "method": "POST",
        "url": url,
        "data": {
            "action": "edit",
            "id": data.get("id"),
            "name": data.get("name"),
            "email": data.get("email"),
            "password": data.get("password"),
            "phones": JSON.stringify(phones)
        },
        "success": function (resp) {
            closeModalForm();
            tabela.ajax.reload();
        }
    });
};

const openModal = (id, usuario) => {
    $("#modal").modal("show");
    $("#modal").find(".modal-header")
            .html(`<h5>Deseja realmenete deletar o usuario <strong>"${usuario}"</strong> ?</h5>`);
    $("#modal").find(".modal-footer")
            .html(`<button class="btn btn-outline-secondary" onclick="closeModal()">Não</button>
                    <button class="btn btn-primary" onclick="delUser('${id}')">Sim</button>`);
};

const closeModal = () => {
    $('#modal').modal('hide');
    $('#modal').find('.modal-header').html('');
    $('#modal').find('.modal-footer').html('');
};

const delUser = (id) => {
    $.ajax({
        "method": "POST",
        "url": url,
        "data": {
            "action": "del",
            "id": id
        },
        success: function () {
            tabela.row("#" + id).remove().draw(false);
            closeModal();
        }
    });
};

const logout = () => {
    $.ajax({
        "method": "POST",
        "url": "/autenticacao",
        "data": {"action": "logout"},
        success: function () {
            location.replace(location.origin + "/login.jsp");
        }
    });
};
