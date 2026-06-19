package test.aczg.repository;

import aczg.desafio.domain.TextoMusical;
import aczg.desafio.repository.ConstrucaoLetraRepository;
import aczg.desafio.repository.LetraRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ConstrucaoLetraRepository")
class ConstrucaoLetraRepositoryTest {

    private LetraRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ConstrucaoLetraRepository();
    }

    @Test
    @DisplayName("buscarLetra() não retorna nulo")
    void deveBuscarLetraSemRetornarNulo() {
        assertNotNull(repository.buscarLetra());
    }

    @Test
    @DisplayName("buscarLetra() retorna título correto")
    void deveTerTituloCorreto() {
        TextoMusical musica = repository.buscarLetra();
        assertEquals("Construção", musica.getTitulo());
    }

    @Test
    @DisplayName("buscarLetra() retorna artista correto")
    void deveTerArtistaCorreto() {
        TextoMusical musica = repository.buscarLetra();
        assertEquals("Chico Buarque", musica.getArtista());
    }

    @Test
    @DisplayName("buscarLetra() retorna conteúdo não vazio")
    void deveTerConteudoNaoVazio() {
        TextoMusical musica = repository.buscarLetra();
        assertFalse(musica.getConteudo().isBlank());
    }

    @Test
    @DisplayName("Conteúdo contém trecho reconhecível da letra")
    void deveTerConteudoReconhecivel() {
        TextoMusical musica = repository.buscarLetra();
        assertTrue(musica.getConteudo().contains("Amou daquela vez"),
                   "Deve conter o primeiro verso");
        assertTrue(musica.getConteudo().contains("Caiu como se fosse um sólido"),
                   "Deve conter verso do final");
    }
}
