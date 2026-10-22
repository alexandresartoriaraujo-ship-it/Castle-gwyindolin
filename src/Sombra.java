public class Sombra extends Inimigo {

    public Sombra() {
        super("Sombra", 999);
    }

    @Override
    public void agir(Jogador jogador) {
        System.out.println("A Sombra sussurra seu nome... (-10 de sanidade)");
        jogador.perderSanidade(10);
    }
}