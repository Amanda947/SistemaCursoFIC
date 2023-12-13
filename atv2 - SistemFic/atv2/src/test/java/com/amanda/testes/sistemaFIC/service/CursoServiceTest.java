/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.Curso;
import com.amanda.testes.sistemaFIC.models.repository.CursoRepository;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {
    @Mock
    private CursoRepository repository;

    @InjectMocks
    private CursoService service;
    
    @Test
    public void testSaveWithInvalidFields() {
        Curso obj = new Curso(); // curso com campos em falta

        // Exceção lançada ao salvar um curso com campos inválidos
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testSaveWithValidFields() {
        Curso obj = new Curso("Sistemas", "14h", "Curso de sistemas"); // curso com todos os campos 

        // Configurar o comportamento do repository.save mock
        when(repository.save(obj)).thenReturn(obj);

        Curso objSaved = service.save(obj);

        assertNotNull(objSaved);
    }
}
