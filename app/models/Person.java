package models;

import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.db.jpa.Model;
import util.UniqueObjectCheck;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.List;

/**
 * Author: Ronald Haring
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends Model {
    @Required
    @CheckWith(value = UniquePersonCheck.class, message = "person.not.unique")
    public String firstName;
    @Required
    public String lastName;

    /**
     * Used by the crud module (of vets) to show the person (a vet) in the overview
     */
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Check if there is no user by the given first and last name
     */
    private static final class UniquePersonCheck extends UniqueObjectCheck<Person> {
        protected List<Person> loadExistingUniqueObjects(Person item) {
            return Person.find("byFirstNameAndLastName", item.firstName, item.lastName).fetch(2);
        }
    }

}
