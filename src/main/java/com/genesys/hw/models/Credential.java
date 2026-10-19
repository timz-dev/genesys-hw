package com.genesys.hw.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;

public class Credential {
    private String username;
    private String password;

    public static Credential forUser(String user) {
        try (InputStream json = Credential.class.getResourceAsStream("/credential.json")) {
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
