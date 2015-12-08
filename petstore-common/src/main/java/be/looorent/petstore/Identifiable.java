package be.looorent.petstore;


/**
 * Every entity that has an ID.
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
public interface Identifiable<T> {

	/**
	 * @return The ID of the entity.
	 */
	T getId();
}
