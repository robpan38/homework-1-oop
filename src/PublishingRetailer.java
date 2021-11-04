import java.util.ArrayList;

public class PublishingRetailer {
    private final int ID;
    private final String name;
    private ArrayList<IPublishingArtifact> publishingArtifacts;
    private ArrayList<Countries> countries;

    public PublishingRetailer(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.publishingArtifacts = new ArrayList<IPublishingArtifact>();
        this.countries = new ArrayList<Countries>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<IPublishingArtifact> getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public ArrayList<Countries> getCountries() {
        return countries;
    }

    public void addPublishingArtifact(IPublishingArtifact publishingArtifact) {
        this.publishingArtifacts.add(publishingArtifact);
    }

    public void addCountry(Countries country) {
        this.countries.add(country);
    }
}
