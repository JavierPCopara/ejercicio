package com.ec.ejercicio.service;

import com.ec.ejercicio.dto.UsuarioDto;
import com.ec.ejercicio.model.Cliente;
import com.ec.ejercicio.model.Persona;
import com.ec.ejercicio.repository.ClienteRepository;
import com.ec.ejercicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PersonaRepository personaRepository;

    @Transactional
    @Async
    public void crearCliente(UsuarioDto usuario) {
        Persona persona = new Persona();
        Cliente cliente = new Cliente();
        setPersona(persona, usuario);
        personaRepository.save(persona);
        cliente.setContrasenia(usuario.getContrasenia());
        cliente.setEstado(usuario.isEstado());
        cliente.setPersona(persona);
        clienteRepository.save(cliente);
    }

    @Transactional
    public ResponseEntity<?> actualizarCliente(Long id, UsuarioDto usuarioDto) {
        if (clienteRepository.findById(id).isPresent()) {
            Cliente cl = clienteRepository.findById(id).get();
            setPersona(cl.getPersona(), usuarioDto);
            cl.setId(id.intValue());
            cl.setContrasenia(usuarioDto.getContrasenia());
            cl.setEstado(usuarioDto.isEstado());
            personaRepository.save(cl.getPersona());
            return ResponseEntity.ok(clienteRepository.save(cl));
        } else {
            return null;
        }
    }

    @Transactional
    public void eliminarCliente(Long id) {
        if (clienteRepository.findById(id).isPresent()) {
            Cliente cl = clienteRepository.findById(id).get();
            clienteRepository.delete(clienteRepository.findById(id).get());
            personaRepository.deleteById(Long.valueOf(cl.getPersona().getId()));
        }
    }

    private void setPersona(Persona persona, UsuarioDto usuarioDto) {
        persona.setNombre(usuarioDto.getNombre());
        persona.setGenero(usuarioDto.getGenero());
        persona.setEdad(usuarioDto.getEdad());
        persona.setTelefono(usuarioDto.getTelefono());
        persona.setIdentificacion(usuarioDto.getIdentificacion());
        persona.setDireccion(usuarioDto.getDireccion());
    }
}
