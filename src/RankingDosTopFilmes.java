import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RankingDosTopFilmes {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {

        // definindo variavel que vai conter no resultado
        String json = "";

        try {
            // Criando o cliente
            HttpClient client = HttpClient.newHttpClient();

            // Criando a solicitação
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:3000/tarefas"))
                    .GET()
                    .build();

            // Enviando a solicitação e obtendo a resposta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimindo o status e o corpo da resposta

            json = response.body();

            // System.out.println("Response Code: " + response.statusCode());
            // System.out.println("Response Body: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Filmes filminhos = new Filmes(json);
        filminhos.mostrarTitulos();
    }
}
