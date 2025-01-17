package com.pethealth.service;

import com.pethealth.model.Owner;
import com.pethealth.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner createOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    public Optional<Owner> updateOwner(Long id, Owner updatedOwner) {
        return ownerRepository.findById(id).map(owner -> {
            owner.setName(updatedOwner.getName());
            owner.setEmail(updatedOwner.getEmail());
            owner.setPhone(updatedOwner.getPhone());
            return ownerRepository.save(owner);
        });
    }

    public boolean deleteOwner(Long id) {
        return ownerRepository.findById(id).map(owner -> {
            ownerRepository.delete(owner);
            return true;
        }).orElse(false);
    }
}
