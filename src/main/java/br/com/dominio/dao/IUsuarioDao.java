package br.com.dominio.dao;

import br.com.dominio.model.Usuario;
import java.util.List;

interface IUsuarioDao {
   public void criar(Usuario usuario) throws UsuarioDaoException;
   public Boolean usuarioExiste(String email, String senha) throws UsuarioDaoException;
   public List<Usuario> pegarTodos() throws UsuarioDaoException;
   public void atualizar(Usuario usuario) throws UsuarioDaoException;
   public void excluir(Integer id) throws UsuarioDaoException;
}
