/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.model.sistema.enums.Gender;
import br.com.senaimg.wms.model.sistema.enums.UserStatus;
import br.com.senaimg.wms.dao.UserDAO;
import br.com.senaimg.wms.exception.UserDeletedException;
import br.com.senaimg.wms.util.Encryption;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String name; //
    @Column(unique = true, nullable = false)
    private String username;
    @Column(columnDefinition = "MEDIUMBLOB", nullable = false)
    private byte[] password;
    @Column(nullable = false)
    private String email; //
    @ManyToOne    
    private Profile profile;
    @OneToOne(cascade = CascadeType.ALL)
    private ImageFile profilePic;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(nullable = false)
    private Date birthDate; //
    @Column(nullable = false)
    private Gender gender; //
    @Column(nullable = false)
    private String phoneNumber; //
    @OneToOne(cascade = CascadeType.ALL)   
    @JoinColumn (nullable = false)
    private SecQuestion secQuestion;
    private UserStatus status;

    /**
     * Creates a new User given all params (obs: password will be encrypted)
     *
     * @param id
     * @param name
     * @param username
     * @param password - Encrypted
     * @param email
     * @param profile
     * @param profilePic
     * @param birthDate
     * @param gender
     * @param phoneNumber
     * @param secQuestion
     */
    public User(Integer id,
            String name,
            String username,
            String password,
            String email,
            Profile profile,
            ImageFile profilePic,
            Date birthDate,
            Gender gender,
            String phoneNumber,
            SecQuestion secQuestion) {
        this.id = id;
        this.name = name;
        this.username = username;
        setPassword(password);
        this.email = email;
        this.profile = profile;
        this.profilePic = profilePic;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.secQuestion = secQuestion;
    }

    /**
     *
     * @param name
     * @param username
     * @param password
     * @param email
     * @param profile
     * @param profilePic
     * @param birthDate
     * @param gender
     * @param phoneNumber
     * @param secQuestion
     * @param status
     */
    public User(
            String name,
            String username,
            String password,
            String email,
            Profile profile,
            ImageFile profilePic,
            Date birthDate,
            Gender gender,
            String phoneNumber,
            SecQuestion secQuestion,
            UserStatus status) {
        this.id = id;
        this.name = name;
        this.username = username;
        setPassword(password);
        this.email = email;
        this.profile = profile;
        this.profilePic = profilePic;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.secQuestion = secQuestion;
        this.status = status;
    }

    /**
     * Creates a new User
     *
     * @param name
     * @param username
     * @param password
     * @param email
     * @param profile
     * @param profilePic
     * @param birthDate
     * @param gender
     * @param phoneNumber
     * @param secQuestion
     * @param status
     */
    public User(String name, String username, byte[] password, String email, Profile profile, ImageFile profilePic, Date birthDate, Gender gender, String phoneNumber, SecQuestion secQuestion, UserStatus status) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.profilePic = profilePic;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.secQuestion = secQuestion;
        this.status = status;
    }

    /**
     * Creates a new User
     */
    public User() {
    }

    /**
     * Creates a new User given its ID, useful for database queries
     *
     * @param id
     */
    public User(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return username + " - " + name;
    }

    /**
     * Name of the user
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the user
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Username of the user
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username of the user
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Email of the user
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Email of the user
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * A Profile is an object that contains the permissions of the User in the
     * system
     *
     * @return profile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * A Profile is an object that contains the permissions of the User in the
     * system
     *
     * @param profile
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     * The profile picture of this user
     *
     * @return profilePic
     */
    public ImageFile getProfilePic() {
        return profilePic;
    }

    /**
     * The profile picture of this user
     *
     * @param profilePic
     */
    public void setProfilePic(ImageFile profilePic) {
        this.profilePic = profilePic;
    }

    /**
     * Identifier, for use in database
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Identifier, for use in database
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * The User's birthdate
     *
     * @return birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * The User's birthdate
     *
     * @param birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * The gender of the User (Gender.MALE, Gender.FEMALE, Gender.OTHER)
     *
     * @return gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * The gender of the User (Gender.MALE, Gender.FEMALE, Gender.OTHER)
     *
     * @param gender
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * The User's phone number
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * The User's phone number
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * In case of password recovery, it's needed to create and answer a
     * SecurityQuestion, this question should be set for each new User
     *
     * @return secQuestion
     */
    public SecQuestion getSecQuestion() {
        return secQuestion;
    }

    /**
     * In case of password recovery, it's needed to create and answer a
     * SecurityQuestion, this question should be set for each new User
     *
     * @param secQuestion
     */
    public void setSecQuestion(SecQuestion secQuestion) {
        this.secQuestion = secQuestion;
    }

    /**
     * Gets the engrypted password fot this User
     *
     * @return Encrypted password
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * Gets the engrypted password fot this User
     *
     * @return Encrypted password
     */
    public String getPasswordString() {
        return new String(password);
    }

    /**
     * Takes the param and encrypts it before storing
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = Encryption.encrypt(password);
    }
    
    /**
     *
     * @param bytes
     */
    public void setPassword(byte[] bytes) {
        this.password = bytes;
    }

    /**
     * Takes the param and encrypts it before storing
     *
     * @param password
     */
    public void setPassword(char[] password) {
        this.password = Encryption.encrypt(password);
    }

    /**
     * Gets the user status
     *
     * @return status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * Sets the user status
     *
     * @param status
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * Inserts this object into the database
     */
    public void insert() {
        UserDAO.insertUser(this);
    }

    /**
     * Updates this object in the database
     */
    public void update() {
        UserDAO.updateUser(this);
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        UserDAO.deleteUser(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        UserDAO.mergeUser(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt User >
     */
    public static List<User> list() {
        return UserDAO.selectUsers();
    }
    
    /**
     *
     * @throws UserDeletedException
     */
    public void refresh() throws UserDeletedException{
        UserDAO.refresh(this);
    }

}
