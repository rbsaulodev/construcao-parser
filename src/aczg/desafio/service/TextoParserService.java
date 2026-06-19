package aczg.desafio.service;

import java.util.List;
import java.util.Map;

public interface TextoParserService {
    List<String> getPalavrasComCedilhaOuTilde(String texto);
    Map<String, Long> getFrasesRepetidas(String texto);
    List<String> getPalavrasComDitongo(String texto);
    List<String> getPalavrasComTritongo(String texto);
    List<String> getPalavrasComHiato(String texto);
    List<String> getFrasesComQuatroPalavras(String texto);
    List<String> getPalavrasProparoxitonas(String texto);
    List<String> getTextoSemPlural(String texto);
}
