# Route Allocator

## Description
This library was created to help implementing parties to allocate routes to drivers or people in an efficient way. This is supposed to also help balance the load on a transport network based on how many people are currently utilising the transport network. This library has mainly six classes to utilise the library.

## Features
1. Connector

This class is mainly used to coonect different locations together to form a route. This represents the connections between locations that form a route. This will be a key component in the routing allocation.

2. Location

This class is mainly to represent a location in the physical world located at a latitude and a longitude. This is connected to another location using a connector. Therefore this also is a key component in the library

3. LocationManager

This class is mainly used to manage all the locations present in the library at run time. It is used to check  the presence of loactions as well as delete and also add locations.

4. Route

This class is mainly used to represent list of locations and connectors at runtime. This is used to add points to a route, remove points from a route, search for if the point exists and other APIs.

5. RouteManager

This class is mainly used to manage routes at runtime. It checks, adds, modifies routes.

6. Route Allocator

This class is used to allocate routes at runtime based on filters and other metrics. This is the main entry use case of the application and every other module feeds into the route allocator. This is the main API that will be used.

## Installation
### Step 1: Downloading the library
```bash
#This line downloads the library in to your working directory
git clone https://github.com/RafeAaron/RouteAllocator.git
```

### Step 2: Importing the library and all it's features
```java
//This line imports all our features in to the current file
import RouteAllocation.*
```

### Step 3: Using the library
```java
//This creates a new Location
Location location = new Location();

//This creates a new locationManager
LocationManager locationManager = new LocationManager();

//This create a new route
Route route = new Route();

//This create a new route manager
RouteManager routeManager = new RouteManager();

//This creates a new connector
Connector connector = new Connector();
```

## Authors
Rubangakene Stuart rafeaaron21@gmail.com

## Licence
Released under MIT License

Copyright (c) 2024 Rubangakene Stuart.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.