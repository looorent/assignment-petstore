package be.looorent.junit;

import be.looorent.petstore.Identifiable;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
public abstract class PersistenceITest<T extends Identifiable<?>> extends DatabaseIntegrationTest {

	private T createdEntity;

	@Before
	public void setup() throws Exception {
		createdEntity = createEntity();
		if (!entityManager.contains(createdEntity)) {
			entityManager.persist(createdEntity);			
		}
		simulateCommit();
	}

	@Test
	public void entityHasBeenPersistedCorrectly() {
		assertThat(createdEntity.getId(), is(not(nullValue())));
		T loadedEntity = entityManager.find(getEntityClass(),
				createdEntity.getId());
		assertThatEntitiesAreEqual(loadedEntity, createdEntity);
	}

	protected abstract T createEntity() throws Exception;

	protected abstract Class<T> getEntityClass();

	protected abstract void assertThatEntitiesAreEqual(T loadedEntity, T expectedEntity);
}
