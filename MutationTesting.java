/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Suraj Singh
 */
class MutationTesting {
    static StringBuffer operand;
    static StringBuffer operator;
    static StringBuffer stat;
    public static void Mutant_Output(StringBuffer output)
    {            
        System.out.println(output);
    }
    public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    Pattern decision = Pattern.compile("\\b(if)\\b(.*)"); // decision
    Pattern mutationChange = Pattern.compile("\\(([a-zA-Z]+)(<|>|<=|>=|==)([a-zA-Z]+)\\)");
    Matcher decisionMatch,conditionMatch;
    String path, line,condition,var1,var2,op;
    int counter;
    boolean done=false;
    char choice;
    
    Color c = new Color(0, 0, 255);
    //Font f = new Font();
    do
    {
        
    operand = new StringBuffer();
    operator = new StringBuffer();
    stat = new StringBuffer();
    counter=0;
    System.out.print("Enter file name : ");
    path = "C:\\Users\\Suraj Singh\\Desktop\\matcher1.txt";
    System.out.println("\n\n******************** FILE CONTENT ********************\n");
        try
        {
            BufferedReader r = new BufferedReader(new FileReader(path));
            while( (line=r.readLine())!=null)
            {
                counter++;// store the line number in the sampled program.
                System.out.println(counter+" "+line);                
                decisionMatch = decision.matcher(line);
                if(decisionMatch.find() && !done)
                {
                    condition = decisionMatch.group(2);
                    conditionMatch = mutationChange.matcher(condition);
                    if(conditionMatch.find())
                    {
                        var1 = conditionMatch.group(1);
                        var2 = conditionMatch.group(3);
                        op = conditionMatch.group(2);
                        operand.append("\tif("+var2+op+var1+")\n");
                        operator.append("\tif("+var1+"<"+var2+")\n");                    
                        done = true;
                    }
                }
                else //if(!done)
                {
                    operand.append(counter+" "+line+"\n");
                    operator.append(counter+" "+line+"\n");
                }
                if(line.trim().startsWith("static "))
                {
                    //System.out.println("Static found");
                    stat.append(counter+"\t"+line.trim().substring(6)+"\n");
                }
                else
                    stat.append(counter+" "+line+"\n");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("\nOutput : a is greater than b\n");
        System.out.println("******** Enter the type of mutant to check **********");
        System.out.println("\t1. Operand Mutant \n\t2. Operator Mutant\n\t3. static keyword");
        counter = s.nextInt();
        if(counter==1)
        { 
            System.out.println("\n************************ Operand Mutant **********************\n");
            Mutant_Output(operand);
            System.out.println("Output : b is greater than a");
        }
        else if(counter==2)
        {            
            System.out.println("\n************************ Operator Mutant **********************\n");
            Mutant_Output(operator);
            System.out.println("Output : b is greater than a");
        }
        else if(counter==3)
        {
             System.out.println("\n************************ static keyword change **********************\n");
             Mutant_Output(stat);
             System.out.println("Output : Compilation Error : Non-static variable in static context ");
        }
        else
        {
            System.out.println("Please enter correct choice !!!");
        }
        operand.delete(0, operand.length());
        System.out.println("\nDo you want to continue (y/Y) ?");
        choice = s.next().charAt(0);
    }while(choice=='Y'||choice=='y');
  }
}



