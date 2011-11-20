package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * Author: Ronald Haring
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class NamedEntity extends Model {
    @Required
    public String name;

    public String toString() {
        return name;
    }
}
