package com.ec.ejercicio.controller;

import com.ec.ejercicio.dto.UsuarioDto;
import com.ec.ejercicio.model.Persona;
import com.ec.ejercicio.repository.ClienteRepository;
import com.ec.ejercicio.repository.PersonaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;
    @Mock
    private PersonaRepository personaRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        clienteRepository.deleteAll();
    }

    /*
    *** Prueba Unitaria
     */
    @Test
    void testClienteConstructor() {
        Persona p = new Persona();
        p.setIdentificacion("111111222222");
        when(personaRepositoryMock.findByIdentificacion("111111222222")).thenReturn(p);
        Persona persona = personaRepositoryMock.findByIdentificacion("111111222222");
        assertNotNull(persona);
    }

    /*
     *** Prueba de integracion
     */
    @Test
    void testCrearCliente() throws Exception {
        // Datos de entrada del cliente
        UsuarioDto clienteMock = new UsuarioDto("Juan Pérez", "M", 33, "192837484", "Las casas", "0943454", "1232321", true, 1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(clienteMock);
        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonString))
                .andExpect(status().isCreated())
                .andExpect(content().string("{\"mensaje\":\"Operación exitosa\",\"resultado\":true}"));
        Persona clienteExistente = personaRepository.findByIdentificacion("192837484");
        assert(clienteExistente.getId()!=null);
    }
}
