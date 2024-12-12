package liftsystem;

import java.util.Scanner;

public class LiftSystem {

    public static int findStops(int liftNumber , int currentLiftFloor ,int source ,int destination){

        int numberOfStops=0;

        if(liftNumber==3 || liftNumber==4){
                if(source>=6 && source<=10){
                    numberOfStops+=Math.abs(currentLiftFloor-source);
                }
                else{
                    numberOfStops+=currentLiftFloor-6+1;
                }

                currentLiftFloor = source;

                if(destination>=6 && destination<=10){
                    numberOfStops+=Math.abs(currentLiftFloor-destination);
                }
                else{
                    numberOfStops+=currentLiftFloor-6+1;
                }
        }
        else{
            numberOfStops+=Math.abs(currentLiftFloor - source);
            currentLiftFloor=source;
            numberOfStops+=Math.abs(currentLiftFloor - destination);
        }
        return  numberOfStops;
    }

    public static int findLift(int[] lift , int source,int destination){
        int minDistance=11 ,  firstPreference=-1 ,  secondPreference=-1 , minStops=11;

        for(int i=0;i< lift.length;i++){
            if( lift[i]==-1 || ( (i==0 || i==1) &&((source>=6 && source <=10) || (destination>=6 && destination<=10) )) ||
                    ( (i==2 || i==3) && ((source>=1 && source <=5) ||  (destination>=1 && destination<=5)) ) ){
                continue;
            }


            if(Math.abs(source-lift[i])<minDistance){
                int numberOfStops = findStops( i, lift[i] , source ,destination);
                if( ((lift[i]>=source && destination<source) ||
                        (lift[i]<source && source<destination)) || numberOfStops<minStops ){
                        firstPreference = i;
                        minStops=numberOfStops;
                    minDistance=Math.abs(source-lift[i]);
                }
                else{
                    secondPreference=i;
                    minDistance=Math.abs(source-lift[i]);
                }
            }
        }

        if(firstPreference!=-1 && Math.abs(source-firstPreference)<Math.abs(source-secondPreference)){
            return firstPreference;
        }
        return secondPreference;
    }
    public static void main(String[] args) {
        int[] lift = new int[5];


        while (true){

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter Your Current Floor");
            int source =scanner.nextInt();

            System.out.println("Enter Your Destination Floor");
            int destination = scanner.nextInt();

            int liftInd = findLift(lift , source,destination);

            lift[liftInd]=destination;


            System.out.print("Lift : ");
            for(int i=1;i<=5;i++){
                System.out.printf("L"+i+" ");
            }
            System.out.println();
            System.out.print("Floor: ");
            for(int i=0;i<lift.length;i++){
                System.out.print(lift[i]+" ");
            }
            System.out.println();
        }
    }
}
