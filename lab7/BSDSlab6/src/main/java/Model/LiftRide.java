package Model;

import com.google.gson.annotations.SerializedName;

public class LiftRide {

    private Integer skierID;
    private Integer resortID;
    private String seasonID;
    private String dayID;
    private Integer waitTime = null;
    private Integer liftID = null;
    //private Integer time = null;

    public LiftRide(Integer skierID, Integer resortID, String seasonID, String dayID, Integer waitTime, Integer liftID) {
        this.skierID = skierID;
        this.resortID = resortID;
        this.seasonID = seasonID;
        this.dayID = dayID;
        this.waitTime = waitTime;
        this.liftID = liftID;
    }

    public LiftRide(){

    }

//    public LiftRide(){
//        resortID = 30;
//        seasonID = "034566";
//        dayID = "Tuesday";
//        skierID = 34507;
//
//    }

    public Integer getResortID() {
        return resortID;
    }

    public void setResortID(Integer resortID) {
        this.resortID = resortID;
    }

    public String getSeasonID() {
        return seasonID;
    }

    public void setSeasonID(String seasonID) {
        this.seasonID = seasonID;
    }

    public String getDayID() {
        return dayID;
    }

    public void setDayID(String dayID) {
        this.dayID = dayID;
    }

    public Integer getSkierID() {
        return skierID;
    }

    public void setSkierID(Integer skierID) {
        this.skierID = skierID;
    }

//    public Integer getTime() {
//        return time;
//    }
//
//    public void setTime(Integer time) {
//        this.time = time;
//    }

    public Integer getLiftID() {
        return liftID;
    }

    public void setLiftID(Integer liftID) {
        this.liftID = liftID;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Model.LiftRide {\n");


        sb.append("    time: ").append(toIndentedString(skierID)).append("\n");
        sb.append("    liftID: ").append(toIndentedString(resortID)).append("\n");
        sb.append("    waitTime: ").append(toIndentedString(seasonID)).append("\n");
        sb.append("    waitTime: ").append(toIndentedString(dayID)).append("\n");
        //sb.append("    time: ").append(toIndentedString(time)).append("\n");
        sb.append("    liftID: ").append(toIndentedString(liftID)).append("\n");
        sb.append("    waitTime: ").append(toIndentedString(waitTime)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }





//    public String tostring() {
//        return this.resortID + this.seasonID + this.dayID  + this.skierID;
//    }
}
