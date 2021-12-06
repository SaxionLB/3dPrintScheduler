package nl.saxion.Models;

import java.util.ArrayList;

/* Standard cartesian FDM printer */
public class Printer {
    private final int id;
    private final String name;
    private final String manufacturer;
    private final int maxX;
    private final int maxY;
    private final int maxZ;
    private Spool currentSpool;

    public Printer(int id, String name, String manufacturer, int maxX, int maxY, int maxZ) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }

    public void setCurrentSpools(ArrayList<Spool> spools) {
        this.currentSpool = spools.get(0);
    }

    public void setCurrentSpool(Spool spool) {
        this.currentSpool = spool;
    }

    public Spool getCurrentSpool() {
        return currentSpool;
    }

    public Spool[] getCurrentSpools() {
        Spool[] spools = new Spool[1];
        spools[0] = currentSpool;
        return spools;
    }

    @Override
    public boolean printFits(Print print) {
        return print.getHeight() <= maxZ && print.getWidth() <= maxX && print.getLength() <= maxY;
    }

    @Override
    public int CalculatePrintTime(String filename) {
        return 0;
    }

    @Override
    public String toString() {
        String result = super.toString() +
                "maxX: " + maxX + System.lineSeparator() +
                "maxY: " + maxY + System.lineSeparator() +
                "maxZ: " + maxZ + System.lineSeparator();
        if (currentSpool != null) {
            result += "Current spool: " + currentSpool.getId()+ System.lineSeparator();
        }
        return result;
    }
}
