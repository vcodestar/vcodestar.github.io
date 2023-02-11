public class Food extends Item {

    public Food(String name, double price, String ingredients)
    {
        super(name, price, ingredients);
    }

    public static void main(String[] args) 
    {
        Food test=new Food("Club Chicken",5,"Foo1,Foo2");
        System.out.println("Your order:");
        System.out.println(test.getName()+", "+test.getPrice()+"$");
        System.out.println(test.getIngredients());

    }
}
