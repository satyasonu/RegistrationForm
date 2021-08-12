import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class LoginForm implements ActionListener {
	JFrame frame2;
	
	JLabel passwordLabel=new JLabel("PASSWORD");
	JLabel emailLabel=new JLabel("EMAIL");
	JTextField emailTextField=new JTextField();
	JPasswordField passwordField=new JPasswordField();
	JButton loginButton = new JButton("LOGIN");
	JButton Registartion = new JButton("New User?");
	
	LoginForm()
    {
		createLoginWindow();
        setLocationAndSize();
        addComponentsToFrame();
        actionEvent();
    }
	
	public void createLoginWindow() {
		frame2=new JFrame();
        frame2.setTitle("Applicant Login Form");
        frame2.setBounds(40,40,400,350);
        frame2.getContentPane().setBackground(Color.green);
        frame2.getContentPane().setLayout(null);
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setResizable(false);
	}
	
	public void setLocationAndSize()
    {
		Registartion.setBounds(200,250,100,35);
		emailLabel.setBounds(20,20,40,70);
		emailTextField.setBounds(180,43,165,23);
		passwordLabel.setBounds(20,120,100,70);
		passwordField.setBounds(180,143,165,23);
		loginButton.setBounds(100, 200, 110, 25);
    }
	
	public void addComponentsToFrame()
    {
		frame2.add(emailLabel);
		frame2.add(passwordLabel);
		frame2.add(passwordField);
		frame2.add(emailTextField);
		frame2.add(loginButton);
		frame2.add(Registartion);
    }
	public void actionEvent()
    {
		loginButton.addActionListener(this);
		Registartion.addActionListener(this);
		
    }
	 @Override
	    public void actionPerformed(ActionEvent e) {
		 if(e.getSource()==loginButton) {
			 String UserName = emailTextField.getText();
			 String Password = passwordField.getText();
			 try {
				 //Creating Connection Object
	                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/satya","root","9853614220");
	                //Prepared Statement
	                PreparedStatement Pstatement=connection.prepareStatement("Select EMAIL,PASSWORD from student where email=? and password=? ");
	                //Specifying the values of it's parameter
	                Pstatement.setString(1, UserName);
	                Pstatement.setString(2, Password);
	                ResultSet rs = Pstatement.executeQuery();
	                if(rs.next()) {
	                	//frame2.dispose();
	                	JOptionPane.showMessageDialog(null, "You have logged in successfully");
	                	new FetchData();
	                	frame2.dispose();
	                	
	                }
	                else {
	                	JOptionPane.showMessageDialog(null, "Wrong Email and Password.");
	                	
	                }
	                			 }
			 catch (SQLException e1) {
                 e1.printStackTrace();
             }
			 
		 }
		 if(e.getSource()==Registartion) {
			 new RegistartionForm();
			 frame2.dispose();
		 }
	 }
	 
}
