import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Mecanismo {

    private BufferedReader reader;
    private ArrayList<String> bufferPrimario;
    private String chaveCifra;

    public void CarregarArquivo(String caminhoCompleto) {
        CarregarBufferPrimario(caminhoCompleto);
        ProcessarBufferPrimario();
    }

    private void CarregarBufferPrimario(String caminhoCompleto) {
        this.reader = null;
        try {
            this.reader = new BufferedReader(new FileReader(caminhoCompleto));
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public void ProcessarBufferPrimario() {
        this.bufferPrimario = new ArrayList<>();
        try {
            String linha;
            while ((linha = this.reader.readLine()) != null) {
                bufferPrimario.add(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Erro ao fechar o arquivo: " + e.getMessage());
                }
            }
        }
    }

    public void imprimirArquivo(String caminhoArquivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + caminhoArquivo);
        }
    }

    public Mecanismo(String chave) {
        this.chaveCifra = chave;
    }

    public void executarCripto(String arquivoTexto, String arquivoCripto) {

        CarregarArquivo(arquivoTexto); // armazena no buffer primario

        ArrayList<String> bufferCripto = new ArrayList<>();
        VigenereCipher cifra = new VigenereCipher();

        for (String linha : bufferPrimario) {
            StringBuilder novaLinha = new StringBuilder();

            novaLinha.append(cifra.criptografar(linha, chaveCifra)); // parte em que criptografa cada linha

            bufferCripto.add(novaLinha.toString());
        }

        // escreve no arquivo texto saidacripto.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoCripto))) {
            for (String linha : bufferCripto) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        System.out.println("=====ENTRADA DE TEXTO=====");
        imprimirArquivo("src/Textos/entradaTexto.txt");
        System.out.println("=====SAIDA DO TEXTO CRIPTOGRAFADO=====");
        imprimirArquivo("src/Textos/saidaCripto.txt");
    }

    public void executarDecripto(String entradaCripto, String saidaDecripto) {
        CarregarArquivo(entradaCripto); // armazena no buffer primário

        ArrayList<String> bufferDecripto = new ArrayList<>();
        VigenereCipher cifra = new VigenereCipher(); // cria uma instância de VigenereCipher

        for (String linha : bufferPrimario) {
            String decriptografada = cifra.descriptografar(linha, chaveCifra);
            bufferDecripto.add(decriptografada);
        }

        // escreve no arquivo texto saidaDecripto.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saidaDecripto))) {
            for (String linha : bufferDecripto) {
                writer.write(linha);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo: " + e.getMessage());
        }

        System.out.println("=====SAIDA DE TEXTO DESCRIPTOGRAFADO=====");
        imprimirArquivo("src/Textos/saidaDecripto.txt");
    }
}