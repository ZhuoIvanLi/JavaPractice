package Sort;

/**
 * 10.7 and 10.8: Search for missing or duplicated int in an large array, but only give a certain amount of memory to do
 * it.
 * For example: use 1 GB memory to find missing integer in 4 billion non-negative integers
 * Or: find duplicates in an array of 32000 integers by using 4 kilobyte memory
 *
 * solution: hash each integer and store 1 or 0 as bit in byte or int array
 */
public class SearchMissingOrDuplicates {
    public void searchMissing(int[] arr) {
        long numberOfInt = ((long)Integer.MAX_VALUE) + 1; // about 2 billion integer

        // 1 byte = 8 bits and one bit can represent one integer, so each byte can store 8 integer
        byte[] byteSet = new byte[(int) (numberOfInt / 8)];

        // store every integer in byte set
        for(int x : arr){
            int index = x / 8;
            int bitNumber = x % 8;

            if((byteSet[index] & (1 << bitNumber)) != 0) { // Find the duplicated integer
                System.out.println("Duplicated integer: " + x);
            }else{
                // Add integer in byte set
                byteSet[index] |= 1 << bitNumber;
            }
        }

        // Find the missing integer
        for(int i=0; i < byteSet.length; i++){
            for(int j=0; j < 8; j++){
                if((byteSet[i] & (1 << j)) == 0){ // Missing this integer, so print it out
                    System.out.println(i * 8 + j);
                    return;
                }
            }
        }


    }

}
