import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Pract extends JFrame implements ActionListener {
    
    private Scanner scanner = new Scanner(System.in);
    private static Order order;
    private ArrayList<JButton> buttonList = new ArrayList<JButton>();
    private String[] categs;
    private ArrayList<Item> orderList = new ArrayList<Item>();
    //private JButton button ;
    JLabel label;

    public Pract() 
    {
        int tabNum=this.inputTableNumber();
        order=new Order(tabNum);
        order.generateMenu();
        label=new JLabel();
        label.setBounds(150,250,150,150);
        label.setVisible(true);

        this.setTitle("VCSoftware LLC");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        this.copyCategories(order);
        this.buttonGenCategories(order);
        for(int i=0; i<buttonList.size();i++)
        {
            //System.out.println(buttonList.get(i).getText());
            this.add(buttonList.get(i));
        }
        this.add(label);
    }

    public int inputTableNumber()
    {
        int tableNumber = scanner.nextInt();
        return tableNumber;
    }

    public void copyCategories(Order inOrder)
    {   
        String[] temp=new String[inOrder.getCategoriesSize()];
        inOrder.copyArray(temp);
        categs=temp;
    }

    public void printCategories()
    {
        for (int i=0; i<categs.length;i++) {System.out.println(categs[i]);}
    }

    public void addToCart(int i,int j)
    {
        order.chooseCategoryHelper(i,j);
    }

    public void buttonGenCategories(Order inOrder)
    {
        for (int i = 0; i < inOrder.getCategoriesSize(); i++)
        {
            //System.out.println(inOrder.getCategoriesSize());
            JButton button = new JButton(categs[i]);
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
            //System.out.println(categs[i]);
        }

        JButton sendOrderButton=new JButton("Send Order");
        sendOrderButton.setBounds(375,100+100*(inOrder.getCategoriesSize()+1),250,100);
        sendOrderButton.addActionListener(this);
        //button.setText(categs[i]);
        sendOrderButton.setFocusable(false);
        sendOrderButton.setHorizontalTextPosition(JButton.CENTER);
        sendOrderButton.setVerticalTextPosition(JButton.BOTTOM);
        sendOrderButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        sendOrderButton.setIconTextGap(-15);
        sendOrderButton.setForeground(Color.cyan);
        sendOrderButton.setBackground(Color.lightGray);
        sendOrderButton.setBorder(BorderFactory.createEtchedBorder());
        buttonList.add(sendOrderButton);

        JButton deleteFromOrder=new JButton("Delete Item");
        deleteFromOrder.setBounds(375,100+100*inOrder.getCategoriesSize(),250,100);
        deleteFromOrder.addActionListener(this);
        //button.setText(categs[i]);
        deleteFromOrder.setFocusable(false);
        deleteFromOrder.setHorizontalTextPosition(JButton.CENTER);
        deleteFromOrder.setVerticalTextPosition(JButton.BOTTOM);
        deleteFromOrder.setFont(new Font("Comic Sans",Font.BOLD,25));
        deleteFromOrder.setIconTextGap(-15);
        deleteFromOrder.setForeground(Color.cyan);
        deleteFromOrder.setBackground(Color.lightGray);
        deleteFromOrder.setBorder(BorderFactory.createEtchedBorder());
        buttonList.add(deleteFromOrder);

        JButton commentButton=new JButton("Add Comment");
        commentButton.setBounds(375,100+100*(inOrder.getCategoriesSize()+2),250,100);
        commentButton.addActionListener(this);
        //button.setText(categs[i]);
        commentButton.setFocusable(false);
        commentButton.setHorizontalTextPosition(JButton.CENTER);
        commentButton.setVerticalTextPosition(JButton.BOTTOM);
        commentButton.setFont(new Font("Comic Sans",Font.BOLD,25));
        commentButton.setIconTextGap(-15);
        commentButton.setForeground(Color.cyan);
        commentButton.setBackground(Color.lightGray);
        commentButton.setBorder(BorderFactory.createEtchedBorder());
        buttonList.add(commentButton);


    }


    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<buttonList.size(); i++) {

            
            if(e.getSource()==buttonList.get(i) && i==buttonList.size()-3)
            {
                //System.out.println("foo123");
                order.sendOrder();
                //Pract p2=new Pract();
            }

            if(e.getSource()==buttonList.get(i) && i==buttonList.size()-2)
            {
                //System.out.println("foo1234");
                OrderListFrame olf=new OrderListFrame(0,order,this);
                //Pract p2=new Pract();
            }

            if(e.getSource()==buttonList.get(i) && i==buttonList.size()-1)
            {
                //System.out.println("foo1234");
                CommentFrame cmf=new CommentFrame(0,order,this);
                //Pract p2=new Pract();
            }

            if(e.getSource()==buttonList.get(i) && i!=buttonList.size()-2 && i!=buttonList.size()-3 && i!=buttonList.size()-1)
            {
                //System.out.println("foo");
                CatMenu cm=new CatMenu(i,this);
                this.setVisible(false);
                //this.dispose();
            }
        }
        
    }

    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on
      //order.generateMenu();
      System.out.println("Enter table number: " );
      Pract p =new Pract();
      //p.copyCategories(order);
      //p.printCategories();
      //p.buttonGenCategories(order);

    }


    
}