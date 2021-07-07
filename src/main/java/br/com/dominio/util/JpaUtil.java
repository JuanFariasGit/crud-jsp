package br.com.dominio.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

    private static EntityManagerFactory managerFactory = null;
    private static EntityManager manager = null;

    public static EntityManager getEntityManager() {
        if (managerFactory == null) {
            managerFactory = Persistence.createEntityManagerFactory("crud-jsp");
        }
        if (manager == null) {
            manager = managerFactory.createEntityManager();
        }
        return manager;
    }
}
