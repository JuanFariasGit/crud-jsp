package br.com.dominio.dao;

import br.com.dominio.model.Usuario;
import br.com.dominio.util.ExceptionUtil;
import java.util.List;

interface IUsuarioDao {
    
   public Boolean usuarioExiste(String email, String senha) throws ExceptionUtil;
   public Boolean emailExistePorEmail(String email) throws ExceptionUtil;
   public Boolean emailExistePorIdEEmail(Integer id, String email) throws ExceptionUtil;
   public void criar(Usuario usuario) throws ExceptionUtil;
   public List<Usuario> pegarTodos() throws ExceptionUtil;
   public void atualizar(Usuario usuario) throws ExceptionUtil;
   public void excluir(Integer id) throws ExceptionUtil;
}
