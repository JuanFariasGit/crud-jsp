
import br.com.dominio.dao.UsuarioDao;
import br.com.dominio.model.Telefone;
import br.com.dominio.model.Usuario;
import br.com.dominio.util.ExceptionUtil;
import br.com.dominio.util.JpaUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Assert;
import org.junit.Test;


public class UsuarioDaoTest {
    
    @Test
    public void crearTest() throws ExceptionUtil {
      EntityManager manager = JpaUtil.getEntityManager();
      
      Usuario usuario = new Usuario();
      UsuarioDao usuarioDao = new UsuarioDao();
      List<Telefone> telefones = new ArrayList<>();
      
      usuario.setNome("Juan Farias");
      usuario.setEmail("juanfarias580@gmail.com");
      usuario.setSenha("123");
      
      telefones.add(new Telefone(null, "81", "999966808", "Celular"));
      usuario.setTelefones(telefones);
      
      usuarioDao.criar(usuario);
      
      Usuario usuarioCriado = manager.find(Usuario.class, usuario.getId());
      
      Assert.assertEquals(usuarioCriado.getTelefones(), usuario.getTelefones());
    }
    
    @Test
    public void atualizarTest() throws ExceptionUtil {
      EntityManager manager = JpaUtil.getEntityManager();
      
      Usuario usuario = new Usuario();
      UsuarioDao usuarioDao = new UsuarioDao();
      List<Telefone> telefones = new ArrayList<>();
      
      usuario.setNome("Juan Farias");
      usuario.setEmail("juanfarias580@gmail.com");
      usuario.setSenha("123");
      
      telefones.add(new Telefone(null, "81", "999966808", "Celular"));
      usuario.setTelefones(telefones);
      
      usuarioDao.criar(usuario);
      Usuario usuarioCriado = manager.find(Usuario.class, usuario.getId());
      String nomeCriado = usuarioCriado.getNome();
      
      usuarioCriado.setNome("Juan de Oliveira Farias");
      usuarioCriado.setEmail("juanfarias580@gmail.com");
      usuarioCriado.setSenha("123");
      
      telefones.add(new Telefone(null, "81", "999966808", "Celular"));
      usuarioCriado.setTelefones(telefones);
      
      usuarioDao.atualizar(usuarioCriado);
      Usuario usuarioAtualizado = manager.find(Usuario.class, usuario.getId());
      String nomeAtualizado = usuarioAtualizado.getNome();
      
      Assert.assertNotEquals(nomeCriado, nomeAtualizado);
    }
}
