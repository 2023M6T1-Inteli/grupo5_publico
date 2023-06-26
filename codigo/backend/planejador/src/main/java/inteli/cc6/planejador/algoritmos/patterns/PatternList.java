package inteli.cc6.planejador.algoritmos.patterns;

import java.util.ArrayList;

public class PatternList {
    private final ArrayList<Pattern> PatternList = new ArrayList<Pattern>();

    //@param pattern - pattern to be added to this class' ArrayList of patterns
    public void addPattern(Pattern pattern){
        this.PatternList.add(pattern);
    }

    //@return reelList - ArrayList containing all patterns stored in this class
    public ArrayList<Pattern> getPatternList(){
        return this.PatternList;
    }
}
