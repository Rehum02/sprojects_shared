/** --------------------------------------------------------
 * @filename 	 			: Blockchain.java
 * @description  			: Blockchain
 * @author_dev 				: MichéKOKORA [MK]
 * @date 				    : 22.02.2023
 * @project 	 			: Coursera - Software Architecture for Big data - Project 2-1
 *
 *  (c) Copyright 2023
 *-------------------------------------------------------- */

package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Blockchain {

    Map<Integer,Block> blockchain = new HashMap<Integer, Block>();

    public boolean isEmpty() {
        return size() == 0 ? true: false;
    }

    public void add(Block block) {
        blockchain.put( (size()+1), block);
    }

    public int size() {
        return blockchain.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {
        int s = size();
        boolean r = true;
        int c = 0;
        int k;
        Block b;
        String previous_hash = "";

        // todo - check an empty chain
        if(s == 0)
        {
            return true;
        }

        // todo - check a chain of one
        if(s == 1)
        {
            // --  :::  isNotValid_whenOneIsNotMined  + isValid_HashIncorrect
            b = blockchain.get(1);

            Pattern p = Pattern.compile("\\s");
            Matcher m = p.matcher( b.getHash() );

            return  isMined(b) && !m.find();
        }

        // todo - check a chain of  many
        if(s > 1)
        {
            // --  :::  isNotValid_whenManyAreNotMined +  isNotValid_forIncorrectPreviousHash
            for (Map.Entry <Integer, Block> e : blockchain.entrySet()) {
                k = e.getKey();
                b = e.getValue();

                if(!isMined(b))  // a- isNotValid_whenManyAreNotMined
                {
                    r = false;
                    break;
                }

                if(k > 1 && !(b.getPreviousHash().equals(  previous_hash )))  // b- isNotValid_forIncorrectPreviousHash
                {
                    r = false;
                    break;
                }
                previous_hash = b.getHash();
            }
            return r;
        }

        return false;
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }
}