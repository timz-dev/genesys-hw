import SauceDemo.Credential;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utilities {
    public static Credential parseCredentials() {
        ObjectMapper mapper = new ObjectMapper();
        Credential credential = null;
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/credential.json")));
            credential =  mapper.readValue(json, Credential.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        return credential;
    }
}
