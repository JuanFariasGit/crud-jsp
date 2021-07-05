package br.com.dominio.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.dominio.model.Usuario;
import br.com.dominio.util.CriptografiaUtil;
import br.com.dominio.util.ExceptionUtil;
import br.com.dominio.util.JpaUtil;

public class UsuarioDao implements IUsuarioDao {

    private final EntityManager manager;
    private final CriptografiaUtil cript;

    public UsuarioDao() {
        this.manager = JpaUtil.getEntityManager();
        this.cript = new CriptografiaUtil();
    }

    @Override
    public void criar(Usuario usuario) throws ExceptionUtil {
        if (usuario == null) {
            throw new ExceptionUtil("O valor passado não pode ser nulo.");
        }
        try {
            manager.getTransaction().begin();
            manager.persist(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new ExceptionUtil("Erro ao adicionar dados: " + e.getMessage());
        }
    }

    @Override
    public Boolean usuarioExiste(String email, String senha) throws ExceptionUtil {
        if (email == null && senha == null) {
            throw new ExceptionUtil("Os valores passados não podem ser nulo.");
        }
        String senhaMd5 = cript.md5(senha);
        try {
            TypedQuery<Usuario> query = manager.createQuery(
                    "select usuario from Usuario usuario where usuario.senha = :senha and usuario.email = :email",
                    Usuario.class).setParameter("email", email).setParameter("senha", senhaMd5);

            int res = query.getResultList().size();

            return res == 1;
        } catch (Exception e) {
            throw new ExceptionUtil(e.getMessage());
        }
    }

    @Override
    public Boolean emailExistePorEmail(String email) throws ExceptionUtil {
        if (email == null) {
            throw new ExceptionUtil("O valor passado não pode ser nulo.");
        }
        try {
            TypedQuery<Usuario> query = manager.createQuery(
                    "select usuario from Usuario usuario where usuario.email = :email",
                    Usuario.class).setParameter("email", email);

            int res = query.getResultList().size();
            return res == 1;
        } catch (Exception e) {
            throw new ExceptionUtil(e.getMessage());
        }
    }
    
    @Override
    public Boolean emailExistePorIdEEmail(Integer id, String email) throws ExceptionUtil {
        if (id == null && email == null) {
           throw new ExceptionUtil("Os valores passados não podem ser nulo."); 
        }
        try {
            TypedQuery<Usuario> query = manager.createQuery(
                    "select usuario from Usuario usuario where usuario.id = :id and usuario.email = :email",
                    Usuario.class).setParameter("email", email).setParameter("id", id);

            int res = query.getResultList().size();
            return res == 1;
        } catch (Exception e) {
            throw new ExceptionUtil(e.getMessage());
        }
    }

    @Override
    public List<Usuario> pegarTodos() throws ExceptionUtil {
        try {
            List<Usuario> usuarios;
            usuarios = manager.createQuery("select usuario from Usuario usuario", Usuario.class).getResultList();
            return usuarios;
        } catch (Exception e) {
            throw new ExceptionUtil(e.getMessage());
        }
    }

    @Override
    public void atualizar(Usuario usuario) throws ExceptionUtil {
        if (usuario == null) {
            throw new ExceptionUtil("O valor passado não pode ser nulo.");
        }
        try {
            manager.getTransaction().begin();
            manager.merge(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new ExceptionUtil("Erro ao atualizar dados: " + e.getMessage());
        }
    }

    @Override
    public void excluir(Integer id) throws ExceptionUtil {
        if (id == null) {
            throw new ExceptionUtil("O valor passado não pode ser nulo.");
        }
        try {
            Usuario usuario = manager.find(Usuario.class, id);
            manager.getTransaction().begin();
            manager.remove(usuario);
            manager.getTransaction().commit();
        } catch (Exception e) {
            throw new ExceptionUtil("Erro ao excluir dados: " + e.getMessage());
        }
    }
}
