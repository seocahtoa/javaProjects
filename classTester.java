package project2;

import java.util.Scanner;

public class classTester {
    public static void main(String args[]){
        // Location l1 = new Location(10.0,20.0);
        // Location l2 = new Location(20.0,30.0);
        // Location l3 = new Location(10.0,20.0);
        // System.out.println(l1.haversine(10.0, 20.0, 20.0, 30.0));
        // System.out.println(l1.getDistance(l2));

        // System.out.println(l1.equals(l3));
        // String str = "";
        // System.out.println(str.length());

        // System.out.print(String.format("%6s", ""));
        // System.out.print("1");

        // double latitude = 0.001;
        // System.out.println(String.format("|%10.2f|",latitude));

        // Meteorite m1 = new Meteorite("Dddddddd",4);
        // m1.setYear(1980);
        // Location l1 = new Location(-15,-10);
        // m1.setLocation(l1);
        // m1.setMass(85);
        // System.out.println(m1.toString());
        // Meteorite m2 = new Meteorite("aaaa",10);
        // m2.setMass(80);
        // Meteorite m3 = new Meteorite("aaaa",5);
        // m3.setMass(100);

        // MeteoriteLinkedList m = new MeteoriteLinkedList();
        // m.add(m1);
        // m.add(m2);
        // m.add(m3);
        // System.out.println(m.toString());


        // // System.out.println(m2.compareTo(m3));


        // MeteoriteList test = new MeteoriteList();
        // test.add(m1);
        // test.add(m2);
        // test.add(m3);

        // System.out.println(test);
        // test.getByMass(80, 5).printContent();
        // System.out.println(test.getByMass(80, 5).toString());

    

        // System.out.println(test.getByLocation(lo));
    
        // System.out.println(list.toString());
        
        // System.out.println(list.remove("Dddddddd", 4) + "\n");
        // System.out.println(list);
        MeteoriteList list = new MeteoriteList();
		Meteorite m1 = new Meteorite("aaaa",12345);
		Meteorite m2 = new Meteorite("aaab", 11111);
        Meteorite m3 = new Meteorite("aaaa",12345);
        Meteorite m4 = new Meteorite("Yamato 790240",25589);
        Meteorite m5 = new Meteorite("Yamato 790241",25590);
		Location l1 = new Location(10.0000,10.0000);
		Location l2 = new Location(0.0,0.0);
        Location l3 = new Location (80,80);
        Location l4 = new Location (-71.500000,35.666670);
        Location l5 = new Location(-71.500000,35.666670);
        m4.setLocation(l4);
        m5.setLocation(l5);
		m1.setLocation(l1);
		m2.setLocation(l2);
		m3.setLocation(l3);
		m1.setMass(15);
		m2.setMass(10);
        m3.setMass(30);
        m4.setMass((int)10.82);
        m5.setMass((int)5.03);
		m1.setYear(2000);
		m2.setYear(1976);
        m3.setYear(1976);
        m4.setYear(1979);
        m5.setYear(1979);
		list.add(m1);
		list.add(m2);
        list.add(m3);
        list.add(m4);
        list.add(m5);

        for(Meteorite m : list){
            System.out.println(m.toString());
        }
        System.out.println(list.getByYear(1976).toString());
        System.out.println(list.getByYear(1979).toString());



    }
}
