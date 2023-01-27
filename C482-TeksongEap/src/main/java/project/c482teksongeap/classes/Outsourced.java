package project.c482teksongeap.classes;

/**
 * Class for an outsourced part
 * @author Teksong Eap
 */
public class Outsourced extends Part{

    /**
     * Company name for part
     */
    private String companyName;

    /**
     * Constructor for Outsourced part.
     *
     * @param id part ID
     * @param name part name
     * @param price part price
     * @param stock part stock
     * @param min part min
     * @param max part max
     * @param companyName company name for part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
