public class VigenereCipher {
    public String criptografar(String texto, String chave) {
        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();
        chave = chave.toUpperCase();
        int chaveIndex = 0;
        for (int i = 0; i < texto.length(); i++) {
            char caractere = texto.charAt(i);
            if (Character.isLetter(caractere)) {
                int letraTexto = caractere - 'A';
                int letraChave = chave.charAt(chaveIndex % chave.length()) - 'A';
                char letraCifrada = (char) ((letraTexto + letraChave) % 26 + 'A');
                resultado.append(letraCifrada);
                chaveIndex++;
            } else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }

    public String descriptografar(String textoCifrado, String chave) {
        StringBuilder resultado = new StringBuilder();
        textoCifrado = textoCifrado.toUpperCase();
        chave = chave.toUpperCase();
        int chaveIndex = 0;
        for (int i = 0; i < textoCifrado.length(); i++) {
            char caractere = textoCifrado.charAt(i);
            if (Character.isLetter(caractere)) {
                int letraTexto = caractere - 'A';
                int letraChave = chave.charAt(chaveIndex % chave.length()) - 'A';
                char letraDecifrada = (char) ((letraTexto - letraChave + 26) % 26 + 'A');
                resultado.append(letraDecifrada);
                chaveIndex++;
            } else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }
}