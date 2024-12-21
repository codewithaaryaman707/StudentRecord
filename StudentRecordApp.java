import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class StudentRecord extends JFrame{
    private Container c;
    private JLabel lblName, lblRoll;
    private JTextField txtName, txtRoll;
    private JRadioButton rbPass, rbFail;
    private JButton btnSubmit;

    public StudentRecord(){
        c = getContentPane();
        c.setLayout(new FlowLayout());

        lblName = new JLabel("Enter name ");
        txtName = new JTextField(10);
        lblRoll = new JLabel("Enter roll");
        txtRoll = new JTextField(10);
        rbPass  = new JRadioButton("PASS",true);
        rbFail = new JRadioButton("FAIL");
        btnSubmit = new JButton("SUBMIT");

        Font f = new Font("Times New Roman",Font.BOLD,30 );
        lblName.setFont(f);
        txtName.setFont(f);
        lblRoll.setFont(f);
        txtRoll.setFont(f);
        rbPass.setFont(f);
        rbFail.setFont(f);
        btnSubmit.setFont(f);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbPass);
        bg.add(rbFail);


        c.add(lblRoll);
        c.add(txtRoll);
        c.add(lblName);
        c.add(txtName);
        c.add(rbPass);
        c.add(rbFail);
        c.add(btnSubmit);

        ActionListener a =(ae)->{
            try{
                //load
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                //connect
                String url = "jdbc:mysql://localhost:3306/studentdata";
                Connection con = DriverManager.getConnection(url,"root","halloween");
                //process
                String name = txtName.getText();
                int rno = Integer.parseInt(txtRoll.getText());
                String status = "";
                if(rbPass.isSelected())  status = "PASS";
                else                     status = "FAIL";

                String sql = "insert into information values(?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,rno);
                ps.setString(2,name);
                ps.setString(3, status);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(c,"SUCCESS");
                txtName.setText("");
                txtRoll.setText("");
            }catch (SQLException h){
                JOptionPane.showMessageDialog(c,"ISSUE" +h);
            }

        };btnSubmit.addActionListener(a);
        setSize(300,400);
        setTitle("Student Record");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
}
public class StudentRecordApp {
    public static void main(String[] args){
        StudentRecord sr = new StudentRecord();
    }
}
