package com.company.sdet_programs.two_dim_array;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ClemonteJohnstone {

    public static void main(String... args) {
        String[][] array = { {"a", "b", "c"} , {"d", "e", "f"} };
        Predicate<String> pred = s -> s.startsWith("e");
        //var list = filter(array, pred, 1);
        //list.forEach(System.out::println);
    }
/*
    public static List<Clemonte> filter(String[][] array, Predicate<String> pred, int column) {
        return IntStream.range(0, array.length)
                .filter(i -> pred.test(array[i][column]))
                .mapToObj(i -> new Clemonte(i, array[i]))
                .toList();
    }*/
}
/*
    record Clemonte(int rownr, String[] array) {
        @Override
        public String toString() {
            return String.format("%d: %s", rownr, Arrays.toString(array));
        }
    }
*/



/*
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class _01_Remove_Object_From_ArrayList_By_Overriding_Equals {
    @Override
    public boolean equals(Object object) {
        // File file = (File) object;
    	// Check some condition here
        return object instanceof File;
    }

    public static void main(String[] args) {
    	List<File> files = new ArrayList<>();

        files.add(new File("/usr/local/bin/chromedriver"));
        files.add(new File("/usr/local/bin/chromedriver"));


        files.remove(new File("/usr/local/bin/chromedriver"));

        for(File file : files)
            System.out.println(file);

    }
}
*/


/*

class Message {
	String msg = "LET IT GO!";

	public void print() {
		System.out.println(msg);
	}
}

public class _09_Question_POJO_Pass_By_Ref {
	public static void change(Message m) { 			// Line n5
		m.msg = "NEVER LOOK BACK!"; 				// Line n6
	}

	public static void main(String[] args) {
		Message obj = new Message(); 				// Line n1
		obj.print(); 								// Line n2
		change(obj); 								// Line n3
		obj.print(); 								// Line n4
	}
}
 */