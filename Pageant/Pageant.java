import java.io.*;
import java.util.*; 

public class Pageant 
{ 
    private static int[][] arrN;
    public static void main (String[] args) throws FileNotFoundException, IOException
    {
        boolean[][] booleanArr = filetoBoolArray();
        arrN = new int[booleanArr.length][booleanArr[0].length];

        int count = 0; 
        for (int x =0; x < booleanArr.length; x++)
        {
            for (int c =0; c <booleanArr[0].length; c++)
            {
                if (booleanArr[x][c] == true)
                {
                    count++; 
                    recursivelyMeasureAndMarkCow (x, c, booleanArr, count);
                }
            }
        }

        ArrayList<String> ones = new ArrayList<String>();
        ArrayList<String> twos = new ArrayList<String>();
        
         

        for (int x= 0; x < arrN.length; x ++)
        { 
            for (int y = 0; y<arrN[0].length; y++)
            { 
                    if (arrN[x][y] == 1) 
                        ones.add (x + " " + y); 
                    if (arrN[x][y] == 2) 
                        twos.add((x + " " + y)); 
                }
            }
        
        for (int x =0; x < twos.size(); x++) 
            System.out.println (twos.get(x));

        int dMin = 2500; 
        String sMin1 = ""; 
        String sMin2 = "";

        for (int x = 0; x < ones.size(); x++) 
        { 
            for (int y = 0; y < twos.size(); y++)
            { 
                int distance; 
                String[] sTemp = ones.get(x).split("\\W+");
                int xR = Integer.parseInt(sTemp[0]);
                int xC = Integer.parseInt(sTemp[1]); 
                String[] sTemp2 = twos.get(y).split("\\W+");
                int yR = Integer.parseInt(sTemp2[0]);
                int yC = Integer.parseInt(sTemp2[1]);

               
                distance = ((Math.abs(yR - xR)) + (Math.abs(yC - yC))); 
              
                if (distance < dMin)
                { 
                    dMin = distance;
                    sMin1 = ones.get(x);
                    sMin2 = twos.get(x);
                }
            }
        }
        System.out.println (sMin1 + " " + sMin2);
        System.out.println (dMin);
    }

    private static boolean[][] filetoBoolArray() throws FileNotFoundException, IOException
    { 
        Scanner keyboard = new Scanner (System.in); 
        System.out.print ("Enter file name: "); 

        String file; 
        file = keyboard.nextLine().trim();
        BufferedReader b = new BufferedReader (new FileReader (file)); 

        String size = b.readLine(); 
        String[] sTemp = size.split("\\W+");
        int r = Integer.parseInt(sTemp[0]);
        int c = Integer.parseInt(sTemp[1]); 

        boolean[][] temp = new boolean[r][c]; 
        for (int x = 0; x < r; x++) 
        {
            String s = b.readLine();
            for (int y = 0; y < c; y++)
            {
                char f = s.charAt(y);
                if (f == '.')
                    temp[x][y] = false; 
                else if (f == 'X')
                    temp[x][y] = true;
            }
        } 
        return temp; 
    }

    private static void recursivelyMeasureAndMarkCow (int row, int col, boolean[][] arr, int count)
    { 

        if (row < 0 || col < 0 || col >= arr[0].length || row >= arr.length)
        {
            return;
        }
        boolean b = arr[row][col];
        if (b == false) 
        {
            arrN[row][col] = 0;

        }
        else 
        {  
            arrN[row][col] = count;
            arr[row][col] = false; 
            recursivelyMeasureAndMarkCow (row-1, col, arr, count);
            recursivelyMeasureAndMarkCow (row+1, col, arr, count);
            recursivelyMeasureAndMarkCow (row, col+1, arr, count);
            recursivelyMeasureAndMarkCow (row, col-1, arr, count); 

        }
    }

    
}


