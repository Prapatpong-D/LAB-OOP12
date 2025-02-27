// use gridlayout(4,0);
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;


public class StudentView implements ActionListener,WindowListener{
    private JFrame jf;
    private JPanel pnm,pnb,pnInfo,pntext,pnBotton;
    private JTextArea showmoney;
    private JTextField idfill,namefill;
    private JLabel id,name,money;
    private JButton deposit,witdraw;
    private int balance=0;
    private final String FILE_NAME="StudentM.dat";
    
    public StudentView(){
        jf = new JFrame("Student skibidi");
        pnm = new JPanel();
        pnb = new JPanel();
        pnInfo = new JPanel();
        pntext = new JPanel();
        pnBotton = new JPanel();
        showmoney = new JTextArea("0");
        idfill = new JTextField();
        namefill = new JTextField();
        showmoney.setEditable(false);
        id = new JLabel("ID: ");
        name = new JLabel("Name: ");
        money = new JLabel("Money: ");
        deposit = new JButton("Deposit");
        witdraw = new JButton("Withdraw");
        
        deposit.addActionListener(this);
        witdraw.addActionListener(this);
        jf.addWindowListener(this);
        
        pnm.setLayout(new GridLayout(2,2));
        pnb.setLayout(new GridLayout(1,2));
        pnInfo.setLayout(new GridLayout(3,1));
        pntext.setLayout(new GridLayout(3,1));
        pnBotton.setLayout(new GridLayout(1,2));
        
        pnBotton.add(deposit);
        pnBotton.add(witdraw);
        pntext.add(idfill);
        pntext.add(namefill);
        pntext.add(showmoney);
        pnInfo.add(id);
        pnInfo.add(name);
        pnInfo.add(money);
        pnb.add(pnInfo);
        pnb.add(pntext);
        pnm.add(pnb);
        pnm.add(pnBotton);
        
        jf.add(pnm);
        jf.setSize(400,500);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if(command.equals("Deposit")){
            balance += 100;
            showmoney.setText(String.valueOf(balance));
        }else if(command.equals("Withdraw")){
            balance -= 100;
            showmoney.setText(String.valueOf(balance));
        }
    }
     private void SaveData(){
    try {
        int ids = Integer.parseInt(idfill.getText());
        String studentName = namefill.getText();
        balance = Integer.parseInt(showmoney.getText());  

        Student s = new Student(studentName, ids, balance);
        
        FileOutputStream fOut = new FileOutputStream(FILE_NAME, false);  
        ObjectOutputStream oout = new ObjectOutputStream(fOut);
        
        oout.writeObject(s);
        oout.flush();
        oout.close();
        fOut.close();
        
        System.out.println("Data saved successfully!");
    } catch (IOException i) {
        System.out.println("Error saving data: " + i.getMessage());
    }
}

     private void LoadData(){
    try {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        FileInputStream fin = new FileInputStream(FILE_NAME);
        ObjectInputStream iout = new ObjectInputStream(fin);
        
        Student student = (Student) iout.readObject();
        
        idfill.setText(String.valueOf(student.getID()));
        namefill.setText(student.getName());
        balance = student.getMoney();  
        showmoney.setText(String.valueOf(balance));

        iout.close();
        fin.close();
        
        System.out.println("Data loaded successfully!");
    } catch (IOException | ClassNotFoundException i) {
        System.out.println("Error loading data: " + i.getMessage());
    }
}


    @Override
    public void windowOpened(WindowEvent e) {
        LoadData();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
        SaveData();
        jf.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }
    @Override
    public void windowDeactivated(WindowEvent e) {}
}