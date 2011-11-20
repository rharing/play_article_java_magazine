package models;

import play.data.validation.Required;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

/**
 * The person that owns a number of pets
 */
@Entity
public class PetOwner extends Person {
    @Required
    public String address;
    @Required
    public String city;
    @Required
    public String telephone;

    @OneToMany(mappedBy = "petOwner")
    public Set<Pet> pets;

    public static List<PetOwner> search(final String search) {
        final List<PetOwner> petOwners;
        if (search.contains("*")) {
            petOwners = PetOwner.find("from PetOwner where lastName like ?1",
                    search.replaceAll("\\*", "%")).fetch();
        }
        else {
            petOwners = PetOwner.find("byLastName", search).fetch();
        }
        return petOwners;
    }
}
