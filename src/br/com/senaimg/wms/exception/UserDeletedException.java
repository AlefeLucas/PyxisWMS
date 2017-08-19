/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.exception;

/**
 *
 * @author Ã�lefeLucas
 */
public class UserDeletedException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4439139738187566863L;

	/**
     *
     * @param message
     */
    public UserDeletedException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public UserDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public UserDeletedException(Throwable cause) {
        super(cause);
    }

    /**
     *
     */
    public UserDeletedException() {
    }
    
}
