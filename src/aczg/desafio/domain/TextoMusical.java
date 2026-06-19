package aczg.desafio.domain;

public final class TextoMusical {

    private final String titulo;
    private final String artista;
    private final String conteudo;

    public TextoMusical(String titulo, String artista, String conteudo) {
        this.titulo = titulo;
        this.artista = artista;
        this.conteudo = conteudo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public String getConteudo() {
        return conteudo;
    }
}
