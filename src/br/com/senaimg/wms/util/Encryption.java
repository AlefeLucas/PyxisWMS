/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.util;

import java.util.Random;

/**
 *
 * @author Alefe Lucas
 */
public abstract class Encryption {

    /**
     * Encrypts a char array, using each char as a seed for a Random object, and
     * then generating a pseudorandom Long in radix Character.MAX_RADIX (36,
     * from 0 to Z), then concatenating all of them
     *
     * @param cArray     
     * @return Encrypted password
     */
    public static byte[] encrypt(char[] cArray) {
                   
        String encryptedPassword = "";      
       
        long sum = 0;
        for (char c : cArray) {
            sum += c;
        }
        sum += cArray.length;
        
        long seed = new Random(sum).nextLong();
        int index = 0;
        for (char c : cArray) {
            Random random = new Random(c + seed + index++);
            encryptedPassword += Long.toUnsignedString(random.nextLong(), Character.MAX_RADIX).toUpperCase();
            
        }
        
        return encryptedPassword.getBytes();
    }

    /**
     * Encrypts a char array, using each char as a seed for a Random object, and
     * then generating a pseudorandom Long in radix Character.MAX_RADIX (36,
     * from 0 to Z), then concatenating all of them
     *
     * @param s
     * @return Encrypted password
     */
    public static byte[] encrypt(String s) {
        return encrypt(s.toCharArray());
    }

    /**
     * Encrypts a char array, using each char as a seed for a Random object, and
     * then generating a pseudorandom Long in radix Character.MAX_RADIX (36,
     * from 0 to Z), then concatenating all of them
     *
     * @param cs
     * @return Encrypted password
     */
    public static byte[] encrypt(CharSequence cs) {
        return encrypt(cs.toString());
    }
}
