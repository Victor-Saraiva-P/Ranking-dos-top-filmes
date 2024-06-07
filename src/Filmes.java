import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.SimpleElementVisitor14;

public class Filmes {
    // PROPRIEDADES
    private List<String> titles;
    private List<String> posterPath;
    private List<String> voteAverage;

    // metodos comuns
    public Filmes(String json) {
        setTitles(pegarAtributo("title", json));
        setposterPath(pegarAtributo("poster_path", json));
        setvoteAverage(pegarAtributo("vote_average", json));
    }

    public List<String> pegarAtributo(String propriedade, String json) {

        String regex;
        if (propriedade == "vote_average") // checa se o valor se trata de um vote_average que é tratado diferente por
                                           // ser um número
        {
            regex = String.format("\"%s\":\\s*(\\d+(\\.\\d{1,3})?)", propriedade);
        } else // outro, seguir normalmente o código
        {
            regex = String.format("\"%s\":\\s*\"([^\"]+)\"", propriedade);
        }

        // Compilar a expressão regular em um objeto Pattern
        Pattern pattern = Pattern.compile(regex);

        // Criar um Matcher para a sequência de caracteres
        Matcher matcher = pattern.matcher(json);

        // Utilizar o Matcher para encontrar todas as palavras
        List<String> listaResultado = new ArrayList<>(); // criar uma lista que vai conter os matchs

        while (matcher.find()) {
            String expressao;

            if (propriedade == "poster_path") // verificar se trata dum link para colocar o hipertexto antes
            {
                expressao = "https://image.tmdb.org/t/p/" + matcher.group();
            } else {
                expressao = (String) matcher.group();
            }

            expressao = expressao.replace("\"", ""); // retirar as aspas
            expressao = expressao.replace(String.format("%s:", propriedade), ""); // retirar o inicion como ex:"title: o
                                                                                  // filme", para apenas "o filme"
            listaResultado.add(expressao);
        }
        System.out.println(listaResultado.size());
        return listaResultado;
    }

    public void mostrarTitulos() {
        List<String> listaResultado = getTitles();

        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    public void mostrarPosters() {
        List<String> listaResultado = getposterPath();

        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    public void mostrarMedias() {
        List<String> listaResultado = getvoteAverage();

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

    public List<String> getposterPath() {
        return posterPath;
    }

    public void setposterPath(List<String> posterPath) {
        this.posterPath = posterPath;
    }

    public List<String> getvoteAverage() {
        return voteAverage;
    }

    public void setvoteAverage(List<String> voteAverage) {
        this.voteAverage = voteAverage;
    }

}
