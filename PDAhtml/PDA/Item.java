abstract class Item {

    private String name;
    private double price;
    private String ingredients;

    public Item(String name,double price,String ingredients)
    {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
    }

    public String getName()
    {
        return name;
    }

    public double getPrice()
    {
        return price;
    }

    public String getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(String str)
    {
        this.ingredients = str;
    }
}
