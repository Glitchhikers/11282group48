import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;


public class TEST {
    @Test
    public void evalCar(){
        Vector<String[]> test = Car.searchCar("Nissan",2020,"Altima");
        assertEquals(2020, Integer.parseInt(test.elementAt(0)[63]));
        assertEquals("Nissan", test.elementAt(0)[46]);
        assertEquals("Altima", test.elementAt(0)[47]);
    }
    @Test
    public void evalGPM(){
        Vector<String[]> test = Car.searchCar("Nissan",2020,"Altima");
        assertEquals(278.0, Double.parseDouble(test.elementAt(0)[14]),0.1);
    }
    @Test
    public void evalDisp(){
        Vector<String[]> test = Car.searchCar("Nissan",2020,"Altima");
        assertEquals(2.5, Double.parseDouble(test.elementAt(0)[23]),0.1);
    }
    @Test
    public void evalMPG(){
        Vector<String[]> test = Car.searchCar("Pontiac",1986,"Fiero");
        assertEquals(22, Integer.parseInt(test.elementAt(0)[4]));
        assertEquals(29,Integer.parseInt(test.elementAt(0)[34]));
    }
    @Test
    public void evalNoCar(){
        Vector<String[]> test = Car.searchCar("Pontiac",2020,"Fiero");
        assertEquals(0,test.size());

    }
    @Test
    public void evalTrims(){
        Vector<String[]> test = Car.searchCar("Nissan",2020,"Altima");
        assertEquals(5,test.size());
        for (String[] trim: test){
            assertEquals("Nissan",trim[46]);
            assertTrue(trim[47].contains("Altima"));
        }
    }
    @Test
    public void evalDriverPft(){
        double gasPrice = 3.10;
        int milesCity = 60;
        int milesHighway = 60;
        double gasRatio = 0.6;
        final double DRIVERPROFIT = 4.0/60.0;
        int numRiders = 1;
        Vector<String[]> test = Car.searchCar("Kia",2020,"K900");
        Car testCar = new Car(test.elementAt(0));
        GasPrice trip = new GasPrice(testCar,milesHighway,milesCity,gasPrice,gasRatio,DRIVERPROFIT,numRiders);
        assertEquals(7.29,trip.driverProfit(),0.1);
    }
    @Test
    public void evalRiderPrice(){
        double gasPrice = 3.10;
        int milesCity = 60;
        int milesHighway = 60;
        double gasRatio = 0.6;
        final double DRIVERPROFIT = 4.0/60.0;
        int numRiders = 1;
        Vector<String[]> test = Car.searchCar("Kia",2020,"K900");
        Car testCar = new Car(test.elementAt(0));
        GasPrice trip = new GasPrice(testCar,milesHighway,milesCity,gasPrice,gasRatio,DRIVERPROFIT,numRiders);
        assertEquals(17.96,trip.riderPrice(),0.1);
    }
    @Test
    public void evalMultipleRiders(){
        double gasPrice = 3.10;
        int milesCity = 60;
        int milesHighway = 60;
        double gasRatio = 0.6;
        final double DRIVERPROFIT = 4.0/60.0;
        int numRiders = 2;
        Vector<String[]> test = Car.searchCar("Kia",2020,"K900");
        Car testCar = new Car(test.elementAt(0));
        GasPrice trip = new GasPrice(testCar,milesHighway,milesCity,gasPrice,gasRatio,DRIVERPROFIT,numRiders);
        assertEquals(17.96/2.0,trip.riderPrice(),0.1);
    }

}
