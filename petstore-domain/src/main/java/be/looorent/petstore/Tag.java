package be.looorent.petstore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static be.looorent.petstore.IdentifiableUtil.equalsOnId;
import static be.looorent.petstore.IdentifiableUtil.hashCodeOnId;
import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
@Entity
public class Tag implements Identifiable<Long> {

    @Id
    private Long id;

    @NotNull
    private String name;

    /**
     * @deprecated Used by JPA only
     */
    @Deprecated
    Tag() {}

    public Tag(String name) {
        checkArgument(isNotEmpty(name), "name must not be null or empty");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        return equalsOnId(this, other);
    }

    @Override
    public int hashCode() {
        return hashCodeOnId(this);
    }
}
