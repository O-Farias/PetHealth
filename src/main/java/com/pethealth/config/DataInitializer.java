package com.pethealth.config;

import com.pethealth.model.Owner;
import com.pethealth.model.Pet;
import com.pethealth.repository.OwnerRepository;
import com.pethealth.repository.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(OwnerRepository ownerRepository, PetRepository petRepository) {
        return args -> {
            Owner owner = new Owner();
            owner.setName("John Doe");
            owner.setEmail("johndoe@example.com");
            owner.setPhone("123456789");

            Pet pet = new Pet();
            pet.setName("Buddy");
            pet.setSpecies("Dog");
            pet.setBreed("Golden Retriever");
            pet.setAge(3);
            pet.setOwner(owner);

            ownerRepository.save(owner); 
        };
    }
}
