import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**Aquí es guarda els Id de les especias
 * i es crida de les diferents funcions de la
 * clase Spice*/
public class SpiceMain {
    /**Per guardar la informació llegida del csv de l'exercici anterior*/
    static List<List<String>> spices = new ArrayList<List<String>>();

    public static List<List<String>> getSpices() {
        return spices;
    }
    private static int id = 0;

    public static int getId() {
        return id;
    }

    public static void setId(int value) {
        id = value;
    }
    /**
     * Entrada main on s'arranca la l'aplicació.
     *
     * @param args posada en marxa args
     */
    public static void main(String[] args) throws IOException, SQLException, ParseException {

        /**Referencia al controlador per cridar les fucions de les especias*/
        SpiceMenu menu = new SpiceMenu();

        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();

        SpiceController spiceController = new SpiceController(c);
        ReadSpiceCsv.ReadCsv();


        int option = menu.mainMenu();
        while (option >= 0) {
            switch (option) {
                case 0:
                    sortir(connectionFactory);
                case 1:
                    spiceController.selectAllInfo();
                    break;

                case 2:
                    spiceController.selectByCountry();
                    break;

                case 3:
                    spiceController.selectByFormat();
                    break;

                case 4:
                    spiceController.selectByCuisine();
                    break;

                case 5:
                    spiceController.selectByUser();
                    break;

                case 6:
                    spiceController.insertNew();
                    break;

                case 7:
                    spiceController.selectSpiceName();
                    spiceController.actualitzarNomEspecia();
                    break;

                case 8:
                    spiceController.deleteAllTables();
                    break;

                case 9:
                    spiceController.selectSpiceName();
                    spiceController.deleteSpice();
                    break;

                case 10:
                    spiceController.deleteSpicebyCountry();
                    break;

                case 11:
                    spiceController.createAlltables();
                    break;

                case 12:
                    spiceController.fillAllDataSpices();
                    break;

                default:
                    System.out.println("Introdueixi una de les opcions anteriors");
                    break;
            }
            option = menu.mainMenu();
        }

    }
    /**
     * Métode per sortir de la aplicació*/
    public static void sortir(ConnectionFactory c) throws SQLException {
        System.out.println("ADÉU!");
        c.disconnect();
        System.exit(0);
    }

}
