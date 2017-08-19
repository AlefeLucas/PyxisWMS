/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.model.sistema;

import br.com.senaimg.wms.dao.SettingsDAO;
import br.com.senaimg.wms.language.Language;
import br.com.senaimg.wms.model.sistema.enums.CssTheme;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author ÁlefeLucas
 */
@Entity
@Table(name = "settings")
public class Settings implements Serializable {

    @Id
    private int id = 1;
    private Date dateSaved;
    private Language languageSystem;
    private boolean showTipOnStartup = true;
    private CssTheme theme;
    @Transient
    private static Settings last;

    /**
     *
     */
    public Settings() {
    }

    /**
     *
     * @param id
     * @param date
     * @param language
     */
    public Settings(int id, Date date, Language language) {
        this.id = id;
        this.dateSaved = date;
        this.languageSystem = language;
    }

    /**
     *
     * @param languageSystem
     */
    public Settings(Language languageSystem) {
        this.languageSystem = languageSystem;
        this.dateSaved = new Date();
    }

    public Settings(Date dateSaved, Language languageSystem, CssTheme theme) {
        this.dateSaved = dateSaved;
        this.languageSystem = languageSystem;

        this.theme = theme;
    }

    /**
     *
     * @param id
     */
    public Settings(int id) {
        this.id = id;
    }

    /**
     *
     * @param date
     * @param language
     */
    public Settings(Date date, Language language) {
        this.dateSaved = date;
        this.languageSystem = language;
    }

    /**
     * Deletes this object from the database
     */
    public void delete() {
        SettingsDAO.deleteSettings(this);
    }

    /**
     * Inserts of Updates if not exists this object in the database
     */
    public void merge() {
        SettingsDAO.mergeSettings(this);
    }

    /**
     * Lists this class from the database
     *
     * @return List &lt Settings>
     */
    public static List<Settings> list() {
        return SettingsDAO.selectSettingsList();
    }

    /**
     *
     * @return
     */
    public static Settings getLast() {
        if (last == null) {
            last = SettingsDAO.selectSettingsLast();
        }
        return last;
    }

    public static void changeLast() {

        last = SettingsDAO.selectSettingsLast();

    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Date getDateSaved() {
        return dateSaved;
    }

    /**
     *
     * @param dateSaved
     */
    public void setDateSaved(Date dateSaved) {
        this.dateSaved = dateSaved;
    }

    /**
     *
     * @return
     */
    public Language getLanguageSystem() {
        return languageSystem;
    }

    /**
     *
     * @param languageSystem
     */
    public void setLanguageSystem(Language languageSystem) {
        this.languageSystem = languageSystem;
    }

    public CssTheme getTheme() {
        return theme;
    }

    public void setTheme(CssTheme theme) {
        this.theme = theme;
    }

    public boolean isShowTipOnStartup() {
        return showTipOnStartup;
    }

    public void setShowTipOnStartup(boolean showTipOnStartup) {
        this.showTipOnStartup = showTipOnStartup;
    }

}
