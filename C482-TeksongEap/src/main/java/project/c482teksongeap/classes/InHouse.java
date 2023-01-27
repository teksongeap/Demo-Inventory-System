package project.c482teksongeap.classes;

/**
 * Class for an in-house part
 * @author Teksong Eap
 */
public class InHouse extends Part{
    /**
     * Machine ID for InHouse part
     */
    private int machineID;

    /**
     * Constructor for InHouse part
     *
     * @param id part ID
     * @param name part name
     * @param price part price
     * @param stock part stock
     * @param min part min
     * @param max part max
     * @param machineID machine ID for part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**
     * @return machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     * @param machineID new machineID to set
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
