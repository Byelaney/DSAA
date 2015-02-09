import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class QuickSort {
	
	private static int[] arr = new int[10000];
	private static int size = 0;
	private static long compares = 0;
	
	public static void main(String []args){
		
		
		QuickSort.readTxtFile("QuickSort.txt");
		
		QuickSort.Sort(arr, 0, size-1);
		
		System.out.println(compares);
		
//		QuickSort a = new QuickSort();
//		a.test();
		
		
	}
	
	public static void Sort(int[] arr,int l,int r){
		if(l >= r){
			return ;
		}
				
		int pivlot = ChoosePivot(arr,l,r);
		//System.out.println("pivlot is " + pivlot);
		//display(arr,l,r);
		int p = Partition3(arr,l,r);
		//System.out.print("p is "+ p + "----");
		
		//System.out.println("p is " + p);
		
		Sort(arr,l,p-1);
		compares += p-1 - l + 1;
		Sort(arr,p+1,r);
		compares += r - p;
		
	}
	
	
	public static void test(){
		int []arr = {3,9,8,4,6,10,2,5,7,1};
		int p = ChoosePivot(arr,0,9);
		System.out.println(p);
	}
	
	private static int Partition1(int[] arr,int l,int r){
		int p = arr[l];
		int i = l + 1;
		int j = l + 1;
		while(j <= r){
			if(arr[j] < p){
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
				i++;
			}
			j++;
		}
		
		int tmp = arr[l];
		arr[l] = arr[i-1];
		arr[i-1] = tmp;
		
		return i-1;
	}
	
	private static int Partition2(int[] arr,int l,int r){
		int p = arr[r];
		int temp = arr[l];
		arr[l] = p;
		arr[r] = temp;
		
		int i = l + 1;
		int j = l + 1;
		while(j <= r){
			if(arr[j] < p){
				int tmp = arr[j];
				arr[j] = arr[i];
				arr[i] = tmp;
				i++;
			}
			j++;
		}
		
		int tmp = arr[l];
		arr[l] = arr[i-1];
		arr[i-1] = tmp;
		
		return i-1;
	}
	
	private static void display(int []arr,int l,int r){
		for(int i = l;i<=r;i++){
			System.out.print(arr[i] + "   ");
		}
		System.out.println();
	}
	
	private static int Partition3(int[] arr,int l,int r){
		int p = arr[l];
		int i = l+1;
		int j = l+1;
		while(j <= r){
			if(arr[j] < p){
				swap(arr,i, j);
				i++;
			}
			j++;
		}
		
		swap(arr,i-1, l);
		
		return i-1;
	}
	
	private static int ChoosePivot(int []arr,int l,int r){
		int center = (l + r) / 2;
		int result = getMiddle(arr[center],arr[l],arr[r]);
		int result_idx = 0;
		
		if(arr[center] == result)
			result_idx = center;
		else if(arr[l] == result)
			result_idx = l;
		else
			result_idx = r;
		
		//System.out.println("result is "+ result);
		
	    swap(arr,l, result_idx);
//	    System.out.println("arr[l] is "+ arr[l]);
//	    System.out.println("arr[result] is "+ arr[result]);
//		
	    
	    return arr[l]; // return median value
	}
	
	 private static void swap(int []arr,int dex1, int dex2) {
		    int temp = arr[dex1];
		    arr[dex1] = arr[dex2];
		    arr[dex2] = temp;
		  }
	
	 
	 private static int getMiddle(int a,int b,int c){
		 return (a < b ? (b < c ? b : a < c ? c : a) : (b > c ? b : a > c ? c  
	                : a)); 
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
                    //System.out.println(size);
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
}
