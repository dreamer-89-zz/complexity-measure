/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guarav Shah
 */
class graph
{
    graph[] adjacents = new graph[2]; // each node can have two outgoing branches out of it.
    String type; // will store the line numbers which this node comprise
    int start;
    int end;
    int index=0; // will represent the number of outgoing edges out of a node.
    public String toString()
    {
        return " N"+this.index+" "+this.start+" "+this.end;
    }
}
class CyclomaticComplexity_Using_GraphMatrix {
    static ArrayList<graph> nodes;
    static int number;
    public static void print_Graph_Matrix()
    {
        graph node,left,right;
        int graphMatrix[][] = new int[nodes.size()][nodes.size()],value[] = new int[nodes.size()],verticalSum=0;
        System.out.println("\n");
        System.out.println("******************** CONNECTION MATRIX ********************");
        System.out.print("    ");
        for(int i=0;i<nodes.size();i++)
        {
            System.out.print("N"+(i+1)+"  ");
        }
        System.out.println();
        for(int i=0;i<nodes.size();i++)
        {
            value[i]=0;
            node = nodes.get(i);
            // left link is not null
            if(node.adjacents[0]!=null)
            {
                left = node.adjacents[0];
                graphMatrix[i][left.index-1]++;
                value[i]++;
            }
            if(node.adjacents[1]!=null)
            {
                right = node.adjacents[1];
                graphMatrix[i][right.index-1]++;
                value[i]++;
            }
        }
        for(int i=0;i<nodes.size();i++)
        {
            System.out.print("N"+nodes.get(i).index+"  ");
            for(int j=0;j<nodes.size();j++)
            {
                System.out.print(graphMatrix[i][j]+"   ");
            }
            if(value[i]!=0)
            {
                verticalSum+= value[i]-1;
                System.out.print(value[i]+"-1="+(value[i]-1));
            }
            else
            {
                System.out.print(value[i]+"-0=0");
            }
            System.out.println();
        }
        System.out.println("\nCYCLOMATIC COMPLEXITY USING CONNECTION MATRIX "+verticalSum+"+1="+(verticalSum+1));
    }
    public static void print_Nodes_Lines()
    {
        graph node;
        System.out.println("\n********* Mapping Flow Graph Node To DD Graph Nodes *********\n");
        System.out.println("Program   DD Path   Adjacent Nodes");
        System.out.println("Graph     Graph     Left     Right");
        System.out.println("Nodes     Nodes     Edge     Edge");
        System.out.println("----------------------------------");
        for(int i=0;i<nodes.size();i++)
        {
            node = nodes.get(i);
            System.out.printf("%2s-%2s %6s",node.start,node.end,"N"+node.index);
            if(node.adjacents[0]!=null)
                //System.out.print(" "+node.adjacents[0].toString()+" ");
                System.out.printf("%10s","N"+node.adjacents[0].index);
            if(node.adjacents[0]!=null && node.adjacents[1]!=null)
                System.out.printf("%10s","N"+node.adjacents[1].index);
            else if(node.adjacents[1]!=null)
                System.out.print("Adjacent Nodes "+node.adjacents[1].toString()+" ");
            System.out.println();
        }
    }
public static void main(String arfs[])
{
    Scanner s = new Scanner(System.in);
    String line,path;
    // alternateNode will take care of statements which are part of one directional output of conditional statements.
    boolean conditionFound=false,alternateNode=false,mergeNode=false;
    StringBuffer sb = new StringBuffer(); 
    Stack<Integer> scope = new Stack<Integer>(); // 1 means top element is opening bracket, 2 means closing bracket
    Stack<graph> stack = new Stack<graph>();
    nodes = new ArrayList<graph>();
    
    int counter=0,lastConditionPoint=1; //  lastConditionPoint will store the last time before a condition node
    // Pattern definition section
    Pattern decision = Pattern.compile("\\b(if|for|while|do)\\b(.*)"); // decision
    // left node will store the node on left of conditional node, right node will store the node on right side of the conditional node.
    graph node,last=null,left=null,right=null,temp=null; // last node will store the last condition node found in order to trace the path along two outgoing edges.
    // Matcher definition section
    Matcher dM,bM;
    do
    {
    System.out.print("Enter file name : ");
    path = "C:\\Users\\Suraj Singh\\Desktop\\"+s.next()+".txt";
    System.out.println("\n\n******************** FILE CONTENT ********************");
    try
    {
        BufferedReader r = new BufferedReader(new FileReader(path));
        while( (line=r.readLine())!=null)
        {
            counter++;// store the line number in the sampled program.
           dM = decision.matcher(line);
           System.out.print(counter+" ");
           System.out.println(line);
           if(dM.find()) // We found a conditional node
           {
//               conditionFound = true;
               node = new graph();
               node.start=lastConditionPoint;
               node.end = counter;// will store the starting and ending line numbers for which this node is defined.
               lastConditionPoint = counter+1;
               node.index = ++number;
               if(stack.size()!=0) // nested conditional statements
               {
                   temp = stack.peek();
                   temp.adjacents[0] = node; // set the left outgoing edge
               }
              // last = node; // last node is stored in order to set the pointers when scope of this condition ends
               stack.push(node);
               nodes.add(node);
               conditionFound = true;
               //nodes.add(new graph())
               // Add code for this node
           }
           else if(line.contains("{") && conditionFound) // beginning of new block scope
           {               
               conditionFound=false;
               scope.add(1);
           }
           else if(line.contains("else"))
           {
               alternateNode = true;
           }
           else if(line.contains("}") && scope.size()!=0 && !alternateNode) // we found the complete block scope with open and closing brackets
           {
               //inside the scope we need to create one single graph node
               node = new graph();
               node.start = lastConditionPoint;
               node.end = counter;
               node.index = ++number;
               last = stack.peek();
               if(left!=null && right!=null)
                   left.adjacents[0] = right.adjacents[0] = node;
               if(last.adjacents[0]==null)
                    last.adjacents[0] = node; // if condition is true then statements inside scope are executed and form the sibling of last node.
               left = node;
               nodes.add(node);
               mergeNode = false;
               lastConditionPoint = counter+1;
               scope.pop();
           }
           else if(line.contains("}") && alternateNode) // take care of else part
           {
                node = new graph();
                node.start = lastConditionPoint;
                node.end = counter;
                node.index = ++number;
                nodes.add(node);
                last = stack.pop();
                right = node;
                //last.adjacents[1] = new graph();
                if(last.adjacents[1]==null)
                    last.adjacents[1] = node; 
                lastConditionPoint = counter+1;
                alternateNode = false;
                mergeNode = true;
                last = null; // both left and right are set of the conditional node
           }
        } // end of while loop
        
        // To handle the case when last statements are part of some condition statements
        node = new graph();
        node.start = lastConditionPoint;
        node.end = counter;
        node.index = ++number;
        nodes.add(node);
        if(!mergeNode) // alternateNode = true, both outgoing edges hasn't been taken care of
        {
            last.adjacents[1] = new graph();
            last.adjacents[1] = node;            
        }
        else // if alternateNode is present, then this is the merge node
        {
            // set the link of the nodes inside the conditional statements.
            left.adjacents[0] = node;
            right.adjacents[0] = node;
        }
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println(e.toString());
    }    
    }while(false); // run only once :)
    print_Nodes_Lines();
    print_Graph_Matrix();
 }
}





	