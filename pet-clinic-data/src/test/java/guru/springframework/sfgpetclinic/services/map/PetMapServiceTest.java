package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PetMapServiceTest {

PetMapService petMapService;
final Long petId = 1L;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
    petMapService = new PetMapService();
    petMapService.save(Pet.builder().id(petId).build());
}

@Test
void findAll() {
    Set<Pet> pets = petMapService.findAll();

    assertEquals(1, pets.size());
}

@Test
void deleteById() {
    petMapService.deleteById(petId);

    assertEquals(0, petMapService.findAll().size());
}

@Test
void delete() {
    petMapService.delete(petMapService.findById(petId));

    assertEquals(0, petMapService.findAll().size());
}

@Test
void saveExistingId() {
    Long id = 2L;
    Pet pet = Pet.builder().id(id).build();
    Pet savedPet = petMapService.save(pet);

    assertEquals(id, savedPet.getId());
}

@Test
void saveNoId() {
    Pet savedPet = petMapService.save(Pet.builder().build());

    assertNotNull(petMapService);
    assertNotNull(savedPet.getId());
}

@Test
void findById() {
    Pet pet = petMapService.findById(petId);

    assertEquals(petId, pet.getId());
}
}