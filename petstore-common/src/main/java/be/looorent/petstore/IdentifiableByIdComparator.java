package be.looorent.petstore;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Used to compare some numeric identifiables object by their id.
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
class IdentifiableByIdComparator implements
		Comparator<Identifiable<? extends Number>>, Serializable {

    private static final long serialVersionUID = 1L;

	@Override
	public int compare(Identifiable<? extends Number> o1, Identifiable<? extends Number> o2) {
		return Long.compare(o1.getId().longValue(), o2.getId().longValue());
	}
}
