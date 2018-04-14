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
 * @author Gaurav Kumar Shah
 */
class Decision_Table_Testing {
    static String conditions[][] = new String[11][11];
    static StringBuffer sb = new StringBuffer();
    static int numberer;
    static String[] stubs = new String[]{"C1: x<y+z","C2: y<x+z","C3: z<x+y","C4: x=y","C5: y=z","C6: z=x","A1: Not a Triangle",
        "A2: Equilateral Triangle","A3: Isoceles Triangle","A4: Scalene Triangle","A5: Impossible"};
    public static void main(String args[])
    {
        Scanner s = new Scanner(System.in);
        char choice;
        do
        {  
        numberer = 1;
        System.out.print("\nDecision Table Testing\n");       
        System.out.println("\nTESTCASE  X  Y  Z    OUTPUT");
        generateOutputEquivalence();
        setImpossibleActionEntry();
        System.out.println();
        System.out.println("\nDECISOIN TABLE\n");
        printDecisionTable();
        System.out.println("\nDo you want to continue(y/n) ? ");
        choice = s.next().charAt(0);
        System.out.println();
        }while(choice=='Y'||choice=='y');
    }    
    public static void printDecisionTable()
    {
        for(int i=0;i<11;i++)
        {
            System.out.printf("%-25s", stubs[i]);
            for(int j=0;j<11;j++)
                System.out.printf("%2s ",conditions[i][j]);
            System.out.println("");
        }
    }
    public static void setImpossibleActionEntry()
    {
        int im[] = new int[]{4,5,7};
        int row[] = new int[]{5,4,3};
        for(int i =0;i<im.length;i++)
            for(int j =0;j<6;j++)
                conditions[j][im[i]] = "1";  
        for(int i =0;i<im.length;i++)
            conditions[10][im[i]] = "*";
        for(int i =0;i<3;i++)
            conditions[row[i]][im[i]] = "0";
    }
    public static void generateOutputEquivalence()
    {
        Random m = new Random();
        int number=11,x,y,z;
        boolean executed[] = new boolean[11];   
        boolean condition1,condition2,condition3,c4,c5,c6;
        int range = 100,i,j;
        for(i =0;i<11;i++)
            for(j =0;j<11;j++)
                conditions[i][j]="";
        while(number>0)
        {
           condition1 = condition2 = condition3 = false;
           x = m.nextInt(range);
           y = m.nextInt(range);
           z = m.nextInt(range);
           if(x>y+z)
           {
               condition1=true; 
               if(!executed[0])
               {
                   System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Not a Triangle");
                   conditions[0][0] = "0";
                   for(j=1;j<6;j++)
                       conditions[j][0] = "-";
                   executed[0]=true;
                   number--;
                   conditions[6][0] = "*";
               }
           }
           if(y > x+z)
           {
               condition2 = true;
               if(!executed[1])
               {
                   System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Not a Triangle");
                   conditions[0][1] = "1";
                   conditions[1][1] = "0";
                   for(j=2;j<6;j++)
                       conditions[j][1] = "-";
                   executed[1]=true;
                   conditions[6][1] = "*";
                   number--;
               }
           }
           if(z > x+y)
           {
               condition3 = true;
               if(!executed[2])
               {
                   System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Not a Triangle");
                   for(j=0;j<2;j++)
                       conditions[j][2] = "1";
                   conditions[2][2] = "0";
                   for(j=3;j<6;j++)
                       conditions[j][2] = "-";
                   executed[2]=true;
                   conditions[6][2] = "*";
                   number--;
               }
           }
           if(!condition1 && !condition2 && !condition3 )
           {
                if(x==y && y==z && x==z)
                {
                    if(!executed[3])
                    {
                        System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Equilateral ");
                        for(j=0;j<6;j++)
                            conditions[j][3] = "1";
                        executed[3]=true;
                        conditions[7][3] = "*";
                        number--;
                    }
                    // Make impossible Entries.
                    else if(!executed[4])
                    {
                        System.out.printf("\n%-9d %-3s %-3s %-3s %-30s",numberer++,"X","X","X","Impossible");
                        //System.out.println()
                        executed[4] = true;
                        number--;
                    }
                    else if(!executed[5])
                    {
                        System.out.printf("\n%-9d %-3s %-3s %-3s %-30s",numberer++,"X","X","X","Impossible");
                        executed[5] = true;
                        number--;
                    }
                    else if(!executed[7])
                    {
                        System.out.printf("\n%-9d %-3s %-3s %-3s %-30s",numberer++,"X","X","X","Impossible");
                        executed[7] = true;
                        number--;
                    }
                }
                else if(x==y && y!=z && x!=z)
                {
                    if(!executed[6])
                    {
                        System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Isosceles ");
                        for(j=0;j<4;j++)
                            conditions[j][6] = "1";
                        for(;j<6;j++)
                            conditions[j][6] = "0";
                        conditions[8][6] = "*";
                        executed[6]=true;
                        number--;
                    }
                }
                else if(x!=y && y==z && x!=z)
                {
                    if(!executed[8])
                    {
                        System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Isosceles ");
                        executed[8]=true;
                        for(j=0;j<6;j++)
                            conditions[j][8] = "1";
                        conditions[8][8]="*";                        
                        conditions[3][8] = "0";
                        conditions[5][8] = "0";
                        number--;
                    }
                }
                else if(x!=y && y!=z && x==z)
                {
                    if(!executed[9])
                    {
                        System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Isosceles ");
                        executed[9]=true;
                        for(j=0;j<6;j++)
                            conditions[j][9] = "1";
                        conditions[3][9] = "0";
                        conditions[4][9] = "0";
                        conditions[8][9] = "*";
                        number--;
                    }
                }
                else if(x!=y && y!=z && x!=z)
                {
                    if(!executed[10])
                    {
                        System.out.printf("\n%-8d %3d %3d %-4d %-30s",numberer++,x,y,z,"Scalene ");
                        executed[10]=true;
                        for(j=0;j<3;j++)
                            conditions[j][10] = "1";
                        for(;j<6;j++)
                            conditions[j][10] = "0";
                        conditions[9][10] = "*";
                        number--;
                    }
                }
           }
        }
    }    
}
