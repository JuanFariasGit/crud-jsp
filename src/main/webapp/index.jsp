<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate>
    <jsp:attribute name="head">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
        <link rel="stylesheet"
              href="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.24/r-2.2.7/datatables.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </jsp:attribute>

    <jsp:attribute name="script">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://cdn.datatables.net/v/bs4-4.1.1/dt-1.10.24/r-2.2.7/datatables.min.js"></script>
        <script src="assets/js/scriptIndex.js"></script>
        <script src="assets/js/scriptTelefone.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="d-flex justify-content-between align-items-center">
            <button type="button" class="btn btn-primary mb-2" onclick="openModalFormAdd()">
                <i class="fas fa-plus-circle fa-lg"></i>
            </button>
            <div>
                <span id="logout" onclick="logout()">Sair</span>
            </div>
        </div>
        <table id="tabela"
               class="table table-striped table-bordered text-center">
            <thead class="thead-dark">
                <tr>
                    <th class="all">Nome</th>
                    <th class="min-tablet">E-mail</th>
                    <th class="none">Senha</th>
                    <th class="none">Telefones</th>
                    <th class="all"></th>
                </tr>
            </thead>
        </table>

        <%@ include file="partials/modalIndex.jsp"%>
        <%@ include file="partials/modalTelefone.jsp"%>
    </jsp:body>

</t:pageTemplate>