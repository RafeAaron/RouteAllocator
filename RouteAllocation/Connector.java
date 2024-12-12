package RouteAllocation;

//This class deals with connecting two locations together
public class Connector {
    private String locationStart;
    private String locationEnd;

    public Connector(String nameOfStart, String nameOfEnd){
        this.locationStart = nameOfStart;
        this.locationEnd = nameOfEnd;
    }

    public String toString(){
        return "Start: " +this.locationStart + " End: " + this.locationEnd;
    }

    public String getLocationStart(){
        return this.locationStart;
    }

    public String getLocationEnd(){
        return this.locationEnd;
    }
}