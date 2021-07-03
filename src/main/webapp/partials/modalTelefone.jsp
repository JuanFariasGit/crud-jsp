<div id="modal-form-tel" class="modal fade" role="dialog"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">ADICIONAR TELEFONE</div>
            <div class="modal-body">
                <form id="form-telefone">
                    <div class="form-group">
                        <label for="ddd">DDD:</label> <input class="form-control" type="text" id="ddd" name="ddd">
                    </div>
                    <div class="form-group">
                        <label for="numero">Número:</label> <input class="form-control" type="text" id="numero" name="numero">
                    </div>
                    <div class="form-group">
                        <label for="tipo">Tipo:</label> <input class="form-control" type="text" id="tipo" name="tipo">
                    </div>
                    <button type="submit" class="btn btn-sm btn-primary" onclick="addTelefone(event)">ADICIONAR</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-sm btn-danger"
                        onclick="closeModalFormTel()">CANCELAR</button>
            </div>
        </div>
    </div>
</div>