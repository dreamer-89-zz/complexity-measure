/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package software_testing_meaures;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.net.*;
import javax.swing.JLabel;

/**
 *
 * @author Suraj Singh
 */
class Mutation_Testing2 extends Applet implements ActionListener
{
    TextField input,output;
   Label label1,label2;
   Button b1;
   JLabel lbl;
   int num, sum = 0;
    public void init()
    {
        String link = "google";
        Button b = new Button(link);
        //b.add();
        b.addActionListener(this);
        add(b);
       Label label1,label2;
       TextField input,output;
       Button b1;
       JLabel lbl;
       label1 = new Label("please enter number : ");
      add(label1);
      label1.setBackground(Color.yellow);
      label1.setForeground(Color.magenta);
      input = new TextField(5);
      add(input);
      label2 = new Label("Sum : ");
      add(label2);
      label2.setBackground(Color.yellow);
      label2.setForeground(Color.magenta);
      output = new TextField(20);
      add(output);
      b1 = new Button("Add");
      add(b1);
      b1.addActionListener(this);
      lbl = new JLabel("Swing Applet Example. ");
      add(lbl);
      setBackground(Color.yellow);
    }
   public void actionPerformed(ActionEvent ae){
      try{
         num = Integer.parseInt(input.getText());
         sum = sum+num;
         input.setText("");
         output.setText(Integer.toString(sum));
         lbl.setForeground(Color.blue);
         lbl.setText("Output of the second Text Box : " 
         + output.getText());
      }
      catch(NumberFormatException e){
         lbl.setForeground(Color.red);
         lbl.setText("Invalid Entry!");
      }
        Button source = (Button)ae.getSource();
        String link = "http://www."+source.getLabel()+".com";
        try{
            AppletContext a = getAppletContext();
            URL url = new URL(link);
            a.showDocument(url, "_blank");
        }
        catch(MalformedURLException e)
        {
            System.out.println(e.getMessage());
        }
}
