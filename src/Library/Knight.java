package Library;

/**
 * @author Alireza.d.a
 */
public class Knight extends Entity {

    public Knight(int x, int y, Grid grid, EntityType type,String imagePath ,String player)  {
        super(x, y, grid, type, player,imagePath,600,400,1);
    }

    public void move() {
        new Thread(()->{
            try {
                Thread.sleep(500);
                super.move(this,2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
