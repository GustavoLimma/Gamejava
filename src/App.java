import java.util.*;

public class App {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner option = new Scanner(System.in);
        int opt;

        do {
            System.out.println("\n=== Jogo de Dados ===");
            System.out.println("1. Exibir Ranking");
            System.out.println("2. Adicionar Jogador");
            System.out.println("3. Jogar");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opt = Integer.parseInt(option.nextLine());
             
            switch (opt) {
                case 1:
                    game.exibirRanking();
                    break;
                case 2:
                    try {
                        System.out.print("Nome do jogador: ");
                        String nome = option.nextLine();
                        System.out.print("Número apostado (1 a 12): ");
                        int aposta = option.nextInt();
                        option.nextLine(); // Consumir quebra de linha

                        game.adicionarJogador(nome, aposta);
                        System.out.println("Jogador adicionado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 3:
                    game.jogar();
                    break;
                case 4:
                    System.out.println("Encerrando o jogo. Até a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opt != 4);

        option.close();
    }
}