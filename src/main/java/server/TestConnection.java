package server;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestConnection {

    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        String payload = gson.toJson(gson);

        StringEntity entity = new StringEntity(payload,
                ContentType.APPLICATION_JSON);

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("http://localhost:8090/api/submit");
        request.setEntity(entity);

        HttpResponse response = httpClient.execute(request);
        try {
            BufferedReader rd;
            InputStreamReader stream = new InputStreamReader(response.getEntity().getContent());
            rd = new BufferedReader(stream);

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            stream.close();
            System.out.println("Json: " + result.toString());
        } catch (IOException e){
        }


    }
}
