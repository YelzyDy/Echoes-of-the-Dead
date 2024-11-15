package EOD.objects;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;

import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import EOD.characters.Player;
import EOD.characters.Npc;
import java.util.ArrayList;
import EOD.worlds.*;
public class Quests extends JPanel implements MouseInteractable{
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int ifActive = 0;

    private final JPanel textPanel;
    private JScrollPane scrollPane;
    private JList<String> textList;
    private DefaultListModel<String> textListModel;
    private Player player;
    private SceneBuilder scene;
    private ArrayList<Npc> npcList;
    private World world;
    public double targetX;

    public Quests() {
        this.textPanel = new JPanel();
        targetX = -15;
        initializeUI();
    }

    public void setPlayer(Player player){
        this.player = player;
        this.npcList = player.getPanel().npcList;
        this.scene = player.getPanel();
        this.scene.addMouseListener(new MouseClickListener(this));
        this.world = player.getWorld();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setBounds((int) (screenSize.width * 0.13), (int) (screenSize.height * 0.4),
                (int) (screenSize.width * 0.83), (int) (screenSize.height * 0.45));

        initializeQuestLabel();
        initializeTextPanel();
        initializeTextList();

        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }

    private void initializeQuestLabel() {
        JLabel questLabel = new JLabel(" Quests:");
        questLabel.setFont(new Font("Monospaced", Font.PLAIN, 36));
        questLabel.setForeground(Color.WHITE);
        questLabel.setHorizontalAlignment(SwingConstants.LEFT);

        add(questLabel, BorderLayout.NORTH);
    }

