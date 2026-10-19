import SauceDemo.Credential;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;

public class Utilities {
    public static Credential parseCredentials(String user) {
        try (InputStream json = Utilities.class.getResourceAsStream("/credential.json")) {
            if (json == null) {
                throw new IllegalStateException("credential.json not found on classpath");
            }
            Credential credential = new ObjectMapper().readValue(json, new TypeReference<Map<String, Credential>>() {}).get(user);
            if (credential == null) {
                throw new IllegalArgumentException("No credentials for user: " + user);
            }
            return credential;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
