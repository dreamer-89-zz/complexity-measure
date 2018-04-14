/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Gaurav Shah
 */
class External_Exam {
     public static void main(String arfs[]) throws IOException
    {
        Scanner s = new Scanner(System.in);
        String line,path,defined[],variable,position,assignment,usages,input;
        int counter=0; 
        char choice;
        boolean declare;
        StringBuffer output = new StringBuffer();
        String[] counts;
        // It should include all the data types of the language used.
        Pattern declara = Pattern.compile("\\b(int|float|String|long|boolean|double|byte)\\b(.*)"); // declara
      //Pattern assignment = Pattern.compile("(\\b[a-z0-9]+\\b)=(.*)"); 
        Pattern def = Pattern.compile("([a-z][a-z0-9]*)(\\[])?(\\s*=\\s*)?(\\bnew\\b)?(\\b[a-z0-9]+)?(\\[[a-z0-9]+])?[,;]");      
//                int coins[] = new int[n];  coins[i] = r.nextInt();
        Pattern assign_definition_via_object = Pattern.compile("([a-z][a-z0-9]*)(\\s*=\\s*)(\\b[a-z0-9]+.[a-z0-9]+)(\\.*)");      
        Pattern simple_assign = Pattern.compile("([a-zA-z]+)(\\s*=\\s*)(.*);");
        Pattern usage = Pattern.compile("\\b[a-zA-Z][a-zA-Z0-9]*\\b");
        Matcher findDeclaration,findGeneralize,findSimpleAssign,findGenrealizeViaObject,findUsage;
        Set<String> variable_declaration = new HashSet<String>();
        Map<String,String> definition = new HashMap<String,String>(); // first parameter is the variable name, second parameter gives the place of definition.
        Map<String,String> used = new HashMap<String,String>();
        do
        {
        System.out.print("Enter file name : ");
        path = "C:\\Users\\Suraj Singh\\Desktop\\"+s.next()+".txt";
        System.out.println("\n******************************* FILE CONTENT *******************************");
        try
        {
            BufferedReader r = new BufferedReader(new FileReader(path));
            while( (line=r.readLine())!=null)
            {
                counter++; /// to keep track of line numbers
                findDeclaration = declara.matcher(line);
                findGeneralize = def.matcher(line);
                findSimpleAssign = simple_assign.matcher(line);
                System.out.println(line);
                // DEFINITION NODES
                if(findDeclaration.find())// This is a variable declara line. Process it and check for assignments.
                {
                    declare = true;
                    input = findDeclaration.group(2);
                    findGeneralize = def.matcher(input);
                    findGenrealizeViaObject = assign_definition_via_object.matcher(input);
                    // first check for declara with assignment through fucntion call or arithmetic operators
                    if(findGenrealizeViaObject.find())
                    {
                        variable = findGenrealizeViaObject.group(1);
                        if(findGenrealizeViaObject.group(3)!=null)// this is definition  and assignment 
                            definition.put(findGenrealizeViaObject.group(1), Integer.toString(counter));
                        variable_declaration.add(findGenrealizeViaObject.group(1));
                    }
                    else
                    {
                        while(findGeneralize.find())
                        {
                            if(findGeneralize.group(3)!=null)// this is definition  and assignment 
                                definition.put(findGeneralize.group(1), Integer.toString(counter));
                            variable_declaration.add(findGeneralize.group(1));
                        }
                    }
                }
                else if(findGeneralize.find())//this matches the variable assignment point.
                {
                    variable = findGeneralize.group(1);
                    if(variable_declaration.contains(variable))  // variable must be in definition list
                    {
                        if(!definition.containsKey(findGeneralize.group(1)))
                            definition.put(findGeneralize.group(1), Integer.toString(counter));
                        else
                        {
                            assignment = definition.get(findGeneralize.group(1));
                            definition.remove(findGeneralize.group(1));
                            definition.put(findGeneralize.group(1), assignment+" "+counter);
                        }
                    }
                }
                else if(findSimpleAssign.find())
                {
                    if(variable_declaration.contains(findSimpleAssign.group(1)))  // variable must be in definition list
                    {
                        if(!definition.containsKey(findSimpleAssign.group(1)))
                            definition.put(findSimpleAssign.group(1), Integer.toString(counter));
                        else
                        {
                            assignment = definition.get(findSimpleAssign.group(1));
                            definition.remove(findSimpleAssign.group(1));
                            definition.put(findSimpleAssign.group(1), assignment+" "+counter);
                        }
                    }
                }
                // USAGE NODES
                 if(line.contains("="))
                            line = line.substring(line.indexOf("="));                                   
                    findUsage = usage.matcher(line);  
                    while(findUsage.find())
                    {
                        variable = findUsage.group(0);
                        if(variable_declaration.contains(variable)) // this variable must first be assigned somewhere or else ignore this word
                        {
                            if(used.containsKey(variable)) // it is already present in usage path
                            {
                                usages = used.get(variable);
                                used.remove(variable);
                                used.put(variable, usages+" "+counter);
                            }
                            else
                            {
                                used.put(variable, Integer.toString(counter)+" ");
                            }
                        }
                    }
                }            
        }
        catch(Exception e)
        {
             System.out.println("Error Occured");
        }
        Iterator iterator = definition.entrySet().iterator();
        System.out.println("\n******************************* DU PATHS *******************************\n");
        System.out.printf("%10s %20s %20s", "S.NO","VARIABLE(S)","DU-PATH\n");
        counter=0;
        while(iterator.hasNext())
        {
            System.out.println("-----------------------------------------------------------------------");
            counter++;
            Map.Entry<String,String> e = (Map.Entry<String,String>) iterator.next();
            variable = e.getKey();
            defined = e.getValue().split("\\s+");
            if(defined.length>1)
            {
                for(int j=0;j<defined.length;j++)
                {
                    position = defined[j];
                    if(used.containsKey(variable))
                    {
                        counts = used.get(variable).split("\\s+");
                        for(int sn = 0;sn<counts.length;sn++)
                        {
                            if(j==0 && sn==0)
                                System.out.printf("%10d%20s%15s,%1s\n",counter,variable,defined[0],counts[0]);
                            else
                                System.out.printf("%45s,%1s\n",defined[j],counts[sn]);
                        }
                    }
                    else
                    {
                        output.append(position+", Not used\n");
                    }
                }
            }
            else
            {
                if(used.containsKey(variable))
                    {
                        counts = used.get(variable).split("\\s+");
                        System.out.printf("%10d%20s%15s,%1s\n",counter,variable,e.getValue(),counts[0]);
                        for(int sn = 1;sn<counts.length;sn++)
                        {
                             System.out.printf("%45s,%1s\n",e.getValue(),counts[sn]);
                           // output.append(e.getValue()+","+counts[sn]+"\n");
                        }
                    }
                else
                    {
                        output.append(e.getValue()+", Not used\n");
                    }
            }
        }
        
        iterator = definition.entrySet().iterator();
        System.out.println("\n\n\n******************************* DC PATHS *******************************\n");
        System.out.printf("%10s %20s %20s", "S.NO","VARIABLE(S)","DC-PATH\n");
        counter=0;
        while(iterator.hasNext())
        {
            System.out.println("-----------------------------------------------------------------------");
            counter++;
            Map.Entry<String,String> e = (Map.Entry<String,String>) iterator.next();
            variable = e.getKey();
            defined = e.getValue().split("\\s+");
            position = defined[defined.length-1];
            if(used.containsKey(variable))
            {
                counts = used.get(variable).split("\\s+");
                for(int sn = 0;sn<counts.length;sn++)
                {
                    if(sn==0)
                        System.out.printf("%10d%20s%15s,%1s\n",counter,variable,position,counts[0]);
                    else
                        System.out.printf("%45s,%1s\n",position,counts[sn]);
                }
            }
            else
            {
                output.append(position+", Not used\n");
            }
        }
         System.out.print("\nDo you want to continue (y/n)? ");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }
}
