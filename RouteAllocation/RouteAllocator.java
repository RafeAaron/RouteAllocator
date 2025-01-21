package RouteAllocation;
import RouteAllocation.RouteManager;

public class RouteAllocator {

    RouteManager routeManager;

    public RouteAllocator(RouteManager routeManagerIntro)
    {
        this.routeManager = routeManagerIntro;
    }
    
    public Route assignRoute(String startLocationName, String endLocationName) {
        // Loop through all routes and find the one that matches the start and end location
        for (int i = 0; i < routeManager.getNumberOfRoutes(); i++) {
            Route route = routeManager.routes[i];
            
            // Check if the route starts and ends with the specified locations
            if (route.getStartingLocation().getLocationName().equals(startLocationName) &&
                route.getEndingLocation().getLocationName().equals(endLocationName)) {
                return route; // Found the matching route
            }
        }
        return null; // No route found
    }

    // Assign a route that passes through specific locations
    public Route assignRouteThroughLocation(String startLocationName, String endLocationName, String intermediateLocation) {
        Route[] routesStartingFrom = routeManager.getRoutesThatBeginFrom(startLocationName);

        for (Route route : routesStartingFrom) {
            if (route.getEndingLocation().getLocationName().equals(endLocationName)) {
                // Check if the route passes through the intermediate location
                for (Location location : route.getLocationsOnRoute()) {
                    if (location.getLocationName().equals(intermediateLocation)) {
                        return route; // Return the route if it passes through the intermediate location
                    }
                }
            }
        }
        return null; // No route found that passes through the location
    }

    // Assign the optimal route based on multiple criteria (shortest, least traffic, etc.)
    public Route assignOptimalRoute(String startLocationName, String endLocationName) {
        Route[] routes = routeManager.getRoutesThatBeginFrom(startLocationName);
        Route optimalRoute = null;

        for (Route route : routes) {
            if (route.getEndingLocation().getLocationName().equals(endLocationName)) {
                // Criteria 1: Shortest route
                if (optimalRoute == null || isShorterRoute(route, optimalRoute)) {
                    optimalRoute = route;
                }

                // Criteria 2: Least traffic
                if (optimalRoute == null || hasLessTraffic(route, optimalRoute)) {
                    optimalRoute = route; // Prioritize route with the least traffic
                }
            }
        }
        return optimalRoute;
    }

    // Assign a route based on starting and ending location with a filter (e.g., shortest, least traffic, fastest, least stops)
    public Route assignRouteWithFilter(String startLocationName, String endLocationName, String filterType) {
        Route[] possibleRoutes = routeManager.getRoutesThatBeginFrom(startLocationName);
        Route bestRoute = null;

        for (Route route : possibleRoutes) {
            if (route.getEndingLocation().getLocationName().equals(endLocationName)) {
                // Apply filtering criteria
                if (filterType.equals("shortest")) {
                    if (bestRoute == null || isShorterRoute(route, bestRoute)) {
                        bestRoute = route;
                    }
                }
                else if (filterType.equals("least_traffic")) {
                    if (bestRoute == null || hasLessTraffic(route, bestRoute)) {
                        bestRoute = route;
                    }
                }
                else if (filterType.equals("fastest")) {
                    if (bestRoute == null || isFasterRoute(route, bestRoute)) {
                        bestRoute = route;
                    }
                }
                else if (filterType.equals("least_stops")) {
                    if (bestRoute == null || hasFewerStops(route, bestRoute)) {
                        bestRoute = route;
                    }
                }
            }
        }
        return bestRoute;
    }

    // Helper method to determine if a route is shorter (by the number of locations on the route)
    private boolean isShorterRoute(Route route1, Route route2) {
        return route1.getNumberOfLocationsOnRoute() < route2.getNumberOfLocationsOnRoute();
    }

    // Helper method to determine if a route has less traffic (lower traffic score is better)
    private boolean hasLessTraffic(Route route1, Route route2) {
        return route1.getTrafficScore() < route2.getTrafficScore(); // Compare traffic score (lower is better)
    }

    // Helper method to determine if a route is faster (by travel time, assuming travel time is available)
    private boolean isFasterRoute(Route route1, Route route2) {
        return route1.getTravelTime() < route2.getTravelTime(); // Compare travel time (lower is better)
    }

    // Helper method to determine if a route has fewer stops (fewer intermediate locations)
    private boolean hasFewerStops(Route route1, Route route2) {
        return route1.getNumberOfLocationsOnRoute() < route2.getNumberOfLocationsOnRoute();
    }


}
