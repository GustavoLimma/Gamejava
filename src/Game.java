import java.io.*;
import java.util.*;

public class Game {
    private List<Player> jogadores;
    private Map<String, Integer> ranking;
    private Dado dado1;
    private Dado dado2;

    public Game() {
        jogadores = new ArrayList<>();
        ranking = carregarRanking();
        dado1 = new Dado();
        dado2 = new Dado();
    }

    public void adicionarJogador(String nome, int aposta) throws IllegalArgumentException {
        if (jogadores.size() >= 11) {
            throw new IllegalArgumentException("Número máximo de jogadores atingido.");
        }

        for (Player p : jogadores) {
            if (p.getAposta() == aposta) {
                throw new IllegalArgumentException("Aposta duplicada não permitida.");
            }
        }

        jogadores.add(new Player(nome, aposta));
    }

    public void jogar() {
        int resultado = dado1.rolar() + dado2.rolar();
        System.out.println("Número sorteado: " + resultado);

        boolean vencedorEncontrado = false;

        for (Player p : jogadores) {
            if (p.getAposta() == resultado) {
                System.out.println("Vencedor: " + p.getNome());
                p.incrementarVitorias();
                atualizarRanking(p);
                vencedorEncontrado = true;
                break;
            }
        }

        if (!vencedorEncontrado) {
            System.out.println("A máquina venceu!");
        }
    }

    private Map<String, Integer> carregarRanking() {
        Map<String, Integer> ranking = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ranking.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                ranking.put(partes[0], Integer.parseInt(partes[1]));
            }
        } catch (IOException e) {
            System.out.println("Ranking inicial vazio.");
        }
        return ranking;
    }

    private void atualizarRanking(Player p) {
        ranking.put(p.getNome(), p.getVitorias());
        salvarRanking();
    }

    private void salvarRanking() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt"))) {
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar o ranking.");
        }
    }

    public void exibirRanking() {
        System.out.println("TOP 5 Ranking:");
        ranking.entrySet().stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .forEach(entry -> System.out.println(entry.getKey() + " - Vitórias: " + entry.getValue()));
    }
}
