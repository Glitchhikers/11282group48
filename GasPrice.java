public class GasPrice {
    static final double ecoNormal = 1454.0; //Number to normalize average Co2 GPM at Average GPM = 10% drop in profit

    private Car car;
    private int milesHigh;
    private int milesCity;
    private double destPrice;
    private double gasRatio; //Percent rider pays of the gas price
    private double driverProfit;


    public GasPrice(Car car, int milesHigh, int milesCity, double destPrice, double gasRatio, double driverProfit){
        this.car = car;
        this.milesHigh = milesHigh;
        this.milesCity = milesCity;
        this.destPrice = destPrice;
        this.gasRatio = gasRatio;
        this.driverProfit = driverProfit; //In Dollars per Mile (8 Dollars per 60 miles is 0.1333 $/mi
    }

    public double riderPrice(){
        double highway = (milesHigh/car.highwayMPG()) * destPrice;
        double city = (milesCity/car.cityMPG()) * destPrice;
        double total = highway + city;
        total =  driverProfit() + (total * gasRatio);
        return total;
    }

    public double changeRatio(double newPercent){
        gasRatio = newPercent;
        return riderPrice();
    }

    public double changeMiles(int milesCity, int milesHigh){
        this.milesCity = milesCity;
        this.milesHigh = milesHigh;
        return riderPrice();
    }

    public double driverProfit(){
        double profit = (milesCity + milesHigh) * driverProfit;
        double ecoVal = Math.pow(car.co2GPM(),2.0)/Math.pow(ecoNormal,2.0);
        profit = profit * (1-ecoVal);
        return profit;
    }

}
