package sample;

public class FruitClusterList {
    public FruitClusterNode head;
    public FruitClusterNode tail;

    public void addFruitCluster(FruitClusterNode fruitClusterNode) {
        if(tail == null){
            head = fruitClusterNode;
            tail = fruitClusterNode;
        }else{
            tail.nextNode = fruitClusterNode;
            //FruitCluster temp = fruitClusterNode;
            fruitClusterNode.preNode = tail;
            tail = fruitClusterNode;
        }
    }
    //Delete clusters with outliers less than 5
    public int getLength() {
        int count = 0;
        if (head != null) {
            FruitClusterNode temp = head;
            while (temp != null) {
                count++;
                //System.out.println("fruitClusterList" + count + "\n");
                temp = temp.getNextNode();
            }
            return count;
        } else {
            return 0;
        }
    }
    public FruitClusterNode getHead(){
        return head;
    }
    public FruitClusterNode getTail(){
        return tail;
    }
    public void removeFruitCluster(int minOutlierValue){
        FruitClusterNode temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            //System.out.println(temp.getPixelsNum());
            if (count == 1) {
                if (temp.getValue().getPixelsNum() <= minOutlierValue) {
                    temp = temp.getNextNode();
                    head = temp;
                } else {
                    temp = temp.getNextNode();
                }
            } else {
                if (temp.getValue().getPixelsNum() <= minOutlierValue) {
                    temp.preNode.nextNode = temp.nextNode;
                    if (temp.nextNode != null) {
                        temp.nextNode.preNode = temp.preNode;
                    }
                    temp = temp.getNextNode();
                } else {
                    temp = temp.getNextNode();
                }
            }
        }
    }
    // bubble sorting of a linked list
    public void sortFruitClusterByPixelsNum() {
        FruitClusterNode p = null;
        System.out.println("sortFruitClusterByPixelsNum");
        Boolean isChange = true;
        int count = 0;
        while (p != head.nextNode && isChange) {
            isChange = false;
            count++;
            FruitClusterNode q = head;
            for(; q.getNextNode() !=null && !q.getNextNode().isEqual(p); q = q.getNextNode()) {
                if (q.getValue().getPixelsNum() > q.getNextNode().getValue().getPixelsNum()) {
                    FruitCluster tp1 = q.getValue();
                    q.setValue(q.getNextNode().getValue());
                    q.getNextNode().setValue(tp1);
                    isChange = true;
                }
            }
            p = q;
        }
    }
}
