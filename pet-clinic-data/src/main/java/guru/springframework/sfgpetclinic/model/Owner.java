package guru.springframework.sfgpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

@Builder
public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
    super(id, firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;

    if(pets != null) {
        this.pets = pets;
    }
}

@Column(name = "address")
private String address;

@Column(name = "city")
private String city;

@Column(name = "telephone")
private String telephone;

@OneToMany(cascade = ALL, mappedBy = "owner")
private Set<Pet> pets = new HashSet<>();

}
