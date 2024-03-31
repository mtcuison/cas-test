
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.guanzon.clients.Model_Client_Address;
import org.guanzon.clients.Model_Client_Mobile;
import org.guanzon.clients.Model_Client_Master;
import org.guanzon.validators.ValidatorFactory;
import org.guanzon.validators.ValidatorInterface;
import org.rmj.appdriver.MiscUtil;
import org.rmj.appdriver.SQLUtil;

public class testModel {
    public static void main (String [] args){
        // Connection parameters
        String url = "jdbc:mysql://172.16.0.14:3306/CASys_DBF";
        String username = "sa";
        String password = "Atsp,imrtptd";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to server.");
            
            //initialize validator object
            ValidatorInterface validator;
            
            //initialize entity
            Model_Client_Master master = new Model_Client_Master(connection);
            System.out.println("Connected to master model class.");
            
            master.setClientID("M00124000001");
            master.setClientType("0");
            master.setLastName("Cuison");
            master.setFirstName("Michael");
            master.setMiddleName("Medina");
            master.setSuffixName("Jr.");
            master.setFullName("Cuison, Michael Jr. Medina");
            master.setSex("0");
            master.setCivilStatus("0");
            master.setCitizenshipID("01");
            master.setCitizenshipName("Philippines");
            master.setBirthDate(SQLUtil.toDate("2025-01-01", SQLUtil.FORMAT_SHORT_DATE));
            master.setBirthPlaceID("0314");
            master.setBirthPlaceName("Dagupan City, Pangasinan");
            master.setAdditionalInfo("Anak ni mac gwapo.");
            master.setSpouseID("");
            master.setSpouseName("");
            master.setTaxIDNumber("");
            master.setSparepartsSalesClient(true);
            master.setModifiedBy("M001111122");
            master.setModifiedDate(SQLUtil.toDate("2024-03-29 19:15:00", SQLUtil.FORMAT_TIMESTAMP));
            
            validator = ValidatorFactory.make(ValidatorFactory.TYPE.Client_Master, master);
            if (!validator.isEntryOkay()){
                System.err.println(validator.getMessage());
                System.exit(1);
            }
            
            System.out.println(MiscUtil.makeSQL(master, "xBirthPlc;xCitizenx;xSpouseNm"));
            
            Model_Client_Mobile mobile = new Model_Client_Mobile(connection);
            System.out.println("Connected to mobile model class.");
            
            mobile.setMobileID("M00124000001");
            mobile.setClientID("M00124000001");
            mobile.setContactNo("09260375777");
            mobile.setContactType("0");
            mobile.setOwnership("0");
            mobile.setPrimary(true);
            mobile.setPriority(1);
            mobile.setMarketingRecipient(true);
            mobile.setModifiedDate(SQLUtil.toDate("2024-03-29 19:15:00", SQLUtil.FORMAT_TIMESTAMP));
            
            validator = ValidatorFactory.make(ValidatorFactory.TYPE.Client_Mobile, mobile);
            if (!validator.isEntryOkay()){
                System.err.println(validator.getMessage());
                System.exit(1);
            }
            
            
            System.out.println(MiscUtil.makeSQL(mobile));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
