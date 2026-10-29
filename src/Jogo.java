import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
    private Jogador jogador;
    private Mapa mapa;
    private Sala salaAtual;
    private int estado;

    public Jogo() {
        mapa = new Mapa();
        salaAtual = mapa.getSalaInicial();
        estado = 0;
    }

    public void iniciar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Castle Gwyindolin ===");

        System.out.println("Qual é o seu nome?");
        String nome = sc.nextLine();

        System.out.println("Escolha sua classe:");
        System.out.println("1 - Estudante (vida 85, sanidade 85, ataque 15)");
        System.out.println("2 - Padre (vida 70, sanidade 100, ataque 10)");
        System.out.println("3 - Detetive (vida 100, sanidade 70, ataque 20)");
        String escolha = sc.nextLine();

        ClasseJogador classe = ClasseJogador.ESTUDANTE;
        if (escolha.equals("2")) {
            classe = ClasseJogador.PADRE;
        } else if (escolha.equals("3")) {
            classe = ClasseJogador.DETETIVE;
        }

        jogador = new Jogador(nome, classe);

        System.out.println("Você acorda sem lembrar como chegou aqui, " + jogador.getNome() + ".");
        salaAtual.mostrar();

        while (estado == 0) {
            mostrarMenu();
            String opcao = sc.nextLine();

            if (opcao.equals("1")) {
                System.out.println("Direção (norte, sul, leste, oeste):");
                String direcao = sc.nextLine();
                ir(direcao);
            } else if (opcao.equals("2")) {
                System.out.println("Nome do item:");
                String item = sc.nextLine();
                pegar(item);
            } else if (opcao.equals("3")) {
                System.out.println("Nome do item:");
                String item = sc.nextLine();
                usar(item);
            } else if (opcao.equals("4")) {
                System.out.println("Nome do item:");
                String item = sc.nextLine();
                examinar(item);
            } else if (opcao.equals("5")) {
                jogador.getInventario().listar();
            } else if (opcao.equals("6")) {
                System.out.println(jogador.getNome() + " (" + jogador.getClasse() + ")");
                System.out.println("Vida: " + jogador.getVida() + " | Sanidade: " + jogador.getSanidade() + " | Ataque: " + jogador.getAtaque());
            } else if (opcao.equals("7")) {
                salaAtual.mostrar();
            } else if (opcao.equals("8")) {
                atacar();
            } else if (opcao.equals("0")) {
                estado = 3;
            } else {
                System.out.println("Opção inválida.");
            }

            verificarFim();
        }

        if (estado == 1) {
            System.out.println();
            System.out.println("FINAL BOM - A Luz da Manhã");
            System.out.println("Você atravessa o portão com a mente intacta.");
            System.out.println("O sol nasce e o Castelo fica para trás, como um pesadelo distante.");
        } else if (estado == 4) {
            System.out.println();
            System.out.println("FINAL NORMAL - Cicatrizes");
            System.out.println("Você escapa, mas as sombras continuam te seguindo em sonho.");
            System.out.println("Algumas noites, você ainda ouve alguém sussurrar o seu nome.");
        } else if (estado == 5) {
            System.out.println();
            System.out.println("FINAL RUIM - O Casarão Não Te Larga");
            System.out.println("Você cruza o portão, mas sua mente ficou lá dentro.");
            System.out.println("Ao olhar para trás, vê a si mesmo na janela, sorrindo.");
        } else if (estado == 2) {
            System.out.println();
            System.out.println("GAME OVER - Você não resistiu...");
        } else {
            System.out.println();
            System.out.println("Você desistiu, como um covarde.");
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("Vida: " + jogador.getVida() + " | Sanidade: " + jogador.getSanidade());
        System.out.println("1-Ir  2-Pegar  3-Usar  4-Examinar  5-Inventário  6-Status  7-Olhar  8-Atacar  0-Sair");
        System.out.print("O que você faz? ");
    }

    private void ir(String direcao) {
        Sala destino = null;

        if (direcao.equals("norte")) {
            destino = salaAtual.getNorte();
        } else if (direcao.equals("sul")) {
            destino = salaAtual.getSul();
        } else if (direcao.equals("leste")) {
            destino = salaAtual.getLeste();
        } else if (direcao.equals("oeste")) {
            destino = salaAtual.getOeste();
        }

        if (destino == null) {
            System.out.println("Não dá pra ir por aí.");
        } else {
            String chave = destino.getChaveNecessaria();
            if (chave == null) {
                entrar(destino);
            } else {
                Item item = jogador.getInventario().buscar(chave);
                if (item == null) {
                    System.out.println("Está trancado. Precisa de: " + chave);
                } else {
                    System.out.println("Você abriu a porta com a " + chave + ".");
                    destino.destrancar();
                    entrar(destino);
                }
            }
        }
    }

    private void entrar(Sala destino) {
        salaAtual = destino;
        salaAtual.mostrar();
        if (salaAtual == mapa.getPortao()) {
            escolherFinal();
        } else {
            passarTurno();
        }
    }

    private void escolherFinal() {
        int sanidade = jogador.getSanidade();

        if (sanidade >= 50) {
            estado = 1;
        } else if (sanidade >= 25) {
            estado = 4;
        } else {
            estado = 5;
        }
    }

    private void pegar(String nome) {
        Item item = salaAtual.removerItem(nome);
        if (item == null) {
            System.out.println("Não tem isso aqui.");
        } else {
            jogador.getInventario().adicionar(item);
            System.out.println("Você pegou: " + item.getNome());
            passarTurno();
        }
    }

    private void usar(String nome) {
        Item item = jogador.getInventario().buscar(nome);
        if (item == null) {
            System.out.println("Você não tem esse item.");
        } else if (item instanceof Usavel) {
            Usavel usavel = (Usavel) item;
            usavel.usar(jogador);
            jogador.getInventario().remover(item);
            passarTurno();
        } else {
            System.out.println("Isso não pode ser usado.");
        }
    }

    private void examinar(String nome) {
        Item item = jogador.getInventario().buscar(nome);
        if (item == null) {
            System.out.println("Você não tem esse item.");
        } else {
            System.out.println(item.examinar());
        }
    }

    private void atacar() {
        ArrayList<Inimigo> inimigos = salaAtual.getInimigos();

        if (inimigos.size() == 0) {
            System.out.println("Não há ninguém para atacar aqui.");
        } else {
            Inimigo alvo = inimigos.get(0);
            alvo.receberDano(jogador.getAtaque());
            System.out.println("Você ataca " + alvo.getNome() + " e causa " + jogador.getAtaque() + " de dano.");

            if (alvo.getVida() <= 0) {
                System.out.println(alvo.getNome() + " foi derrotado!");
                salaAtual.removerInimigo(alvo);
            }
            passarTurno();
        }
    }

    private void passarTurno() {
        for (Inimigo inimigo : salaAtual.getInimigos()) {
            inimigo.agir(jogador);
        }
        jogador.perderSanidade(2);
    }

    private void verificarFim() {
        if (estado == 0) {
            if (jogador.getVida() <= 0) {
                estado = 2;
            }
            if (jogador.getSanidade() <= 0) {
                estado = 2;
            }
        }
    }
}