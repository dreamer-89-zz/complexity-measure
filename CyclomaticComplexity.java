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
//class CyclomaticComplexity {
//    public static void main(String arfs[])
//    {
//        Scanner s = new Scanner(System.in);
//        int i;
//        String input = s.next();
//        for(i =0;i<input.length()/2;i++)
//        {
//            if(input.charAt(i)!=input.charAt(input.length()-i-1))
//                break;
//        }
//        if(i==input.length()/2)
//            System.out.println("Palindrome");
//        else
//            System.out.println("Not a Palindrome");
//    }
//}
/*
Program to find the result of student
*/
class CyclomaticComplexity
{
     public static void main(String arfs[])
    {
        Scanner s = new Scanner(System.in);  
	int a,sum = 5;
        System.out.println("Enter marks ");
        int n = s.nextInt();
        if(n < 100 && n > 0)
        {
            System.out.println("Marks obtained "+n);
            if(n>50)
            {
                System.out.println("Student Passed ");
            }
            else
            {
                System.out.println("Student Failed");
            }
        }
	System.out.println("The End !!!");
    }
}