import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Inversion {

    private static long[] arr = new long[100000];
    private static long size = 0;

    public static long[] mergeSort(long []arr){
      if(arr.length == 1)
        return arr;

      int mid = arr.length/2;
      long[] arr1 = new long[mid];
      long[] arr2 = new long[arr.length-mid];

      System.arraycopy(arr, 0, arr1, 0, arr1.length);
      System.arraycopy(arr, mid, arr2, 0, arr2.length);

      arr1 = mergeSort(arr1);
      arr2 = mergeSort(arr2);

      return merge(arr1,arr2);
    }

    private static long[] merge(long[] arr1,long[] arr2){
      int i,j ;
      long []result = new long[arr1.length + arr2.length];
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


        //Inversion iversion = new Inversion();
//	    	iversion.display(sa);
//
//	    	iversion.display(Inversion.mergeSort(sa));
//
        //int i = iversion.inverse(sa);

        Inversion.readTxtFile("integer.txt");
        long count = Inversion.inverse(Inversion.getArr());
        System.out.println(count);
      }

    public static long inverse(long[] arr){
      if(arr.length == 1)
        return 0;

      long[] arr1 = new long[arr.length/2];
      long[] arr2 = new long[arr.length - arr.length/2];

      System.arraycopy(arr, 0, arr1, 0, arr1.length);
      System.arraycopy(arr, arr1.length, arr2, 0, arr2.length);

      long left = inverse(arr1);
      long right = inverse(arr2);
      arr1 = mergeSort(arr1);
      arr2 = mergeSort(arr2);

      long whole = inverseSplit(arr1,arr2);

      return left + right + whole;
    }

    private static long inverseSplit(long[] arr1,long[] arr2){
      int i,j;
      long count;
      i = 0;
      j = 0;
      count = 0;
      while( i< arr1.length && j < arr2.length){

        if(arr1[i] <= arr2[j]){
          i++;
          j = 0;
        }
        else if(arr1[i] > arr2[j]){
          j++;
          count++;
          if(j >= arr2.length){
            i++;
            j = 0;
          }
        }



      }


     return count;
    }

    public static void readTxtFile(String filePath){
          try {
                  String encoding="UTF-8";
                  File file=new File(filePath);
                  if(file.isFile() && file.exists()){ //判断文件是否存在
                      InputStreamReader read = new InputStreamReader(
                      new FileInputStream(file),encoding);//考虑到编码格式
                      BufferedReader bufferedReader = new BufferedReader(read);
                      String lineTxt = null;
                      int i = 0;
                      while((lineTxt = bufferedReader.readLine()) != null){
                          arr[i] = Integer.parseInt(lineTxt);
                          i++;
                      }
                      size = i;
                      read.close();
          }else{
              System.out.println("找不到指定的文件");
          }
          } catch (Exception e) {
              System.out.println("读取文件内容出错");
              e.printStackTrace();
          }

      }

  public static long[] getArr() {
    return arr;
  }

  public static void setArr(long[] arr) {
    Inversion.arr = arr;
  }

  public static long getSize() {
    return size;
  }

  public static void setSize(long size) {
    Inversion.size = size;
  }


}
