# Castle Gwyindolin

Jogo de horror psicológico em modo texto (console), feito em **Java** para a disciplina de Programação Orientada a Objetos. Inspirado em jogos de RPG Maker como *Ao Oni*, *Mogeko Castle* e *Yume Nikki*.

## Sobre o jogo

O jogador acorda dentro de um casarão abandonado sem lembrar como chegou lá. Para escapar, ele precisa explorar as salas, pegar itens, abrir portas trancadas e sobreviver às criaturas que vivem na casa.

Além da **vida**, o jogador tem uma **sanidade** que cai com o tempo e com os ataques de certos inimigos. Os inimigos são perigosos, mas podem ser enfrentados: cada classe de personagem tem um valor de ataque diferente, e lutar também custa turnos. O final que o jogador consegue depende da sanidade com que ele chega à saída.

## Objetivo

Chegar ao **Portão** do casarão. Para isso é preciso pegar a *chave do porão*, entrar no Porão, pegar a *chave final* e usá-la para abrir o Portão.

## Como executar

Requisitos: Java instalado (JDK).

1. Coloque todos os arquivos `.java` na mesma pasta.
2. Compile:

```
javac -encoding UTF-8 *.java
```

3. Execute:

```
java Main
```

Se você usa o IntelliJ, basta abrir o projeto e rodar a classe `Main`.

## Como jogar

No começo, digite seu nome e escolha uma classe:

| Classe     | Vida | Sanidade | Ataque |
|------------|------|----------|--------|
| Estudante  | 85   | 85       | 15     |
| Padre      | 70   | 100      | 10     |
| Detetive   | 100  | 70       | 20     |

Depois, use o menu:

| Opção | Ação                                   | Gasta turno? |
|-------|----------------------------------------|--------------|
| 1     | Ir para outra sala                     | Sim          |
| 2     | Pegar um item da sala                  | Sim          |
| 3     | Usar um item do inventário             | Sim          |
| 4     | Examinar um item do inventário         | Não          |
| 5     | Ver inventário                         | Não          |
| 6     | Ver status                             | Não          |
| 7     | Olhar a sala                           | Não          |
| 8     | Atacar um inimigo da sala              | Sim          |
| 0     | Sair                                   | Não          |

Os nomes dos itens e as direções (`norte`, `sul`, `leste`, `oeste`) são digitados em letras minúsculas e sem acento. Exemplo: `chave do porao`.

## Regras

- O jogo é por turnos. A cada turno gasto, os inimigos da sala atacam o jogador e a sanidade cai 2 pontos.
- A **Sombra** (Cozinha) tira 10 de sanidade por turno. Ela tem 20 de vida.
- O **Oni** (Porão) tira 20 de vida por turno. Ele tem 30 de vida.
- Um inimigo derrotado some da sala e deixa de atacar.
- A *bandagem* devolve 30 de vida. O *amuleto* devolve 25 de sanidade. Vida e sanidade nunca passam de 100.
- O Porão e o Portão ficam trancados e só abrem com a chave certa no inventário.
- Vida ou sanidade em 0 significa **Game Over**.

## Finais

Ao chegar no Portão, o final depende da sanidade:

| Sanidade   | Final                                   |
|------------|-----------------------------------------|
| 50 ou mais | **Final bom** – A Luz da Manhã          |
| 25 a 49    | **Final normal** – Cicatrizes           |
| Abaixo de 25 | **Final ruim** – O Castelo Não Te Larga |

## Mapa

```
            [Portão]
                |
[Cozinha] - [Corredor] - [Porão]
                |
            [Quarto]
```

O jogador começa no Quarto. O Corredor liga todos os outros locais.

## Estrutura do projeto

São 17 arquivos, cada um com uma classe, interface ou enum.

**Personagens**
- `Personagem` (abstrata): nome, vida, receber dano e curar.
- `Jogador`: sanidade, ataque, classe escolhida e inventário.
- `Inimigo` (abstrata): obriga todo inimigo a ter o método `agir`.
- `Oni` e `Sombra`: inimigos com ataques diferentes.
- `ClasseJogador` (enum): Estudante, Padre e Detetive, com vida, sanidade e ataque iniciais.

**Itens**
- `Item` (abstrata): nome e descrição.
- `Chave`, `Remedio` e `Amuleto`: tipos de item.
- `Inventario`: guarda os itens do jogador.
- `Interagivel` (interface): o que pode ser examinado.
- `Usavel` (interface): o que pode ser usado pelo jogador.

**Estrutura do jogo**
- `Sala`: itens, inimigos, saídas e tranca.
- `Mapa`: cria as salas e liga uma na outra.
- `Jogo`: menu, turnos, combate e finais.
- `Main`: inicia o jogo.

## Conceitos de OO utilizados

| Conceito | Onde aparece |
|----------|--------------|
| **Classes e objetos** | Cada elemento do jogo é uma classe: `Jogador`, `Oni`, `Sala`, `Item` etc. |
| **Atributos e métodos** | Ex.: `Jogador` tem `sanidade` e os métodos `perderSanidade()` e `recuperarSanidade()`. |
| **Encapsulamento** | Atributos `private`, com métodos que controlam as mudanças (a vida nunca fica negativa nem passa de 100). |
| **Construtores** | `Jogador(nome, classe)` chama `super()` de `Personagem`. `Mapa()` monta o casarão inteiro. |
| **Herança** | `Jogador` e `Inimigo` herdam de `Personagem`. `Oni` e `Sombra` herdam de `Inimigo`. `Chave`, `Remedio` e `Amuleto` herdam de `Item`. |
| **Abstração** | `Personagem`, `Inimigo` e `Item` são abstratas: não existe um "inimigo genérico" no jogo. |
| **Polimorfismo** | No `passarTurno()`, o `Jogo` chama `inimigo.agir(jogador)` sem saber se é `Oni` ou `Sombra`. Cada um age de um jeito. |
| **Interfaces** | `Usavel` só é assinada por `Remedio` e `Amuleto` (a `Chave` não pode ser usada). `Interagivel` define o que pode ser examinado. |
| **Composição** | `Jogador` possui um `Inventario`. `Mapa` possui as `Sala`s. `Jogo` possui o `Mapa`. |
| **Agregação** | `Inventario` e `Sala` guardam `Item`s, e `Sala` guarda `Inimigo`s. Esses objetos existem independentemente de onde estão. |
| **Enum** | `ClasseJogador` define um conjunto fixo de classes e os valores iniciais de cada uma. |

## Combate

O combate foi mantido simples. Ao escolher a opção 8, o jogador ataca o primeiro inimigo da sala e causa o dano da sua classe usando `receberDano()`, que já existe em `Personagem`. Se a vida do inimigo chegar a 0, ele é removido da sala pelo método `removerInimigo()`. Depois do ataque, os inimigos que sobraram agem normalmente.

## Possíveis melhorias

- Trocar o atributo `estado` (número) por um enum `EstadoJogo`.
- Criar NPCs com diálogos e missões.
- Criar um chefe (`Boss`) como nova subclasse de `Inimigo`.
- Criar uma classe `Batalha` para separar o combate do `Jogo`.
- Permitir escolher qual inimigo atacar quando houver mais de um na sala.
- Adicionar mais salas, itens e puzzles.

## Autor

[Alexandre Sartori de araújo] – [Materia:Back-end] – [Professora:Fiama]
