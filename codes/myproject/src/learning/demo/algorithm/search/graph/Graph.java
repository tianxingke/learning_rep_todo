package learning.demo.algorithm.search.graph;

import java.io.InputStream;

/**
 * 无向图
 * 
 * @author tianxingke
 * 
 */
public class Graph {
	/**
	 * 创建一个含有V个顶点但不含有边的图
	 * 
	 * @param V
	 */
	public Graph(int V) {
	}

	/**
	 * 从标准输入流in读入一幅图
	 * 
	 * @param in
	 */
	public Graph(InputStream in) {

	}
	
	/**
	 * 顶点数
	 * @return
	 */
	int V() {
		return 0;
	}
	/**
	 * 边数
	 * @return
	 */
	int E(){
		return 0;
	}

	/**
	 * 向图中添加一条边v-w
	 * @param v
	 * @param w
	 */
	void addEdge(int v,int w){
		
	}
	
	/**
	 * 和v相邻的所有顶点
	 * @param v
	 * @return
	 */
	Iterable<Integer> adj(int v){
		return null;
	}
	
	/**
	 * 对象的字符串表示
	 */
	public String toString(){
		return null;
	}
}
