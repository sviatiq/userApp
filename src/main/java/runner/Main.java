package runner;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import domain.Role;
import domain.User;

import javax.json.*;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    private final static String userDir = System.getProperty("user.dir");
    private final static String filename = "test.json";
    private final static Path path = Paths.get(String.format("%s/%s", userDir, filename));

    public static void main(String[] args) {
        //userInformation();
        deleteMail("aliv@s.f");
        //deleteEmail("aliv@s.f");
    }

    synchronized static void userInformation() {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Введите имя: ");
            String name = scanner.nextLine();
            System.out.print("Введите фамилию: ");
            String surname = scanner.nextLine();

            System.out.print("Введите почту: ");
            String email = scanner.nextLine();

            //Проверка почты
            for (int k = 0; k <= email.length(); k++) {
                if (email.contains("@") && email.contains(".")) {
                    continue;
                } else {
                    System.out.println("Неверная почта!");
                    System.out.print("Введите почту: ");
                    email = scanner.nextLine();
                }
            }

            System.out.print("Введите роль: ");
            String role = scanner.nextLine();

            System.out.print("Введите телефон: ");
            String cellphone = scanner.nextLine();
            System.out.println();


            //Проверка номера
            for (int j = 0; j <= cellphone.length(); j++) {
                if (cellphone != null || cellphone.substring(0, 3).equals("380") && cellphone.length() == 12) {
                    continue;
                } else {
                    System.out.print("Введен неправильный номер!");
                    System.out.print("Введите телефон: ");
                    cellphone = scanner.nextLine();
                }
            }
            setUser(name, surname, email, role, cellphone);
            }

    private static void setUser(String name, String surname, String email, String role, String cellphone) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setRole(Role.valueOf(role.toUpperCase()));
        user.setCellphone(cellphone);

        writeAndRead(user);
    }

    public static void writeAndRead(User user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        boolean exists = Files.exists(path);

        if (exists) {
            List<User> users = null;
            try (Reader reader = new FileReader(filename)) {
                Type arrayListType = new TypeToken<List<User>>() {
                }.getType();
                users = gson.fromJson(new JsonReader(reader), arrayListType);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (Writer writer = new FileWriter(filename)) {
                users.add(user);
                gson.toJson(users, writer);

            } catch (IOException e) {
                e.printStackTrace();
            }
            for (User us : users) {
                System.out.println("Name: "+ us.getName());
                System.out.println("Surname: "+ us.getSurname());
                System.out.println("Email: "+ us.getEmail());
                System.out.println("Role: " + us.getRole());
                System.out.println("Phone: "+ us.getCellphone());
                System.out.println("--*--*--*--");
            }

        } else {
            try (Writer writer = new FileWriter(filename)) {
                gson.toJson(Collections.singleton(user), writer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

//    public static void deleteEmail(String email) throws FileNotFoundException{
//        InputStream is = new FileInputStream(filename);
//        javax.json.JsonReader fileReader = Json.createReader(is);
//        JsonArray contactObj = (JsonArray)Json.createParserFactory(fileReader.readObject());
//        JsonArrayBuilder factory = Json.createArrayBuilder();
//
//        for(int i = 0; i<contactObj.size(); i++){
//            if(contactObj.getJsonObject(i).getString("email") != email){
//                JsonObjectBuilder nameBuilder = Json.createObjectBuilder();
//
//                nameBuilder.add("name", contactObj.getJsonObject(i).getJsonObject(user.getName());
//                nameBuilder.add("surname", contactObj.getJsonObject(i).getJsonObject(user.getSurname());
//                nameBuilder.add("role", contactObj.getJsonObject(i).getJsonObject(user.getRole().toString()));
//                nameBuilder.add("cellphone", contactObj.getJsonObject(i).getJsonObject(user.getCellphone()));
//
//                factory.add(nameBuilder);
//            }
//        }
//        JsonArray res = factory.build();
//
//        OutputStream os = new FileOutputStream(filename);
//        JsonWriter fileWriter = Json.createWriter(os);
//        fileWriter.writeArray(contactObj);
//        fileWriter.close();
//    }

    public static void deleteMail(String email){
        try {
            JsonObject jsonObject = (JsonObject)new JsonParser().parse(new FileReader(filename));
            Set keys = jsonObject.keySet();
                    Iterator iter = keys.iterator();
            while(iter.hasNext()){
                String key = (String)iter.next();
                if(key.equals(email)){
                    System.out.println("REMOVE: ");
                    System.out.println(key+":"+jsonObject.get(key).toString());
                    iter.remove();
                    jsonObject.remove(key);
                }
            }
            try(FileWriter fileWriter = new FileWriter(filename)){
                fileWriter.write(jsonObject.toString());
                fileWriter.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
