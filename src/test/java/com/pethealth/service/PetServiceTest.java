package com.pethealth.service;

import com.pethealth.model.Pet;
import com.pethealth.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PetServiceTest {

    private final PetRepository petRepository = mock(PetRepository.class);
    private final PetService petService = new PetService(petRepository);

    @Test
    void testCreatePet() {
        
        Pet pet = new Pet();
        pet.setName("Rex");
        when(petRepository.save(Mockito.any(Pet.class))).thenReturn(pet);

        
        Pet result = petService.createPet(pet);

        
        assertNotNull(result);
        assertEquals("Rex", result.getName());
        verify(petRepository, times(1)).save(pet);
    }
}
