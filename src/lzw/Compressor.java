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
        //this.stringToCompress = r1.returnTextToCompress();
        this.stringToCompress = "aaabbbbbbaabaaba";
        this.compressedString = "";
        r1.closeFile();
        this.numCodes = 0;
        this.formInitialTable2();
    } // End Constructor
    
    private void formInitialTable(){
        this.dict = new HashMap();
        for(int i = 0; i < 256; i++){
            this.dict.put(Character.toString((char) i), new MyInteger(i)); // put values in dict
        }
    }// Form the initial mapping able
    
    private void formInitialTable2(){
        this.dict = new HashMap();    
        this.dict.put("a", new MyInteger(0)); // put values in dict
        this.dict.put("b", new MyInteger(1));
        this.numCodes+=2;
    }// Form the initial mapping able
    
    
    
   public boolean compress(){
       String sb = this.stringToCompress;
       String match = "";
       for(int i = 0; i < sb.length(); i++){
           if(this.dict.get(sb.substring(0, sb.length()-i)) != null){ 
               match = sb.substring(0, sb.length()-i);
               System.out.println("Matched " + match);
               this.compressedString += (this.dict.get(match)).code;
               this.stringToCompress = this.stringToCompress.replaceFirst(match, "");
               sb = this.stringToCompress;
               System.out.println("The new string is" + stringToCompress);
               updateTable(match);
           }
       }
       if(!stringToCompress.isEmpty()) compress();
       
       return true;
   }
   private void updateTable(String s){
       if(!this.stringToCompress.isEmpty()){
           s=s+this.stringToCompress.charAt(0);
           this.dict.put(s, new MyInteger(numCodes));
           System.out.println("Added pattern " + s + this.numCodes + "to the dict");
           this.numCodes++;
       }
   } 
   
    private String findPattern(String s){
        String matchedPattern = "";
        if(this.dict.get(s) != null);
            matchedPattern = s;
        return matchedPattern;
    }
   
    
    private void updateDict(String pattern, int index){
        if(index < this.stringToCompress.length()-1){
            pattern = pattern+this.stringToCompress.charAt(index+1);
            System.out.println(pattern);
            this.dict.put(pattern, new MyInteger(this.numCodes++));
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
