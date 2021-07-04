<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate>

    <jsp:attribute name="head">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
        <link rel="stylesheet">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </jsp:attribute>

    <jsp:attribute name="script">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="assets/js/scriptCadastro.js"></script>
        <script src="assets/js/scriptTelefone.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container d-flex justify-content-center align-items-center"
             style="max-width: 600px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-secondary">
                            <div class="card-header">
                                <div id="mensagem"></div>
                                <h3 class="card-title">Cadastro</h3>
                            </div>
                            <form id="form" method="post">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="name">Nome:</label> 
                                        <input class="form-control" type="text" id="name" name="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">E-mail:</label> 
                                        <input class="form-control" type="email" id="email" name="email">
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Senha:</label> 
                                        <input class="form-control" type="password" id="password" name="password">
                                    </div>
                                    <hr>
                                    <label>Telefones:</label>
                                    <button type="button" class="btn btn-primary mb-2"
                                            onclick="openModalFormTel()">
                                        <i class="fas fa-plus-circle fa-lg"></i>
                                    </button>
                                    <ul id="telefones"></ul>
                                    <hr>
                                    <button type="submit" class="btn btn-sm btn-primary"
                                            onclick="addUser(event)">CADASTRAR</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="partials/modalTelefone.jsp"%>
    </jsp:body>

</t:pageTemplate>
