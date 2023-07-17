package RailCars;

public abstract class RailroadCar {
    private String type = "Railroad car";
    private String shipper;
    private String securityInf;
    private int id = 0;
    private static int counter = 1;
    private double netWeight;
    private double grossWeight;
    private boolean electGrid = false;


    protected RailroadCar(String shipper, String securityInf, double netWeight, String type){
        this.type = type;
        this.netWeight = netWeight;
        this.shipper = shipper;
        this.securityInf = securityInf;
        this.id = counter;
        counter++;
    }



    public String getShipper() {
        return shipper;
    }

    public boolean isElectGrid() {
        return electGrid;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setShipper(String shipper) {
        this.shipper = shipper;
    }

    public String getSecurityInf() {
        return securityInf;
    }

    public void setSecurityInf(String securityInf) {
        this.securityInf = securityInf;
    }

    public int getId() {
        return id;
    }



    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    @Override
    public String toString(){
        return  "\n\t"+type+"\n\t"+
                "Shipper:" + this.shipper +"\n\t"+
                "Security information: " + this.securityInf +"\n\t"+
                "ID: " + this.id +"\n\t"+
                "Net Weight: " + this.netWeight+"\n\t";
    }

    public String getType() {
        return type;
    }
}
