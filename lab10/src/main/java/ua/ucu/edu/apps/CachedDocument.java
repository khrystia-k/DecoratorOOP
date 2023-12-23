package ua.ucu.edu.apps;


import lombok.SneakyThrows;

import java.sql.*;

public class CachedDocument extends SmartDocument {

    private static final String JDBC_URL = "jdbc:sqlite:decorator_oop.db";

    public CachedDocument(String gcsPath) {
        super(gcsPath);
    }


    public String parse() {
        String cachedText = getCachedText();
        if (cachedText != null) {
            return cachedText; // Return cached text if available
        }

        // If not in the cache, use Google Cloud Vision API
        String text = super.parse();

        // Save the result to the local cache
        saveToCache(text);

        return text;
    }

    @SneakyThrows
    private String getCachedText() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            //table with columns "gcs_path" and "text"
            String query = "SELECT text FROM documents WHERE gcs_path = '" + getGcsPath() + "'";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                return resultSet.getString("text");
            }
        }

        return null; // Return null if not found in the cache
    }

    @SneakyThrows
    private void saveToCache(String text) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             Statement statement = connection.createStatement()) {

            //  table with columns "gcs_path" and "text"
            String query = "INSERT INTO documents (gcs_path, text) VALUES ('" +
                    getGcsPath() + "', '" + text + "')";
            statement.executeUpdate(query);
        }
    }

}

