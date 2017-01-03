/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmodena.cli;

import com.dmodena.main.JCryptoException;
import com.dmodena.main.JCryptoUtils;
import java.io.File;

/**
 *
 * @author Doug Modena
 */
public class jcrypto {
    public static void main(String[] args) {
        if (args.length == 0) help();
        else {
            switch(args[0]) {
                case "-e":
                    encrypt(args);
                    break;
                case "-d":
                    decrypt(args);
                    break;
                default:
                    help();
                    break;
            }
        }
    }
    
    public static void encrypt(String[] args) {
        try {
            JCryptoUtils.encrypt(new File(args[1]), new File(args[2]));
        } catch (JCryptoException ex) {
            System.out.println(ex);
        }
    }
    
    public static void decrypt(String[] args) {
        try {
            JCryptoUtils.decrypt(new File(args[1]), new File(args[2]));
        } catch (JCryptoException ex) {
            System.out.println(ex);
        }
    }
    
    public static void help() {
        System.out.println("jcrypto - encrypt/decrypt text files");
        System.out.println("to encrypt run 'jcrypto -e inputFile outputFile'");
        System.out.println("to decrypt run 'jcrypto -d inputFile outputFile'");
    }
}
