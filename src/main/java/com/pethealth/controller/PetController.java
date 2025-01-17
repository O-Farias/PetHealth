package com.pethealth.controller;

import com.pethealth.model.Pet;
import com.pethealth.repository.PetRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Listar todos os pets
    @GetMapping
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    // Buscar pet por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return petRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo pet
    @PostMapping
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        Pet savedPet = petRepository.save(pet);
        return ResponseEntity.ok(savedPet);
    }

    // Atualizar pet
    @PutMapping("/{id}")
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @RequestBody Pet updatedPet) {
        return petRepository.findById(id)
                .map(pet -> {
                    pet.setName(updatedPet.getName());
                    pet.setSpecies(updatedPet.getSpecies());
                    pet.setBreed(updatedPet.getBreed());
                    pet.setAge(updatedPet.getAge());
                    Pet savedPet = petRepository.save(pet);
                    return ResponseEntity.ok(savedPet);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar pet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        return petRepository.findById(id)
                .map(pet -> {
                    petRepository.delete(pet);
                    return ResponseEntity.noContent().<Void>build(); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
