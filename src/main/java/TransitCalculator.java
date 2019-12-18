/*
This program takes information about riding on a transit system. It takes the 
number of days of travel, the total number of rides and whether they receive
a discount.
Based on this information it works out the best package to purchase to get the
best value for money.
*/
import java.util.ArrayList;
import java.lang.Math;


public class TransitCalculator{
  double numDays;
  int rides;
  boolean discount;
  public TransitCalculator(double numberDays, int totRides, boolean disc){
    numDays = numberDays;
    rides = totRides;
    discount = disc;
    //class constructor
  }
  
  public double unlimited30Price(ArrayList<Double> fares){
    //works out the unlimited 30 day price per journey
    double a = Math.ceil(numDays/30);
    double finPri = (a*fares.get(2))/rides;
    double roundoff = Math.round(finPri *100.0)/ 100.0;
    return roundoff; 
  }
  
  public double unlimited7Price(ArrayList<Double> fares){
   //works out the unlimited7 day price per journey
    double a = Math.ceil(numDays/7);
    double finPri = (a*fares.get(1))/rides;
    double roundoff = Math.round(finPri *100.0)/ 100.0;
    return roundoff; 
  }
    
  public double[] getRidePrices(ArrayList<Double> fares ){
   //gets the prices for each of the packages based on the fares passed in and price per ride.
    double[] pricePerRide = new double[3];
    pricePerRide[0]=fares.get(0);//pay as you go option requires no calculation
    pricePerRide[1]=unlimited7Price(fares);
    pricePerRide[2]=unlimited30Price(fares);
    return pricePerRide;//returns the price per ride for each package
  }
  
  public String getBestFare(ArrayList<Double> fares){
    double [] pricePerRide = getRidePrices(fares);
    double lowest = pricePerRide[0];//sets the lowest to the first item to start
    int j=0;
    String bestMethod=" ";
   
    for(int i=1; i<pricePerRide.length;i++){
      if(lowest>pricePerRide[i]){
        lowest = pricePerRide[i];
        j=i;   
      }//works through the price per ride to find the lowest price and package
    }
    if (j==0){
    	bestMethod= "You should get the Pay-Per-Ride option £"+lowest+" per ride.";
    } else if(j==1){
      bestMethod= "You should get the 7 day Unlimited option £"+lowest+" per ride.";
    } else if (j==2){
      bestMethod= "You should get the 30 day Unlimited option £"+lowest+" per ride.";
    }
    //bestmethod is set to the statement which has the lowest option
    return bestMethod;//returns the statement
  }
  
  public ArrayList<Double> getFares(boolean discount){
    if(discount==true){
      ArrayList<Double> fares = new ArrayList<Double>();
      fares.add(1.35);
      fares.add(16.50);
      fares.add(63.50);
      //1.35,16.50,63.50 are the fares if discount is true
      return fares;//returns the fares
      //System.out.println(Arrays.toString(fares));
    } else{
      ArrayList<Double> fares = new ArrayList<Double>();
      fares.add(2.75);
      fares.add(33.00);
      fares.add(127.00);
      //These are the fares are the fares if discount is false
      return fares;//returns the fares
    }

  }
  
  public static void main(String[] args){
    TransitCalculator custA = new TransitCalculator(5,12,true); 
    //creates a new instance of the TransitCalculator
    ArrayList<Double> fares = custA.getFares(custA.discount);
    //gets the fares from the getFares function passing the discount value
    System.out.println(custA.getBestFare(fares));
    //passes the fares to get the best fare and prints the statement
    
    
    //creates second instance for Customer B
    TransitCalculator custB = new TransitCalculator(26,54, false);
    fares = custB.getFares(custB.discount);
    System.out.println(custB.getBestFare(fares));
    
    //creates third instance for Customer C
    TransitCalculator custC = new TransitCalculator(26,54, true);
    fares = custC.getFares(custC.discount);
    System.out.println(custC.getBestFare(fares));
  
  }
}