package hello.core.Singleton;

public class StatefulService {

    //private int price; //state유지

    public int order(String name, int price) {
        System.out.println("name = " + name + "price = " + price );
        //this.price = price; //문제 발생.
        return price;
    }

    //public int getPrice() {
    //  return price;
    //}

}
