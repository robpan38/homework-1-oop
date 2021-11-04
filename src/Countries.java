public class Countries {
    private final int ID;
    private final String countryCode;

    public Countries(int ID, String countryCode) {
        this.ID = ID;
        this.countryCode = countryCode;
    }

    public int getID() {
        return ID;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
