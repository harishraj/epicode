package com.additional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class DP {

    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");

        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));

            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        List<String> list = letterCombinations("9259845371");
        Iterator<String> iter = list.iterator();
        while(iter.hasNext())
            System.out.println(iter.next());
    }

}
