import java.io.IOException;
import java.lang.Thread;
import java.lang.Integer;
import java.util.*;

public class Main {

	public static final String BLUE_FG = "\u001B[34m";
	public static final String PURPLE_FG = "\u001B[35m";
	public static final String YELLOW_BG = "\u001B[43m";
	public static final String COLOR_RESET = "\u001B[0m";

	public static void header() throws IOException, InterruptedException {
		new ProcessBuilder("clear").inheritIO().start().waitFor();
		System.out.println("Need title");
		System.out.println("DELA CRUZ, JOHN LORENZ N.\n");
	}

	public static void pressEnterToContinue() {
		System.out.print("\nPress \'Enter\' key to continue...");
		try {
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
		} catch (Exception e) {
		}
	}

	public static void partitionError(float arr[], int quantity, int counter) {
		System.out.println("Partition Sizes(M):");
		if (counter > 0) {
			for (int i = 0; i < counter; i++) {
				System.out.println("Partition " + (i + 1) + ": " + arr[i]);
			}
		}
		if (counter == (quantity - 1) && arr[quantity - 1] != 0) {
			System.out.println("Partition " + (counter + 1) + ": " + arr[counter]);
		} else {
			System.out.print("Partition " + (counter + 1) + ": ");
		}
	}

	public static void jobsError(float arr[], int quantity, int counter) {
		System.out.println("Job Sizes(M):");
		if (counter > 0) {
			for (int i = 0; i < counter; i++) {
				System.out.println("Input Job (" + (i + 1) + "/" + quantity + "): " + arr[i]);
			}
		}
		if (counter == (quantity - 1) && arr[quantity - 1] != 0) {
			System.out.println("Input Job (" + (counter + 1) + "/" + quantity + "): " + arr[counter]);
		} else {
			System.out.print("Input Job (" + (counter + 1) + "/" + quantity + "): ");
		}
	}

	public static void jobListError(float arr[], int quantity) {
		System.out.println("\nJob List:");
		for (int i = 0; i < quantity; i++) {
			System.out.println("Job " + (i + 1) + " : " + arr[i] + "M");
		}
	}

	public static void restartInput() {
		System.out.println("\nPress \'Enter\' key to continue...");
		System.out.println("\nAllocate or Deallocate?");
		System.out.print("Allocate(1) Deallocate(2): ");
	}

	public static void descendSort(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}

