import java.util.*;
import java.io.*;
import java.lang.*;

public class Kruskal
{
  public static int cost[][];
  public static int n;
  
  public static void krush(int cost[][],int n)
  {
    int set[]=new int[10];         // Holds the forest or set of N nodes 
    int mst[][]=new int[10][10];   // Holds the MST nodes 
    int printcost[][]=new int[10][10];

    int min;
    int i,j;
    int u=-1,v=-1;
    int count=0;
    int sum=0,k=0;    // sum holds total cost of MST and k is the index of MST                                              
    
    // Initializes the set 
    for(i=0;i<n;i++)
     set[i]=i;                                                               

    while(count<n-1)
    {
      min=999;
      
      for(i=0;i<n;i++)
      {
        for(j=0;j<n;j++)
        {
          if(cost[i][j]!=0 && cost[i][j]<min)
           { 
        	  //Edges with least cost 
              min=cost[i][j];
              u=i;
              v=j;                                                    
           }
        }
      }
      
      i=find(u,set);
      j=find(v,set);

      if(i!=j)
       { 
    	 // Selects the edges for Spanning tree 
         mst[k][0]=u;
         mst[k][1]=v;                                                   
         k++;
   
         count++;
         sum=sum+min;
         // Takes the union of (u,v) 
         set[v]=u;                                                      
       }
      
      // print the cost of MST
      printcost[u][v]=cost[u][v];                                       

      // Delete the edge(u,v) from the list to consider next min 
      cost[u][v]=cost[v][u]=999;                                       

    }

    if(sum>=999)
     System.out.println("\nSpanning tree doesnt exist");
    else
     { 
       System.out.println("\nThe edges selected for MST are:");
       for(i=0;i<n-1;i++)
         System.out.printf("\nEdge%d :  %d----%d   cost = %d",(i+1),
        		 mst[i][0],mst[i][1],printcost[mst[i][0]][mst[i][1]]);
       System.out.println("\nMin cost of Spanning tree : "+sum);
     }
    
  }

  public static int find(int x,int set[])
  {
	// To check the nodes results in circuit or closed path 
    while(set[x]!=x)
      x=set[x];
                                                                         
    return x;
  }

  
  public static void main(String args[])
  {
	Scanner in=new Scanner(System.in);
    cost=new int[10][10];
    int i, j, n;
    
    //asking users to either put inputs from command line or from txt file.
    System.out.println("Kruskal Algorithm");
    System.out.println("1.Enter input from command line.");
    System.out.println("2.Enter the file name to get the input.");
    System.out.println("Select an option: ");
    int option = in.nextInt();
    System.out.print("Enter the number of vertices: ");
    n=in.nextInt();
    
    switch(option)
    {
            case 1:
                System.out.print("\n-----FOR INF PUT IN 0-----\nEnter the adjacency matrix:\n");
                for(i=0;i < n;i++)
                {
                 for(j=0;j < n;j++)
                 {
                  cost[i][j]=in.nextInt();
                 }
                }
                krush(cost,n);
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
                                    for(i=0;i < n;i++)
                                    {
                                     for(j=0;j < n;j++)
                                     {
                                    	 cost[i][j]=scanner.nextInt();                                      
                                     }
                                    }
                                    krush(cost,n);
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