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

    private Rewards rewards;
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
    private boolean monologuerDone = false;
    private Npc constance;
    private Npc miggins;
    

    public Quests(World world) {
        this.textPanel = new JPanel();
        this.world = world;
    }

    public void setRewards(Rewards rewards){
        this.rewards = rewards;
    }

    public void setPlayer(Player player){
        this.player = player;
        this.npcList = player.getPanel().npcList;
        this.objList = player.getPanel().objList;
        this.enemyList = player.getPanel().enemyList;
        this.scene = player.getPanel();
        this.scene.addMouseListener(new MouseClickListener(this));
        if(world.getTitle().equals("world1")){
            constance = npcList.get(1);
            miggins = npcList.get(3);
        }else if(world.getTitle().equals("world2")){
            constance = npcList.get(0);
            miggins = npcList.get(4);
        }else if(world.getTitle().equals("world3")){
            constance = npcList.get(6);
            miggins = npcList.get(4);
        }
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
            case 11: return  "Defeat the Necromancer";
            case 10: return "Enter the red portal.";
            case 9: return "Speak to Miggins about a quest";
            case 8: return "Buy an item from Miggin's shop.";
            case 7: return "Speak to the old woman near the shop.";
            case 6: return "Tell Constance you've defeated the skeleton";
            case 5: return "Defeat the broadaxe-wielding skeleton.";
            case 4: return "Enter the green portal.";
            case 3: return "Tell Constance you're done talking to the locals.";
            case 2: return "Talk to the locals (0/3)";
            case 1: return "Accept a quest from Constance.";
            case 0: return "Approach the orange haired lady.";
            default: return "Welcome!";
        }
    }

    public String initializeIndicesForWorld2Q(int index){
        switch (index) {
            case 12: return "Warp to the next world";
            case 11: return "Bring good news to Miggins";
            case 10: return "Defeat the Executioner";
            case 9: return "Enter the red portal.";
            case 8: return "Speak to Miggins about a quest";
            case 7: return "Speak to the old woman near the shop.";
            case 6: return "Tell Constance you've defeated the skeleton";
            case 5: return "Defeat the shield-wielding skeleton.";
            case 4: return "Enter the green portal.";
            case 3: return "Tell Constance you're done talking to the locals.";
            case 2: return "Talk to the locals (0/4).";
            case 1: return "Accept a quest from Constance.";
            case 0: return "Talk to Constance.";
            default: return "Welcome!";
        }
    }

    public String initializeIndicesForWorld3Q(int index){
        switch (index) {
            case 9: return "Enter the red portal.";
            case 8: return "Speak to Miggins about a quest";
            case 7: return "Speak to the old woman near the shop.";
            case 6: return "Tell Constance you've defeated the skeleton";
            case 5: return "Defeat the shield-wielding skeleton.";
            case 4: return "Enter the green portal.";
            case 3: return "Tell Constance you're done talking to the locals.";
            case 2: return "Talk to the locals (0/3).";
            case 1: return "Accept a quest from Constance.";
            case 0: return "Talk to Constance.";
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
            if (npc.getName().equals("Yoo") || npc.getName().equals("Natty") || npc.getName().equals("Faithful") ||
                npc.getName().equals("Ruby") || npc.getName().equals("Renegald") || npc.getName().equals("Chea") || 
                npc.getName().equals("Asriel") || npc.getName().equals("Akefay") || npc.getName().equals("Monologuer")) {
                if (!npc.doneDialogues) {
                    npc.onClick(null);
                    break;
                }
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

    private String getEnemyType(String type){
        switch(type){
            case "skeleton1" ->{
                return "minions";
            }case "skeleton2" ->{
                return "minions";
            }case "skeleton3" ->{
                return "minions";
            }case "death" ->{
                return "miniboss";
            }case "necromancer" ->{
                return "miniboss";
            }default->{
                return null;
            }
        }
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
            clickConstance();
        }else if(ifActive == 1){
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
            clickMiggins();
        }else if(ifActive == 9){
            clickRedPortal();
        }else if(ifActive == 10){
            clickMiniBoss();
        }else if(ifActive == 11){
            clickMiggins();
        }else if(ifActive == 12){
            clickPurplePortal();
        }
    }

    private void handleWorld3Q(){
        if(ifActive == 0){
            clickConstance();
        }else if(ifActive == 1){
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
            clickMiggins();
        }else if(ifActive == 9){
            clickRedPortal();
        }
    }

    public void callPerformQuests(){
        int currentSceneIndex = world.getScene().getCurrentSceneIndex();
        Player player = world.getScene().getPlayer();

        if(ifActive > 2){
            // Find the quest text in the list model and update it, regardless of active status
            for(int i = 0; i < textListModel.getSize(); i++) {
                String currentText = textListModel.getElementAt(i);
                if(currentText.contains("Talk to the locals")) {
                    // Preserve the gray HTML formatting for completed quests while updating the count
                    String str = null;
                    if(world.getTitle().equals("world1")){
                        str = "(3/3)";
                    }else if(world.getTitle().equals("world2")){
                        str = "(4/4)";
                    }else{
                        str = "(3/3)";
                    }
                    if(currentText.contains("<html>")) {
                        textListModel.setElementAt("<html><font color='#808080'>Talk to the locals " + str + "</font></html>", i);
                    } else {
                        textListModel.setElementAt("Talk to the locals " + str, i);
                    }
                    break;
                }
            }
            
        }

        rewards.getMinionsChest().setVisible(!rewards.getMinionsChest().isPerformQActive && currentSceneIndex != 0 && currentSceneIndex == rewards.getMinionsChest().getIndex());
        rewards.getMiniBossChest().setVisible(!rewards.getMiniBossChest().isPerformQActive && currentSceneIndex != 0 && currentSceneIndex  == rewards.getMiniBossChest().getIndex());
        rewards.getQuestChest().setVisible(!rewards.getQuestChest().isPerformQActive && currentSceneIndex != 0 && currentSceneIndex == rewards.getQuestChest().getIndex());

        for(QuestableObjects obj : objList){
            if((int)player.getPosX() == (int)obj.targetX && currentSceneIndex == obj.getIndex()){
                obj.performQuest();
            }
        }
        if((int)player.getPosX() == (int)rewards.getQuestChest().targetX && currentSceneIndex == rewards.getQuestChest().getIndex()){
            rewards.getQuestChest().performQuest();
        }

        if((int)player.getPosX() == (int)rewards.getMinionsChest().targetX && currentSceneIndex == rewards.getMinionsChest().getIndex()){
            rewards.getMinionsChest().performQuest();
        }

        if((int)player.getPosX() == (int)rewards.getMiniBossChest().targetX && currentSceneIndex == rewards.getMiniBossChest().getIndex()){
            rewards.getMiniBossChest().performQuest();
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
                world.getRewards().handleAllyProfileRewards();
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
                        case "Monologuer" -> monologuerDone = true;
                    }
            }
        } 
        switch(world.getTitle()){
            case "world1" -> {
                if (callWorld1QDynamically()){
                    addQuests();
                }
            }
            case "world2" ->{
                if(callWorld2QDynamically()){
                    addQuests();
                }
            }
            case "world3" -> {
                if(callWorld3QDynamically()){
                    addQuests();
                }
            }
        }
    }

    public boolean callWorld1QDynamically(){
        if(ifActive == 0){
            if(constanceDone){
                constance.activateQuest = true;
                setQuestStatus(1);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 1){
            if(constance.doneQDialogues){
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
            for(int i = 0; i < arr.length; i++) {
                if(arr[i]) {
                    localCount++; // Increment count instead of setting it to index
                }
            }
                textListModel.setElementAt("Talk to the locals (" + (localCount) + "/3)", 0);
            if (yooDone && faithfulDone && nattyDone) {
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(3);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 3){
            if(constance.doneODialogues[0]){
                objList.get(1).setIsActivated(true);
                // constance.dialogues.getQuestsDialogues().removeOptionButton(constance.dialogues.getQuestsDialogues().getObjectiveIndex());
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
                setQuestStatus(6);
                rewards.getEnemyRewards(getEnemyType(enemy.getCharacterType()), enemy.getMoneyDrop(), enemy.getX() * 1.2, screenSize.height * 0.23);
                rewards.getMinionsChest().setIndex(enemy.getIndex());
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(1, true);
                return true;
            }
        }

        if(ifActive == 6){
            if(constance.doneODialogues[1]){
                constance.activateQuest = false;
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
                miggins.activateQuest = true;
                setQuestStatus(9);
                return true;
            }
        }

        if(ifActive == 9){
            if(miggins.doneQDialogues){
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
                rewards.getEnemyRewards(getEnemyType(enemy.getCharacterType()), enemy.getMoneyDrop(), enemy.getX() * 1.2, screenSize.height * 0.23);
                rewards.getMiniBossChest().setIndex(enemy.getIndex());
                miggins.dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(12);
                scene.ally.setStatic(true);
                return true;
            }
        }

        if(ifActive == 12){
            if(miggins.doneODialogues[0]){
                objList.get(3).setIsActivated(true);
                miggins.activateQuest = false;
                setQuestStatus(13);  // Increment quest status
                rewards.getQuestsRewards(100, miggins.getX() * 0.8, screenSize.height * 0.23);
                rewards.getQuestChest().setIndex(miggins.getIndex());
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

    public boolean callWorld2QDynamically(){
        if(ifActive == 0){
            if(constanceDone){
                constance.activateQuest = true;
                setQuestStatus(1);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 1){
            if(constance.doneQDialogues){
                setQuestStatus(2);  // Increment quest status
                for(Npc npc : npcList){
                    npc.setStatic(false);
                }
                return true;
            }
        }

        if(ifActive == 2){
            Boolean[] arr = {rubyDone, faithfulDone, renegaldDone, monologuerDone};
            int localCount = 0;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i]) {
                    localCount++; // Increment count instead of setting it to index
                }
            }

                textListModel.setElementAt("Talk to the locals (" + (localCount) + "/4)", 0);
            if (renegaldDone && faithfulDone && rubyDone && monologuerDone) {
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(3);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 3){
            if(constance.doneODialogues[0]){
                objList.get(1).setIsActivated(true);
                // constance.dialogues.getQuestsDialogues().removeOptionButton(constance.dialogues.getQuestsDialogues().getObjectiveIndex());
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
                setQuestStatus(6);
                rewards.getEnemyRewards(getEnemyType(enemy.getCharacterType()), enemy.getMoneyDrop(), enemy.getX() * 1.2, screenSize.height * 0.23);
                rewards.getMinionsChest().setIndex(enemy.getIndex());
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(1, true);
                return true;
            }
        }

        if(ifActive == 6){
            if(constance.doneODialogues[1]){
                constance.activateQuest = false;
                setQuestStatus(7);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 7){
            if(migginsDone){
                miggins.activateQuest = true;
                setQuestStatus(8);
                return true;
            }
        }

        if(ifActive == 8){
            if(miggins.doneQDialogues){
                objList.get(2).setIsActivated(true);
                setQuestStatus(9);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 9){
            QuestableObjects obj = objList.get(2);
            if(obj.doneInteraction){
                setQuestStatus(10);
                return true;
            }
        }

        if(ifActive == 10){
            Enemy enemy = enemyList.get(1);
            if(enemy.getIsDefeated()){
                rewards.getEnemyRewards(getEnemyType(enemy.getCharacterType()), enemy.getMoneyDrop(), enemy.getX() * 1.2, screenSize.height * 0.23);
                rewards.getMiniBossChest().setIndex(enemy.getIndex());
                miggins.dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(11);
                scene.ally.setStatic(true);
                return true;
            }
        }

        if(ifActive == 11){
            if(miggins.doneODialogues[0]){
                objList.get(3).setIsActivated(true);
                miggins.activateQuest = false;
                setQuestStatus(12);  // Increment quest status
                rewards.getQuestsRewards(100, miggins.getX() * 0.8, screenSize.height * 0.23);
                rewards.getQuestChest().setIndex(miggins.getIndex());
                return true;
            }
        }

        if(ifActive == 12){
            QuestableObjects obj = objList.get(3);
            if(obj.doneInteraction){  
                setQuestStatus(13);
                return true;
            }
        }
        return false;
    }   

    public boolean callWorld3QDynamically(){
        if(ifActive == 0){
            if(constanceDone){
                constance.activateQuest = true;
                setQuestStatus(1);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 1){
            if(constance.doneQDialogues){
                setQuestStatus(2);  // Increment quest status
                for(Npc npc : npcList){
                    npc.setStatic(false);
                }
                return true;
            }
        }

        if(ifActive == 2){
            Boolean[] arr = {cheaDone, akefayDone, asrielDone};
            int localCount = 0;
            for(int i = 0; i < arr.length; i++) {
                if(arr[i]) {
                    localCount++; // Increment count instead of setting it to index
                }
            }

                textListModel.setElementAt("Talk to the locals (" + (localCount) + "/3)", 0);
            if (cheaDone && akefayDone && asrielDone) {
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(0, true);
                setQuestStatus(3);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 3){
            if(constance.doneODialogues[0]){
                objList.get(1).setIsActivated(true);
                // constance.dialogues.getQuestsDialogues().removeOptionButton(constance.dialogues.getQuestsDialogues().getObjectiveIndex());
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
                setQuestStatus(6);
                rewards.getEnemyRewards(getEnemyType(enemy.getCharacterType()), enemy.getMoneyDrop(), enemy.getX() * 1.2, screenSize.height * 0.23);
                rewards.getMinionsChest().setIndex(enemy.getIndex());
                constance.dialogues.getQuestsDialogues().updateObjectivesAtIndex(1, true);
                return true;
            }
        }

        if(ifActive == 6){
            if(constance.doneODialogues[1]){
                constance.activateQuest = false;
                setQuestStatus(7);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 7){
            if(migginsDone){
                miggins.activateQuest = true;
                setQuestStatus(8);
                return true;
            }
        }

        if(ifActive == 8){
            if(miggins.doneQDialogues){
                objList.get(2).setIsActivated(true);
                setQuestStatus(9);  // Increment quest status
                return true;
            }
        }

        if(ifActive == 9){
            QuestableObjects obj = objList.get(2);
            if(obj.doneInteraction){
                setQuestStatus(10);
                return true;
            }
        }
        return false;
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
