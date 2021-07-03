<div id="modal-form" class="modal fade" role="dialog"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"></div>
            <div class="modal-body">
                <form id="form" method="post">
                    <div class="form-group">
                        <label for="nome">Nome:</label> <input class="form-control" type="text" id="name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="nome">E-mail:</label> <input class="form-control" type="email" id="email" name="email">
                    </div>
                    <div class="form-group">
                        <label for="nome">Senha:</label> <input class="form-control" type="password" id="password" name="password">
                    </div>
                    <label>Telefones:</label>
                    <button id="btn-telelefone" type="button" class="btn btn-primary mb-2"
                            onclick="openModalFormTel()">
                        <i class="fas fa-plus-circle fa-lg"></i>
                    </button>
                    <ul id="telefones"></ul>
                    <button id="btn-submit" type="submit" class="btn btn-sm btn-primary"></button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-danger"
                        onclick="closeModalForm()">CANCELAR</button>
            </div>
        </div>
    </div>
</div>

<div id="modal" class="modal fade" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header"></div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>