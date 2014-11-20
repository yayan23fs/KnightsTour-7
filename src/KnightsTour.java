import javax.swing.JOptionPane;
import java.util.*;

public class KnightsTour {
	
	public static int[][] array;
	
	public static void display()
	{
		String s = "";
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				if(array[i][j]==1)
				{
					s+="K ";
				}
				else
				{
					s+="_ ";
				}
			}
			s+='\n';
		}
		System.out.println(s);
	}
	
	
	
	public static boolean canMove(int r,int c,int row,int col)
	{
		
		if(row<0||row>array.length||col<0||col>array[0].length)
			return false;
		
		if(Math.abs(row-r)>=3||Math.abs(col-c)>=3)
			return false;
		if(Math.abs(row-r)+Math.abs(col-c)==3)
			return true;
		
		return true;
	}
	
	public static LinkedList validPlaces(int r,int c)
	{
		LinkedList list = new LinkedList();
		
		int a[] = new int[2];
		
		
		if(canMove(r,c,r-2,c+1)==true) 		
		{
			a[0]=r-2;
			a[1]=c+1;
			list.add(a);
			System.out.println(r-2+","+c+1);
		}
		
		if(canMove(r,c,r-1,c+2)==true) 			//
		{
			a[0]=r-1;
			a[1]=c+2;
			list.add(a);
			System.out.println(r-1+","+c+2);
		}
		
		if(canMove(r,c,r+1,c+2)==true) 			//
		{
			a[0]=r+2;
			a[1]=c+2;
			list.add(a);
			System.out.println(r+1+","+c+2);
		}
		
		if(canMove(r,c,r+2,c+1)==true) 			//
		{
			a[0]=r+2;
			a[1]=c+1;
			list.add(a);
			System.out.println(r+2+","+c+1);
		}
		
		if(canMove(r,c,r+2,c-1)==true) 			//
		{
			a[0]=r+2;
			a[1]=c-1;
			list.add(a);
			System.out.println(r+2+","+(c-1));
		}
		
		if(canMove(r,c,r-1,c-2)==true) 			//
		{
			a[0]=r-1;
			a[1]=c-2;
			list.add(a);
			System.out.println(r+1+","+(c-2));
		}
		
		if(canMove(r,c,r-1,c-2)==true) 			//
		{
			a[0]=r-1;
			a[1]=c-2;
			list.add(a);
			System.out.println(r-1+","+(c-2));
		}
		
		if(canMove(r,c,r-2,c-1)==true)
		{
			a[0]=r-2;
			a[1]=c-1;
			list.add(a);
			System.out.println(r-2+","+(c-1));
		}
		
		
		return list;
	}
	
	public static boolean knight(int r,int c)
	{
		
		
		
		return false;
	}
	
	public static void main(String args[])
	{
		String s=JOptionPane.showInputDialog("Enter the size of the board");
		
		int n = Integer.parseInt(s.charAt(0)+"");
		int m = Integer.parseInt(s.charAt(2)+"");
		
		array = new int[n][m];
		
		display();
		
		System.out.println(canMove(0,0,1,2));
		
		LinkedList b = validPlaces(0,0);
		
		for(int i=0;i<b.size();i++)
		{
			System.out.println(((int [])b.get(i))[0]+","+((int [])b.get(i))[1]);
		}
		
	}
}
