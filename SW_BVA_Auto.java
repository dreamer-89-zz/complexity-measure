/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.util.Scanner;

/**
 *
 * @author Suraj Singh
 */
class SW_BVA_Auto {
    static int BoundaryValues[];
    static int high,low,discriminant,counter;
    public static void setBoundaryValues(int low, int high)
    {
        BoundaryValues = new int[]{low,low+1,high-1,high};// Assuming four boundary values        
    }
    public static void calculateDiscriminant(int a, int b, int c)
    {
        System.out.print(counter+++".  "+a+"x\u00b2+"+b+"x+"+c+" => ");
        discriminant = b*b - 4*a*c;
        if(a>high || a<low|| b < low || b > high || c < low || c > high)
            System.out.println("Invalid Input... Values Out of Range !!!");
        else if(a==0)
            System.out.println("Not a Quadratic Equation");
        else if(discriminant>0)
            System.out.println("Real Roots");
        else if(discriminant==0)
            System.out.println("Equal Roots");
        else if(discriminant<0)
            System.out.println("Imaginary Roots");
    }
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int a,b,c,average;
        char choice;
        do
        {
        System.out.print("Enter the range for [a,b,c] => ");
        low = s.nextInt();
        high = s.nextInt();
        counter = 1;
        average = (low+high)/2;
        System.out.println(low+"<= a,b,c <="+high);
        System.out.println();
        // Auto generation of values of a,b anc c
        setBoundaryValues(low,high);
        for(int i=0;i<3;i++)
        {           
            for(int j=0;j<BoundaryValues.length;j++)
            {
                a = BoundaryValues[j];
                b = average;
                c = average;
                if(i==0) // a should be changed
                    calculateDiscriminant(a,b,c);  
                else if(i==1) // b should be changed
                {
                    calculateDiscriminant(a,b,c);
                }
                else // c should be changed
                {
                    calculateDiscriminant(c,b,a);
                }
            }          
        }
        calculateDiscriminant(average,average,average);
        System.out.println("\nDo you want to continue ? \nY/y to continue. Other key to stop.");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }
}
