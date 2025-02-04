package br.senai.lab365.LABMedical.repositories;

import br.senai.lab365.LABMedical.entities.Consulta;
import br.senai.lab365.LABMedical.repositories.ConsultaRepository;
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
class ConsultaRepositoryTest {

    @Autowired
    private ConsultaRepository consultaRepository;

    @BeforeEach
    void setUp() {
        // Limpa o repositório antes de cada teste
        consultaRepository.deleteAll();
    }


    @Test
    void findByPacienteId_DeveRetornarListaVaziaParaPacienteSemConsultas() {
        // Arrange
        Long pacienteId = 1L;

        // Act
        List<Consulta> consultas = consultaRepository.findByPacienteId(pacienteId);

        // Assert
        assertThat(consultas).isEmpty();
    }

    @Test
    void findByPacienteId_DeveRetornarListaVaziaParaPacienteInexistente() {
        // Act
        List<Consulta> consultas = consultaRepository.findByPacienteId(999L); // ID que não existe

        // Assert
        assertThat(consultas).isEmpty();
    }
}
