package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Specialty;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.time.LocalDate.now;

@Component
public class DataLoader implements CommandLineRunner {
private final OwnerService ownerService;
private final VetService vetService;
private final PetTypeService petTypeService;
private final SpecialtyService specialtyService;

public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
}

@Override
public void run(String... args) throws Exception {
    int count = petTypeService.findAll().size();
    if (count == 0) {
        loadData();
    }
}

private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType dogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    dog.setName("Cat");
    PetType catPetType = petTypeService.save(cat);

    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty savedRadilogy = specialtyService.save(radiology);

    Specialty surgery = new Specialty();
    radiology.setDescription("Surgery");
    Specialty savedSurgery = specialtyService.save(surgery);

    Specialty dentistry = new Specialty();
    radiology.setDescription("Dentistry");
    Specialty savedDentistry = specialtyService.save(dentistry);

    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Weston");
    owner1.setAddress("Poland 48");
    owner1.setCity("Warsaw");
    owner1.setTelephone("123-456-789");

    Pet mikesPet = new Pet();
    mikesPet.setPetType(dogPetType);
    mikesPet.setOwner(owner1);
    mikesPet.setBirthDate(now());
    mikesPet.setName("Rosco");
    owner1.getPets().add(mikesPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Fiona");
    owner2.setLastName("Glennane");
    owner2.setAddress("Poland 48");
    owner2.setCity("Warsaw");
    owner2.setTelephone("123-456-789");

    Pet fionasPet = new Pet();
    fionasPet.setPetType(catPetType);
    fionasPet.setOwner(owner2);
    fionasPet.setBirthDate(now());
    fionasPet.setName("Bruno");
    owner2.getPets().add(fionasPet);

    ownerService.save(owner2);
    System.out.println("Loaded owners...");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");
    vet1.getSpecialties().add(savedRadilogy);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jessie");
    vet2.setLastName("Porter");
    vet2.getSpecialties().add(savedSurgery);

    vetService.save(vet2);
    System.out.println("Loaded vets...");
}
}
