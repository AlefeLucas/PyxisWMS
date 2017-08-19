/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.SecQuestionDAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table(name = "security_question")
public class SecQuestion implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String question;
    private String answer;
    @OneToOne(mappedBy = "secQuestion")
    private User user;

    /**
     * Creates a new SecurityQuestion object
     * @param question
     * @param answer
     */
    public SecQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    /**
     * Creates a new SecurityQuestion object
     * @param id
     */
    public SecQuestion(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new SecurityQuestion object - default constructor
     */
    public SecQuestion() {
    }   
    
    /**
     * Identifier
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Identifier
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the question
     *
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Sets the question
     *
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the answer
     *
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets the answer
     *
     * @param answer
     * @throws IllegalArgumentException If the given answer is less than 4
     * characters.
     */
    public void setAnswer(String answer) throws IllegalArgumentException {
        if (answer.length() < 4) {
            throw new IllegalArgumentException("Answer must have at least 4 characters.");
        }
        this.answer = answer;
    }
    
    /**
     *
     */
    public void insert(){
        SecQuestionDAO.insertSecQuestion(this);
    }
    
    /**
     *
     */
    public void update(){
        SecQuestionDAO.updateSecQuestion(this);
    }
    
    /**
     *
     */
    public void delete(){
        SecQuestionDAO.deleteSecQuestion(this);
    }
    
    /**
     *
     */
    public void merge(){
        SecQuestionDAO.mergeSecQuestion(this);
    }
    
    /**
     *
     * @return
     */
    public static List<SecQuestion> list(){
        return SecQuestionDAO.selectSecQuestions();
    }
}
