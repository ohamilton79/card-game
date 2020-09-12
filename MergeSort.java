package sample;

public class MergeSort {

    public static int[] getScoreList(String[] list) {
        int[] scoreList = new int[list.length];
        //Counter variable
        int count = 0;
        for (String entry : list) {
            int commaIndex = entry.indexOf(",");
            int playerScore = Integer.parseInt(entry.substring(commaIndex+1));
            scoreList[count] = playerScore;
            //Increment counter
            count++;
        }
        return scoreList;
    }

    public static String[] getNameList(String[] list) {
        String[] nameList = new String[list.length];
        //Counter variable
        int count = 0;
        for (String entry : list) {
            int commaIndex = entry.indexOf(",");
            String playerName = entry.substring(0, commaIndex);
            nameList[count] = playerName;
            //Increment counter
            count++;
        }
        return nameList;
    }

    public static int checkIndex(int playerScore, int[] sortedList, int[] scoreList, String[] nameList) {

        int sortedIndex = 0;
        //Find its index in the sorted list
        sortedIndex = 0;
        int sortedCount = 0;
        for (int sortedScore : sortedList) {
            if (sortedScore == playerScore) {
                sortedIndex = sortedCount;
                break;
            }
            //Increment counter
            sortedCount++;
        }
        return sortedIndex;
    }

    public static String[] getSortedNames(int[] sortedList, int[] scoreList1, int[] scoreList2, String[] nameList1, String[] nameList2) {
        //New list for corresponding 'sorted names', with the same length as the sorted items list
        String[] sortedNames = new String[sortedList.length];
        //Re-match the names to the new sorted scores
        int unsortedCount = 0;

        for (int playerScore: scoreList1) {
            int sortedIndex = checkIndex(playerScore, sortedList, scoreList1, nameList1);
            //This index is the new index of the corresponding name, in its own sorted list
            sortedNames[sortedIndex] = nameList1[unsortedCount];
            unsortedCount++;
        }
        unsortedCount = 0;
        for (int playerScore: scoreList2) {
            int sortedIndex = checkIndex(playerScore, sortedList, scoreList2, nameList2);
            //This index is the new index of the corresponding name, in its own sorted list
            sortedNames[sortedIndex] = nameList2[unsortedCount];
            unsortedCount++;
        }

        return sortedNames;
    }

    public static int[] mergeLists(int[] leftList, int[] rightList) {

        //Get the number of new items
        int newItems = leftList.length + rightList.length;
        int[] newList = new int[newItems];

        //Counters
        int leftPointer = 0;
        int rightPointer = 0;
        int count = 0;

        while (leftPointer < leftList.length || rightPointer < rightList.length) {
            //One pointer outside bounds of list - write other values
            if (leftPointer >= leftList.length) {
                //Write the right list's values to the new list
                newList[count] = rightList[rightPointer];
                rightPointer++;
                //Continue to next loop so there is no more comparing and resulting index errors (but also increment the counter)
                count++;
                continue;
            }

            if (rightPointer >= rightList.length) {
                //Write the left list's values to the new list
                newList[count] = leftList[leftPointer];
                leftPointer++;
                //Continue to next loop so there is no more comparing and resulting index errors (but also increment the counter)
                count++;
                continue;
            }

            //Comparisons between left and right list items
            if (leftList[leftPointer] > rightList[rightPointer]) {
                newList[count] = leftList[leftPointer];
                leftPointer++;

            } else {
                newList[count] = rightList[rightPointer];
                rightPointer++;
            }

            //Increment the counter
            count++;
        }

        //When finished, return the new (sorted) list
        return newList;
    }

}
