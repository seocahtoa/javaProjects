package project2;

import java.util.ArrayList;
/**
 * This class extends the ArrayList to store Meteorite object. In this class, there exists three different
 * method in searching the MeteoriteList. getByMass method searches the MeteoriteList for any Meteorite
 * objects that have a mass within the delta grams of the specificed mass. getByLocation method searches 
 * the MeteoriteList for the Meteorite object that is the closest in terms of latitude and longitude.
 * getByYear searches the MeteoriteList for any Meteorite objects that landed on Earth on the year specified.
 * 
 * @param year
 * @return
 * 
 * @author Minsu Seo
 */

public class MeteoriteList extends ArrayList<Meteorite>{

    MeteoriteList(){}
    /**
     * Searches the list for any Meteorite objects that have the mass included in the interval given my mass 
     * and delta parameters.
     * @param mass
     * @param delta
     * @return MeteoriteLinkedList
     * @throws IllegalArgumentException
     */
    public MeteoriteLinkedList getByMass(int mass, int delta) throws IllegalArgumentException{
        //Throws IllegalArgumentException when mass and delta is not greater than 0.
        if (mass < 0 || delta < 0)
            throw new IllegalArgumentException("The mass and delta has to be greater than 0.");
        MeteoriteLinkedList list = new MeteoriteLinkedList();
        MeteoriteList gram = new MeteoriteList();
        for (Meteorite m : this){
            //If the Meteorite object has a mass that is between the interval add it to MeteoriteList that was created
            if ((m.getMass() >= mass - delta) && (m.getMass() <= mass +delta)){
                if (m.getMass() != 0){
                    gram.add(m);
                }
            }
        }
        //Returns null when there are no Meteorite that are in the interval
        if (gram.size() == 0){
            return null;
        }
        //Given MeteoriteList has size greater than 1, search for the smallest value given by the compareTo method of Meteorite
        //class of the MeteoriteList. After you found the smallest value, add it to the MeteorieLinkedList and remove the said
        //Object from the MeteoriteList.
        while(gram.size() > 1 ){
            Meteorite smallest = gram.get(0);
            int index = 0;
            for (int i = 1;i<gram.size();i++){
                if (smallest.compareTo(gram.get(i)) > 0){
                    smallest = gram.get(i);
                    index = i;
                }
            }
            list.add(smallest);
            gram.remove(index);
        }
        //If the size of the MeteoriteList is 1, just add the alone element into the MeteoriteLinkedList and delete the MeteoriteList.
        if (gram.size() == 1){
            list.add(gram.get(0));
            gram.clear();
        }
        return list;
    }
    /**
     * Searches through the MeteoriteList and looks for the Meteorite Object that have the shortest distance between each other.
     * @param loc
     * @return Meteorite
     * @throws IllegalArgumentException
     */
    public Meteorite getByLocation(Location loc) throws IllegalArgumentException{
        //If the loc parameter is null, throw a new IllegalArgumentException
        if (loc == null)
            throw new IllegalArgumentException("The location is null.");
        //If the MeteoriteList is empty, return null
        if (this.isEmpty())
            return null;
        Meteorite shortest = this.get(0);
        //Iterates through the MeteoriteList and looks for the closest Meteorite from the given location
        for (int i = 1; i < this.size();i++){
            if (this.get(i).getLocation().getDistance(loc) < shortest.getLocation().getDistance(loc))
                shortest = this.get(i);
                return shortest;
        }
        return null;
    }
    /**
     * Looks for Meteorite Object in the MeteoriteList that landed on Earth the same year as the one given.
     * @param year
     * @return MeteoriteLinkedList
     * @throws IllegalArgumentException
     */
    public MeteoriteLinkedList getByYear(int year) throws IllegalArgumentException{
        if (year < 0)
            throw new IllegalArgumentException("invalid year");

        MeteoriteLinkedList list = new MeteoriteLinkedList();
        MeteoriteList y = new MeteoriteList();
        //Goes through the MeteoriteList and loks for the Meteorite Object that have the same year as the year given and adds it to the new MeteoriteList y
        for (Meteorite m : this){
            if (m.getYear() == year)
                y.add(m);
        }
        //If no objects match the criteria, return null
        if (y.size() == 0){
            return null;
        }
        //For list full of Meteorites that have the same years
        while(y.size() > 1 ){
            Meteorite smallest = y.get(0);
            int index = 0;
            for (int i = 1;i<y.size();i++){
                //Compare with each other and find the smallest one
                if (smallest.compareTo(y.get(i)) > 0){
                    smallest = y.get(i);
                    index = i;
                }
            }
            list.add(smallest);
            y.remove(index);
            //delete the smallest from y and add the smallest to the MeteoriteLinkedList
        }
        //If y size 1, add the Object to MeteoriteLinkedList and clear y
        if (y.size() == 1){
            list.add(y.get(0));
            y.clear();
        }
        return list;

    }




    
}
