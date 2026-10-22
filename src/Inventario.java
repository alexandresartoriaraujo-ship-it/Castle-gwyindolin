import java.util.ArrayList;

public class Inventario {
    private ArrayList<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        itens.add(item);
    }

    public void remover(Item item) {
        itens.remove(item);
    }

    public Item buscar(String nome) {
        for (Item i : itens) {
            if (i.getNome().equals(nome)) {
                return i;
            }
        }
        return null;
    }

    public void listar() {
        if (itens.size() == 0) {
            System.out.println("Inventário vazio.");
        } else {
            System.out.println("Inventário:");
            for (Item i : itens) {
                System.out.println("- " + i.getNome());
            }
        }
    }
}