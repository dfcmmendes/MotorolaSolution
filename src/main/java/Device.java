import java.util.List;

public class Device {
    private Integer radioId;
    private String alias;
    private String location;
    private List<String> allowedLocations;

    public Device(Integer radioId, String alias, List<String> allowedLocations) {
        this.radioId = radioId;
        this.alias = alias;
        this.allowedLocations = allowedLocations;
    }

    public Integer getRadioId() {
        return radioId;
    }

    public String getAlias() {
        return alias;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getAllowedLocations() {
        return allowedLocations;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean verifyLocation(String newLocation) {
        if(allowedLocations.contains(newLocation))
            return true;
        else
            return false;
    }
}
