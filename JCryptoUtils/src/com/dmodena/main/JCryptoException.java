/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmodena.main;

/**
 *
 * @author Doug Modena
 */
public class JCryptoException extends Exception {
    public JCryptoException() { }
    
    public JCryptoException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
