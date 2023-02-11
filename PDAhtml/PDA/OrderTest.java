import java.util.*;

public class OrderTest {

    private static boolean endOrder = false;
    private static Scanner scanner=new Scanner(System.in);
    private static Scanner scanner2 = new Scanner(System.in);
    private String currentCategory;

    public static void makeChoice(String input,Order inOrder)
    {
        if(input.equals("E"))
        {
            endOrder=true;
        }
        if(input.equals("B"))
        {
            inOrder.goBack();
        }
        if(input.equals("D"))
        {
            inOrder.deleteFromOrder();
        }
        if(input.equals("S"))
        {
            inOrder.showItemsInOrder();
        }
        if(input.equals("C"))
        {
            System.out.println("");
            System.out.println("Categories");

            inOrder.printCategories();
            
            System.out.println("");
            System.out.println("Choose category: ");

            int num=scanner2.nextInt();

            inOrder.chooseCategory(num);
            inOrder.chooseCategoryHelper(num,0);
        }
    }


    public static void main(String[] args) {
        
        System.out.println("Enter table number: ");

        int tableNumber=scanner2.nextInt();
        Order order = new Order(tableNumber);
        order.generateMenu();
        
        //order.printCategories();
        System.out.println("Enter answer:");
        
        while(endOrder!=true)
        {
            System.out.println("------------------------------------------");
            System.out.println("Press C to continue");
            System.out.println("Press B to go back");
            System.out.println("Press D to delete item from order list");
            System.out.println("Press S to show items in order list");
            System.out.println("Press E to send order and Exit");
            System.out.println("------------------------------------------");


            String answer1=scanner.nextLine();

            makeChoice(answer1,order);
            

        }
        order.sendOrder();
    }
}
