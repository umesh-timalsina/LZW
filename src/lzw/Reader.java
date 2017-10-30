/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** A class to create read text file.
 * @author tumesh
 */
public class Reader {
    private Scanner input;
    /** Read the file specified.
     * @param fileName is the name of the file to be read
     */
    public void openFile(String fileName){
        try{
            input = new Scanner(new File(fileName));
        }
        catch(FileNotFoundException fileNotFoundException){
            System.err.println("Error- File Not Found");
            System.exit(1);
        }//end catch
    }//end method openfile
    /** Create a single string of text to compress text from the file.
     * @return a single string on which LZW compression could be applied
     */
    public String returnTextToCompress(){
        String textToCompress = "";
        try{
            while(input.hasNextLine()){
//                textToCompress += !(input.next().equals("\n"))?
//                                        input.next():" "; // ignore new line marker
                  textToCompress += input.nextLine() + '\n';
                  textToCompress = textToCompress.trim();
            }// return text to compress as a single string
        }//end try
        catch(NoSuchElementException noSuchElementException){
            System.err.println("Error- File Improperly Formed");
            input.close();
            System.exit(1);
        }// end catch block
        catch(IllegalStateException illegalStateException){
            System.err.println("Error- Could not read from file");
            System.exit(1);
        }// end illegal state exception
        return textToCompress;
    }
    public boolean closeFile(){
        boolean fileClosed = false;
        if(input != null){
            input.close();
            fileClosed = true;
        }
        return fileClosed;
    }
}
