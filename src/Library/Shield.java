package Library;

/**
 * @author Alireza.d.a
 */
public class Shield extends Entity{

    public Shield(int x, int y, Grid grid, EntityType type,String imagePath ,String player)  {
        super(x, y, grid, type, player,imagePath,1000,150,1);
    }


    public void move() {
        new Thread(()->{
            try {
                Thread.sleep(1000);
                super.move(this,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
