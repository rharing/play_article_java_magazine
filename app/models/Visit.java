package models;

import net.sf.oval.constraint.Past;
import org.hibernate.annotations.ForeignKey;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * A visit of a pet on a certain date.
 */
@Entity
public class Visit extends Model {

    @Past(message = "visit.date.must.be.in.past")
    public Date date = new Date();

    @Required
    public String description;

    @ManyToOne
    public Pet pet;

    @ManyToOne
    public Vet vet;

}
