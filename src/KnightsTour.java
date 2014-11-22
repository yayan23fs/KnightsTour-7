import javax.swing.JOptionPane;
import java.util.*;

public class KnightsTour {
	
	public static int[][] array;
	
	public static int solutions;
	
	public static String display()
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
		return s;
	}
	
	
	
	public static boolean canMove(int r,int c,int row,int col)
	{
		
		if(row<0||row>=array.length||col<0||col>=array[0].length)
			return false;
		
		if(Math.abs(row-r)>=3||Math.abs(col-c)>=3)
			return false;
		if(Math.abs(row-r)+Math.abs(col-c)==3)
			return true;
		
		return true;
	}
	
	public static int[][] validPlaces(int r,int c)
	{
		List<String> list = new ArrayList<String>();
		
		String s = "";
		
		if(canMove(r,c,r-2,c+1)==true&&array[r-2][c+1]==0)
		{
			s=(r-2)+","+(c+1);
			list.add(s);
			//System.out.println((r-2)+","+(c+1));
		}
		
		if(canMove(r,c,r-1,c+2)==true&&array[r-1][c+2]==0)
		{
			s=(r-1)+","+(c+2);
			list.add(s);
			//System.out.println((r-1)+","+(c+2));
		}
		if(canMove(r,c,r+1,c+2)==true&&array[r+1][c+2]==0)
		{
			s=(r+1)+","+(c+2);
			list.add(s);
			//System.out.println((r+1)+","+(c+2));
		}
		if(canMove(r,c,r+2,c+1)==true&&array[r+2][c+1]==0)
		{
			s=(r+2)+","+(c+1);
			list.add(s);
			//System.out.println((r+2)+","+(c+1));
		}
		if(canMove(r,c,r+2,c-1)==true&&array[r+2][c-1]==0)
		{
			s=(r+2)+","+(c-1);
			list.add(s);
			//System.out.println((r+2)+","+(c-1));
		}
		if(canMove(r,c,r+1,c-2)==true&&array[r+1][c-2]==0)
		{
			s=(r+1)+","+(c-2);
			list.add(s);
			//System.out.println((r+1)+","+(c-2));
		}
		if(canMove(r,c,r-1,c-2)==true&&array[r-1][c-2]==0)
		{
			s=(r-1)+","+(c-2);
			list.add(s);
			//System.out.println((r-1)+","+(c-2));
		}
		if(canMove(r,c,r-2,c-1)==true&&array[r-2][c-1]==0)
		{
			s=(r-2)+","+(c-1);
			list.add(s);
		}
		
		int[][] a = new int[list.size()][2];
		
		for(int i=0;i<list.size();i++)
		{
			a[i][0]=Integer.parseInt(list.get(i).charAt(0)+"");
			a[i][1]=Integer.parseInt(list.get(i).charAt(2)+"");
		}
		
		
		return a;
	}
	
	public static boolean checkOnes()
	{
		
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				if(array[i][j]==0)
					return false;
			}
		}
		
		return true;
	}
	
	
	public static void knight(int r,int c)
	{
		int[][] a = validPlaces(r,c);
		
		for(int i=0;i<a.length;i++)
		{
			array[a[i][0]][a[i][1]] = 1;
			//display();
			if (checkOnes() == true)
			{
				solutions++;
			}
			knight(a[i][0], a[i][1]);
		}
		array[r][c]=0;
	}
	
	public static void main(String args[])
	{
		solutions=0;
		String s=JOptionPane.showInputDialog("Enter the size of the board");
		
		int n = Integer.parseInt(s.charAt(0)+"");
		int m = Integer.parseInt(s.charAt(2)+"");
		
		array = new int[n][m];
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				array[i][j]=0;
			}
		}
		
		
		System.out.println();
		System.out.println();
		
	}
}
