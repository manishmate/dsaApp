package org.example.patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Recursion {
    /**
     * Print all subsequence of string
     * Expected Time Complexity: O( n*2^n  )
     * Expected Space Complexity: O( n * 2^n )
     * Input :
     * str = "abc"
     * Output: a ab abc ac b bc c
     *                                  (index=0, current="")
     *                                /                     \
     *                    Pick 'a'   /                       \  Don't Pick 'a'
     *                              /                         \
     *                       (1, "a")                        (1, "")
     *                      /        \                        /       \
     *            Pick 'b' /          \ Skip 'b'    Pick 'b' /         \ Skip 'b'
     *                    /            \                    /            \
     *               (2, "ab")        (2, "a")          (2, "b")         (2, "")
     *               /      \          /      \           /      \           /     \
     *              /        \        /        \        /        \         /       \
     *             /          \      /          \      /          \       /          \
     *         (3,"abc")  (3,"ab") (3,"ac")   (3,"a") (3,"bc")  (3,"b")  (3,"c")    (3,"")
     *           [ADD]      [ADD]    [ADD]     [ADD]    [ADD]     [ADD]   [ADD]     [SKIP]
     */

    public static List<String> getAllSubsrting(String str){
        ArrayList<String> list =  new ArrayList<>();
        int index =0;
        String current = "";
        addAllSubstring(str,current,index,list);
        //sort to get in lexi-graphical order
        Collections.sort(list);
        return list;
    }

    public static void addAllSubstring(String str, String current, int index, ArrayList<String> list){
        if(index==str.length())
            if(current.length()!=0)
                list.add(current);

        //include character
        addAllSubstring(str,current+str.charAt(index), index+1,list);

        //exclude character
        addAllSubstring(str,current, index+1,list);

    }

    /**
     * All permutations of string
     * Input: s = "ABC"
     * Output: ["ABC", "ACB", "BAC", "BCA", "CAB", "CBA"]
     *
     *                                        ( "", "ABC" )
     *                                      /       |       \
     *                            i=0, 'A'/   i=1, 'B'|   i=2, 'C'\
     *                                  /        |        \
     *                          ( "A", "BC" )  ( "B", "AC" )  ( "C", "AB" )
     *                            /       \      /       \      /       \
     *                  i=0, 'B'/ i=1, 'C'\ i=0,'A'/ i=1,'C'\ i=0,'A'/ i=1,'B'\
     *                        /         \       /         \       /         \
     *                  ("AB","C") ("AC","B") ("BA","C") ("BC","A") ("CA","B") ("CB","A")
     *                      |          |          |          |          |          |
     *                i=0, 'C'|    i=0, 'B'|    i=0, 'C'|    i=0, 'A'|    i=0, 'B'|    i=0, 'A'|
     *                      |          |          |          |          |          |
     *                 ("ABC","") ("ACB","") ("BAC","") ("BCA","") ("CAB","") ("CBA","")
     *                   [ADD]      [ADD]      [ADD]      [ADD]      [ADD]      [ADD]
     */

    /**CONCEPT */
    /*void backtrack(prefix, remaining, set) {
        if (remaining is empty) {
            add prefix in set
            return
        }

        for (each char in remaining) {
            pick char
            recurse with (prefix + char, remaining - char,set)
        }
    }*/


    public ArrayList<String> findPermutation(String s) {
        // Code here
        HashSet<String> set = new HashSet<>();
        generate("",s,set);
        return new ArrayList<String>(set);
    }

    public void generate(String prefix, String remaining, HashSet<String> set){

        if(remaining.length()==0){
            set.add(prefix);
            return;
        }

        for(int i=0; i<remaining.length();i++){
            char current = remaining.charAt(i);
            String newprefix = prefix+current;
            String remainingStr = remaining.substring(0,i)+remaining.substring(i+1);
            generate(newprefix,remainingStr,set);
        }
    }


}
