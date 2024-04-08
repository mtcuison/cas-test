
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.rmj.appdriver.MiscUtil;

public class testResultSet2XML {
    public static void main (String [] args){
        final String DIRECTORY = "D:/GGC_Java_Systems/config/metadata/";
        
        // Connection parameters
        String url = "jdbc:mysql://172.16.0.14:3306/CASys_DBF";
        String username = "";
        String password = "";
        
        String lsSQL = "SELECT" +
                            "  a.sClientID" +
                            ", a.cClientTp" +
                            ", a.sLastName" +
                            ", a.sFrstName" +
                            ", a.sMiddName" +
                            ", a.sSuffixNm" +
                            ", a.sMaidenNm" +
                            ", a.sCompnyNm" +
                            ", a.cGenderCd" +
                            ", a.cCvilStat" +
                            ", a.sCitizenx" +
                            ", a.dBirthDte" +
                            ", a.sBirthPlc" +
                            ", a.sAddlInfo" +
                            ", a.sSpouseID" +
                            ", a.sTaxIDNox" +
                            ", a.sLTOIDxxx" +
                            ", a.sPHBNIDxx" +
                            ", a.cLRClient" +
                            ", a.cMCClient" +
                            ", a.cSCClient" +
                            ", a.cSPClient" +
                            ", a.cCPClient" +
                            ", a.cRecdStat" +
                            ", a.sModified" +
                            ", a.dModified" +
                            ", b.sTownName xBirthPlc" +
                            ", c.sCntryNme xCitizenx" +
                            ", d.sCompnyNm xSpouseNm" +
                        " FROM Client_Master a" +
                            " LEFT JOIN TownCity b ON a.sBirthPlc = b.sTownIDxx" +
                            " LEFT JOIN Country c ON a.sCitizenx = c.sCntryCde" +
                            " LEFT JOIN Client_Master d ON a.sSpouseID = d.sClientID" +
                        " WHERE 0=1";
        
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to server.");

            Statement loSt = connection.createStatement();
            ResultSet loRS = loSt.executeQuery(lsSQL);
            String lsFileName = "Model_Client_Master.xml";
            
            System.setProperty("sys.default.path.metadata", DIRECTORY);
            
            //export column metadata to xml
            if (MiscUtil.resultSet2XML(loRS, DIRECTORY + lsFileName)){
                System.out.println("ResultSet exported.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
