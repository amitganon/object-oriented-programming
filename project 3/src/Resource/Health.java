package Resource;

public class Health extends AbstractResource{
    public Health(int HealthPool){
        super(HealthPool, 0);
        super.name = "Health";
    }
}
