import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KnightsTour {
	
	public static int[][] array;
	
	public static JFrame frame;
	
	public static int x,y;
	
	public static boolean isPlaced;
	
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
		java.util.List<String> list = new ArrayList<String>();
		
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
		array[r][c]=1;
		for(int i=0;i<a.length;i++)
		{
			array[a[i][0]][a[i][1]] = 1;
			//display();
			if (checkOnes() == true)
			{
				//Got solution
				solutions++;
				//System.out.println("solved");
				
			}
			knight(a[i][0], a[i][1]);
		}
		array[r][c]=0;
	}
	
	public static void initialise()
	{
		x=y=0;
		isPlaced = false;
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
	}
	
	public static void drawChessBoard(Graphics g)
	{
		int FWidth = frame.getWidth();
		int FHeight = frame.getHeight()-22;
		
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				if((i+j)%2==0)
					g.setColor(Color.white);
				else
					g.setColor(Color.black);
				
				int width = FWidth/array[0].length;
				int height = (FHeight*2/3)/array.length;
				
				g.fillRect(j*width, i*height, width, height);
			}
			
		}
	}
	
	public static void placeKnight(Graphics g)
	{
		int r = getGridPosition()[0];
		int c = getGridPosition()[1];
		System.out.println("r: "+r+" c: "+c);
		int FWidth = frame.getWidth();
		int FHeight = frame.getHeight();
		
		int width = Math.floorDiv(FWidth,array[0].length);
		int height = Math.floorDiv((FHeight*2/3),array.length);
		
		int x = r*height+height/3;
		int y = c*width+width/3;
		
		
		
		g.fillOval(y, x, 25, 25);
		
	}
	
	public static class Panel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			int FWidth = frame.getWidth();
			int FHeight = frame.getHeight()-22;
			
			
			drawChessBoard(g);
			
			g.setColor(Color.BLUE);
			if(isPlaced==true)
				placeKnight(g);
			
		}
		
	}
	
	
	public static class Mouse implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			x=e.getX();
			y=e.getY();
			System.out.println(x+","+y);
			isPlaced = true;
			frame.paintComponents(frame.getGraphics());
			getGridPosition();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static int[] getGridPosition()
	{
		
		int r = Math.floorDiv(y,Math.floorDiv((frame.getHeight()*2/3),array.length));   // Position / height of the frame. Height is FrameHeight/No.of grids.
		int c = Math.floorDiv(x,Math.floorDiv(frame.getWidth(),array[0].length));		// Position / width of the frame.
		int[] a = new int[2];
		a[0]=r;
		a[1]=c;
		
		return a;
	}
	
	
	public static void main(String args[])
	{
		initialise();
		solutions=0;
		frame = new JFrame("Knight's tour");
		Panel graphicsPanel = new Panel();
		
		
		graphicsPanel.addMouseListener(new Mouse());
		frame.setLayout(new GridLayout(1,1));
		
		//frame.add(textPanel);
		frame.setSize(600, 700);
		frame.add(graphicsPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
