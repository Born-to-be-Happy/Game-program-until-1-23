import javafx.scene.layout.GridPane;
import javafx.scene.image.ImageView;
import java.util.Random;

public class ItemController {
    private ItemData ItemData;

    public ItemController(ItemData ItemData) {
        this.ItemData = ItemData;
    }

    public void PlaceItemsRandomly(MapData MapData) {
        int Width = MapData.getWidth();
        int Height = MapData.getHeight();

        for (int y = 0; y < Height; y++) {
            for (int x = 0; x < Width; x++) {
                ItemData.clearItem(x, y);
            }
        }

        ItemData.setItem(19, 13, ItemData.Door);
        System.out.println("Door配置[20,14]");

        Random Rand = new Random();
        
        int[] ItemTypes = {
            ItemData.Door, 
            ItemData.Bomb, 
            ItemData.Key
        } ;

        for (int i = 1; i < ItemTypes.length; i++) {
            int TargetType = ItemTypes[i];
            boolean Placed = false;

            while (!Placed) {
                int X = Rand.nextInt(Width);
                int Y = Rand.nextInt(Height);

                if (MapData.getMap(X, Y) == MapData.TYPE_SPACE &&
                    ItemData.getItemType(X, Y) == ItemData.NONE &&
                    !(X == 1 && Y == 1)) {
                    
                    ItemData.setItem(X, Y, TargetType);
                    System.out.println("アイテム配置: 座標[" + X + "," + Y + "] タイプ:" + TargetType);
                    Placed = true;
                }
            }
        }
    }

    public int CheckCollision(MoveChara Chara) {
        int Cx = Chara.getPosX();
        int Cy = Chara.getPosY();
        
        int ItemType = ItemData.getItemType(Cx, Cy);

        if (ItemType != ItemData.NONE) {
            System.out.println("Item GET! Type: " + ItemType + " at [" + Cx + "," + Cy + "]");
            
            return ItemType;
        }

        return ItemData.NONE;
    }

    public void DrawItems(GridPane MapGrid, MapData MapData) {
        int Width = MapData.getWidth();
        int Height = MapData.getHeight();

        for (int y = 0; y < Height; y++) {
            for (int x = 0; x < Width; x++) {
                int ItemType = ItemData.getItemType(x, y);

                if (ItemType != ItemData.NONE) {
                    ImageView ItemView = ItemData.getItemImageView(ItemType);
                    if (ItemView != null) {
                        MapGrid.add(ItemView, x, y);
                    }
                }
            }
        }
    }
}