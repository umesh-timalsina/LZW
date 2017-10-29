/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.util.HashMap;

/**
 *
 * @author tumeshS
 */
public class LZW {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Compressor c1 = new Compressor("str.txt");
        //System.out.println(c1.getStringToCompress());
        c1.compress();
        //String s1 = "Apple";
        //String s2 = s1;
        HashMap h1 = c1.getDict();
        System.out.println(h1.get("He"));
        
        
        
    }
    
}
