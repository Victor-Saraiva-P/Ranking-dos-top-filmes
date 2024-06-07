import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RankingDosTopFilmes {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
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
            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
