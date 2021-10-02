package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file path: "); //entrando com caminho
		String strPath = sc.nextLine();
		
		File sourceFile = new File(strPath); 
		String sourceFolderStr = sourceFile.getParent(); //pegando o caminho da pasta do arquivo
		
		boolean sucess = new File(sourceFolderStr + "\\out").mkdir(); //criando pasta na pasta do arquivo
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv"; // criando arquivo na pasta
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			
			String line = br.readLine();
			
			while(line != null) {
				
				String[] fields = line.split(",");
				String name = fields[0];
				double price = Double.parseDouble( fields[1]);				
				int quant = Integer.parseInt(fields[2]);
				list.add(new Product(name, price, quant));
				
				line = br.readLine();
				
			}
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
			
			for ( Product p : list) {
				bw.write(p.getName()+","+String.format("%.2f", p.total()));
				bw.newLine();
			}
			
			System.out.println(targetFileStr + "CREATED!");
			
		}
		catch (IOException e) {
			System.out.println("Error writing file: "+ e.getMessage());
		}
			
		} catch (IOException e) {
			System.out.println("Error reading file: "+ e.getMessage());
			
		}
			sc.close();
		}
		
		
	}


