package vo;

/**
 * @author Ji Rui
 */
public class Info {

    private String id;
    private int hasFever;
    private int isHealthy;
    private int hasContactWithPatients;
    private int hasContactWithForeigners;
    private int isDanger;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getHasFever() {
        return hasFever;
    }

    public void setHasFever(int hasFever) {
        this.hasFever = hasFever;
    }

    public int getIsHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(int isHealthy) {
        this.isHealthy = isHealthy;
    }

    public int getHasContactWithPatients() {
        return hasContactWithPatients;
    }

    public void setHasContactWithPatients(int hasContactWithPatients) {
        this.hasContactWithPatients = hasContactWithPatients;
    }

    public int getHasContactWithForeigners() {
        return hasContactWithForeigners;
    }

    public void setHasContactWithForeigners(int hasContactWithForeigners) {
        this.hasContactWithForeigners = hasContactWithForeigners;
    }

    public int getIsDanger() {
        return isDanger;
    }

    public void setIsDanger(int danger) {
        this.isDanger = danger;
    }
}