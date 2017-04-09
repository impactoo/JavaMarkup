package javamarkup.syntaxanalyser;

import javamarkup.lexicalanalyser.SymbolRecord;
import java.util.*;
import java.io.*;

public class SyntaxAnalyser{
	public static void analyse(File sourcecode){
		// retrieve the symbol table
		ArrayList<SymbolRecord> symbolTable = new ArrayList<>();
		BufferedReader buf;
		try{
			buf = new BufferedReader(new FileReader(sourcecode.getName().split("[.]")[0]+".symboltable.jm"));
			String line = null;
			while((line = buf.readLine())!=null){
				String[] words = line.split(",");
				symbolTable.add(new SymbolRecord(Integer.parseInt(words[0]), words[1], Mapper.getType(words[2])));
			} 
		}catch(IOException e){
			System.out.println("error");
		}

		String inputString = Mapper.generateInputString(symbolTable);
		System.out.println(inputString);
		ArrayList<String> errorLogs = Parser.parse(inputString, symbolTable);
	}
}