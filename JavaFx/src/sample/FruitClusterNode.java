package sample;

public class FruitClusterNode {
    FruitCluster fruitCluster;
    FruitClusterNode nextNode;
    FruitClusterNode preNode;

    public FruitClusterNode(FruitCluster fruitCluster){
        this.fruitCluster = fruitCluster;
    }
    public FruitCluster getValue() {
        return fruitCluster;
    }
    public void setValue(FruitCluster fruitCluster) {
        this.fruitCluster = fruitCluster;
    }
    public Boolean isEqual(FruitClusterNode node) {
        if (node == null) return false;
        if (this.fruitCluster.getID() == node.getValue().getID()) {
            return true;
        }
        return false;
    }
    public FruitClusterNode getNextNode() {
        return nextNode;
    }

    public FruitClusterNode getPreNode() {
        return preNode;
    }
}
