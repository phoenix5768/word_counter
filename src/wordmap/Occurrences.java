
package wordmap;

import com.sun.source.tree.Tree;

import java.util.Map;
import java.util.Set;

import java.util.TreeMap;
import java.util.TreeSet;

public class Occurrences  
{
    private Map< String, Map< String, Set< Position >>> occ; 
        // Maps words -> filename -> sets of their Positions in the file.
 
    public Occurrences( ) 
    {
        occ = new TreeMap<> ( ); 
    }

    public void put( String word, String filename, Position pos ) {
        if(!occ.containsKey(word)) {
           // System.out.println("1 " + word + " " + filename + " " + pos);
            Map<String, Set<Position>> tempMap = new TreeMap<>();
            Set<Position> tempSet = new TreeSet<>();
            tempSet.add(pos);
            tempMap.put(filename, tempSet);
            occ.put(word,tempMap);


        } else {
            if(!occ.get(word).containsKey(filename)) {
                //System.out.println("2 " + word + " " + filename + " " + pos);
                Set<Position> tmp = new TreeSet<>();
                tmp.add(pos);
                occ.get(word).put(filename, tmp);
            } else {
                //System.out.println("3 " + word + " " + filename + " " + pos);
                Position p = pos.clone();
                occ.get(word).get(filename).add(p);
                //System.out.println(pos + " " + occ.get(word).get(filename).contains(pos));
            }

        }

    }


    public int countWords( ) {
        return occ.size();
    }

    public int count( ) {
        int sum = 0;
        for(Map.Entry<String, Map<String, Set<Position>>> entry : occ.entrySet()) {
            for(Map.Entry<String, Set<Position>> entry1 : entry.getValue().entrySet()) {
                sum = sum + entry1.getValue().size();
            }
        }
        return sum;
    }

    public int count( String word ) {
        int sum = 0;
        for(Map.Entry<String, Map<String, Set<Position>>> entry : occ.entrySet()) {
            if(entry.getKey() == word) {
                for(Map.Entry<String, Set<Position>> entry1 : entry.getValue().entrySet()) {
                    sum = sum + entry1.getValue().size();
                }
            }
        }
        return sum;

//        if(occ.get(word) != null) {
//            return occ.get(word).size();
//        } else {
//            return 0;
//        }

    }

    public int count( String word, String file ) {
        if(occ.get(word).get(file) != null) {
            return occ.get(word).get(file).size();
        } else {
            return 0;
        }
    }
 
    public String toString( ) {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<String, Map<String, Set<Position>>> entry : occ.entrySet()) {
            str.append("word \"" + entry.getKey() + "\"" + " occurs " + count(entry.getKey()) + " times: " + "\n");

            //System.out.println("word \"" + entry.getKey() + "\"" + " occurs " + count(entry.getKey()) + " times: ");
            for(Map.Entry<String, Set<Position>> entry1 : entry.getValue().entrySet()) {
                str.append("   " + "in file \"" + entry1.getKey() + "\":" + "\n");
                //System.out.println("   " + "in file \"" + entry1.getKey() + "\":");
                for(Position s : entry1.getValue()) {
                    str.append("      at " + s + "\n");
                    //System.out.println("       at " + s);
                }

               //System.out.println(entry.getKey() + " " + entry1.getKey() + " " + entry1.getValue() +
               //        " " + entry1.getValue().size());
           }
      }
        return str.toString();
    }
}


