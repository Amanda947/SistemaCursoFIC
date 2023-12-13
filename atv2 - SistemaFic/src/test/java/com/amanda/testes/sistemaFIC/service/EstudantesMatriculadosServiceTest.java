/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amanda.testes.sistemaFIC.service;

import com.amanda.testes.sistemaFIC.models.entity.Celular;
import com.amanda.testes.sistemaFIC.models.entity.Curso;
import com.amanda.testes.sistemaFIC.models.entity.Estudante;
import com.amanda.testes.sistemaFIC.models.entity.EstudantesMatriculados;
import com.amanda.testes.sistemaFIC.models.entity.TurmaCurso;
import com.amanda.testes.sistemaFIC.models.repository.EstudantesMatriculadosRepository;
import com.amanda.testes.sistemaFIC.models.repository.TurmaCursoRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class EstudantesMatriculadosServiceTest {

    @Mock
    private EstudantesMatriculadosRepository repository;

    @InjectMocks
    private EstudantesMatriculadosService service;

    @Mock
    private TurmaCursoRepository turmaRepository;

    @Test
    public void testSaveWithValidFields() {
        EstudantesMatriculados obj = createEstudantesMatriculadosWithValidValues(); // EstudantesMatriculados com todos os campos 

        // Configurar o comportamento do repository.save mock
        when(repository.save(obj)).thenReturn(obj);
        when(turmaRepository.save(obj.getTurmaCurso())).thenReturn(obj.getTurmaCurso());

        EstudantesMatriculados objSaved = service.save(obj);

        assertNotNull(objSaved);
    }

    @Test
    public void testEnrollStudentWithNotAvailableSlots() {
        EstudantesMatriculados obj = createEstudantesMatriculadosWithValidValues(); // EstudantesMatriculados com todos os campos 
        obj.getTurmaCurso().setVagasDisponiveis(0);

        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testEnrollStudentOutsideEnrollmentPeriod() {
        EstudantesMatriculados obj = createEstudantesMatriculadosWithValidValues(); // EstudantesMatriculados com todos os campos 
        obj.setDataMatricula(LocalDate.MIN);

        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }
    
    
    private EstudantesMatriculados createEstudantesMatriculadosWithValidValues() {
        LocalDate date = LocalDate.of(2001, 12, 13);

        Estudante estudante = new Estudante("Amanda", "11232", "Palmas", "amanda@ifto.com", date); // Estudante com todos os campos 
        List<Celular> celulares = new ArrayList();
        celulares.add(new Celular("40028922", estudante));
        estudante.setCelulares(celulares);

        List estudantes = new ArrayList();
        estudantes.add(estudante);

        Curso curso = new Curso("Sistemas", "14h", "Curso de sistemas"); // curso com todos os campos 

        TurmaCurso turma = new TurmaCurso("Sala 10", 100,
                LocalDate.of(2024, 3, 20), LocalDate.of(2024, 7, 20),
                LocalDate.of(2023, 12, 1), LocalDate.of(2024, 2, 15),
                curso);

        EstudantesMatriculados matricula = new EstudantesMatriculados(LocalDate.now(), turma, estudante);

        List<EstudantesMatriculados> matriculas = new ArrayList();
        matriculas.add(matricula);

        turma.setEstudantesMatriculadoses(matriculas);
        return matricula;
    }
}
