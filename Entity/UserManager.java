package Entity;

import java.lang.*;
import java.util.*;
import java.io.*;


public class UserManager {
    private User[] users;
    private File usersFile, tempFile;
    private FileWriter usersFileWriter;
    private Scanner usersScanner;

    public UserManager() {
        users = new User[100];
    }

    public void addUser(User user) {
        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                users[i] = user;

                try {
                    usersFile = new File("./Resources/Text/Users.txt");
                    if (usersFile.createNewFile()) {
                        System.out.println("File created: " + usersFile.getName());
                    }
                    usersFileWriter = new FileWriter(usersFile, true);
                    usersFileWriter.write(users[i].getUsername() + " ---> ");
                    usersFileWriter.write(users[i].getPassword() + " ---> ");
                    usersFileWriter.write(users[i].getName() + " ---> ");
                    usersFileWriter.write(users[i].getGender() + " ---> ");
                    usersFileWriter.write(users[i].getEmail().toLowerCase() + "\n");

                    usersFileWriter.flush();
                    usersFileWriter.close();
                } catch (IOException ie) {
                    System.out.println("An error has occurred.");
                    ie.printStackTrace();
                }
                break;
            }
        }
    }
}
