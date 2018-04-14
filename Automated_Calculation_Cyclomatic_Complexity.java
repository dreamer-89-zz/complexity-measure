/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Suraj Singh
 */
class Automated_Calculation_Cyclomatic_Complexity {
    static String[] matching_Expression = new String[]{""};
    public static void main(String arfs[]) throws IOException
    {
        Scanner s = new Scanner(System.in);
        String line,path;
        int counter=0,isSwitch=0;// store the number of decision nodes in the DD graph
        char choice;
        StringBuffer inputFile, matcherFile;
        do
        {
        counter = 0;
        System.out.print("Enter file name : ");
        path = "C:\\Users\\Suraj Singh\\Desktop\\"+s.next()+".java";
        System.out.print("Do you want source code : ");
        choice = s.next().charAt(0);        
        try
        {
            BufferedReader r = new BufferedReader(new FileReader(path));
            if(choice=='y'||choice=='Y') 
                System.out.println("\n*************************************SOURCE CODE*************************************");
            while( (line=r.readLine())!=null)
            {
                if(choice=='y'||choice=='Y') System.out.println(line);
                if(line.matches("(^\\s*)(.*)(if|for|while)(\\(.*)"))
                    counter++;
                else if(line.matches("(^\\s*)switch(\\(.*)"))
                    isSwitch++;    // Beginning of switch statement
                else if(line.matches("(^\\s*)case(\\s+)(\\w+):(.*)") && isSwitch>0) // support nested sw
                    counter++;
                else if(line.matches("(^\\s*)default(\\s*):(.*)") && isSwitch>0)//end of switch further case will not be cosidered
                {
                    isSwitch--;
                    counter++;
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }      
        if(choice=='y'||choice=='Y') System.out.println("**************************************************************************************");
        System.out.println("\nCYCLOMATIC COMPLEXITY => "+counter);
        System.out.print("\nDo you want to continue (y/n)? ");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }
}
