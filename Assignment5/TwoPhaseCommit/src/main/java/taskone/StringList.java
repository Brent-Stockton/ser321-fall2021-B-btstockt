package taskone;

import java.util.List;
import java.util.ArrayList;

class StringList {
    
    List<String> strings = new ArrayList<String>();

    public String toString() {
        return strings.toString();
    }

    public void add(String str) {
        int pos = strings.indexOf(str);
        if (pos < 0) {
            strings.add(str);
        }
    }

    public void display(){
        for(String lists:strings){
            System.out.println(lists);
        }
    }
}

