
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * Clase que conté totes les funcions sobre especias*/
public class SpiceController {

    private Connection conn;
    /**Referencia per establir la connexió amb la base de dades */
    public SpiceController(Connection connection) {
        this.conn = connection;
    }
    //Creates
    /**
     * Crea totes les taules de la base de dades*/
    public void createAlltables() {
        //tabla general
        try {
            String createTableSql = "CREATE TABLE spices"
                    + "("
                    + "Name varchar(100) primary key,"
                    + "Introduction text,"
                    + "Description text,"
                    + "Ingredients text,"
                    + "Basic_Preparation text,"
                    + "Recommended_Applications text,"
                    + "Cuisine text,"
                    + "Product_Style varchar(30),"
                    + "Botanical_Name varchar(50),"
                    + "Fold varchar(20),"
                    + "Notes varchar(150),"
                    + "Shell_Life text,"
                    + "Bottle_Style text,"
                    + "Capacity_Volume text,"
                    + "Dimensions text,"
                    + "Cap text,"
                    + "Caffeine_Free text,"
                    + "Scoville_Heat_Scale text,"
                    + "Handling_Storage text,"
                    + "Country_Of_Origin text,"
                    + "Dietary_Preferences text,"
                    + "Allergen_Information text,"
                    + "Page_Link text,"
                    + "Id integer"
                    + ")";
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createTableSql);
            //Tabla product Style
            Statement st = conn.createStatement();
            String createTableProduct = "CREATE TABLE productStyle (format varchar(30) primary key)";
            st.executeUpdate(createTableProduct);
            //Intermediate table product Style
            String createTableIntProduct = "CREATE TABLE interProduct (nameSpices varchar(100) REFERENCES " +
                    "spices(Name) ON UPDATE CASCADE ON DELETE CASCADE, format varchar(30) REFERENCES " +
                    "productStyle(format) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT name_format_pkey" +
                    " PRIMARY KEY(nameSpices, format))";
            Statement intermProd = conn.createStatement();
            intermProd.executeUpdate(createTableIntProduct);
            //Table country origin of spices
            Statement countryst = conn.createStatement();
            String createTableCountry = "CREATE TABLE countryOrigin (country text primary key)";
            countryst.executeUpdate(createTableCountry);
            //Intermediate table country origin and spices
            String createTableIntCountry = "CREATE TABLE interCountry (nameSpices varchar(100) REFERENCES " +
                    "spices(Name) ON UPDATE CASCADE ON DELETE CASCADE, country text REFERENCES " +
                    "countryOrigin(country) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT name_country_pkey" +
                    " PRIMARY KEY(nameSpices, country))";
            Statement intermCount = conn.createStatement();
            intermCount.executeUpdate(createTableIntCountry);
            //Table of cuisine
            Statement cuisinest = conn.createStatement();
            String createTableCuisine = "CREATE TABLE cuisine (geozone text primary key)";
            cuisinest.executeUpdate(createTableCuisine);
            //Intermediate table of cuisine and spices
            String createTableIntCuisine = "CREATE TABLE interCuisine (nameSpices varchar(100) REFERENCES " +
                    "spices(Name) ON UPDATE CASCADE ON DELETE CASCADE, geozone text REFERENCES " +
                    "cuisine(geozone) ON UPDATE CASCADE ON DELETE CASCADE, CONSTRAINT name_cuisine_pkey" +
                    " PRIMARY KEY(nameSpices, geozone))";
            Statement intermCuis = conn.createStatement();
            intermCuis.executeUpdate(createTableIntCuisine);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en crear les taules");
        }
    }

    //Inserts

    /**
     * Omple la base de dades amb la informació obtinguda
     * del csv de l'exercici anterior
     */
    public void fillAllDataSpices() {
        try {
            //Inserts de la tabla general
            String inserts = "INSERT INTO spices"
                    + "(Name, Introduction, Description, Ingredients, Basic_Preparation, Recommended_Applications, " +
                    "Cuisine, Product_Style, Botanical_Name, Fold, Notes, Shell_Life, Bottle_Style, Capacity_Volume, " +
                    "Dimensions, Cap, Caffeine_Free, Scoville_Heat_Scale, Handling_Storage, Country_Of_Origin, " +
                    "Dietary_Preferences, Allergen_Information, Page_Link, Id)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(inserts);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                statement.setString(1, SpiceMain.spices.get(i).get(0));
                statement.setString(2, SpiceMain.spices.get(i).get(1));
                statement.setString(3, SpiceMain.spices.get(i).get(2));
                statement.setString(4, SpiceMain.spices.get(i).get(3));
                statement.setString(5, SpiceMain.spices.get(i).get(4));
                statement.setString(6, SpiceMain.spices.get(i).get(5));
                statement.setString(7, SpiceMain.spices.get(i).get(6));
                statement.setString(8, SpiceMain.spices.get(i).get(7));
                statement.setString(9, SpiceMain.spices.get(i).get(8));
                statement.setString(10, SpiceMain.spices.get(i).get(9));
                statement.setString(11, SpiceMain.spices.get(i).get(10));
                statement.setString(12, SpiceMain.spices.get(i).get(11));
                statement.setString(13, SpiceMain.spices.get(i).get(12));
                statement.setString(14, SpiceMain.spices.get(i).get(13));
                statement.setString(15, SpiceMain.spices.get(i).get(14));
                statement.setString(16, SpiceMain.spices.get(i).get(15));
                statement.setString(17, SpiceMain.spices.get(i).get(16));
                statement.setString(18, SpiceMain.spices.get(i).get(17));
                statement.setString(19, SpiceMain.spices.get(i).get(18));
                statement.setString(20, SpiceMain.spices.get(i).get(19));
                statement.setString(21, SpiceMain.spices.get(i).get(20));
                statement.setString(22, SpiceMain.spices.get(i).get(21));
                statement.setString(23, SpiceMain.spices.get(i).get(22));
                statement.setInt(24, SpiceMain.getId());
                SpiceMain.setId(SpiceMain.getId()+1);
                statement.execute();
            }
            //Inserts de product Style
            String insertProd = "INSERT INTO productStyle (format) VALUES (?) ON CONFLICT DO NOTHING";
            PreparedStatement prod = conn.prepareStatement(insertProd);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                prod.setString(1, SpiceMain.spices.get(i).get(7));
                prod.executeUpdate();
            }
            //Inserts Intermediate product Style
            String insInterprod = "INSERT INTO interProduct(nameSpices, format) VALUES(?,?)";
            PreparedStatement intPr = conn.prepareStatement(insInterprod);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                intPr.setString(1, SpiceMain.spices.get(i).get(0));
                intPr.setString(2, SpiceMain.spices.get(i).get(7));
                intPr.execute();
            }
            //Insert table country origin
            String insertCountry = "INSERT INTO countryOrigin (country) VALUES (?) ON CONFLICT DO NOTHING";
            PreparedStatement countr = conn.prepareStatement(insertCountry);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                countr.setString(1, SpiceMain.spices.get(i).get(19));
                countr.executeUpdate();
            }
            //Inserts intermediate table country origin
            String insInterCount = "INSERT INTO interCountry(nameSpices, country) VALUES(?,?)";
            PreparedStatement intCo = conn.prepareStatement(insInterCount);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                intCo.setString(1, SpiceMain.spices.get(i).get(0));
                intCo.setString(2, SpiceMain.spices.get(i).get(19));
                intCo.execute();
            }
            //Insert table cuisine
            String insertCuisine = "INSERT INTO cuisine (geozone) VALUES (?) ON CONFLICT DO NOTHING";
            PreparedStatement cuis = conn.prepareStatement(insertCuisine);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                cuis.setString(1, SpiceMain.spices.get(i).get(6));
                cuis.executeUpdate();
            }
            //Inserts intermediate cuisine table
            String insInterCuis = "INSERT INTO interCuisine(nameSpices, geozone) VALUES(?,?)";
            PreparedStatement intCui = conn.prepareStatement(insInterCuis);
            for (int i = 1; i < SpiceMain.spices.size(); i++) {
                intCui.setString(1, SpiceMain.spices.get(i).get(0));
                intCui.setString(2, SpiceMain.spices.get(i).get(6));
                intCui.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en carregar les dades");
        }
    }

    /**
     * Métode per introduïr un nou registre en aquest
     * cas, una nova especia
     */
    public void insertNew(){
        //Insert nou registre
        String registrer = null;
        String inserts = "INSERT INTO spices"
                + "(Name, Introduction, Description, Ingredients, Basic_Preparation, Recommended_Applications, " +
                "Cuisine, Product_Style, Botanical_Name, Fold, Notes, Shell_Life, Bottle_Style, Capacity_Volume, " +
                "Dimensions, Cap, Caffeine_Free, Scoville_Heat_Scale, Handling_Storage, Country_Of_Origin, " +
                "Dietary_Preferences, Allergen_Information, Page_Link, Id)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = conn.prepareStatement(inserts);
            System.out.println("Escriu el nom de la nova especia");
            registrer = br.readLine();
            statement.setString(1, registrer);
            System.out.println("Escriu una breu introducció");
            registrer = br.readLine();
            statement.setString(2, registrer);
            System.out.println("Escriu una breu descripció");
            registrer = br.readLine();
            statement.setString(3, registrer);
            System.out.println("Escriu una preparació bàsica");
            registrer = br.readLine();
            statement.setString(4, registrer);
            System.out.println("Escriu un camp d'aplicació");
            registrer = br.readLine();
            statement.setString(5, registrer);
            System.out.println("Escriu les cuines que les fan servir");
            registrer = br.readLine();
            statement.setString(6, registrer);
                System.out.println("Escriu una recomenació per fer servir la especia");
            registrer = br.readLine();
            statement.setString(7, registrer);
            System.out.println("Escriu el format de la especia");
            registrer = br.readLine();
            statement.setString(8, registrer);
            System.out.println("Escriu el nom botànic si en té");
            registrer = br.readLine();
            statement.setString(9, registrer);
            System.out.println("Escriu la dosis si en té");
            registrer = br.readLine();
            statement.setString(10, registrer);
            System.out.println("Escriu una nota si es necesària");
            registrer = br.readLine();
            statement.setString(11, registrer);
            System.out.println("Escriu la forma de conservació");
            registrer = br.readLine();
            statement.setString(12, registrer);
            System.out.println("Escriu el format d'ampolla si en té");
            registrer = br.readLine();
            statement.setString(13, registrer);
            System.out.println("Escriu el volum si en té");
            registrer = br.readLine();
            statement.setString(14, registrer);
            System.out.println("Escriu les dimensions si en té");
            registrer = br.readLine();
            statement.setString(15, registrer);
            System.out.println("Escriu el tipus de tapes si en té");
            registrer = br.readLine();
            statement.setString(16, registrer);
            System.out.println("Te cafeína?");
            registrer = br.readLine();
            statement.setString(17, registrer);
            System.out.println("Escriu els graus de Scoville");
            registrer = br.readLine();
            statement.setString(18, registrer);
            System.out.println("Escriu com s'emmagatzema");
            registrer = br.readLine();
            statement.setString(19, registrer);
            System.out.println("Escriu el país d'origen");
            registrer = br.readLine();
            statement.setString(20, registrer);
            System.out.println("Escriu les preferencies dietàries");
            registrer = br.readLine();
            statement.setString(21, registrer);
            System.out.println("Escriu informació per alergens");
            registrer = br.readLine();
            statement.setString(22, registrer);
            System.out.println("Escriu link de la pàgina per referencies");
            registrer = br.readLine();
            statement.setString(23, registrer);
            statement.setInt(24, SpiceMain.getId());
            SpiceMain.setId(SpiceMain.getId()+1);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Selects
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);

    /**
     * Mostra tota la informació disponible de la
     * taula de spices
     */
    public void selectAllInfo() {
        //Mostra tota la informació d'un registre
        try {
            String consulta = "select * from spices";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                // retrieve data for each column
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + rs.getString(i));
                }
                System.out.println("------------------------------------------------------------------------------\n" +
                        "------------------------------------------------------------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra tots els noms i el seu id de les especies
     * dins de la base de dades
     */
    public void selectSpiceName() {
        //Mostra el nom de la especia i el seu id
        try {
            String consulta = "select * from spices";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "--" + rs.getString("Name") + "--" + rs.getString("Id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra les especies per països que
     * demana l'usuari
     */
    public void selectByCountry() {
        //Mostra les especias per països
        try {
            String country = null;
            String consulta = "select * from intercountry where country = ?";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            System.out.println("Escriu el nom del país en anglès que es vol consultar");
            country = br.readLine();
            sentencia.setString(1, country);
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                // retrieve data for each column
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + rs.getString(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mostra les especies per cuina de tota la base de dades
     */
    public void selectByCuisine() {
        //Mostra les especias per tipus de cuina
        try {

            String consulta = "select * from intercountry";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                // retrieve data for each column
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + rs.getString(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Mostra les especies que content paraules o text
     * que indiqui l'usuari
     */
    public void selectByUser() {
        //Mostra la informació de la especia mitjançant text clau de l'usuari
        try {
            String user = null;
            String consulta = "select * from spices where description like ?";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            System.out.println("Escriu el text que conté la especia que es vol buscar");
            user = br.readLine();
            sentencia.setString(1, "%" + user + "%");
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                // retrieve data for each column
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + rs.getString(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Mostra les especies per format del producte
     * indicat per l'usuari
     */
    public void selectByFormat() {
        //Mostra especia per format de producte
        try {
            String format = null;
            String consulta = "select * from spices where Product_Style = ?";
            PreparedStatement sentencia = conn.prepareStatement(consulta);
            System.out.println("Escriu el format de la especia en àngles");
            format = br.readLine();
            sentencia.setString(1, format);
            sentencia.execute();
            ResultSet rs = sentencia.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                // retrieve data for each column
                for (int i = 1; i <= columnCount; i++) {
                    System.out.println(metaData.getColumnName(i) + ": " + rs.getString(i));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Updates

    /**
     *Actualitza el nom de la especia mitjançant el ID
     * @throws SQLException
     */
    public void actualitzarNomEspecia() throws SQLException {

        ResultSet rs = null;
        String resposta = null;
        int id = 0;

        Statement st = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        try {
            rs = st.executeQuery("SELECT * FROM spices");

            if (rs.next() == false) {
                System.out.println("No hi ha especias insertades. ");
            } else {
                System.out.println("Introdueix el num Id de la especia que es vol canviar");
                id = Integer.parseInt(br.readLine());
                System.out.println("Introdueix el nom nou de la especia");
                resposta = br.readLine();
                PreparedStatement ps = conn.prepareStatement(
                        "UPDATE spices SET name = ? WHERE Id = ?");
                ps.setString(1,resposta);
                ps.setInt(2, id);
                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No s'ha pogut entregar correctament");
        }
    }

    //Deletes

    /**
     * Borra totes les taules de la base de dades
     */
    public void deleteAllTables() {
        try {
            //Borrar todas las tablas
            String dropTablesSql = "DO $$ DECLARE " +
                    "    r RECORD;" +
                    "BEGIN " +
                    "    FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = current_schema()) LOOP" +
                    "        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(r.tablename) || ' CASCADE';" +
                    "    END LOOP;" +
                    "END $$;";
            Statement delete = conn.createStatement();
            delete.executeUpdate(dropTablesSql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error en eliminar totes les taules");
        }
    }

    /**
     * Borra un registre en aquest una especia per ID
     */
    public void deleteSpice(){
        int id = 0;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String delete = "DELETE FROM spices WHERE Id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(delete);
            System.out.println("Introdueix el num Id de la especia que es vol eliminar");
            id = Integer.parseInt(br.readLine());
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Eliminar especias per països
     */
    public void deleteSpicebyCountry(){
        String country = null;
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String delete = "DELETE FROM spices WHERE Country_Of_Origin = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(delete);
            System.out.println("Introdueix el país de la especia que es vol eliminar");
            country = br.readLine();
            pstmt.setString(1, country);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
