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
    private boolean akefayDone = false;
    private boolean asrielDone = false;
    private boolean cheaDone = false;
    private boolean migginsDone = false;


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
            case "world3": return initializeIndicesForWorld3Q(index);
            default: return null;
        }
    }

    // public String initializeIndicesForWorld1Q(int index){
    //     switch (index) {
    //         case 8: return "Enter the purple portal.";
    //         case 7: return "Defeat the Necromancer.";
    //         case 6: return "Enter the red portal.";
    //         case 5: return "Investigate the shop.";
    //         case 4: return "Speak to the old woman near the shop.";
    //         case 3: return "Defeat the skeleton.";
    //         case 2: return "Enter the green portal.";
    //         case 1: return "Talk to everyone.";
    //         case 0: return "Approach young lady (Constance).";
    //         default: return "Welcome!";
    //     }
    // }

    public String initializeIndicesForWorld1Q(int index){
        switch (index) {
            case 13: return "Warp to the next world";
            case 12: return "Bring good news to Miggins";
            case 11: return  "Defeat the Necomrancer";
            case 10: return "Enter the red portal.";
            case 9: return "Speak to Miggins about a quest";
            case 8: return "Buy an item from Miggin's shop.";
            case 7: return "Speak to the old woman near the shop.";
            case 6: return "Tell Constance you've defeated the skeleton";
            case 5: return "Defeat the axe-wielding skeleton.";
            case 4: return "Enter the green portal.";
            case 3: return "Tell Constance you're done talking to the locals.";
            case 2: return "Talk to the locals (0/3)";
            case 1: return "Speak to Constance about a quest.";
            case 0: return "Approach the orange haired lady.";
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

    public String initializeIndicesForWorld3Q(int index){
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
            if ((npc.getName().equals("Ruby") || npc.getName().equals("Renegald"))
                && !npc.doneDialogues) {
                npc.onClick(null);
                break;
            }
        }
    }
    

    private void q0World3(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Chea") || npc.getName().equals("Natty") || 
                (npc.getName().equals("Asriel") || npc.getName().equals("Akefay")))
                && !npc.doneDialogues) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void clickConstance(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Constance"))
                && (!npc.doneDialogues || !npc.doneQDialogues || !npc.doneODialogues[0] || !npc.doneODialogues[1])) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void clickLocals(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Yoo") || (npc.getName().equals("Natty") || npc.getName().equals("Faithful")))
                && !npc.doneDialogues) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void clickGreenPortal(){
        objList.get(1).onClick(null);
    }

    private void clickMinion(){
        enemyList.get(0).onClick(null);
    }

    private void clickMiggins(){
        for(Npc npc : npcList) {
            if ((npc.getName().equals("Miggins")) 
                && (!npc.doneDialogues || !npc.doneQDialogues || !npc.doneODialogues[0] || !npc.doneODialogues[1])) {
                npc.onClick(null);
                break;
            }
        }
    }

    private void clickShop(){
        objList.get(0).onClick(null);
    }

    private void clickRedPortal(){
       objList.get(2).onClick(null);
    }

    private void clickMiniBoss(){
        enemyList.get(1).onClick(null);
    }

    private void clickPurplePortal(){
        objList.get(3).onClick(null);
    }



    private void handleWorld1Q() {
        if (ifActive == 0) {
            clickConstance(); 
        }else if (ifActive == 1) {
            clickConstance(); 
        }else if(ifActive == 2){
            clickLocals();
        }else if(ifActive == 3){
            clickConstance(); 
        }else if(ifActive == 4){
            clickGreenPortal();
        }else if(ifActive == 5){
            clickMinion();
        }else if(ifActive == 6){
            clickConstance();
        }else if(ifActive == 7){
            clickMiggins();
        }else if(ifActive == 8){
            clickShop();
        }else if(ifActive == 9){
            clickMiggins();
        }else if(ifActive == 10){
            clickRedPortal();
        }else if(ifActive == 11){
            clickMiniBoss();
        }else if(ifActive == 12){
            clickMiggins();
        }else{
            clickPurplePortal();
        }
    }

    private void handleWorld2Q(){
        if(ifActive == 0){
            q0World2();
        }else if(ifActive == 1){
            clickGreenPortal();
        }else if(ifActive == 2){
            // q3World1();
        }else if(ifActive == 3){
            clickMiggins();
        }else if(ifActive == 4){
            // q6World1();
        }else if(ifActive == 5){
            // q7World1();
        }else if(ifActive == 6){
            // q8World1();
        }
    }

    private void handleWorld3Q(){
        if(ifActive == 0){
            q0World3();
        }else if(ifActive == 1){
            clickGreenPortal();
        }else if(ifActive == 2){
            // q3World1();
        }else if(ifActive == 3){
            clickMiggins();
        }else if(ifActive == 4){
            // q6World1();
        }else if(ifActive == 5){
            // q7World1();
        }else if(ifActive == 6){
            // q8World1();
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
            if(scene.ally.doneDialogues){
                world.getBattle().getRewards().handleAllyProfileRewards();
                world.getScene().remove(world.getScene().ally);
                world.getScene().ally = null;
            }
        }

        for(Npc npc : npcList) {
            if (npc.doneDialogues) {
                    npc.onExit(null);
                    npc.dialogues.askButton.setVisible(true);
                    switch (npc.getName()) {
                        case "Yoo" -> yooDone = true;
                        case "Constance" -> constanceDone = true;
                        case "Faithful" -> faithfulDone = true;
                        case "Natty" -> nattyDone = true;
                        case "Renegald" -> renegaldDone = true;
                        case "Ruby" -> rubyDone = true;
                        case "Akefay" -> akefayDone = true;
                        case "Asriel" -> asrielDone = true;
                        case "Chea" -> cheaDone = true;
                        case "Miggins" -> migginsDone = true;
                    }
            }
        } 
        switch(world.getTitle()){
            case "world1" -> {
                if (callWorld1QDynamically()){
                    addQuests();
                }
            }
            case "world2" -> callWorld2QDynamically();
            case "world3" -> callWorld3QDynamically();
        }
    }

    public boolean callWorld1QDynamically(){
        if(ifActive == 0){
            if(constanceDone){
                npcList.get(1).activateQuest = true;
                setQuestStatus(1);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 1){
            if(npcList.get(1).doneQDialogues){
                setQuestStatus(2);  // Increment quest status
                for(Npc npc : npcList){
                    npc.setStatic(false);
                }
                return true;
            }
        }

        if(ifActive == 2){
            Boolean[] arr = {nattyDone, yooDone, faithfulDone};
            int localCount = 0;
            for(int i = 0; i < arr.length; i++){
                if(arr[i]){
                    localCount = i;
                }
            }
                textListModel.setElementAt("Talk to the locals (" + (localCount) + "/3)", 0);
            if (yooDone && faithfulDone && nattyDone) {
                npcList.get(1).dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(3);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 3){
            if(npcList.get(1).doneODialogues[0]){
                objList.get(1).setIsActivated(true);
                // npcList.get(1).dialogues.getQuestsDialogues().removeOptionButton(npcList.get(1).dialogues.getQuestsDialogues().getObjectiveIndex());
                setQuestStatus(4);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 4){
            QuestableObjects obj = objList.get(1);
            if(obj.doneInteraction){ 
                setQuestStatus(5);
                return true;
            }
        }

        if(ifActive == 5){
            Enemy enemy = enemyList.get(0);
            if(enemy.getIsDefeated()){
                npcList.get(1).dialogues.getQuestsDialogues().updateObjectivesAtIndex(1, true);
                setQuestStatus(6);
                return true;
            }
        }

        if(ifActive == 6){
            if(npcList.get(1).doneODialogues[1]){
                setQuestStatus(7);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 7){
            if(migginsDone){
                setQuestStatus(8);
                return true;
            }
        }

        if(ifActive == 8){
            QuestableObjects obj = world.getShop();
            if(obj.doneInteraction){  
                npcList.get(3).activateQuest = true;
                setQuestStatus(9);
                return true;
            }
        }

        if(ifActive == 9){
            if(npcList.get(3).doneQDialogues){
                objList.get(2).setIsActivated(true);
                setQuestStatus(10);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 10){
            QuestableObjects obj = objList.get(2);
            if(obj.doneInteraction){
                setQuestStatus(11);
                return true;
            }
        }

        if(ifActive == 11){
            Enemy enemy = enemyList.get(1);
            if(enemy.getIsDefeated()){
                npcList.get(3).dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(12);
                scene.ally.setStatic(true);
                return true;
            }
        }

        if(ifActive == 12){
            if(npcList.get(3).doneODialogues[0]){
                objList.get(3).setIsActivated(true);
                // npcList.get(1).dialogues.getQuestsDialogues().removeOptionButton(npcList.get(1).dialogues.getQuestsDialogues().getObjectiveIndex());
                setQuestStatus(13);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 13){
            QuestableObjects obj = objList.get(3);
            if(obj.doneInteraction){  
                setQuestStatus(14);
                return true;
            }
        }
        return false;
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
            if(obj.doneInteraction){ 
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
            if(npcList.get(4).doneDialogues){
                setQuestStatus(4);
                addQuests();
                objList.get(2).setIsActivated(true);
            }
        }
        if(ifActive == 4){
            QuestableObjects obj = objList.get(2);
            if(obj.doneInteraction){
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
            if(obj.doneInteraction){  
                setQuestStatus(7);
                addQuests();
            }
        }
    }   

    public void callWorld3QDynamically(){
        if(ifActive == 0){
            if (cheaDone && akefayDone && asrielDone) {
                objList.get(1).setIsActivated(true);
                setQuestStatus(1);  // Increment quest status
                addQuests();
            }
        }
        if(ifActive == 1){
            QuestableObjects obj = objList.get(1);
            if(obj.doneInteraction){ 
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
            if(npcList.get(4).doneDialogues){
                setQuestStatus(4);
                addQuests();
                objList.get(2).setIsActivated(true);
            }
        }
        if(ifActive == 4){
            QuestableObjects obj = objList.get(2);
            if(obj.doneInteraction){
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
            if(obj.doneInteraction){  
                setQuestStatus(7);
                addQuests();
            }
        }
    }   
    
    @Override
    public void onClick(MouseEvent e) {
        int index = textList.locationToIndex(e.getPoint());
        System.out.println("Index: " + index + "IfActive: " + ifActive);
        Object source = e.getSource();
        if(source != scene && world.getTitle().equals("world1") && index == 0){
            handleWorld1Q();
        }else if(source != scene && world.getTitle().equals("world2") && index == 0){
            handleWorld2Q();
        }else if(source != scene && world.getTitle().equals("world3") && index == 0){
            handleWorld3Q();
        }
    }

    @Override
    public void onHover(MouseEvent e) {
       
    }

    @Override
    public void onExit(MouseEvent e) {
        
    }
}
