package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditServices {
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "action_name, timestamp, thread_name";

    //File attributes

    private static String fileName = "audit";
    private static Path path = Paths.get("src", "output", "fisiere", fileName + ".csv");
    private static FileWriter fileWriter = null;

    static {
        boolean exist = false;
        if (new File(String.valueOf(path)).exists())
            exist = true;
        if (!exist) {
            try {
                fileWriter = new FileWriter(String.valueOf(path), true);

                //Write the CSV file header
                fileWriter.append(FILE_HEADER.toString());

                //Add a new line separator after the header
                fileWriter.append(NEW_LINE_SEPARATOR);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.flush();
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error while flushing/closing Audit fileWriter !!!");
                    e.printStackTrace();
                }
            }
        }
    }

    public static void writeCsvFile(String actionName) {
        // Create a date object
        Date date = new Date();

        // Formating date
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss:SSS a dd-MMM-yyyy");

        //Write a new action to the CSV file
        try {
            fileWriter = new FileWriter(String.valueOf(path), true);

            fileWriter.append(actionName);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(ft.format(date));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(Thread.currentThread().getName());
            fileWriter.append(NEW_LINE_SEPARATOR);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing Audit fileWriter !!!");
                e.printStackTrace();
            }
        }
    }
}