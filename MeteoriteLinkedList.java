package project2;

/**
 * Using the concept of LinkedList, it implements its own LinkedList called MeteoriteLinkedList that accepts Meteorite
 * Object as an element. In order to implement the concept of LinkedList, private class, Node has been defined in this class.
 * Inside the MeteoriteLinkedList, there exists two constructor, one that create an empty LinkedList and one that copys the content
 * of MeteoriteList into MeteoriteLinkedList. There also exists several methods, add, remove, toString.
 * 
 * @author Minsu Seo
 */

public class MeteoriteLinkedList{

    Node head;
    Node tail;

    int size;

    /**
     * Creates an empty MeteoriteLinkedList
     */
    public MeteoriteLinkedList(){
        head = null;
        tail = null;
        size = 0;

    }
    /**
     * Creates a MeteoriteLinkedList that has the same content as MeteoriteList.
     * @param list
     * @throws IllegalArgumentException
     */
    public MeteoriteLinkedList(MeteoriteList list) throws IllegalArgumentException{
        //If the list is null, IllegalArgumentExceptin is thrown
        if (list == null)
            throw new IllegalArgumentException("The list is null");
        //Iterates through the MeteoriteList and adds them to MeteoriteLinkedLIst one by one
        for (int i = 0; i < list.size();i++){
            if (i == 0){
                head = new Node(list.get(i));
            }
            else{
                Node current = head;
                while(current.next != null){
                    current = current.next;
                }
                current.next = new Node(list.get(i));
            }
        }
        
    }
    /**
     * Adds an Meteorite Object to the MeteoriteLinkedList
     */
    public boolean add(Meteorite m) throws IllegalArgumentException{
        //Throws IllegalArgumentException when the Meteorite object that is given is null
        if (m == null)
            throw new IllegalArgumentException("The meteorite is null");
        //Create a Node n that contains m
        Node n = new Node(m);
        //If the LinkedList is empty change the head to n
        if (head == null){
            this.head = n;
        }
        else{
            Node current = head;
            //Goes to the last Node in the MeteoriteLinkedList
            while(current.next!=null){
                current = current.next;
            }
            //Connect n with the last node of the MeteoriteLinkedList
            current.next = n;
        }
        size++;
        return true;
        }

    /**
     *  Removes the Node that has the same name and id as the one given
     */    
    public Meteorite remove(String name, int id){
        //If the MeteoriteLinkedList is empty, return null
        if (head == null){
            return null;
        }
        //If the head of the list have the same name and id, returns the head
        if (head.data.getName().equals(name)&&head.data.getId() == id){
            Meteorite result = head.data;
            head = head.next;
            return result;

        }
        else{
            //Goes through the list and finds the one that has the same name and id. Return the said object and remove it form the list
            Node current = head;
            while(current.next!=null){
                if(current.next.data.getName().equals(name)&&current.next.data.getId()==id){
                    Meteorite result = current.next.data;
                    current.next = current.next.next;
                    return result;
                }
                current = current.next;
            }
        }
        return null;
    }

    /**
     * Method that converts LinkedList into a string that can be also printed
     */
    public String toString(){
        String result = "";
        Node current = head;
        while(current!= null){
            //Uses the toString method in Meteorite class
            result += current.data.toString() +"\n";
            current=current.next;
        }
        return result;
    }

    // public void printContent() {
    //     StringBuilder sb = new StringBuilder("[");
    //     Node node = head;
    //     while (node != null) {
    //         sb.append(node.data).append(',');
    //         node = node.next;
    //     }
    //     int lastComma = sb.lastIndexOf(",");
    //     if (lastComma != -1) {
    //         sb.deleteCharAt(lastComma);
    //     }
    //     sb.append(']');
    //     System.out.println(sb);
    // }

    /**
     * https://cs.nyu.edu/~joannakl/cs102_f20/projects/project2.html
     * Adapted from Data Storage and Organization section of above website
     * @author Professor Klukowska
     */
    private class Node implements Comparable<Node> {
        Meteorite data;
        Node next;
    
        Node ( Meteorite data ) {
            this.data = data;
        }
    
        public String toString () {
            return data.toString();
        }
    
        public boolean equals( Object o ) {
            if (this == o) return true;
            if (!(o instanceof Node)) {
                return false;
            }
            Node other = (Node) o;
            if (!this.data.equals(other.data)) {
                return false;
            }
            return true;
        }
    
        public int compareTo (Node n ) {
            return data.compareTo(n.data);
        }
    }
    
}
