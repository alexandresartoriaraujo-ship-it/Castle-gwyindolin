public class Oni extends Inimigo {

    public Oni() {
        super("Oni", 30);
    }

    @Override
    public void agir(Jogador jogador) {
        System.out.println("O Oni te agarra! (-20 de vida)");
        jogador.receberDano(20);
    }
}