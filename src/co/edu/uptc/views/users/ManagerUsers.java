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
    private void loadUsers(){
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
                verificateIndexes();
            }
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"Error técnico");
        }
    }

    private void verificateIndexes() {
        boolean isOk = true;
        int count = 1;
        for (User user:users) {
            if (count != user.getId()){
                isOk = false;
                break;
            }
            count++;
        }
        if (!isOk){
            List<User> usersOk = new ArrayList<>();
            for (User user:users) {
                usersOk.add(new User(user,usersOk.size()+1));
            }
            users.clear();
            users.addAll(usersOk);
            updateUsers();
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
        users.add(new User(user, users.size()+1));
        updateUsers();
    }
    public void deleteUser(User user){
        User toRemove=null;
        for (User user1:users) {
            if (user.getId()==user1.getId()){
                toRemove = user1;
                break;
            }
        }
        users.remove(toRemove);
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
    public boolean isExist(String value, int id){
        for (User user1:users) {
            if (user1.getNameUser().equals(value) && id != user1.getId()){
                return true;
            }
            if (user1.getEmail().equals(value) && id != user1.getId()){
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
