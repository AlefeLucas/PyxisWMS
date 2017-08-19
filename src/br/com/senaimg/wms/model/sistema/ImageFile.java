/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.model.sistema.enums.ImageCategory;
import br.com.senaimg.wms.dao.ImageFileDAO;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javafx.scene.image.Image;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author Alefe Lucas
 */
@Entity
@Table (name= "image")
public class ImageFile implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;
    private String fileName;
    private String format;
    @Column(columnDefinition = "MEDIUMBLOB", nullable = false)
    private byte[] bytes;
    private ImageCategory category;

    /**
     * Default constructor
     */
    public ImageFile() {
    }

    /**
     * Creates a new ImageFile object
     *
     * @param id
     */
    public ImageFile(Integer id) {
        this.id = id;
    }

    /**
     * Creates a new ImageFile object
     *
     * @param fileName
     * @param bytes
     * @param category
     */
    public ImageFile(String fileName, byte[] bytes, ImageCategory category) {
        this.fileName = fileName;
        this.bytes = bytes;
        this.category = category;
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
     * File name
     *
     * @return fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * File name
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Format of the file
     *
     * @return format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Gets the stream containing this image
     *
     * @return InputStream
     */
    public InputStream getStream() {
        return new ByteArrayInputStream(this.getBytes());
    }

    /**
     * Gets the JavaFx Image
     *
     * @return Image
     */
    public Image getImage() {
        return new Image(getStream());
    }

    /**
     * Format of the file
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Binary value
     *
     * @return binary
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Binary value
     *
     * @param bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * The ImageCategory of this image
     *
     * @return category
     */
    public ImageCategory getCategory() {
        return category;
    }

    /**
     * The ImageCategory of this image
     *
     * @param category
     */
    public void setCategory(ImageCategory category) {
        this.category = category;
    }

    /**
     *
     */
    public void insert(){
        ImageFileDAO.insertImageFile(this);
    }
    
    /**
     *
     */
    public void update(){
        ImageFileDAO.updateImageFile(this);
    }
    
    /**
     *
     */
    public void delete(){
        ImageFileDAO.deleteImageFile(this);
    }
    
    /**
     *
     */
    public void merge(){
        ImageFileDAO.mergeImageFile(this);
    }
    
    /**
     *
     * @return
     */
    public static List<ImageFile> selectImageFiles(){
        return ImageFileDAO.selectImageFiles();
    }
}
