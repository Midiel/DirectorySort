/*
 * Midiel Rodriguez
 * Course: COP-337
 * Assignemnt:
 * 
 * The purpose of this program is to sort a list of directories.
 *
 * The program asks for the name of the text file to open. Opens the provided
 * text file and loads it into a list. Then is sorts the list in accending
 * order and prints/displays the sorted list.
 * 
 * Disclaimer:
 * I hereby certify that this code is my work and my work alone and understand
 * the syllabus regarding plagiarized code.
 * Copyright 2018 Midiel
 */
package directorysort;

import java.io.FileInputStream;                 // To load text file
import java.io.FileNotFoundException;           // To catch FileNotFoundExcept.
import java.io.IOException;                     // To catch IOException
import java.util.ArrayList;                     // To load the list
import java.util.Collections;                   // To sort the list
import java.util.Comparator;                    // Used to sort the list
import java.util.InputMismatchException;        // To catch exception
import java.util.Scanner;                       // To get user input
import java.util.regex.PatternSyntaxException;  // To catch exception

/**
 * This program sorts a list of directories in ascending order.
 *
 * @author Midiel Rodriguez
 * @version 1.0 Match 08, 2018
 */
public class DirectorySort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Holds the text file name
        String nameOfTextFile;
        
        // To hold the directory list
        ArrayList<String> directoryList = new ArrayList<>();
        
        // To read the text file content
        FileInputStream fileByteStream = null;
        
        /*
         * Creates an instance of a <code>Scanner class</code>. It will be
         * used to obtain user input.
         */
        Scanner scnr = new Scanner(System.in);
        
        // Creates new scanner to load the text file
        Scanner scanner2 = null;
        
        // To keep displaying the loop to obtain a valid file name
        boolean validFile = false;
        
        // Loop to obtain a valid text file name
        while (!validFile) {
            try {
                // Calls getNameOfTextFile to get the user defined file name
                nameOfTextFile = getNameOfTextFile(scnr);
                // Try to open the text file
                fileByteStream = new FileInputStream(nameOfTextFile);
                // Creates new scanner
                scanner2 = new Scanner(fileByteStream);
                // Load the list
                while(scanner2.hasNext()) {
                    directoryList.add(scanner2.nextLine());
                }
                
                // Sort the list ascending order
                sortAscending(directoryList);
                
                // Print the entire list
                directoryList.forEach((s) -> {
                    System.out.println(s);
                });
                
                // To end the loop
                validFile = true;

            } catch (FileNotFoundException e) {
                scnr.nextLine();                    // Clears the input buffer
                System.out.println("Caught FileNotFoundException. "
                                   + "File not found, please try again.");
            } catch (InputMismatchException f) {
                scnr.nextLine();                    // Clears the input buffer
                System.out.println("Caught InputMismatchException. "
                                   + "an error occurs while reading the file, "
                                   + "please try again.");
            } catch (PatternSyntaxException g) {
                System.out.println(g.getDescription());
            } catch (ClassCastException | UnsupportedOperationException h) {
                System.out.println("Caught ClassCastException."  
                                   + h.getMessage() + ". Please try again.");
            } catch (NullPointerException | NumberFormatException i) {
                System.out.println("Caught NullPointerException."  
                                   + i.getMessage() + ". Please try again.");
            }
        }
        
        // Closes the scanner objects
        scnr.close();
        scanner2.close();
        
        // Try to close the text file
        try {
            fileByteStream.close();
        } catch(IOException h) {
            System.out.println("Caught IOException: " + h.getMessage());
        }
        
        // Exit the application 
        System.exit(0);   
    }
    
    /**
     * To get the name of the text file to open
     * 
     * @param scnr scanner object used to get user input
     * @return a string that represents the name of the text file to open
     */
    static String getNameOfTextFile(Scanner scnr) {
        System.out.println("Please enter the filename which contains the " +
                           "list of directories:");
        return scnr.next();
    }
    
    /**
     * To sort the directory list in ascending order
     * 
     * @param list the directory list to be sorted
     */
    public static void sortAscending(ArrayList<String> list){
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                
                // Removes all digits from the string
                String s1Characters = s1.replaceAll("\\d", "");
                String s2Characters = s2.replaceAll("\\d", "");

                // If the string/characters are equal, then compare the integers
                if(s1Characters.equalsIgnoreCase(s2Characters)) {
                    return extractDigits(s1) - extractDigits(s2);
                }
                
                // Compare the string/vcharacters
                return s1.compareTo(s2);
            }

            // Extracts the numeric value from the string
            int extractDigits(String s) {
                // Remove all non-digits from the string
                String digit = s.replaceAll("\\D", "");
                
                /*
                 * Returns zero (0) if there is no digits otherwise it returns
                 * the integer
                */
                if(digit.isEmpty()) {
                    return 0;
                } else {
                    return Integer.parseInt(digit);    
                }
            }
        });   
    }   
}
