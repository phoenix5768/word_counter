package wordmap;

import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        File file = new File("test_dir");
        wordmap.Occurrences occ = new wordmap.Occurrences();
        FileWalker.addOccurrences(file, occ);
        System.out.println(occ);
        System.out.println(occ.countWords());
        System.out.println(occ.count());

//        wordmap.Occurrences occ = new wordmap.Occurrences( );
//
//        occ.put( "hello", "file", new wordmap.Position(1,2));
//        occ.put( "hello", "file", new wordmap.Position(3,4));
//        occ.put( "hello", "file", new wordmap.Position(3, 5));
//        occ.put( "goodbye", "f2", new wordmap.Position(5,6));
//        occ.put( "goodbye", "file", new wordmap.Position(1,2));
//        occ.put( "goodbye", "file", new wordmap.Position(1, 3));
//        occ.put( "goodbye", "file", new wordmap.Position(1, 1));
//
//        System.out.println( occ );
//        System. out. println( "countWords      " + occ. countWords( ));
//        System. out. println( "count           " + occ. count( ));
//        System. out. println( "goodbye in f2   " + occ. count( "goodbye", "f2" ));
//        System. out. println( "goodbye in f4   " + occ. count( "goodbye", "f4" ));
//        System. out. println( "hello in file   " + occ. count( "hello", "file" ));
//        System. out. println( "farewell        " + occ. count( "farewell" ));
//        System. out. println( "goodbye        " + occ. count( "goodbye" ));

    }
}
