/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dmodena.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Doug Modena
 */
public class JCryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    
    /**
     * Encrypt from file to file with default key
     * @param inputFile Input file
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void encrypt(File inputFile, File outputFile) throws JCryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, JCryptoKeys.DEFAULT, inputFile, outputFile);
    }
    
    /**
     * Encrypt from byte array to file with default key
     * @param inputBytes Input byte array
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void encrypt(byte[] inputBytes, File outputFile) throws JCryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, JCryptoKeys.DEFAULT, inputBytes, outputFile);
    }
    
    /**
     * Encrypt from file to file
     * @param key Encryption key
     * @param inputFile Input file
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void encrypt(String key, File inputFile, File outputFile) throws JCryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }
    
    /**
     * Encrypt from byte array to file
     * @param key Encryption key
     * @param inputBytes Input byte array
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void encrypt(String key, byte[] inputBytes, File outputFile) throws JCryptoException {
        doCrypto(Cipher.ENCRYPT_MODE, key, inputBytes, outputFile);
    }
    
    /**
     * Decrypt from file to file with default key
     * @param inputFile Input file
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void decrypt(File inputFile, File outputFile) throws JCryptoException {
        doCrypto(Cipher.DECRYPT_MODE, JCryptoKeys.DEFAULT, inputFile, outputFile);
    }
    
    /**
     * Decrypt from file to byte array with default key
     * @param inputFile Input file
     * @return Output byte array
     * @throws JCryptoException Exception
     */
    public static byte[] decrypt(File inputFile) throws JCryptoException {
        return doCrypto(Cipher.DECRYPT_MODE, JCryptoKeys.DEFAULT, inputFile);
    }
    
    /**
     * Decrypt from file to file
     * @param key Encryption key
     * @param inputFile Input file
     * @param outputFile Output file
     * @throws JCryptoException Exception
     */
    public static void decrypt(String key, File inputFile, File outputFile) throws JCryptoException {
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }
    
    /**
     * Decrypt from file to byte array
     * @param key Encryption key
     * @param inputFile Input file
     * @return Output byte array
     * @throws JCryptoException Exception
     */
    public static byte[] decrypt(String key, File inputFile) throws JCryptoException {
        return doCrypto(Cipher.DECRYPT_MODE, key, inputFile);
    }
    
    
    private static void doCrypto(int cipherMode, String key, byte[] inputBytes, File outputFile) throws JCryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
            
            byte[] outputBytes = cipher.doFinal(inputBytes);
            
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            
            outputStream.close();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException | IOException ex) {
            throw new JCryptoException(ex.getMessage(), ex);
        }
    }
    
    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) throws JCryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
            
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            
            byte[] outputBytes = cipher.doFinal(inputBytes);
            
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);
            
            inputStream.close();
            outputStream.close();
        } catch (NoSuchAlgorithmException | NoSuchPaddingException 
                | InvalidKeyException | IOException
                | IllegalBlockSizeException | BadPaddingException ex) {
            throw new JCryptoException(ex.getMessage(), ex);
        }
    }
    
    private static byte[] doCrypto(int cipherMode, String key, File inputFile) throws JCryptoException {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
            
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);
            
            inputStream.close();
            
            return cipher.doFinal(inputBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IOException
                | IllegalBlockSizeException | BadPaddingException ex) {
            throw new JCryptoException(ex.getMessage(), ex);
        }
    }
}
