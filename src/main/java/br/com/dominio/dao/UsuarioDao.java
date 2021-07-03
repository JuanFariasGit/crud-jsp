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

    public void criar(Usuario usuario) throws UsuarioDaoException {
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

    public Boolean usuarioExiste(String email, String senha) {
        String senhaMd5 = cript.md5(senha);

        TypedQuery<Usuario> query = manager.createQuery(
                "select usuario from Usuario usuario where usuario.senha = :senha and usuario.email = :email",
                Usuario.class).setParameter("email", email).setParameter("senha", senhaMd5);

        int res = query.getResultList().size();

        return res == 1;
    }

    public List<Usuario> pegarTodos() {
        List<Usuario> usuarios;
        usuarios = manager.createQuery("select usuario from Usuario usuario", Usuario.class).getResultList();
        return usuarios;
    }

    public void atualizar(Usuario usuario) throws UsuarioDaoException {
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

    public void excluir(Integer id) throws UsuarioDaoException {
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
