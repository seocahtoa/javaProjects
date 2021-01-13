package project2;

/** 
* The purpose of this class is to store characteristics of a meteorite in a class. The Meteorite class implements the Comparable interface
* to override the compareTo method. It also overrides the toString and equals method from the object class. There exists
* only one constructor for this class and it takes a String and an int as a parameter for name and id, respectively. When the name equals the empty string
* or if the id is not a positive integer, the constructor throws an IllegalArgumentException.
* @author Minsu Seo
**/

public class Meteorite implements Comparable<Meteorite>{
    String name;
    int id;
    int mass;
    int year = 5000;
    double latitude;
    double longitude;

    //Constructor that throws an error when the name equals an empty string or if the id is not a positive integer
    public Meteorite(String name, int id) throws IllegalArgumentException{
        if (name.equals("") || id < 1)
            throw new IllegalArgumentException("invalid name or ID.");
        this.name = name;
        this.id = id;
    }
    //Returns the name of the Meteorite class
    public String getName(){
        return this.name;
    }
    //Returns the id of the Meteorite class
    public int getId(){
        return this.id;
    }
    //Returns the latitude of the Meteorite class
    public double getLatitude(){
        return this.latitude;
    }
    //Returns the longtitude of the Meteorite class
    public double getLongitude(){
        return this.longitude;
    }

    //Configures the mass field in the Meteorite class and throws IllegalArgumentException when the mass is less than 1
    public void setMass(int mass) throws IllegalArgumentException{
        if (mass < 0)
            throw new IllegalArgumentException("invalid mass");
        if(mass > 1)
            this.mass = mass;
        else{
            this.mass = 0;
        }
    }
    //Returns the mass of the called Meteorite class
    public int getMass(){
        return this.mass;
    }

    //Configures the year field in the Meteorite class and throws IllegalArgumentException when the new year is less than the original year or if the year is less than 1
    public void setYear(int year) throws IllegalArgumentException{
        if (year >= this.year || year < 1)
            throw new IllegalArgumentException("Invalid year");
        this.year = year;
    }
    //Returns the year of the called Meteorite class
    public int getYear(){
        return this.year;
    }
    //From the Location class, it extracts the latitude and longitude of the Meteorite and modifies the latitude and longitude in the Meteorite class
    public void setLocation(Location loc){
        this.latitude = loc.getLatitude();
        this.longitude = loc.getLongitude();
    }
    //Returns the Location class containing latitude and longtitude of a Meteorite
    public Location getLocation(){
        return new Location(this.latitude,this.longitude);
    }
    //Compares two Meteorite classes
    @Override
    public int compareTo(Meteorite o){
        //Checks if the name of two Meteorite classes are equal to each other
        if (this.name.equals(o.name)){
            //Checks if two Meteorite classes have the same ID
            if (this.id == o.id)
                return 0;
            //Checks which two Meteorite ID's are greater
            else if (this.id < o.id)
                return 1;
            else if (this.id > o.id)
                return -1;
        }
        //If the name are not equal to each other, return the one that is "greater" based on alphabetical order of the name
        return this.name.compareTo(o.name);
    }
    //Checks whether two Meteorite classes are equal to each other
    @Override
    public boolean equals(Object o){
        //Checks if two classes are referenced to a same object
        if (this == o){
            return true;
        }
        //Chekcs if o is an instance of Meteorite class
        if (!(o instanceof Meteorite))
            return false;
        //Type casts o into Meteorite class
        Meteorite m = (Meteorite) o;
        //Checks if two Metorite class have the same name or id
        if (!(this.name.equals(m.name) || this.id == m.id))
            return false;
        return true;
    }
    //toString method that is formatted as such
    @Override
    public String toString(){
        String name = String.format("%-20s",this.getName());
        String ID = String.format("%4d",this.getId());
        String year = String.format("%4d",this.getYear());
        String mass = String.format("%6d",this.getMass());
        String latitude = String.format("%10.5f",this.getLatitude());
        String longitude = String.format("%10.5f",this.getLongitude());
        if(this.getId() == 0)
            ID = "                    ";
        if(this.getYear() == 5000){
            year = "    ";
        }
        if(this.getMass() == 0){
            mass = "      ";
        }
        return name + ID + " " + year + mass + latitude + longitude;
    }
}