    private void initializeTextPanel() {
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.BLACK);
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(textPanel, BorderLayout.CENTER);
    }

    private void initializeTextList() {
        textListModel = new DefaultListModel<>();
        textList = new JList<>(textListModel);
        textList.setBackground(Color.BLACK);
        textList.setForeground(Color.WHITE);
        textList.setFont(new Font("Monospaced", Font.PLAIN, 28));
        textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        addQuests();

        textList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
                label.setHorizontalAlignment(SwingConstants.CENTER);
        
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.WHITE, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
        
                label.setBackground(Color.BLACK);
                label.setForeground(Color.WHITE);
                label.setOpaque(true);
        
                label.setPreferredSize(new Dimension(label.getPreferredSize().width, 85));
        
                return label;
            }
        });

        textList.setLayoutOrientation(JList.VERTICAL);

        scrollPane = new JScrollPane(textList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.BLACK);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));

        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE;
                this.trackColor = Color.BLACK;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = super.createDecreaseButton(orientation);
                button.setBackground(Color.BLACK);
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = super.createIncreaseButton(orientation);
                button.setBackground(Color.BLACK);
                return button;
            }
        });
        textList.addMouseListener(new MouseClickListener(this));
        textPanel.add(scrollPane);
    }

    
    private String getQuestTextForIndex(int index) { // Change the quests here
        switch (index) {
            case 8: return "Enter the purple portal.";
            case 7: return "Defeat the Necromancer.";
            case 6: return "Enter the red portal.";
            case 5: return "Investigate the shop.";
            case 4: return "Speak to the old woman near the shop.";
            case 3: return "Defeat the skeleton.";
            case 2: return "Enter the green portal.";
            case 1: return "Talk to everyone.";
            case 0: return "Look around.";
            default: return "Welcome!";
        }
    }

    public void setQuestStatus(int ifActive) {
        this.ifActive = ifActive;
    }

    public void addQuests(){
        textListModel.clear();
        for (int i = ifActive; i >= 0; i--) {
            String questText = getQuestTextForIndex(i);

            if (i == ifActive) {
                textListModel.addElement(questText);
            } else {
                textListModel.addElement("<html><font color='#808080'>" + questText + "</font></html>");
            }
        }
    }

    private void q0World1(MouseEvent e){;
        Point clickPoint = e.getPoint();
        targetX = clickPoint.x;
    }

    private void movePlayerToLocation(double locationX){
        if(player.getPosX() < locationX){
            targetX = locationX * 0.9;
        }else targetX =  locationX * 1.1;
        player.clickObjectAt(scene, targetX);
        double playerX = player.getPosX();
        int deltaX = 0;
        if (playerX > locationX) {
            // If player is on right, stay on right
            targetX = locationX * 1.1; 
            deltaX = ((int)targetX - (int)player.getPosX()) / 10;
        } else {
            // If player is on left, stay on left
            targetX = locationX * 0.9; // 20 pixels to left of NPC
            deltaX = ((int)targetX - (int)player.getPosX()) / 10;
        }
        
        // Move player to appropriate position
        if((int) playerX != (int) targetX){
            player.getAnimator().moveTo((int)targetX, deltaX);
        }
    }

    private void handleNpcClick(Npc npc){
        movePlayerToLocation(npc.getPosX());
        npc.onClick(null);
    }
    private void q1World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 1 || currentScene != 0){
            if(currentScene > 1 || currentScene > 0 )movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }

        if(currentScene == 0) {
            for(Npc npc : npcList) {
                if ((npc.getName().equals("Yoo") || npc.getName().equals("Constance")) 
                    && !npc.doneQuest) {
                    handleNpcClick(npc);
                    break;
                }
            }
        }else if(currentScene == 1){
            for(Npc npc : npcList) {
                if ((npc.getName().equals("Natty") || npc.getName().equals("Faithful")) 
                    && !npc.doneQuest) {
                    handleNpcClick(npc);
                    break;
                }
            }
        }
    }

    private void q2World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 1){
            if(currentScene > 1)movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }else{
            movePlayerToLocation(scene.objList.get(1).getPosX());
        }
    }

    private void q3World1(){
        scene.enemyList.get(0).onClick(null);
    }

    private void q4World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 2){
            if(currentScene > 2)movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }else{
            for(Npc npc : npcList) {
                if ((npc.getName().equals("Miggins")) 
                    && !npc.doneQuest) {
                    handleNpcClick(npc);
                    break;
                }
            }
        }
    }

    private void q5World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 2){
            if(currentScene > 2)movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }else{
            movePlayerToLocation(scene.objList.get(0).getPosX());
        }
    }

    private void q6World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 2){
            if(currentScene > 2)movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }else{
            movePlayerToLocation(scene.objList.get(2).getPosX());
        }
    }

    private void q7World1(){
        scene.enemyList.get(1).onClick(null);
    }

    private void q8World1(){
        int currentScene = scene.getCurrentSceneIndex();
        if(currentScene != 2){
            if(currentScene > 2)movePlayerToLocation(screenSize.width * 0.01);
            else movePlayerToLocation(screenSize.width * 1.1);
        }else{
            movePlayerToLocation(scene.objList.get(3).getPosX());
        }
    }

    private void handleWorld1Q(MouseEvent e) {
        Object source = e.getSource();
        if(source == scene && ifActive == 0){
            q0World1(e);
        }else if (source != scene && ifActive == 1) {
            q1World1(); 
        }else if(source != scene && ifActive == 2){
            q2World1();
        }else if(source != scene && ifActive == 3){
            q3World1();
        }else if(source != scene && ifActive == 4){
            q4World1();
        }else if(source != scene && ifActive == 5){
            q5World1();
        }else if(source != scene && ifActive == 6){
            q6World1();
        }else if(source != scene && ifActive == 7){
            q7World1();
        }else if(source != scene && ifActive == 8){
            q8World1();
        }
    }
    
    @Override
    public void onClick(MouseEvent e) {
        int index = textList.locationToIndex(e.getPoint());
        System.out.println("Index: " + index + "IfActive: " + ifActive);
        if(world.getTitle().equals("world1") && index == 0){
            handleWorld1Q(e);
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
