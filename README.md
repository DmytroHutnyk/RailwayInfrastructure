# Railway Infrastructure Simulation

This Java application simulates and manages railway infrastructure, including railway stations, train connections, and various types of railroad cars. 
It allows for dynamic creation and manipulation of trainsets and railway stations, managing logistic operations like loading cargo, connecting cars, and ensuring proper route and collision management across a network of tracks.

## Features

### Trainset Management
- **Dynamic Locomotive Attributes**: Each locomotive has a maximum number of railroad cars, maximum load weight, and a requirement for electric grid connections.
- **Unique Identification**: Locomotives and railroad cars are assigned unique IDs upon creation.
- **Speed Variation**: Locomotive speeds vary by ?3% every second, influencing the movement dynamics of trainsets along routes.

### Railroad Car Types
- **Diverse Types**: Includes passenger, post office, baggage, restaurant, and various freight car types such as basic, heavy, refrigerated, for liquid/gaseous/explosive/toxic materials.
- **Special Features**: Each railroad car type has unique features, e.g., need for electrical connection, special cargo conditions.

### Route Management
- **Algorithmic Route Determination**:  Routes between stations are determined using Dijkstra's shortest path algorithm, ensuring efficient and dynamic route planning based on a graph of railway connections.
- **Collision Prevention**: Ensures only one trainset moves between two stations at any time, with others queued according to appearance order.

### Safety and Monitoring
- **Speed Monitoring**: Triggers a `RailroadHazard` exception if a trainset exceeds 200 km/h, providing detailed safety warnings.
- **Comprehensive Reports**: Displays detailed reports about trainsets, including progress, cargo summary, and other logistics after entering a trainset identifier.

### Data Persistence and Reporting
- **Automatic Data Logging**: Every 5 seconds, details of all trainsets are saved to `AppState.txt`, sorting trainsets and cars by specific attributes.
- **Exception Handling**: All operational exceptions are caught and communicated effectively, ensuring the application runs smoothly without interruption.

## Usage Overview

### Managing Trainsets
- Create and modify trainsets by attaching/detaching railroad cars, loading cargo, and setting routes.
- Monitor and adjust the operational parameters in real-time based on dynamic speed changes and route availability.

### Viewing Reports and Logs
- Detailed reports are available through the console by providing the unique identifier of a trainset.
- `AppState.txt` file is used for a historical log of all train operations sorted by specified criteria.

### Upon launching the application, a console-based menu is displayed with the following options:
##  Console Menu


###  **Add Entities**
- **`add train <train_name>`**  
  Creates a new locomotive with a random starting and destination station.
- **`add connection <station1> <station2> <distance>`**  
  Creates a new railway connection between stations.
- **`add station <station_name>`**  
  Adds a new station to the railway network.
- **`add railcar <train_id> <railcar_type>`**  
  Attaches a new railcar to an existing trainset.

### ️ **Remove Entities**
- **`remove train <train_id>`**  
  Removes a trainset from the system.
- **`remove connection <station1> <station2>`**  
  Deletes a railway connection.
- **`remove station <station_name>`**  
  Removes a station.
- **`remove railCar <train_id> <railcar_id>`**  
  Detaches a railcar from a trainset.

### **Trainset Information**
- **`getInfo <train_id>`**  
  Displays details about a specific trainset, including:
    -  Route progress
    -  Attached railcars
    -  Cargo and passenger details

###  **Additional Commands**
- **`help`**  
   Displays usage instructions.
- **`quit`**  
   Exits the application.

---
 **Note:** Commands should be entered exactly as shown, with appropriate parameters where needed.  
 **Predefined Locomotives:** The system initializes **24 locomotives** with IDs ranging from **1 to 24**. These can be used directly when assigning railcars or retrieving trainset information.  
 **Available Railcar Types:** The following railcar types can be attached to trainsets:
- `PassengerRailCar`
- `RestaurantRailCar`
- `BaggageMailRailCar`
- `ExplosiveRailCar`
- `GaseousRailCar`
- `LiquidRailCar`
- `PostOfficeRailCar`
- `LiquidToxicRailCar`
- `RefrigeratedRailCar`
- `ToxicRailCar`  
   **Predefined Stations:** Station names are automatically loaded from the `cities.txt` file upon application startup.

## Steps to Start the Application
### 1. Clone the Repository
Open a terminal and run:

```bash
git clone https://github.com/yourusername/your-repository.git
```
Replace yourusername and your-repository with your actual GitHub username and repository name.

### 2. Navigate to the Project Directory
Open a terminal and navigate to the root directory of your project:

```bash
cd /path/to/your/project
```

### 3. Compile the Project
Use the Java compiler to build the application:

- **macOS/Linux & Windows(PowerShell):**:
  ```bash
  javac -d out -cp src (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
  ```
- **Windows (CMD only):**:
  ```bash
  javac -d out -cp src $(for /R %i in (*.java) do @echo %i)
  ```

### 4. Run the Application
Start the application using following command:
 ```bash
  java -cp out Main
  ```