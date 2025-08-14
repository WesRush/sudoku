package br.com.dio;

import br.com.dio.model.Board;
import br.com.dio.model.Space;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom; // Import para gerar números aleatórios
import java.util.stream.Stream;

import static br.com.dio.util.BoardTemplate.BOARD_TEMPLATE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toMap;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    private static Board board;
    private final static int BOARD_LIMIT = 9;

    // --- LÓGICA DE TABULEIROS MÚLTIPLOS ---

    // Tabuleiro 1 (O que já tínhamos)
    private static final String[] BOARD_1 = {
            "0,0;5,true", "0,1;3,false", "0,2;4,false", "0,3;6,false", "0,4;7,true", "0,5;8,false", "0,6;9,false", "0,7;1,false", "0,8;2,false",
            "1,0;6,true", "1,1;7,false", "1,2;2,false", "1,3;1,true", "1,4;9,true", "1,5;5,false", "1,6;3,false", "1,7;4,false", "1,8;8,false",
            "2,0;1,false", "2,1;9,true", "2,2;8,false", "2,3;3,false", "2,4;4,false", "2,5;2,false", "2,6;5,false", "2,7;6,true", "2,8;7,false",
            "3,0;8,false", "3,1;5,false", "3,2;9,false", "3,3;7,false", "3,4;6,false", "3,5;1,false", "3,6;4,true", "3,7;2,false", "3,8;3,true",
            "4,0;4,true", "4,1;2,false", "4,2;6,false", "4,3;8,false", "4,4;5,false", "4,5;3,false", "4,6;7,false", "4,7;9,true", "4,8;1,false",
            "5,0;7,false", "5,1;1,false", "5,2;3,false", "5,3;9,false", "5,4;2,false", "5,5;4,false", "5,6;8,false", "5,7;5,false", "5,8;6,true",
            "6,0;9,false", "6,1;6,true", "6,2;1,false", "6,3;5,false", "6,4;3,false", "6,5;7,false", "6,6;2,true", "6,7;8,false", "6,8;4,false",
            "7,0;2,false", "7,1;8,false", "7,2;7,false", "7,3;4,true", "7,4;1,true", "7,5;9,false", "7,6;6,false", "7,7;3,true", "7,8;5,false",
            "8,0;3,false", "8,1;4,false", "8,2;5,false", "8,3;2,false", "8,4;8,false", "8,5;6,false", "8,6;1,false", "8,7;7,false", "8,8;9,true"
    };

    // Tabuleiro 2 (Um novo tabuleiro para variar)
    private static final String[] BOARD_2 = {
            "0,0;8,true", "0,1;1,false", "0,2;2,false", "0,3;9,false", "0,4;5,true", "0,5;4,false", "0,6;7,false", "0,7;6,false", "0,8;3,false",
            "1,0;4,false", "1,1;3,false", "1,2;5,true", "1,3;6,false", "1,4;7,false", "1,5;8,false", "1,6;1,false", "1,7;9,true", "1,8;2,false",
            "2,0;6,false", "2,1;7,false", "2,2;9,false", "2,3;1,false", "2,4;2,false", "2,5;3,false", "2,6;5,false", "2,7;8,false", "2,8;4,true",
            "3,0;1,true", "3,1;2,false", "3,2;8,false", "3,3;5,false", "3,4;3,false", "3,5;6,true", "3,6;9,false", "3,7;4,false", "3,8;7,false",
            "4,0;3,false", "4,1;6,false", "4,2;4,false", "4,3;7,false", "4,4;9,false", "4,5;1,false", "4,6;2,false", "4,7;5,false", "4,8;8,false",
            "5,0;9,false", "5,1;5,false", "5,2;7,true", "5,3;8,false", "5,4;4,false", "5,5;2,false", "5,6;3,false", "5,7;1,true", "5,8;6,false",
            "6,0;2,true", "6,1;8,false", "6,2;1,false", "6,3;3,false", "6,4;6,false", "6,5;5,false", "6,6;4,false", "6,7;7,false", "6,8;9,true",
            "7,0;5,false", "7,1;9,true", "7,2;3,false", "7,3;4,false", "7,4;8,false", "7,5;7,true", "7,6;6,false", "7,7;2,false", "7,8;1,false",
            "8,0;7,false", "8,1;4,false", "8,2;6,false", "8,3;2,false", "8,4;1,true", "8,5;9,false", "8,6;8,false", "8,7;3,false", "8,8;5,true"
    };

    // Uma lista contendo todos os tabuleiros disponíveis.
    // Você pode adicionar BOARD_3, BOARD_4, etc. aqui.
    private static final List<String[]> PREMADE_BOARDS = List.of(BOARD_1, BOARD_2);

    /**
     * Este método escolhe e retorna um dos tabuleiros pré-definidos aleatoriamente.
     * @return Um array de String com a configuração de um tabuleiro.
     */
    private static String[] getRandomBoardSetup() {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, PREMADE_BOARDS.size());
        return PREMADE_BOARDS.get(randomIndex);
    }

    // --- FIM DA LÓGICA DE TABULEIROS MÚLTIPLOS ---


    public static void main(String[] args) {
        // A lógica de 'args' não é mais necessária para iniciar um jogo padrão,
        // mas pode ser mantida para um modo de "debug" ou "teste" se desejar.
        // Por simplicidade, vamos focar na lógica do menu.

        var option = -1;
        while (true){
            System.out.println("\nSelecione uma das opções a seguir");
            System.out.println("1 - Iniciar um novo Jogo");
            System.out.println("2 - Colocar um novo número");
            System.out.println("3 - Remover um número");
            System.out.println("4 - Visualizar jogo atual");
            System.out.println("5 - Verificar status do jogo");
            System.out.println("6 - Limpar jogo");
            System.out.println("7 - Finalizar jogo");
            System.out.println("8 - Sair");

            option = scanner.nextInt();

            switch (option){
                // --- MUDANÇA PRINCIPAL AQUI ---
                case 1 -> {
                    // Pega um tabuleiro aleatório da nossa lista
                    String[] randomBoard = getRandomBoardSetup();
                    // Converte o array para o formato Map que o 'startGame' espera
                    final var positions = Stream.of(randomBoard)
                            .collect(toMap(
                                    k -> k.split(";")[0],
                                    v -> v.split(";")[1]
                            ));
                    startGame(positions);
                }
                case 2 -> inputNumber();
                case 3 -> removeNumber();
                case 4 -> showCurrentGame();
                case 5 -> showGameStatus();
                case 6 -> clearGame();
                case 7 -> finishGame();
                case 8 -> System.exit(0);
                default -> System.out.println("Opção inválida, selecione uma das opções do menu");
            }
        }
    }

    private static void startGame(final Map<String, String> positions) {
        if (nonNull(board)){
            // Adicionei uma opção para o usuário decidir se quer começar um novo jogo
            System.out.println("Um jogo já está em andamento. Deseja iniciar um novo e perder o progresso atual? (sim/não)");
            String confirm = scanner.next();
            if (!confirm.equalsIgnoreCase("sim")) {
                return;
            }
        }

        // Esta verificação não é mais estritamente necessária aqui, pois a lógica do menu
        // agora garante que 'positions' nunca estará vazio. Removê-la simplifica o código.
        /*
        if (positions.isEmpty()) {
            System.out.println("ERRO: O jogo não pode ser iniciado sem a configuração do tabuleiro.");
            return;
        }
        */

        List<List<Space>> spaces = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            spaces.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++) {
                var positionConfig = positions.get("%s,%s".formatted(i, j));
                var configParts = positionConfig.split(","); // Evita chamar split duas vezes
                var expected = Integer.parseInt(configParts[0]);
                var fixed = Boolean.parseBoolean(configParts[1]);
                var currentSpace = new Space(expected, fixed);
                spaces.get(i).add(currentSpace);
            }
        }

        board = new Board(spaces);
        System.out.println("O jogo está pronto para começar!");
        showCurrentGame(); // Mostra o tabuleiro assim que o jogo começa
    }


    // ... (O restante dos seus métodos: inputNumber, removeNumber, etc. permanecem iguais)
    private static void inputNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Informe a coluna (0-8) em que o número será inserido:");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha (0-8) em que o número será inserido:");
        var row = runUntilGetValidNumber(0, 8);
        System.out.printf("Informe o número (1-9) que vai entrar na posição [%s,%s]:\n", col, row);
        var value = runUntilGetValidNumber(1, 9);
        if (!board.changeValue(col, row, value)){
            System.out.printf("A posição [%s,%s] tem um valor fixo e não pode ser alterada.\n", col, row);
        } else {
            showCurrentGame();
        }
    }

    private static void removeNumber() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Informe a coluna (0-8) do número a ser removido:");
        var col = runUntilGetValidNumber(0, 8);
        System.out.println("Informe a linha (0-8) do número a ser removido:");
        var row = runUntilGetValidNumber(0, 8);
        if (!board.clearValue(col, row)){
            System.out.printf("A posição [%s,%s] tem um valor fixo e não pode ser removida.\n", col, row);
        } else {
            System.out.printf("Valor da posição [%s,%s] removido.\n", col, row);
            showCurrentGame();
        }
    }

    private static void showCurrentGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        var args = new Object[81];
        var argPos = 0;
        for (int i = 0; i < BOARD_LIMIT; i++) {
            for (int j = 0; j < BOARD_LIMIT; j++) {
                // A lógica de preenchimento dos argumentos estava um pouco confusa.
                // Esta forma é mais direta para preencher linha por linha.
                Integer actual = board.getSpaces().get(j).get(i).getActual();
                args[argPos++] = (isNull(actual) || actual == 0) ? " " : actual;
            }
        }
        System.out.println("Seu jogo se encontra da seguinte forma:");
        System.out.printf((BOARD_TEMPLATE) + "%n", args);
    }

    private static void showGameStatus() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.printf("O jogo atualmente se encontra no status %s\n", board.getStatus().getLabel());
        if(board.hasErrors()){
            System.out.println("O jogo contém erros. Verifique o tabuleiro.");
        } else {
            System.out.println("O jogo não contém erros.");
        }
    }

    private static void clearGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        System.out.println("Tem certeza que deseja limpar seu jogo e perder todo seu progresso? (sim/não)");
        var confirm = scanner.next();
        while (!confirm.equalsIgnoreCase("sim") && !confirm.equalsIgnoreCase("não")){
            System.out.println("Informe 'sim' ou 'não'");
            confirm = scanner.next();
        }

        if(confirm.equalsIgnoreCase("sim")){
            board.reset();
            System.out.println("Seu progresso foi reiniciado.");
            showCurrentGame();
        }
    }

    private static void finishGame() {
        if (isNull(board)){
            System.out.println("O jogo ainda não foi iniciado. Selecione a opção 1.");
            return;
        }

        if (board.gameIsFinished()){
            System.out.println("Parabéns, você concluiu o jogo!");
            showCurrentGame();
            board = null; // Libera o tabuleiro para um novo jogo
        } else if (board.hasErrors()) {
            System.out.println("Seu jogo contém erros, verifique seu board e ajuste-o.");
        } else {
            System.out.println("Você ainda precisa preencher algum espaço.");
        }
    }


    private static int runUntilGetValidNumber(final int min, final int max){
        int current = -1;
        while(true) {
            try {
                current = Integer.parseInt(scanner.next());
                if (current >= min && current <= max) {
                    return current;
                } else {
                    System.out.printf("Número inválido. Informe um número entre %s e %s:\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Entrada inválida. Informe um número inteiro entre %s e %s:\n", min, max);
            }
        }
    }
}