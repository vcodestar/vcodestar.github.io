import java.util.*;
public class Menu {
    static ArrayList<Item> menu=new ArrayList<Item>();

    public void showMenu()
    {   System.out.println("Menu has:");
        System.out.println("_______________");
        for(int i=0;i<menu.size();i++)
        {
            System.out.println(menu.get(i));
        }
    }

    public static void generateMenu()
    {
        ArrayList<Item> menuList = new ArrayList<Item>();
        menu=menuList;
    }
}
