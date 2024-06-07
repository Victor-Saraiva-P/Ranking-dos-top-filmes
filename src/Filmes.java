import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filmes {
    private List<String> titles;

    public Filmes(String json) {
        setTitles(pegarAtributo("nome", json));
    }

    public List<String> pegarAtributo(String propriedade, String json)
    {
        String regex = String.format("\"%s\":\\s*\"([^\"]+)\"", propriedade); // Padrão para palavras

        // Compilar a expressão regular em um objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Criar um Matcher para a sequência de caracteres
        Matcher matcher = pattern.matcher(json);

        // Utilizar o Matcher para encontrar todas as palavras
        List<String> listaResultado = new ArrayList<>(); // criar uma lista que vai conter os matchs

        while (matcher.find()) {
            String expressão = matcher.group().replace("\"", ""); // retirar as aspas
            expressão = expressão.replace(String.format("%s: ", propriedade), "");
            listaResultado.add(expressão);
        }
        return listaResultado;
    }

    public void mostrarTitulos()
    {
       List<String> listaResultado = getTitles();
        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    // metodos especiais
    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

}
