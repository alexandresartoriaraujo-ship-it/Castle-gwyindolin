import java.util.ArrayList;

public class Sala {
    private String nome;
    private String descricao;
    private String chaveNecessaria;
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Inimigo> inimigos = new ArrayList<>();
    private Sala norte;
    private Sala sul;
    private Sala leste;
    private Sala oeste;

    public Sala(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public Sala getNorte() {
        return norte;
    }

    public void setNorte(Sala norte) {
        this.norte = norte;
    }

    public Sala getSul() {
        return sul;
    }

    public void setSul(Sala sul) {
        this.sul = sul;
    }

    public Sala getLeste() {
        return leste;
    }

    public void setLeste(Sala leste) {
        this.leste = leste;
    }

    public Sala getOeste() {
        return oeste;
    }

    public void setOeste(Sala oeste) {
        this.oeste = oeste;
    }

    public String getChaveNecessaria() {
        return chaveNecessaria;
    }

    public void trancar(String chave) {
        chaveNecessaria = chave;
    }

    public void destrancar() {
        chaveNecessaria = null;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public Item removerItem(String nomeItem) {
        for (Item i : itens) {
            if (i.getNome().equals(nomeItem)) {
                itens.remove(i);
                return i;
            }
        }
        return null;
    }

    public void adicionarInimigo(Inimigo inimigo) {
        inimigos.add(inimigo);
    }

    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }

    public void mostrar() {
        System.out.println();
        System.out.println("Local: " + nome);
        System.out.println(descricao);
        for (Item i : itens) {
            System.out.println("Você vê: " + i.getNome());
        }
        for (Inimigo e : inimigos) {
            System.out.println("Cuidado! " + e.getNome() + " está aqui.");
        }
        if (norte != null) {
            System.out.println("Saída ao norte");
        }
        if (sul != null) {
            System.out.println("Saída ao sul");
        }
        if (leste != null) {
            System.out.println("Saída ao leste");
        }
        if (oeste != null) {
            System.out.println("Saída ao oeste");
        }
    }
}