import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemData{

    public static final int Door = 0 ;
    public static final int Bomb = 1 ;
    public static final int Key = 2 ;
    public static final int NONE = -1 ; // アイテムなし

    private static final String ItemImageFiles[] = {
        "png/Door.png" ,
        "png/Bomb.png" ,
        "png/Key.png" ,
    } ;

    private Image[] ItemImages ;
    private ImageView[][] ItemImageViews ;
    private int width ;
    private int height ;
    private int[][] ItemMap;

    public ItemData(int width , int height){

        this.width = width;
        this.height = height;
        this.ItemMap = new int[height][width];
        this.ItemImages = new Image[ItemImageFiles.length];

        for (int i = 0 ; i < ItemImages.length ; i ++){
            ItemImages[i] = new Image(ItemImageFiles[i]) ; 
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ItemMap[y][x] = NONE;
            }
        }
    }
    
    public void setItem(int x, int y, int type) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            ItemMap[y][x] = type;
        }
    }

    public int getItemType(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return ItemMap[y][x];
        }
        return NONE ;
    }

    public ImageView getItemImageView(int type) {
        if (type >= 0 && type < ItemImages.length) {
            return new ImageView(ItemImages[type]);
        }
        return null;
    }

    public void clearItem(int x, int y) {
        setItem(x, y, NONE);
    }
}