import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Classe per llegir els csv de l'exercici anterior
 */
public class ReadSpiceCsv {
    public static void ReadCsv() throws IOException {
        try (CSVReader csvReader = new CSVReader(new FileReader("C:\\Users\\Hao\\Documents\\DAM 6 Acces\\ACB\\resources\\spices.csv"))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                SpiceMain.spices.add(Arrays.asList(values));
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }
}