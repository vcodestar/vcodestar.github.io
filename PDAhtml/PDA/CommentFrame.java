import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CommentFrame extends JFrame implements ActionListener {
    
    private Order order;
    private Pract pract;
    private ArrayList<Item> cart=new ArrayList<Item>();
    private ArrayList<JButton> buttonList=new ArrayList<JButton>();
    private JLabel label;
    
    public CommentFrame(int tableNum,Order order,Pract pract) 
    {
        this.order = order;
        this.pract= pract;
        label= new JLabel();
        label.setBounds(150,250,150,150);
        label.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        this.buttonGen();
        for(int i = 0; i < buttonList.size(); i++)
        {
            //System.out.println(buttonList.get(i).getText());
            this.add(buttonList.get(i));
        }
        this.add(label);
    }

    public void buttonGen()
    {
        order.copyOrderList(cart);
       // System.out.println(order.getOrderListSize());
       // System.out.println(cart.size());
        for(int i=0;i<order.getOrderListSize();i++) 
        {
            JButton button = new JButton(cart.get(i).getName());
            button.setBounds(375,100+100*i,250,100);
            button.addActionListener(this);
            //button.setText(categs[i]);
            button.setFocusable(false);
            button.setHorizontalTextPosition(JButton.CENTER);
            button.setVerticalTextPosition(JButton.BOTTOM);
            button.setFont(new Font("Comic Sans",Font.BOLD,25));
            button.setIconTextGap(-15);
            button.setForeground(Color.cyan);
            button.setBackground(Color.lightGray);
            button.setBorder(BorderFactory.createEtchedBorder());
            buttonList.add(button);
            //System.out.println(cart.size());
            //System.out.println(cart.get(i));
        }
        JButton goBackButton=new JButton("Back");
        goBackButton.setBounds(375,100+100*order.getOrderListSize(),250,100);
        goBackButton.addActionListener(this);
        //button.setText(categs[i]);
        goBackButton.setFocusable(false);
        goBackButton.setHorizontalTextPosition(JButton.CENTER);
        goBackButton.setVerticalTextPosition(JButton.BOTTOM);
        goBackButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        goBackButton.setIconTextGap(-15);
        goBackButton.setForeground(Color.cyan);
        goBackButton.setBackground(Color.lightGray);
        goBackButton.setBorder(BorderFactory.createEtchedBorder());
        buttonList.add(goBackButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        for(int i=0;i<buttonList.size(); i++) {

            if(e.getSource()==buttonList.get(i) && i==buttonList.size()-1)
            {
                ///System.out.println("foo123");
                pract.setVisible(true);
                this.dispose();
                //Pract p2=new Pract();
            }

            if(e.getSource()==buttonList.get(i) && i!=buttonList.size()-1)
            {
                //System.out.println("foo123");
                order.addComment(i);
                pract.setVisible(true);
                this.dispose();
                //Pract p2=new Pract();
            }
        }
    }

    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on
      Order ord=new Order(0);
      Pract p=new Pract();
      ord.generateMenu();
      ord.chooseCategoryHelper(0, 1);
      OrderListFrame OLF=new OrderListFrame(0,ord,p);
      OLF.buttonGen();
      //p.copyCategories(order);
      //p.printCategories();
      //p.buttonGenCategories(order);

    }

}
