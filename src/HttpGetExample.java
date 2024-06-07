import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpGetExample {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
        // Minha key de acesso a API
        String key = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNTg2YmYyNzE4MWJkMGU1ZTMzOGM0ZjQyNzg1OTNkZSIsInN1YiI6IjY2NjI1MDVkOGZmYjZiMjE0M2YyMzg3ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.HeHFhJGkLyWsgaNLz8Yv173ANAxFMCGtrr4kN-zSSGw";

        String apiURL = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=pt-BR-US&page=1&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200";

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
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            
            // Imprimir o status e o corpo da resposta
            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
