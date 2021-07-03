package br.com.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;
import br.com.dominio.util.JpaUtil;

public class UsuarioDao {

    EntityManager manager = JpaUtil.getEntityManager();
    CriptografiaUtil cript = new CriptografiaUtil();

    public void criar(Usuario usuario) throws UsuarioDaoException {
        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new UsuarioDaoException(e);
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
            throw new UsuarioDaoException(e);
        }
    }

    public void excluir(Integer id) throws UsuarioDaoException {
        try {
            Usuario usuario = manager.find(Usuario.class, id);
            manager.getTransaction().begin();
            manager.remove(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new UsuarioDaoException(e);
        }

    }
}
