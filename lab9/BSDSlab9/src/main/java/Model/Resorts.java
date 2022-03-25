package Model;

public class Resorts {


    public Resorts(Integer resortID, String year) {
        this.resortID = resortID;
        //this.seasonID = seasonID;
        this.year = year;
    }

    private Integer resortID;
    //private String seasonID;
    private String year;



    public Resorts(){

    }


    public Integer getResortID() {
        return resortID;
    }

    public void setResortID(Integer resortID) {
        this.resortID = resortID;
    }

//    public String getSeasonID() {
//        return seasonID;
//    }
//
//    public void setSeasonID(String seasonID) {
//        this.seasonID = seasonID;
//    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Model.LiftRide {\n");

        sb.append("    liftID: ").append(toIndentedString(resortID)).append("\n");
        //sb.append("    seasonID: ").append(toIndentedString(seasonID)).append("\n");
        sb.append("    year: ").append(toIndentedString(year)).append("\n");
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


}
