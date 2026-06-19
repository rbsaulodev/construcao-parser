package aczg.desafio.domain;

import java.util.List;
import java.util.Map;

public final class ResultadoAnalise {

    private final List<String> palavrasComCedilhaOuTilde;
    private final Map<String, Long> frasesRepetidas;
    private final List<String> palavrasComDitongo;
    private final List<String> palavrasComTritongo;
    private final List<String> palavrasComHiato;
    private final List<String> frasesComQuatroPalavras;
    private final List<String> palavrasProparoxitonas;
    private final List<String> textoSemPlural;

    private ResultadoAnalise(Builder builder) {
        this.palavrasComCedilhaOuTilde  = List.copyOf(builder.palavrasComCedilhaOuTilde);
        this.frasesRepetidas = Map.copyOf(builder.frasesRepetidas);
        this.palavrasComDitongo = List.copyOf(builder.palavrasComDitongo);
        this.palavrasComTritongo = List.copyOf(builder.palavrasComTritongo);
        this.palavrasComHiato = List.copyOf(builder.palavrasComHiato);
        this.frasesComQuatroPalavras = List.copyOf(builder.frasesComQuatroPalavras);
        this.palavrasProparoxitonas = List.copyOf(builder.palavrasProparoxitonas);
        this.textoSemPlural = List.copyOf(builder.textoSemPlural);
    }

    public static Builder builder() { return new Builder(); }

    public List<String> getPalavrasComCedilhaOuTilde() {
        return palavrasComCedilhaOuTilde;
    }

    public Map<String, Long> getFrasesRepetidas() {
        return frasesRepetidas;
    }

    public List<String> getPalavrasComDitongo() {
        return palavrasComDitongo;
    }

    public List<String> getPalavrasComTritongo() {
        return palavrasComTritongo;
    }

    public List<String> getPalavrasComHiato() {
        return palavrasComHiato;
    }

    public List<String> getFrasesComQuatroPalavras() {
        return frasesComQuatroPalavras;
    }

    public List<String> getPalavrasProparoxitonas() {
        return palavrasProparoxitonas;
    }

    public List<String> getTextoSemPlural() {
        return textoSemPlural;
    }

    public static final class Builder {
        private List<String> palavrasComCedilhaOuTilde = List.of();
        private Map<String, Long> frasesRepetidas = Map.of();
        private List<String> palavrasComDitongo = List.of();
        private List<String> palavrasComTritongo = List.of();
        private List<String> palavrasComHiato = List.of();
        private List<String> frasesComQuatroPalavras = List.of();
        private List<String> palavrasProparoxitonas = List.of();
        private List<String> textoSemPlural = List.of();

        public Builder palavrasComCedilhaOuTilde(List<String> v)  {
            this.palavrasComCedilhaOuTilde = v;
            return this;
        }

        public Builder frasesRepetidas(Map<String, Long> v) {
            this.frasesRepetidas = v;
            return this;
        }

        public Builder palavrasComDitongo(List<String> v) {
            this.palavrasComDitongo = v;
            return this;
        }

        public Builder palavrasComTritongo(List<String> v) {
            this.palavrasComTritongo = v;
            return this;
        }

        public Builder palavrasComHiato(List<String> v) {
            this.palavrasComHiato = v;
            return this;
        }

        public Builder frasesComQuatroPalavras(List<String> v) {
            this.frasesComQuatroPalavras = v;
            return this;
        }

        public Builder palavrasProparoxitonas(List<String> v) {
            this.palavrasProparoxitonas = v;
            return this;
        }

        public Builder textoSemPlural(List<String> v) {
            this.textoSemPlural = v;
            return this;
        }

        public ResultadoAnalise build() {
            return new ResultadoAnalise(this);
        }
    }
}
