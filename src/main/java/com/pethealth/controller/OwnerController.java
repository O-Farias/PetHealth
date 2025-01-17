package com.pethealth.controller;

import com.pethealth.model.Owner;
import com.pethealth.repository.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    // Listar todos os tutores
    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Buscar tutor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar novo tutor
    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner savedOwner = ownerRepository.save(owner);
        return ResponseEntity.ok(savedOwner);
    }

    // Atualizar tutor
    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner updatedOwner) {
        return ownerRepository.findById(id)
                .map(owner -> {
                    owner.setName(updatedOwner.getName());
                    owner.setEmail(updatedOwner.getEmail());
                    owner.setPhone(updatedOwner.getPhone());
                    Owner savedOwner = ownerRepository.save(owner);
                    return ResponseEntity.ok(savedOwner);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar tutor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        return ownerRepository.findById(id)
                .map(owner -> {
                    ownerRepository.delete(owner);
                    return ResponseEntity.noContent().<Void>build(); 
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
