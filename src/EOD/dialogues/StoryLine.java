package EOD.dialogues;
import EOD.gameInterfaces.Freeable;
import java.awt.*;
public class StoryLine implements Freeable{

        private String[] arr = new String[50];
        private String[] qArr = new String[50];
        private String[] oArr = new String[50];
        private int size;
        private int questSize;
        private int objectivesSize;
        private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private boolean[] objDone = new boolean[4];
        private String playerName;
        public void free(){
                size = 0;
                arr = null;
                qArr = null;
                questSize = 0;
        }

        public int getDoneObjectiveIndex() {
                int lastIndex = -1;
                for (int i = 0; i < 4; i++) {
                    if (objDone[i]) {
                        lastIndex = i; 
                    }
                }
                return lastIndex;
            }
            
        
        public int getTargetIndex(int clickedIndex){
                int c = 0;
                for(int j = 0, count = 1; j < objectivesSize; j++){
                    if(getOArr()[j].equals("-")){
                        System.out.println(oArr[j]);
                        count++;
                    }
                    if(clickedIndex == count){
                        break;
                    }
                    c++;
                }
                System.out.println("Clicked index to return: " + ((clickedIndex != 1) ? c + 1 : 0));
                return (clickedIndex != 1) ? c + 1 : 0;
        }

        public void setPlayerName(String playerName){
                this.playerName = playerName;
        }
        
        public boolean[] getObjDoneArray(){
                return objDone;
        }

        public void removeQuestLine(int index) {
                if (index < 0 || index >= questSize) {
                    return; // Guard against invalid indices
                }
        
                // Shift all elements after the removed index
                for (int i = index; i < questSize - 1; i++) {
                    qArr[i] = qArr[i + 1];
                }
                
                // Clear the last element and decrease size
                qArr[questSize - 1] = null;
                questSize--;
        
                // Also remove corresponding objective if it exists
                if (index > 0 && index - 1 < objectivesSize) {  // Subtract 1 because index 0 is quest title
                    int objIndex = index - 1;  // Convert quest index to objective index
                    removeObjectiveLine(objIndex);
                }
            }
        
            private void removeObjectiveLine(int objIndex) {
                // Find the start and end of the objective section (marked by "-" or end of array)
                int start = 0;
                int sectionCount = 0;
                
                // Find the correct section to remove
                for (int i = 0; i < objectivesSize; i++) {
                    if (oArr[i] != null && oArr[i].equals("-")) {
                        if (sectionCount == objIndex) {
                            // We found the start of the section to remove
                            removeObjectiveSection(start, i);
                            return;
                        }
                        start = i + 1;
                        sectionCount++;
                    }
                }
                
                // Handle the last section if it matches our target
                if (sectionCount == objIndex) {
                    removeObjectiveSection(start, objectivesSize - 1);
                }
            }
        
            private void removeObjectiveSection(int start, int end) {
                int sectionLength = end - start + 1;
                
                // Shift remaining elements
                for (int i = start; i < objectivesSize - sectionLength; i++) {
                    oArr[i] = oArr[i + sectionLength];
                }
                
                // Clear the last elements
                for (int i = objectivesSize - sectionLength; i < objectivesSize; i++) {
                    oArr[i] = null;
                }
                
                objectivesSize -= sectionLength;
            }
        

        public void exposition() {
                int i = 0;
                
                this.arr[i++] = "...";
                this.arr[i++] = "It has been a long day at CITU. After hours of scanning through countless lines of codes, all you can think about is the comfort of your bed and the peace of being home.";
                this.arr[i++] = "After stepping out of the university campus, you trudge toward the parking lot, exhaustion weighing heavily on your shoulders. The sun had long dipped beneath the horizon, leaving the streets bathed in the eerie glow of streetlights. You slid into the driver's seat, the familiar hum of the engine offering a slight sense of comfort as you started the car.";
                this.arr[i++] = "Pulling onto the road, you glanced in the rearview mirror and caught sight of a car idling just across the street, its headlights glaring. Shaking off the sensation, you turned onto the main road, eager to get home. But as you made each turn, the car followed. Left, right, another left-it mirrored every move. An uneasy feeling began to settle in, creeping up your spine. What was a coincidence at first now felt intentional.";
                this.arr[i++] = "\"Something is wrong.\"";
                this.arr[i++] = "Determined to shake the car off, you took an abrupt turn into a side street, speeding up as you weaved through the backroads. You heart raced, and every sharp corner felt like a calculated move. You spotted a small alley up ahead, barely wide enough for a car. Without hesitation, you darted into it and killed the engines. You sat in the darkness, holding your breath. Moments later, the other car went past the alley, oblivious. You restarted the engine and quickly sped to the opposite direction.";
                this.arr[i++] = " You had to make a detour, taking the longer, less familiar route just to be safe. As you merged onto the Cebu-Cordova Link Expressway (CCLEX), the vast expanse of the bridge stretched ahead, disappearing into the fog. The bridge is lit by the occasional, flickering tail lights of the cars in front of you.";
                this.arr[i++] = "Thunder rumbles in the distance, you are just minutes away from home.";
                this.arr[i++] = "Then suddenly, from the corner of your eye, headlights blind you-the familiar vehicle from earlier veers recklessly into your lane. The screech of tires on wet pavement fills your ears, followed by the metallic crunch of impact. You got a glimpse of the driver of the car, but before you could properly identify who it is, your own car spins, losing all control. Glass shatters. You feel the weight of your vehicle tilt as the guardrail bursts. For a split second, there is a gut-curling freefall before both vehicles plunge into the cold, dark waters below.";
                this.arr[i++] = "...";
                
                this.arr[i++] = " You wake up gasping, your chest tight with panic. There is no water filling your lungs, no agony gripping your body, yet the terror is all-consuming. Your breath comes in shallow bursts, but something is wrong-the air is still. Too still. And there's no sound. No feeling. Just a cold, oppressive silence that wraps around you like a shroud.";
                this.arr[i++] = "The world around you is dark and blurry, like a dream where nothing feels entirely real, but the fear coursing through your veins is far too tangible to be a nightmare. You try to focus, but it's like looking through a fog that refuses to lift.";
                this.arr[i++] = "Then, from the corner of your eye, a figure begins to take shape, emerging from the surrounding shadows. Cloaked in darkness, the figure's form is indistinct, more of a presence than a person. It moves without sound, its silhouette unnaturally still despite the distance between you. You don't know exactly what it is or who it might be, but a deep, primal instinct tells you that if it reaches you, it will be the end of you.";
                this.arr[i++] =" You are not ready.";
                this.arr[i++] = "The panic seizes you, harder and faster than before. Tears blur your vision as they spill down your cheeks, mixing with the cold sweat on your skin.";
                this.arr[i++] = "\"Please, stop!\"";
                this.arr[i++] = " You cry out, your voice breaking under the weight of your desperation. Your hands tremble as you raise them instinctively, as if you could hold off the approaching figure with sheer will alone.";
                this.arr[i++] = "\"Don't do this. I'm not ready yet! It's not my time!\"";
                this.arr[i++] = "But the figure continues its silent approach, unyielding, like death itself creeping closer with each passing second. The numbness of death begins to settle in, your heart pounding as though it knows the end is near. But then... the figure pauses.";
                this.arr[i++] = "It halts just within reach, but it does not vanish. Instead, the silence seems to stretch as the figure sits there, almost as if... considering you.";
                
                this.arr[i++] = " You can feel it watching you, though there are no visible eyes. Just the weight of its attention pressing down on you. ";
                this.arr[i++] = "Sensing your opportunity, you speak, your voice trembling but urgent.";
                this.arr[i++] = "\"I shouldn't be here! This wasn't supposed to happen! I don't know who killed me, or why. It's unfair. I didn't deserve this! I'm innocent!\"";
                this.arr[i++] = "The words come spilling out in a rush, each one more desperate than the last.";
                this.arr[i++] = "The figure shifts, as though weighing your plea. Then, finally, it speaks.";
                this.arr[i++] = "\"If I had to let you go, I'd be fired from my job. Haha.\"";
                this.arr[i++] = "The voice is casual-unexpectedly light, even sarcastic. A far cry from the menacing silhouette that stands before you. You blink, confusion cutting through the fog of fear.  Its voice resembled that of Mark Edward Fischbach which made you feel like you were losing your mind.";
                this.arr[i++] = "\"Is this some sick joke? Did I really die?\"";
                this.arr[i++] = "The figure laughs again, a low rumble of amusement.";
                this.arr[i++] = "\"Dead? Yeah, sorry to break it to you, but you're definitely not alive anymore.\"";
                
                this.arr[i++] = " Your mind races, your pulse still erratic as the figure's words sink in. “But... this isn't right. I don't even know why this happened. It wasn't my time. You can't just... take me!” You push back, your desperation returning in full force.";
                this.arr[i++] = "It leans in, voice dropping to a mock-serious tone. \"I'll let you go back, but you're not getting off scot-free. You want justice? Fine. Go find out who killed you. Track them down, and maybe I'll think about letting you go.\"";
                this.arr[i++] = "\"Is that-\"";
                this.arr[i++] = "Without warning, the figure vanishes, dissolving into the shadows like smoke in the wind. The oppressive darkness that had clung to you begins to thin, and the world around you shifts. The void melts away, revealing a ghostly cityscape-familiar yet warped.";
                this.arr[i++] = "The streets are empty, but whispers echo in the air, faint voices that seem to come from nowhere and everywhere at once. The sky above is a dark, and the air cthis.arries a strange feeling.";
                this.arr[i++] = " You stand at the edge of the city, your breath catching in your throat as you take in the eerie, lifeless surroundings. This is no place you have ever known, but somehow, it feels like a shadow of the world you once lived in.";
                this.arr[i++] = " You've been given a second chance, but the rules are unclear. The figure's taunts echo in your mind as you step forward into the ghostly city, unsure of what comes next. The streets stretch ahead of you, silent and ominous, and somewhere out there lies the answer to the mystery of your death.";
                this.arr[i++] = "However, the afterlife has its own dangers. You may not know the rules here, but you know one thing for certain: you are not alone. There are other lost souls here, and not all of them are friendly. Some might help you. Others... might try to keep you from ever finding the truth.";
                this.arr[i++] = " Your journey begins now.";
                this.arr[i++] = "...";

                this.size = i;
        }

        public void missConstanceIntro(String playerType, String worldType) {
                int i = 0;
            
                if (worldType.equals("world1")) {
                    this.arr[i++] = "[You enter a misty area of the underworld and spot an ordinary woman sitting on a rock, talking to herself. She looks up and her eyes brighten.]";
                    this.arr[i++] = "Miss Constance: \"Oh! A new face! Finally, some fresh meat—I mean, company! You must be dying to know who's who down here, right? Well, you've found the one and only Miss Constance! But you can call me Miss C—everyone does.\"";
                    this.arr[i++] = playerName + ": \"Actually, I—\"";
                    this.arr[i++] = "Miss C (interrupting): \"Faithful? Total creep. Stalked *everyone*—even in death! And Yoo? Ugh, such a drama queen. Always going on about his tragic past, like we don't all have skeletons in our closets! Oh, and Miggins? Tried sneaking ads into the Grand Mausoleum. I mean, really?\"";
                    this.arr[i++] = playerName + " (awkwardly): \"Yeah, well, it was nice meeting you. I'll just check out the rest of the city before—\"";
                    this.arr[i++] = "Miss C: \"Leaving already? But we're just getting started! Come back soon—I've got *so* much dirt to share. Wait till you hear about Myself!\"";
                    this.arr[i++] = "[She's a little too much to handle right now, so you slowly back away, her voice trailing after you.]";
                    this.arr[i++] = "Miss C (calling out): \"Don't forget to visit! And maybe avoid the monsters, unless you like surprise fights. Just sayin'.\"";
                    this.arr[i++] = "Miss C (continuing): \"Oh, and one last thing—those skeletons by the purple portal? Could you take care of them? Thanks!\"";
                }
            
                if (worldType.equals("world2")) {
                    this.arr[i++] = "[Miss Constance suddenly stops mid-sentence, looking at you more closely.]";
                    this.arr[i++] = "Miss C: \"Wait a second... You made it to the city outskirts?! Congrats! Look at you, all adventurous!\"";
                    this.arr[i++] = playerName + ": \"Yeah, why's that such a big deal?\"";
                    this.arr[i++] = "Miss C (wide-eyed): \"Big deal? Sweetie, that's *huge*! Do you know how many people don't even make it past Mr. Bones over there?\"";
                    this.arr[i++] = playerName + ": \"A bit of skill, a lot of luck.\"";
                    this.arr[i++] = "Miss C: \"Skill and luck? Ha! More like a guardian angel pulling double shifts. Trust me, most folks get crushed trying to leave the city.\"";
                    this.arr[i++] = playerName + ": \"Why are you out here anyway?\"";
                    this.arr[i++] = "Miss C: \"Oh, you know, just enjoying the scenic death vibes. And maybe keeping tabs on you. Don't look so shocked! I get bored.\"";
                    this.arr[i++] = "Miss C: \"By the way, if you're heading deeper, watch out. The spirits out there are no joke—they'll mess with your head. Even tougher souls than you have cracked.\"";
                    if (playerType.equals("knight")) {
                        this.arr[i++] = "Miss C: \"Oh, and P.S.—I heard your little murderer friend has " + "<font color='#FF0000'>" + "freckles" + "</font>" + ". Cute, right?\"";
                    } else if (playerType.equals("wizard")) {
                        this.arr[i++] = "Miss C: \"Oh, and P.S.—I heard your little murderer friend has " + "<font color='#FF0000'>" + "scars" + "</font>" + ". Spooky!\"";
                    } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Miss C: \"Oh, and P.S.—I heard your little murderer friend has " + "<font color='#FF0000'>" + "freckles" + "</font>" + ". Adorable!\"";
                    }
                    this.arr[i++] = playerName + ": \"Great. Just great.\"";
                    this.arr[i++] = "Miss C: \"Thanks! Oh, and Chea told me. She's a doll, isn't she?\"";
                }
            
