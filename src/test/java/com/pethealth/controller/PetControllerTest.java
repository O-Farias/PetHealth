package com.pethealth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethealth.model.Pet;
import com.pethealth.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetPetById() throws Exception {
        // Mock data
        Pet pet = new Pet(1L, "Rex", "Dog", 5);
        when(petService.getPetById(1L)).thenReturn(Optional.of(pet));

        // Perform GET request
        mockMvc.perform(get("/api/pets/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Rex")))
                .andExpect(jsonPath("$.species", is("Dog")))
                .andExpect(jsonPath("$.age", is(5)));

        verify(petService, times(1)).getPetById(1L);
    }

    @Test
    void testCreatePet() throws Exception {
        // Mock data
        Pet pet = new Pet(1L, "Max", "Cat", 3);
        when(petService.createPet(Mockito.any(Pet.class))).thenReturn(pet);

        // Perform POST request
        mockMvc.perform(post("/api/pets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Max")))
                .andExpect(jsonPath("$.species", is("Cat")))
                .andExpect(jsonPath("$.age", is(3)));

        verify(petService, times(1)).createPet(Mockito.any(Pet.class));
    }

    @Test
    void testUpdatePet() throws Exception {
        // Mock data
        Pet updatedPet = new Pet(1L, "Buddy", "Dog", 4);
        when(petService.updatePet(eq(1L), Mockito.any(Pet.class))).thenReturn(Optional.of(updatedPet));

        // Perform PUT request
        mockMvc.perform(put("/api/pets/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Buddy")))
                .andExpect(jsonPath("$.species", is("Dog")))
                .andExpect(jsonPath("$.age", is(4)));

        verify(petService, times(1)).updatePet(eq(1L), Mockito.any(Pet.class));
    }

    @Test
    void testDeletePet() throws Exception {
        // Mock data
        when(petService.deletePet(1L)).thenReturn(true);

        // Perform DELETE request
        mockMvc.perform(delete("/api/pets/1"))
                .andExpect(status().isNoContent()); 

        verify(petService, times(1)).deletePet(1L);
    }
}
