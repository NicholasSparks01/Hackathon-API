import java.io.IOException;
import java.sql.SQLException;

public class SolutionRunner{
	public static void main (String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		Solution test = new Solution();
		test.inData();
		test.apiTest();
		test.sendToDataBase();
	}
}