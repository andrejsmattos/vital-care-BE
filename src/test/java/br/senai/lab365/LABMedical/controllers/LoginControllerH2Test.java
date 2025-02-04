//package br.senai.lab365.LABMedical.controllers;
//
//import br.senai.lab365.LABMedical.dtos.login.LoginRequest;
//import br.senai.lab365.LABMedical.dtos.login.LoginResponse;
//import br.senai.lab365.LABMedical.dtos.usuario.RedefinicaoSenhaRequest;
//import br.senai.lab365.LABMedical.dtos.usuario.UsuarioPreRegistroRequest;
//import br.senai.lab365.LABMedical.dtos.usuario.UsuarioPreRegistroResponse;
//import br.senai.lab365.LABMedical.entities.Perfil;
//import br.senai.lab365.LABMedical.entities.Usuario;
//import br.senai.lab365.LABMedical.repositories.PacienteRepository;
//import br.senai.lab365.LABMedical.repositories.PerfilRepository;
//import br.senai.lab365.LABMedical.repositories.UsuarioRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Set;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.Matchers.*;
//import org.springframework.test.context.ActiveProfiles;
//
//
//
//@WebMvcTest(controllers = LoginController.class)
//
//public class LoginControllerH2Test {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private PerfilRepository perfilRepository;
//
//    @Autowired
//    private JwtEncoder jwtEncoder;
//
//    @Autowired
//    private PacienteRepository pacienteRepository;
//
//    @BeforeEach
//    void setUp() {
//        usuarioRepository.deleteAll();
//        perfilRepository.deleteAll();
//
//        // Configura um perfil no banco de dados H2
//        Perfil perfilPaciente = new Perfil();
//        perfilPaciente.setNomePerfil("PACIENTE");
//        perfilRepository.save(perfilPaciente);
//
//        // Carrega o perfil salvo para garantir que esteja no contexto atual do JPA
//        perfilPaciente = perfilRepository.findByNomePerfil("PACIENTE");
//
//        // Cria e salva o usuário com o perfil criado
//        Usuario usuario = new Usuario();
//        usuario.setEmail("usuario@teste.com");
//        usuario.setPassword("senha123");
//        usuario.setNome("Usuario Teste");
//        usuario.setPerfilList(Set.of(perfilPaciente)); // adiciona o perfil carregado
//
//        usuarioRepository.save(usuario);
//    }
//
//
//
//    @Test
//    void testGeraToken_Integration() throws Exception {
//        // Cria o LoginRequest JSON para enviar ao endpoint
//        String loginRequestJson = "{\"email\":\"usuario@teste.com\", \"password\":\"senha123\"}";
//
//        // Executa o request de login e valida a resposta
//        mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequestJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").isNotEmpty())
//                .andExpect(jsonPath("$.email", is("usuario@teste.com")))
//                .andExpect(jsonPath("$.nome", is("Usuario Teste")));
//    }
//
//    @Test
//    void testCadastraUsuario_Integration() throws Exception {
//        // Cria o JSON para o registro de pré-cadastro
//        String preRegistroJson = """
//            {
//                "email": "novo@teste.com",
//                "nomePerfil": "PACIENTE",
//                "senha": "novaSenha123"
//            }
//            """;
//
//        // Executa o request de cadastro de novo usuário
//        mockMvc.perform(post("/usuarios/pre-registro")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(preRegistroJson))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.email", is("novo@teste.com")))
//                .andExpect(jsonPath("$.senhaComMascara", containsString("nova")));
//    }
//
//    @Test
//    void testRedefinicaoSenha_Integration() throws Exception {
//        // JSON para redefinição de senha
//        String redefinicaoSenhaJson = """
//            {
//                "password": "novaSenha123"
//            }
//            """;
//
//        // Redefine a senha para o usuário existente
//        mockMvc.perform(post("/usuarios/email/usuario@teste.com/redefinir-senha")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(redefinicaoSenhaJson))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.email", is("usuario@teste.com")))
//                .andExpect(jsonPath("$.senhaComMascara", containsString("nova")));
//    }
//}
