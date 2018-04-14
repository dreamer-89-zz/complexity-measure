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
class SW_BoundaryValueAnalysis {
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        int a,b,c,D,low,high;
        char choice;
        do
        {
        System.out.print("Enter the range for [a,b,c]=> ");
        low = s.nextInt();
        high = s.nextInt();
        System.out.println(low+"<= a,b,c <="+high);
        System.out.println("\nEnter the values of a, b and c ");
        a = s.nextInt();
        b = s.nextInt();
        c = s.nextInt();
        D = b*b - 4*a*c;
        System.out.print("\n"+a+"x\u00b2+"+b+"x+"+c+" => ");
        if(a>high || a<low|| b < low || b > high || c < low || c > high)
            System.out.println("Invalid Input... Values Out of Range !!!");
        else if(a==0)
            System.out.println("Not a Quadratic Equation");
        else if(D>0)
            System.out.println("Real Roots");
        else if(D==0)
            System.out.println("Equal Roots");
        else if(D<0)
            System.out.println("Imaginary Roots");
        System.out.println("\nDo you want to continue ? \nY/y to continue. Other key to stop.");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }
}
