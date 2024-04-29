# Railway Infrastructure Simulation

This Java application simulates and manages railway infrastructure, including railway stations, train connections, and various types of railroad cars. It allows for dynamic creation and manipulation of trainsets and railway stations, managing logistic operations like loading cargo, connecting cars, and ensuring proper route and collision management across a network of tracks.

## Features

### Trainset Management
- **Dynamic Locomotive Attributes**: Each locomotive has a maximum number of railroad cars, maximum load weight, and a requirement for electric grid connections.
- **Unique Identification**: Locomotives and railroad cars are assigned unique IDs upon creation.
- **Speed Variation**: Locomotive speeds vary by ?3% every second, influencing the movement dynamics of trainsets along routes.

### Railroad Car Types
- **Diverse Types**: Includes passenger, post office, baggage, restaurant, and various freight car types such as basic, heavy, refrigerated, for liquid/gaseous/explosive/toxic materials.
- **Special Features**: Each railroad car type has unique features, e.g., need for electrical connection, special cargo conditions.

### Route Management
- **Algorithmic Route Determination**: Routes between stations are determined using an algorithmic approach based on a graph of railway connections.
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
- Access detailed reports through the console by providing the unique identifier of a trainset.
- Review the `AppState.txt` file for a historical log of all train operations sorted by specified criteria.

## Contact

For support or queries, please contact me at:

- **Email**: [dgutnik1@gmail.com](mailto:dgutnik1@gmail.com)
- **LinkedIn**: [Your LinkedIn Profile](https://www.linkedin.com/in/dmytro-hutnyk-a68600254/)