package projectoopt;
import javax.swing.JFrame;

public class ProjectOOPT {    
    public static void main(String[] args) {
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        mainWindow open = new mainWindow();
        open.mainMenu();
    }
}

////////////Class///////////////////////////////

package projectoopt;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class mainWindow {
    
    JFrame mainMenu = new JFrame();
    JFrame pMenu    = new JFrame();
    JFrame rMenu    = new JFrame();
    JFrame oMenu    = new JFrame();
    
    JButton submit1 = new JButton("Submit");
    JButton submit2 = new JButton("Submit");
    listener listen = new listener();
    ImageIcon img   = new ImageIcon("abc.png");
    
    String infosss []      = new String[4];
    records recorder       = new records();
    JTextArea ta_reason    = new JTextArea(20,18);
    JTextArea ta_medi      = new JTextArea(10 , 17);
    
    JTextField tf_name;
    JTextField tf_age;
    JTextField tf_contNum;
    JTextField tf_gender;
    
    JLabel lName,lage;
    JLabel lcontNum,lmedi;
    JLabel lreason,lgender;
    JLabel linebreak,lrecords;
    
    JButton newpatient,oldpatient;
    JButton exit,submit;
    JButton back,patientrecords;
    
    int height = 600;
    int width  = 800;
    
    String apNum;

    static int    MRnum,numAp;
    static String name,age;
    static String contNum,gender;
    static String reason,medi;
    
    public void mainMenu () {
        
        frame(mainMenu,"Dr. Mike Varshavski");
        
        JPanel panel1  = new JPanel();
        newpatient     = new JButton("New Patient");
        oldpatient     = new JButton("Returning Patient");
        patientrecords = new JButton("Patient record");
        exit           = new JButton("Exit");
        
        newpatient.addActionListener(listen);
        oldpatient.addActionListener(listen);
        patientrecords.addActionListener(listen);
        exit.addActionListener(listen);
        
        panel1.setBackground(Color.LIGHT_GRAY);
        
        effect(newpatient);
        effect(oldpatient);
        effect(patientrecords);
        effect(exit);

        panel1.add(newpatient);
        panel1.add(oldpatient);
        panel1.add(patientrecords);
        panel1.add(exit);
        
        mainMenu.add(panel1);
    }
    public void newPatient () {
        
        frame(pMenu, "New Patient");
       
        JPanel panel2 = new JPanel();
        tf_name       = new JTextField(15);
        tf_age        = new JTextField(3);
        tf_contNum    = new JTextField(10);
        tf_gender     = new JTextField(5);   
        lName         = new JLabel("Enter your name: ");
        lage          = new JLabel("Enter your age: ");
        lcontNum      = new JLabel("Enter your contact number: ");
        lreason       = new JLabel("State your reason for the visit: ");
        lmedi         = new JLabel("Mention medicine you are on: ");
        lgender       = new JLabel("Enter your gender: ");
        linebreak     = new JLabel("                           ");
        
        submit1.addActionListener(listen);
        
        panel2.add(lName);
        panel2.add(tf_name);
        panel2.add(lage);
        panel2.add(tf_age);
        panel2.add(lgender);
        panel2.add(tf_gender);
        panel2.add(linebreak);
        panel2.add(lcontNum);
        panel2.add(tf_contNum);
        panel2.add(lmedi);
        panel2.add(ta_medi);
        panel2.add(lreason);
        panel2.add(ta_reason);
        panel2.add(submit1);
        
        ta_reason.setLineWrap(true);
        ta_reason.setWrapStyleWord(true);
        ta_medi.setWrapStyleWord(true);
        ta_medi.setWrapStyleWord(true);
        
        panel2.setBackground(Color.LIGHT_GRAY);
        
        effect(submit1);
        
        pMenu.add(panel2);
     }
    public void returnPatient () {
        
        String recN = JOptionPane.showInputDialog("Enter your MR number");
    
        frame(oMenu,"Returning Patient");
        
        JPanel panel4     = new JPanel();
        JTextArea oldInfo = new JTextArea(6,5);
        JLabel lab1       = new JLabel("State the reason for your visit");
        JLabel lab2       = new JLabel("Enter any medication you are currently on");
        
        String recs [] = recorder.rPatient(recN);
        oldInfo.setFont(new Font("Serif",Font.PLAIN,20));
        
        for (int i = 0; i < recs.length; i++){
            
            oldInfo.append(recs[i]+"\n");
            infosss[i] = recs[i];
        }
        
        oldInfo.setEditable(false);
        submit2.addActionListener(listen);
        effect(submit2);
        
        panel4.setBackground(Color.LIGHT_GRAY);
        
        panel4.add(oldInfo);
        panel4.add(lab1);
        panel4.add(ta_reason);
        panel4.add(lab2);
        panel4.add(ta_medi);
        panel4.add(submit2);
        
        ta_reason.setLineWrap(true);
        ta_reason.setWrapStyleWord(true);
        ta_medi.setWrapStyleWord(true);
        ta_medi.setWrapStyleWord(true);
        
        oMenu.add(panel4);
    }
    public void patientRecord () {
        
        String rec = JOptionPane.showInputDialog("Enter the MR number of the patient");
        
        frame(rMenu, "Records");
        
        JPanel panel4 = new JPanel();
        JLabel info   = new JLabel(recorder.pRecords(rec));
        back          = new JButton("Back");
        
        back.addActionListener(listen);
        effect(back);
        
        panel4.setBackground(Color.LIGHT_GRAY);
        
        panel4.add(info);
        panel4.add(back);
        
        rMenu.add(panel4);
    }
    
    private class listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newpatient) {
                mainMenu.dispose();
                newPatient();
            }
            if (e.getSource() == oldpatient) {
                returnPatient();                
                mainMenu.dispose();
            }   
            if (e.getSource() == patientrecords) {
                patientRecord();
            }   
            if (e.getSource() == exit) {
                System.exit(0);
            }
            if (e.getSource() == submit1){
                try {
                    recorder.read();
                    recorder.apointmentNum();

                    MRnum   = recorder.idNum;
                    numAp   = recorder.apNum;
                    name    = tf_name.getText();
                    age     = tf_age.getText();
                    gender  = tf_gender.getText();
                    contNum = tf_contNum.getText();
                    reason  = ta_reason.getText();
                    medi    = ta_medi.getText();
                    
                    JOptionPane.showMessageDialog(null,"MR Number "+MRnum+"\n"+ "Mr/Ms."+name+"\n"+"Age: "+age+"\n"+"Gender : "+gender+"\n"+"reason: \n"+reason+"\n"+"Current medication "+medi+"\n"+"Apointment No: \n"+numAp);
                    pMenu.dispose();
                    recorder.storeData();
                    
                    System.exit(0);
                }catch (Exception ex) {}
            }
            if (e.getSource() == submit2){
               try{
                   MRnum  = Integer.parseInt(infosss[0]);
                   name   = infosss[1];
                   age    = infosss[2];
                   gender = infosss[3];
                   reason = ta_reason.getText();
                   medi   = ta_medi.getText();
                   recorder.apointmentNum();
                   numAp  = recorder.apNum;

                   oMenu.dispose();
                   JOptionPane.showMessageDialog(null,"MR Number "+MRnum+"\n"+ "Mr/Ms."+name+"\n"+"Age: "+age+"\n"+"Gender: "+gender+"\n"+"reason: \n"+reason+"\n"+"Current medication "+medi+"\n"+"Apointment No: \n"+numAp);
                   
                   System.exit(0);
               }catch (Exception ex){}
            }
            if (e.getSource() == back) {
                rMenu.dispose();
            }
        }}
    public void effect(JButton button) {
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                
                button.setBackground(Color.gray);
                button.setForeground(Color.white);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
            
                button.setBackground(UIManager.getColor("control"));
                button.setForeground(Color.BLACK);
            }
        });   
    }
    public void frame (JFrame frame, String title) {
        frame.setSize(width, height);
        frame.setTitle(title);
        frame.setIconImage(img.getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}

//////////////class////////////////////////////////////

package projectoopt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class records {
    
    String[] mydata = new String[4];
    String[] excep  = {"Wrong MR number"};
    static int idNum,apNum;
    
    public void storeData()  {
        try{
            File data        = new File("records.csv");
            FileWriter fw    = new FileWriter(data, true);
            PrintWriter pw   = new PrintWriter(fw);
            StringBuilder sb = new StringBuilder();

            sb.append(mainWindow.MRnum);
            sb.append(",");
            sb.append(mainWindow.name+" ");
            sb.append(",");
            sb.append(mainWindow.age+" ");
            sb.append(",");
            sb.append(mainWindow.gender+" ");
            sb.append(",");
            sb.append(mainWindow.contNum+" ");
            sb.append("\r\n");

            pw.write(sb.toString());
            pw.close();
        }catch(Exception ex){}
    }
   
    public void read () throws Exception {
        
            FileReader fr     = new FileReader("idNum.csv");    
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            
            while ((line = br.readLine()) != null) {
             
                String[] values = line.split("\n");
                idNum = Integer.parseInt(values[0]);
                idNum++;
            }
        
           File id          = new File("idNum.csv");
           FileWriter fw    = new FileWriter(id, false);
           PrintWriter pw   = new PrintWriter(fw);
           StringBuilder sb = new StringBuilder();
           
           String number = Integer.toString(idNum);
           
           sb.append(number);
           sb.append("\n");
           
           pw.write(sb.toString());
           pw.close();
    }
    public void apointmentNum () throws Exception {
         
        FileReader fr = new FileReader("apointments.csv");
    
            BufferedReader br = new BufferedReader(fr); 
            
            String line;
            while ((line = br.readLine()) != null) {
             
                String[] values = line.split("\n");
                apNum = Integer.parseInt(values[0]);
                
                if (apNum > 24 ){apNum = 0;}
                apNum++;
            }
        
           File id          = new File("apointments.csv");
           FileWriter fw    = new FileWriter(id, false);
           PrintWriter pw   = new PrintWriter(fw);
           StringBuilder sb = new StringBuilder();
           
           String number = Integer.toString(apNum);
           
           sb.append(number);
           sb.append("\n");
           
           pw.write(sb.toString());
           pw.close();
    }
    public String pRecords (String a) {
     
        try {
            
            FileReader fr     = new FileReader("records.csv");
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] values = line.split("\n");
                String[] vals = values[0].split(",");
                if(vals[0].equals(a)){return values[0];}
            }
            
        }catch (Exception ex) {}
        return "Record not found";
    }
    public String[] rPatient (String recNum) {
        try{
            
            FileReader fr     = new FileReader("records.csv");
            BufferedReader br = new BufferedReader(fr);
            
            String line;
            while ((line = br.readLine()) != null) {
                
                String[] values = line.split("\n");
                String[] vals = values[0].split(",");
                
                if(vals[0].equals(recNum)){
                     
                    for (int i = 0; i < mydata.length; i++){
                    vals = values[0].split(",");
                    mydata[i] = vals[i];
                    }
                    return mydata;
                }
            }
        }catch (Exception ex){}
        return excep;
    }
}