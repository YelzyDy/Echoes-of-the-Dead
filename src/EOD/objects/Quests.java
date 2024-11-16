package EOD.objects;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.MouseEvent;

import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.scenes.SceneBuilder;
import EOD.characters.Player;
import EOD.characters.enemies.Enemy;
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
    private ArrayList<QuestableObjects> objList;
    private ArrayList<Enemy> enemyList;
    private World world;


    private boolean yooDone = false;
    private boolean constanceDone = false;
    private boolean faithfulDone = false;
    private boolean nattyDone = false;
    private boolean renegaldDone = false;
    private boolean rubyDone = false;
    public Quests() {
        this.textPanel = new JPanel();
    }

    public void setPlayer(Player player){
        this.player = player;
        this.npcList = player.getPanel().npcList;
        this.objList = player.getPanel().objList;
        this.enemyList = player.getPanel().enemyList;
        this.scene = player.getPanel();
        this.scene.addMouseListener(new MouseClickListener(this));
        this.world = player.getWorld();
    }

    public void initializeUI() {
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
        switch(world.getTitle()){
            case "world1": return initializeIndicesForWorld1Q(index);
            case "world2": return initializeIndicesForWorld2Q(index);
            default: return null;
        }
    }

    public String initializeIndicesForWorld1Q(int index){
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

    public String initializeIndicesForWorld2Q(int index){
        switch (index) {
            case 6: return "Warp to the next world";
            case 5: return "Defeat the Executioner.";
            case 4: return "Enter the red portal.";
            case 3: return "Speak to Miggings";
            case 2: return "Defeat the skeleton.";
            case 1: return "Enter the green portal.";
            case 0: return "Talk to everyone.";
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
    
    private void q0World2(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Ruby") || npc.getName().equals("Constance") || 
                (npc.getName().equals("Faithful") || npc.getName().equals("Renegald")))
                && !npc.doneQuest) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void q1World1(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Yoo") || npc.getName().equals("Constance") || 
                (npc.getName().equals("Natty") || npc.getName().equals("Faithful")))
                && !npc.doneQuest) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void q2World1(){
        objList.get(1).onClick(null);
    }

    private void q3World1(){
        enemyList.get(0).onClick(null);
    }

    private void q4World1(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Miggins")) 
                && !npc.doneQuest) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void q5World1(){
        objList.get(0).onClick(null);
    }

    private void q6World1(){
       objList.get(2).onClick(null);
    }

    private void q7World1(){
        enemyList.get(1).onClick(null);
    }

    private void q8World1(){
        objList.get(3).onClick(null);
    }



    private void handleWorld1Q(MouseEvent e) {
        Object source = e.getSource();
        if (source != scene && ifActive == 1) {
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

    private void handleWorld2Q(){
        if(ifActive == 0){
            q0World2();
        }else if(ifActive == 1){
            q2World1();
        }else if(ifActive == 2){
            q3World1();
        }else if(ifActive == 3){
            q4World1();
        }else if(ifActive == 4){
            q6World1();
        }else if(ifActive == 5){
            q7World1();
        }else if(ifActive == 6){
            q8World1();
        }
    }

    public void callPerformQuests(){
        int currentSceneIndex = world.getScene().getCurrentSceneIndex();
        Player player = world.getScene().getPlayer();
        for(QuestableObjects obj : objList){
            if((int)player.getPosX() == (int)obj.targetX && currentSceneIndex == obj.getIndex()){
                obj.performQuest();
            }
        }

        for(Npc npc : npcList){
            if((int)player.getPosX() == (int)npc.targetX && currentSceneIndex == npc.getIndex()){
                npc.performQuest();
            }
        }

        if(scene.ally != null){
            if((int)player.getPosX() == (int)scene.ally.targetX && currentSceneIndex == scene.ally.getIndex()){
                scene.ally.performQuest();
            }
            if(scene.ally.doneQuest){
                world.getBattle().getRewards().handleAllyProfileRewards();
                world.getScene().remove(world.getScene().ally);
                world.getScene().ally = null;
            }
        }

        for(Npc npc : npcList) {
            if (npc.doneQuest) {
                    npc.onExit(null);
                    npc.dialogues.askButton.setVisible(true);
                    switch (npc.getName()) {
                        case "Yoo" -> yooDone = true;
                        case "Constance" -> constanceDone = true;
                        case "Faithful" -> faithfulDone = true;
                        case "Natty" -> nattyDone = true;
                        case "Renegald" -> renegaldDone = true;
                        case "Ruby" -> rubyDone = true;
                    }
            }
        } 
        switch(world.getTitle()){
            case "world1" -> callWorld1QDynamically();
            case "world2" ->callWorld2QDynamically();
        }
    }

        public void callWorld2QDynamically(){
            if(ifActive == 0){
                if (renegaldDone && constanceDone && faithfulDone && rubyDone) {
                    objList.get(1).setIsActivated(true);
                    setQuestStatus(1);  // Increment quest status
                    addQuests();
                }
            }

            if(ifActive == 1){
                QuestableObjects obj = objList.get(1);
                if(obj.doneQuest){ 
                    setQuestStatus(2);
                    addQuests();
                }
            }

            if(ifActive == 2){
                Enemy enemy = enemyList.get(0);
                if(enemy.getIsDefeated()){
                    setQuestStatus(3);
                    addQuests();
                }
            }

            if(ifActive == 3){
                if(npcList.get(4).doneQuest){
                    setQuestStatus(4);
                    addQuests();
                    objList.get(2).setIsActivated(true);
                }
            }

            if(ifActive == 4){
                QuestableObjects obj = objList.get(2);
                if(obj.doneQuest){
                    setQuestStatus(5);
                    addQuests();
                }
            }

            if(ifActive == 5){
                Enemy enemy = enemyList.get(1);
                if(enemy.getIsDefeated()){
                    setQuestStatus(6);
                    addQuests();
                    scene.ally.setStatic(true);
                }
            }

            if(ifActive == 6){
                QuestableObjects obj = objList.get(3);
                if(obj.doneQuest){  
                    setQuestStatus(7);
                    addQuests();
                }
            }
        }   

    public void callWorld1QDynamically(){
        if(ifActive == 0){
            if((int)player.getPosX() == (int)player.clickX){
                setQuestStatus(++ifActive);  // Increment quest status
                addQuests();
                for(Npc npc : npcList){
                    npc.setStatic(false);
                }
            }
        }

        if(ifActive == 1){
            if (yooDone && constanceDone && faithfulDone && nattyDone) {
                objList.get(1).setIsActivated(true);
                setQuestStatus(++ifActive);  // Increment quest status
                addQuests();
            }
        }

        if(ifActive == 2){
            QuestableObjects obj = objList.get(1);
            if(obj.doneQuest){ 
                setQuestStatus(3);
                addQuests();
            }
        }

        if(ifActive == 3){
            Enemy enemy = enemyList.get(0);
            if(enemy.getIsDefeated()){
                setQuestStatus(4);
                addQuests();
            }
        }

        if(ifActive == 4){
            if(npcList.get(3).doneQuest){
                setQuestStatus(5);
                addQuests();
            }
        }

        if(ifActive == 5){
            QuestableObjects obj = objList.get(0);
            if(obj.doneQuest){  
                setQuestStatus(6);
                addQuests();
                objList.get(2).setIsActivated(true);
            }
        }

        if(ifActive == 6){
            QuestableObjects obj = objList.get(2);
            if(obj.doneQuest){
                setQuestStatus(7);
                addQuests();
            }
        }

        if(ifActive == 7){
            Enemy enemy = enemyList.get(1);
            if(enemy.getIsDefeated()){
                setQuestStatus(8);
                addQuests();
                scene.ally.setStatic(true);
            }
        }

        if(ifActive == 8){
            QuestableObjects obj = objList.get(3);
            if(obj.doneQuest){  
                setQuestStatus(9);
                addQuests();
            }
        }
    }
    
    @Override
    public void onClick(MouseEvent e) {
        int index = textList.locationToIndex(e.getPoint());
        System.out.println("Index: " + index + "IfActive: " + ifActive);
        Object source = e.getSource();
        if(world.getTitle().equals("world1") && index == 0){
            handleWorld1Q(e);
        }else if(source != scene && world.getTitle().equals("world2") && index == 0){
            handleWorld2Q();
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
