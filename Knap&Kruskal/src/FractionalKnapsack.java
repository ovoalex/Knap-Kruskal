import java.util.*;
import java.io.*;
import java.lang.*;

public class FractionalKnapsack
{
  public static float weight[];
  public static float profit[];
  public static float capacity;
  public static int n;

  public static void knapsack()
  {
    float selected[]=new float[n];
    float TotalProfit=0;
    float TotalItem=0;
    float RemCap=capacity;                                                 
    int i;

    for(i=0;i<n;i++)
      selected[i]=0;
 
    for(i=0;i<n;i++)
     {
        if(RemCap<weight[i])
           break;

        else
          {
             selected[i]=1;
             TotalProfit=TotalProfit+profit[i];
             TotalItem=TotalItem+selected[i];
             RemCap=RemCap-weight[i];
          }
     }
    // Find the fraction of the object selected for Max Profit
    if(i<n)
     {
        selected[i]=RemCap/weight[i];                                       
        TotalProfit=TotalProfit+(selected[i]*profit[i]);
        TotalItem=TotalItem+selected[i];
     }

     System.out.println("\nTotal Item selected : "+TotalItem);
     System.out.println("\nTotal Profit of Knapsack : "+TotalProfit);
        
  }

  public static void main(String args[])
  {
	Scanner in=new Scanner(System.in);
    int i,j;
    //asking users to either put inputs from command line or from txt file.
    System.out.println("Fractional Knapsack.");
    System.out.println("1.Enter Input from command line.");
    System.out.println("2.Enter the file name to get the inputs");
    System.out.println("Select an option: ");
    int option = in.nextInt();
    
    System.out.println("Enter the number of Items : ");
    n=in.nextInt();

    weight=new float[n];
    profit=new float[n];
    
    System.out.println("Enter the maximum capacity of the Knapsack : ");
    capacity=in.nextInt();
    
    
    switch(option)
    {
            case 1:
                
                for(i=0;i<n;i++)
                {
                  System.out.println("Enter the Weight and Profit of Item"+(i+1)+" : ");
                  weight[i]=in.nextInt();
                  profit[i]=in.nextInt();
                }

               float ratio[]=new float[n];

               for(i=0;i<n;i++)
                 ratio[i]=profit[i]/weight[i];

               for(i=0;i<n;i++)
                {
                  for(j=0;j<n-i-1;j++)
                   {
                     if(ratio[j]<ratio[j+1])
                       {
                   	   //Sort in descending order based on Profit/Weight ratio using Bubble Sort
                          float temp=ratio[j];
                          ratio[j]=ratio[j+1];
                          ratio[j+1]=temp;                                                     
                         
                          temp=weight[j];
                          weight[j]=weight[j+1];
                          weight[j+1]=temp;
           				
                          temp=profit[j];
                          profit[j]=profit[j+1];
                          profit[j+1]=temp;
                       }
                   }
                }
               knapsack();
               break;
               
            case 2:
                    System.out.println("Enter the file name: ");
                    try{
                    		//to open file and check file
                            in.nextLine();
                            String filename;
                            filename = in.nextLine();
                            File file = new File(filename); 
                            //open scanner for input int from txt file
                            Scanner scanner = new Scanner(file);
                            BufferedReader br = new BufferedReader(new FileReader(file)); 

                            String line; 
                            
                            
                            while ((line = br.readLine()) != null) {
                                    String []tokens = line.split(" ");
                                    
                                    System.out.println("Entering Weight and profit from file...");
                                    for(i=0;i<n;i++)
                                    {
                                     /*input.txt
                                      	10 30
										20 50
										30 60
										 
										2.6666667 items selected
										Profit = 120 */
                                    
                                      weight[i]=scanner.nextInt();
                                      profit[i]=scanner.nextInt();
                                    }

                                   float ratio1[]=new float[n];

                                   for(i=0;i<n;i++)
                                     ratio1[i]=profit[i]/weight[i];

                                   for(i=0;i<n;i++)
                                    {
                                      for(j=0;j<n-i-1;j++)
                                       {
                                         if(ratio1[j]<ratio1[j+1])
                                           {
                                       	   //Sort in descending order based on Profit/Weight ratio using Bubble Sort
                                              float temp=ratio1[j];
                                              ratio1[j]=ratio1[j+1];
                                              ratio1[j+1]=temp;                                                     
                                             
                                              temp=weight[j];
                                              weight[j]=weight[j+1];
                                              weight[j+1]=temp;
                               				
                                              temp=profit[j];
                                              profit[j]=profit[j+1];
                                              profit[j+1]=temp;
                                           }
                                       }
                                    }
                                   knapsack();
                                   break;
                            }
                    }catch(Exception e){
                            System.out.println("Unable to open the file specified");
                            return;
                    }
                    break;
            default :
                    System.out.println("Please select a right option");
                    return;
    }
  }

}