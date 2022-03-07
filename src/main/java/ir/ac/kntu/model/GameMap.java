package ir.ac.kntu.model;

import ir.ac.kntu.ImageAddresses;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GameMap implements Serializable {

    public static TileEnum chosenTileType = TileEnum.Empty;
    private static GameMap currentMap = new GameMap();
    private static ArrayList<GameMap> allGameMaps = new ArrayList<GameMap>();
    
    //each tile is 50px
    //650px * 550px map size
    // 11 * 13 tile
    private Tile[][] map;

    public GameMap(){
        map = new Tile[13][11];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                try {
                    map[i][j] = new Tile(TileEnum.Sand, TileUsage.CreationBody, new Image(new FileInputStream(ImageAddresses.sandImage)), j, i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /////
    /////
    public static void writeMapToFile(GameMap gameMap, String address){
        StringBuilder toBeStored = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 11; j++) {
                toBeStored.append(convertTileEnumToNumber(gameMap.getMap()[i][j]).toString()+" ");
            }
            if (i!=12){
                toBeStored.append("|");
            }
        }
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(address));
            bw.write(toBeStored.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readAllMainMapsFromFile(){
        //this method runs only once in the beginning of the app
        try {
            BufferedReader bw = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/files/maps/main_maps.txt"));
            String mapText;
            while ((mapText = bw.readLine())!=null){
                GameMap newMap = convertTextToGameMap(mapText);
                GameMap.getAllGameMaps().add(newMap);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameMap convertTextToGameMap(String savedText){
        GameMap gameMap= new GameMap();

        String[] matrix = savedText.trim().split(Pattern.quote("|"));

        for (int i = 0; i < 13; i++) {
            String[] line = matrix[i].trim().split(" ");
            for (int j = 0; j < 11; j++) {
                gameMap.getMap()[i][j].setTileEnum(convertNumberToTileEnum(Integer.parseInt(line[j])));
                try {
                    gameMap.getMap()[i][j].setImage(new Image(new FileInputStream(ImageAddresses.startupPath+
                            gameMap.getMap()[i][j].getTileEnum().toString() + ".png")));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return gameMap;
    }

    private static Integer convertTileEnumToNumber(Tile tile) {
        switch (tile.getTileEnum()){
            case Empty:
                return 1;
            case Sand:
                return 2;
            case Rock:
                return 3;
            case Heart:
                return 4;
            case Mushroom:
                return 5;
            case Balloon:
                return 6;
            case Dragon:
                return 7;
            case Player:
                return 8;
        }
        return 2;
    }

    public static TileEnum convertNumberToTileEnum(Integer input){
        switch (input){
            case 1:
                return TileEnum.Empty;
            case 2:
                return TileEnum.Sand;
            case 3:
                return TileEnum.Rock;
            case 4:
                return TileEnum.Heart;
            case 5:
                return TileEnum.Mushroom;
            case 6:
                return TileEnum.Balloon;
            case 7:
                return TileEnum.Dragon;
            case 8:
                return TileEnum.Player;
        }
        return TileEnum.Sand;
    }
    /////
    /////

    public Tile[][] getMap() {
        return map;
    }

    public static ArrayList<GameMap> getAllGameMaps() {
        return allGameMaps;
    }

    public static GameMap getCurrentMap() {
        return currentMap;
    }

    public static void setCurrentMap(GameMap currentMap) {
        GameMap.currentMap = currentMap;
    }
}
