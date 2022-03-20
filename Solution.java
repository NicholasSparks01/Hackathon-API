import java.net.http.*;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpResponse.*;
import java.util.Scanner;
import java.sql.*;

public class Solution {
	// variables could be api request parameters
	// assign variables can be done through a constructor or method
	String customerName, address, request, timeFrame, customerDescription;
	String name, policyNumber, description, itemStatus, driversLiscence, carType, carMake, carModel, carPlate, ticketNumber;
	String driverFile = "replace this string with the database driver";
	String login = "replace with username";
	String pwd = "replace with password";
	public Solution(){
		
	}
	
	public void inData(){
	//geting in data from the front end
	Scanner fileReader = new Scanner("javascript_input.txt");
	name = fileReader.nextLine();
	policyNumber = fileReader.nextLine();
	description = fileReader.nextLine();
	itemStatus = fileReader.nextLine();
	driversLiscence = fileReader.nextLine();
	carType = fileReader.nextLine();
	carMake = fileReader.nextLine();
	carModel = fileReader.nextLine();
	carPlate = fileReader.nextLine();
	ticketNumber = fileReader.nextLine();
	fileReader.close();
	}
	
	public void apiTest() throws IOException, InterruptedException {
		String test, test2;
		var client = HttpClient.newHttpClient();
		//requests for both apis
		HttpRequest request;
		HttpRequest lossRequest;
			try {
				request = HttpRequest.newBuilder().uri(new URI("https://api-stage.nationwide.com/hackathon/customer-acquisition/homeowners-sales-quoting/v2​/quotes​/i don't think we thought about this​/policy-holders​/" + policyNumber + "?X-NW-Message-ID=test?client_id=Uwh05yJcOcCJBbrHblXfQZ68hoM5Kuph?session-id=1?quote-id=i_dont_think _we_thought_of_this")).build();
				lossRequest = HttpRequest.newBuilder().uri(new URI("https://api-stage.nationwide.com/hackathon/customer-acquisition/homeowners-sales-quoting/v2​/quotes​/quoteIDHere/losses/lossIDHere?X-NW-Message-ID=test?client_id=Uwh05yJcOcCJBbrHblXfQZ68hoM5Kuph?session-id=1?quote-id=i_dont_think _we_thought_of_this\")).build();?X-NW-Message-ID=test?client_id=Uwh05yJcOcCJBbrHblXfQZ68hoM5Kuph?session-id=1?quote-id=i_dont_think _we_thought_of_this")).build();
				HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
				HttpResponse<String> lossResponse = client.send(request, BodyHandlers.ofString());
				HttpHeaders headers = response.headers();
		        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
		        // print status code
		        System.out.println(response.statusCode());

		        // print response body
		        System.out.println(response.body());
		        
		        test = response.body();
		        
		        HttpHeaders lossHeaders = lossResponse.headers();
		        lossHeaders.map().forEach((k, v) -> System.out.println(k + ":" + v));
		        // print status code
		        System.out.println(lossResponse.statusCode());

		        // print response body
		        System.out.println(lossResponse.body());
		        
		        test2 = lossResponse.body();
		        
		        System.out.println(test);
		        System.out.println(test2);
		        
		        String temp;
		        Scanner stringScan = new Scanner(test);
		        Scanner stringScan2 = new Scanner(test2);
				while((stringScan.hasNext())&&(stringScan.hasNext())) {
					if(stringScan.hasNext("\"firstName\"")) {
						customerName = stringScan.next();
					}
					if(stringScan.hasNext("\"addressLine1\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"addressLine2\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"city\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"state\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"postalCode\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"postalCode\"")){
						temp = stringScan.next();
						address = address +  " " + temp;
					}
					if(stringScan.hasNext("\"cause\"")) {
						temp = stringScan2.next();
						itemStatus = temp;
					}
				}
				stringScan.close();
				stringScan2.close();

			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//auto claim
			if((carType != null)&&(carMake != null)&&(carModel != null)&&(carPlate != null)&&(ticketNumber != null)) {
				try {
					//request = HttpRequest.newBuilder().uri(new URI("https://api-stage.nationwide.com/hackathon/reporting/claimsexperience/v1/claims?X-NW-Message-ID=test?client_id=Uwh05yJcOcCJBbrHblXfQZ68hoM5Kuph?claimNumber=1")).build();
					request = HttpRequest.newBuilder().uri(new URI("https://api-stage.nationwide.com/hackathon/customer-acquisition/auto-sales-quoting/v2//quotes​/could_this_be_policyNumber​/vehicles​/could_this_be_carPlate?X-NW-Message-ID=test?client_id=Uwh05yJcOcCJBbrHblXfQZ68hoM5Kuph")).build();
					HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
					//.thenApply(HttpResponse::body).thenAccept(System.out::println).join();
					HttpHeaders headers = response.headers();
			        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
			        
			        // print status code
			        System.out.println(response.statusCode());
			        
			        // print response body
			        System.out.println(response.body());
			        
			        test = response.body();
			        
			        System.out.println(test);
			        
			        Scanner stringScan = new Scanner(test);
					while((stringScan.hasNext())&&(stringScan.hasNext())) {
						if(stringScan.hasNext("\"vehicleType\"")) {
							
							carType = stringScan.next();
						}
						if(stringScan.hasNext("\"make\"")){
							carMake = stringScan.next();
						}
						if(stringScan.hasNext("\"model\"")){
							carModel = stringScan.next();
						}
					}
					stringScan.close();

				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	//this method sends the data to the sql data base
public void sendToDataBase() throws ClassNotFoundException, SQLException{
		String q1;
		Class.forName(driverFile);
		Connection con = DriverManager.getConnection("replace this string with the link to the database", login, pwd);
		Statement stmt = con.createStatement();
		if((carType != null)&&(carMake != null)&&(carModel != null)&&(carPlate != null)&&(ticketNumber != null)) {
			q1 = "insert into userid values('" +name+ "', '" + policyNumber+ "', '"+ description + "', '" + itemStatus + "', '"  +pwd+ "', '" + driversLiscence + "', '" + carType + "', '" + carMake + "', '" + carModel + "', '" + carPlate + "', '" + ticketNumber +"')";
		}
		else {
			q1 = "insert into userid values('" +name+ "', '" + policyNumber+ "', '"+ description + "', '" + itemStatus + "', '"  +pwd+"')";
		}
	      int x = stmt.executeUpdate(q1);
	      if (x > 0)           
              System.out.println("Successfully Inserted");           
          else           
              System.out.println("Insert Failed");
           
          con.close();
	}
		
		
	}