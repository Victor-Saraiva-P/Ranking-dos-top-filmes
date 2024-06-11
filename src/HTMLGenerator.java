import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HTMLGenerator {

    private PrintWriter writer;

    public HTMLGenerator(String filePath) throws Exception {
        // Ajuste aqui: Criação do PrintWriter com UTF-8
        this.writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8),
                true);
    }

    public void generate(List<Filme> filmes) {
        String comeco = """
                <!DOCTYPE html>
                <html lang="pt">
                <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Lista de Filmes</title>
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
                <link rel="stylesheet" href="style.css"> <!-- Seu arquivo CSS externo -->
                </head>
                <body>
                <div class="container mt-5">
                <h1 class="text-center">Lista de Filmes</h1>
                <ul class="list-unstyled">
                """;
        writer.println(comeco);

        for (Filme filme : filmes) {
            String meioConteudo = String.format(
                    """
                            <li class="media my-4">
                            <img src="%s" class="mr-3" alt="%s">
                            <div class="media-body">
                            <h5>%s (%d)</h5>
                            <p>Nota: %.3f</p>
                            </div>
                            </li>
                            """, filme.getPosterImagem(), filme.getTitulo(), filme.getTitulo(), filme.getAno(),
                    filme.getNota());
            writer.println(meioConteudo);
        }

        String fim = """
                </ul>
                </div>
                </body>
                </html>
                """;
        writer.println(fim);
    }
}
