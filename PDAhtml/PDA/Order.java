import java.util.*;

public class Order {

    private ArrayList<Item> myOrder=new ArrayList<Item>();
    private double cost;
    private int tableNumber;
    private String[] categories={"Coffees","Beverages","Beers","Alchoholic Drinks and Cocktails","Food"};
    private static ArrayList<ArrayList<Item>> menu=new ArrayList<ArrayList<Item>>();
    private Scanner scanner=new Scanner(System.in);

    public Order(int tableNumber)
    {
        this.tableNumber = tableNumber;
    }
    
    public void generateMenu()
    {
        ArrayList<Item> beers = new ArrayList<Item>();
        ArrayList<Item> coffees= new ArrayList<Item>();
        ArrayList<Item> beverages=new ArrayList<Item>();
        ArrayList<Item> alchohol=new ArrayList<Item>();
        ArrayList<Item> food=new ArrayList<Item>();

        //////// BEERS////////////////////////////////////////////////////////////////
        Drink corona=new Drink("Corona",6,null);
        Drink valia=new Drink("Valia Calda",6,null);
        Drink bud=new Drink("Bud",5,null);
        Drink becks=new Drink("Becks",3.5,null);
        
        beers.add(corona);
        beers.add(valia);
        beers.add(bud);
        beers.add(becks);
        ///////////////////////////////////////////////////////////////////////////////

        ///// COFFEE//////////////////////////////////////////////////////////////////
        Drink freddoS=new Drink("Freddo Sketos", 3.30, null);
        Drink freddoM=new Drink("Freddo Metrios", 3.30, null);
        Drink freddoG=new Drink("Freddo Glykos", 3.30, null);
        Drink cappucino=new Drink("Cappucino",3,null);

        coffees.add(freddoS);
        coffees.add(freddoM);
        coffees.add(freddoG);
        coffees.add(cappucino);
        //////////////////////////////////////////////////////////////////////////////////

        ///// BEVERAGES//////////////////////////////////////////////////////////////////

        Drink teaPeach=new Drink("Peach Tea",3.30,"Peach");
        Drink teaLemon=new Drink("Lemon Tea",3.30,"Lemon");
        Drink soda=new Drink("Soda",3.30,"Foo1");
        Drink carbWater=new Drink("Carbonated Water",3.30,"Foo1");
        Drink cherry=new Drink("Cherry Juice",3.30,"Cherry");
        Drink peach=new Drink("Peach Juice",3.30,"Peach");
        Drink energy=new Drink("Energy Juice",3.30,"Energy");
        Drink teaGreen=new Drink("Green Tea",3.30,"Foo1");

        beverages.add(teaPeach);
        beverages.add(teaLemon);
        beverages.add(teaGreen);
        beverages.add(cherry);
        beverages.add(peach);
        beverages.add(energy);
        beverages.add(soda);
        beverages.add(carbWater);

        ///////////////////////////////////////////////////////////////////////////////////

        ///// ALCHOHOL/////////////////////////////////////////////////////////////////////

        Drink mojito=new Drink("Mojito",7,"Foo1");
        Drink cinco=new Drink("Cinco Latino",8,"Foo2");
        Drink mamacita=new Drink("Mamacita",8,"Foo3");
        Drink daquiri=new Drink("Daquiri",8,"Foo4");
        Drink gin=new Drink("Gin",7.50,"Foo12");
        Drink vodka=new Drink("Vodka",7.50,"Foo212");
        Drink whiskey=new Drink("Whiskey", 8.0, "Foo121313");

        alchohol.add(mojito);
        alchohol.add(cinco);
        alchohol.add(mamacita);
        alchohol.add(daquiri);
        alchohol.add(gin);
        alchohol.add(vodka);
        alchohol.add(whiskey);
        //////FOOD/////////////////////////////////////////////////////////////////////////
        Food clubClassic=new Food("Classic Club",7,null);
        Food clubChicken=new Food("Chicken Club",8,null);
        Food baguetteCh=new Food("Chicken Baguette",4.20,null);
        Food baguettePr=new Food("Proscutto Baguette",4,null);
        Food superBowl=new Food("Super Bowl",5,null);


        food.add(clubClassic);
        food.add(clubChicken);
        food.add(baguetteCh);
        food.add(baguettePr);
        food.add(superBowl);
        ///////////////////////////////////////////////////////////////////////////////////
        menu.add(coffees);
        menu.add(beverages);
        menu.add(beers);
        menu.add(alchohol);
        menu.add(food);
    }

    public void printMenu()
    {
        for(int i=0; i<menu.size();i++) 
        {
            System.out.println(categories[i]);
            System.out.println("--------------------------");
            for (int j = 0; j <menu.get(i).size();j++)
            {

                System.out.println(menu.get(i).get(j).getName()+", "+menu.get(i).get(j).getPrice());
            }
        }
    }

    public void printCategories()
    {
        for (int i = 0; i <categories.length; i++)
        {
            System.out.println((i+1)+"." +categories[i]);
        }
    }

