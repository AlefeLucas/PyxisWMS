/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class ValidateFieldUtil {

    /**
     *
     * @param email
     * @return
     */
    public static boolean isValidEmailAddress(String email) {
        if (biggerThan(email, 255)) {
            return false;
        }
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isValidEAN13(String codigoBarra) {
        if (!codigoBarra.matches("^[0-9]{13}$")) {
            return false;
        }
        int[] numeros = codigoBarra.chars().map(Character::getNumericValue).toArray();
        int somaPares = numeros[1] + numeros[3] + numeros[5] + numeros[7] + numeros[9] + numeros[11];
        int somaImpares = numeros[0] + numeros[2] + numeros[4] + numeros[6] + numeros[8] + numeros[10];
        int resultado = somaImpares + somaPares * 3;
        int digitoVerificador = 10 - resultado % 10;
        return digitoVerificador == numeros[12];
    }

    /**
     *
     * @param name
     * @return
     */
    public static boolean isValidName(String name) {
        if (biggerThan(name, 255)) {
            return false;
        }
        String ePattern = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(name);
        return m.matches();
    }

    public static boolean isValidMnemonic(String mnemonic) {
        if (biggerThan(mnemonic, 20)) {
            return false;
        }
        String ePattern = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(mnemonic);
        return m.matches();
    }

    public static boolean isValidCompanyCode(String companyCode) {
        if (biggerThan(companyCode, 10)) {
            return false;
        }
        String ePattern = "^[\\p{L}.'-]+$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(companyCode);
        return m.matches();
    }

    public static boolean isValidTax(String tax) {
        if (biggerThan(tax, 40)) {
            return false;
        }
        for (char c : tax.toCharArray()) {
            if ((c < '0' || c > '9') && c != '.' && c != '-' && (c < 'A' || c > 'Z')) {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidTaxType(String type) {
        if (biggerThan(type, 20)) {
            return false;
        }
        for (char c : type.toCharArray()) {
            if ((c < 'A' || c > 'Z') && c != '.' && c != '-') {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidPostalCode(String postal) {
        try {
            Integer.parseInt(postal);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     *
     * @param iso
     * @return
     */
    public static boolean isValidIso(String iso) {
        if (biggerThan(iso, 3)) {
            return false;
        }
        String ePattern = "[A-Z]{2}";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(iso);
        return m.matches();
    }

    /**
     *
     * @param name
     * @param max
     * @return
     */
    public static boolean biggerThan(String name, int max) {
        
        return name.length() > max;
    }

    /**
     *
     * @param username
     * @return
     */
    public static boolean isValidUsername(String username) {
        if (biggerThan(username, 255)) {
            return false;
        }
        String ePattern = "^[A-Za-z_][A-Za-z0-9_]{3,29}$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(username);
        return m.matches();

    }

    /**
     *
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        String ePattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(password);
        return m.matches();

    }

    /**
     *
     * @param string
     * @return
     */
    public static boolean isValidSomething(String string) {
        return !(biggerThan(string, 255) || string.trim().isEmpty());

    }

    /**
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {

        for (char c : phone.toCharArray()) {
            if (c < '0' || c > '9') {
                return false;
            }
        }
        if (!(phone.length() >= 7 && phone.length() <= 13)) {
            return false;
        }
        return true;
    }
     public static boolean isValidDouble(String newValue) {
        boolean validSelling;
        try {
            Double.parseDouble(newValue);
            validSelling = true;
        } catch (NumberFormatException | NullPointerException ex) {
            validSelling = false;
        }
        return validSelling;
    }

    public static boolean isValidWebAddress(String web) {
        if (biggerThan(web, 255)) {
            return false;
        }
        String ePattern = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(web);
        return m.matches();
    }

    public static boolean isValidLocationField(String code) {
        if (biggerThan(code, 32)) {
            return false;
        } else {
            return true;
        }

    }

}
