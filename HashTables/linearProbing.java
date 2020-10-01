import java.util.*;

class Main {
  public static void main(String[] args) {
    Map<String, Integer>map = new Map<>(); 
    map.add("this",1 ); 
    map.add("coder",2 ); 
    map.add("this",4 ); 
    map.add("hi",5 ); 
    System.out.println(map.size()); 
    System.out.println(map.remove("this")); 
    System.out.println(map.remove("this")); 
    System.out.println(map.size()); 
    System.out.println(map.isEmpty());
  }
}

class HashItem<K,V>{
  K key;
  V value;

  public HashItem(K key, V value){
    this.key = key;
    this.value = value;
  }
}

class Map<K, V>{
  private ArrayList<HashItem<K,V>> bucketArray;
  private int numBuckets;
  private int size;

  public Map(){
    bucketArray = new ArrayList<>();
    numBuckets=10;
    size=0;

    for(int i =0; i<numBuckets; i++){
      bucketArray.add(null);
    }
  }

  public int size(){return size;}
  public boolean isEmpty() {return size() ==0;}

  private int getBucketIndex(K key){
    int hashCode = key.hashCode();
    int index = hashCode % numBuckets;
    return index;
  }

  public V remove(K key){
    int hashIndex = getBucketIndex(key);
    int ct =0;
    while(this.bucketArray.get(hashIndex) !=null || ct<=this.numBuckets){
      HashItem<K,V> item = this.bucketArray.get(hashIndex);
      if(item!=null && item.key==key){
        HashItem<K,V> temp = this.bucketArray.get(hashIndex);
        this.bucketArray.set(hashIndex,null);
        size--;
        return temp.value;
      }
      hashIndex = (hashIndex+1) % this.numBuckets;
      ct++;
    }
    return null;
  }

  public V get(K key){
    int hashIndex = getBucketIndex(key);
    int ct =0;
    while(this.bucketArray.get(hashIndex) !=null || ct<=this.numBuckets){
      HashItem<K,V> item = this.bucketArray.get(hashIndex);
      if(item!=null && item.key==key){
        return this.bucketArray.get(hashIndex).value;
      }
      hashIndex = (hashIndex+1) % this.numBuckets;
      ct++;
    }
    return null;
  }

  public void add(K key, V value){
    int bucketIndex = getBucketIndex(key);
    HashItem<K,V> item = bucketArray.get(bucketIndex);
    HashItem<K,V> newItem = new HashItem(key, value);
    if(item==null){
      bucketArray.add(bucketIndex, newItem);
    }else{
      int i = (bucketIndex +1) % this.numBuckets;

      while(bucketArray.get(i) != null){
        i = (i +1) % this.numBuckets;
      }
      bucketArray.add(i, newItem);
    }

    size++;

    if(this.size==this.numBuckets){
      for(int i =0; i<numBuckets; i++){
        this.bucketArray.add(null);
      }
      this.numBuckets *=2;
    }
  }
}
