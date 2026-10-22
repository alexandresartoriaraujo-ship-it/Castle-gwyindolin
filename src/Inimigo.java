public abstract class Inimigo extends Personagem {

    public Inimigo(String nome, int vida) {
        super(nome, vida);
    }

    public abstract void agir(Jogador jogador);
}