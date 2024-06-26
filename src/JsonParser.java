import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    // PROPRIEDADES
    private String json;
    private List<String> titles;
    private List<String> posterPath;
    private List<String> voteAverage;
    private List<String> year;

    // metodo construtor
    public JsonParser(String json) {
        setTitles(pegarAtributo("title", json));
        setPosterPath(pegarAtributo("poster_path", json));
        setVoteAverage(pegarAtributo("vote_average", json));
        setYear(pegarAtributo("release_date", json));
    }

    // metodos comuns
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
                expressao = "https://image.tmdb.org/t/p/w200" + matcher.group();
            } else {
                expressao = (String) matcher.group();
            }

            expressao = expressao.replace("\"", ""); // retirar as aspas
            expressao = expressao.replace(String.format("%s:", propriedade), ""); // retirar o inicio como ex:"title: o
                                                                                  // filme", para apenas "o filme"

            if (propriedade == "release_date") // se for o ano do filme pegar apenas os quatro primeiros char, que
                                               // representam o ano do filme
            {
                expressao = expressao.substring(0, 4);
            }

            listaResultado.add(expressao);
        }
        return listaResultado;
    }

    public List<Filme> parse() {
        List<String> titles = getTitles();
        List<String> urlImages = getPosterPath();
        List<String> rating = getVoteAverage();
        List<String> year = getYear();

        List<Filme> filmes = new ArrayList<>(); // armazenar dados em uma lista de filmes
        for (int i = 0; i < titles.size(); i++) {
            Filme filme = new Filme(titles.get(i), urlImages.get(i), Float.parseFloat(rating.get(i)),
                    Integer.parseInt(year.get(i)));
            filmes.add(filme);
        }
        return filmes;
    }

    public void mostrarAno() {
        List<String> listaResultado = getYear();

        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    public void mostrarPosters() {
        List<String> listaResultado = getPosterPath();

        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    public void mostrarMedias() {
        List<String> listaResultado = getVoteAverage();

        for (String item : listaResultado) {
            System.out.println(item);
        }
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<String> getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(List<String> posterPath) {
        this.posterPath = posterPath;
    }

    public List<String> getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(List<String> voteAverage) {
        this.voteAverage = voteAverage;
    }

    public List<String> getYear() {
        return year;
    }

    public void setYear(List<String> year) {
        this.year = year;
    }

    // metodos especiais

}
