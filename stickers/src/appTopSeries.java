import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.Key;
import java.util.List;
import java.util.Map;

public class appTopSeries {

    public static void main(String[] args) throws Exception {
        

        // fazer conexão HTTP e buscar os top 3 series
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeSeries = parser.parse(body);
        
        // exibir e manipular os dados 
        System.out.println("\n");
        System.out.println("\u001b[1mPopular series\u001b[m");
        System.out.println();
        for (int i = 0; i < listaDeSeries.size(); i++) {
            Map<String,String> serie = listaDeSeries.get(i);
            System.out.println("\u001b[1m Título:\u001b[m " + serie.get("title"));
            System.out.println("\u001b[1mPoster \u001b[m " + serie.get("image"));
            System.out.println("\u001b[1mimDb Rating: \u001b[m " + serie.get("imDbRating"));

            double classificacao = Double.parseDouble(serie.get("imDbRating"));
            int numeroEstrelinha = (int) classificacao;
            for (int n = 1; n <= numeroEstrelinha; n++){
                System.out.print("⭐️");
            }
            System.out.println("\n");

            System.out.println();
        }

    }
}

