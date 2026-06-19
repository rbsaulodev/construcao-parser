package aczg.desafio.service;

import aczg.desafio.domain.ResultadoAnalise;
import aczg.desafio.domain.TextoMusical;
import aczg.desafio.repository.LetraRepository;

import java.util.Objects;

public class AnalisadorMusicalService {

    private final LetraRepository letraRepository;
    private final TextoParserService parserService;

    public AnalisadorMusicalService(LetraRepository letraRepository, TextoParserService parserService) {
        this.letraRepository = Objects.requireNonNull(letraRepository);
        this.parserService   = Objects.requireNonNull(parserService);
    }

    public ResultadoAnalise analisar() {
        TextoMusical musica  = letraRepository.buscarLetra();
        String conteudo = musica.getConteudo();

        return ResultadoAnalise.builder()
            .palavrasComCedilhaOuTilde(parserService.getPalavrasComCedilhaOuTilde(conteudo))
            .frasesRepetidas (parserService.getFrasesRepetidas(conteudo))
            .palavrasComDitongo (parserService.getPalavrasComDitongo(conteudo))
            .palavrasComTritongo (parserService.getPalavrasComTritongo(conteudo))
            .palavrasComHiato (parserService.getPalavrasComHiato(conteudo))
            .frasesComQuatroPalavras (parserService.getFrasesComQuatroPalavras(conteudo))
            .palavrasProparoxitonas (parserService.getPalavrasProparoxitonas(conteudo))
            .textoSemPlural (parserService.getTextoSemPlural(conteudo))
            .build();
    }

    public TextoMusical buscarMusica() {
        return letraRepository.buscarLetra();
    }
}
