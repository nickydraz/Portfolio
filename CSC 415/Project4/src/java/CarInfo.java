/**
 *
 * @author nicholasdrazenovic
 */
public class CarInfo {
    public final String shortDescription;
    public final String carName;  // serves as prefix for the file name
    public CarInfo(String carName, String shortDescription)
    {
        this.shortDescription = shortDescription;
        this.carName = carName;
    }
    
    @Override
    public String toString()
    {
        String str =
        String.format("[car name : %s;  short description: %s]",carName, shortDescription);
        return str; 
    }

}
