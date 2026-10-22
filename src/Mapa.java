public class Mapa {
    private Sala salaInicial;
    private Sala portao;

    public Mapa() {
        Sala quarto = new Sala("Quarto", "Um quarto escuro. A cama está cheia de manchas.");
        Sala corredor = new Sala("Corredor", "Um corredor longo. As luzes piscam.");
        Sala cozinha = new Sala("Cozinha", "Panelas enferrujadas e um cheiro podre no ar.");
        Sala porao = new Sala("Porão", "Úmido e silencioso demais.");
        portao = new Sala("Portão", "O ar frio da noite. Você está livre!");

        quarto.setNorte(corredor);
        corredor.setSul(quarto);
        corredor.setLeste(cozinha);
        corredor.setOeste(porao);
        corredor.setNorte(portao);
        cozinha.setOeste(corredor);
        porao.setLeste(corredor);

        porao.trancar("chave do porao");
        portao.trancar("chave final");

        quarto.adicionarItem(new Remedio("bandagem", "Uma bandagem velha, mas limpa.", 30));
        cozinha.adicionarItem(new Chave("chave do porao", "Uma chave pequena e enferrujada."));
        cozinha.adicionarItem(new Amuleto("amuleto", "Um amuleto quente ao toque.", 25));
        porao.adicionarItem(new Chave("chave final", "Uma chave dourada."));

        cozinha.adicionarInimigo(new Sombra());
        porao.adicionarInimigo(new Oni());

        salaInicial = quarto;
    }

    public Sala getSalaInicial() {
        return salaInicial;
    }

    public Sala getPortao() {
        return portao;
    }
}