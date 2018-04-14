package software_testing_meaures;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package college_lab;
//
///**
// *
// * @author Suraj Singh
// */
//import java.util.Scanner;
//
///**
// *
// * @author Suraj Singh
// */
//class Triangle {
//static int BoundaryValues[],RobustnessValues[],WorstCase[];
//    static int high,low,discriminant,counter;
//    public static void setBoundaryValues(int low, int high)
//    {
//        BoundaryValues = new int[]{low,low+1,high-1,high};// Assuming four boundary values        
//        RobustnessValues= new int[]{low-1,low,low+1,(low+high)/2,high-1,high};// Assuming six boundary values    
//        WorstCase = new int[]{low,low+1,(low+high)/2,high-1,high};// Assuming five boundary values        
//    }
//    public static boolean isValidTriangle(int a, int b, int c)
//    {
//        if(a > b+c)
//            return false;
//        if(b > a+c )
//            return false;
//        if(c > a+b)
//            return false;
//        return false;
//    }
//    public static void main(String args[])
//    {
//        Scanner s = new Scanner(System.in);
//        int a,b,c,count=0,type,average;
//        char choice;
//        do
//        {            
//        System.out.println("\nEnter the type of testing to be performed !!!");
//        System.out.println("\n1.\tBoundary Value Analysis.");
//        System.out.println("\n2.\tRobustness  Testing");
//        System.out.println("\n3.\tWorst Case Testing\n\nEnter your choice !!!");
//        type = s.nextInt();
//        System.out.println("\nEnter the range of a,b,c");
//        System.out.print("Enter the values for [a,b,c] => ");
//        a = s.nextInt();
//        b = s.nextInt();
//        c = s.nextInt();
//        counter = 1;
//        System.out.println();
//        System.out.println("\nTestCase   a  b  c    Output");
//        // Auto generation of values of a,b anc c
//        setBoundaryValues(low,high);
//        switch(type)
//        {
//            case 1 : 
//                for(int i=0;i<3;i++)
//                {           
//                    for(int j=0;j<BoundaryValues.length;j++)
//                    {
//                        a = BoundaryValues[j];
//                        b = average;
//                        c = average;
//                        if(i==0) // a should be changed
//                            calculateDiscriminant(a,b,c);  
//                        else if(i==1) // b should be 
//                            calculateDiscriminant(b,a,c);
//                        else // c should be changed
//                            calculateDiscriminant(c,b,a);
//                    }          
//                }
//                calculateDiscriminant(average,average,average);
//                break;
//            case 2:
//                for(int i=0;i<3;i++)
//                {           
//                    for(int j=0;j<RobustnessValues.length;j++)
//                    {
//                        a = RobustnessValues[j];
//                        b = average;
//                        c = average;
//                        if(i==0) // a should be changed
//                            calculateDiscriminant(a,b,c);  
//                        else if(i==1) // b should be changed
//                            calculateDiscriminant(b,a,c);
//                        else // c should be changed
//                            calculateDiscriminant(c,b,a);
//                    }          
//                }
//                calculateDiscriminant(average,average,average);
//                break;
//            case 3 :  
//                for(int j=0;j<WorstCase.length;j++)
//                    {
//                        a  = WorstCase[j];                
//                        for(int k=0;k<WorstCase.length;k++)
//                        {
//                            b = WorstCase[k];
//                            for(int l=0;l<WorstCase.length;l++)
//                            {
//                                c = WorstCase[l];
//                                if(count==0) // a should be changed
//                                    calculateDiscriminant(a,b,c);  
//                                else if(count==1) // b should be changed
//                                    calculateDiscriminant(b,a,c);
//                                else // c should be changed
//                                    calculateDiscriminant(c,b,a);
//                            }                    
//                        }
//                        count++;
//                    }         
//                break;
//            default : System.out.println("\n Please enter the correct choice ");               
//        }        
//        System.out.println("\nDo you want to continue ? \nY/y to continue. Other key to stop.");
//        choice = s.next().charAt(0);
//        System.out.println();
//        }while(choice=='Y'||choice=='y');
//    }    
//}
