import java.io.*;
import java.util.*;

public class EmployeeFileProcessor 
{
	public static void processEmployeeFile(String path) throws IOException
	{
		boolean end = true;
		Scanner sc = new Scanner(System.in);
		while(end)
		{
			System.out.print("1.Add Employee\n2.View Employee\n3.Search Employee\n4.Reset List\n5.Exit\nChoice: ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				case 1:
				{
					AddEmployee(path);
					break;
				}
				case 2:
				{
					ViewEmployee(path);
					break;
				}
				case 3:
				{
					SearchEmployee(path);
					break;
				}
				case 4:
				{
					ResetFile(path);
					break;
				}
				case 5:
				{
					sc.close(); 
					System.out.println("Leaving...");
					end = false;
					break;
				}
				default:
				{
					System.err.print("Invalid Option Type!");
					break;
				}
			}
		}
	}
	public static void AddEmployee(String path) throws IOException // it will add(write) employee data to file with the help of bufferedWriter's line writing operation
	{
		File file = new File(path);
		FileWriter fileWriter = new FileWriter(file, true); // we done this true, in order to prevent overwrite on file, now it will append end of the file for every, initialization
		BufferedWriter buffWriter = new BufferedWriter(fileWriter);
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("|Please add the user in this structure: ID-NAME-SALARY|");
			String line = sc.nextLine();
			buffWriter.write(line);
			buffWriter.newLine();
			System.out.print("|Please enter 1 to turn main menu else press any number|\n");
			int choice = sc.nextInt();
			sc.nextLine(); // When we use get int before sc.nextLine it may give us unexpected behavior in order to prevent that we add empty nextLine operation to clean line for reading without mistake
			if(choice == 1)
				break;
		}
		buffWriter.close();
		fileWriter.close();
		sc.close();
		processEmployeeFile(path);
	}
	public static void ViewEmployee(String path) throws FileNotFoundException
	{
		File file = new File(path);
		Scanner sc = new Scanner(file); // in order o view all the employees onto console we only need to print them line by line
		
		System.out.println("\n--Employee List--");
		while(sc.hasNextLine())
		{
			System.out.println(sc.nextLine());
		}
		System.out.println("-----------------\n");
		sc.close();
	}
	public static void SearchEmployee(String path)	throws IOException
	{
		File file = new File(path);
		Scanner sc = new Scanner(file);
		Scanner sn = new Scanner(System.in);
		ArrayList<String[]> list = new ArrayList<String[]>();

		while(sc.hasNextLine()) // get the line with Scanner line operation split and place it into the string array
		{
			String line = sc.nextLine();
			list.add(line.split("-"));
		}
		
		while(true)
		{
			System.out.print("Please enter the ID or the NAME of the employee that you want to search\nID\\NAME: ");
			String input = sn.next();
			for(int i = 0; i < list.size(); i++)
			{
				if(list.get(i)[0].toLowerCase().equals(input.toLowerCase()) || list.get(i)[1].toLowerCase().equals(input.toLowerCase())) // is input equals to name ?
				{
					System.out.println("\nEmployee found!");
					System.out.print("ID: " + list.get(i)[0] + "\nNAME: " + list.get(i)[1] + "\nSALARY: " + list.get(i)[2]);
				}
			}
			System.out.print("\n|Please enter 1 to turn main menu else press any number|");
			int choice = sn.nextInt();
			if(choice == 1)
			{
				sc.close();
				sn.close();
				processEmployeeFile(path);
			}
		}
	}
	public static void ResetFile(String path) throws IOException
	{
		File file = new File(path);
		FileWriter fileWriter = new FileWriter(file);
		String reset = "";
		fileWriter.write(reset);
		fileWriter.close();
	}
	public static void main(String[] args) throws IOException 
	{
		String path = "C:\\Users\\Eren\\Desktop\\employee_data.txt";
		processEmployeeFile(path);
	}

}
