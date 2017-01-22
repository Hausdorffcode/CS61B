public class SNode{
	public Integer val;
	public SNode prev;
	
	public SNode(Integer v, SNode p){
		val = v;
		prev = p;
	}
	public SNode (Integer v){
		this(v, null);
	}
}