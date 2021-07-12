<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:pageTemplate>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
    </jsp:attribute>

    <jsp:attribute name="script">
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script src="assets/js/scriptLogin.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="container d-flex justify-content-center align-items-center" style="max-width: 600px;">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card card-secondary">
                            <div class="card-header">
                                <%@ include file="partials/loader.jsp"%>
                                <h3 class="card-title">Login</h3>
                            </div>
                            <form id="form" method="POST">
                                <div class="card-body">
                                    <div class="form-group">
                                        <label for="nome">E-mail:</label> <input class="form-control" type="email" id="email" name="email">
                                    </div>
                                    <div class="form-group">
                                        <label for="nome">Senha:</label> <input class="form-control" type="password" id="password" name="password">
                                    </div>
                                    <button type="submit" class="btn btn-sm btn-primary" onclick="login(event)">ENTRAR</button>
                                </div>
                            </form>
                            <div class="p-3">
                                <a href="cadastro.jsp">Cadastrar uma conta</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>

</t:pageTemplate>