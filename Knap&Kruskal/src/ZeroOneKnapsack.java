import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ZeroOneKnapsack
{
    public void solve(int[] wt, int[] val, int W, int N)
    {
        int NEGATIVE_INFINITY = Integer.MIN_VALUE;
        int[][] m = new int[N + 1][W + 1];
        int[][] sol = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++)
            {
                for (int j = 0; j <= W; j++)
                    {
                        int m1 = m[i - 1][j];
                        int m2 = NEGATIVE_INFINITY;
                        if (j >= wt[i])
                            m2 = m[i - 1][j - wt[i]] + val[i];
                        m[i][j] = Math.max(m1, m2);
                        sol[i][j] = m2 > m1 ? 1 : 0;
                    }
            }
        int[] selected = new int[N + 1];
        for (int n = N, w = W; n > 0; n--)
            {
                if (sol[n][w] != 0)
                    {
                        selected[n] = 1;
                        w = w - wt[n];
                    }
                else
                    selected[n] = 0;
            }
        System.out.print("\nItems with Profit ");
        for (int i = 1; i < N + 1; i++)
            if (selected[i] == 1)
                System.out.print(val[i] +" ");
        System.out.println("are selected by 0/1 Knapsack algorithm.");
    }
    
    
    public static void main (String[] args)
    {
    	Scanner in=new Scanner(System.in);
        int i,j;
        //asking users to either put inputs from command line or from txt file.
        System.out.println("0/1 Knapsack");
        System.out.println("1.Enter inputs from command line");
        System.out.println("2.Enter the file name to get the input");
        System.out.println("Select an option: ");
        int option = in.nextInt();
        
        //to get the input to solve
        ZeroOneKnapsack ks = new ZeroOneKnapsack();
        System.out.println("Enter the number of Items : ");
        int n = in.nextInt();
        int[] wt = new int[n + 1];
        int[] val = new int[n + 1];

        
        System.out.println("Enter the maximum capacity of the Knapsack : ");
        int W = in.nextInt();
        
        
        switch(option)
        {
                case 1:
                    
                    for(i=0;i<n;i++)
                    {
                      System.out.println("Enter the Weight and Profit of Item"+(i+1)+" : ");
                      wt[i]=in.nextInt();
                      val[i]=in.nextInt();
                    }
                    
                    ks.solve(wt, val, W, n);
                    //scan.close();
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
                                        
                                        System.out.println("Enter the Weight and Profit of Item from the file.");
                                        for(i=0;i<n;i++)
                                        {                       
	                                        /*input.txt
	                                        3 items
	                                        Capacity 50
	                                        Weight --- Profit
	                                      	10 30
											20 50
											30 60
											 
											taking from 20+30 (50 capacity)
											Profit = 110 */
                                        	wt[i]=scanner.nextInt();
                                        	val[i]=scanner.nextInt();
                                        }
                                        
                                        ks.solve(wt, val, W, n);
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