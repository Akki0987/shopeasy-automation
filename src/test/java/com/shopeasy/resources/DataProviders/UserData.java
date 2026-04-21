package com.shopeasy.resources.DataProviders;

import com.shopeasy.pojo.UserDetails;
import com.shopeasy.utils.JsonDataReader;
import com.shopeasy.utils.Utilities;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class UserData{

    @DataProvider(name = "getDataFromJsonUsingPOJO")
    public Object[][] getPojoObject() throws IOException {

        String path = System.getProperty("user.dir") + "/src/test/java/com/shopeasy/resources/testData/users.json";
        List<UserDetails> users = JsonDataReader.getJsonDataToPojoObject(path);

        Object [][] data = new Object[users.size()][1];
        for(int i=0;  i<users.size(); i++)
        {
            data[i][0] = users.get(i);
        }

        return data;
    }

    @DataProvider
    public Object[][] getHashmapObject() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/java/com/shopeasy/resources/testData/users.json";
        List<HashMap<String, String>> users = JsonDataReader.getJsonDataToHashmap(path);

        Object [][] data = new Object[users.size()][1];
        for(int i=0;  i<users.size(); i++)
        {
            data[i][0] = users.get(i);
        }

        return data;
    }

}
