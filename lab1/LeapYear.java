/*input a year to judge if the year is a leap year
 *A leap year is either:
 *1.divisible by 400 or
 *2.divisible by 4 and not by 100*/
 
 public class LeapYear{
	 public static void isLeapYear(int year){
		 if (year % 400 == 0 | (year % 4 == 0 && year % 100 != 0)){
			 System.out.println(year + "is a leap year!");
		 }
		 else{
			 System.out.println(year + "is not a leap year.");
		 }
	 }
 }