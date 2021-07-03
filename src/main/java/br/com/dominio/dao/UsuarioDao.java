package br.com.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;
import br.com.dominio.util.JpaUtil;

public class UsuarioDao implements IUsuarioDao {

    EntityManager manager;
    CriptografiaUtil cript = new CriptografiaUtil();

    public UsuarioDao() {
        this.manager = JpaUtil.getEntityManager();
    }

    @Override
    public void criar(Usuario usuario) throws UsuarioDaoException {
        if (usuario == null) 
            throw new UsuarioDaoException("O valor passado não pode ser nulo.");
        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new UsuarioDaoException("Erro ao adicionar dados: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public Boolean usuarioExiste(String email, String senha) throws UsuarioDaoException {
        if (email == null && senha == null) 
            throw new UsuarioDaoException("Os valores passados não podem ser nulo.");
        String senhaMd5 = cript.md5(senha);
        try {
        TypedQuery<Usuario> query = manager.createQuery(
                "select usuario from Usuario usuario where usuario.senha = :senha and usuario.email = :email",
                Usuario.class).setParameter("email", email).setParameter("senha", senhaMd5);

        int res = query.getResultList().size();

        return res == 1;
        } catch (Exception e) {
            throw new UsuarioDaoException(e.getMessage());
        }
    }

    @Override
    public List<Usuario> pegarTodos() throws UsuarioDaoException {
        try {
            List<Usuario> usuarios;
            usuarios = manager.createQuery("select usuario from Usuario usuario", Usuario.class).getResultList();
            return usuarios;
        } catch (Exception e) {
            throw new UsuarioDaoException(e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws UsuarioDaoException {
        if (usuario == null) 
            throw new UsuarioDaoException("O valor passado não pode ser nulo.");
        try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new UsuarioDaoException("Erro ao atualizar dados: " + e.getMessage());
        } finally {
            manager.close();
        }
    }

    @Override
    public void excluir(Integer id) throws UsuarioDaoException {
        if (id == null) 
            throw new UsuarioDaoException("O valor passado não pode ser nulo.");
        try {
            Usuario usuario = manager.find(Usuario.class, id);
            manager.getTransaction().begin();
            manager.remove(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new UsuarioDaoException("Erro ao excluir dados: " + e.getMessage());
        } finally {
            manager.close();
        }

    }
}
