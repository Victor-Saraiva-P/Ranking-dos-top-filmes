import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class teste {
    public static void main(String[] args) {
        String input = "The quick brown fox jumps over the lazy dog.";
        String regex = "\"name\":\\s*\"([^\"]+)\"";  // Padrão para palavras

        // Compilar a expressão regular em um objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Criar um Matcher para a sequência de caracteres
        Matcher matcher = pattern.matcher(input);

        // Utilizar o Matcher para encontrar todas as palavras
        while (matcher.find()) {
            System.out.println("Found word: " + matcher.group());
        }
    }
}
