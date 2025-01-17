package com.pethealth.service;

import com.pethealth.model.Pet;
import com.pethealth.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> updatePet(Long id, Pet updatedPet) {
        return petRepository.findById(id).map(pet -> {
            pet.setName(updatedPet.getName());
            pet.setSpecies(updatedPet.getSpecies());
            pet.setBreed(updatedPet.getBreed());
            pet.setAge(updatedPet.getAge());
            return petRepository.save(pet);
        });
    }

    public boolean deletePet(Long id) {
        return petRepository.findById(id).map(pet -> {
            petRepository.delete(pet);
            return true;
        }).orElse(false);
    }
}
