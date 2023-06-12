import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

public class GameBoard 
{
    private String reset = "\u001B[0m";
    private String red = "\u001B[31m";
    private String green = "\u001B[32m";
    private String yellow = "\u001B[33m";
    private int k; // user input
    private int n; // number of cubes
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Cube> cubes = new ArrayList<Cube>();
    private ArrayList<ArrayList<Cube>>  levels = new ArrayList<ArrayList<Cube>>();

    
    public GameBoard()
    {
        System.out.println("\n====================================");
        System.out.println("\nEnter number of cubers per row(K) : ");
        k = getValidInput();
        n = 3 * k;
        System.out.println("n : " + n);
        System.out.println("\n====================================");

        int id;
        int x;
        int y;

        ArrayList<Cube> level1 = new ArrayList<Cube>();
        ArrayList<Cube> level2 = new ArrayList<Cube>();
        ArrayList<Cube> level3 = new ArrayList<Cube>();

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        for(int l1 = 0; l1 < (4 * k); l1++)
        {
            levels.get(0).add(null);
        }

        for(int l23 = 0; l23 < k; l23++)
        {
            levels.get(1).add(null);
            levels.get(2).add(null);
        }

        System.out.println("Level 3 : " + levels.get(2));
        System.out.println("Level 2 : " + levels.get(1));
        System.out.println("Level 1 : " + levels.get(0));

        HashSet<Integer> numbers = new HashSet<Integer>();

        for(int i = 1; i <= n; i++)
        {
            System.out.println("\nEnter cube 's ID : ");
            id = getValidInput();
            while(numbers.contains(id) || id > n || id == 0)
            {
                if(id > n || id == 0) System.out.println("ID should be less than " + n +" and greater than zero. Try a different id");
                else System.out.println("Cube with this ID already exists. Try a different one");
                id = getValidInput();
            }
            numbers.add(id);

            Cube cube = new Cube(id); // id of cube is n
            System.out.println("\nEnter cube[" + id +"] 'x' position : ");
            x = getValidInput();
            System.out.println("\nEnter cube[" + id +"] 'y' position : ");
            y = getValidInput();

            while(!isValid(x, y))
            {
                System.out.println("!!!!!!!!!!! BE CAREFUL !!!!!!!!!!!!!!!!!!!!!!!");
                System.out.println("\nEnter a valid cube[" + id +"] 'x' position : ");
                x = getValidInput();
                System.out.println("\nEnter a valid cube[" + id +"] 'y' position : ");
                y = getValidInput();
                System.out.println("==============================================");
            }

            if(y == 1)
            {
                levels.get(0).set(x - 1, cube);
            }
            else if(y == 2)
            {
                levels.get(1).set(x - 1, cube);
            }
            else
            {
                levels.get(2).set(x - 1, cube);
            }

            cube.setXY(x, y);              
            cubes.add(cube);
        }

        printState();
       
    }

    public int getValidInput()
    {
        boolean validInput = false;
        int num = 0;

        while(!validInput)
        {
            System.out.println("\nEnter a number:");
            if(scanner.hasNextInt())
            {
                num = scanner.nextInt();
                validInput = true;
            }
            else
            {
                System.out.println("\nInvalid input, please enter an integer.");
                scanner.next();
            }
        }

        return num;
    }

    public int getK()
    {
        return k;
    }

    public boolean isValid(int x, int y)
    {
        if(y > 3)
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\nY max value is 3. Try a different position.");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return false;
        }
        if((x > k && y > 1) || (x > 4 * k && y == 1))
        {
            return false;
        }
        if(y == 2)
        {
            if(levels.get(0).get(x - 1) == null)
            {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\nThere is no cube in level 1. Place a cube there first.");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                return false;
            }
        }
        if(y == 3)
        {
            if(levels.get(1).get(x - 1) == null)
            {
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                System.out.println("\nThere is no cube in level 2. Place a cube there first.");
                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                return false;
            }
        }
        if(y == 1 && levels.get(0).get(x - 1) != null)
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\nThere is a cube there already. Try a different position.");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return false;
        }
        if(y == 2 && levels.get(1).get(x - 1) != null)
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\nThere is a cube there already. Try a different position.");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return false;
        }
        if(y == 3 && levels.get(2).get(x - 1) != null)
        {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("\nThere is a cube there already. Try a different position.");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            return false;
        }
        return true;
    }

    public ArrayList<ArrayList<Cube>> getState()
    {
        return levels;
    }

    public void printState()
    {
        System.out.print("Level 3 : " );
        for (Cube cube : levels.get(2)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
            System.out.print(" X"); 
        }
        System.out.println();
        System.out.print("Level 2 : " );
        for (Cube cube : levels.get(1)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
                System.out.print(" X"); 
        }
        System.out.println();
        System.out.print("Level 1 : " );
        for (Cube cube : levels.get(0)) {
            if(cube != null)
            {
                if(this.isInPlace(cube))
                {
                    System.out.print(green + cube.getID()+ " " + reset); 
                }
                else
                {
                    System.out.print(red + cube.getID()+ " " + reset); 
                }
            }
            else
                System.out.print(" X");
        }
    }

    public boolean isInPlace(Cube cube)
    {
        if(cube == null)
        {
            return false;
        }
        if(cube.getX() <= k && cube.getID() - (cube.getY() - 1) * k - cube.getX() == 0)
        {
            return true;
        }
        return false;
    }
    
}
