package com.educorp.eduinteractive;

import java.util.ArrayList;
import java.util.List;

public class TofoTenUnLimite {

	public static void main(String[] args) {
		List<String> lista = new ArrayList <String>();
		int i = 0;
		String s = "tkfagleskdajfjñdshflñdshfldsafhkdjsfbkdajshfkdsajfhkdsajfhkdjñfhkjdsfBjakbfuaiwrhfsjbfañjdfwhjlkñjr";
		while(true) {
			lista.add(s + i);
			i++;
			if(i%1000 == 0) {
				System.out.println("" + i);
			}
		}
	}
}
