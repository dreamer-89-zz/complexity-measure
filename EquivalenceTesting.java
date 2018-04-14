/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Suraj Singh
 */
class EquivalenceTesting {
    
static int BoundaryValues[];
    static int high,low,discriminant,average,counter;
    public static void setBoundaryValues(int low, int high)
    {
        BoundaryValues = new int[]{low-5,average,high+1};// Assuming three boundary values   
    }
    public static boolean valuesInRange(int a, int b, int c)
    {
        if(a>high || a<low|| b < low || b > high || c < low || c > high)
            return false;
        else
            return true;
    }
    public static void generateOutputEquivalence()
    {
        Random m = new Random();
        int count,a,b,c,d;
        boolean done[] = new boolean[4];
        if(low<=0)
            count=4;
        else
        {
            count = 3;
            done[3]= true;        
        }
        while(count>0)
        {
           a = m.nextInt(high+1);
           b = m.nextInt(high+1);
           c = m.nextInt(high+1);
           d = b*b - 4*a*c;
           if(d == 0 && a!=0 && valuesInRange(a,b,c) && !done[0])
           {
               calculateDiscriminant(a,b,c);       
               done[0] = true;
           }
           else if(d > 0 && valuesInRange(a,b,c)&& !done[1])
           {
               calculateDiscriminant(a,b,c);
               done[1] = true;
           }
           else if(d < 0 && valuesInRange(a,b,c)&& !done[2])
           {
               calculateDiscriminant(a,b,c);
               done[2] = true;
           }
           else if(a==0 && valuesInRange(a,b,c)&& !done[3])
           {
               calculateDiscriminant(a,b,c);
               done[3] = true;
           }
           else 
               continue;
           count--;
        }
    }
    public static void calculateDiscriminant(int a, int b, int c)
    {
        discriminant = b*b - 4*a*c;
        if(a>high || a<low|| b < low || b > high || c < low || c > high)
            System.out.printf("\n%-8d %3d %3d %-4d %-50s",counter,a,b,c,"Invalid Input... Values Out of Range !!!");
        else if(a==0)
            System.out.printf("\n%-8d %3d %3d %-4d %-30s",counter,a,b,c,"Not a Quadratic Equation");
        else if(discriminant>0)
            System.out.printf("\n%-8d %3d %3d %-4d %-30s",counter,a,b,c,"Real Roots");
        else if(discriminant==0)
            System.out.printf("\n%-8d %3d %3d %-4d %-30s",counter,a,b,c,"Equal Roots");
        else if(discriminant<0)
            System.out.printf("\n%-8d %3d %3d %-4d %-30s",counter,a,b,c,"Imaginary Roots");
        counter++;
    }
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int a,b,c,count=0,inputCount;
        char choice;
        do
        {  
        System.out.print("Enter the range for [a,b,c] => ");
        low = s.nextInt();
        high = s.nextInt();
        average = (low+high)/2;
        counter = 1;
        System.out.println(low+"<= a,b,c <="+high);
        System.out.println();
        // Auto generation of values of a,b anc c
        setBoundaryValues(low,high);
        System.out.print("\nINPUT EQUIVALENCE CLASSES");
        
        System.out.println("\nTestCase   a  b  c    Output");
        if(low<=0)
            calculateDiscriminant(0,average,average);//First input equivalence class
        for(int i=0;i<3;i++)
        {           
            for(int j=0;j<BoundaryValues.length;j++)
            {
                a = BoundaryValues[j];
                b = average;
                c = average;
                if(i==0) // a should be changed
                    calculateDiscriminant(a,b,c);  
                else if(i==1) // b should be 
                    calculateDiscriminant(b,a,c);
                else // c should be changed
                    calculateDiscriminant(c,b,a);
            }          
        }
        inputCount = counter-1;
        //calculateDiscriminant(average,average,average);
        System.out.println("\n\nOUTPUT EQUIVALENCE CLASSES ");
        counter = 1;
        System.out.println("\nTestCase   a  b  c    Output");
        //calculateDiscriminant(0,average,average);//First input equivalence class
        generateOutputEquivalence();
        counter--;
        count = (inputCount+counter);
        System.out.println("\n\nTotal number of test cases : "+inputCount+"+"+counter+"=>"+count);
        System.out.println("Number of Redundant Cases : 2");
        System.out.println("Total number of different test cases : "+(count-2));
        System.out.println("\nDo you want to continue ? \nY/y to continue. Other key to stop.");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }    
}
