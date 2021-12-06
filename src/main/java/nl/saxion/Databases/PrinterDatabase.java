package nl.saxion.Databases;

import nl.saxion.Models.HousedPrinter;
import nl.saxion.Models.MultiColor;
import nl.saxion.Models.Print;
import nl.saxion.Models.Printer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PrinterDatabase {
    private ArrayList<Printer> printers;


    private ArrayList readPrintersFromFile() {
        ArrayList<Printer> printers = new ArrayList<>();

        //read printers from JSON
        JSONParser jsonParser = new JSONParser();
        URL printersResource = getClass().getResource("/printers.json");
        if (printersResource == null) {
            System.err.println("Warning: Could not find printers.json file");
            return null;
        }
        try (FileReader reader = new FileReader(printersResource.getFile())) {
            JSONArray printersJSON = (JSONArray) jsonParser.parse(reader);
            for (Object p : printersJSON) {
                JSONObject printer = (JSONObject) p;
                int id = ((Long) printer.get("id")).intValue();
                int type = ((Long) printer.get("type")).intValue();
                String name = (String) printer.get("name");
                String manufacturer = (String) printer.get("manufacturer");
                int maxX = ((Long) printer.get("maxX")).intValue();
                int maxY = ((Long) printer.get("maxY")).intValue();
                int maxZ = ((Long) printer.get("maxZ")).intValue();
                int maxColors = ((Long) printer.get("maxColors")).intValue();
                //current Spool
                JSONArray currentSpools = (JSONArray) printer.get("currentSpools");
                //determine printer type
                if (type == 1) {
                    printers.add(new Printer(id, name, manufacturer, maxX, maxY, maxZ));
                } else if (type == 2) {
                    printers.add(new HousedPrinter(id, name, manufacturer, maxX, maxY, maxZ));
                } else if (type == 3) {
                    printers.add(new MultiColor(id, name, manufacturer, maxX, maxY, maxZ, maxColors));
                }

            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }


        return printers;
    }

    public Printer findPrinter() {
        Print printer;

        return printer;
}
