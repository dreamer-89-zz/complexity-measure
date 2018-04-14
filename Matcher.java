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
class Matcher
{
    static int a,b;
    public static void Greater()
    {     
	if(a>b)
		System.out.println("a is greater than b");
	else
		System.out.println("b is greater than a");   
    }
     public static void main(String arfs[])
    {
        System.out.println("Matcher Called");
    }
}