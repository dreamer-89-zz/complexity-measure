/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

/**
 *
 * @author Suraj Singh
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//p 20507
//It is prime
//q 19927
//It is prime
//n 408642989
//it is not prime and is divisible by 19927 20507.0
//phi 408602556
//it is not prime and is divisible by 2 2.0430128E8
//e 11
//It is prime
//d 1494103

public class Test
{
    public static void main( String args[] ){

      // String to be scanned to find the pattern.
      //String line = "int a,b,c=100,e=200;";
        // String line = "if(a>10) System.out.println(ab);"; to find keywords
        String line = "        int a, sum = 5, b = 13;";//"  int a,sum=1;";   //         int a,sum = 5;
        String variables = "12  12";
        System.out.println(1494103*11);
        //System.out.println(variables.split(" ")[0] + variables.split(" ")[1]+ variables.split(" ")[2]);
        System.out.println(Integer.toBinaryString(57));
        System.out.println((730-561));
      System.out.println(Integer.toBinaryString(730-561));
      //
      String input = "(a>b)";
      Pattern mutationChange = Pattern.compile("\\(([a-zA-Z]+)(<|>|<=|>=|==)([a-zA-Z]+)\\)");
      Matcher operator = mutationChange.matcher(input);
      String var1,operator1,var2;
      if(operator.find())
      {
          var1 = operator.group(1);
          operator1 = operator.group(2);
          var2 = operator.group(3);
          System.out.println(var1+" "+operator1+" "+var2);
      }      
      
      Pattern declaration = Pattern.compile("\\b(int|float|String|long)\\b(.*)"); // declaration
      //Pattern assignment = Pattern.compile("(\\b[a-z0-9]+\\b)=(.*)"); 
      Pattern generalize = Pattern.compile("([a-z][a-z0-9]*)(\\[])?(\\s*=\\s*)?(\\b[a-z0-9]+)?[,;]");      
      Pattern assign_definition_via_object = Pattern.compile("([a-z][a-z0-9]*)(\\s*=\\s*)?([a-z0-9])+.([a-z0-9])+(\\(\\))?[,;]");      // (\\([a-zA-Z]+))?
      Pattern usage = Pattern.compile("\\b[a-zA-Z0-9]*\\b");
      //Pattern justVar = Pattern.compile("[a-z][a-z0-9]*[,;]");
     // Pattern varWithVal = Pattern.compile(line)
      // Now create matcher object.
      
      Matcher dec = declaration.matcher(line);
      //Matcher ass = assignment.matcher(line);
      Matcher var,used,varWithObject;
      System.out.println(line);
      used = usage.matcher(line);
      Pattern simple_assign = Pattern.compile("([a-zA-z]+)(\\s*=\\s*)(.*);");
      Matcher simp = simple_assign.matcher("sum = a+4;");
      if(simp.find())
      {
          System.out.println(simp.group(1)+" "+simp.group(2)+" "+simp.group(3));
          System.out.println(simp.group(0));
      }
//      while(used.find())
//      {S
//          System.out.println(used.group(0));//+" "+used.start()+" "+used.end());
//      }
//      if (dec.find( )) {
//         //System.out.println(dec.group(2));
//         variables = dec.group(2);
//         var = generalize.matcher(variables);
//         //justVariable = justVar.matcher(variables);
//         while(var.find())
//         {             
//             System.out.println(var.group(1)+" "+var.group(2)+" "+var.group(3)+" "+var.group(4));
//             if(var.group(3)==null)
//             {
//                 System.out.println("Variable declared only "+var.group(1));
//             }
//             else
//             {
//                 System.out.println("Variables declared with value "+var.group(1)+"=>"+var.group(4));
//             }
//         }
//         varWithObject = assign_definition_via_object.matcher(variables);
//         while(varWithObject.find())
//         {
//             System.out.println(varWithObject.group(1)+" "+varWithObject.group(2)+" "+varWithObject.group(3));
//         }
//      } 
   }
}