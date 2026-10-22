public class Remedio extends Item implements Usavel {
    private int cura;

    public Remedio(String nome, String descricao, int cura) {
        super(nome, descricao);
        this.cura = cura;
    }

    @Override
    public void usar(Jogador jogador) {
        jogador.curar(cura);
        System.out.println("Você usou " + getNome() + " (+" + cura + " de vida)");
    }
}