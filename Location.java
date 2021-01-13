package project2;

/*
The purpose of this class is to store latitude and longitude in a class called Location.
Inside this class, there exists a Location constructor that accepts two double values of latitude and longitude 
in that order that throws IllegalArgumentException when an invalid latitude and longitude is passed. There also exists 
a getDistance method which throws an IllegalArgumentException when the Location parameter is referenced as null and it
returns the distance between two Location classes with the help of haversine method. The Location class also contains
a getter methods for both latitude and longitude and it overrides the equals method from Objects class as well.

*/

public class Location {
    // Change it to private when done to see if it crashes
    // Stores meteorite's latitude and longitude
    double latitude;
    double longitude;

    // A Location class constructor that accepts two double values of meteorite's latitude and longiitude
    public Location(double latitude, double longitude) throws IllegalArgumentException{
        //It throws anIllegalArgymentException when the latitude is less than -90.0 or greater than 90.0 or if the longitude is less than -180.0 or greater than 180.0.
        if ((latitude < -90.0 && latitude > 90.0) || (longitude < -180.0 && longitude > 180.0)){
            throw new IllegalArgumentException("Invalid latitude and longitude. ");
        }
        //If the parameters are valid, then they are stored in the latitude and longitude data field of the class
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //method getDistance that returns the distance between two meteorites with their latitude and longitude
    public double getDistance(Location loc) throws IllegalArgumentException{
        //thros IllegalArgumentException when the Location object that has been passed as a parameter is referenced to null
        if (loc == null){
            throw new IllegalArgumentException("Location not valid.");
        }
        //uses the haversine method to calculate the distance
        return haversine(this.latitude,this.longitude,loc.getLatitude(),loc.getLongitude());
    }

    //getter method for latitude
    public double getLatitude(){
        return this.latitude;
    }

    //getter method for longitude
    public double getLongitude(){
        return this.longitude;
    }

    @Override
    public boolean equals(Object o){
        //returns true if two same objects are being compared
        if (o == this)
            return true;
        //returns false if Object o is not a Location object
        if (!(o instanceof Location))
            return false;
        //type casts the Object o into a Location object to access their data fields
        Location l = (Location) o;
        //the absolute difference between two Location object's latitude has to be less thatn 0.00001
        if (!(Math.abs(this.latitude-l.getLatitude()) < 0.00001))
            return false;
        //the absolute difference between two Location object's longitude has to be less than 0.0001
        if (!(Math.abs(this.longitude-l.getLongitude()) < 0.00001))
            return false;
        return true;
    }

    /*
    https://cs.nyu.edu/~joannakl/cs102_f20/projects/project2.html
    haversine method referenced from Appedix of above
    */

    static double haversine(double lat1, double lon1, double lat2, double lon2){
	    // distance between latitudes and longitudes
	    double dLat = Math.toRadians(lat2 - lat1);
	    double dLon = Math.toRadians(lon2 - lon1);

	    // convert to radians
	    lat1 = Math.toRadians(lat1);
	    lat2 = Math.toRadians(lat2);

	    // apply formulae
	    double a = Math.pow(Math.sin(dLat / 2), 2) +
			   Math.pow(Math.sin(dLon / 2), 2) *
			   Math.cos(lat1) *
			   Math.cos(lat2);
	    double rad = 6371;
	    double c = 2 * Math.asin(Math.sqrt(a));
	    return rad * c;
    }


}
