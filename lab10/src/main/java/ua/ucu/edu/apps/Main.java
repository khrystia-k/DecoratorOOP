package ua.ucu.edu.apps;

public class Main {

    public static void main(String[] args) {
        CachedDocument sd = new CachedDocument("gs://opp-decorator/figure-65.png"); // here is image from my gcs
        System.out.println(sd.parse());
    }
    
}