    public void chooseCategory(int number)
    {
        System.out.println(categories[number-1]);

        if((number-1)==0)
        {
            printItemsInCategory(1);
        }
        if((number-1)==1)
        {
            printItemsInCategory(2);
        }
        if((number-1)==2)
        {
            printItemsInCategory(3);
        }
        if((number-1)==3)
        {
            printItemsInCategory(4);
        }
        if((number-1)==4)
        {
            printItemsInCategory(5);
        }
    }

    public void chooseCategoryHelper(int number,int enumItem)
    {
        //System.out.println("Select Item:");

        //int enumItem=scanner.nextInt();

        this.addToOrder(menu.get(number).get(enumItem));

        System.out.println("");
        System.out.println(menu.get(number).get(enumItem).getName()+" added to order list.");
            
    }

    public int findIndex(String str)
    {
        int index=0;

        for(int i = 0; i <categories.length;i++)
        {
            if(categories[i].equals(str))
            {
                return index+1;
            }
            if(!categories[i].equals(str))
            {
                index++;
            }
        }
        return -1;

    }

    public void printItemsInCategory(int number)
    {
        if((number-1)==0)
        {
            printItemsInCategoryHelper(0);
        }
        if((number-1)==1)
        {
            printItemsInCategoryHelper(1);
        }
        if((number-1)==2)
        {
            printItemsInCategoryHelper(2);
        }
        if((number-1)==3)
        {
            printItemsInCategoryHelper(3);
        }
        if((number-1)==4)
        {
            printItemsInCategoryHelper(4);
        }
    }

    public void printItemsInCategoryHelper(int number)
    {
        for(int j=0; j<menu.get(number).size(); j++)
        System.out.println((j+1)+"."+menu.get(number).get(j).getName());
    }

    public void addToOrder(Item item)
    {
        myOrder.add(item);
    }

    public void deleteFromOrder(int index)
    {
        if(myOrder.size()==0)
        {
            System.out.println("No items in order.");
            return;
        }
        /*for(int i=0; i<myOrder.size(); i++)
        {
            System.out.println((i+1)+"."+myOrder.get(i).getName());
        }

        System.out.println("------------------------");
        System.out.println("Press B to go back");
        System.out.println("---------------------------");

        int itemNum=scanner.nextInt();
        if (itemNum==0)
        {
            this.goBack();
        }
        else
        {
            System.out.println(myOrder.get(itemNum).getName()+" deleted from order list"); 
            myOrder.remove((itemNum-1));   
        }     */
        myOrder.remove(index);
    }

    public void showItemsInOrder()
    {
        for(int i=0; i<myOrder.size(); i++)
        {
            System.out.println((i+1)+"."+myOrder.get(i).getName());
        }
    }

    public double computeOrder()
    {
        for (int i = 0; i < myOrder.size(); i++)
        {
            cost+=myOrder.get(i).getPrice();
        }

        return cost;
    }

    public void sendOrder()
    
    {
        System.out.println("Table number: "+tableNumber);
        System.out.println("-------------");
        for (int i = 0; i < myOrder.size(); i++)
        {
            System.out.println(myOrder.get(i).getName()+", "+myOrder.get(i).getPrice());
            if(myOrder.get(i).getIngredients()!=null)
            {
                System.out.println(myOrder.get(i).getIngredients());
            }
        }
        this.computeOrder();
        System.out.println("Total cost: "+cost+"$");
        System.exit(0);
    }
    
    public void goBack() 
    {
        printCategories();
    }

    public int getCategoriesSize() 
    {
        return categories.length;
    }

    public void copyArray(String[] array) 
    {
        for(int i=0; i<categories.length; i++)
        {
            array[i] = categories[i];
        }
    }

    public void copyArraysItems(int index,String[] arrayToPassItems)
    {
       
        for(int i=0; i<menu.get(index).size(); i++)
        {
            arrayToPassItems[i] = menu.get(index).get(i).getName();
        }
    }

    public int getArraysItemsSize(int index)
    {
        return menu.get(index).size();
    }

    public int getOrderListSize()
    {
        return myOrder.size();
    }

    public void copyOrderList(ArrayList<Item> itemList)
    {
        //Collections.copy(itemList, myOrder);
        itemList.addAll(myOrder);
        //itemList=new ArrayList<Item>(List.copyOf(myOrder));
    }

    public void addComment(int i)
    {
        System.out.println();
        System.out.println("Add comment:");
        String str= scanner.nextLine();
        myOrder.get(i).setIngredients("#"+str);
    }
    public static void main(String[] args)
    {
        Order order = new Order(679);
       // Food food=new Food("Club", 5, "Chicken");
        //Food food2=new Food("Toast",3,"Cheese");
        //order.addToOrder(food);
        //order.addToOrder(food2);
        order.generateMenu();
        //order.printMenu();
        order.printCategories();
        //order.printItemsInCategory(1);
        //order.chooseCategory(1);
        //order.chooseCategoryHelper(1,1);
        //System.out.println(order.findIndex("Coffees"));
        //order.addToOrder(drinks.get(0));
        //order.addToOrder(drinks.get(2));
        //order.computeOrder();
        //order.sendOrder();
        /*for(int i=0; i<menu.get(2).size(); i++)
        {
            System.out.println((i+1)+"."+menu.get(2).get(i).getName());
        }*/
    }


}
