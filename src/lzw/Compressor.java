/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;
import java.util.HashMap;

/**
 *
 * @author tumesh
 */
public class Compressor {
    private Reader r1;
    private String fileName;
    private String stringToCompress;
    private String compressedString;
    private HashMap<String, MyInteger> dict;
    private int numCodes;
    
    public Compressor(String fileName){
        this.r1 = new Reader();
        this.fileName = fileName;
        r1.openFile(fileName);
        this.stringToCompress = r1.returnTextToCompress();
        this.compressedString = "";
        r1.closeFile();
        this.formInitialTable();
        this.numCodes = 256;
    } // End Constructor
    
    private void formInitialTable(){
        this.dict = new HashMap();
        for(int i = 0; i < 256; i++){
            this.dict.put(Character.toString((char) i), new MyInteger(i)); // put values in dict
        }
    }// Form the initial mapping able
    
    public boolean compress(){
        String sb1 = this.stringToCompress;
        sb1 = sb1.trim();
        String searchString;
        for(int i = 0; i < sb1.length(); i++){
            searchString = sb1.substring(0, sb1.length()-i);
            if(this.dict.get(searchString) != null){
                this.compressedString += this.dict.get(searchString) + "\n";
                sb1=sb1.replaceFirst(searchString, "");
                searchString+=((sb1==null)?"":sb1.charAt(1));
                this.dict.put(searchString, new MyInteger(++this.numCodes));
            }
        }
        return true;
    }
    
    private void updateDict(String pattern, int index){
        if(index < this.stringToCompress.length()-1){
            pattern = pattern+this.stringToCompress.charAt(index+1);
            System.out.println(pattern);
            this.dict.put(pattern, new MyInteger(++this.numCodes));
        }
    }
    
    
    public String getStringToCompress(){
        return this.stringToCompress;
    }

    public HashMap<String, MyInteger> getDict() {
        return dict;
    }

    public void setDict(HashMap<String, MyInteger> dict) {
        this.dict = dict;
    }

    public String getCompressedString() {
        return compressedString;
    }

    public void setCompressedString(String compressedString) {
        this.compressedString = compressedString;
    }

    public int getNumCodes() {
        return numCodes;
    }

    public void setNumCodes(int numCodes) {
        this.numCodes = numCodes;
    }
    
    
    private class  MyInteger{
        public short code;
        
        public MyInteger(int value){
            this.code = (short)value;
        }
    }

}
