import java.util.Scanner;

public class PasseioDoCavalo {

    static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
    static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
    static int boardSize;
    static int[][] board;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o tamanho do tabuleiro (n para tabuleiro n x n): ");
        boardSize = scanner.nextInt();
        board = new int[boardSize][boardSize];

        // Inicializa o tabuleiro com -1 (não visitado)
        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                board[i][j] = -1;

        // Começa da posição (0,0)
        board[0][0] = 0;

        if (resolver(0, 0, 1)) {
            imprimirCaminho();
        } else {
            System.out.println("Nenhuma solução encontrada.");
        }
        scanner.close();
    }

    // Backtracking para resolver o passeio
    public static boolean resolver(int x, int y, int moveCount) {
        if (moveCount == boardSize * boardSize)
            return true;

        for (int i = 0; i < 8; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (ehValido(nextX, nextY)) {
                board[nextX][nextY] = moveCount;
                if (resolver(nextX, nextY, moveCount + 1))
                    return true;
                board[nextX][nextY] = -1; // backtrack
            }
        }
        return false;
    }

    // Verifica se a posição é válida
    public static boolean ehValido(int x, int y) {
        return x >= 0 && y >= 0 && x < boardSize && y < boardSize && board[x][y] == -1;
    }

    // Converte índice para notação de xadrez (ex: (0,0) → a1)
    public static String paraNotacaoXadrez(int x, int y) {
        return "" + (char) ('a' + y) + (x + 1);
    }

    // Imprime o caminho em notação de xadrez
    public static void imprimirCaminho() {
        int totalMovimentos = boardSize * boardSize;
        String[] passos = new String[totalMovimentos];

        for (int i = 0; i < boardSize; i++)
            for (int j = 0; j < boardSize; j++)
                passos[board[i][j]] = paraNotacaoXadrez(i, j);

        System.out.println("Solução encontrada:");
        for (int i = 0; i < passos.length; i++) {
            System.out.printf("%2d: %s\n", i + 1, passos[i]);
        }
    }
}
