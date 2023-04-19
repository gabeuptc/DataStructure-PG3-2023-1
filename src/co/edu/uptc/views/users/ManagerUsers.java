package co.edu.uptc.views.users;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ManagerUsers {
    private List<User> users;
    private int idUser=1;
    private static ManagerUsers instance;

    private ManagerUsers() {
        users = new ArrayList<>();
        loadUsers();
    }
    public static ManagerUsers getInstance(){
        if (instance==null){
            instance = new ManagerUsers();
        }
        return instance;
    }
    public void loadUsers(){
        try {
            File file = new File("data/users.json");
            if (file.exists()){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder json = new StringBuilder();
                String cad;
                while((cad = bufferedReader.readLine())!=null) {
                    json.append(cad);
                }
                bufferedReader.close();
                Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
                users = new Gson().fromJson(json.toString(),userListType);
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }
    }
    private void updateUsers(){
        try {
            StringBuilder json = new StringBuilder();
            json.append("[");
            int count = 1;
            for (User user:users) {
                String json1= new Gson().toJson(user);
                json.append(json1);
                if (count!=users.size()){
                    json.append(",\n");
                }
                count++;
            }
            json.append("]");
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("data/users.json")));
            writer.write(String.valueOf(json));
            writer.close();
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }
    }
    public void addUser(User user){
        users.add(new User(user,idUser));
        idUser++;
        updateUsers();
    }
    public void deleteUser(User user){
        users.remove(user);
        updateUsers();
    }
    public boolean isExist(User user){
        for (User user1:users) {
            if (user1.getNameUser().equals(user.getNameUser()) ||
                user1.getEmail().equals(user.getEmail())){
                return true;
            }
        }
        return false;
    }
    public void setUser(User user){
        User toSet = null;
        for (User user1:users) {
            if (user1.getId()==user.getId()){
                toSet = user1;
                break;
            }
        }
        if (toSet!=null){
            toSet.setNameUser(user.getNameUser());
            toSet.setEmail(user.getEmail());
            toSet.setPassword(user.getPassword());
        }
        updateUsers();
    }
    public User getUser(String value){
        for (User user:users) {
            if (user.getNameUser().equals(value) || user.getEmail().equals(value)){
                return user.clone();
            }
        }
        return null;
    }
}