  	public static void newSet(String dataHighlight[][], String oldData[][], String partitionTaken[], float partitions[],
			int partitionQuantity, int currentSet) {
		Formatter set = new Formatter();
		for (int i = 0; i < partitionQuantity; i++) {
			int indexCounter = 0;
			set.format("%7s %5s %15s %7s ", "P" + (i + 1), "|", PURPLE_FG + partitions[i] + COLOR_RESET, "|");
			for (int j = (currentSet - 1); j > 0; j--) {
				if (dataHighlight[currentSet - j][i] == "1") {
					set.format("%2s %2s ", YELLOW_BG + oldData[indexCounter][i] + COLOR_RESET, "|");
				} else {
					set.format("%2s %2s ", oldData[indexCounter][i], "|");
				}
				indexCounter++;
			}
			set.format("%2s %2s\n", partitionTaken[i], "|");
		}
		System.out.print(set);
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		header();

		int jobCounter = 0;
		int jobQuantity = 0;
		int partitionCounter = 0;
		int partitionQuantity = 0;
		float memory = 0;
		float availableMemory = 0;
		float osSize = 0;
		boolean validMemory = false;
		boolean validSize = false;
		boolean validPartition = false;
		boolean validJob = false;
		boolean flag = false;

		System.out.print("Enter memory capacity(M): ");

		do {
			try {
				do {
					Scanner input = new Scanner(System.in);
					memory = input.nextFloat();
					if (memory <= 0) {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.print("Enter memory capacity(M): ");
					}
				} while (memory <= 0);
				validMemory = true;
			} catch (InputMismatchException ex) {
				validMemory = false;
				System.out.println("Invalid input. Please enter a valid number.");
				Thread.sleep(1250);
				header();
				System.out.print("Enter memory capacity(M): ");
			}
		} while (!validMemory);

		System.out.print("Enter OS Size(M): ");

		do {
			try {
				do {
					Scanner input = new Scanner(System.in);
					osSize = input.nextFloat();
					if (osSize <= 0) {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.print("Enter OS Size(M): ");
					} else if (osSize >= memory) {
						System.out.println("Invalid input. The OS size should be less than memory.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.print("Enter OS Size(M): ");
					} else {
						validSize = true;
					}
				} while (memory <= 0);
			} catch (InputMismatchException ex) {
				validSize = false;
				System.out.println("Invalid input. Please enter a valid number.");
				Thread.sleep(1250);
				header();
				System.out.println("Enter memory capacity(M): " + memory);
				System.out.print("Enter OS Size(M): ");
			}
		} while (!validSize);

		availableMemory = memory;
		availableMemory -= osSize;

		// partition
		System.out.print("\nNo. of Partitions: ");

		do {
			try {
				do {
					Scanner input = new Scanner(System.in);
					partitionQuantity = input.nextInt();
					if (partitionQuantity <= 0) {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.println("Enter OS Size(M): " + osSize);
						System.out.print("\nNo. of Partitions: ");
					}
				} while (partitionQuantity <= 0);
				validPartition = true;
			} catch (InputMismatchException ex) {
				validPartition = false;
				System.out.println("Invalid input. Please enter a valid number.");
				Thread.sleep(1250);
				header();
				System.out.println("Enter memory capacity(M): " + memory);
				System.out.println("Enter OS Size(M): " + osSize);
				System.out.print("\nNo. of Partitions: ");
			}
		} while (!validPartition);

		float partitions[] = new float[partitionQuantity];
		// Partition Sizes
		int currentPartition = 1;
		System.out.println("Partition Sizes(M):");
		do {
			float number = 0;
			flag = false;
			System.out.print("Partition " + (partitionCounter + 1) + ": ");
			do {
				try {
					Scanner input = new Scanner(System.in);
					number = input.nextFloat();
					if (number > 0) {
						availableMemory -= number;
						if (availableMemory < 0) {
							availableMemory += number;
							System.out.println("Invalid input. The inputted partition size overlaps memory capacity.");
							Thread.sleep(1250);
							header();
							System.out.println("Enter memory capacity(M): " + memory);
							System.out.println("Enter OS Size(M): " + osSize);
							System.out.println("\nNo. of Partitions: " + partitionQuantity);
							partitionError(partitions, partitionQuantity, partitionCounter);
							flag = false;
						} else if (availableMemory < (partitionQuantity - currentPartition)) {
							availableMemory += number;
							System.out.println("Invalid input. This partition size leaves no space for other partitions.");
							Thread.sleep(1500);
							header();
							System.out.println("Enter memory capacity(M): " + memory);
							System.out.println("Enter OS Size(M): " + osSize);
							System.out.println("\nNo. of Partitions: " + partitionQuantity);
							partitionError(partitions, partitionQuantity, partitionCounter);
							flag = false;
						} else {
							flag = true;
						}
					} else {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.println("Enter OS Size(M): " + osSize);
						System.out.println("\nNo. of Partitions: " + partitionQuantity);
						partitionError(partitions, partitionQuantity, partitionCounter);
					}
				} catch (InputMismatchException ex) {
					flag = false;
					System.out.println("Invalid input. Please enter a valid number.");
					Thread.sleep(1250);
					header();
					System.out.println("Enter memory capacity(M): " + memory);
					System.out.println("Enter OS Size(M): " + osSize);
					System.out.println("\nNo. of Partitions: " + partitionQuantity);
					partitionError(partitions, partitionQuantity, partitionCounter);
				}
			} while (!flag);

			partitions[partitionCounter] = number;
			partitionCounter++;
			currentPartition++;
		} while (partitionCounter < partitionQuantity);

		partitionCounter--;
		// NO of jobs

		System.out.print("\nNo. of Jobs: ");

		do {
			try {
				do {
					Scanner input = new Scanner(System.in);
					jobQuantity = input.nextInt();
					if (jobQuantity <= 0) {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.println("Enter OS Size(M): " + osSize);
						System.out.println("\nNo. of Partitions: " + partitionQuantity);
						partitionError(partitions, partitionQuantity, partitionCounter);
						System.out.print("\nNo. of Jobs: ");
					}
				} while (jobQuantity <= 0);
				validJob = true;
			} catch (InputMismatchException ex) {
				validPartition = false;
				System.out.println("Invalid input. Please enter a valid number.");
				Thread.sleep(1250);
				header();
				System.out.println("Enter memory capacity(M): " + memory);
				System.out.println("Enter OS Size(M): " + osSize);
				System.out.println("\nNo. of Partitions: " + partitionQuantity);
				partitionError(partitions, partitionQuantity, partitionCounter);
				System.out.print("\nNo. of Jobs: ");
			}
		} while (!validJob);

		System.out.println("Job Sizes(M):");

		float jobs[] = new float[jobQuantity];

		// JOBS sizes

		do {
			float number = 0;
			flag = false;
			System.out.print("Input Job (" + (jobCounter + 1) + "/" + jobQuantity + "): ");
			do {
				try {
					Scanner input = new Scanner(System.in);
					number = input.nextFloat();
					if (number > 0) {
						flag = true;
					} else {
						System.out.println("Invalid input. Please enter a positive number.");
						Thread.sleep(1250);
						header();
						System.out.println("Enter memory capacity(M): " + memory);
						System.out.println("Enter OS Size(M): " + osSize);
						System.out.println("\nNo. of Partitions: " + partitionQuantity);
						partitionError(partitions, partitionQuantity, partitionCounter);
						System.out.println("\nNo. of Jobs: " + jobQuantity);
						jobsError(jobs, jobQuantity, jobCounter);
					}
				} catch (InputMismatchException ex) {
					flag = false;
					System.out.println("Invalid input. Please enter a valid number.");
					Thread.sleep(1250);
					header();
					System.out.println("Enter memory capacity(M): " + memory);
					System.out.println("Enter OS Size(M): " + osSize);
					System.out.println("\nNo. of Partitions: " + partitionQuantity);
					partitionError(partitions, partitionQuantity, partitionCounter);
					System.out.println("\nNo. of Jobs: " + jobQuantity);
					jobsError(jobs, jobQuantity, jobCounter);
				}
			} while (!flag);

			jobs[jobCounter] = number;
			jobCounter++;
		} while (jobCounter < jobQuantity);

		flag = false;
		// job list

		System.out.println("\nJob List:");
		for (int i = 0; i < jobQuantity; i++) {
			System.out.println("Job " + (i + 1) + ": " + jobs[i] + "M");
		}

		pressEnterToContinue();

		System.out.println(">Memory Structure");
		Formatter fmt = new Formatter();
		fmt.format("%9s   %1s \n", "Memory", "|Partition Size|");
		fmt.format("%1s%17s %6s  \n", "OS Partition|", BLUE_FG + osSize + COLOR_RESET, "|");
		for (int i = 0; i < partitionQuantity; i++) {
			fmt.format("%7s %5s %16s %6s \n", "P" + (i + 1), "|", PURPLE_FG + partitions[i] + COLOR_RESET, "|");
		}
		System.out.println(fmt);

		int currentSet = 0;
		int rank = 0;

		int jobAllocated[] = new int[jobQuantity];
		String failedJobs[] = new String[jobQuantity];
		int totalAllocations = 0;

		String allocationOrder[] = new String[partitionQuantity];
		String partitionTaken[] = new String[partitionQuantity];

		for (int i = 0; i < partitionQuantity; i++) {
			allocationOrder[i] = "";
			partitionTaken[i] = "";
		}

		pressEnterToContinue();

		System.out.println(">Allocation/Deallocation");

		String oldData[][] = new String[jobQuantity][partitionQuantity];
		String dataHighlight[][] = new String[jobQuantity][partitionQuantity];

    boolean maxSet = false;
    boolean validLoop = true;
		do {
			boolean validSet = false;
			rank = 0;
			for (int i = 0; i < partitionQuantity; i++) {
				if (partitionTaken[i] != "") {
					rank++;
				}
			}

			int index = 0;
			for (int i = 0; i < jobQuantity; i++) {
				if (jobAllocated[i] == 0) {
					for (int j = index; j < partitionQuantity; j++) {
						if (jobs[i] <= partitions[j] && partitionTaken[j] == "") {
							jobAllocated[i] = 1;
							allocationOrder[rank] = ("J" + String.valueOf(i + 1));
							partitionTaken[j] = ("J" + String.valueOf(i + 1));     
              index = j;
							rank++;
							break;
						}
					}
				}
				if (rank == partitionQuantity || index == 7) {
					break;
				}
			}

			for (int counter = 0; counter < partitionQuantity; counter++) {
				oldData[currentSet][counter] = partitionTaken[counter];
			}

			currentSet++;
			Formatter set = new Formatter();

			if (currentSet == 1) {
				set.format("%9s   %1s%1s\n", "Memory", "|Partition Size|", "Set 1|");
				set.format("%1s%16s %7s%3s %2s \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|");
				for (int i = 0; i < partitionQuantity; i++) {
					set.format("%7s %5s %15s %7s %2s %2s \n", "P" + (i + 1), "|",
							PURPLE_FG + partitions[i] + COLOR_RESET, "|", partitionTaken[i], "|");
				}
				System.out.println(set);
				pressEnterToContinue();
			}

			else if (currentSet == 2) {
				set.format("%9s   %1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|");
				set.format("%1s%16s %7s%3s %2s%3s %2s \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 3) {
				set.format("%9s   %1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|", "Set 3|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 4) {
				set.format("%9s   %1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|", "Set 3|",
						"Set 4|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 5) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|", "Set 3|",
						"Set 4|", "Set 5|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 6) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|", "Set 3|",
						"Set 4|", "Set 5|", "Set 6|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 7) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|",
						"Set 3|", "Set 4|", "Set 5|", "Set 6|", "Set 7|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|",
						"OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 8) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|",
						"Set 3|", "Set 4|", "Set 5|", "Set 6|", "Set 7|", "Set 8|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n", "OS Partition|",
						BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|",
						"OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 9) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|", "Set 2|",
						"Set 3|", "Set 4|", "Set 5|", "Set 6|", "Set 7|", "Set 8|", "Set 9|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n",
						"OS Partition|", BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS",
						"|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 10) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|",
						"Set 2|", "Set 3|", "Set 4|", "Set 5|", "Set 6|", "Set 7|", "Set 8|", "Set 9|", "Set 10|");
				set.format("%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n",
						"OS Partition|", BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS",
						"|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

			else if (currentSet == 11) {
				set.format("%9s   %1s%1s%1s%1s%1s%1s%1s%1s%1s%1s%1s%1s\n", "Memory", "|Partition Size|", "Set 1|",
						"Set 2|", "Set 3|", "Set 4|", "Set 5|", "Set 6|", "Set 7|", "Set 8|", "Set 9|", "Set 10|",
						"Set 11|");
				set.format(
						"%1s%16s %7s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s%3s %2s  \n",
						"OS Partition|", BLUE_FG + osSize + COLOR_RESET, "|", "OS", "|", "OS", "|", "OS", "|", "OS",
						"|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|", "OS", "|");
				System.out.print(set);
				newSet(dataHighlight, oldData, partitionTaken, partitions, partitionQuantity, currentSet);
				pressEnterToContinue();
			}

      if (!validLoop){
        maxSet = true; 
      }
      validLoop = false;
      
			int orderLimit = 0;
			int jobsOrder[] = new int[partitionQuantity];
			float deallocationQuery[] = new float[partitionQuantity];

			for (int i = 0; i < partitionQuantity; i++) {
				if (partitionTaken[i] != "") {
					String job = partitionTaken[i];
					job = job.substring(1);
					jobsOrder[i] = Integer.valueOf(job) - 1;
					deallocationQuery[i] = jobs[jobsOrder[i]];
					orderLimit++;
				}
			}

			Arrays.sort(deallocationQuery);
      descendSort(jobsOrder);

      int noValue = 0;
      for (int i = 0; i < partitionQuantity; i++){
        if (jobsOrder[i] == 0){
          if (i < (partitionQuantity - 1)){
            if (jobsOrder[i + 1] == 0){
              noValue++; 
            } 
          } else {
            break; 
          }
        }
      }

			for (int i = 0; i < partitionQuantity; i++) {
				for (int j = 0; j < orderLimit; j++) {
					int oldIndex = jobsOrder[j];
					if (deallocationQuery[i] == Float.valueOf(jobs[jobsOrder[j]])) {
						jobsOrder[j] = jobsOrder[i];
						jobsOrder[i] = oldIndex;
						break;
					}
				}
			}

			int firstTwo = 0;
			int currentIndex = noValue;
			do {
				String firstText = "";
				if (jobsOrder[currentIndex] == 0 && jobAllocated[0] == 1 && !flag) {
					firstText = "J" + String.valueOf(jobsOrder[currentIndex] + 1);
					flag = true;
					firstTwo++;
				} else if (jobsOrder[currentIndex] != 0) {
					firstText = "J" + String.valueOf(jobsOrder[currentIndex] + 1);
					firstTwo++;
				}

				if (firstText != "") {
					for (int j = 0; j < partitionQuantity; j++) {
						String secondText = partitionTaken[j];
						if (firstText != "" && secondText != "") {
							if (secondText.compareTo(firstText) == 0) {
								dataHighlight[currentSet][j] = "1";
								partitionTaken[j] = "";
								validSet = true;
								break;
							}
						}
					}
				}

				currentIndex++;
				if (currentIndex > (partitionQuantity - 1)) {
					break;
				}
			} while (firstTwo != 2);

			if (validSet) {
				totalAllocations++;
			}

      for (int i = 0; i < partitionQuantity; i++){
        if (partitionTaken[i] != ""){
          validLoop = true;
          break;
        }
      }
      
		} while (!maxSet);

		int failedQuantity = 0;

		for (int i = 0; i < jobQuantity; i++) {
			if (jobAllocated[i] == 0) {
				failedJobs[failedQuantity] = "J" + String.valueOf(i + 1);
				failedQuantity++;
			}
		}

		if (failedQuantity == 0) {
			System.out.println("\nConclusion:\n\tAll jobs were executed.\n\tThere are total of " + totalAllocations + " set allocations.");
		} else {
			System.out.print("\nConclusion:\n\tNot all jobs were exceuted. ");
			for (int i = 0; i < failedQuantity; i++) {
				if (i == 0 && failedQuantity != 2) {
					System.out.print(failedJobs[i]);
				} else if (failedQuantity == 2) {
					System.out.print(failedJobs[i] + " and " + failedJobs[i + 1]);
					break;
				} else if (i != 0 && i == (failedQuantity - 1)) {
					System.out.print(" and " + failedJobs[i]);
					break;
				} else {
					System.out.print(", " + failedJobs[i]);
				}

			}
			System.out.println(" failed to load.");
			if (totalAllocations > 0) {
				System.out.println("\tThere are only " + totalAllocations + " sets of allocations.");
			} else {
				System.out.println("\tThere are no sets of allocations.");
			}
		}
	}
}