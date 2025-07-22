
public class Catch {
    protected String  Name ;
    protected Double GetBallRate;
    protected Double catchingRate ;
    // Constructor
    public Catch(String name, Double getBallRate, Double catchingRate) {
        Name = name;
        GetBallRate = getBallRate;
        this.catchingRate = catchingRate;
    }


    //Setter and getter
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public Double getGetBallRate() {
        return GetBallRate;
    }
    public void setGetBallRate(Double getBallRate) {
        GetBallRate = getBallRate;
    }
    public Double getCatchingRate() {
        return catchingRate;
    }
    public void setCatchingRate(Double catchingRate) {
        this.catchingRate = catchingRate;
    }
}


