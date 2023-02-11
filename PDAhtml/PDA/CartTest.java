public class CartTest {
    static Cart cart = new Cart();
    static Menu menu=new Menu();

    public static void main(String[] args)
    {
        menu.generateMenu();
        cart.addToCart("Foo3");
        menu.showMenu();
        cart.showCart();

    }

}
