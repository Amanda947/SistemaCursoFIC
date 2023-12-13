/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.Celular;
import com.amanda.testes.sistemaFIC.models.entity.Estudante;
import com.amanda.testes.sistemaFIC.models.repository.EstudanteRepository;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class EstudanteServiceTest {
    @Mock
    private EstudanteRepository repository;

    @InjectMocks
    private EstudanteService service;
    
    @Test
    public void testSaveWithInvalidFields() {
        Estudante obj = new Estudante(); // Estudante com campos em falta

        // Exceção lançada ao salvar um Estudante com campos inválidos
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testSaveWithValidFields() {
        LocalDate date = LocalDate.of(2001, 12, 13);

        Estudante obj = new Estudante("Amanda", "11232", "Palmas", "amanda@ifto.com", date); // Estudante com todos os campos 
        List<Celular> celulares = new ArrayList();
        celulares.add(new Celular("40028922", obj));
        obj.setCelulares(celulares);
        
        // Configurar o comportamento do repository.save mock
        when(repository.save(obj)).thenReturn(obj);

        Estudante objSaved = service.save(obj);

        assertNotNull(objSaved);
    }
    
    @Test
    public void testSaveInvalidAge() {
        LocalDate date = LocalDate.of(2020, 12, 13);
        
        Estudante obj = new Estudante("Amanda", "11232", "Palmas", "amanda@ifto.com", date); // Estudante com todos os campos 
        List<Celular> celulares = new ArrayList();
        celulares.add(new Celular("40028922", obj));
        obj.setCelulares(celulares);

        assertThrows(IllegalArgumentException.class, () -> service.save(obj));

    }
}
