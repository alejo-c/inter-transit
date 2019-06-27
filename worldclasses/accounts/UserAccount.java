package worldclasses.accounts;

public class UserAccount extends Account {

    /* ATTRIBUTTES __________________________________________________________ */
    private Integer level;
    private Integer points;

    /* CONSTRUCTORS _________________________________________________________ */
    public UserAccount(String username, String nickname, String password, String image, Integer level, Integer points) {
        super(username, nickname, password, image);
        this.level = level;
        this.points = points;
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password, String image) {
        super(username, nickname, password, image);
        this.points = 0;
        this.level = 1;
    }

    /* ______________________________________________________________________ */
    public UserAccount(String username, String nickname, String password) {
        super(username, nickname, password);
        this.points = 0;
        this.level = 1;
    }

    /* GETTERS ______________________________________________________________ */
    public Integer getLevel() {
        return level;
    }

    /* ______________________________________________________________________ */
    public Integer getPoints() {
        return points;
    }

    /* SETTERS ______________________________________________________________ */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /* ______________________________________________________________________ */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /* METHODS ______________________________________________________________ */
    @Override
    public String toString() {
        String superString = super.toString();
        return "User" + superString.substring(0, superString.length() - 1) + ", "
                + this.getLevel() + ", "
                + this.getPoints() + "}";
    }
}
