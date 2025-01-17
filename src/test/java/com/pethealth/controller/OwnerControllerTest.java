package com.pethealth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pethealth.model.Owner;
import com.pethealth.service.OwnerService;
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

@WebMvcTest(OwnerController.class)
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerService ownerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetOwnerById() throws Exception {
        // Mock data
        Owner owner = new Owner(1L, "João", "joao@example.com", "123456789");
        when(ownerService.getOwnerById(1L)).thenReturn(Optional.of(owner));

        // Perform GET request
        mockMvc.perform(get("/api/owners/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("João")))
                .andExpect(jsonPath("$.email", is("joao@example.com")))
                .andExpect(jsonPath("$.phone", is("123456789")));

        verify(ownerService, times(1)).getOwnerById(1L);
    }

    @Test
    void testCreateOwner() throws Exception {
        // Mock data
        Owner owner = new Owner(1L, "Maria", "maria@example.com", "987654321");
        when(ownerService.createOwner(Mockito.any(Owner.class))).thenReturn(owner);

        // Perform POST request
        mockMvc.perform(post("/api/owners")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(owner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Maria")))
                .andExpect(jsonPath("$.email", is("maria@example.com")))
                .andExpect(jsonPath("$.phone", is("987654321")));

        verify(ownerService, times(1)).createOwner(Mockito.any(Owner.class));
    }

    @Test
    void testUpdateOwner() throws Exception {
        // Mock data
        Owner updatedOwner = new Owner(1L, "Carlos", "carlos@example.com", "555555555");
        when(ownerService.updateOwner(eq(1L), Mockito.any(Owner.class))).thenReturn(Optional.of(updatedOwner));

        // Perform PUT request
        mockMvc.perform(put("/api/owners/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedOwner)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Carlos")))
                .andExpect(jsonPath("$.email", is("carlos@example.com")))
                .andExpect(jsonPath("$.phone", is("555555555")));

        verify(ownerService, times(1)).updateOwner(eq(1L), Mockito.any(Owner.class));
    }

    @Test
    void testDeleteOwner() throws Exception {
        // Mock data
        when(ownerService.deleteOwner(1L)).thenReturn(true);

        // Perform DELETE request
        mockMvc.perform(delete("/api/owners/1"))
                .andExpect(status().isNoContent()); 

        verify(ownerService, times(1)).deleteOwner(1L);
    }
}
