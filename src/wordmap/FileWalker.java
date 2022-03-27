
package wordmap;
import java.io.File;
import java.io.FileReader; 
import java.io.BufferedReader; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;


public abstract class FileWalker
{

    public static boolean seemsOK( File f )
    {
        return f. exists( ) && f. canRead( ); 
    }

    public static boolean seemsOK( String filename )
    { 
        return seemsOK( new File( filename ));
    }

    public static Occurrences getOccurrences( String filename ) 
    throws FileNotFoundException, IOException
    {
        Occurrences occ = new Occurrences( ); 
        addOccurrences( new File( filename ), occ );
        return occ; 
    }



    public static void addOccurrences( File file, Occurrences occ ) throws FileNotFoundException, IOException {
        if(file.isDirectory()) {
            traverseDirectory(file, occ);
        } else if(file.isFile()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            addOccurrences(reader, file.toString(), occ);
        }
     }

     public static void traverseDirectory(File file, Occurrences occ) throws IOException {
        for(File f : file.listFiles()) {
            if(f.isDirectory()) {
                traverseDirectory(f, occ);
            }

            if(f.isFile()) {
                addOccurrences(f, occ);
            }
        }
     }


    public static void addOccurrences( BufferedReader source, String sourcename, Occurrences occ ) throws IOException {
        int colNum = 0;
        int lineNum = 1;

        String token = "";

        int ch;

        do{
            ch = source.read();

            if(Syntax.isInWord((char)ch)) {
                token = token + (char)ch;
            } else {
                colNum++;
                if(token.length() > 0) {
                    //System.out.println("token: " + token.toLowerCase());
                    Position pos = new Position(lineNum, colNum);
                    occ.put(token.toLowerCase(), sourcename, pos);
                    colNum = colNum + token.length();
                    token = "";
                }
            }
            if(Syntax.isNewLine((char)ch)) {
                lineNum++;
                colNum = 0;
            }
        } while (ch != -1);
        source.close();
    }

}


