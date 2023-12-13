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
import com.amanda.testes.sistemaFIC.models.repository.TurmaCursoRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
public class TurmaCursoServiceTest {
    @Mock
    private TurmaCursoRepository repository;

    @InjectMocks
    private TurmaCursoService service;
    
    @Test
    public void testSaveWithInvalidFields() {
        TurmaCurso obj = new TurmaCurso(); // Turmacurso com campos em falta

        // Exceção lançada ao salvar um Turmacurso com campos inválidos
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testSaveWithValidFields() {
        TurmaCurso obj = createTurmaCursoWithValidValues();

        // Configurar o comportamento do repository.save mock
        when(repository.save(obj)).thenReturn(obj);

        TurmaCurso objSaved = service.save(obj);

        assertNotNull(objSaved);
    }
    
    @Test
    public void testInvalidPeriodForMatriculation() {
        // Criar uma turma com período de matrículas inválido
        Curso curso = new Curso("Sistemas", "14h", "Curso de sistemas"); // curso com todos os campos 

        TurmaCurso obj = new TurmaCurso("Sala 10", 100, 
                LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 15), 
                LocalDate.of(2024, 3, 20), LocalDate.of(2024, 7, 20), 
                curso);

        // Verificar se a exceção é lançada ao salvar uma turma com período de matrículas inválido
        assertThrows(IllegalArgumentException.class, () -> service.save(obj));
    }

    @Test
    public void testFindTurmaWithEstudantesMatriculados() {
        // Configurar o comportamento do repositório mock para retornar uma turma com estudantes matriculados
        when(repository.findById(1)).thenReturn(Optional.of(createTurmaCursoWithValidValues()));

        // Verificar se a turma com estudantes matriculados é retornada corretamente pelo findById
        TurmaCurso turma = service.findById(1);

        assertNotNull(turma);
    }

    @Test
    public void testFindTurmaWithoutEstudantesMatriculados() {
        // Configurar o comportamento do repositório mock para retornar uma turma sem estudantes matriculados
        TurmaCurso obj = createTurmaCursoWithValidValues();
        obj.setEstudantesMatriculadoses(new ArrayList());
        when(repository.findById(2)).thenReturn(Optional.of(obj));

        // Verificar se é lançada a exceção ao tentar encontrar uma turma sem estudantes matriculados
        assertThrows(IllegalArgumentException.class, () -> service.findById(2));
    }
    
    private TurmaCurso createTurmaCursoWithValidValues(){
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
                LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 15), 
                curso);
        
        List<EstudantesMatriculados> matriculas = new ArrayList();
        matriculas.add(new EstudantesMatriculados(LocalDate.now(), turma, estudante));
        
        turma.setEstudantesMatriculadoses(matriculas);
        return turma;
    }
}
