package scanner;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Write a description of class ScannerTester here.  Description
 * 
 * @author AAron L
 * @version 9-6-19
 */
public class ScannerTester
{
    /**
     * The tester
     * @param args the 
     * @throws ScanErrorException ???
     * @throws IOException ??? 
     */
    public static void main(String[] args) throws ScanErrorException, IOException
    {
        Scanner sc = new Scanner(new FileInputStream("parser/a.txt"));
        while(sc.hasNext())
            System.out.println(sc.nextToken());
    }
}
