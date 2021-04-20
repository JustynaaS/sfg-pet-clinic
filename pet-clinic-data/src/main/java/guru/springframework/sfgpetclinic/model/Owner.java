package guru.springframework.sfgpetclinic.model;

import lombok.Setter;

import java.util.Set;

@Setter
public class Owner extends Person {
private Set<Pet> pets;

}
