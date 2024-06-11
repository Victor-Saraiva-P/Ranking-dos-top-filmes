import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class RankingDosTopFilmes {
    public static void main(String[] args) throws Exception {

        String json = "";
        for (int pages = 1; pages < 13; pages++) // para eu poder percorrer varias paginas e juntar tudo num json
        {
            // Minha key de acesso a API
            String key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNTg2YmYyNzE4MWJkMGU1ZTMzOGM0ZjQyNzg1OTNkZSIsInN1YiI6IjY2NjI1MDVkOGZmYjZiMjE0M2YyMzg3ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HeHFhJGkLyWsgaNLz8Yv173ANAxFMCGtrr4kN-zSSGw";

            String apiURL = String.format(
                    "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=pt-BR-US&page=%s&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200",
                    pages);

            try // GET
            {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiURL))
                        .header("accept", "application/json")
                        .header("Authorization",
                                String.format("Bearer %s", key))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                // Enviar a solicitação e receber a resposta
                HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                        HttpResponse.BodyHandlers.ofString());

                // Colocar o valor da resposta dentro de uma variavel
                json += response.body();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // pegar elementos dos filmes para o objeto filme
        AtributosFilme jsonTratado = new AtributosFilme(json);
        List<String> titles = jsonTratado.getTitles();
        List<String> urlImages = jsonTratado.getposterPath();
        List<String> rating = jsonTratado.getvoteAverage();
        List<String> year = jsonTratado.getYear();

        List<Filme> filmes = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            Filme filme = new Filme(titles.get(i), urlImages.get(i), Float.parseFloat(rating.get(i)),
                    Integer.parseInt(year.get(i)));
            filmes.add(filme);
        }

        // criar um html com os filmes
        HTMLGenerator htmlArquivo = new HTMLGenerator("filmes.html");
        htmlArquivo.generate(filmes);
        html.close
    }
}
