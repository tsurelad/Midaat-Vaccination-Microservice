package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Vaccination Date
 */
@Entity
@Data
public class VaccinationDate implements IdObject {
    @Id
    String id;

    String name;
    String description;

    @OneToMany(mappedBy = "vaccinationDate")
    @JsonIgnore
    List<CompoundInVaccinationDate> compounds;

    @Override
    public String toString() {
        return "VaccinationDate{" +
                "description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
