package logic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import domain.User;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class WriterClass {

    private final static String userDir = System.getProperty("user.dir");
    private final static String filename = "test.json";
    private final static Path path = Paths.get(String.format("%s/%s", userDir, filename));
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<String, User> users = new HashMap<>();

    public void writeToFile(User user) {
        boolean exists = Files.exists(path);
        if (exists) {
            try (Writer writer = new java.io.FileWriter(filename)) {
                users.put(user.getEmail(), user);
                gson.toJson(users, writer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (Writer writer = new java.io.FileWriter(filename)) {
                gson.toJson(Collections.singleton(user), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rewriteFile(Map<String, User> users) {

        File file = new File(filename);
        file.delete();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!users.isEmpty()) {
            for (Map.Entry<String, User> pair : users.entrySet()) {
                writeToFile(pair.getValue());
            }
        }

    }

    public void readFromFile() {
        try (Reader reader = new FileReader(filename)) {
            Type hashMapType = new TypeToken<Map<String, User>>() {
            }.getType();
            users = gson.fromJson(new JsonReader(reader), hashMapType);

            if (users == null) {
                users = new HashMap<>();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUserInformation(Map<String, User> users) {
        if (!users.isEmpty()) {
            for (Map.Entry<String, User> pair : users.entrySet()) {
                Object value = pair.getValue();
                System.out.println(value);
                System.out.println("--*--*--*--");
            }
        } else {
            System.out.println("List of users is empty!");
        }


    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
