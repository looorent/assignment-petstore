package be.looorent.petstore;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;


/**
 * Used to compare all identifiables by concrete type and Id
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
public final class IdentifiableUtil {

    private IdentifiableUtil() {}

    public static int hashCodeOnId(Identifiable<?> identifiable) {
        if (identifiable.getId() == null) {
            return System.identityHashCode(identifiable);
        }
        else {
            return new HashCodeBuilder().append(identifiable.getId()).toHashCode();
        }
    }

    public static boolean equalsOnId(Identifiable<?> identifiable, Object other) {
        if (identifiable == other) {
            return true;
        }

        if (other == null || !identifiable.getClass().isAssignableFrom(other.getClass())) {
            return false;
        }

        Identifiable<?> otherIdentifiable = (Identifiable<?>) other;
        if (identifiable.getId() == null && otherIdentifiable.getId() == null) {
            return EqualsBuilder.reflectionEquals(identifiable, otherIdentifiable);
        }
        else {
            return new EqualsBuilder().append(identifiable.getId(), otherIdentifiable.getId()).isEquals();
        }
    }

    public static <T extends Identifiable<? extends Number>> Iterator<T> orderedIterator(Collection<T> collectionToIterate)  {
        List<T> list = new ArrayList<>(collectionToIterate);
        Collections.sort(list, new IdentifiableByIdComparator());
        return list.iterator();
    }
}
