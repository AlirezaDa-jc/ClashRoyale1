package Library;

/**
 * @author Alireza.d.a
 */
public class ElectricTower extends Entity{
    public ElectricTower(int x, int y, Grid grid, EntityType type,String imagePath ,String player)  {
        super(x, y, grid, type, player,imagePath,1500, 250, 3);
    }

}
