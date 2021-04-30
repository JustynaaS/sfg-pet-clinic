package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

@Mock
OwnerRepository ownerRepository;

@Mock
PetRepository petRepository;

@Mock
PetTypeRepository petTypeRepository;

@InjectMocks
OwnerSDJpaService ownerSDJpaService;

Owner returnOwner;

private static final String LAST_NAME = "Smith";
final Long ownerId = 1L;

@BeforeEach
void setUp() {
    returnOwner = Owner.builder().id(ownerId).lastName(LAST_NAME).build();
}

@Test
void findAll() {
    Set<Owner> returnOwnerSet = new HashSet<>();
    returnOwnerSet.add(Owner.builder().id(1L).build());
    returnOwnerSet.add(Owner.builder().id(2L).build());

    when(ownerRepository.findAll()).thenReturn(returnOwnerSet);

    Set<Owner> owners = ownerSDJpaService.findAll();

    assertNotNull(owners);
    assertEquals(2, owners.size());
}

@Test
void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(of(returnOwner));

    Owner owner = ownerSDJpaService.findById(ownerId);

    assertNotNull(owner);
}

@Test
void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(empty());

    Owner owner = ownerSDJpaService.findById(ownerId);

    assertNull(owner);
}

@Test
void save() {
    Owner ownerToSave = Owner.builder().id(ownerId).build();

    when(ownerRepository.save(any())).thenReturn(ownerToSave);

    Owner savedOwner = ownerSDJpaService.save(ownerToSave);

    assertNotNull(savedOwner);
    verify(ownerRepository, times(1)).save(any());
}

@Test
void delete() {
    ownerSDJpaService.delete(returnOwner);

    verify(ownerRepository, times(1)).delete(any());
}

@Test
void deleteById() {
    ownerSDJpaService.deleteById(ownerId);

    verify(ownerRepository, times(1)).deleteById(anyLong());
}

@Test
void findByLastName() {
    when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
    Owner savedOwner = ownerSDJpaService.findByLastName(LAST_NAME);

    assertEquals(LAST_NAME, savedOwner.getLastName());
    verify(ownerRepository, times(1)).findByLastName(any());
}
}