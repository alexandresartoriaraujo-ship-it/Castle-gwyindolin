public enum ClasseJogador {
    ESTUDANTE(85, 85, 15),
    PADRE(70, 100, 10),
    DETETIVE(100, 70, 20);

    private int vidaInicial;
    private int sanidadeInicial;
    private int ataque;

    ClasseJogador(int vidaInicial, int sanidadeInicial, int ataque) {
        this.vidaInicial = vidaInicial;
        this.sanidadeInicial = sanidadeInicial;
        this.ataque = ataque;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getSanidadeInicial() {
        return sanidadeInicial;
    }

    public int getAtaque() {
        return ataque;
    }
}