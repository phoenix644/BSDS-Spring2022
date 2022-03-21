package Model;

public class Skier {

    private Integer resortID;
    private String seasonID;
    private String dayID;
    private Integer skierID;

    public Skier (){
        resortID = 30;
        seasonID = "034566";
        dayID = "Tuesday";
        skierID = 34507;

    }

    public String tostring() {
        return this.resortID + this.seasonID + this.dayID  + this.skierID;
    }
}
