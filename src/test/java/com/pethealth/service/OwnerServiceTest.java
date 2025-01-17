package com.pethealth.service;

import com.pethealth.model.Owner;
import com.pethealth.repository.OwnerRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OwnerServiceTest {

    private final OwnerRepository ownerRepository = mock(OwnerRepository.class);
    private final OwnerService ownerService = new OwnerService(ownerRepository);

    @Test
    void testGetOwnerById() {
        
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("João");
        when(ownerRepository.findById(1L)).thenReturn(Optional.of(owner));

        
        Optional<Owner> result = ownerService.getOwnerById(1L);

        
        assertTrue(result.isPresent());
        assertEquals("João", result.get().getName());
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetOwnerById_NotFound() {
        
        when(ownerRepository.findById(1L)).thenReturn(Optional.empty());

        
        Optional<Owner> result = ownerService.getOwnerById(1L);

        
        assertTrue(result.isEmpty());
        verify(ownerRepository, times(1)).findById(1L);
    }
}
