public class Profile {

    private String name;
    private String UUID;

    public Profile(String name, String UUID)
    {
        this.UUID = UUID;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return UUID;
    }
}
