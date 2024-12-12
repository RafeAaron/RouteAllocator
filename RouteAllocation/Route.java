package RouteAllocation;

public class Route {
    private String routeName;
    private Location[] routeLocations;
    private int sizeOfLocationsOnRoute;
    private Connector[] connections;
    private int sizeOfConnectors;

    public Route(String nameOfRoute){
        this.routeName = nameOfRoute;
        this.routeLocations = new Location[1];
        this.sizeOfLocationsOnRoute = 0;
        this.connections = new Connector[1];
        this.sizeOfConnectors = 0;
    }

    //Checks if the array is full or not
    public boolean isConnectionsArrayFull(){
        if(this.connections.length == this.sizeOfConnectors) return true;

        return false;
    }

    //Returns the first location in an array of locations
    public Location getStartingLocation(){
        if(this.sizeOfLocationsOnRoute < 1) return new Location("Null");

        return this.routeLocations[0];
    }

    //Returns the last location in an array of locations
    public Location getEndingLocation(){
        if(this.sizeOfLocationsOnRoute < 1) return new Location("Null");

        return this.routeLocations[this.sizeOfLocationsOnRoute - 1];
    }

    //Expands the Connections array when full
    public boolean expandConnectionsArray(){
        try{
            Connector[] newConnectorArray = new Connector[this.connections.length * 2];

            for(int a = 0; a < this.connections.length; a++){
                newConnectorArray[a] = this.connections[a];
            }

            this.connections = newConnectorArray;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    //Adds a location to the route and also a connector between the newest location and the old location
    public boolean addLocationToRoute(Location location){

        try{
        
            if(this.routeLocations.length == this.sizeOfLocationsOnRoute){
                Location[] newRouteLocations = new Location[this.sizeOfLocationsOnRoute * 2];

                for(int i = 0; i < this.routeLocations.length; i++){
                    newRouteLocations[i] = this.routeLocations[i];
                }

                this.routeLocations = newRouteLocations;
            }

            this.routeLocations[sizeOfLocationsOnRoute] = location;

            if(this.routeLocations.length == 1){
                return true;
            }else{
                Connector connect = new Connector(routeLocations[this.sizeOfLocationsOnRoute - 1].getLocationName(), location.getLocationName());

                if(isConnectionsArrayFull()){
                    if(expandConnectionsArray()){

                        this.connections[this.sizeOfConnectors] = connect;
                        this.sizeOfConnectors++;

                    }else{
                        return false;
                    }
                }else{
                    this.connections[this.sizeOfConnectors] = connect;
                    this.sizeOfConnectors++;
                }

                return true;

            }
        }catch(Exception ex){

            return false;

        }
    }

    //Returns a list of connecting points
    public String getConnections(){
        String connectionsList = "";

        for(int i = 0; i < this.sizeOfConnectors; i++){
            if(i == this.sizeOfConnectors - 1){
                connectionsList += this.connections.toString();
            }

            connectionsList += this.connections[i].toString() + ", ";
        }

        return connectionsList;
    }

    //Get the number of connections present on route
    public int getNumberOfConnections(){
        return this.sizeOfConnectors;
    }

    //Get the number of routes on route
    public int getNumberOfLocationsOnRoute(){
        return this.sizeOfLocationsOnRoute;
    }

    //Returns the name of the route
    public String getRouteName(){
        return this.routeName;
    }

    public String getNamesOfLocationsOnRoute(){

        String locations = "";

        for(int c = 0; c < this.sizeOfLocationsOnRoute; c++){
            
            if(c == this.sizeOfLocationsOnRoute - 1){
                locations += this.routeLocations[c].getLocationName();
            }

            locations += this.routeLocations[c].getLocationName() + ", ";
        }

        return locations;
    }

    public String searchForLocationOnRoute(Location location){

        for(int d = 0; d < this.sizeOfLocationsOnRoute; d++){
            if(this.routeLocations[d].getLocationName().compareTo(location.getLocationName()) == 0){
                return "Present";
            }
        }

        return "Absent";
    }

    public Location[] getLocationsOnRoute(){
        return this.routeLocations;
    }
}