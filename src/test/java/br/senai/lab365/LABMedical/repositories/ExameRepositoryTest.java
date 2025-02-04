package br.senai.lab365.LABMedical.repositories;

import br.senai.lab365.LABMedical.entities.Exame;
import br.senai.lab365.LABMedical.repositories.ExameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ExameRepositoryTest {

    @Autowired
    private ExameRepository exameRepository;

    @BeforeEach
    void setUp() {
        // Limpa o repositório antes de cada teste
        exameRepository.deleteAll();
    }



    @Test
    void findByPacienteId_DeveRetornarListaVaziaParaPacienteSemExames() {
        // Arrange
        Long pacienteId = 1L;

        // Act
        List<Exame> exames = exameRepository.findByPacienteId(pacienteId);

        // Assert
        assertThat(exames).isEmpty();
    }

    @Test
    void findByPacienteId_DeveRetornarListaVaziaParaPacienteInexistente() {
        // Act
        List<Exame> exames = exameRepository.findByPacienteId(999L); // ID que não existe

        // Assert
        assertThat(exames).isEmpty();
    }
}
