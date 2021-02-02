package Library;

/**
 * @author Alireza.d.a
 */
public class Goblin extends Entity {

    public Goblin(int x, int y, Grid grid, EntityType type,String imagePath ,String player) {
        super(x, y, grid, type, player,imagePath,200,250,1);
    }

    public void move() {
        new Thread(()->{
            try {
                Thread.sleep(300);
                super.move(this,3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
