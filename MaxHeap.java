class Main {
  public static void main(String[] args) {
    int arr[] = {4,6,1,3,9,20,0};
    MaxHeap heap = new MaxHeap(arr.length);
    for(int i =0; i<arr.length; i++){
      heap.insert(arr[i]);
    }
    heap.printHeap();
  }
}

class MaxHeap{
  public int[] arr;
  public int count;
  public int capacity;

  public MaxHeap(int capacity){
    this.count = 0;
    this.capacity = capacity;
    this.arr = new int[capacity];
  }

  public void printHeap(){
    for(int i =0; i<this.count; i++){
      System.out.println(arr[i]);
    }
  }

  public void insert(int data){
    if(this.count == this.capacity){
      resizeHeap();
    }
    this.arr[this.count] = data;
    int newItemIndex = this.count;
    this.count++;
    int parentIndex = getParentIndex(newItemIndex);

    while(this.arr[newItemIndex]>this.arr[parentIndex] && newItemIndex !=0){
        int temp = this.arr[newItemIndex];
        this.arr[newItemIndex] = this.arr[parentIndex];
        this.arr[parentIndex] = temp;
        
        newItemIndex = parentIndex;
        parentIndex = getParentIndex(newItemIndex);
    }
    
  }

  private void resizeHeap(){
    int[] old_arr = this.arr;
    System.arraycopy(this.arr, 0, old_arr, 0, this.count-1);
    this.arr = new int[this.capacity*2];
    if(this.arr==null){
      System.out.println("Error");
      return;
    }
    for(int i =0; i< this.capacity; i++){
      this.arr[i] = old_arr[i];
    }
    this.capacity*=2;
    old_arr=null;
  }

  public int getParentIndex(int i){
    if(i<0 || i>this.count){
      return -1;
    }

    return (i-1)/2;
  }

  private int LeftChild(int i){
    int left = 2*i+1;
    if(left >= this.count){
      return -1;
    }
    return left;
  }

  private int RightChild(int i){
    int right = 2*i+2;
    if(right >= this.count){
      return -1;
    }
    return right;
  }

  public int GetMaximum(){
    if(this.count==0){
      return -1;
    }
    return this.arr[0];
  }

  public int DeleteMax(){
    if(this.count==0){
      return -1;
    }

    int data = this.arr[0];
    arr[0] = this.arr[this.arr.length-1];
    this.count--;
    heapify(0);
    return data;
  }

  private void heapify(int i){
    int l,r, max, temp;
    l=LeftChild(i);
    r=RightChild(i);
    if(l != -1 && this.arr[l] > this.arr[i]){
      max=l;
    }else{
      max=i;
    }

    if(r!=-1 && this.arr[r] > this.arr[max]){
      max=r;
    }

    if(max!=i){
      temp = this.arr[i];
      this.arr[i] = this.arr[max];
      this.arr[max] = temp;
      heapify(max);
    }

  }
}