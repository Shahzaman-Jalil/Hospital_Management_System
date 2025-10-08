# Hospital Management System

A comprehensive Java-based application for managing hospital operations, including patient records, appointments, staff management, room allocation, and more.

## Features

- **Patient Management**: Register new patients, update personal details, view all patient information, discharge patients.
- **Appointment Scheduling**: Book, view, and manage appointments for doctors and patients.
- **Staff Management**: Manage doctors, nurses, and other employees, including login and attendance tracking.
- **Room Management**: Allocate, search, and manage hospital rooms.
- **Medical Records**: View and update patient diagnoses, vitals, and prescribed medications.
- **Ambulance Services**: Manage ambulance information and scheduling.
- **Department Management**: Organize and manage hospital departments.
- **User Authentication**: Secure login for doctors, nurses, patients, and reception staff.

## Technologies Used

- **Java** (Swing for GUI)
- **JDBC** for database connectivity
- **SQLite/MySQL** (configurable via connectors in `libraries/`)
- **Custom Icons and Images** for enhanced UI

## Project Structure

```
src/
  Hospital_Management_System/
    [Java source files for all modules]
  icon/
    [Image assets for UI]
libraries/
  [Database connector libraries]
README.md
```

## Setup Instructions

1. **Clone the Repository**
   ```powershell
   git clone https://github.com/Shahzaman-Jalil/Hospital_Management_System.git
   ```

2. **Configure Database**
   - Place your JDBC connector (e.g., `sqlitejdbc_v056.xml`, `mysql_connector_j_9_2_0.xml`) in the `libraries/` folder.
   - Update database connection details in the relevant Java files (e.g., `coon.java`).

3. **Build and Run**
   - Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).
   - Add the JDBC connector JARs to your project dependencies.
   - Run the main class (typically `Hospital_Management_System.java`).

## Usage

- Log in as a doctor, nurse, patient, or receptionist to access respective modules.
- Use the GUI to manage appointments, patient records, staff schedules, and more.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License.

## Authors

- Shahzaman Jalil
