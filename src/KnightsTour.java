import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class KnightsTour {
	
	public static int[][] array;
	
	public static JFrame frame;
	
	public static int x,y,key;
	
	public static int[] solutions,start,end;
	
	public static float len;
	
	public static boolean isPlaced,flag;
	
	
	public static String display()
	{
		String s = "";
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				s+=array[i][j]+" ";
				/*
				if(array[i][j]==1)
				{
					s+="K ";
				}
				else
				{
					s+="_ ";
				}
				*/
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
	
	
	public static boolean knight(int r,int c)
	{
		int[][] a = validPlaces(r,c);
		solutions[key-1]=r*array[0].length+c;
		array[r][c]=key++;
		
		for(int i=0;i<a.length;i++)
		{
			solutions[key-1]=a[i][0]*array[0].length+a[i][1];
			array[a[i][0]][a[i][1]] = key;
			if (checkOnes() == true)
			{
				//solutions
				System.out.println("Solution found");
				display();
				return true;
			}
			if(knight(a[i][0], a[i][1])==true)
				return true;
		}
		key=array[r][c];
		array[r][c]=0;
		return false;
	}
	
	public static void setNumber(int n)
	{
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[0].length;j++)
			{
				array[i][j]=n;
			}
		}
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
				int height = FHeight/array.length;
				
				g.fillRect(j*width, i*height, width, height);
				if(isPlaced==true)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font("SansSerif",Font.PLAIN,25));
					g.drawString(array[i][j]+"", j*width+width/3, i*height+height/2);
				}
			}
			
		}
	}
	
	public static int[] getGridPosition()
	{
		
		int r = Math.floorDiv(y,Math.floorDiv(frame.getHeight(),array.length));   // Position / height of the frame. Height is FrameHeight/No.of grids.
		int c = Math.floorDiv(x,Math.floorDiv(frame.getWidth(),array[0].length));		// Position / width of the frame.
		int[] a = new int[2];
		a[0]=r;
		a[1]=c;
		
		return a;
	}
	
	
	public static void placeKnight(Graphics g)
	{
		g.setColor(Color.blue);
		int r = getGridPosition()[0];
		int c = getGridPosition()[1];
		System.out.println("r: "+r+" c: "+c);
		int FWidth = frame.getWidth();
		int FHeight = frame.getHeight();
		
		int width = Math.floorDiv(FWidth,array[0].length);
		int height = Math.floorDiv(FHeight,array.length);
		
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
		}
		
	}
	
	public static class Action implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(len>array.length*array[0].length)
			{
				
				flag=false;
				return;
			}
			Graphics g = frame.getGraphics();
			g.setColor(Color.red);
			
			int x1,x2,y1,y2;
			int width = frame.getWidth()/array[0].length;
			int height = frame.getWidth()/array.length;
			x1 = solutions[(int)len]%array[0].length;
			x1=x1*width+width/2;
			x2 = solutions[((int)len)+1]%array[0].length;
			x2=x2*width+width/2;
			y1 = solutions[(int)len]/array[0].length;
			y1=y1*height+height/2;
			y2 = solutions[((int)len)+1]/array[0].length;
			y2=y2*height+height/2;
			
			g.drawLine(x1, y1, x1+(int)((x2-x1)*(len-(int)len)), y1+(int)((y2-y1)*(len-(int)len)));
			
			len+=0.01;
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
			if(knight(getGridPosition()[0],getGridPosition()[1])==false)
			{
				JOptionPane.showMessageDialog(null, "No solution found for that position");
			}
			else
			{
				isPlaced = true;
				System.out.println("Drawing solution");
				frame.paintComponents(frame.getGraphics());
				//drawSolution(frame.getGraphics());
				Timer timer = new Timer(5,new Action());
				timer.start();
				setNumber(0);
			}
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
	
	public static void initialise()
	{
		x=y=0;
		flag = isPlaced = false;
		len=0;
		String s=JOptionPane.showInputDialog("Enter the size of the board");
		int n = Integer.parseInt(s.charAt(0)+"");
		int m = Integer.parseInt(s.charAt(2)+"");
		array = new int[n][m];
		key = 1;
		solutions = new int[n*m];
		
		setNumber(0);
	}
	
	public static void main(String args[])
	{
		initialise();
		
		frame = new JFrame("Knight's tour");
		Panel graphicsPanel = new Panel();
		
		graphicsPanel.addMouseListener(new Mouse());
		
		frame.setLayout(new GridLayout(1,1));
		frame.setSize(600, 600);
		frame.add(graphicsPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
