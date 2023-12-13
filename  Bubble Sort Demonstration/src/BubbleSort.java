import java.time.Duration;
import java.time.Instant;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//Demonstration of the bubble sort algorithm
public class BubbleSort {

	public static void main(String[] args)  {

		//This will move to the next file
		for(int size =10; size <= 1000000; size*=10) {

			//Initialize new array
			int[] numbers = new int[size];
			//array index
			int index = 0;


			//Read a list of numbers into an array
			BufferedReader inFile;
			try {
				inFile = new BufferedReader(new FileReader("src\\sortme" 
						+ size + ".txt"));
				//Read in each line into the array 
				String line = inFile.readLine();
				while(line != null) { 
					//Use parseInt to change line from String to int
					numbers[index] = Integer.parseInt(line);
					//Move to the next index
					index++;
					//Read in a new line
					line = inFile.readLine();
				}
				inFile.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			System.out.println("Sorting sortme" + size + ".txt ..." );
			System.out.println("-----------------------------------");
			//Time sorting process
			Instant start = Instant.now();
			numbers = bsort(numbers);
			Instant finish = Instant.now();




			//Time to sort in MilliSeconds
			long elapsed = Duration.between(start, finish).toMillis();

			System.out.println("Time to sort(in MS): " + elapsed);
			System.out.println("-----------------------------------\n\n");

		}
	}//End Main


	//Standard bubble sort algorithm
	public static int[] bsort(int[] listIn){

		//Keep track of the number of swaps and comparisons made
		long swapCount =0 ,compCount = 0;

		for(int pass = 1; pass < listIn.length; pass++){
			//Compares each number to the following number
			for(int number = 0; number < (listIn.length - pass); number++){
				compCount++;
				if (listIn[number] > listIn[(number + 1)]){
					swapCount++;
					int temp = listIn[number];
					listIn[number] = listIn[(number + 1)];
					listIn[(number + 1)] = temp;
				}//End if
			}
		}

		System.out.println("Number of Swaps: " + swapCount);
		System.out.println("Number of Comparisons: " + compCount);
		return listIn;
	}//End bSort



	//Improved bubble sort function(will stop when no swaps are made)
	public static int[] smartBSort(int[] listIn){

		//Keep track of the number of swaps and comparisons made
		long swapCount =0 ,compCount = 0;

		for(int pass = 1; pass < listIn.length; pass++){
			boolean swap = false;
			//Compares each number to the following number
			for(int number = 0; number < (listIn.length - pass); number++){//Pass
				//Will iterate if a comparison is made
				compCount++;

				if (listIn[number] > listIn[(number + 1)]){
					//Will iterate if a swap is made
					swapCount ++;
					//temporarily holds the current number 
					int temp = listIn[number];
					//Move next number to current
					listIn[number] = listIn[(number + 1)];
					//temp gets moved to next
					listIn[(number + 1)] = temp;
					swap = true;
				}//End if

			}

			//Will break if no swap is made
			if(!swap) 
				break;

		}

		System.out.println("Number of Comparisons: " + compCount);
		System.out.println("Number of Swaps: " + swapCount);

		return listIn;
	}//End smartBSort

}