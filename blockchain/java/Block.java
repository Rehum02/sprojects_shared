/** --------------------------------------------------------
 * @filename 	 		: Block.java
 * @description  		: Handle Block
 * @author_dev 			: MichéKOKORA [MK]
 * @date 				: 02.2023
 * @project 	 		: S Architecture for Big data  Project 2 -1  | Simple Blockchain | TDD
 *
 *  (c) Copyright 2023
 *-------------------------------------------------------- */

package io.collective.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private String previousHash;
    private long timestamp;
    private int nonce;
    private String hash;
    private String hash_computed;

    public Block(String previousHash, long timestamp, int nonce) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = calculatedHash();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public String getHash() {
        return hash;
    }

    // MK :: Added
    public void setHash(String hash) {
        this.hash = hash;
    }

    public String calculatedHash() throws NoSuchAlgorithmException {
        String h = String.format("%s%s%s", previousHash, timestamp, nonce);
        return calculateHash(h);
    }

    /// Supporting functions that you'll need.

    static String calculateHash(String string) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(string.getBytes());
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }
}
