package tools;

import main.ScreenAlignment;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpritesheetModifier extends Frame {

    public static int WINDOW_WIDTH = 960;
    public static int WINDOW_HEIGHT = 540;

    public static int VIRTUAL_WIDTH = 256;
    public static int VIRTUAL_HEIGHT = 144;

    private static int SCROLL_SPEED = 60;

    private static BufferedImage imageToDraw;
    private static BufferedImage originalImage;
    private static BufferedImage numberedImage;
    private static BufferedImage drawImage;

    public int camX = 0;
    public int camDrawX = 0;
    public int camY = 0;
    public int camDrawY = 0;

    private Canvas canvas;

    public MenuBar menuBar = new MenuBar();

    public Menu newFile = new Menu("New");
    public MenuItem loading = new MenuItem("+");
    public MenuItem numbering = new MenuItem("1, 2, 3 ...");
    public MenuItem drawing = new MenuItem("Draw");

    public int tileWidth;
    public int tileHeight;
    public String fileName;
    public String filePath;

    public int mapWidth;
    public int mapHeight;

    public int chosenTile = 0;

    public boolean isDrawing = false;
    public boolean isChoosing = false;

    public int[][] map;

    public SpritesheetModifier(){
        loadMenuBar();

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setMaximumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        canvas.setFocusable(false);

        // Screen bzw. Panel hinzufügen
        setLayout(new BorderLayout());
        add(canvas, BorderLayout.CENTER);

        addKeyListener(new KeyHandler());

        pack();
        setLocationRelativeTo(null);
        setFocusable(true);
        setVisible(true);                           // Fenster sichtbar machen
    }

    private void loadMenuBar(){

        loading.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean finishedInput = false;
                File file = null;

                do {

                    InputDialog inputDialog = new InputDialog(true);
                    int rueckgabewert = JOptionPane.showConfirmDialog(
                            null,
                            inputDialog,
                            "Angaben zum Tileset",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (rueckgabewert == JOptionPane.OK_OPTION) {
                        // check if Input is "valid"
                        try {
                            tileWidth = Integer.parseInt(inputDialog.fieldWidth.getText());
                            tileHeight = Integer.parseInt(inputDialog.fieldHeight.getText());

                            file = new File(inputDialog.fieldFilePath.getText());
                            finishedInput = inputDialog.fieldFilePath.getText().contains(".png")
                                    && tileWidth > 0
                                    && tileHeight > 0;
                        } catch(Exception ex) {
                            finishedInput = false;
                        }
                    } else {
                        finishedInput = true;
                    }
                }while(!finishedInput);

                try{
                    originalImage = ImageIO.read(file);
                    imageToDraw = originalImage;

                    fileName = file.getName();
                    filePath = file.getPath();

                    render();
                    render();

                    numbering.setEnabled(true);
                    drawing.setEnabled(true);
                }catch(Exception ex){

                }
            }
        });
        loading.setEnabled(true);

        numbering.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNumberedImage();
            }
        });
        numbering.setEnabled(false);

        drawing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean finishedInput = false;

                do {

                    InputDialog inputDialog = new InputDialog(false);
                    int rueckgabewert = JOptionPane.showConfirmDialog(
                            null,
                            inputDialog,
                            "Angaben zur Mapgrösse",
                            JOptionPane.OK_CANCEL_OPTION);

                    if (rueckgabewert == JOptionPane.OK_OPTION) {
                        // check if Input is "valid"
                        try {
                            mapWidth = Integer.parseInt(inputDialog.fieldWidth.getText());
                            mapHeight = Integer.parseInt(inputDialog.fieldHeight.getText());

                            finishedInput = mapWidth > 0 && mapHeight > 0;
                            isChoosing = finishedInput;
                            map = new int[mapWidth][mapHeight];

                            drawImage = new BufferedImage(mapWidth * tileWidth, mapHeight * tileHeight, originalImage.getType());
                            renderDrawing();
                        } catch(Exception ex) {
                            finishedInput = false;
                        }
                    } else {
                        finishedInput = true;
                    }
                }while(!finishedInput);
            }
        });
        drawing.setEnabled(false);

        newFile.add(loading);
        newFile.add(numbering);
        newFile.add(drawing);

        menuBar.add(newFile);

        setMenuBar(menuBar);
    }

    public void render() {
        if(imageToDraw != null) {
            BufferStrategy bs = canvas.getBufferStrategy();
            if (bs == null) {
                canvas.createBufferStrategy(2);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            ScreenAdapter.drawImage((Graphics2D) g, imageToDraw, camX, camY, imageToDraw.getWidth(), imageToDraw.getHeight());

            bs.show();
            g.dispose();
        }
    }

    public void renderDrawing() {
        if(drawImage != null) {
            BufferStrategy bs = canvas.getBufferStrategy();
            if (bs == null) {
                canvas.createBufferStrategy(2);
                return;
            }
            Graphics g = bs.getDrawGraphics();
            g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            ScreenAdapter.drawImage((Graphics2D) g,
                    originalImage,
                    -camX * tileWidth,
                    -camY * tileHeight,
                    imageToDraw.getWidth(),
                    imageToDraw.getHeight());

            ScreenAdapter.drawRect((Graphics2D) g,
                    0,
                    0,
                    tileWidth,
                    tileHeight);

            for(int y = camDrawY; y < mapHeight - camDrawY; y++){
                for(int x = camDrawX; x < mapWidth - camDrawX; x++){
                    int imageX = map[x][y] % (int) (originalImage.getWidth() / tileWidth);
                    int imageY = (int) map[x][y] / (int) (originalImage.getWidth() / tileWidth);

                    ScreenAdapter.drawImage((Graphics2D) g,
                            originalImage.getSubimage(imageX * tileWidth, imageY * tileHeight, tileWidth, tileHeight),
                            (x - camDrawX) * tileWidth + VIRTUAL_WIDTH / 2,
                            (y - camDrawY) * tileHeight + 0 * VIRTUAL_HEIGHT / 2,
                            tileWidth,
                            tileHeight);
                }
            }

            ScreenAdapter.drawRect((Graphics2D) g,
                    VIRTUAL_WIDTH / 2,
                    0,
                    tileWidth,
                    tileHeight);

            bs.show();
            g.dispose();
        }
    }

    private void createNumberedImage(){
        if(originalImage != null){
            numberedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
            Graphics g = numberedImage.getGraphics();
            g.drawImage(originalImage, 0, 0, null);

            g.setColor(Color.MAGENTA);
            g.setFont(new Font("Times New Roman", Font.PLAIN, 6));
            for(int y = 0; y < numberedImage.getHeight(); y = y + tileHeight) {
                for (int x = 0; x < numberedImage.getWidth(); x = x + tileWidth) {
                    ScreenAdapter.drawString((Graphics2D) g,
                            "" + ((y / tileHeight) * (numberedImage.getWidth() / tileWidth) + (x / tileWidth)),
                            x + 3,
                            y + 3,
                            ScreenAlignment.NONE);
                }
            }
            g.dispose();
            imageToDraw = numberedImage;
            render();
        }else{

        }
    }

    public static void main(String[] args) {

        new SpritesheetModifier();
    }

    private class InputDialog extends JPanel{

        TextField fieldWidth = new TextField();
        TextField fieldHeight = new TextField();
        TextField fieldFilePath = new TextField();

        Button buttonSearch = new Button("SEARCH");

        public InputDialog(boolean withFilePath){
            setLayout(new GridLayout(withFilePath ? 3 : 2, 3));
            add(new Label("width:"));
            add(fieldWidth);
            add(Box.createHorizontalStrut(15));
            add(new Label("height:"));
            add(fieldHeight);
            add(Box.createHorizontalStrut(15));
            if(withFilePath) {
                add(new Label("image path"));
                add(fieldFilePath);
                add(buttonSearch);

                buttonSearch.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser chooser = new JFileChooser();

                        int rueckgabewert = chooser.showOpenDialog(null);

                        if (rueckgabewert == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();
                            if (file.getAbsolutePath().contains(".png"))
                                fieldFilePath.setText(file.getAbsolutePath());
                            else
                                JOptionPane.showMessageDialog(null, "Keine PNG-Datei ausgewählt!");
                        } else {

                        }
                    }
                });
            }
        }
    }

    private class KeyHandler extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            if(isDrawing || isChoosing) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        isDrawing = !isDrawing;
                        isChoosing = !isChoosing;
                        break;
                    case KeyEvent.VK_LEFT:
                        if(isChoosing)
                            camX = Math.max(0, camX - 1);
                        else
                            camDrawX = Math.max(0, camDrawX - 1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(isChoosing)
                            camX = Math.min((int) (originalImage.getWidth() / tileWidth), camX + 1);
                        else
                            camDrawX = Math.min(mapWidth - 1, camDrawX + 1);
                        break;
                    case KeyEvent.VK_DOWN:
                        if(isChoosing)
                            camY = Math.min((int) (originalImage.getHeight() / tileHeight), camY + 1);
                        else
                            camDrawY = Math.min(mapHeight - 1, camDrawY + 1);
                        break;
                    case KeyEvent.VK_UP:
                        if(isChoosing)
                            camY = Math.max(0, camY - 1);
                        else
                            camDrawY = Math.max(0, camDrawY - 1);
                        break;
                    case KeyEvent.VK_SPACE:
                        if(isChoosing)
                            chosenTile = camX + camY * (int) (originalImage.getWidth() / tileWidth);
                        else
                            map[camDrawX][camDrawY] = chosenTile;
                        break;
                }
                renderDrawing();
            }
        }
    }
}
