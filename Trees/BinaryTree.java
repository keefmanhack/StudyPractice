import java.util.LinkedList;
import java.util.Queue;

class Main {
  public static void main(String[] args) {
    int[] data = {5,2,6,7,10,12};
    BinaryTree tree = new BinaryTree();
    for(int i =0; i< data.length; i++){
      tree.insertElement(data[i]);
    }
    System.out.println("Height: " + tree.findHeight(tree.getRoot()));
    System.out.println("Size: " + tree.findSize(tree.getRoot()));
    tree.deleteElement(7);
    System.out.println("Size: " + tree.findSize(tree.getRoot()));
    System.out.println("Height: " + tree.findHeight(tree.getRoot()));
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
  

  public BinaryTreeNode getRoot(){
    return this.root;
  }

  public int findHeight(BinaryTreeNode node){
    if(node == null){
      return 0;
    }
    int leftCt = findHeight(node.getLeft());
    int rightCt = findHeight(node.getRight());
    return leftCt > rightCt ? leftCt +1 : rightCt +1;
  }

  public int findSize(BinaryTreeNode node){
    int leftCt = node.getLeft() == null ? 0: findSize(root.getLeft());
    int rightCt = node.getRight() == null ? 0: findSize(node.getRight());

    return 1 + leftCt + rightCt;
  }

  public void printInOrder(BinaryTreeNode node){
    if(node != null){
      printInOrder(node.getLeft());
      System.out.println(node.getData());
      printInOrder(node.getRight());
    }
  }

  public void printPostOrder(BinaryTreeNode node){
    if(node!=null){
      printPostOrder(node.getLeft());
      printPostOrder(node.getRight());
      System.out.println(node.getData());
    }
  }

  public void printPreOrder(BinaryTreeNode node){
    System.out.println(node.getData());
    printPreOrder(node.getLeft());
    printPreOrder(node.getRight());
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


  public void deleteElement(int data){
      if(root ==null){
        return;
      }

      if(this.root.getLeft() == null && this.root.getRight() == null){
        if(root.getData() == data){
          return;
        }else{
          return;
        }
      }

      Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
      q.add(this.root);
      BinaryTreeNode temp = new BinaryTreeNode(), dataNode = new BinaryTreeNode();

      while(!q.isEmpty()){
        temp = q.peek();
        q.remove();

        if(temp.getData() == data){
            dataNode = temp;
        }

        if(temp.getLeft() != null){
          q.add(temp.getLeft());
        }

        if(temp.getRight() != null){
          q.add(temp.getRight());
        }
      }

      if(dataNode != null){

        int x = temp.getData();
        deleteDeepestNode(this.root, temp);
        dataNode.setData(x);
      }
  }

  private void deleteDeepestNode(BinaryTreeNode root, BinaryTreeNode delNode){
    Queue<BinaryTreeNode> q = new LinkedList<BinaryTreeNode>();
    q.add(root);
    BinaryTreeNode temp = null;

    while(!q.isEmpty()){
      temp = q.peek();
      q.remove();

      if(temp == delNode){
        temp = null;
        return;
      }

      if(temp.getRight() != null){
        if(temp.getRight() == delNode){
          temp.setRight(null);
          return;
        }else{
          q.add(temp.getRight());
        }
      }

      if(temp.getLeft() != null){
        if(temp.getLeft() == delNode){
          temp.setLeft(null);
          return;
        }else{
          q.add(temp.getLeft());
        }
      }
    }
  }

  public BinaryTreeNode findElement(int data){
      return findHelper(this.root, data);
  }

  private BinaryTreeNode findHelper(BinaryTreeNode node, int data){
    if (node == null){
      return node;
    }

    if(node.getData() == data){
      return node;
    }

    BinaryTreeNode leftNode = findHelper(node.getLeft(), data);
    if(leftNode!= null && leftNode.getData() == data){
      return leftNode;
    }

    BinaryTreeNode rightNode = findHelper(node.getRight(), data);

    return rightNode;
  }

}
