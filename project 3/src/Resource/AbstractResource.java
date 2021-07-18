package Resource;

public abstract class AbstractResource {

    private int maxAmount;
    private int currentAmount;
    protected String name;
    private int cost;

    public AbstractResource(int maxAmount, int cost){
        this.maxAmount = maxAmount;
        this.currentAmount = maxAmount;
        this.cost = cost;
    }

    public int MaxAmount(){
        return maxAmount;
    }

    public void SetMaxAmount(int maxAmount){
        this.maxAmount = maxAmount;
    }

    public int CurrentAmount(){
        return currentAmount;
    }

    public void SetCurrentAmount(int currentAmount){
        this.currentAmount = currentAmount;
    }


    public int Cost(){
        return cost;
    }

    public String toString(){
        return name + ": " + currentAmount + "/" + maxAmount;
    }
}
