import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Car {
    private String make;
    private int year;
    private String model;
    private int mpgH;
    private int mpgC;
    private double gpm;
    private String engineDisp;

    //Static method to locate the list of most similar cars
    static public Vector<String[]> searchCar(String make, int year, String model){
        Vector<String[]> listCar = new Vector<>();

        //Scan for available "models"
        //CSV Provided by Dept. of Energy at fueleconomy.gov
        File file = new File("vehicles.csv");
        try {
            Scanner scan = new Scanner(file);
            //Go past headers
            scan.nextLine();
            while (scan.hasNextLine()){
                String[] carStats;
                carStats = scan.nextLine().split(",");
                if (carStats[63].equals(String.valueOf(year)) && carStats[46].equals(make) && carStats[47].contains(model)){
                    listCar.add(carStats);
                }
            }
        }
        catch (Exception FileNotFoundException){
            System.out.println("ERROR: File not found!");
        }

        return listCar;
    }

    public Car (String[] carStats){
        year = Integer.parseInt(carStats[63]);
        make = carStats[46];
        model = carStats[47];
        mpgC = Integer.parseInt(carStats[4]);
        mpgH = Integer.parseInt(carStats[34]);
        gpm =  Double.parseDouble(carStats[14]);
        engineDisp = carStats[23];
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getEngineDisp() {
        return engineDisp;
    }

    public double highwayMPG(){
        return mpgH;
    }
    public double cityMPG(){
        return mpgC;
    }
    public double co2GPM(){
        return gpm;
    }
}
