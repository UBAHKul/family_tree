public package Family;
 
import java.util.*;
 
public class family {
    public static boolean iAmDebugging = true;
    private Amoeba myRoot = null;
    public static int myDepth = 0;
    public static int mySize = 0;
 
    public family(String name) {
        myRoot = new Amoeba(name, null);
    }
 
    public   class Amoeba {
 
        public String myName; // имя
        public Amoeba myParent; // родитель
        public ArrayList<Amoeba> myChildren; // дети
 
        public Amoeba(String name, Amoeba parent) {
            myName = name;
            myParent = parent;
            myChildren = new ArrayList<Amoeba>();
        }
 
        public String toString() {
            return myName;
        }
 
 
 
        // Добавляет ребенка с заданным именем
        public void addChild(String childName) {
            Amoeba child = new Amoeba(childName, this);
            myChildren.add(child);
        }
    }
 
    //Добавляет ребенка по имени родителя
    public void addChild(String parentName, String childName) {
        if (myRoot != null) {
            family.addChildHelper(parentName, childName, myRoot);
        }
    }
 
    public static void addChildHelper(String parentName, String childName, Amoeba currentAmoeba) {
        if (currentAmoeba.myName.equals(parentName)) {
            currentAmoeba.addChild(childName); // если родитель соответствует, то добаляем
        } else {
            Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
            while (iter.hasNext()) {
                family.addChildHelper(parentName, childName, iter.next());
            }
 
        }
    }
 
 
    public void printFlat() {
        if (myRoot != null) {
            family.printFlatHelper(myRoot);
        }
    }
 
    public static void printFlatHelper(Amoeba currentAmoeba) {
        if (currentAmoeba.myChildren == null)
            System.out.println(currentAmoeba.myName);
        else {
            System.out.println(currentAmoeba.myName);
            Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
            while (iter.hasNext()) {
                family.printFlatHelper(iter.next());
            }
        }
    }
 
    // Выводит имена всех членов семьи
 
    public void print() {
        if (myRoot != null) {
            family.printHelper(myRoot);
        }
    }
 
    public static void printHelper(Amoeba currentAmoeba) {
        if (currentAmoeba.myChildren == null) {
            depthIndenter(myDepth);
            System.out.println(currentAmoeba.myName);
        } else {
            depthIndenter(myDepth);
            System.out.println(currentAmoeba.myName);
            myDepth++;
            Iterator<Amoeba> iter = currentAmoeba.myChildren.iterator();
            while (iter.hasNext()) {
                depthIndenter(myDepth);
                family.printHelper(iter.next());
            }
            myDepth--;
        }
    }
 
    public static void depthIndenter(int myDepth) {
        while (myDepth > 0) {
            System.out.print("    ");
            myDepth--;
        }
    }
 
    public static void main(String[] args) {
        family family = new family("Amos McCoy");
        family.addChild("Petrov", "mom/dad");
        family.addChild("Petrov", "auntie");
        family.addChild("mom/dad", "me");
        family.addChild("mom/dad", "Oleg");
        family.addChild("mom/dad", "Maria");
        family.addChild("me", "Oleg");
        family.addChild("me", "Alexandr");
        family.addChild("me", "Elena");
        family.addChild("Oleg", "Alexey");
        family.addChild("Oleg", "Natalia");
        family.addChild("Elene", "Ivan");
        family.addChild("Elena", "Evgenyi");
 
        if (iAmDebugging) {
            family.printFlat();
        }
 
        System.out.println("Here's the family:");
        family.print();
 
    }
} 

