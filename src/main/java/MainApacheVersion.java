import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kadavr95 on 22.01.2018.
 */

public class MainApacheVersion {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i=0; i<999; i++) {
            CloseableHttpClient clientGlobal = HttpClients.createDefault();
            HttpGet getGlobal = new HttpGet("https://hsreplay.net/archetypes/"+i+"/");
            try (CloseableHttpResponse respGlobal = clientGlobal.execute(getGlobal)) {
                String htmlGlobal = EntityUtils.toString(respGlobal.getEntity());
                Pattern pGlobal = Pattern.compile("<title>(.*) - HSReplay.net</title>");
                Matcher mGlobal = pGlobal.matcher(htmlGlobal);
                while (mGlobal.find()) {
                        System.out.format("%3d: %-36s  -  %s%n",i,"https://hsreplay.net/archetypes/"+i+"/",
                                mGlobal.group(1).replaceAll("&#39;","'"));
                }
            }
            clientGlobal.close();
        }
    }
}
