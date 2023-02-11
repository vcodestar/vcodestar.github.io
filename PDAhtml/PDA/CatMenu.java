import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CatMenu extends JFrame implements ActionListener {

    private Scanner scanner= new Scanner(System.in);
    private static Order order= new Order(0);
    private ArrayList<JButton> buttonList= new ArrayList<JButton>();
    private String[] productName;
    private int indexC;
    private Pract pract;
    JLabel label;

    public CatMenu(int indexC,Pract pract) 
    {
        this.pract= pract;
        this.indexC = indexC;
        label= new JLabel();
        label.setBounds(150,250,150,150);
        label.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        this.copyItems(order,indexC);
        this.buttonGenCategories(order);
        for(int i = 0; i < buttonList.size(); i++)
        {
            //System.out.println(buttonList.get(i).getText());
            this.add(buttonList.get(i));
        }
        this.add(label);
    }

    public void copyItems(Order inOrder,int index)
    {
        String[] temp=new String[inOrder.getArraysItemsSize(index)];
        inOrder.copyArraysItems(index,temp);
        productName=temp;
    }

    public void buttonGenCategories(Order inOrder)
    {
        for (int i = 0; i < inOrder.getArraysItemsSize(indexC); i++)
        //for (int i = 0; i < inOrder.getCategoriesSize(); i++)
        {
            //System.out.println(inOrder.getCategoriesSize());
            JButton button = new JButton(productName[i]);
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

        JButton goBackButton=new JButton("Back");
        goBackButton.setBounds(375,100+100*inOrder.getArraysItemsSize(indexC),250,100);
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
        for(int i=0;i<buttonList.size(); i++) {

            if(e.getSource()==buttonList.get(i) && i==buttonList.size()-1)
            {
                //System.out.println("foo123");
                pract.setVisible(true);
                this.dispose();
                //Pract p2=new Pract();
            }

            if(e.getSource()==buttonList.get(i) && i!=buttonList.size()-1)
            {
                pract.addToCart(indexC,i);
                //order.chooseCategoryHelper(indexC,i);
                //Pract p2=new Pract();
            }
        }
        
    }

    public static void main(String[] args) {

        // JButton = a button that performs an action when clicked on
      order.generateMenu();
      Pract p=new Pract();
      CatMenu cm=new CatMenu(0,p);
      //p.copyCategories(order);
      //p.printCategories();
      //p.buttonGenCategories(order);

    }


}
