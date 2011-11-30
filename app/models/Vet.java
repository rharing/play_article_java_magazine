package models;

import org.hibernate.annotations.ForeignKey;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * A vet with his/her specialities
 */
@Entity
public class Vet extends Person {
    @ManyToMany
    @ForeignKey(name = "vet_id")
    public List<Speciality> specialities;

    @OneToMany(mappedBy = "vet")
    public List<Visit> visits;


}
