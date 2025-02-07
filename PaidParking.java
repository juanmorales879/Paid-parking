
// Program should calculated total amount for parking based on time and type of customer

import java.util.Scanner;

public class PaidParking {

	public static void main(String[] args) {
		
Scanner kbd = new Scanner(System.in);

	double totalamount = 0.00;
	double entryFee = 2.00;
	double RVhalfhourFee = 3.00; // regular (before 11am) and validated fee for every half hour
    double regularhalfFee = 5.00; //fee for every half after 11am
    double validatedFee = 5.00; // initial validated fee
    double earlybirdFee = 15.00; //early bird fee
    
	int halfHour = 30;
	int minInhour = 60; // minutes in an hour
    int rateChange = 11 * 60; // time where regular rate changes
    
    
    
  System.out.println("Please enter type of rate: ");

		char rate = kbd.nextLine().charAt(0);

  System.out.println("Enter the time in hour minute format :");

		String HourMin = kbd.nextLine();

//Find entry hour

	int firstColon = HourMin.indexOf(":");

		String entryHour = HourMin.substring(0, firstColon);

//Find entry minutes

	int firstSpace = HourMin.indexOf(" ");

		String entryMinutes = HourMin.substring(firstColon+1, firstSpace);

//Find end hour

	int secondColon = HourMin.lastIndexOf(":");

		String endHour = HourMin.substring(firstSpace+1, secondColon);

//Find end minutes 

		String endMinutes = HourMin.substring(secondColon+1);

// Convert to integers

	int entryHourInt = Integer.parseInt(entryHour);
	int entryMinInt = Integer.parseInt(entryMinutes);
	int endHourInt = Integer.parseInt(endHour);
	int endMinutesInt = Integer.parseInt(endMinutes);

// find total hours 

	int totalHour = endHourInt - entryHourInt; 

	int totalHourinMin = totalHour * minInhour;

// add minutes 

	int totalMinutes = endMinutesInt - entryMinInt;

// Final time in parking lot

	int finalTime = totalHourinMin + totalMinutes;
	
//transform entry 6 a.m to minutes

	int minEntry = entryHourInt * minInhour; //used to transform entry inputed time to minutes

	int limitEntrytime1 = 6 * minInhour; // 6 in minutes

//transform 8:29am to minutes

	int maxEntry = entryHourInt * minInhour + entryMinInt; //used to transform entry inputed time to minutes

	int limitEntrytime2 = 8 * minInhour + 29; // 8:29 in minutes

//transform 3pm to minutes

	int minEnd = endHourInt * minInhour; //used to transform ending inputed time to minutes
	int limitEndtime1 = 15 * minInhour; //3 in minutes
	
// transform 5:59pm to minutes
	
	int maxEnd = endHourInt * minInhour + endMinutesInt; //used to transform ending inputed time to minutes
	int limitEndtime2 = 17 * minInhour + 59; // 5:59 in minutes

switch (rate) {

case 'R' : 
 
	if ((minEntry >= limitEntrytime1 && maxEntry <= limitEntrytime2) && (minEnd >= limitEndtime1 && maxEnd <= limitEndtime2) ) {
		totalamount = earlybirdFee; 
				System.out.printf("Charging the EARLY BIRD rate. Please pay $%.2f " , totalamount);	
	}
		else if (endHourInt * minInhour + endMinutesInt <= rateChange) {  
				totalamount = entryFee + (finalTime/halfHour) * RVhalfhourFee;
					System.out.printf("Charging REGULAR rates before 11:00am. Please pay $%.2f " , totalamount);
	} 		else {
					totalamount = entryFee + (finalTime/halfHour) * regularhalfFee;
						System.out.printf("Charging REGULAR rates after 11:00am. Please pay $%.2f " , totalamount);
	}
	
	break;

case 'V' :
	
	if (finalTime > 180){
		
		int afterThreehours = finalTime - 180;	
			totalamount = validatedFee + (afterThreehours/halfHour) * RVhalfhourFee;
				System.out.printf("Charging VALIDATED rates. Please pay  $%.2f " , totalamount);
	}
		else {
			totalamount = validatedFee;
				System.out.printf("Charging VALIDATED rate. Please pay $%.2f ", totalamount);
	}
	break;

default : 
	System.out.println( "You entered an incorrect code!" );
	break;
	
}
	kbd.close();

	}
	
}
