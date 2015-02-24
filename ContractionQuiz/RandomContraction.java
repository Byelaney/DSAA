import graph.Edge;
import graph.Vertice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class RandomContraction {
	private static ArrayList<Vertice> vertices = new ArrayList<Vertice>();
	private static ArrayList<Edge> alledges = new ArrayList<Edge>();
	
	public static int displayRes(ArrayList<Vertice> v,ArrayList<Edge> e){
		Vertice v1 = v.get(0);
		Vertice v2 = v.get(1);
		
		int nums = 0;
		for(int i =0;i<e.size();i++){
			if(e.get(i).getEndpoint1() == v1.getValue()){
				if(e.get(i).getEndpoint2() == v2.getValue()){
					nums++;
				}
			}
			else if(e.get(i).getEndpoint2() == v1.getValue()){
				if(e.get(i).getEndpoint1() == v2.getValue()){
					nums++;
				}
			}	
		}
		
		return nums;
	}
	
	public static void main(String []args){
		//int min_edge = 10000;
		int min_num = 10000;
		for(int i = 0;i<50;i++){
			RandomContraction.readTxtFile("kargerMinCut.txt");
			//System.out.println("there are " + alledges.size() + " edges");
			//System.out.println("there are " + vertices.size() + " vertices");
			
			randContraction(vertices,alledges);
//			if(alledges.size() < min_edge)
//				min_edge = alledges.size();
			//System.out.println("there are " + alledges.size() + " edges");
			//System.out.println("there are " + vertices.size() + " vertices");
			
			int v1 = vertices.get(0).getValue();
			int v2 = vertices.get(1).getValue();
			Edge e = new Edge(v1,v2);
			int num = 0;
			for(int j = 0;j<alledges.size();j++){
				if(alledges.get(j).compare(e))
					num++;
			}
			if(num < min_num && num > 1)
				min_num = num;
						
			vertices = new ArrayList<Vertice>();
			alledges = new ArrayList<Edge>();
		}
		//System.out.println("min_edge is " + min_edge);
		System.out.println("min_num is " + min_num);
		
	}
	
	public static Edge randPick(ArrayList<Edge> alledge){
		Random random = new Random();
		int pick_idx = random.nextInt(alledge.size());
		Edge e = alledge.get(pick_idx);
		return e;
	}
	
	
	public static void randContraction(ArrayList<Vertice> v, ArrayList<Edge> egs){
		while(v.size() > 2){
			Edge e = PickOne(egs);
			Merge(v,egs,e);
			
			//finally remove self loop
			for(int i = 0;i < egs.size();i++){
				if(egs.get(i).getEndpoint1() == egs.get(i).getEndpoint2())
					egs.remove(i);	
			}
			
		}
		
		
	}
	
	public static void Merge(ArrayList<Vertice> v, ArrayList<Edge> egs,Edge e){
		int vertice_1 = e.getEndpoint1();
		int vertice_2 = e.getEndpoint2();
		
		//first remove vertice_2 from v
		for(int i = 0;i<v.size();i++){
			if(v.get(i).getValue() == vertice_2){
				v.remove(i);
				break;
			}	
		}
			
		//second delete e from egs
		for(int i = 0;i<egs.size();i++){
			if(egs.get(i).compare(e)){
				egs.remove(i);
				break;
			}
		}
		
		//finally change all vertice_2 pos to vertice_1
		for(int i = 0;i<egs.size();i++){
			if(egs.get(i).getEndpoint1() == vertice_2){
				egs.get(i).setEndpoint1(vertice_1);
			}
			else if(egs.get(i).getEndpoint2() == vertice_2){
				egs.get(i).setEndpoint2(vertice_1);
			}
			else{
				//do nothing
			}
			
		}
		
		
	}
	
	
	public static Edge PickOne(ArrayList<Edge> egs){
		Random random = new Random();
		int idx = random.nextInt(egs.size());
		
		return egs.get(idx);
	}
	
	
	/**
	 * read data into vertices and alledges
	 * @param filePath
	 */
	public static void readTxtFile(String filePath){
        try {
                String encoding="UTF-8";
                File file=new File(filePath);
                if(file.isFile() && file.exists()){ //判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
       
                    int line=0;
                    while((lineTxt = bufferedReader.readLine()) != null){
                    	//System.out.println(lineTxt);
                    	String[] ss = lineTxt.split("\\s");
                    	ArrayList<Edge> edges = new ArrayList<Edge>();
                    	for(int i = 1;i<ss.length;i++){
                    		Edge e = new Edge(line+1,Integer.parseInt(ss[i]));
                    		addtoEdge(e);
                    		edges.add(e);
                    	}
                    	vertices.add(new Vertice(line+1));
                    	line++;
                    }
                    read.close();
                
        }else{
            System.out.println("找不到指定的文件");
        }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
     
    }
	
	
	/**
	 * add to alledges and filter same edge
	 * @param e
	 */
	public static void addtoEdge(Edge e){
		for(int i =0;i<alledges.size();i++){
			if(alledges.get(i).compare(e)){
				return;
			}
		}
		alledges.add(e);
		
	}
	
	
}
