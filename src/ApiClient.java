import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private String keyApi;

    public ApiClient(String keyApi) {
        this.keyApi = keyApi;

    }

    public String getBody() {
        String corpo = "";
        for (int pages = 1; pages < 13; pages++) // para eu poder percorrer varias paginas e juntar tudo num json
        {
            // Minha key de acesso a API

            String apiURL = String.format(
                    "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=pt-BR-US&page=%s&sort_by=vote_average.desc&without_genres=99,10755&vote_count.gte=200",
                    pages);

            try // GET
            {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiURL))
                        .header("accept", "application/json")
                        .header("Authorization",
                                String.format("Bearer %s", getKeyApi()))
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                // Enviar a solicitação e receber a resposta
                HttpResponse<String> response = HttpClient.newHttpClient().send(request,
                        HttpResponse.BodyHandlers.ofString());

                // Colocar o valor da resposta dentro de uma variavel
                corpo += response.body();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return corpo;
    }

    // metodos especiais
    public String getKeyApi() {
        return keyApi;
    }

    public void setKeyApi(String keyApi) {
        this.keyApi = keyApi;
    }

}
