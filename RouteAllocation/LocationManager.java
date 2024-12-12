package RouteAllocation;

//This class will handle storage and manipulation of locations within the systems
/*
 * The LocationManager has two attributes
 * @attribute locations
 * @attribute numberOfLocations
 * 
 * It has a number of features or functions that are useful to the user
 * Such as:
 * Adding a location
 * getting a stored location
 * removing a stored location
 * and checking if a location is present
 */
public class LocationManager {

    Location[] locations;
    int numberOfLocations;
    
    public LocationManager(){
        this.locations = new Location[1];
        this.numberOfLocations = 0;
    }

    //This function extends the locations array when full
    public boolean extendLocationsArray(){

        try{

                Location[] newLocations = new Location[this.locations.length * 2];

                for(int a = 0; a < this.locations.length; a++){
                    newLocations[a] = this.locations[a];
                }

                this.locations = newLocations;
            
                return true;

        }catch(Exception ex){
            return false;
        }
    }

    //This function checkes if the array needs extension
    public boolean locationsArrayNeedsExtension(){

        if(this.locations.length == this.numberOfLocations) return true;

        return false;
    }

    public boolean addLocation(Location location){

        if(locationsArrayNeedsExtension()){
            extendLocationsArray();
        }

        try{
            this.locations[this.numberOfLocations] = location;
            this.numberOfLocations++;
            return true;

        }
        catch(Exception ex){
            return false;
        }

    }

    //This function looks in the stored locations if the location exists
    public boolean isLocationPresent(String nameOfLocation){

        for(int a = 0; a < this.numberOfLocations; a++){
            if(this.locations[a].getLocationName().compareTo(nameOfLocation) == 0){
                return true;
            }
        }

        return false;

    }

    //This function returns a location if it is present in the list of stored locations
    public Location getLocation(String nameOfLocation){

        Location intendedLocation = new Location("Null");

        for(int b = 0; b < this.numberOfLocations; b++){
            if(this.locations[b].getLocationName().compareTo(nameOfLocation) == 0){
                intendedLocation = this.locations[b];
            }
        }

        return intendedLocation;

    }

    //This function removes a location from a list of stored locations
    //If it isn't present, it returns a location with a place name of "Null"
    public Location removeLocation(String nameOfLocation){

        Location location = new Location("Null");
        int position = -1;

        if(isLocationPresent(nameOfLocation)){

            for(int a = 0; a < this.numberOfLocations; a++){

                if(this.locations[a].getLocationName().compareTo(nameOfLocation) == 0){
                    position = a;
                    location = this.locations[a];
                    break;
                }

            }

            for(int b = 0; b < this.numberOfLocations; b++){
                if(b > position && b != -1){
                    this.locations[b - 1] = this.locations[b];
                }
            }

        }

        return location;

    }
}
