package RouteAllocation;
import RouteAllocation.Route;

//This class will handle route allocations on the go
public class RouteManager {

    public Route[] routes;
    private int routesLength;
    private LocationManager locationManager;
    private boolean locationManagerAbsent;

    public RouteManager(LocationManager locationManager){

        if(locationManager != null){
            this.locationManager = locationManager;
            locationManagerAbsent = false;
        }

        this.routesLength = 0;
        this.routes = new Route[1];
        this.locationManagerAbsent = true;
    }

    private boolean routesArrayNeedsExpansion(){

        if(this.routesLength == this.routes.length) return true;

        return false;
    }

    private String expandRoutesArray(){

        try{
            if(routesArrayNeedsExpansion()){
                Route[] newArray = new Route[this.routes.length * 2];

                for(int a = 0; a < this.routes.length; a++){
                    newArray[a] = this.routes[a];
                }

                this.routes = newArray;

                return "Array Expanded";
            }else{
                return "No need to Expand Array";
            }
        }
        catch(Exception ex){
            return "Error in expanding Array";
        }
    }

    public boolean addRoute(Route route){
        try{

            if(routesArrayNeedsExpansion()) expandRoutesArray();

            this.routes[this.routesLength] = route;
            this.routesLength++;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    public boolean removeRoutesWithLocation(Location location){
        int position = -1;

        for(int i = 0; i < this.routesLength; i++){
            if(this.routes[i].searchForLocationOnRoute(location).compareTo("Present") == 0){
                position = i;
            }
        }

        if(position != -1){
            
            for(int a = 0; a < this.routesLength; a++){
                if(a > position){
                    this.routes[a - 1] = this.routes[a]; 
                }
            }

            return true;
        }

        return false;
    }

    public Route[] searchForRoutesWithLocation(Location location){
        int size = 0;
        Route []routesFound = new Route[0]; 
        
        for(int b = 0; b < this.routesLength; b++){
            if(this.routes[b].searchForLocationOnRoute(location).compareTo("Present") == 0){
                size++;

                Route[] newRoutesArray = new Route[size + 1];

                for(int c = 0; c < size; c++){
                    newRoutesArray[c] = routesFound[c];
                }

                routesFound = newRoutesArray;
                routesFound[size] = this.routes[b];
                size++;
            }
        }

        return routesFound;
    }

    public boolean removeRoute(Route route){
        int position = -1;

        for(int a = 0; a < this.routesLength; a++){

            if(this.routes[a].getRouteName().compareTo(route.getRouteName()) == 0){
                position = a;
                break;
            }

        }

        if(position != -1){
            for(int d = 0; d < this.routesLength; d++){
                if(d > position) this.routes[d - 1] = this.routes[d];
            }
        }

        return false;
    }

    public Route searchForRouteByName(String name){
        Route route = new Route("Null", 10);
        
        for(int i = 0; i < this.routesLength; i++){
            if(this.routes[i].getRouteName().compareTo(name) == 0){
                route = this.routes[i];
                break;
            }
        }

        return route;
    }

    public Route[] getAllRoutes(){
        return this.routes;
    }

    public int getNumberOfRoutes(){
        return this.routesLength;
    }

    public String listAllRoutes(){
        String listOfRoutes = "";

        for(int i = 0; i < this.routesLength; i++){

            if(i == this.routesLength - 1){
                listOfRoutes += this.routes[i].getRouteName();
            }

            listOfRoutes += this.routes[i].getRouteName() + ", ";
        }

        return listOfRoutes;
    }

    //Returns a list of routes that begin from a particular location
    public Route[] getRoutesThatBeginFrom(String locationName){

        int continueWithSearch = -1;

        if(this.locationManagerAbsent == false){
            if(!this.locationManager.isLocationPresent(locationName)){
                return new Route[0];
            }else{
                continueWithSearch = 1;
            }
        }else{
            continueWithSearch = 1;
        }

        if(continueWithSearch == 1){

            int numberOfRoutes = 0;
            Route[] routesBeginningFrom = new Route[0];

            for(int i = 0; i < this.routesLength; i++){
                
                if(this.routes[i].getStartingLocation().getLocationName().compareTo(locationName) == 0){
                    
                    Route[] routesArray = new Route[numberOfRoutes + 1];

                    for(int a = 0; a < numberOfRoutes; a++){
                        routesArray[a] = routesBeginningFrom[a];
                    }

                    routesBeginningFrom = routesArray;

                    routesArray[numberOfRoutes] = this.routes[i];
                    numberOfRoutes++;
                }
            }

            return routesBeginningFrom;
        }else{
            return new Route[0];
        }
    }

    //Returns a list of routes that end with a particular location
    public Route[] getRoutesThatEndsWith(String locationName){

        int continueWithSearch = -1;

        if(this.locationManagerAbsent == false){
            if(!this.locationManager.isLocationPresent(locationName)){
                return new Route[0];
            }else{
                continueWithSearch = 1;
            }
        }else{
            continueWithSearch = 1;
        }

        if(continueWithSearch == 1){

            int numberOfRoutes = 0;
            Route[] routesEndingWith = new Route[0];

            for(int i = 0; i < this.routesLength; i++){
                
                if(this.routes[i].getEndingLocation().getLocationName().compareTo(locationName) == 0){
                    
                    Route[] routesArray = new Route[numberOfRoutes + 1];

                    for(int a = 0; a < numberOfRoutes; a++){
                        routesArray[a] = routesEndingWith[a];
                    }

                    routesEndingWith = routesArray;

                    routesArray[numberOfRoutes] = this.routes[i];
                    numberOfRoutes++;
                }
            }

            return routesEndingWith;
        }else{
            return new Route[0];
        }
    }

}
