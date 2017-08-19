/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import br.com.senaimg.wms.model.sistema.ImageFile;
import br.com.senaimg.wms.model.sistema.SecQuestion;
import br.com.senaimg.wms.model.sistema.User;
import br.com.senaimg.wms.model.sistema.enums.Gender;
import br.com.senaimg.wms.model.sistema.enums.ImageCategory;
import br.com.senaimg.wms.model.sistema.enums.UserStatus;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author Alefe Lucas
 */
public abstract class UserGenerator {

//    /**
//     * Generates Users programatically
//     *
//     * @return List of Users
//     */
//    public static List<User> generateUsers() {
//        List<User> users = new ArrayList<>();
//
//        Profile profile = new Profile("Admin");
//        profile.insert();
//
//        String username = "bill";
//        User bill = setUser(username);
//
//        username = "catarina";
//        User catarina = setUser(username);
//
//        username = "manuel";
//        User manuel = setUser(username);
//
//        username = "claudia";
//        User claudia = setUser(username);
//
//        username = "jose";
//        User jose = setUser(username);
//
//        username = "rafael";
//        User rafael = setUser(username);
//
//        users.add(bill);
//        users.add(catarina);
//        users.add(claudia);
//        users.add(jose);
//        users.add(manuel);
//        users.add(rafael);
//
//        return users;
//    }

    /**
     * Generates a User with a given username; Name: Username Thomson; Email:
     * username@pyxiswms.com; Password: username123; Date of birth: 09/09/1997;
     * ProfilePic: UserUsername.png Phone number: (31) 3593-4213 Security
     * Question: Qual é o seu primeiro nome? Answer: username
     *
     * @param username
     * @return 
     */
    public static User setUser(String username) {
        String temp = username.toUpperCase();
        char c = temp.charAt(0);
        String uName = c + username.substring(1, username.length());
        String name = uName + " Thompson";
        String password = username + "1234";
        String email = username + "@pyxiswms.com";                
        Date birthDate = new Date(1997 - 1900, 9 - 1, 9);
        ImageFile profilePic = setProfilePic(uName);
        String phoneNumber = "1234567890";
        SecQuestion secQuestion = new SecQuestion("What is your first name?", uName);
        User user = new User(name, username, password, email, null, profilePic, birthDate, Gender.OTHER, phoneNumber, secQuestion, UserStatus.ON);

        return user;
    }

    /**
     * Loads the users profile pic (bill, manuel, jose, claudia, catarina,
     * rafael)
     */
    private static ImageFile setProfilePic(String uName) {
        //Path path = Paths.get("I:\\TCC\\res\\images\\user" + uName + ".jpg");
        Image i = SystemImageUtil.getImage("user" + uName + ".jpg");
        BufferedImage bImage= SwingFXUtils.fromFXImage(i, null);
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        byte[] binary;
        try {
            ImageIO.write(bImage, "jpg", s);
            binary = s.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error generating users setting profile pics");
            binary = null;
            System.exit(1);
        }                      
       
        
        ImageFile profilePic = new ImageFile(uName, binary, ImageCategory.USER_PROF);
        return profilePic;
    }
}
