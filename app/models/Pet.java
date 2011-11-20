package models;

import play.data.validation.Required;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.Set;

/**
 * Properties for a pet.
 */
@Entity
public class Pet extends NamedEntity {
    @Required
    public Date birthDate;

    @ManyToOne
    @Required
    public PetType type = new PetType();

    @ManyToOne
    public PetOwner petOwner;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
    public Set<Visit> visits;
}
