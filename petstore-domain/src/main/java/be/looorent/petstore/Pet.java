package be.looorent.petstore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static be.looorent.petstore.IdentifiableUtil.equalsOnId;
import static be.looorent.petstore.IdentifiableUtil.hashCodeOnId;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;

/**
 * @author Lorent Lempereur - lorent.lempereur[at]gmail.com
 */
@Entity
public class Pet implements Identifiable<Long> {

    @Id
    private Long id;

    @NotNull
    private String name;

    @Enumerated(STRING)
    private PetStatus status;

    @ManyToOne
    private Category category;

    @ManyToMany(cascade = ALL, fetch = EAGER)
    private Set<Tag> tags;

    @ElementCollection(fetch = EAGER)
    @CollectionTable(name = "PHOTO")
    private Set<String> photoUrls;

    /**
     * @deprecated Used by JPA only
     */
    @Deprecated
    Pet() {
        tags = new HashSet<>();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(Set<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
