package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.net.URI;
import java.util.List;

/**
 * Compound
 */
@Entity
@Data
public class Compound implements IdObject {
    @Id
    String id;

    String name;
    String compoundMethod;
    String description;
    String furtherInformation;
    String color;
    URI externalLink;

    @OneToMany(mappedBy = "compound")
    @JsonIgnore
    List<VaccinationInCompound> vaccinations;

    @OneToMany(mappedBy = "compound")
    @JsonIgnore
    List<CompoundInVaccinationDate> vaccinationDates;
}
