package br.senai.lab365.LABMedical.services;

import br.senai.lab365.LABMedical.entities.Perfil;
import br.senai.lab365.LABMedical.repositories.PerfilRepository;
import br.senai.lab365.LABMedical.services.PerfilService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerfilServiceTest {

    @Mock
    private PerfilRepository perfilRepository;

    @InjectMocks
    private PerfilService perfilService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criaPerfilSeNaoExiste_QuandoPerfilNaoExiste() {
        String nomePerfil = "ADMIN";

        // Configurar o repositório para retornar null, simulando que o perfil não existe
        when(perfilRepository.findByNomePerfil(nomePerfil)).thenReturn(null);

        perfilService.criaPerfilSeNãoExiste(nomePerfil);

        // Verificar se o método save foi chamado uma vez
        verify(perfilRepository, times(1)).save(any(Perfil.class));
    }

    @Test
    void criaPerfilSeNaoExiste_QuandoPerfilJaExiste() {
        String nomePerfil = "ADMIN";
        Perfil perfilExistente = new Perfil();
        perfilExistente.setNomePerfil(nomePerfil);

        // Configurar o repositório para retornar um perfil existente
        when(perfilRepository.findByNomePerfil(nomePerfil)).thenReturn(perfilExistente);

        perfilService.criaPerfilSeNãoExiste(nomePerfil);

        // Verificar que o método save não foi chamado, já que o perfil já existe
        verify(perfilRepository, never()).save(any(Perfil.class));
    }

    @Test
    void criaPerfil_CriaPerfisPadraoSeNaoExistem() {
        // Configurar o repositório para retornar null para todos os perfis, simulando que eles não existem
        when(perfilRepository.findByNomePerfil("ADMIN")).thenReturn(null);
        when(perfilRepository.findByNomePerfil("MÉDICO")).thenReturn(null);
        when(perfilRepository.findByNomePerfil("PACIENTE")).thenReturn(null);

        perfilService.criaPerfil();

        // Verificar que o método save foi chamado três vezes para criar os perfis
        verify(perfilRepository, times(3)).save(any(Perfil.class));
    }

    @Test
    void criaPerfil_NaoCriaPerfisSeJaExistem() {
        // Configurar o repositório para retornar perfis existentes para todos
        when(perfilRepository.findByNomePerfil("ADMIN")).thenReturn(new Perfil());
        when(perfilRepository.findByNomePerfil("MÉDICO")).thenReturn(new Perfil());
        when(perfilRepository.findByNomePerfil("PACIENTE")).thenReturn(new Perfil());

        perfilService.criaPerfil();

        // Verificar que o método save nunca foi chamado, pois todos os perfis já existem
        verify(perfilRepository, never()).save(any(Perfil.class));
    }
}


