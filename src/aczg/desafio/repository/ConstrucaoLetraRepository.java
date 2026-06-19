package aczg.desafio.repository;

import aczg.desafio.domain.TextoMusical;

public class ConstrucaoLetraRepository implements LetraRepository {

    private static final String TITULO  = "Construção";
    private static final String ARTISTA = "Chico Buarque";

    private static final String LETRA =
        "Amou daquela vez como se fosse a última\n" +
        "Beijou sua mulher como se fosse a última\n" +
        "Ergueu-se da cadeira como se fosse a última\n" +
        "E guardou o seu sorriso com carinho trágico\n" +
        "\n" +
        "Pôs o pé no chão firme como se fosse sábado\n" +
        "Trouxe os pés à calçada como se fosse um príncipe\n" +
        "Conduziu sua balança como se fosse um pêndulo\n" +
        "E ergueu no patamar quatro paredes sólidas\n" +
        "\n" +
        "Tijolo com tijolo num desenho lógico\n" +
        "Seus olhos embotados de cimento e lágrimas\n" +
        "Sentou pra descansar como se fosse sábado\n" +
        "Comeu feijão com arroz como se fosse um príncipe\n" +
        "\n" +
        "Bebeu e soluçou como se fosse um pêndulo\n" +
        "Dançou e gargalhou como se fosse o último\n" +
        "Recostou na ribanceira como se fosse máquina\n" +
        "E flutuou no ar como se fosse um pássaro\n" +
        "\n" +
        "E não voou como se fosse um pássaro\n" +
        "Caiu como se fosse um bêbado\n" +
        "Caiu como se fosse um frágil\n" +
        "Caiu como se fosse um sólido\n" +
        "\n" +
        "E o João que passou um susto\n" +
        "E a Joana que gritou\n" +
        "E o Paulo que havia uns cinco\n" +
        "E a Maria que sorriu\n" +
        "\n" +
        "Amou daquela vez como se fosse a última\n" +
        "Beijou sua mulher como se fosse a última\n" +
        "Ergueu-se da cadeira como se fosse a última\n" +
        "E guardou o seu sorriso com carinho trágico\n" +
        "\n" +
        "Pôs o pé no chão firme como se fosse sábado\n" +
        "Trouxe os pés à calçada como se fosse um príncipe\n" +
        "Conduziu sua balança como se fosse um pêndulo\n" +
        "E ergueu no patamar quatro paredes sólidas\n" +
        "\n" +
        "Tijolo com tijolo num desenho lógico\n" +
        "Seus olhos embotados de cimento e lágrimas\n" +
        "Sentou pra descansar como se fosse sábado\n" +
        "Comeu feijão com arroz como se fosse um príncipe\n" +
        "\n" +
        "Bebeu e soluçou como se fosse um pêndulo\n" +
        "Dançou e gargalhou como se fosse o último\n" +
        "Recostou na ribanceira como se fosse máquina\n" +
        "E flutuou no ar como se fosse um pássaro\n" +
        "\n" +
        "E não voou como se fosse um pássaro\n" +
        "Caiu como se fosse um bêbado\n" +
        "Caiu como se fosse um frágil\n" +
        "Caiu como se fosse um sólido\n" +
        "\n" +
        "E o João que passou um susto\n" +
        "E a Joana que gritou\n" +
        "E o Paulo que havia uns cinco\n" +
        "E a Maria que sorriu\n" +
        "\n" +
        "Amou daquela vez como se fosse a última\n" +
        "Beijou sua mulher como se fosse a última\n" +
        "Ergueu-se da cadeira como se fosse a última\n" +
        "E guardou o seu sorriso com carinho trágico\n" +
        "\n" +
        "Pôs o pé no chão firme como se fosse sábado\n" +
        "Trouxe os pés à calçada como se fosse um príncipe\n" +
        "Conduziu sua balança como se fosse um pêndulo\n" +
        "E ergueu no patamar quatro paredes sólidas\n" +
        "\n" +
        "Tijolo com tijolo num desenho lógico\n" +
        "Seus olhos embotados de cimento e lágrimas\n" +
        "Sentou pra descansar como se fosse sábado\n" +
        "Comeu feijão com arroz como se fosse um príncipe\n" +
        "\n" +
        "Bebeu e soluçou como se fosse um pêndulo\n" +
        "Dançou e gargalhou como se fosse o último\n" +
        "Recostou na ribanceira como se fosse máquina\n" +
        "E flutuou no ar como se fosse um pássaro\n" +
        "\n" +
        "E não voou como se fosse um pássaro\n" +
        "Caiu como se fosse um bêbado\n" +
        "Caiu como se fosse um frágil\n" +
        "Caiu como se fosse um sólido";

    @Override
    public TextoMusical buscarLetra() {
        return new TextoMusical(TITULO, ARTISTA, LETRA);
    }
}
