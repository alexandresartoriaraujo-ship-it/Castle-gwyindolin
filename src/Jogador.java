public class Jogador extends Personagem {
    private int sanidade;
    private int ataque;
    private ClasseJogador classe;
    private Inventario inventario;

    public Jogador(String nome, ClasseJogador classe) {
        super(nome, classe.getVidaInicial());
        this.classe = classe;
        sanidade = classe.getSanidadeInicial();
        ataque = classe.getAtaque();
        inventario = new Inventario();
    }

    public ClasseJogador getClasse() {
        return classe;
    }

    public int getSanidade() {
        return sanidade;
    }

    public int getAtaque() {
        return ataque;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void perderSanidade(int valor) {
        sanidade = sanidade - valor;
        if (sanidade < 0) {
            sanidade = 0;
        }
    }

    public void recuperarSanidade(int valor) {
        sanidade = sanidade + valor;
        if (sanidade > 100) {
            sanidade = 100;
        }
    }
}