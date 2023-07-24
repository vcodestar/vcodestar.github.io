
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class OrderBtn extends JFrame implements ActionListener {
    
    int table=0;
   // OrderTest OT=new OrderTest();
    Order order=new Order(table);
    Scanner scanner=new Scanner(System.in);

    JButton button;
    JButton button2;
    JLabel label;
    JLabel label2;

    OrderBtn() 
    {
        label = new JLabel();
        label.setBounds(150,250,150,150);
        label.setVisible(false);
        label2 = new JLabel();
        label2.setBounds(150,250,150,150);
        label2.setVisible(false);

        button = new JButton();
        button2 = new JButton();

        button.setBounds(100,100,250,100);
        button.addActionListener(this);
        button.setText("New Order");

        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.BOTTOM);
		button.setFont(new Font("Comic Sans",Font.BOLD,25));
		button.setIconTextGap(-15);
		button.setForeground(Color.cyan);
		button.setBackground(Color.lightGray);
		button.setBorder(BorderFactory.createEtchedBorder());

        button2.setBounds(100,300,250,100);
        button2.addActionListener(this);
        button2.setText("Exit");

        button2.setFocusable(false);
        button2.setHorizontalTextPosition(JButton.CENTER);
        button2.setVerticalTextPosition(JButton.BOTTOM);
		button2.setFont(new Font("Comic Sans",Font.BOLD,25));
		button2.setIconTextGap(-15);
		button2.setForeground(Color.cyan);
		button2.setBackground(Color.lightGray);
		button2.setBorder(BorderFactory.createEtchedBorder());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(500,500);
		this.setVisible(true);
		this.add(button);
        this.add(button2);
		this.add(label);
        this.add(label2);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button) {
			System.out.println("foo");
			button.setEnabled(false);
            //OrderTest.main(null);
			label.setVisible(true);
		}	
        if(e.getSource()==button2) {
			System.out.println("foo2");
			button2.setEnabled(false);
			label.setVisible(true);
            //this.dispose();
            this.setVisible(false);
            OrderBtn btn=new OrderBtn();

		}	
	}

    
    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on
            
        new OrderBtn();

    }
    
}
