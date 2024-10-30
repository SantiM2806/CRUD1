//Creación de la base de datos contacts.db y una tabla contacts.

import java.sql.*;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:contacts.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS contacts ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "name TEXT NOT NULL,"
                   + "phone TEXT NOT NULL UNIQUE)";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

// Clase principal llamada Database
import java.sql.*; // Importación de paquetes.

public class Database { // Clase "Database"
    private static final String DB_URL = "jdbc:sqlite:contacts.db"; // Variable "DB_URL"

    // Método para conectar con la base de datos en línea 6
    public static Connection connect() throws SQLException { // Método "connect"
        return DriverManager.getConnection(DB_URL);
    }

    // Método para crear la tabla de contactos
    public static void createTable() { // Método "createTable" 
        String sql = "CREATE TABLE IF NOT EXISTS contacts (" // Variable "sql" 
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "name TEXT NOT NULL,"
                   + "phone TEXT NOT NULL UNIQUE)";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) { // Variables "conn" y "stmt" 
            stmt.execute(sql);
        } catch (SQLException e) { // Variable "e" 
            System.out.println(e.getMessage());
        }
    }

    // Método para crear un contacto
    public static void createContact(String name, String phone) { // Método "createContact" 
        String sql = "INSERT INTO contacts(name, phone) VALUES(?, ?)"; // Variable "sql" 
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) { // Variables "conn" y "pstmt"
            pstmt.setString(1, name); // Variables "name" y "phone"
            pstmt.setString(2, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) { // Variable "e"
            System.out.println(e.getMessage());
        }
    }

    // Método para leer todos los contactos
    public static void readContacts() { // Método "readContacts"
        String sql = "SELECT * FROM contacts"; // Variable "sql"
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { // Variables "conn", "stmt" y "rs"
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " Name: " + rs.getString("name") + " Phone: " + rs.getString("phone")); // Variables "id", "name" y "phone" 
            }
        } catch (SQLException e) { // Variable "e"
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un contacto
    public static void updateContact(int id, String name, String phone) { // Método "updateContact" y variables "id", "name", "phone"
        String sql = "UPDATE contacts SET name = ?, phone = ? WHERE id = ?"; // Variable "sql"
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) { // Variables "conn" y "pstmt"
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { // Variable "e"
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un contacto
    public static void deleteContact(int id) { // Método "deleteContact" y variable "id" 
        String sql = "DELETE FROM contacts WHERE id = ?"; // Variable "sql"
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) { // Variables "conn" y "pstmt" 
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { // Variable "e"
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) { // Método "main"
        Database.createTable(); // Llamada a métodos CRUD
        Database.createContact("John Doe", "123-456-7890");
        Database.readContacts();
        Database.updateContact(1, "Johnathan Doe", "098-765-4321");
        Database.deleteContact(1);
    }
}