                if (worldType.equals("world3")) {
                    this.arr[i++] = "[You notice Miss Constance, her usual chatter replaced by a tense silence as she looks around nervously.]";
                    this.arr[i++] = "Miss C: \"Oh, it's you… already? I thought — never mind. You probably shouldn't stick around.\"";
                    this.arr[i++] = playerName + ": \"What's going on? You look spooked.\"";
                    this.arr[i++] = "Miss C (whispering): \"Something's wrong. The forest... it's suffocating, like it's alive and watching. Even the monsters won't go near it. Someone is waiting for you too. They were watching you all this time!\"";
                    if (playerType.equals("knight")) {
                        this.arr[i++] = "Miss C (shaking her head): \"Whoever's waiting for you always has a " + "<font color='#FF0000'>" + "frightening frown" + "</font>" + ". Creepy, huh?\"";
                    } else if (playerType.equals("wizard")) {
                        this.arr[i++] = "Miss C (shaking her head): \"Whoever's waiting for you has this " + "<font color='#FF0000'>" + "empty expression" + "</font>" + ", like they're staring into your soul. Gives me chills.\"";
                    } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Miss C (shaking her head): \"Whoever's waiting for you always wears a " + "<font color='#FF0000'>" + "sinister smile" + "</font>" + ". Gives me the creeps.\"";
                    }
                    this.arr[i++] = playerName + ": \"Thanks for the heads-up. Guess I'll find out soon enough.\"";
                    this.arr[i++] = "[She gives you a weak smile but says no more as you step into the ominous forest.]";
                }
            
                this.size = i;
        }
            
        public void missConstanceQuests(String worldType) {
                int i = 0;
                int q = 0;
                int o = 0;
                // Initial Dialogue
                boolean isQuestDone = objDone[0] && objDone[1];
            
                if (worldType.equals("world1")) {
                        // Quest Title
                        if(isQuestDone) {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        } else if(objDone[0]) {
                            this.qArr[q++] = "<font color='#00d5ff'>Objective #1 Complete!</font>";
                        } else {
                            this.qArr[q++] = "Quest: Investigate The City";
                        }

                        // Revised Quest Objectives
                        if (!objDone[0]) {
                                this.qArr[q++] = "Objective #1: Talk to everyone.";
                        } else {
                                this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Finished talking to everyone</font>";
                        }
                
                        if (!objDone[1]) {
                                this.qArr[q++] = "Objective #2: Enter the green portal and confront the skeleton minion.";
                        } else {
                                this.qArr[q++] = "<font color='#FFFF00'>- Objective #2 Done!: Defeated the skeleton minion</font>";
                        }
                
                        // Objective Dialogue
                        if (!objDone[0]) {
                                this.oArr[o++] = "Gather clues from the local denizens; they might possess valuable information about your nemesis.";
                                this.oArr[o++] = "-";
                        } else {
                                this.oArr[o++] = "Clues from the locals will greatly help you determine the identity of your murderer!";
                                this.oArr[o++] = "Fun Fact: <font color='#fc0303'>- Red</font> is often used in contexts where it signifies important information, danger, or attention-grabbing elements.";
                                this.oArr[o++] = "-";
                        }
                
                        if (!objDone[1]) {
                                this.oArr[o++] = "These pesky mindless minions will do anything to stop you from reaching their boss.";
                        } else {
                                this.oArr[o++] = "Now there is nothing between you and the Executioner.";
                        }
                
                        // Dialogue Sequence
                        if (!objDone[0] && !objDone[1]) {
                                this.arr[i++] = playerName + ": \"Why is the portal blocked anyway?\"";
                                this.arr[i++] = "Miss C: \"[cheery sarcasm] Oh, you've finally noticed the chaos? Took you long enough!\"";
                                this.arr[i++] = playerName + ": \"Look, I'm just trying to figure out what's going on. Why can't anyone get through?\"";
                                this.arr[i++] = "Miss C: \"Well, some wannabe overlord decided to set up shop near the portals. It's soooo annoying!\"";
                                this.arr[i++] = playerName + ": \"What kind of overlord are we talking about?\"";
                                this.arr[i++] = "Miss C: \"Oh, the usual kind: leader of a skelegang, just a big ol' mess. And his little minions? Ugh, just... don't get me started.\"";
                                this.arr[i++] = playerName + ": \"Great... How do I stop him?\"";
                                this.arr[i++] = "Miss C: \"Well, first, you've gotta deal with his minions. I tried talking to them, and let's just say they sent me flying across the street... good times, not!\"";
                                this.arr[i++] = playerName + ": \"Where?\"";
                                this.arr[i++] = "Miss C: \"There's a green portal down the street. It'll spawn any minute now. Those skeletons love to use the green portals. Real handy for getting around the city.\"";
                                this.arr[i++] = playerName + ": \"How long will I have to wait exactly?\"";
                                this.arr[i++] = "Miss C: \"Well, patience is a virtue... I guess? It'll spawn soon enough, trust me. But in the meantime, why don't you get to know the locals? They're all a bunch of weirdos. You might as well, since you have all the time in the afterlife to kill.\"";
                                this.arr[i++] = "Miss C: \"Like, Yoo? Natty the nuts? That guy over there? Oh, and the old lady. Such a charming bunch.\"";
                                this.arr[i++] = "Miss C: \"Once you're done with that, come back to me! I just *love* chatting!\"";
                                this.arr[i++] = playerName + ": \"Ehhh...\"";
                                this.arr[i++] = "Miss C: \"[Gestures dramatically toward the distant portal] Maybe you'll even find some clues about your mysterious killer, oooooo~\" [giggles]";
                                this.arr[i++] = playerName + ": \"Wait, how did you know I was looking for them?\"";
                                this.arr[i++] = "Miss C: \"Oh, you were mumbling about it earlier. It wasn't hard to pick up. I guess you're the victim?\"";
                                this.arr[i++] = playerName + ": \"Duh! Hello?\"";
                                this.arr[i++] = "Miss C: \"Oh, just one more thing. That Executioner doesn't just play with magic—he lives for it. Make sure you're not his next meal or minion, alright?\"";
                                this.arr[i++] = playerName + ": \"Thanks. I'll get started.\"";
                                this.arr[i++] = "Miss C: \"You better! The city's counting on you... and so am I. Hehehe.\"";
                        } else if (objDone[0]) {
                                i = 0;
                                for (int l = 0; l < objectivesSize; l++) {
                                        this.arr[i++] = this.oArr[l];
                                }
                        } else if (objDone[1]) {
                                for (int l = 0; l < objectivesSize; l++) {
                                        this.arr[i++] = this.oArr[l];
                                }
                        }
                } else if (worldType.equals("world2")) {
                        // Quest Title
                        if(isQuestDone) {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        } else if(objDone[0]) {
                            this.qArr[q++] = "<font color='#00d5ff'>Objective #1 Complete!</font>";
                        } else {
                            this.qArr[q++] = "Quest: Purge the City's Outskirts";
                        }
                
                        // Revised Quest Objectives
                        if (!objDone[0]) {
                                this.qArr[q++] = "Objective #1: Speak with the locals to gather information.";
                        } else {
                                this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Gathered information from the locals</font>";
                        }
                
                        if (!objDone[1]) {
                                this.qArr[q++] = "Objective #2: Confront the assassin hunting you down.";
                        } else {
                                this.qArr[q++] = "<font color='#FFFF00'>- Objective #2 Done!: Defeated the assassin</font>";
                        }
                
                        // Adding objectives dialogue
                        if (!objDone[0]) {
                                this.oArr[o++] = "The locals are aware of an assassin lurking near the outskirts. They might have info on your suspect's whereabouts.";
                                this.oArr[o++] = "-";
                        } else {
                                this.oArr[o++] = "The locals' info points to a secluded area. It seems there's a portal leading somewhere... What's on the other side?";
                                this.oArr[o++] = "-";
                        }
                
                        if (!objDone[1]) {
                                this.oArr[o++] = "The spirit's aura of fear has kept the area abandoned. You must face it to stop its reign of terror.";
                        } else {
                                this.oArr[o++] = "With the spirit defeated, the city's outskirts are safe once more.";
                        }

                        // Dialogue Sequence
                        if (!objDone[0] && !objDone[1]) {
                                this.arr[i++] = playerName + ": \"What's up with the outskirts? Why is it so empty? The city is just right over there.\"";
                                this.arr[i++] = "Miss C: \"Oh, you haven't heard? There's a nasty spirit lurking out there. Creepy, right?\"";
                                this.arr[i++] = playerName + ": \"[sarcastic] Oh nooo... How can it be?\"";
                                this.arr[i++] = "Miss C: \"HAHAHA! Oh honey, that's the least of your worries!\"";
                                this.arr[i++] = playerName + ": \"What's that supposed to mean?!\"";
                                this.arr[i++] = "Miss C: \"I heard the Executioner sent an assassin after you! Bet you made him *really* mad! HAHAHA!\"";
                                this.arr[i++] = playerName + ": \"That's not funny! You need to help me!\"";
                                this.arr[i++] = "Miss C: \"Try asking the locals if they've seen this little assassin. He's sneaky, you know? You wouldn't even see him coming.\"";
                                this.arr[i++] = playerName + ": \"[Groan] When will this nightmare end?\"";
                                this.arr[i++] = "Miss C: \"Oh, darling. It's just beginning. But cheer up! You'll make it! After all, you're the hero of this world, right?\"";
                        } else if (objDone[0] && objDone[1]) {
                                this.arr[i++] = playerName + ": \"That assassin wasn't that much of a trouble.\"";
                                this.arr[i++] = "Miss C: \"Oh, look at you, all triumphant! Color me impressed! You've got more grit than I gave you credit for.\"";
                                this.arr[i++] = playerName + ": \"Yeah, but it doesn't feel like a victory yet. The ominous spirit is still out there.\"";
                                this.arr[i++] = "Miss C: \"Pfft, details! The fact you're here means you've already won half the battle.\"";
                                this.arr[i++] = playerName + ": \"Half the battle? That's not exactly comforting.\"";
                                this.arr[i++] = "Miss C: \"Oh, don't be so dramatic. You've proven you can handle yourself. That assassin didn't stand a chance, I bet!\"";
                                this.arr[i++] = playerName + ": \"You really think so?\"";
                                this.arr[i++] = "Miss C: \"Absolutely! But if I were you, I'd stop standing around here and start sniffing out more clues. Hero work waits for no one!.\"";
                        } else if (objDone[0]) {
                                i = 0;
                                for (int l = 0; l < objectivesSize; l++) {
                                        this.arr[i++] = this.oArr[l];
                                }
                        } else if (objDone[1]) {
                                for (int l = 0; l < objectivesSize; l++) {
                                        this.arr[i++] = this.oArr[l];
                                }
                        }
                } else if (worldType.equals("world3")) {
                        // Quest Title
                        if(isQuestDone) {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        } else if(objDone[0]) {
                            this.qArr[q++] = "<font color='#00d5ff'>Objective #1 Complete!</font>";
                        } else {
                            this.qArr[q++] = "Quest: Explore and purify the forest";
                        }
                    
                        // Revised Quest Objectives
                        if (!objDone[0]) {
                            this.qArr[q++] = "Objective #1: Speak with the bystanders to gather information about the chaos.";
                        } else {
                            this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Learned more about the chaos</font>";
                        }
                    
                        if (!objDone[1]) {
                            this.qArr[q++] = "Objective #2: Face the minion deep in the forest.";
                        } else {
                            this.qArr[q++] = "<font color='#FFFF00'>- Objective #2 Done!: Vanquished the minion</font>";
                        }
                    
                        //objectives dialogue
                        if (!objDone[0]) {
                            this.oArr[o++] = "The locals mention eerie disturbances and destructive rampages in the forest. They suspect dark forces are involved.";
                            this.oArr[o++] = "-";
                        } else {
                            this.oArr[o++] = "The locals' insights lead you to a thicket along the forest path, a dark aura surges among the brambly branches. Something awaits beyond the green portal ahead.";
                            this.oArr[o++] = "-";
                        }
                    
                        if (!objDone[1]) {
                            this.oArr[o++] = "Another minion was sent to stop you. You must face it to proceed deeper into the forest and in turn, deeper into the truth.";
                        } else {
                            this.oArr[o++] = "Minion exterminated, the forest thanks you. However, something bigger is approaching.";
                        }
                    
                        // Dialogue Sequence
                        if (!objDone[0] && !objDone[1]) {
                            this.arr[i++] = playerName + ": \"Why does the forest feel... wrong? Something's out there, isn't it?\"";
                            this.arr[i++] = "Miss C: \"Oh, darling, you're quite perceptive! It's not just *something*—there's something evil causing havoc.\"";
                            this.arr[i++] = playerName + ": \"Let me guess. My nemesis summoned it or perhaps sent it on my way?\"";
                            this.arr[i++] = "Miss C: \"I'm not sure, it's stirring up all kinds of trouble though. Everyone seems tensed!\"";
                            this.arr[i++] = playerName + ": \"Great. Just what I needed. Any advice?\"";
                            this.arr[i++] = "Miss C: \"Start with the bystanders. They might point you to the source of this mischief.\"";
                            this.arr[i++] = playerName + ": \"Why can't this ever be easy?\"";
                            this.arr[i++] = "Miss C: \"Oh, sweetie, you're the hero! If it were easy, you'd be out of a job.\"";
                            this.arr[i++] = playerName + ": \"Right. I'll get to it, then.\"";
                        } else if (objDone[0] && objDone[1]) {
                            this.arr[i++] = playerName + ": \"The minion is defeated. The forest is finally less dangerous. I think?\"";
                            this.arr[i++] = "Miss C: \"Look at you, the savior of greenery! I'm so proud.\"";
                            this.arr[i++] = playerName + ": \"It wasn't easy, but I guess someone had to do it.\"";
                            this.arr[i++] = "Miss C: \"Oh, no doubt about that! And your nemesis? Bet they're trembling now!\"";
                            this.arr[i++] = playerName + ": \"If only. I'm sure they've got more up their sleeve.\"";
                            this.arr[i++] = "Miss C: \"Pfft, let them try. You've got this, hero.\"";
                        } else if (objDone[0]) {
                            i = 0;
                            for (int l = 0; l < objectivesSize; l++) {
                                this.arr[i++] = this.oArr[l];
                            }
                        } else if (objDone[1]) {
                            for (int l = 0; l < objectivesSize; l++) {
                                this.arr[i++] = this.oArr[l];
                            }
                        }
                }                                      

                this.size = i;
                this.questSize = q;
                this.objectivesSize = o;
        }

        public void missConstanceLines(String playerType, String worldType) {
                int i = 0;
            
                if (worldType.equals("world3")) {
                    this.arr[i++] = "<font color='#00FFFF'>" + playerName + ": \"Which way leads deeper into the forest?\"" + "</font>";
                    this.arr[i++] = "Miss C: \"Well, I *could* tell you, but it's far more fun to let you stumble around like a clueless ghost. Just kidding—head towards the green portal, and it should lead you to where you need to go. I'd say good luck, but, like, what's the point?\"";
                    
                    this.arr[i++] = playerName + ": \"Do you have any clue who could be here?\"";
                    this.arr[i++] = "Miss C: \"I told you already, didn't I? Don't go wandering off without a clue. Bad idea, trust me. But if you must, check in with the others—they might have seen something... or not. It's the afterlife; everyone's too tired to care.\"";
                    
                    this.arr[i++] = playerName + ": \"The others seem not to care.\"";
                    this.arr[i++] = "Miss C: \"Oh, they're totally *clueless*. I'm too scared to talk to them myself! Too risky. Some of them have… questionable habits. You'll get used to the whole 'silent, brooding' thing. Or not. Your choice.\"";
                    
                    this.size = i;
                    return;
                }
            
                if (worldType.equals("world2")) {   
                    this.arr[i++] = "<font color='#00FFFF'>" + "Where to now?" + "</font>";
                    this.arr[i++] = "Miss C: \"Walk straight ahead, chat with the others—ugh, I can't believe I'm saying this—and there should be a portal that pops up at the end of the path. More of Mr. Bones' minions will be waiting for you. Word on the afterlife street is, they're super *angry*. HAHAHA!\"";
            
                    this.arr[i++] = playerName + ": \"Everyone is avoiding you, they said—\"";
                    this.arr[i++] = "Miss C: \"Oh, they all love to pretend they're too busy for me. Please. I tried talking to one of them earlier. It's like trying to get a conversation out of a rock. And they act like being moody is a personality trait. Ridiculous, right?\"";
            
                    this.arr[i++] = playerName + ": \"Are you close with Yoo?\"";
                    this.arr[i++] = "Miss C: \"Yoo? Oh, honey, *Yoo* is that guy sitting all alone under a tree, pretending to be deep and mysterious. We get it, Yoo, you're *dark and brooding.* So original. Like, get over yourself, darling. Join the club, we're all miserable down here!\"";
            
                    this.arr[i++] = playerName + ": \"Have you visited the shop over there?\"";
                    this.arr[i++] = "Miss C: \"Oh, you mean Miggins? She's trying so hard to impress everyone with her 'Homemade Pies.' I've tasted them, and let me tell you, those pies are from the *store*—not homemade! It's pathetic, honestly.\"";
            
                    this.arr[i++] = playerName + ": \"Are you happy here?\"";
                    this.arr[i++] = "Miss C: \"Happy? No. 'Death is peaceful'? Pfft, lies. It's boring as heck. No one talks. Except me, of course. If I didn't talk, I'd go completely insane. Maybe I already have. Who can even tell?\"";
            
                    this.arr[i++] = playerName + ": \"Why are you giggling?\"";
                    this.arr[i++] = "Miss C: \"Oh my gosh, like, if trees emitted Wi-Fi signals, they'd basically be cell towers, right? And, I mean, phones are basically fruits of the tree now. *It's science*, Iphone! Don't make me explain how apples are like smartphones. I could go on... but let's not.\"";
                }
            
                this.arr[i++] = "<font color='#00FFFF'>" + playerName + ": \"Can you help me out? I need to find someone, this place is way too big for me to search alone.\"" + "</font>";
                this.arr[i++] = "Miss C: \"Sure, why not? Ask the locals—they might have seen your person. Can't promise they'll actually *remember* anything, though. But hey, it's worth a shot!\"";
            
                this.arr[i++] = playerName + ": \"What are you waiting for? You look impatient.\"";
                this.arr[i++] = "Miss C: \"Waiting for something exciting to happen! But no, just the same old boring spirit world. I was hoping a ghost would show up, but nope. It's like they're avoiding me! Honestly, *rude.*\"";
            
                this.arr[i++] = playerName + ": \"Do you know anyone who is still alive?\"";
                this.arr[i++] = "Miss C: \"Oh please, do you think anyone remembers me? I wasn't exactly a star back in my day. Just a regular city girl. But imagine—*imagine*—if there was a statue somewhere with my name on it? In a park, next to some pigeons. A girl can dream, even in the afterlife.\"";
            
                this.arr[i++] = playerName + ": \"Do you have any friends?\"";
                this.arr[i++] = "Miss C: \"I tried! Seriously, I tried. But the souls here are, like, totally not into making friends. They're all just 'too cool' for me. You'd think we'd bond over being stuck together forever. But nope. No one laughs at my jokes. *So rude.*\"";
            
                this.arr[i++] = playerName + ": \"Have you been here for a while?\"";
                this.arr[i++] = "Miss C: \"How long have I been dead, you ask? Well, time doesn't really work the same down here. It could've been a thousand years or just ten minutes. I really can't tell. By the way, what *year* is it? Because I have no idea. Does anyone?\"";
            
                this.arr[i++] = playerName + ": \"Do you like it here? What do you think of this place?\"";
                this.arr[i++] = "Miss C: \"Do I like it here? Ugh, it's as bleak as you'd think. No sun, no stars, just endless fog. It's like living inside a cloud. And don't even get me started on the fog—it's a wet blanket for your soul, 24/7.\"";
            
                this.size = i;
        }
            

        public void nattyIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + " wander through a shadowy, eerie part of the underworld. A woman stands alone by the edge of a dark river, her eyes darting nervously. You approach cautiously, sensing the tension in the air.";
                this.arr[i++] = "Natty: \"Oh... it's you? I-I didn't expect to see you again, I'm Natty. I thought maybe... maybe you'd forgotten me. It's so dark here, and I'm not sure I can do this... I'm scared.\"";
                this.arr[i++] = playerName + ": \"Have we really met before?. I have my fair share with souls like you, I think?\"";
                this.arr[i++] = "Natty: \"Before? Ha! Of course you did! Also, I'm pretty new here. This place kind of spooky, right? I've been through worse! They can't hurt me then, and this place won't break me now!\"";
                this.arr[i++] = "Natty: \"But... what if I'm not strong enough this time? Last time, I barely made it. This place... it's worse than anything we've seen before. I-I don't think I can survive it... I'm too scared.\"";
                this.arr[i++] = playerName + ": \"Just take a deep breath. You're not exactly alone here, Natty. We can handle this.\"";
                this.arr[i++] = "Natty: \"Alone? No! No! No! I'm never alone! That won't beat me, not now, not ever! I'm not the fragile soul you thought I was. I'm a unstoppable! I can handle anything! HAHAHA!\"";
                this.arr[i++] = playerName + ": \"Are you... Okay? You're swinging between your moods way too fast.\"";
                this.arr[i++] = "Natty: \"No... No, I didn't! I... I can't!\"";
                this.arr[i++] = playerName + " reach out, watching as Natty shifts between defiance and fear, her emotions like a pendulum. She seems caught in an internal struggle, and it might be best to leave her to calm down before speaking further.";
        
                this.size = i;
        }
        
        public void nattyLines() {
                int i = 0;

                this.arr[i++] = playerName + ": \"This city looks really spooky. What do you think?\"";
                this.arr[i++] = "Natty: \"There's something about this place... it whispers to me, you know? Almost like it knows who I am.\"";

                this.arr[i++] = playerName + ": \"Is there a way out of this city?\"";
                this.arr[i++] = "Natty: \"I guess... There's no escape, is there? Feels like I've been walking in circles for hours, days, years, oh no.\"";
                
                this.arr[i++] = playerName + ": You look lonely, why are you avoiding the others?\"";
                this.arr[i++] = "Natty: \"They said I wouldn't last a day on my own. But look at me now, still breathing! This place isn't as bad as where I came from.\"";
                
                this.arr[i++] = playerName + ": \"What was your life like before? Can you tell me about how things used to be?\"";
                this.arr[i++] = "Natty: \"It was great! I don't remember much, but I'm pretty sure it was full of happiness, I think? Honestly, though, everything feels like a blur now. Hahaha.\"";
                                
                this.arr[i++] = playerName + ": \"No offense, but some souls here have mentioned that you're a bit mental. What happened?\"";
                this.arr[i++] = "Natty: You think I'm crazy? Oh, please! If I were, you wouldn't be here talking to me! You just need something from poor little Natty, don't you? Shame on you.\"";
                                
                this.arr[i++] = playerName + ": \"Why are you giggling over there?\"";
                this.arr[i++] = "Natty: \"Wanna a hear a joke? Who sell fake pies and calls herself Miggins?\" [chuckles to herself]";
                               
                this.arr[i++] = playerName + ": You seem happier than usual. What's up?\"";
                this.arr[i++] = "Natty: \"Why are asylums full of singles? Because none of them can keep their thoughts together!\" [laughs hysterically]";
                             
                this.arr[i++] = playerName + ": You look anxious, am I scaring you?\"";
                this.arr[i++] = "Natty: You think I'm afraid of you? No. I'm not afraid of you...\"";
                               
                this.arr[i++] = "[Natty appears to panicking] You: \"Hey, is something wrong?\"";
                this.arr[i++] = "Natty: \"I can't do this! The voices—they're everywhere! Crawling under my skin, it tickles! HAHAHAHA!\"";

                this.arr[i++] = playerName + ": \"Hey, I'm looking for someone. Have you met anyone here who might have died from drowning?\"";
                this.arr[i++] = "Natty: \"Yeah. I mean, no. I mean, I-I don't know. I don't ask souls about their death.\"";

                this.arr[i++] = playerName + ": \"I died drowning from vehicular homicide. How about you?\"";
                this.arr[i++] = "Natty: \"I died trying to escape the asylum I'm from. Let's just say that they did an amazing job of keeping me in.\"";

                this.size = i;
        }

        public void yooIntro() {
                int i = 0;

                this.arr[i++] = playerName + ": \"Do I know you from somewhere? You seem... familiar, somehow.\"";
                this.arr[i++] = "Yoo: \"...\"";
                this.arr[i++] = playerName + ": \"Um, it's strange. I can't quite place you, but it feels like we've crossed paths before.\"";
                this.arr[i++] = "Yoo: \"...\"";
                this.arr[i++] = playerName + ": \"Um, hello? Ah, I see. You're not like others, I guess. Are you the quiet type?\"";
                this.arr[i++] = "Yoo: \"What'd ya expect? You think souls just talk with strangers down here?\"";
                this.arr[i++] = playerName + ": \"Sorry. It's just, are you...\"";
                this.arr[i++] = "Yoo: \"Single? Sorry, you're a little too late for that.\"";
                this.arr[i++] = playerName + ": \"O-oh, I mean. Are you new here?\"";
                this.arr[i++] = "Yoo: \"I'm just someone passing through. Like everyone else. I'm here for a while now.\"";
                this.arr[i++] = playerName + ": \"Why are you all the way here, anyway? You don't like the souls over there?\"";
                this.arr[i++] = "Yoo: \"The reason doesn't matter.\"";
                this.arr[i++] = playerName + ": \"Sorry for prying, I was just curious.\"";
                this.arr[i++] = "Yoo: You should mind your own business. You seem to be the nosy type, like Constance.\"";
                this.arr[i++] = playerName + ": \"Excuse me? I'm just trying to get to know you better.\"";
                this.arr[i++] = "Yoo: \"My story isn't worth telling. Beat it.\"";

                this.size = i;
        }
           
        public void yooLines() {
                int i = 0;

                this.arr[i++] = playerName + ": \"Hey, what's up?\"";
                this.arr[i++] = "Yoo: \"...\"";    

                this.arr[i++] = playerName + ": \"Hey, you look familar. Do you attend CITU?\"";
                this.arr[i++] = "Yoo: \"Don't look at me, creep.\"";

                this.arr[i++] = playerName + ": \"Look, I'm looking for this soul. I don't exactly remember what they look like, but they might have came by here. They died too, I think?\"";
                this.arr[i++] = "Yoo: \"I see souls come here and go everyday. I don't care enough to remember faces, go ask somebody else.\"";
                
                this.arr[i++] = playerName + ": \"Have you tried out that shop over there?\"";
                this.arr[i++] = "Yoo: \"I hate it, Miggins' pies taste like medicine.\"";
                
                this.arr[i++] = playerName + ": \"I died like hours ago, I think? Some psycho killed me. They rammed my car, it's so unfair.\"";
                this.arr[i++] = "Yoo: You died unexpectedly? Damn, like everyone else here did? It's not my fault you're a bad driver.\"";
                
                this.arr[i++] = playerName + ": \"Hi-\"";
                this.arr[i++] = "Yoo: \"Go away.\"";

                this.arr[i++] = playerName + ": \"Hey, which way leads to the exit of the city?\"";
                this.arr[i++] = "Yoo: \"I don't know, why are you asking me? You're so annoying...\"";
                
                this.arr[i++] = playerName + "You look disgusted. Is something wrong?\"";
                this.arr[i++] = "Yoo: You reek of seafood. Gross...\"";
                
                this.arr[i++] = playerName + ": \"Is it me or is it always raining around this part of the city?\"";
                this.arr[i++] = "Yoo: \"Jeez. I wonder why...\" [eye rolls]";
                
                this.arr[i++] = playerName + ": \"It's so foggy, there's no way it has to be this THICK. Why is it so foggy?\"";
                this.arr[i++] = "Yoo: \"The weather here corresponds with the emotions of those who live under it, now shut it and leave me alone.\"";
        
                this.size = i;
        }

        public void migginsIntro(String playerType, String worldType) {
                int i = 0;
            
                if (worldType == "world1") {
                        this.arr[i++] = playerName + ": \"I feel like I know you. Have we met before?\"";
                        this.arr[i++] = "Miggins: \"Oh, my dear, I doubt it. But then again, faces blur after a while down here, don't they?\"";
                        this.arr[i++] = playerName + ": \"Something smells good.\"";
                        this.arr[i++] = "Miggins: \"Could be the scent of my pies. I sell it to almost everyone who wanders through. Hard to resist a good snack, even in the afterlife.\"";
                        this.arr[i++] = playerName + ": \"That sounds lovely, I'd do anything for a slice of pi- wait. Do I need to eat? I'm already dead.\"";
                        this.arr[i++] = "Miggins: \"Well, sweetheart, the thing is... Souls do get hungry, they just don't die from it.\"";
                        this.arr[i++] = playerName + ": \"Oh...\"";
                        this.arr[i++] = "Miggins: \"Still hungry? Not for long. My store is still very much alive! Are you interested in some apple pies or apple potions?\"";
                        this.arr[i++] = playerName + ": \"So, how much would that be?\"";
                        this.arr[i++] = "Miggins: \"Oh, cheaper than most, I assure you.\"";
                        this.arr[i++] = playerName + ": \"Why set up shop all the way over here?\"";
                        this.arr[i++] = "Miggins: \"Oh, I decided to set up shop here since souls like you arrive here often after death. It's also pretty peaceful around these parts.\"";
                        this.arr[i++] = playerName + ": \"Well, that would also mean that 'they' are also here...\"";
                        this.arr[i++] = "Miggins: \"Who?\"";
                        this.arr[i++] = playerName + ": \"Someone that I was searching for. It's important.\"";
                        this.arr[i++] = "Miggins: \"Oh, then I hope you find them soon. You're more than welcome to come back if you wish to purchase something, dear.\"";
                }
            
                if (worldType == "world2") {
                        this.arr[i++] = "Miggins: \"Well, look who it is! I was worried sick!\"";
                        this.arr[i++] = playerName + ": \"Why?\"";
                        this.arr[i++] = "Miggins: \"The city outskirts are no joke. That spirit out there has been tormenting everyone who tries to get close!\"";
                        this.arr[i++] = playerName + ": \"I noticed... It wasn't easy getting here.\"";
                        this.arr[i++] = "Miggins: You're tougher than you look, then! Maybe you can do something about it—beat that thing, would you?\"";
                        this.arr[i++] = playerName + ": \"It's not on my to-do list, but I'll keep it in mind.\"";
                        this.arr[i++] = "Miggins: \"Keep it in mind? Darling, if you don't, that thing'll keep scaring off my customers! Think of my livelihood!\"";
                        this.arr[i++] = playerName + ": \"Well, I guess I have to now.\"";
                        this.arr[i++] = "Miggins: \"That's the spirit! You're a hero already. Don't let me down, child!\"";
                }
            
                if (worldType == "world3") {
                        this.arr[i++] = "[Miggins glances at you nervously, wringing his hands as you approach.]";
                        this.arr[i++] = "Miggins: \"Oh, no, no, no... This isn't good at all.\"";
                        this.arr[i++] = playerName + ": \"What's wrong?\"";
                        this.arr[i++] = "Miggins: \"What's wrong?! That killer of yours sent something truly awful this time, a demon born of pure malice. It's out there hunting for you!\"";
                        this.arr[i++] = playerName + ": \"I figured something was up. Miss Constance mentioned a dark aura in the forest.\"";
                        this.arr[i++] = "Miggins: \"A dark aura? Try sheer terror! That thing is relentless. You can't possibly take it on alone!\"";
                        this.arr[i++] = playerName + ": \"I don't have much of a choice, do I?\"";
                        this.arr[i++] = "[Miggins shakes his head, her face pale with worry.]";
                        this.arr[i++] = "Miggins: \"Just promise me you'll be careful. If something happens to you... Well, I don't even want to think about it.\"";
                        this.arr[i++] = playerName + ": \"I'll handle it. Don't worry.\"";
                        this.arr[i++] = "Miggins: \"Don't worry, he says! You're braver than I could ever be, that's for sure. Good luck out there, dear.\"";
                        if (playerType == "knight") {
                                this.arr[i++] = "Miggins: \"Keep on the lookout for your killer too, I got a glimpse of them summoning that vile demon. They were far from view, but I'm certain they had " + "<font color='#FF0000'>" + "short" + "</font>" + ", " + "<font color='#FF0000'>" + "wavy hair" + "</font>" + ".\"";
                        }
                        if (playerType == "wizard") {
                                this.arr[i++] = "Miggins: \"Keep on the lookout for your killer too, I got a glimpse of them summoning that vile demon. They were far from view, but I'm certain they had " + "<font color='#FF0000'>" + "short" + "</font>" + ", " + "<font color='#FF0000'>" + "straight hair" + "</font>" + ".\"";
                        }
                        if (playerType == "priest") {
                                this.arr[i++] = "Miggins: \"Keep on the lookout for your killer too, I got a glimpse of them summoning that vile demon. They were far from view, but I'm certain they had " + "<font color='#FF0000'>" + "long" + "</font>" + ", " + "<font color='#FF0000'>" + "straight hair" + "</font>" + ".\"";
                        }
                }
            
                this.size = i;
        }
            
        public void migginsQuests(String worldType) {
                int i = 0;
                int q = 0;
                int o = 0;
                
                if (worldType == "world1") {
                        boolean isQuestDone = objDone[0];
                        if (isQuestDone) {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        } else {
                            this.qArr[q++] = "Quest: Escape the City.";
                        }
                    
                        // Revised Quest Objectives
                        if (!objDone[0]) {
                            this.qArr[q++] = "Objective #1: Defeat The Executioner Beyond the Crimson Portal.";
                        } else {
                            this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Defeated the Executioner!</font>";
                        }
                    
                        // Objective Descriptions
                        if (!objDone[0]) {
                            this.oArr[o++] = "The Executioner, a merciless enforcer sent by your nemesis, guards the Crimson Portal. To escape the city, you must defeat this terrifying foe.";
                            this.oArr[o++] = "-"; // End of Objective 1
                        } else {
                            this.oArr[o++] = "Miggins: \"Thank you for defeating the Executioner! The city can finally breathe again.\"";
                            this.oArr[o++] = playerName + ": \"It wasn't easy, but at least now people can move freely again.\"";
                            this.oArr[o++] = "Miggins: \"Indeed, and I knew you'd be the one to end this nightmare!\"";
                            this.oArr[o++] = "-";
                        }
                    
                        // Dialogue Sequences
                        if (!isQuestDone) {
                            this.arr[i++] = playerName + ": \"Can you point me to the way out of the city? This place is too vast to leave on foot.\"";
                            this.arr[i++] = "Miggins: \"Oh, bless your heart! There's a portal nearby—the Crimson Portal. But it's... complicated.\"";
                            this.arr[i++] = playerName + ": \"Complicated?\"";
                            this.arr[i++] = "Miggins: \"The Executioner, a giant brute with a thirst for blood, has locked it down. It's been terrible for business—no one trades when there's a skeleton army in the streets.\"";
                            this.arr[i++] = playerName + ": \"I'll deal with this Executioner.\"";
                            this.arr[i++] = "Miggins: \"Oh, would you? You're a lifesaver! Deal with that monster, and I'll make sure you're rewarded properly.\"";
                            this.arr[i++] = playerName + ": \"Why trust me?\"";
                            this.arr[i++] = "Miggins: \"Well, it's not like I've got a line of heroes knocking on my door. Besides, you've got that spark of determination.\"";
                            this.arr[i++] = playerName + ": \"Fine. I'll do it.\"";
                            this.arr[i++] = "Miggins: \"Good on you! But be careful, alright? That Executioner isn't powerful by accident. Come back here when it's done.\"";
                        } else {
                            for (int l = 0; l < objectivesSize; l++) {
                                this.arr[i++] = this.oArr[l];
                            }
                        }
                    }
                    
                    if (worldType == "world2") {
                        boolean isQuestDone = objDone[0];
                    
                        // Quest Objectives
                        if (!isQuestDone) {
                            this.qArr[q++] = "Quest: The Outskirt Spirit.";
                        } else {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        }
                    
                        // Revised Quest Objectives
                        if (!objDone[0]) {
                            this.qArr[q++] = "Objective #1: Defeat the Necromancer haunting the outskirts.";
                        } else {
                            this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Defeated the Necromancer!</font>";
                        }
                    
                        // Objective Descriptions
                        if (!objDone[0]) {
                            this.oArr[o++] = "A Necromancer has taken control of the outskirts, raising the dead and spreading terror. Put an end to their reign to secure the area.";
                            this.oArr[o++] = "-"; // End of Objective 1
                        } else {
                            this.oArr[o++] = "Miggins: \"Thank you for defeating the Necromancer! We can finally start rebuilding what was lost.\"";
                            this.oArr[o++] = playerName + ": \"It's not much, but at least the threat is gone.\"";
                            this.oArr[o++] = "Miggins: \"You're too modest. This is the first step to reclaiming our home. Thank you.\"";
                            this.oArr[o++] = "-";
                        }
                    
                        // Dialogue Sequences
                        if (!isQuestDone) {
                            this.arr[i++] = playerName + ": \"What's the best way to get past the outskirts?\"";
                            this.arr[i++] = "Miggins: \"Oh, dearie, it's a mess out there—foggy, spooky, and full of undead. But you can manage.\"";
                            this.arr[i++] = playerName + ": \"What should I do?\"";
                            this.arr[i++] = "Miggins: \"Head to the crimson portal and confront the source of the chaos: the Necromancer. It won't be easy, but I know you can handle it.\"";
                            this.arr[i++] = playerName + ": \"Alright. I'll take care of it.\"";
                            this.arr[i++] = "Miggins: \"Be careful out there, love. The outskirts aren't for the faint of heart. I'll be here when you're done.\"";
                        } else {
                            for (int l = 0; l < objectivesSize; l++) {
                                this.arr[i++] = this.oArr[l];
                            }
                        }
                    }              
            
                    if (worldType == "world3") {
                        boolean isQuestDone = objDone[0];
                    
                        // Quest Objectives
                        if (!isQuestDone) {
                            // Quest Title
                            this.qArr[q++] = "Quest: Cleanse the Forest.";
                        } else {
                            this.qArr[q++] = "<font color='#00f541'>Quest Complete!</font>";
                        }
                    
                        // Revised Quest Objectives
                        if (!objDone[0]) {
                            this.qArr[q++] = "Objective #1: Confront and defeat your Nemesis' minion.";
                        } else {
                            this.qArr[q++] = "<font color='#FFFF00'>- Objective #1 Done!: Defeated the minion!</font>";
                        }
                    
                        // Objective Descriptions
                        if (!objDone[0]) {
                            this.oArr[o++] = "Your killer has corrupted the forest. To restore peace, you must confront and banish them. Starting with their pesky minions.";
                            this.oArr[o++] = "-"; // End of Objective 1
                        } else {
                            this.oArr[o++] = "Miggins: \"Thank you for defeating the minion!\"";
                            this.oArr[o++] = playerName + ": \"It wasn't easy, but it's just a step closer in ending this nightmare.\"";
                            this.oArr[o++] = "Miggins: \"Indeed, and we all owe you a debt of gratitude. You're a true hero.\"";
                            this.oArr[o++] = "-";
                        }
                    
                        // Dialogue Sequences
                        if (!isQuestDone) {
                            this.arr[i++] = playerName + ": \"I need to get through the forest. Any advice?\"";
                            this.arr[i++] = "Miggins: \"Oh, the forest? This place is bad news, dear. Something dark has taken root there. But if anyone can handle it, it's you.\"";
                            this.arr[i++] = playerName + ": \"What do I need to do?\"";
                            this.arr[i++] = "Miggins: \"Your nemesis causing all sorts of trouble. It's lurking beyond the Crimson Portal near here.\"";
                            this.arr[i++] = playerName + ": \"Are they really dangerous?\"";
                            this.arr[i++] = "Miggins: \"Very. It's not just dangerous—it's cunning and cruel. But I have faith in you. I know you'll see this through.\"";
                            this.arr[i++] = playerName + ": \"I'll take care of it.\"";
                            this.arr[i++] = "Miggins: \"That's the spirit! We're all counting on you. Watch your back out there, and don't let that Nemesis fool you—it's as deceptive as it is deadly.\"";
                        } else {
                            for (int l = 0; l < objectivesSize; l++) {
                                this.arr[i++] = this.oArr[l];
                            }
                        }
                }            
            
                this.size = i;
                this.questSize = q;
                this.objectivesSize = o;
                
        }            

        public void migginsLines(String playerType, String worldType) {
                        int i = 0;

                        this.arr[i++] = playerName + ": \"What are those red bottles near the window of your shop?\"";
                        this.arr[i++] = "Miggins: \"Those red potions are apple flavored, it may taste like medicine since it is one.\"";

                        this.arr[i++] = playerName + ": \"What are those red bottles near the window of your shop?\"";
                        this.arr[i++] = "Miggins: \"Those red potions are apple flavored, it may taste like medicine since it is one.\"";
                        
                        this.arr[i++] = playerName + ": \"Why do the other souls dislike your pies?\"";
                        this.arr[i++] = "Miggins: \"Some of the denizens here just don't like me at all.\"";
                        
                        this.arr[i++] = playerName + ": \"How can apple trees grow here in total darkness?\"";
                        this.arr[i++] = "Miggins: \"Plants grow here without the need for sunlight, the faint glow of the underworld is more than enough.\"";
                        
                        this.arr[i++] = playerName + ": \"Is it okay if I come in and take a look?\"";
                        this.arr[i++] = "Miggins: \"Of course, my shop is open for all!\"";
                        
                        this.arr[i++] = playerName + ": \"I died an unjust death. I take it that you died peacefully at an old age?\"";
                        this.arr[i++] = "Miggins: \"Yep, 88. It's a shame that you died that way, perhaps I could cheer you up with some of my cookings?\"";

                if (worldType == "world2") {
                        this.arr[i++] = "<font color='#00FFFF'>" + playerName + ": \"I'm so glad to see you again, is there a shortcut that cuts right through the outskirts?\"" + "</font>";
                        this.arr[i++] = "Miggins: \"Of course! Another red portal should appear anytime now. However, is that what you want? It's best to take the longer route, since it's safer, but I understand that you're in a hurry.\"";

                        this.arr[i++] = playerName + ": \"So, I can't starve to death?\"";
                        this.arr[i++] = "Miggins: \"Of course. Some souls could even go months without food, but the hunger is way too unbearable that most wouldn't last a year without it.\"";
                        
                        this.arr[i++] = playerName + ": \"Do souls visit here often?\"";
                        this.arr[i++] = "Miggins: You'd be surprised by how many souls crave a little comfort food.\"";
                        
                        this.arr[i++] = playerName + ": \"Do you sell anything other than pies?\"";
                        this.arr[i++] = "Miggins: \"I used to sell seafood. Unfortunately, most fishes are gobbled up by slimes.\"";
                        
                        this.arr[i++] = playerName + ": \"Where are the customers?\"";
                        this.arr[i++] = "Miggins: \"The weather here recently is terrible. It's scaring them away!\"";
                        
                        this.arr[i++] = playerName + ": You're quite energetic despite having died of old age. I guess death makes you feel younger?\"";
                        this.arr[i++] = "Miggins: \"Oh, sweetie, the grave doesn't stop me from whipping up a batch of pies!\"";
                }

                if (worldType == "world3") {
                        this.arr[i++] = "<font color='#00FFFF'>" + playerName + ": \"Where's the red portal? I swear I just saw one right here.\"" + "</font>";
                        this.arr[i++] = "Miggins: \"I casted a temporary removal spell on it, there was too much negative energy, I don't think it will last long. Here, I'll undo it for you. Be careful what you find in the otherside, dear. I wish you the best.\"";

                        this.arr[i++] = playerName + ": \"Do you have any more clues about them?\"";
                        this.arr[i++] = "Miggins: \"No, I'm sorry. They seem to have shrouded themselves in a deep dark aura while conjuring spells. It's like trying too see through smoke.\"";
                
                        this.arr[i++] = playerName + ": \"Do you have any tips to defeat this demon?\"";
                        this.arr[i++] = "Miggins: \"Sorry if this comes across as a bit insensitive, but you might need to stock up on potions.\"";
                        
                        this.size = i;
                        return;
                }

                this.size = i;
        }

        public void faithfulIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"Hello. Hold on, have we met before?\"";
                this.arr[i++] = "Faithful: \"Maybe. Or maybe I just have one of those unforgettable faces.\"";
                this.arr[i++] = playerName + ": \"I don't know, but something about you feels... familiar. Maybe it's just déjà vu.\"";
                this.arr[i++] = "Faithful: \"Déjà vu? Or maybe you've just run out of interesting things to say.\"";
                this.arr[i++] = playerName + ": \"Huh? What's your deal?\"";
                this.arr[i++] = "Faithful: \"Deal? I prefer 'complex.' But sure, we'll go with mysterious for now.\"";
                this.arr[i++] = playerName + ": You don't seem like someone who mingles much. Did I hit a nerve?\"";
                this.arr[i++] = "Faithful: \"Let's just say, I prefer quality over quantity when it comes to people.\"";
                this.arr[i++] = playerName + ": \"Well, I mean... you don't exactly look thrilled to be here either.\"";
                this.arr[i++] = "Faithful: \"Oh, I'm thrilled. Can't you tell? This is my 'thrilled' face.\"";
                this.arr[i++] = playerName + ": \"There's a whole crowd over there. Don't you want to hang with them?\"";
                this.arr[i++] = "Faithful: \"Crowds? Not really my thing. Too many opinions, not enough sense.\"";
                this.arr[i++] = playerName + ": \"Okay, okay, no need to get all defensive. Just asking.\"";
                this.arr[i++] = "Faithful: \"No harm done. Just don't expect a social butterfly over here.\"";
                this.arr[i++] = playerName + ": \"Hey, I'm just trying to be friendly here.\"";
                this.arr[i++] = "Faithful: \"I'll give you credit for effort. Not many stick around this long.\"";
            
                this.size = i;
            }
            
        public void faithfulLines() {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"Hey, you seem like the type that knows their way around.\"";
                this.arr[i++] = "Faithful: \"Depends. What's the second worth to you?\"";
            
                this.arr[i++] = playerName + ": You've got a familiar look. Have we met before, like in CITU?\"";
                this.arr[i++] = "Faithful: \"Ah, CITU.. No, I don't think you'd have noticed me there.\"";
            
                this.arr[i++] = playerName + ": \"I'm looking for someone, but I can't remember their face. Ever get that feeling?\"";
                this.arr[i++] = "Faithful: \"All the time. Faces blend together after a while. What's their story?\"";
            
                this.arr[i++] = playerName + ": \"Have you been to that little place across the way? The one with the pies?\"";
                this.arr[i++] = "Faithful: \"Once. Let's just say, I've had better culinary adventures.\"";
            
                this.arr[i++] = playerName + ": \"I'm still getting used to the whole being dead thing. My car got rammed out of nowhere.\"";
                this.arr[i++] = "Faithful: \"Rough way to go. But hey, now you get to join the club.\"";
            
                this.arr[i++] = playerName + ": \"Hi there, I was wondering if—\"";
                this.arr[i++] = "Faithful: \"Careful, wondering tends to lead to trouble. What's on your mind?\"";
            
                this.arr[i++] = playerName + ": \"I'm trying to get out of this place. Any idea which way to go?\"";
                this.arr[i++] = "Faithful: \"Ah, the eternal question. If I had an answer, I wouldn't be standing here.\"";
            
                this.arr[i++] = playerName + ": You don't look too happy. Something bothering you?\"";
                this.arr[i++] = "Faithful: \"Not at all. Just my resting face. You get used to it.\"";
            
                this.arr[i++] = playerName + ": \"Why does it always seem like it's pouring rain in this city?\"";
                this.arr[i++] = "Faithful: \"Could be worse. At least it's not snowing. Yet.\"";
            
                this.arr[i++] = playerName + ": \"It's so foggy, it's like walking through a cloud. Is it always like this?\"";
                this.arr[i++] = "Faithful: \"Fog's the least of your worries around here. Keep your eyes sharp.\"";
            
                this.size = i;
        }

        //

        public void monoIntro(String playerType, String worldType) {
                int i = 0;
            
                this.arr[i++] = "[You find a man facing a wall, quietly practicing conversations with themselves.]";
                this.arr[i++] = "Mono: \"...okay, so then I say 'nice to meet you' and maybe smile? No, smiling feels weird. But people like smiling. Um... oh! Someone's actually here.\"";
                this.arr[i++] = playerName + ": \"Hello?\"";
                this.arr[i++] = "Mono: \"Oh. Hi. I'm Mono. Sorry, I was just... never mind. Um, you're new here, right? I should probably tell you about... well, I notice things. Like how there are these scratch marks on the walls. Always three lines. Might be important.\"";
                this.arr[i++] = playerName + ": \"I should probably get going—\"";
                this.arr[i++] = "Mono: \"Wait! Sorry, didn't mean to shout. It's just... there's this skeleton guard ahead. Through the green portal. Really dangerous. Thought you should know. I'd feel bad if I didn't mention it.\"";
                this.arr[i++] = "[They fidget with their sleeves while speaking, eyes darting around.]";
                this.arr[i++] = "Mono: \"I'll be here if you need anything. I like this spot. It's quiet. Good for... thinking. And I hear things. From the shadows. Not in a weird way! Just... you know what, never mind.\"";
                this.arr[i++] = "Mono: \"Um, goodbye? Yes, goodbye. That's the right thing to say now, I think.\"";
            
                this.size = i;
        }

        public void monoLines() {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"Do you always talk to yourself?\"";
                this.arr[i++] = "Mono: \"Oh! Um, yes. It helps me practice. Was I being too loud? Sometimes I forget others can hear me.\"";
            
                this.arr[i++] = playerName + ": \"What's with all the shadows you keep mentioning?\"";
                this.arr[i++] = "Mono: \"They're... well... it's hard to explain without sounding strange. More strange than usual, I mean.\"";
            
                this.arr[i++] = playerName + ": \"How long have you been here?\"";
                this.arr[i++] = "Mono: \"Time is... sorry, should I answer directly? People prefer direct answers, right? Um... long enough to memorize every crack in this rock.\"";
            
                this.arr[i++] = playerName + ": \"Do you ever leave this spot?\"";
                this.arr[i++] = "Mono: \"Sometimes. When the shadows show me where to go. That sounded creepy again, didn't it? I need to work on that.\"";
            
                this.arr[i++] = playerName + ": \"Have you seen anyone suspicious around here?\"";
                this.arr[i++] = "Mono: \"I see lots of... no, wait, that's not helpful. Let me try again. I notice things. Small things. Like how some people never leave footprints.\"";
            
                this.arr[i++] = playerName + ": \"Why do you stay here alone?\"";
                this.arr[i++] = "Mono: \"It's quiet. Easier to think. Easier to... be. Does that make sense? Sometimes I can't tell if I'm making sense.\"";
            
                this.arr[i++] = playerName + ": \"What happened to you? How did you end up here?\"";
                this.arr[i++] = "Mono: \"I... um... was trying to help someone. Didn't work out. Sorry, I don't like talking about it. Is that okay?\"";
            
                this.arr[i++] = playerName + ": \"Do you know what those strange markings mean?\"";
                this.arr[i++] = "Mono: \"Yes! I mean, maybe. The shadows whisper about them but... no, that sounds weird. Let me start over. They're important, that's all I know for sure.\"";
            
                this.arr[i++] = playerName + ": \"Have you seen others pass through here?\"";
                this.arr[i++] = "Mono: \"Many. Some stop to talk. Most don't. Which is fine! Totally fine. I'm not very good at talking anyway.\"";
            
                this.arr[i++] = playerName + ": \"What do you do all day?\"";
                this.arr[i++] = "Mono: \"Watch. Listen. Practice talking. Though clearly I need more practice. Was that supposed to be funny? I'm trying to learn humor.\"";
            
                this.size = i;
        }

        public void rubyIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + " stumble upon the edge of the city behind you. You approach the woman ahead, she's full of energy.";
                this.arr[i++] = "Ruby: \"Well, look who showed up. I'm Ruby, by the way. Not that it matters—I'm not here to make friends.\"";
                this.arr[i++] = playerName + ": \"Have we met before? I deal with so many souls... I lose track.\"";
                this.arr[i++] = "Ruby: \"Oh, trust me, you'd remember me if you had. I'm not like the rest of these lost souls. Although...\"";
                this.arr[i++] = "Ruby: \"Just 'cause I look tough doesn't mean I don't feel the weight of this place. It's... intense.\"";
                this.arr[i++] = playerName + ": \"I feel the same, this place is a bit too much sometimes.\"";
                this.arr[i++] = "Ruby: \"Ha! That's how I like it. I like a challenge. I've fought my own battles, and I'm still here.\"";
                this.arr[i++] = playerName + ": You do seem to have gone through a lot.\"";
                this.arr[i++] = "Ruby: \"I've been through worse. Just... get out of my way, alright? I've got my own problems to deal with.\"";
            
                this.size = i;
        }
            
        public void rubyLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"The city looks really unsettling from afar. What do you think?\"";
                this.arr[i++] = "Ruby: \"Unsettling? You could say that. It does look menacing from here.\"";
            
                this.arr[i++] = playerName + ": \"Which way leads to the forest?\"";
                this.arr[i++] = "Ruby: \"Just go straight ahead. But where's the fun in leaving? You can't do anything if you're always running away.\"";
            
                this.arr[i++] = playerName + ": \"What do you think of my outfit?\"";
                if (playerType.equals("knight")) {
                        this.arr[i++] = "Ruby: \"Looks bulky. It makes you look slow.\"";
                } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Ruby: \"Gosh. That nurse outfit takes me back. I remember seeing someone wearing that here way too many times.\"";
                } else {
                        this.arr[i++] = "Ruby: \"Ew, are you cosplaying an anime character?\"";
                }

                this.arr[i++] = playerName + ": \"Can you tell me about what your past life was like?\"";
                this.arr[i++] = "Ruby: \"Life was great, I hoped it stayed that way. Now, everything's gone just like that. I'm not here to reminisce.\"";
            
                this.arr[i++] = playerName + ": \"Why are you laughing by yourself?\"";
                this.arr[i++] = "Ruby: \"Just thought of something funny. Guess you'd have to be me to get it.\"";
            
                this.arr[i++] = playerName + ": You seem a bit more upbeat today. What's up?\"";
                this.arr[i++] = "Ruby: \"I'm laughing at how scared everyone here is. They should toughen up.\"";
            
                this.arr[i++] = playerName + ": You look tense. Am I bothering you?\"";
                this.arr[i++] = "Ruby: You? Nah. I don't get scared of people, alright? Not even you.\"";
            
                this.arr[i++] = "[Ruby starts pacing] You: \"Is something wrong?\"";
                this.arr[i++] = "Ruby: \"Nothing's wrong. Just... too much silence. Puts me on edge.\"";
            
                this.arr[i++] = playerName + ": \"I'm looking for someone. Ever met any souls here who drowned just recently?\"";
                if (playerType.equals("knight")) {
                        this.arr[i++] = "Ruby: You and some other guy. He was really cute, that says a lot from what I've seen around here, hahaha.\"";
                } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Ruby: \"No, I mean yes. She's been around here somewhere. I guess she's a bit chatty and unpredictable?\"";
                } else {
                        this.arr[i++] = "Ruby: \"Yep, some dude talking about how pissed he was at dying like that. He got a hair thing growing out his chin.\"";
                }
            
                this.arr[i++] = playerName + ": \"I drowned. How about you?\"";
                this.arr[i++] = "Ruby: \"Died escaping from... a situation that held me down too long, just like you did. Anyway, guess I didn't make it. But I'm here now, so no use crying over it.\"";
            
                this.size = i;
        }

        public void reginaldIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + " approach the edge of the city, where you see a well-dressed gentleman standing alone, adjusting his suit with exaggerated care.";
                this.arr[i++] = "Reginald: \"Ah! A visitor, splendid! I am Reginald. A pleasure, indeed.\"";
                this.arr[i++] = playerName + ": \"I'm sure you're a pleasure and all that. Have we met before?\"";
                this.arr[i++] = "Reginald: \"Oh, dear, I do believe not. I would recall a face such as yours. It would be improper to forget an acquaintance.\"";
                this.arr[i++] = playerName + ": \"Well, that's just fantastic.\"";
                this.arr[i++] = "Reginald: \"Although I must admit, this place, as they call it... the underworld? It's rather lacking in refinement, wouldn't you agree?\"";
                this.arr[i++] = playerName + ": \"Not exactly a vacation spot, but you get used to it.\"";
                this.arr[i++] = "Reginald: \"Indeed, but one must rise above it! My attire may not be entirely suitable for these surroundings, but one must maintain a sense of dignity.\"";
                this.arr[i++] = playerName + ": \"Sure, because dignity's so helpful down here.\"";
                this.arr[i++] = "Reginald: \"Ah, sarcasm. I do find it rather charming.\"";
            
                this.size = i;
        }
            
        public void reginaldLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"The city looks quite imposing from here. What do you think?\"";
                this.arr[i++] = "Reginald: \"Imposing, perhaps, but I find it rather... gauche. Could use a touch of elegance, wouldn't you agree?\"";
            
                this.arr[i++] = playerName + ": \"Which way leads to the forest?\"";
                this.arr[i++] = "Reginald: \"Ah, through the archway, I believe. Though I must say, wandering into forests is hardly the proper thing to do.\"";
            
                this.arr[i++] = playerName + ": \"What do you think of my outfit?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Reginald: \"Ah, very... sturdy. Though, it does lack a bland.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Reginald: \"Ah, quite dignified! We need more nurses in the world. If so, I would still be alive right now.\"";
                } else {
                    this.arr[i++] = "Reginald: \"Oh, my! It's, um, quite... unique, isn't it?\"";
                }
            
                this.arr[i++] = playerName + ": \"Can you tell me about your past life?\"";
                this.arr[i++] = "Reginald: \"Ah, splendid days of grandeur, indeed! Fine dining, splendid attire, and the most impeccable gatherings. Those were the days.\"";
            
                this.arr[i++] = playerName + ": \"Why are you smiling like that?\"";
                this.arr[i++] = "Reginald: \"Ah, just recalling the time I hosted the most extravagant soirée. Such splendid company!\"";
            
                this.arr[i++] = playerName + ": You seem in high spirits today. Any particular reason?\"";
                this.arr[i++] = "Reginald: \"Ah, one must remain buoyant, regardless of one's circumstances. It is, after all, the hallmark of a true gentleman.\"";
            
                this.arr[i++] = playerName + ": You look tense. Is something bothering you?\"";
                this.arr[i++] = "Reginald: \"Oh, no, not at all! I merely find it rather challenging to maintain one's dignity amid such disorder. Look at this place, quite, um... messy?\"";
            
                this.arr[i++] = playerName + ": \"I'm looking for someone. Ever met any souls here who drowned?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Reginald: \"Why yes. Quite dashing, I must say, if a bit rough around the edges.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Reginald: \"Ah, yes, a most peculiar lady in attire much like yours. She was quite... unwell, to say the least.\"";
                } else {
                    this.arr[i++] = "Reginald: \"Hmm, yes, there was a chap who seemed rather perturbed about his unfortunate... departure.\"";
                }
            
                this.arr[i++] = playerName + ": \"I drowned. How about you?\"";
                this.arr[i++] = "Reginald: \"Oh, well, my departure was rather less dramatic, I fear. Passed away quietly, but with all the dignity befitting a gentleman.\"";
            
                this.size = i;
        }
        
        public void asrielIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + " approach the edge of the city and spot a figure brimming with energy, his voice booming as he greets you.";
                this.arr[i++] = "Asriel: \"Hey! Finally, some company! I'm Asriel, and I'm ready to bring some action around here!\"";
                this.arr[i++] = playerName + ": \"Have we crossed paths before? Can't say I remember everyone I meet.\"";
                this.arr[i++] = "Asriel: \"Oh, trust me, you'd remember me! I'm not one of those quiet, lost souls milling around.\"";
                this.arr[i++] = "Asriel: \"Just because I'm all fired up doesn't mean I'm clueless about this place. It's rough, but I love a good thrill!\"";
                this.arr[i++] = playerName + ": \"Not everyone sees it that way. Guess you're one of a kind here.\"";
                this.arr[i++] = "Asriel: You bet I am! Challenges don't scare me; they make me feel alive!\"";
                this.arr[i++] = playerName + ": \"Seems like you've fought your fair share of battles.\"";
                this.arr[i++] = "Asriel: You bet! Now, enough talk—if you're with me, let's move. If not, stay out of my way!\"";
            
                this.size = i;
        }
            
            public void asrielLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"From here, the city has this intense vibe. What's your take on it?\"";
                this.arr[i++] = "Asriel: \"Intense? Nah, it's practically calling us for a showdown!\"";
            
                this.arr[i++] = playerName + ": \"Which path leads to the forest?\"";
                this.arr[i++] = "Asriel: \"Straight ahead! But honestly, what's the rush? Running off isn't my thing.\"";
            
                this.arr[i++] = playerName + ": \"So, what do you think of this outfit?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Asriel: \"Looks bulky! Can you even move in that?\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Asriel: \"Haha, it's got this old-school nurse vibe! Retro cool, I guess.\"";
                } else {
                    this.arr[i++] = "Asriel: You look like you're ready for an anime convention. Nice!\"";
                }
            
                this.arr[i++] = playerName + ": \"Care to tell me a bit about your past life?\"";
                this.arr[i++] = "Asriel: \"Eh, life was a rush, but who cares about the past? I'm here to face whatever's next!\"";
            
                this.arr[i++] = playerName + ": \"What's got you laughing to yourself?\"";
                this.arr[i++] = "Asriel: \"Ha! Just remembered something hilarious. Doubt you'd get it unless you were me.\"";
            
                this.size = i;
        }
            
            public void akifayIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + " approach the edge of the city, noticing a reserved figure standing alone, looking down as if lost in thought.";
                this.arr[i++] = "Akifay: \"Oh… hello. I didn't expect anyone to stop by. I'm… Akifay.\"";
                this.arr[i++] = playerName + ": \"I don't think we've met before, right?\"";
                this.arr[i++] = "Akifay: \"No, I'd remember… I think. I… tend to keep to myself around here.\"";
                this.arr[i++] = playerName + ": \"It's alright. This place has a way of making anyone feel out of sorts.\"";
                this.arr[i++] = "Akifay: \"Yes… it's so strange, so… heavy. Hard to find any comfort here.\"";
                this.arr[i++] = playerName + ": \"It takes some getting used to. Most just try to stay grounded however they can.\"";
                this.arr[i++] = "Akifay: \"I suppose… I just try to stay unnoticed. I don't mean to be a bother.\"";
                this.arr[i++] = playerName + ": \"Don't worry, you're not a bother. I'm glad we crossed paths.\"";
                this.arr[i++] = "Akifay: \"Oh… thank you. That's… very kind of you to say.\"";
            
                this.size = i;
        }
            
            public void akifayLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"From here, the city seems pretty overwhelming, doesn't it?\"";
                this.arr[i++] = "Akifay: \"Yes… it's so vast, so overpowering. Makes me feel so small.\"";
            
                this.arr[i++] = playerName + ": \"Which path takes me to the forest?\"";
                this.arr[i++] = "Akifay: \"Oh, um… straight ahead. But… if you don't have to, I wouldn't go there.\"";
            
                this.arr[i++] = playerName + ": \"What do you think of this outfit?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Akifay: \"Oh… it's very… protective. Looks… safe.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Akifay: \"Oh… it's… familiar, almost comforting, really. Reminds me of a kind soul I once knew.\"";
                } else {
                    this.arr[i++] = "Akifay: \"Oh, um, it's quite… eye-catching. Very unique.\"";
                }
            
                this.size = i;
        }
        
        public void cheaIntro() {
                int i = 0;
            
                this.arr[i++] = playerName + ": \"Hey! You need to get out of here!\"";
                this.arr[i++] = "Chea: \"Oh, and who might you be? Don't even think about sneaking past me.\"";
                this.arr[i++] = playerName + ": \"I don't have time for this. There's something dangerous out there.\"";
                this.arr[i++] = "Chea: \"Dangerous? Oh, honey, you've got to be more specific. I've lived here long enough to know there's always danger. Now spill—what is it?\"";
                this.arr[i++] = playerName + ": \"A demon. It's roaming the forest, hunting anything that moves. You need to be careful.\"";
                this.arr[i++] = "Chea: \"A demon, you say? What does it look like? Big? Small? Does it have wings? Horns? And why are you so worried about it?\"";
                this.arr[i++] = playerName + ": \"I don't have time to answer your questions. Just stay away from the forest!\"";
                this.arr[i++] = "Chea: \"Stay away? Oh, please. I can take care of myself. But now you've got me curious—what were YOU doing near the forest anyway?\"";
                this.arr[i++] = playerName + ": \"Well, I tried to warn you. Just stay out of trouble.\"";
                this.arr[i++] = "Chea: \"Oh, please...\"";

                this.size = i;
            }
            
            public void cheaLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = playerName + ": You really shouldn't be wandering around here. That demon could attack at any moment.\"";
                this.arr[i++] = "Chea: \"A demon attack? Oh, dear. And where exactly was this, hmm? Don't leave out any details!\"";
            
                this.arr[i++] = playerName + ": \"I'm serious. The forest isn't safe. Why aren't you listening?\"";
                this.arr[i++] = "Chea: \"Listening? Oh, I'm listening. I just want to know why someone like you seems so worked up. Have you actually seen it? What does it DO?\"";
            
                this.arr[i++] = playerName + ": \"Why are you persistent on staying?\"";
                this.arr[i++] = "Chea: \"Because you're acting all mysterious about it! What's a demon doing out here anyway? The suspense is killing, I need to see this go down!\"";
            
                this.arr[i++] = playerName + ": \"Look, I don't want anyone else to get hurt. Please, take my warning seriously.\"";
                this.arr[i++] = "Chea: \"Oh, aren't you sweet? But tell me—did the demon say anything? Does it talk? I always imagined demons would be scary. I'm pretty sure they're not supposed here unless a wicked soul made a deal with the devil. Oh, the tea!\"";
            
                this.arr[i++] = playerName + ": You're not taking this seriously. It's a real threat.\"";
                this.arr[i++] = "Chea: \"I'll take it seriously once I know more about it. Do you think it's coming here? Should I get ready? What do you think it wants?\"";
            
                this.arr[i++] = playerName + ": \"If you keep asking questions, you might just find out the hard way.\"";
                this.arr[i++] = "Chea: \"Oh, don't be dramatic. I've dealt with plenty of problems in my time. But a demon, now that's exciting! Tell me everything! A hellish creature could only be summoned through malicious drama, now that's entertainment!\"";

                if (playerType == "knight") {
                        this.arr[i++] = playerName + ": \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "blood" + "</font>" + " all over them.\"";
                }
                if (playerType == "wizard") {
                        this.arr[i++] = playerName + ": \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "scars" + "</font>" + " all over them.\"";
                } 
                if (playerType == "priest") {
                        this.arr[i++] = playerName + ": \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "blood" + "</font>" + ".\"";
                }

                this.size = i;
        }
            

        public void wizardIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Wizard: \"*coughing* Freedom... finally! Thank you, fellow wanderer. That void-spawn had me trapped in its pocket dimension for what felt like eternities.\"";
                
                this.arr[i++] = playerName + ": \"Another soul in this purgatory? Who are you?\"";
                
                this.arr[i++] = "Wizard: \"*adjusts crooked glasses* They used to call me the Lord of Leaky Abstractions back at CITU. Ironic, considering I couldn't debug my way out of that prison.\"";
                

                if (playerType.equals("priest")) {
                        this.arr[i++] = playerName + ": \"Computer science? I was in nursing before... all this happened.\"";
                        
                        this.arr[i++] = "Wizard: \"*genuine interest* A nursing student! Finally, someone who might understand what I mean when I say my code needs healing. Though I guess we're both dealing with a different kind of debugging now.\"";
                        
                        this.arr[i++] = playerName + ": \"Different field, same destination, huh? How did you end up here?\"";
                        
                        this.arr[i++] = "Wizard: \"*smile fades slightly* Funny how life works. There I was, stressed about a memory leak in my code, then suddenly dealing with a much more literal version of that problem. One distracted driver later, and... well, you know how that story ends.\"";
                } 

                else if (playerType.equals("knight")) {
                    this.arr[i++] = playerName + ": \"Computer science? Quite different from the law books I used to study.\"";
                    
                    this.arr[i++] = "Wizard: \"*perks up* A law student! Well, I guess we both got a crash course in mortality laws, huh? No objection there, your honor. *adjusts glasses awkwardly*\"";
                    
                    this.arr[i++] = playerName + ": \"I'll let that pun slide. But what brought you to this place?\"";
                    
                    this.arr[i++] = "Wizard: \"Same case as yours, different verdict. One distracted driver later, and suddenly a future lawyer and a budding programmer find themselves in the ultimate court of appeals.\"";
                }
            
                this.arr[i++] = "Wizard: \"*becomes serious* That entity that imprisoned me... It feeds on the memories of the deceased, leaving them hollow.\"";
                
                this.arr[i++] = playerName + ": \"Is that why you couldn't escape?\"";
                
                this.arr[i++] = "Wizard: \"*nods grimly* It kept consuming my memories, bit by bit. I almost forgot who I was. The only thing it couldn't take was my terrible puns. Even it had standards.\"";
                
                if (playerType.equals("priest")) {
                    this.arr[i++] = "Wizard: \"It probably thought my jokes would cause terminal errors in its system. Get it? Terminal? Because... oh nevermind.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "Wizard: \"It probably found my jokes in contempt of court. That one was for you, future lawyer.\"";
                }
                
                this.arr[i++] = playerName + ": \"And now you're free...\"";
                
                this.arr[i++] = "Wizard: \"Thanks to you. I heard you're hunting for your killer too. Two heads are better than one, especially when one of them can code... albeit with questionable quality.\"";
                
                if (playerType.equals("priest")) {
                    this.arr[i++] = playerName + ": \"I suppose having someone who can 'debug' our situation couldn't hurt.\"";
                    this.arr[i++] = "Wizard: \"That's the spirit! Though I warn you, my debugging usually creates more bugs than it fixes.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = playerName + ": \"I suppose having a tech expert as a witness couldn't hurt our case.\"";
                    this.arr[i++] = "Wizard: \"Exactly! Though I should warn you, my testimony tends to crash and burn... much like my code.\"";
                }
            
                this.arr[i++] = "Wizard: \"Together, we might actually have a shot at finding peace... and revenge. What do you say, partner?\"";

                this.size = i;
        }

        public void knightIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Knight: \"*stretches stiff muscles* By the scales of justice... I'm free! Your timing is impeccable, stranger.\"";
                
                this.arr[i++] = playerName + ": \"Another trapped soul? Who are you?\"";
                
                this.arr[i++] = "Knight: \"*straightens tie* Former law student. Top of my class at CITU, if that still means anything in purgatory. Though I suppose my mock trial experience didn't help much against that... thing.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = playerName + ": \"A law student? Must be quite a change from debugging code to interpreting laws.\"";
                    
                    this.arr[i++] = "Knight: \"*chuckles* And here I thought I'd seen every type of syntax error. Turns out supernatural law is a whole different jurisdiction. You're a CS student then?\"";
                    
                    this.arr[i++] = playerName + ": \"Was. Until a crash that had nothing to do with computers.\"";
                    
                    this.arr[i++] = "Knight: \"*grimly* Seems we both got force-quit from life's program, didn't we? At least you've kept your sense of humor about it.\"";
                } 
                else if (playerType.equals("priest")) {
                    this.arr[i++] = playerName + ": \"From law school to purgatory. Quite a career change.\"";
                    
                    this.arr[i++] = "Knight: \"*wry smile* Well, I went from studying cases to becoming one. Though I suspect your nursing background might be more useful here than my knowledge of tort law.\"";
                    
                    this.arr[i++] = playerName + ": \"Healing the living is one thing. This place... it's something else entirely.\"";
                    
                    this.arr[i++] = "Knight: \"*thoughtfully* At least we both dedicated our studies to helping others. Fat lot of good that did us in the end.\"";
                }
            
                this.arr[i++] = playerName + ": \"What was holding you prisoner?\"";
                
                this.arr[i++] = "Knight: \"*expression darkens* That entity that imprisoned me... It traps them in endless trials of their worst regrets. It... it made me relive my final case over and over.\"";
                
                this.arr[i++] = playerName + ": Your final case?\"";
                
                this.arr[i++] = "Knight: \"*adjusts collar* I was heading to the courthouse for my first real trial. Pro bono case, defending someone who couldn't afford representation. Never made it there. The driver had other plans.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Knight: \"Now that's what I call a fatal exception. *sees your expression* Sorry, picked that up from my CS roommate.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Knight: \"The ultimate mistrial, you could say. Though I doubt any amount of healthcare could've helped at that point.\"";
                }
            
                this.arr[i++] = playerName + ": \"And the Verdict Reaper kept you reliving that moment?\"";
                
                this.arr[i++] = "Knight: \"*nods* Every time I'd try to change the outcome, find a different route, prepare earlier... but the verdict was always the same. Until you showed up.\"";
                
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Knight: \"I don't suppose you could debug this whole afterlife situation? *small smile* No? Well, I still owe you one. And a good lawyer always pays their debts.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Knight: \"I may not be able to heal like you, but I know how to build a case. And right now, we've got a pretty strong one for getting out of here together.\"";
                }
            
                this.arr[i++] = "Knight: \"I hear you're searching for answers too. Perhaps we can help each other appeal this... situation we've found ourselves in. What do you say?\"";
                
                this.size = i;
        }


        public void priestIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Priest: \"*stretching* Oh thank god, real company! The last conversation I had was with a wall. The wall won.\"";
                
                this.arr[i++] = playerName + ": \"Another trapped soul?\"";
                
                this.arr[i++] = "Priest: \"*brushing off spectral dust* Nursing student. Well, was. Got the 'eternal rest' part of the job description a bit early.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = playerName + ": \"Computer science here. Or was, until...\"";
                    
                    this.arr[i++] = "Priest: \"Until life threw a blue screen of death at you? Yeah, same boat. Different crash.\"";
                    
                    this.arr[i++] = playerName + ": \"That was actually... pretty good.\"";
                    
                    this.arr[i++] = "Priest: \"*grins* Three years of dealing with my CS roommate's tech jokes. Now I'm stuck with them forever. Talk about eternal punishment.\"";
                } 
                else if (playerType.equals("knight")) {
                    this.arr[i++] = playerName + ": \"Law student. Before all this.\"";
                    
                    this.arr[i++] = "Priest: \"*amused* A lawyer and a nurse walk into purgatory... sounds like the start of a really bad joke.\"";
                    
                    this.arr[i++] = playerName + ": \"And here we are at the punchline.\"";
                    
                    this.arr[i++] = "Priest: \"At least we're killing it with the gallows humor. Too soon?\"";
                }
            
                this.arr[i++] = playerName + ": \"What was keeping you here?\"";
                
                this.arr[i++] = "Priest: \"*expression darkens*  That entity that imprisoned me... Nasty piece of work. Trapped me in an endless shift of worst-case scenarios. And I thought night rotation was bad.\"";
                
                this.arr[i++] = playerName + ": \"How did you end up here?\"";
                
                this.arr[i++] = "Priest: \"Rushing to cover an emergency shift. Plot twist - became the emergency instead. Universe has a weird sense of humor, doesn't it?\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = playerName + ": \"No respawns in this game, huh?\"";
                    
                    this.arr[i++] = "Priest: \"*laughs* Nope. And the graphics here are terrible. Though the ghost effects are pretty realistic.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = playerName + ": \"Talk about a mistrial of fate.\"";
                    
                    this.arr[i++] = "Priest: \"*smirks* Objection! Actually, no, that's fair.\"";
                }
            
                this.arr[i++] = "Priest: \"That thing kept me trapped in there for... well, time's weird here. Long enough to memorize every ceiling tile in my phantom hospital.\"";
                
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Priest: \"Thanks for the rescue. Your debug skills just saved my afterlife.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "Priest: \"Thanks for the jailbreak. First time being happy to see a lawyer show up.\"";
                }
            
                this.arr[i++] = playerName + ": \"What now?\"";
                
                this.arr[i++] = "Priest: \"*straightens up* Well, I hear you're hunting down your own tragic backstory. Could use a hand? I promise my bedside manner is way better than my death-side manner.\"";
                System.out.println("size = " + i);
                this.size = i;
        }

        public void skeleton1Corpse(){
                int i = 0;
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "Okay, we get it, you really don't like this skeleton.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "What are you, a necrophiliac? The skeleton is already dead!";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] =  "Dude, I think you broke it. Looks like you found a feature, not a bug.";
                this.arr[i++] = "Achievement Unlocked: \"Skeleton Poking Master\"\n" + //
                                                playerName + "'ve been awarded a temporary increase of +10 base Soul Energy for your dedication to poking the dead. The devs were going to patch this out, but decided it was too funny to remove.";
                this.size = i;
        }

        public void skeleton2Corpse(){
                int i = 0;
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "Seriously, stop poking the poor dead skeleton.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = playerName + " know, kicking a skeleton while it's down is just rude.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] =  "Alright, I'm starting to think you have some unresolved issues with skeletons.";
                this.arr[i++] = "Achievement Unlocked: \"Skeleton Abuse Awareness\"\n" + //
                                                "Congratulations, your incessant skeleton poking has earned you an increase of +10 to your attack stat. The dev team is a little concerned about your skeleton obsession, but hey, who are we to judge?";
                this.size = i;
        }

        public void necromancerCorpse(){
                int i = 0;
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "Careful, you might accidentally raise the dead again.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "I think the necromancer is trying to get the last laugh, even in death.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] =  "Wow, you really hold a grudge against this guy. Maybe you should talk to a therapist.";
                this.arr[i++] = "Achievement Unlocked: \"Necromancer Nemesis\"\n" + //
                                                " Your hatred of necromancers is truly unparalleled. As a reward, you've been granted a temporary +10 boost to your Mana pool. We're a little worried about your anger issues, but at least it's productive in combat.";
                this.size = i;
        }

        public void gorgonCorpse(){
                int i = 0;
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "I wouldn't stare too long, you might end up petrified too.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] = "Turning a Gorgon into a ragdoll, huh? That's a bold move.";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "...";
                this.arr[i++] = "..."; 
                this.arr[i++] =  "Bravo, you've managed to thoroughly de-Gorgon the Gorgon. I'd give you a trophy, but I'm worried it might turn to stone.";
                this.arr[i++] = "Achievement Unlocked: \"Gorgon Gratification\"\n" + //
                "We're not sure why you felt the need to click on the Gorgon's corpse 15 times, but your commitment is impressive. As a reward, you've been granted a permanent boost to your Soul Energy. Try not to stare at it too long, or you might end up like the Gorgon.";
                this.size = i;
        }

        public void questNotComplete(){
                int i = 0;
                this.arr[i++] = "Complete the current quest!";
        }

        public void skillDetails(String playerType) {
                int i = 0;
                
                this.arr[i++] = playerName + " have approached an enemy!";
                this.arr[i++] = "Basic Skill: Deal damage based on attack.";
                
                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Objection Surge (Cost: 15 Soul Shards): Sacrifice 15 Soul shards to buff attack by 15 for 2 turns";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Overclock (Cost: 20 Mana): Sacrifice 15 Mana to buff attack by 15 for 2 turns";
                }else{
                        this.arr[i++] = "Vital Rush(Cost: 25 Soul Energy): Sacrifice 15 Soul Energy to buff attack by 15 for 2 turns";
                }
                
                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Ethereal Shield of Logic(Cost: 30 Mana): Defends against the next damage taken by 40%. If damage taken is greater than 20% of soul energy left, gain 30 Soul Shards this round.";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Quantum Shift(Cost: 30 Mana): Has a 45% chance of evading the next attack. If successful, deal 35 damage and gain 90 mana.";
                }else{
                        this.arr[i++] = "Vital Degrade(Cost: 30 Mana): Deals 10% of the Priest's base Soul Energy as damage to enemy for 3 turns.";
                }

                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Truthbinding(Cost: 50 Mana): Deal 200% Attack + 15% Soul Shards damage and the enemy can't attack this turn.";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Azure Inferno(Cost: 50 Mana): Incenerates enemy in blue flames, dealing 30 + 30% Base Mana";
                }else{
                        this.arr[i++] = "Vengeful Vitality(Cost: 50 Mana): Deals 60% of missing Soul Energy to opponent and heals 40% of base Soul Energy to the party";
                }
                
                this.size = i;
        }

        public void preEnding() {
                int i = 0;

                this.arr[i++] = "Your journey nears its ends. You can't begin to fathom the idea of how far you've come. The friends and foes you made along the way, the obstacles you had to take, and the painstaking long walk to get here. You are finally given the decision to bring forth justice and finally claim your right to live again.";
                this.arr[i++] = "The darkness around you thickens, as if waiting for your next move, feeding off your hesitation. The hollowed voice of Death echoes again, its ethereal presence sending a chill down your spine.";
                this.arr[i++] = "Your time is slipping away. Have you made a decision yet?\"";
                this.arr[i++] = "As you hear those words spoken, you feel your throat run dry. You are faced with one of the most difficult decisions yet - a decision that could lead you to live your life once again. You went through the depths of despair rummaging for hope to find the future - or if there is any at all. Exhaustion sweeps through your core once more.";
                this.arr[i++] = "Surprisingly, you remained calm, collected even and then, you remembered it. You remembered that the life you had once, was nothing but a cycle of agony. You remembered, and now, you are unsure again.";
                this.arr[i++] = "\"I... I don't know. All I've been trying to do was care for people, why am I even here? I'm not even sure if … if I want to know.\"";
                this.arr[i++] = "Feeling so done with everything, you didn't even want to think things through.";
                this.arr[i++] = "Silence befell both of you as the weight of your unspoken thoughts filled the space between. Words lingered on the tip of your tongues, yet neither dared to break the fragile stillness. Not sure if the darkness saw itself in him, but this felt like a tribute to the fallen - the doubts, the fears, and the anguish that came rushing through.";
                this.arr[i++] = "However, it is time to choose.";
                this.arr[i++] = "\"Well... whatever it is that you doubt, you've come this far, and it's time to choose. Now, tell me. Who's on your mind?\"";

                this.size = i;
        }
        
        public void ending1() {
                int i = 0;

                this.arr[i++] = "There's a pause, a suffocating silence as if the universe itself is holding its breath. The veil of darkness that has cloaked your path begins to lift, parting like curtains to reveal the figure in the shadows.";
                this.arr[i++] = "And there, standing in the dim light, is someone all too familiar.";
                this.arr[i++] = "The figure steps forward, and you feel the world tilt beneath your feet.";
                this.arr[i++] = "It's Him.";
                this.arr[i++] = "As the veil of darkness fades, the reality of the situation sets in. You are face-to-face with the very man who destroyed everything you cherished. The air between you hums with tension, the weight of betrayal heavy on your chest. You can feel your heart pounding in your ears, but there is no time for hesitation.";
                this.arr[i++] = "Faithful, your former professor, now stands before you as the orchestrator of your death—and his. His expression is chilling, devoid of remorse, a complete 180 degrees. The very thought twists your stomach into knots, but you can not afford to let him see your fear.";
                this.arr[i++] = "“So, you finally figured it out. Took you long enough hehe.,” Faithful sneers, his voice dripping with mockery.";
                this.arr[i++] = "Your mind races, trying to comprehend the truth. All the pieces finally fit together—the late-night visits your mother would never explain, the mysterious tension between you and Faithful, and now this. It was Faithful that led you to your death—and his own.";
                this.arr[i++] = "“Why did you do it? What did I ever do to you?” You choke out, barely able to contain your fury.";
                this.arr[i++] = "Faithful steps forward, the darkness still clinging to him like a cloak. His eyes gleam with malice, and for the first time, he speaks with a twisted kind of honesty. “You were an obstacle. A problem that needed solving. Your mother was mine, and you… you were just something standing in the way of our perfect life together.”";
                this.arr[i++] = "“Perfect life?” You feel your fists clench at your sides, nails digging into your palms. “You killed me! You killed both of us! And for what? You're sick.”";
                this.arr[i++] = "Faithful shrugs, his indifference chilling. “It would have been perfect… if you hadn't been there. She would have chosen me eventually. I was just speeding up the process.” His smile grows even more twisted as he continues. “Too bad for you, though. I never expected her to love you so much. It was irritating.”";
                this.arr[i++] = "Your body trembles with a mixture of rage and disbelief. How could someone like him, someone who had been a complete hopeless romantic, harbor such malevolence and jealousy? And how could your mother, caring and careful as she was, fall for someone like him? You shake your head, pushing the thoughts aside. It no longer matters. None of it does.";
                this.arr[i++] = "This is your final chance.";
                this.arr[i++] = "Faithful, now standing taller, seems to be amused with your internal struggle. “You think you can stop me?” he taunts. “You've already lost once. This time, I'll make sure you stay dead.”";
                this.arr[i++] = "But you are no longer afraid. The weight of your own death, the weight of his death—those burdens no longer hold you back anymore. Instead, they fuel the fire burning inside you. Faithful may have taken everything from you, but now, you have the power to end this nightmare.";
                this.arr[i++] = "“No,” you say, your voice steady and unwavering. “I'm going to put you down for good.”";
                this.arr[i++] = "Faithful laughs, the sound grating in your ears. He reaches for something in the darkness, and in an instant, a grotesque weapon materializes in his hand—an ebony blade, the edge shimmering with malevolent energy. The air around it warps, twisting as if reality itself is being bent by its presence.";
                this.arr[i++] = "You take a deep breath, summoning all the strength and resolve you have gained throughout your journey. You are not the same person who died before. You have fought your way through the darkest depths of the underworld, battled creatures that defy description, and now, you stand at the end of that road.";
                this.arr[i++] = "“Let's finish this,” you mumbled.";
                
                this.size = i;
        }
            
        public void ending2() { 
                int i = 0;

                this.arr[i++] = "Death vanishes from thin air and leaves a trail of darkness in his wake. The cold thick air came filling everything with a pause of deafening silence. And then suddenly, an unusual, high-pitched voice came right through. You hear a girl come running, arms open and extended as if prepared for a warm gripping hug. A very unusual act for a murderer about to face their justice.";
                this.arr[i++] = "\"Nurseeee …. \" the loud voice fills the air, \"Nurseee, where are you??\" she shouts.";
                this.arr[i++] = "Confused, he looked around to find someone. He swims his way right through the thick fog and as if making a path towards the only person around, the fog slowly vanishes. You quickly composed yourself, and finally you locked eyes. You took a good look at this girl keeping a massive amount of distance between the both of you. She continues to approach still lifting her arms out.";
                this.arr[i++] = "\"Oh, there you are! I've been dying to see you!\"";
                this.arr[i++] = "Shocked as she kept moving closer, you took a huge step back.";
                this.arr[i++] = "\"Dying? Well, we sure are getting there!\"";
                this.arr[i++] =" You took a look again, this time, you looked closely at this petite looking girl. Then, something hit you. These eyes, this face, and this conversation! You've heard a hundred times. Hell, you've even heard it today!";
                this.arr[i++] = "\"Wait, wait, wait, wait, I don't understand. Weren't you one of the patients I attended? Wasn't I good to you? How come?\"";
                this.arr[i++] = "Still baffled, he looks for the reaper to find his answers, and yet he didn't know where he was.";
                this.arr[i++] = "\"Hi! Do you remember me Nurse?\"";
                this.arr[i++] = "\"Well about that it really was quite a stretch of an accident hehe. You see, I got out of the hospital! Oh my gosh, I got out of the hospital. Aren't you happy for me?\"";
                this.arr[i++] = "\"No, you're supposed to be in it and now we're here fighting for life!!\"";
                this.arr[i++] = "\"Well, it's no biggie, cause I still get to see you? Wait, where... where are we? IT ALL HAPPENED SOO FAST? I … I couldn't even remember it. Wait, I DO remember! You were there, and I was sooo glad to see you!\"";
                this.arr[i++] = "\"What do you mean I was there, when you died? You really killed me?\"";
                this.arr[i++] = "Confused yet he continues to listen what this was all about.";
                this.arr[i++] = "\"Killed you, oh please, I didn't kill you. You just died, cause you were there. I was driving you know, cause I just got out.\"";
                this.arr[i++] = "\"No, you escaped!\"";
                this.arr[i++] = "\"But, I still got out, and you were one of the nurses that treated me so well, so I looked for you. I wanted you to see me drive! I'm totally good at it and well, maybe I want us to … you know, go out, have fun?\"";
                this.arr[i++] = "\"… and you killed me.\"";
                this.arr[i++] = "Totally disdained from what you just heard. You felt as if everything was all for naught. All your efforts, your choices, your time and what's most ridiculous of all is how you, trying to be a caring person got him in such an unfavorable situation.";
                this.arr[i++] = "\"Accidentally. Well, I was too excited to see you so I immediately tried to see you and hehe... huh... OH MY GOSHH I KILLED YOU! I'm sorry. I didn't mean to? \"";
                this.arr[i++] = "Confused on what he should be feeling, he tried to gather himself and all it lead to was a huge...";
                this.arr[i++] = "\"AHAHAHHAAAHAAHAHAH... this is crazy. I can't even be mad at you cause this - this was all an accident.\"";
                this.arr[i++] = "You now understand everything. A person sick in the mind, trying to do things they're were not supposed to. What can you even do about that? For a second, you forgot all the anguish you felt. Now, everything should feel better, yet for some reason, you still can't escape the uneasy feeling you had when you first arrived in this world. Was this really the justice you've been looking for?";
                this.arr[i++] = "\"Hey nurse, I'm really sorry about what happened okay? Can we just call it a truce? I, I didn't really mean it. hehe\"";
                this.arr[i++] = "There was nothing left to do. It was stupid to be angry at a person who can't control himself. So, you let go of everything.";
                this.arr[i++] = "\"Fine... There's nothing to be angry about anyway.\"";
                this.arr[i++] = "Natty reached out for her hand and as they came closer together she kept smiling. The eerie feeling you had did not escape as you reached out as well. You shook hands but then...";
                this.arr[i++] = "She suddenly grabbed you closer, and whispered something.";
                this.arr[i++] = "\"I meant it you know, your death was my doing.\"";
                this.arr[i++] = "You pulled back and all the anguish came rushing again.";
                this.arr[i++] = "\"AHAHAHAHAHHAHA.... you fell for it! You fell for it again, just like how you fell for every act that I did when I was still in that stupid asylum!\"";
                this.arr[i++] = "\"What the - what do you even mean??\"";
                this.arr[i++] = "\"I was jealous of you, you know. You might forget, that was an asylum for murderers! Murderers like me. It was natural instinct. Something like, if I can't live a life, why don't I just die with the person who's loved by all.\"";
                this.arr[i++] = "You TOTALLY ARE CRAZY. FINE. LET'S END THIS AND SEE IF YOU REALLY STILL CAN LIVE.\"";

                this.size = i;
        }
        
        public void ending3() {
                int i = 0;
                
                this.arr[i++] = "A deafening silence takes place, as if the universe itself is holding its breath. The veil of darkness that has cloaked your path begins to lift, parting like curtains to reveal the figure in the shadows.";
                this.arr[i++] = "And there, standing in the dim light, is someone all too familiar.";
                this.arr[i++] = "The figure steps forward, and you feel the world tilt beneath your feet.";
                this.arr[i++] = "It's Him.";
                this.arr[i++] = "As the darkness lifts, the truth becomes clear. Standing before you is the man that took your life that night. The tension between you two is electrifying. Your heart pounds in your ears, but there's no room for hesitation now.";
                this.arr[i++] = "Yoo, your former classmate, now stands before you as the orchestrator of your death—and his. His expression is dull, devoid of all emotions, even more so than ever.";
                this.arr[i++] = "“What took you so long to figure it out? I was getting tired of waiting.” Yoo states in a voice filled with sarcasm.";
                this.arr[i++] = "Your mind races, trying to comprehend the truth. All the pieces finally fit together—the moments of underhanded criticism, the subtle sabotage of your achievements, the jealousy you always tried to ignore. Yoo could not stand to see you surpass him, outshine him in every way.";
                this.arr[i++] = "“Why?” you ask, barely able to contain your anger.";
                this.arr[i++] = "“You were too perfect, too good at everything. Your success was my failure. Everyone started comparing me to you, and I couldn't stand it.” His voice shakes with bitter resentment. “I built you up, but you outgrew me. You became everything I wanted to be.”";
                this.arr[i++] = "“You killed me because I was better than you?” Your voice is incredulous, but deep down, the truth settles like a cold weight in your gut.";
                this.arr[i++] = "Yoo's dead demeanor only deepens. “It wasn't enough to outshine me, was it? You had to take everything. My recognition, my reputation... Even in death, you were still that competent. But now? Now you're nothing.”";
                this.arr[i++] = "You'r fists clench at your sides, nails digging into your palms. You can feel the rage bubbling inside you, a molten fury that threatens to consume you.";
                this.arr[i++] = "All the sacrifices you made, all the battles you fought, only to find out it was jealousy that tore everything apart.";
                this.arr[i++] = "“I won't let you get away with this,” you reply.";
                this.arr[i++] = "Yoo shrugs, his indifference chilling.";
                this.arr[i++] = "“You've already lost once. This time, I'll make sure you stay dead.”";
                this.arr[i++] = "But you are no longer afraid. The weight of your death, the weight of his betrayal—those burdens no longer hold you back. Instead, they fuel the fire burning inside you. Yoo may have taken your life away from you, but now, you have the power to bring forth justice.";
                this.arr[i++] = "“No,” you say, your voice steady and unwavering. “This time, I'll make sure you pay for your sins.”";
                this.arr[i++] = "Yoo stares, a soul-piercing gaze. He steps forward, and in an instant, a ghostly aura emanates from his body. The air around it warps, twisting as if reality itself is being bent by his presence.";
                this.arr[i++] = "You take a deep breath, summoning all the strength and resolve you have gained throughout your journey.";
                this.arr[i++] = "You are not the same person who died before. You have fought your way through the darkest depths of the underworld, battled creatures that defy description, and now, you stand at the end of that road.";
                this.arr[i++] = "“Let's finish this.”";

                this.size = i;
        }

        public void postEnding(String playerType) {
                int i = 0;

                if (playerType.equals("knight")) {
                        this.arr[i++] = "The battle is over. Faithful is gone, he better be at the deepest point of the underworld now, never to be seen again.";
                } else if (playerType.equals("nurse")) {
                        this.arr[i++] = "The battle is over. Natty is gone, she better be at the deepest point of the underworld now, never to be seen again.";
                } else {
                        this.arr[i++] = "The battle is over. Yoo is gone, he better be at the deepest point of the underworld now, never to be seen again.";
                }

                this.arr[i++] = "Death congratulates you.";
                this.arr[i++] = "\"Well done, no other mortal was able to put up with so much trouble like you did,\" he chuckles.";
                this.arr[i++] = "\"Now, as promised. Do you have any last words? Your time here has come to an end.\"";                
                this.arr[i++] = "\"Will I be able to see anyone again?\" you ask.";            
                this.arr[i++] = "\"Of course, they'll be waiting for you. I'll get to see you again in fifty years or so, haha.\" Death replies, as he begins to dissipate.";            
                this.arr[i++] = "As the void around you begins to fade too, you feel wet sand enveloping you—and before you know it, you are laying on the seashore across the city. The weight of the afterlife lifts from your shoulders, and for the first time in what feels like an eternity, you breathe freely.";                
                this.arr[i++] = "You're alive.";                
                this.arr[i++] = "Opening your eyes, you find yourself standing in a familiar place. The sunlight filters through the palm trees, the scent of earth and sea filling the air.";
                this.arr[i++] = "You're're back. Back in the world of the living.";               
                this.arr[i++] = "A sense of peace washes over you. It's over. Finally, it's over.";                
                this.arr[i++] = "You close your eyes and take a deep breath, knowing that though your journey may have been long and painful, you've earned a second chance. You wanted to tell your mom everything that happened, but it might be pointless—no one would ever believe the things you went through.";  
                this.arr[i++] = "You are simply grateful to live a second time.";    
                this.arr[i++] = "And this time, you won't waste it.";

                this.size = i;
        }

        public void badEnding() {
                int i = 0;
            
                // Display the suspect list here for the player to choose from.
                
                this.arr[i++] = "The cold, thick air fills everything with a pause of deafening silence. It envelops every inch of space that comes between you and Death. However, as seconds pass, it seems to whirl around Death more and more.";
                
                this.arr[i++] = "You wonder why Death hasn't said anything at all.";
                
                this.arr[i++] = "You failed.";
                
                this.size = i;
        }
            
        public String getLine(int i) {
                // Define padding values
                int horizontalPadding = (int) (screenSize.width * 0.05);
                int verticalPadding = (int) (screenSize.height * 0.01);
                
                String content = this.arr[i];
                
                // Return the formatted HTML string with padding and text alignment
                return String.format("<html><div style='text-align: left; padding: %dpx %dpx;'>%s</div></html>",
                verticalPadding, horizontalPadding, content);
        }

        public String getLine(int i, String[] arr) {
                // Define padding values
                int horizontalPadding = (int) (screenSize.width * 0.05);
                int verticalPadding = (int) (screenSize.height * 0.01);
                
                String content = arr[i];
                
                // Return the formatted HTML string with padding and text alignment
                return String.format("<html><div style='text-align: left; padding: %dpx %dpx;'>%s</div></html>",
                verticalPadding, horizontalPadding, content);
        }

        public int getSize() {
                return this.size;
        }

        public int getSize(String type) {
                switch(type){
                        case "arr": return size;
                        case "oarr": return objectivesSize;
                        case "qarr": return questSize;
                        default: return 0;
                }
        }

        public String[] getArr() {
                return arr;
        }

        public String[] getQArr() {
                return qArr;
        }

        public String[] getOArr() {
                return oArr;
        }
}