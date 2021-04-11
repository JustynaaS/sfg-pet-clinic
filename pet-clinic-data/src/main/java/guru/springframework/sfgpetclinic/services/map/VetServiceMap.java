package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
@Override
public Set<Vet> findAll() {
    return null;
}

@Override
public void deleteById(Long id) {

}

@Override
public void delete(Vet object) {

}

@Override
public Vet save(Vet object) {
    return null;
}

@Override
public Vet findById(Long id) {
    return null;
}
}
