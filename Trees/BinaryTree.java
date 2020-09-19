class Main {
  public static void main(String[] args) {
    int[] data = {5,2,6,7,10,12};
    BinaryTree tree = new BinaryTree();
    for(int i =0; i< data.length; i++){
      tree.insertElement(data[i]);
    }

    tree.printInOrder(tree.getRoot());
  }
}

class BinaryTreeNode {
  private int data;
  private BinaryTreeNode left, right;

  public BinaryTreeNode(){
      left = null;
      right = null;
  }

   public BinaryTreeNode(int data){
      left = null;
      right = null;
      this.data = data;
  }

  public void setData(int data){
    this.data = data;
  }

  public int getData(){
    return this.data;
  }

  public void setLeft(BinaryTreeNode left){
    this.left = left;
  }

  public BinaryTreeNode getLeft(){
    return this.left;
  }

  public void setRight(BinaryTreeNode right){
    this.right = right;
  }

  public BinaryTreeNode getRight(){
    return this.right;
  }
}

class BinaryTree{
  BinaryTreeNode root;
  public void printPreOrder(){
    root = new BinaryTreeNode();
  }

  public BinaryTreeNode getRoot(){
    return this.root;
  }

  public void printInOrder(BinaryTreeNode node){
    if(node != null){
      printInOrder(node.getLeft());
      System.out.println(node.getData());
      printInOrder(node.getRight());
    }
  }

  public void printPostOrder(){

  }

  public void insertElement(int data){
    if(this.root == null){
      this.root = new BinaryTreeNode(data);
    }else{
      insertHelper(root, data);
    }
  }

  private void insertHelper(BinaryTreeNode root, int data){
      if(root==null)
        return;
    
      if(data < root.getData()){
        if(root.getLeft() == null){
          root.setLeft(new BinaryTreeNode(data));
        }else{
          insertHelper(root.getLeft(), data);
        }
      }else if(data > root.getData()){
        if(root.getRight() == null){
          root.setRight(new BinaryTreeNode(data));
        }else{
          insertHelper(root.getRight(), data);
        }
      }
  }


  private BinaryTreeNode preOrder(BinaryTreeNode node){
      
      

      return node;
  }

  public void deleteElement(){
      
  }

  public void findElement(){

  }
}