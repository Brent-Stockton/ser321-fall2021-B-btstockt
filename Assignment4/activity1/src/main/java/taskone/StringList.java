package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {
    
    List<String> strings = new ArrayList<String>();

    public boolean contains(String str) {
        return strings.indexOf(str) >= 0;
    }

    public int size() {
        return strings.size();
    }

    public String toString() {
        return strings.toString();
    }

    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }

    public String remove(int key){
        String temp;
        temp = strings.get(key);
        strings.remove(key);
        return temp;
    }

    //Displaying the list
    public void display(){
        for(String lists:strings){
            System.out.println(lists);
        }
    }

    public int count() {
        int count = strings.size();
        return count;
    }


    public String reverse(int index){
        String name  = strings.get(index);
        StringBuilder Str = new StringBuilder(name).reverse();
        strings.set(index,Str.toString());
        return name;
    }


}

