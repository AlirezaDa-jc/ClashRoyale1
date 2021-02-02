package Library;

/**
 * @author Alireza.d.a
 */
public class Archer extends Entity {
    public Archer(int x, int y, Grid grid, EntityType type,String imagePath ,String player)  {
        super(x, y, grid, type, player,imagePath,300,200,2);
    }
    public void move() {
        super.move(this,1);

    }
}
