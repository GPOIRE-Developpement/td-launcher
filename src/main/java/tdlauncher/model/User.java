package tdlauncher.model;

public class User {
    private String name;
    private long steamId;
    private String profileImage;
    private String country;

    // No-arg constructor for simple deserialization / creation when only steamId is
    // known
    public User() {
    }

    public User(long steamid, String name, String profileImage, String country) {
        this.steamId = steamid;
        this.name = name;
        this.profileImage = profileImage;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSteamId() {
        return steamId;
    }

    public void setSteamId(long steamId) {
        this.steamId = steamId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
