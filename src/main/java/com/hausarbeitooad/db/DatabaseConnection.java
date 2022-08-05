package com.hausarbeitooad.db;

import com.hausarbeitooad.entity.Nutzer;
import com.hausarbeitooad.entity.NutzerBesitzt;
import com.hausarbeitooad.entity.Rezension;
import com.hausarbeitooad.entity.Spiel;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        /*
        try{
            Path filePath = Path.of("src/main/resources/sqlFiles/SpielDatenbank.sql");
            String createTablesSql = Files.readString(filePath);
            System.out.println(createTablesSql);

        } catch (IOException ioException){
            ioException.printStackTrace();
        }*/

        DatabaseConnection lol = new DatabaseConnection();

        try {
            ArrayList<Spiel> bspSpiele = new ArrayList<>();
            bspSpiele.add(new Spiel(1, "banana Guys", "Don't Fall in the Slime", 20.0, "Horrorspiel", 50, new FileInputStream("src/main/resources/images/bananen_game.jpg"), new FileInputStream("src/main/resources/images/bananen_game.jpg")));
            bspSpiele.add(new Spiel(2, "Car Drivers", "Ride or Die, super speed", 40.0, "Racing", 90, new FileInputStream("src/main/resources/images/car.png"), new FileInputStream("src/main/resources/images/car.png")));
            bspSpiele.add(new Spiel(3, "XTreme Bike Ride", "Race down a mountain to be the winner baby", 90.55, "Racing, Thriller", 20, new FileInputStream("src/main/resources/images/bike.png"), new FileInputStream("src/main/resources/images/bike.png")));
            bspSpiele.add(new Spiel(4, "Astronaut Game explore", "explore the galaxy to become rich and influential!", 1.5, "space", 99, new FileInputStream("src/main/resources/images/galaxy.jpg"), new FileInputStream("src/main/resources/images/galaxy.jpg")));
            for (int i = 5; i < 15; i++) {
                bspSpiele.add(new Spiel(i, "Spiel" + i, "Beschreibung" + i, 50, "genre", 20, new FileInputStream("src/main/resources/images/placeholder_grafik.png"), new FileInputStream("src/main/resources/images/placeholder_grafik.png")));
            }
            Nutzer maxi = new Nutzer("maxi", "1234", 100.0);
            Nutzer tim = new Nutzer("tim", "1234", 0);
            NutzerBesitzt nutzerBesitzt = new NutzerBesitzt(1, "maxi", 5);
            Rezension rezension = new Rezension(1, maxi.getbName(), 75, "jo, war super Spiel, habe ich mit Freuden spielen dürfen.");
            //lol.insertImage(new FileInputStream("src/main/resources/images/CSGO.png"), "galaxie");
            lol.insertNutzer(maxi);
            lol.insertSpiel(bspSpiele.get(0));
            lol.insertNutzerBesitzt(nutzerBesitzt);
            lol.insertRezension(rezension);

        } catch (FileNotFoundException fnfe) {
            System.err.println("file not found");
        } catch (SQLException e) {
            System.err.println("some SQL Statements are broken.");
            printSQLException(e);
            //throw new RuntimeException(e);
        } catch (IOException io) {
            io.printStackTrace();
        }
        lol.selectQuery("Select * from Nutzer");
        lol.selectQuery("Select * from Nutzer_Besitzt");
        lol.selectQuery("Select * from Rezension");
        lol.selectQuery("Select * from Spiel");
        //lol.commit();
        lol.closeDB();

    }

    private static DatabaseConnection dbConnInstance = null;

    public static DatabaseConnection getInstance() {
        if (dbConnInstance == null) {
            dbConnInstance = new DatabaseConnection();
        }
        return dbConnInstance;
    }

    private DatabaseConnection() {
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
            String dbName = "derbyDBv2"; // the name of the database
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
            /*
            dropRezension();
            dropNutzerBesitzt();
            dropSpiel();
            dropNutzer();
*/


            Statement statement = conn.createStatement();
            statements.add(statement);
            //Quelle sql skript laden: https://howtodoinjava.com/java/io/java-read-file-to-string-examples/#1-using-filesreadstring-java-11
            //read file line by line: https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java
            //Path filePath = Path.of("src/main/resources/sqlFiles/SpielDatenbank.sql");
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/sqlFiles/SpielDatenbank.sql");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String sqlLine;

            while ((sqlLine = bufferedReader.readLine()) != null) {
                statement.execute(sqlLine);
            }
            fileInputStream.close();


        } catch (SQLException sqlException) {

            printSQLException(sqlException);
        } catch (IOException ioException) {
            ioException.printStackTrace();
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

    /**
     * quelle fehlt, noch suchen
     */
    public void selectQuery(String query) {
        try {
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            ResultSetMetaData rsmd = everything.getMetaData();
            System.out.println(query);
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

    /**
     * gibt guthaben in form eines doubles zurück
     *
     * @author Tim cirksena
     */
    public double selectGuthaben(String username) {
        try {
            String query = "Select guthaben From Nutzer WHERE bName like '" + username + "'";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            //System.out.println(query);
            if (everything.next()) {  //wenn es keine Daten gibt failed es hier
                return everything.getDouble("guthaben");
            } else {
                System.out.println("Es wurde nichts gefunden");
            }
            everything.close();
        } catch (SQLException s) {
            printSQLException(s);
        }
        return 0.0;
    }

    /**
     * negative value um geld abzuziehen
     *
     * @author Tim Cirksena
     */
    public boolean updateGuthaben(String username, double geld) {
        try {
            double tmp = selectGuthaben(username) + geld;
            String query = "UPDATE Nutzer SET guthaben =" + tmp + " WHERE bName like'" + username + "'";
            Statement select = conn.createStatement();
            statements.add(select);
            int everything = select.executeUpdate(query);
            //System.out.println(query);
            //System.out.println(everything); //Hier ist row count
        } catch (SQLException s) {
            printSQLException(s);

        }
        return false;
    }

    public boolean selectUser(String username, String password) {
        try {
            String query = "Select * From Nutzer WHERE bName like '" + username + "'";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            //System.out.println(query);
            while (everything.next()) {
                if (everything.getString("BNAME").equals(username) && everything.getString("PASSWORD").equals(password)) {
                    return true;
                }
            }
            everything.close();
        } catch (SQLException s) {
            printSQLException(s);
        }
        return false;
    }

    public List<Spiel> sammlungView(String username) throws SQLException {
        Statement stmt = conn.createStatement();
        statements.add(stmt);
        ResultSet resultSet = stmt.executeQuery("select logo, Spiel.spielID, name, spielzeit from Nutzer_besitzt join Spiel on Spiel.spielid = Nutzer_besitzt.spielid where Nutzer_besitzt.bName = '" + username + "' order by SpielID asc");
        ArrayList<Spiel> spieleAusDb = new ArrayList<>();
        while (resultSet.next()) {
            //System.out.println(resultSet.getString("name"));
            try {
                spieleAusDb.add(spielFromResultSetName(resultSet));

            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        resultSet.close();
        return spieleAusDb;
    }
    private Spiel spielFromResultSetName(ResultSet resultSet) throws SQLException, IOException {
        String name = resultSet.getString("Name");
        int spielID = resultSet.getInt("SpielID");
        InputStream logo = resultSet.getBlob("logo").getBinaryStream();
        return new Spiel(name, spielID, logo);
    }


    public InputStream retrieveImage(String uniqueName) {
        try {
            Statement stmt = conn.createStatement();
            statements.add(stmt);
            ResultSet resultSet = stmt.executeQuery("SELECT * from imagetest where name='" + uniqueName + "'");
            if (resultSet.next()) {
                Blob imageBlob = resultSet.getBlob("image");
                //System.out.println(imageBlob.length());
                resultSet.close();
                return imageBlob.getBinaryStream();
            }
            resultSet.close();

        } catch (SQLException e) {
            printSQLException(e);
        }

        return null;
    }

    private Spiel spielFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        int spielID = resultSet.getInt("SpielID");
        String name = resultSet.getString("Name");
        String beschreibung = resultSet.getString("beschreibung");
        double preis = resultSet.getDouble("Preis");
        String genre = resultSet.getString("Genre");
        int bewertungProzent = resultSet.getInt("BewertungProzent");
        InputStream logo = resultSet.getBlob("logo").getBinaryStream();
        InputStream titelbild = resultSet.getBlob("titelbild").getBinaryStream();

        return new Spiel(spielID, name, beschreibung, preis, genre, bewertungProzent, logo, titelbild);
    }

    private Rezension rezensionFromResultSet(ResultSet resultSet) throws SQLException, IOException {
        int spielID = resultSet.getInt("SpielID");
        String name = resultSet.getString("bName");
        int userBewertungProzent = resultSet.getInt("userBewertungProzent");
        String beschreibung = resultSet.getString("text");

        return new Rezension(spielID, name, userBewertungProzent, beschreibung);
    }

    //https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html
    public List<Spiel> retrieveSpiele() throws SQLException {
        Statement stmt = conn.createStatement();
        statements.add(stmt);
        ResultSet resultSet = stmt.executeQuery("select * from Spiel order by SpielID asc");
        ArrayList<Spiel> spieleAusDb = new ArrayList<>();
        while (resultSet.next()) {
            try {
                spieleAusDb.add(spielFromResultSet(resultSet));

            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        resultSet.close();
        return spieleAusDb;
    }

    public List<Rezension> retrieveRezensionen(int spielID) throws SQLException {
        Statement stmt = conn.createStatement();
        statements.add(stmt);
        ResultSet resultSet = stmt.executeQuery("select * from Rezension where spielID =" + spielID);
        ArrayList<Rezension> rezensionenAusDB = new ArrayList<>();
        while (resultSet.next()) {
            try {
                rezensionenAusDB.add(rezensionFromResultSet(resultSet));

            } catch (IOException io) {
                io.printStackTrace();
            }
        }
        resultSet.close();
        return rezensionenAusDB;
    }

    public Spiel retrieveSpielById(int id) throws SQLException {
        Statement statement = conn.createStatement();
        statements.add(statement);
        ResultSet resultSet = statement.executeQuery("select * from Spiel where SpielID = " + id);
        Spiel spiel = null;
        if (resultSet.next()) {
            try {
                spiel = spielFromResultSet(resultSet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        resultSet.close();
        return spiel;
    }

    public int retrieveSpielzeitNutzerBesitzt(String bname, int spielID) throws SQLException{
        Statement statement = conn.createStatement();
        statements.add(statement);
        ResultSet resultSet = statement.executeQuery("Select spielzeit from Nutzer_Besitzt where bName like '" + bname + "' and spielID = " + spielID);
        if(resultSet.next()){
            return resultSet.getInt("spielzeit");
        } else return 0;
    }

    public boolean besitztNutzerSpiel(String nutzername, int spielID) throws SQLException {
        Statement statement = conn.createStatement();
        statements.add(statement);

        ResultSet resultSet = statement.executeQuery("Select SpielID from Nutzer_Besitzt where bName like '" + nutzername + "'");
        while (resultSet.next()) {
            if (resultSet.getInt("SpielID") == spielID) {
                return true;
            }
        }
        return false;
    }

    public void commit() {
        try {
            conn.commit();
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
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
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     * <p>
     * Quelle: https://db.apache.org/derby/papers/DerbyTut/embedded_intro.html
     * offizielles apache derby tutorial
     * modifiziert von Maximilian Jaesch
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            if(e.getSQLState().equals("X0Y32")){
                System.out.println("tables existieren bereits.");
            } else{
                System.err.println("\n----- SQLException -----");
                System.err.println("  SQL State:  " + e.getSQLState());
                System.err.println("  Error Code: " + e.getErrorCode());
                System.err.println("  Message:    " + e.getMessage());
            }
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    public void insertNutzer(Nutzer nutzer) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Nutzer values(?,?,?)");
        statements.add(insert);
        insert.setString(1, nutzer.getbName());
        insert.setString(2, nutzer.getPassword());
        insert.setDouble(3, nutzer.getGuthaben());

        insert.executeUpdate();
    }

    public void insertSpiel(Spiel spiel) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Spiel values(?,?,?,?,?,?,?,?)");
        statements.add(insert);
        insert.setInt(1, spiel.getSpielID());
        insert.setString(2, spiel.getName());
        insert.setString(3, spiel.getBeschreibung());
        insert.setDouble(4, spiel.getPreis()); //hier könnte es probleme geben
        insert.setString(5, spiel.getGenre());
        insert.setInt(6, spiel.getBewertungProzent());
        insert.setBlob(7, new ByteArrayInputStream(spiel.getLogo()));
        insert.setBlob(8, new ByteArrayInputStream(spiel.getTitelbild()));

        insert.executeUpdate();
    }

    public void insertRezension(Rezension rezension) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Rezension values(?,?,?,?)");
        statements.add(insert);
        insert.setInt(1, rezension.getSpielID());
        insert.setString(2, rezension.getbName());
        insert.setInt(3, rezension.getUserBewertungProzent());
        insert.setString(4, rezension.getText());

        insert.executeUpdate();
    }

    public void insertNutzerBesitzt(NutzerBesitzt nutzerBesitzt) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into Nutzer_Besitzt values(?,?,?)");
        insert.setInt(1, nutzerBesitzt.getSpielID());
        insert.setString(2, nutzerBesitzt.getbName());
        insert.setInt(3,nutzerBesitzt.getSpielzeit());
        insert.executeUpdate();
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
