package vaccination.models;

import lombok.Data;

import javax.persistence.*;

/**
 * Compound in Vaccination Date
 */
@Entity
@Data
public class CompoundInVaccinationDate implements IdObject{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "compound_id")
    Compound compound;

    @ManyToOne
    @JoinColumn(name = "vaccinationdate_id")
    VaccinationDate vaccinationDate;

    String description;
}
