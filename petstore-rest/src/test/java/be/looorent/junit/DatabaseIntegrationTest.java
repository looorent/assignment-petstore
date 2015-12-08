package be.looorent.junit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Tests that extend this class can work with a test database instance.
 * Every method is executed in a transactional context.
 * The 'domain' Spring configuration is loaded through this class.
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
//@Transactio
//@Rollback
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(SampleDataJpaApplication.class)
public abstract class DatabaseIntegrationTest {

	@PersistenceContext(unitName="petstore")
	protected EntityManager entityManager; 
	
	/**
	 * Flush and clear the persistence context
	 */
	protected void simulateCommit() {
		entityManager.flush();
		entityManager.clear();
	}

	/**
	 * @return the provided object, persisted or not
	 */
	protected <T> T persist(T entity) {
		if (entity != null) {
			if (entity instanceof Iterable) {
				((Iterable<?>) entity).forEach(this::persist);
			}
			else {
				entityManager.persist(entity);			
			}			
		}
		return entity;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}	
}
