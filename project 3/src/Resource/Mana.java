package Resource;

public class Mana extends AbstractResource {

    public Mana(int ManaPool, int cost){
        super(ManaPool, cost);
        this.SetCurrentAmount(ManaPool/4);
        super.name = "Mana";
    }
}
