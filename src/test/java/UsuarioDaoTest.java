
//import br.com.dominio.dao.UsuarioDao;
//import br.com.dominio.model.Telefone;
//import br.com.dominio.model.Usuario;
//import br.com.dominio.util.ExceptionUtil;
//import br.com.dominio.util.JpaUtil;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import org.junit.Assert;
//import org.junit.Test;
//
//public class UsuarioDaoTest {

//    @Test
//    public void crearTest() throws ExceptionUtil {
//        EntityManager manager = JpaUtil.getEntityManager();
//
//        Usuario usuario = new Usuario();
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Telefone> telefones = new ArrayList<>();
//
//        usuario.setNome("Juan Farias");
//        usuario.setEmail("juanfarias580@gmail.com");
//        usuario.setSenha("123");
//
//        telefones.add(new Telefone(null, "81", "999966808", "Celular"));
//        usuario.setTelefones(telefones);
//
//        usuarioDao.criar(usuario);
//
//        Usuario usuarioCriado = manager.find(Usuario.class, usuario.getId());
//
//        Assert.assertEquals(telefones.toString(), 
//                usuarioCriado.getTelefones().toString());
//    }
//
//    @Test
//    public void atualizarTest() throws ExceptionUtil {
//        EntityManager manager = JpaUtil.getEntityManager();
//
//        Usuario usuario = new Usuario();
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Telefone> telefones = new ArrayList<>();
//
//        usuario.setId(1);
//        usuario.setNome("Juan de Oliveira Farias");
//        usuario.setEmail("juanfarias580@gmail.com");
//        usuario.setSenha("123");
//
//        telefones.add(new Telefone(1, "81", "999966808", "Celular"));
//        telefones.add(new Telefone(2, "81", "999999747", "Celular"));
//        usuario.setTelefones(telefones);
//
//        usuarioDao.atualizar(usuario);
//
//        Usuario usuarioAtualizado = manager.find(Usuario.class, usuario.getId());
//
//        Assert.assertEquals(telefones.toString(),
//                usuarioAtualizado.getTelefones().toString());
//    }
//}
