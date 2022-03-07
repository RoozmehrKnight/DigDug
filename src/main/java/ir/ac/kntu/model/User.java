package ir.ac.kntu.model;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;

public class User implements Comparable, Serializable {
    private String username;
    private String password;
    private int playedTimes;
    private int highScore;

    private static User currentUser = new User();
    private static ArrayList<User> allUsers = new ArrayList<User>();

    public static void setCurrentUser(User currentUser) {
        User.currentUser = currentUser;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public User(){

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highScore = 0;
        this.playedTimes= 0;
    }

    public static void addAllUsersToFile() {
        try {
            ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"/files/Users.txt"));
            for (User item:User.getAllUsers()) {
                oos.writeObject(item);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readAllUsersFromFile() {
        //this method runs only once in the beginning of the app
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"/files/Users.txt"));
            Object o;
            while ((o = ois.readObject())!=null){
                User.getAllUsers().add((User) o);
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getHighScore() {
        return highScore;
    }

    public int getPlayedTimes() {
        return playedTimes;
    }

    public void setPlayedTimes(int playedTimes) {
        this.playedTimes = playedTimes;
    }

    @Override
    public int compareTo(Object o) {
        User user= (User)o;
        return user.highScore - this.highScore;
    }

}
