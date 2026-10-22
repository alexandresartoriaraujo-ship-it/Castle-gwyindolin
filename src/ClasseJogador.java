public enum ClasseJogador {
    ESTUDANTE(85, 85),
    PADRE(70, 100),
    DETETIVE(100, 70);

    private int vidaInicial;
    private int sanidadeInicial;

    ClasseJogador(int vidaInicial, int sanidadeInicial) {
        this.vidaInicial = vidaInicial;
        this.sanidadeInicial = sanidadeInicial;
    }

    public int getVidaInicial() {
        return vidaInicial;
    }

    public int getSanidadeInicial() {
        return sanidadeInicial;
    }
}