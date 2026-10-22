public abstract class Item implements Interagivel {
    private String nome;
    private String descricao;

    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String examinar() {
        return descricao;
    }
}