
import java.util.Scanner;
public class coffeeAddict {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args){
        int choice;
        coffees coffee = new coffees();
        System.out.println("Welcome to your personal Nespresso™ tracker!");
        String[] coffeeTypes = {
                "Bianco Forte",
                "IntensoXL",
                "Stormio",
                "Double Espresso Scuro",
                "Altissio",
                "Quit program"
        };
        System.out.println("-------------------------------------------");
        for (int i = 0; i < coffeeTypes.length; i++) {
            System.out.println((i < coffeeTypes.length-1 ? i+1 : 0) + ". " + coffeeTypes[i]);
        }
            System.out.print("Select your coffee: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    coffee = new biancoForte();
                    break;
                case 2:
                    coffee = new intensoXL();
                    break;
                case 3:
                    coffee = new stormio();
                    break;
                case 4:
                    coffee = new doubleEspressoScuro();
                    break;
                case 5:
                    coffee = new altissio();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Specified coffee type not availble");
            }

        String[] choices = {
                "Find your coffee's roast level",
                "Find your coffee's volume",
                "Find your coffee's type",
                "Quit tracker"
        };
        do{
            System.out.println("-------------------------------------------");
            for (int i = 0; i < choices.length; i++) {
                System.out.println((i < choices.length-1 ? i+1 : 0) + ". " + choices[i]);
            }
            System.out.print("Make your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    main_roastLevel(coffee);
                    break;
                case 2:
                    main_volume(coffee);
                    break;
                case 3:
                    main_type(coffee);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.print("Invalid choice");
            }
        }while(choice != 0);

    }

    public static void main_roastLevel(coffees coffee){
        System.out.println("\nThe coffee has a roastness of " + coffee.getRoastness() + "\n");
    }

    public static void main_volume(coffees coffee){
        System.out.println("\nEnter 1 for volume in oz\nEnter 2 for volume in ml");
        System.out.print("Make your choice: ");
        int choice = input.nextInt();
        if (choice == 1)
            System.out.println("\nThe coffee is " + coffee.getOz() + "oz\n");
        else if (choice == 2)
            System.out.println("\nThe coffee is " + coffees.ozToMl(coffee.getOz()) + "ml\n");
        else
            System.out.println("\nInvalid choice\n");
    }

    public static void main_type(coffees coffee){
        System.out.println("\nYour coffee is a " + coffee.getType() +"\n");
    }
}

class coffees{

    private double oz;
    private String type;
    private String roastness;

    public coffees(){
    }

    public coffees (double oz, String type, String roastness){
        this.oz = oz;
        this.type = type;
        this.roastness = roastness;
    }
    public static double ozToMl(double oz){
        return oz * 29.574;
    }

    public double getOz(){
        return oz;
    }

    public String getType(){
        return type;
    }

    public String getRoastness(){
        return roastness;
    }
}

class biancoForte extends coffees{

    public biancoForte(){
        super(7.77,"latte","unknown");
    }


}

class intensoXL extends coffees{

    public intensoXL(){
        super(14.0,"big coffee","7");
    }
}

class stormio extends coffees{

    public stormio(){
        super(7.77,"regular coffee","8");
    }
}

class doubleEspressoScuro extends coffees{

    public doubleEspressoScuro(){
        super(2.7,"double espresso","8");
    }

}
class altissio extends coffees{

    public altissio(){
        super(1.35,"espresso","9");
    }
}
