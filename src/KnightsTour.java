import javax.swing.JOptionPane;


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
	
	public static int[][] validPlaces(int r,int c)
	{
		int[][] a;
		
		
		return a;
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
		
		System.out.println(canMove(0,0,0,0));
		
		
		
	}
}
