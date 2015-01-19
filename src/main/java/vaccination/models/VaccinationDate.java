package vaccination.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Vaccination Date
 */
@Entity
@Data
public class VaccinationDate implements IdObject {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

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
