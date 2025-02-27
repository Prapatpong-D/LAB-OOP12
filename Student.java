
import java.io.*;


public class Student implements  Serializable {
    private String name;
    private int ID;
    private int money;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    
    public Student(String studentName, int ids){
        this.name = studentName;
        this.ID = ids;
        
    }
    public Student(String studentName, int ids, int balance){
        this.name = studentName;
        this.ID = ids;
        this.money = balance;
    }
}
