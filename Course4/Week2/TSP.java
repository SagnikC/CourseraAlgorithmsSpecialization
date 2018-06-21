
import java.io.*;
import java.util.*;

public class TSP {
	
	public static void main(String[] args)  throws IOException{
		

		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Sagnik\\Desktop\\tsp.txt"));
		int n = Integer.parseInt(br.readLine())-1;
		
		double x[] = new double[n];
		double y[] = new double[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		double srcx = Double.parseDouble(st.nextToken());
		double srcy = Double.parseDouble(st.nextToken());
		
		for(int i=0; i<n; i++)
		{
			st = new StringTokenizer(br.readLine());
			x[i] = Double.parseDouble(st.nextToken());
			y[i] = Double.parseDouble(st.nextToken());
		}
		
		double dist[][] = new double[n][n];
		
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				if(i != j)
					dist[i][j] = dist(x[i],y[i],x[j],y[j]);
		
		double dp[][] = new double[(1<<n)][n];
		
		for(int curr=1; curr<(1<<n); curr++)
		{
			if((curr & (curr-1)) == 0 )
			{
				int i = 0;
				while(((1<<i) & curr) == 0) i++;
				dp[curr][i] = dist(srcx,srcy,x[i],y[i]);
			}
			else
			{
				int i = 0;
				while((1<<i) <= curr)
				{
					if(((1<<i) & curr) != 0)
					{
						int j = 0;
						double res = -1;
					
						while((1<<j) <= curr)
						{
							if(((1<<j) & curr)!= 0 && i != j)
							{
								if(res == -1)
									res = dp[curr^(1<<i)][j] + dist[j][i];
								else
									res = Math.min(res, dp[curr^(1<<i)][j] + dist[j][i]);
							}
							j++;
						}
						dp[curr][i] = res;
					}
					i++;
				}
					
			}
		}	
		double min = Double.MAX_VALUE;
		
		for(int i=0; i<n; i++)
			{
			min = Math.min(min, dp[(1<<n)-1][i] + dist(srcx,srcy,x[i],y[i]));
			}
		
		System.out.println(min);
		
		
	}

	static double dist(double x1,double y1,double x2, double y2)
	{
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
}
