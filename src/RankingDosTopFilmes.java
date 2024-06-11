import java.util.List;

public class RankingDosTopFilmes {
    public static void main(String[] args) throws Exception {

        // key para api
        String key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNTg2YmYyNzE4MWJkMGU1ZTMzOGM0ZjQyNzg1OTNkZSIsInN1YiI6IjY2NjI1MDVkOGZmYjZiMjE0M2YyMzg3ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HeHFhJGkLyWsgaNLz8Yv173ANAxFMCGtrr4kN-zSSGw";

        String jsoString = new ApiClient(key).getBody();

        List<Filme> filmes = new JsonParser(jsoString).parse();
        // criar um html com os filmes
        HTMLGenerator htmlArquivo = new HTMLGenerator("filmes.html");
        htmlArquivo.generate(filmes);
    }
}
