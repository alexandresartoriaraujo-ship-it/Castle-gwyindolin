public class Amuleto extends Item implements Usavel {
    private int forca;

    public Amuleto(String nome, String descricao, int forca) {
        super(nome, descricao);
        this.forca = forca;
    }

    @Override
    public void usar(Jogador jogador) {
        jogador.recuperarSanidade(forca);
        System.out.println("Você segura o amuleto e se acalma (+" + forca + " de sanidade)");
    }
}