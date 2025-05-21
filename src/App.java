import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Cifra de Vigenère ===");
        System.out.print("Digite a chave de criptografia (somente letras): ");
        String chave = scanner.nextLine().toUpperCase();
        scanner.close();
        Mecanismo mec = new Mecanismo(chave);
        String entradaTexto = "src/Textos/entradaTexto.txt";
        String saidaCripto = "src/Textos/saidaCripto.txt";
        mec.executarCripto(entradaTexto, saidaCripto);
        String saidaDecripto = "src/Textos/saidaDecripto.txt";
        mec.executarDecripto(saidaCripto, saidaDecripto);
    }
}