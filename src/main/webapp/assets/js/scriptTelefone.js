let list = [];

const addTelefone = (e) => {
    e.preventDefault();
    const data = new FormData(document.getElementById("form-telefone"));

    elem = `<li onclick="delTelefone(this)"><i class="text-danger my-2 far fa-trash-alt fa-lg" style="cursor:pointer">
            </i><div class="ddd">ddd: ${data.get("ddd")}</div class="numero"><div class="numero">Numero:
            ${data.get("numero")}</div><div class="tipo">Tipo: ${data.get("tipo")}</div></li>`;
    $("#telefones").append(elem);
    closeModalFormTel();
};

const delTelefone = (elem) => {
    elem.remove();
};

const createList = (classe) => {
    const list = Array.from(document.querySelectorAll(classe))
            .map(item => item.innerHTML.split(":")[1].trim());
    return list;
};

const createObject = (ddd, numero, tipo) => {
    return {"ddd": ddd, "numero": numero, "tipo": tipo};
};

const createListObject = (lengthList) => {
    list = [];
    const listDDD = createList(".ddd");
    const listNumero = createList(".numero");
    const listTipo = createList(".tipo");

    for (let i = 0; i < lengthList; i++) {
        list.push(createObject(listDDD[i], listNumero[i], listTipo[i]));
    }
    return list;
};

const openModalFormTel = () => {
    $("#modal-form-tel").modal("show");
};

const closeModalFormTel = () => {
    $("#modal-form-tel").modal("hide");
    $("#modal-form-tel").find("#form-telefone").trigger("reset");
};