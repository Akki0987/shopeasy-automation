package com.shopeasy.utils;

import com.shopeasy.pojo.UserDetails;
import org.apache.commons.io.FileUtils;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class JsonDataReader {

    public static List<UserDetails> getJsonDataToPojoObject(String jsonfLocation) throws IOException {

        File jsonPath = new File(jsonfLocation);
        String jsonContent = FileUtils.readFileToString(jsonPath, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<UserDetails> users = mapper.readValue(jsonContent, new TypeReference<List<UserDetails>>() {
        });

        return users;
    }

    public static List<HashMap<String, String>> getJsonDataToHashmap(String jsonLocation) throws IOException {

        File jsonPath = new File(jsonLocation);
        String jsonContent = FileUtils.readFileToString(jsonPath, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> users = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });

        return users;
    }
}
