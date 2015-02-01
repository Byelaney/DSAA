public class MergeSort {

	  public static int[] mergeSort(int []arr){
		  if(arr.length == 1)
			  return arr;

		  int mid = arr.length/2;
		  int[] arr1 = new int[mid];
		  int[] arr2 = new int[arr.length-mid];

		  System.arraycopy(arr, 0, arr1, 0, arr1.length);
		  System.arraycopy(arr, mid, arr2, 0, arr2.length);

		  arr1 = mergeSort(arr1);
		  arr2 = mergeSort(arr2);

		  return merge(arr1,arr2);
	  }

	  private static int[] merge(int[] arr1,int[] arr2){
		  int i,j ;
		  int []result = new int[arr1.length + arr2.length];
		  i = j = 0;
		  int k = 0;
		  while(i < arr1.length && j < arr2.length){

			  if(arr1[i] <= arr2[j]){
				  result[k] = arr1[i];
				  k++;
				  i++;
			  }
			  else if(arr1[i] > arr2[j]){
				  result[k] = arr2[j];
				  k++;
				  j++;
			  }
		  }

		  while(i<arr1.length){
			  result[k] = arr1[i];
			  k++;
			  i++;
		  }

		  while(j<arr2.length){
			  result[k] = arr2[j];
			  k++;
			  j++;
		  }


		  return result;
	  }


	  	public void display(int[] arr){
	  		for(int i =0;i<arr.length;i++){
	  			System.out.print(arr[i] + "   ");
	  		}
	  		System.out.println();
	  	}


	    public static void main(String argv[]){
	    	int[] sa = {1,4,56,12,32,12,4,2,1,2};
				MergeSort iversion = new MergeSort();
	    	iversion.display(sa);

	    	iversion.display(MergeSort.mergeSort(sa));

	    }
}
