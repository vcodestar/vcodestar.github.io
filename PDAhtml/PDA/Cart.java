import java.util.*;

public class Cart {
    
    static ArrayList<String> cart=new ArrayList<String>();
    Scanner scanner=new Scanner(System.in);

    public void addToCart(String item) 
    {
        cart.add(item);
    }
    
    public  void showCart()
    {
        System.out.println("Cart has:");
        System.out.println("_______________");
        for(int i=0; i<cart.size(); i++)
        {
            System.out.println(cart.get(i));
        }
    }

}
