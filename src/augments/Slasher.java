package augments;

public class Slasher<T extends Keyable> {
	
	private Keyable[] table;
	private boolean[] valid;
			
	public Slasher(int size){
		table=new Keyable[size];
		valid=new boolean[size];
	}
	
	public boolean add(T item){
		int hash=item.key()%valid.length;
		if(!valid[hash]){
			valid[hash]=true;
			table[hash]=item;
			return true;
		}
		for(int i=1;i<valid.length;i++){
			if(valid[(hash+i)%valid.length]){
				valid[(hash+i)%valid.length]=true;
				table[(hash+i)%valid.length]=item;
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public T sling(int key){
		int hash=key%valid.length;
		if(!valid[hash]){
			return null;
		}
		if(table[hash].key()==key)
			return (T)(table[hash]);
		for(int i=1;i<valid.length;i++){
			if(valid[(hash+i)%valid.length]){
				if(table[(hash+i)%valid.length].key()==key){
					return (T)(table[(hash+i)%valid.length]);
				}	
			}else
				return null;
		}
		return null;
	}
	
	
	
}
