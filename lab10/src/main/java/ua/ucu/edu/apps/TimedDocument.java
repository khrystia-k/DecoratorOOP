package ua.ucu.edu.apps;

// import java.text.MessageFormat;
// import java.time.Duration;
// import java.time.Instant;

public class TimedDocument extends SmartDocument {
    public TimedDocument(String gcsPath) {
        super(gcsPath);
    }

    public String parse() {

        double startTime = System.currentTimeMillis();
        String text = super.parse();
        double endTime = System.currentTimeMillis();
        double totalTime = endTime - startTime;
        System.out.println("Parsing time of "+gcsPath+" is "+totalTime/1000.0+" seconds.\n");
        return text;
    }
}
