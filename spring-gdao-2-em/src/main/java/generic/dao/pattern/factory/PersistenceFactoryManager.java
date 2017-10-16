package generic.dao.pattern.factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceFactoryManager {

	private static EntityManager manager;

	public static EntityManager createFactory() {
		if (manager == null) {
			try {
				EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_JPA");
				manager = factory.createEntityManager();
			} catch (Exception ex) {
				System.err.println("Erro ao criar factory: " + ex.getMessage());
			}
		}
		return manager;
	}

	public static void closeFactory() {
		if (manager != null) {
			manager.close();
		}
	}
}
