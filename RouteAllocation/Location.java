package RouteAllocation;

public class Location{

    String name;
    String latitude;
    String longitude;
    Location []neighbours = new Location[1];
    String []namesOfNeigbours;
    int countOfNamesOfNeighbours = 0;
    int countOfNeighbours = 0;

    public Location(String placeName){

        this.name = placeName; 
    }

    public String getLocationName(){
        return this.name;
    }

    public boolean setLatitude(String latitude){
        try{
            this.latitude = latitude;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    public boolean setLongitude(String longitude){
        try{
            this.longitude = longitude;
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    //Add a neighbour name to a the list of neighbours
    public boolean addNeighbour(String neighbourName){

        try{
            if(namesOfNeigbours.length == countOfNamesOfNeighbours){
                String []neighboursNew = new String[countOfNamesOfNeighbours * 2];

                for(int i = 0; i < countOfNamesOfNeighbours;i++){
                    neighboursNew[i] = this.namesOfNeigbours[i];
                }

                this.namesOfNeigbours = neighboursNew;
            }

                this.namesOfNeigbours[this.countOfNamesOfNeighbours] = neighbourName;
                this.countOfNamesOfNeighbours++;

                return true;
        }catch(Exception ex){
            return false;
        }

    }

    //Searches the list of names in the nighbours to check if it is a neighbour to the current location
    public boolean searchForNeighbour(String neighbourName){

        for(int i = 0; i < this.countOfNeighbours; i++){
            if(this.namesOfNeigbours[i].compareTo(neighbourName) == 0){
                return true;
            }
        }

        return false;
    }

    //returns a string of the neighbours
    public String listNeighbours(){
        String list = "";

        for(int i = 0; i < this.countOfNeighbours; i++){
            if(i == this.countOfNeighbours - 1){
                list += this.namesOfNeigbours[i];
            }else{
                list += this.namesOfNeigbours[i] + ", ";
            }
        }

        return list;
    }

    //returns the latitude and longitude of the current location
    public String[] getLocationCoordinates(){
        String[] location = new String[2];

        location[0] = this.latitude;
        location[1] = this.longitude;

        return location;
    }

    //Adds the location to the list of neighbours stored in this location
    public boolean addLocationOfNeighbour(Location location){

        try{
            if(this.neighbours.length == this.countOfNeighbours){

                Location []neighboursNew = new Location[this.countOfNeighbours * 2];
                
                for(int a = 0; a < this.countOfNeighbours; a++){
                    neighboursNew[a] = this.neighbours[a];
                }

                this.neighbours = neighboursNew;
            }

            this.neighbours[this.countOfNeighbours] = location;
            this.countOfNeighbours++;

            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    //Checks if the location is one of the neighbours of the current location
    public boolean isLocationANeighbour(String name){
        
        for(int b = 0; b < this.countOfNamesOfNeighbours; b++){
            if(this.namesOfNeigbours[b].compareTo(name) == 0){
                return true;
            }
        }

        return false;
    }

    //Returns the number of names of neighbors it has
    public int getNumberOfNeigbours(){
        return this.countOfNamesOfNeighbours;
    }

    /*
      *  This function returns a list of coordinates for the location
      *  If it knows the location coordinates, it will return the latitude and longitude
      *  If it doesn't know the coordinates because it's not a neighbour, it'll report accordingly
      *  If It has the location as a neighbour but just doesn't know the coordinates, it will report accordingly
      *
      * Output [result, data]
      *     result can be either true or false
      *     data can be the reason or the coordinates
    */
    public String[] getNeighbourCoordinates(String name){

        String[] response = new String[2];

        if(isLocationANeighbour(name)){
            for(int c = 0; c < this.countOfNeighbours; c++){
                if(this.neighbours[c].getLocationName().compareTo(name) == 0){
                    response[0] = "True";
                    response[1] = "Latitude: " + this.neighbours[c].getLocationCoordinates()[0] + " Longitude: " + this.neighbours[c].getLocationCoordinates()[1];
                }

                response[0] = "False";
                response[1] = "The given location's coordinates are not known by this location";

            }
        }else{
            response[0] = "False";
            response[1] = "The given location is not a neighbour to the current location";
        }

        return response;
    }
}