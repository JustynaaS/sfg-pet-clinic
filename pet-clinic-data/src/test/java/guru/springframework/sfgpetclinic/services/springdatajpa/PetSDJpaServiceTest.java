package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PetSDJpaServiceTest {

@Mock
PetRepository petRepository;

@InjectMocks
PetSDJpaService service;

Pet savedPet;
private static final Long ID = 1L;


@BeforeEach
void setUp() {
    savedPet = Pet.builder().id(ID).build();
}

@Test
void findAll() {
    Set<Pet> petSet = new HashSet<>();
    petSet.add(Pet.builder().id(1L).build());
    petSet.add(Pet.builder().id(1L).build());

    when(petRepository.findAll()).thenReturn(petSet);

    Set<Pet> pets = service.findAll();

    assertNotNull(pets);
    assertEquals(2, pets.size());
}

@Test
void findById() {
    when(petRepository.findById(anyLong())).thenReturn(of(savedPet));

    Pet pet = service.findById(ID);

    assertNotNull(pet);
    verify(petRepository, times(1)).findById(anyLong());
}

@Test
void findByIdNotFound() {
    when(petRepository.findById(anyLong())).thenReturn(empty());

    Pet pet = service.findById(2L);

    assertNull(pet);
    verify(petRepository, times(1)).findById(anyLong());
}

@Test
void save() {
    when(petRepository.save(any())).thenReturn(savedPet);
    Pet pet = service.save(savedPet);

    assertNotNull(pet);
    verify(petRepository, times(1)).save(any());
}

@Test
void delete() {
    service.delete(savedPet);

    verify(petRepository, times(1)).delete(any());
}

@Test
void deleteById() {
    service.deleteById(ID);

    verify(petRepository, times(1)).deleteById(anyLong());
}
}