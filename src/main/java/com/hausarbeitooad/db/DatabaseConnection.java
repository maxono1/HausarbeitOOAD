package com.hausarbeitooad.db;

import com.hausarbeitooad.entity.Nutzer;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.entity.Spiel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


public class DatabaseConnection {
    /* the default framework is embedded */
    private String framework;
    private String protocol;

    private ArrayList<Statement> statements;
    private Connection conn;
    private PreparedStatement psInsert;
    private ResultSet rs;

    private String tableName;

    public static void main(String[] args) {
        DatabaseConnection lol = new DatabaseConnection();

        try {
            Spiel fallGuys = new Spiel(1, "Fall Guys", "Don't Fall in the Slime", 1.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/CSGO.png"), new FileInputStream("src/main/resources/images/CSGO.png"));
            Nutzer nutzer = new Nutzer("maxi", "12345", 100.0);
            NutzerBesitzt nutzerBesitzt = new NutzerBesitzt(1, "maxi");
            Rezension rezension = new Rezension(1, "maxi", 75, "jo, war super Spiel, habe ich mit Freuden spielen dürfen.");
            //lol.insertImage(new FileInputStream("src/main/resources/images/CSGO.png"), "galaxie");
            lol.insertNutzer(nutzer);
            lol.insertSpiel(fallGuys);
            lol.insertNutzerBesitzt(nutzerBesitzt);
            lol.insertRezension(rezension);

        } catch (FileNotFoundException fnfe) {
            System.err.println("file not found");
        } catch (SQLException e) {
            System.err.println("some SQL Statements are broken.");
            printSQLException(e);
            //throw new RuntimeException(e);
        }
        lol.selectAll();
        lol.closeDB();

    }

    public DatabaseConnection() {
        framework = "embedded";
        protocol = "jdbc:derby:";
        tableName = "gameImages";
        statements = new ArrayList<>();

        System.out.println("Datenbank starting in " + framework + " mode");

        try {
            Properties props = new Properties(); // connection properties
            // providing a user name and password is optional in the embedded
            // and derbyclient frameworks
            props.put("user", "user1");
            props.put("password", "user1");
            /* By default, the schema APP will be used when no username is
             * provided.
             * Otherwise, the schema name is the same as the user name (in this
             * case "user1" or USER1.)
             *
             * Note that user authentication is off by default, meaning that any
             * user can connect to your database using any password. To enable
             * authentication, see the Derby Developer's Guide.
             */
            String dbName = "derbyDB"; // the name of the database
            /*
             * This connection specifies create=true in the connection URL to
             * cause the database to be created when connecting for the first
             * time. To remove the database, remove the directory derbyDB (the
             * same as the database name) and its contents.
             *
             * The directory derbyDB will be created under the directory that
             * the system property derby.system.home points to, or the current
             * directory (user.dir) if derby.system.home is not set.
             */
            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);
            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);

            Statement statement = conn.createStatement();
            statements.add(statement);
            statement.execute("create table imagetest(num int, name varchar(255), image blob, primary key (name))");
        } catch (SQLException sqlException) {

            printSQLException(sqlException);
        }
    }

    //hier den index returnen, nur ein weg finden zu testen ob es den index gibt
    //man kann in sql ein index erstellen
    public boolean insertImage(FileInputStream fis, String uniqueName) {
        try {
            psInsert = conn.prepareStatement("insert into imagetest values (?,?,?)");
            statements.add(psInsert);

            psInsert.setInt(1, 1);
            psInsert.setString(2, uniqueName);

            psInsert.setBlob(3, fis);
            psInsert.executeUpdate();

            return true;

        } catch (SQLException sqlException) {
            printSQLException(sqlException);
            return false;
        }

    }

    public void selectAll() {
        try {
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery("SELECT * from imagetest");
            ResultSetMetaData rsmd = everything.getMetaData();
            System.out.println("querying select * from imagetest");
            int columnNumber = rsmd.getColumnCount();
            while (everything.next()) {
                for (int i = 1; i <= columnNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = everything.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            everything.close();
        } catch (SQLException s) {
            printSQLException(s);
        }

    }

    public InputStream retrieveImage(String uniqueName) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * from imagetest where name='" + uniqueName + "'");
            if (resultSet.next()) {
                Blob imageBlob = resultSet.getBlob("image");
                System.out.println(imageBlob.length());
                resultSet.close();
                return imageBlob.getBinaryStream();
            }
            resultSet.close();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return null;
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
    }

    public void dropTables() {
        try {
            Statement s = conn.createStatement();
            statements.add(s);
            s.execute("drop table imagetest");

        } catch (SQLException s) {
            printSQLException(s);
        }
    }

    public void closeDB() {
        if (framework.equals("embedded")) {
            try {
                // the shutdown=true attribute shuts down Derby
                DriverManager.getConnection("jdbc:derby:;shutdown=true");

                // To shut down a specific database only, but keep the
                // engine running (for example for connecting to other
                // databases), specify a database in the connection URL:
                //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
            } catch (SQLException se) {
                if (((se.getErrorCode() == 50000)
                        && ("XJ015".equals(se.getSQLState())))) {
                    // we got the expected exception
                    System.out.println("Derby shut down normally");
                    // Note that for single database shutdown, the expected
                    // SQL state is "08006", and the error code is 45000.
                } else {
                    // if the error code or SQLState is different, we have
                    // an unexpected exception (shutdown failed)
                    System.err.println("Derby did not shut down normally");
                    printSQLException(se);
                }
            }
        }
        // release all open resources to avoid unnecessary memory usage

        // ResultSet
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

        // Statements and PreparedStatements
        int i = 0;
        while (!statements.isEmpty()) {
            // PreparedStatement extend Statement
            Statement st = (Statement) statements.remove(i);
            try {
                if (st != null) {
                    st.close();
                    st = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }
        }

        //Connection
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }


    /**
     * Reports a data verification failure to System.err with the given message.
     *
     * @param message A message describing what failed.
     */
    private void reportFailure(String message) {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }


    private void insertDemoUsers() throws SQLException {
        Statement insertDemo = conn.createStatement();
        insertDemo.execute("insert into Nutzer values('tim',1234);\n" +
                "insert into Nutzer values('abdu',1234);");
        statements.add(insertDemo);
    }

    private void insertNutzer(Nutzer nutzer) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Nutzer values(?,?)");
        statements.add(insert);
        insert.setString(1, nutzer.getbName());
        insert.setString(2, nutzer.getPassword());
    }

    private void insertSpiel(Spiel spiel) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Spiel values(?,?,?,?,?,?,?,?)");
        statements.add(insert);
        insert.setInt(1, spiel.getSpielID());
        insert.setString(2, spiel.getName());
        insert.setString(3, spiel.getBeschreibung());
        insert.setDouble(4, spiel.getPreis()); //hier könnte es probleme geben
        insert.setString(5, spiel.getGenre());
        insert.setInt(6, spiel.getBewertungProzent());
        insert.setBlob(7, spiel.getLogo());
        insert.setBlob(8, spiel.getTitelbild());

        insert.executeUpdate();
    }

    private void insertRezension(Rezension rezension) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Rezension values(?,?,?,?)");
        statements.add(insert);
        insert.setInt(1, rezension.getSpielID());
        insert.setString(2, rezension.getbName());
        insert.setInt(3, rezension.getUserBewertungProzent());
        insert.setString(4, rezension.getText());

        insert.executeUpdate();
    }

    private void insertNutzerBesitzt(NutzerBesitzt nutzerBesitzt) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Nutzer_Besitzt values(?,?)");
        insert.setInt(1, nutzerBesitzt.getSpielID());
        insert.setString(2, nutzerBesitzt.getbName());
    }

    public void dropRezension() throws SQLException {
        Statement s = conn.createStatement();
        statements.add(s);
        s.execute("drop table Rezension");
    }

    public void dropNutzerBesitzt() throws SQLException {
        Statement s = conn.createStatement();
        statements.add(s);
        s.execute("drop table Nutzer_Besitzt");
    }

    public void dropNutzer() throws SQLException {
        Statement s = conn.createStatement();
        statements.add(s);
        s.execute("drop table Nutzer");
    }

    public void dropSpiel() throws SQLException {
        Statement s = conn.createStatement();
        statements.add(s);
        s.execute("drop table Spiel");
    }
}
