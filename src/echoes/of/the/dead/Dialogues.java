package echoes.of.the.dead;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dialogues extends JFrame {

    // Constructor that takes an integer parameter to determine which story part to display
    public Dialogues(int dialogue, int boxSize) {
        openstoryDialogue(dialogue , boxSize);
    }

    // Method to open the story dialog box and display either exposition or resolution
    private void openstoryDialogue(int dialogue, int boxSize) {
        int width, height, x, y, borderLine = 0;

        // Box Size Controller
        if (boxSize == 1) {
            width = 1280;
            height = 700;
            x = 0;
            y = 23;
        } else {
            width = 1268;
            height = 315;
            x = 5;
            y = 400;
            borderLine = 5;
        }

        // Create a modal dialog for the story
        JDialog storyDialogue = new JDialog(this, "ECHOES OF THE DEAD", Dialog.ModalityType.APPLICATION_MODAL);
        storyDialogue.setUndecorated(true); // Remove title bar and exit button
        storyDialogue.setSize(width, height);
        storyDialogue.setLayout(new BorderLayout());

        // Set up the story and label for the text
        StoryLine story = new StoryLine();
        story.loadStoryPart(dialogue); // Load either exposition or resolution based on the parameter
        JLabel textLabel = new JLabel(story.getStoryLine(0), SwingConstants.CENTER);
        textLabel.setFont(new Font("MonoSpaced", Font.PLAIN, 28));
        textLabel.setForeground(Color.WHITE); // Set text color to white
        textLabel.setVerticalAlignment(SwingConstants.CENTER);

        // Set the background color of the dialogue to black
        storyDialogue.getContentPane().setBackground(Color.BLACK);

        // Add the text label to the dialogue
        storyDialogue.add(textLabel, BorderLayout.CENTER);

        // Create a border and set it to the dialogue
        LineBorder border = new LineBorder(Color.WHITE, borderLine); // White border of width 5
        storyDialogue.getRootPane().setBorder(border); // Apply border to the root pane

        // Set the location of the dialogue window
        storyDialogue.setLocation(x, y);

        // Add a mouse listener to the dialogue for text progression
        storyDialogue.addMouseListener(new MouseAdapter() {
            private int currentIndex = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                currentIndex++;
                String nextLine = story.getStoryLine(currentIndex);
                if (nextLine != null) {
                    textLabel.setText(nextLine);
                    textLabel.addKeyListener(new java.awt.event.KeyAdapter() {
                        @Override
                        public void keyPressed(java.awt.event.KeyEvent evt) {
                            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) { // Check for ESC key
                                dispose(); // Close the window
                            }
                        }
                    });
                    // Ensure the textLabel can receive key events
                    textLabel.setFocusable(true);
                    textLabel.requestFocusInWindow();
                } else {
                    storyDialogue.dispose();
                }
            }
        });

        // Show the dialog box
        storyDialogue.setVisible(true);
    }

    // The StoryLine class with both exposition and resolution
    class StoryLine {
        private String[] arr;

        // Load either exposition or resolution based on the integer parameter, add more here...
        public void loadStoryPart(int dialogue) {
            switch (dialogue) {
                case 1 -> loadExposition();
                case 2 -> loadResolutionA();
                default -> throw new IllegalArgumentException("Invalid story part. Use 1 for exposition and 2 for resolution.");
                // Add more after case 2
            }
        }

        // Load the exposition
        public void loadExposition() {
        this.arr = new String[50];
        int i = 0;
        arr[i++] = "It has been a long day at CITU. After hours of scanning through countless lines of codes, all you can think about is the comfort of your bed and the peace of being home.";
        arr[i++] = "After stepping out of the university campus, you trudge toward the parking lot, exhaustion weighing heavily on your shoulders. The sun had long dipped beneath the horizon, leaving the streets bathed in the eerie glow of streetlights. You slid into the driver's seat, the familiar hum of the engine offering a slight sense of comfort as you started the car.";
        arr[i++] = "Pulling onto the road, you glanced in the rearview mirror and caught sight of a car idling just across the street, its headlights glaring. Shaking off the sensation, you turned onto the main road, eager to get home. But as you made each turn, the car followed. Left, right, another left-it mirrored every move. An uneasy feeling began to settle in, creeping up your spine. What was a coincidence at first now felt intentional.";
        arr[i++] = "\"Something is wrong.\"";
        arr[i++] = "Determined to shake the car off, you took an abrupt turn into a narrow side street, speeding up as you weaved through the backroads. You heart raced, and every sharp corner felt like a calculated move. You spotted a small alley up ahead, barely wide enough for a car. Without hesitation, you darted into it and killed the engines. You sat in the darkness, holding your breath. Moments later, the other car went past the alley, oblivious. You restarted the engine and quickly sped to the opposite direction.";
        arr[i++] = "You had to make a detour, taking the longer, less familiar route just to be safe. As you merged onto the Cebu-Cordova Link Expressway (CCLEX), the vast expanse of the bridge stretched ahead, disappearing into the fog. The bridge is lit by the occasional, flickering tail lights of the cars in front of you.";
        arr[i++] = "Thunder rumbles in the distance, you are just minutes away from home.";
        arr[i++] = "Then suddenly, from the corner of your eye, headlights blind you-the familiar vehicle from earlier veers recklessly into your lane. The screech of tires on wet pavement fills your ears, followed by the metallic crunch of impact. You got a glimpse of the driver of the car, but before you could properly identify who it is, your own car spins, losing all control. Glass shatters. You feel the weight of your vehicle tilt as the guardrail bursts. For a split second, there is a gut-curling freefall before both vehicles plunge into the cold, dark waters below.";
        arr[i++] = "...";
        arr[i++] = "You wake up gasping, your chest tight with panic. There is no water filling your lungs, no agony gripping your body, yet the terror is all-consuming. Your breath comes in shallow bursts, but something is wrong-the air is still. Too still. And there's no sound. No feeling. Just a cold, oppressive silence that wraps around you like a shroud.";
        arr[i++] = "The world around you is dark and blurry, like a dream where nothing feels entirely real, but the fear coursing through your veins is far too tangible to be a nightmare. You try to focus, but it's like looking through a fog that refuses to lift.";
        arr[i++] = "Then, from the corner of your eye, a figure begins to take shape, emerging from the surrounding shadows. Cloaked in darkness, the figure's form is indistinct, more of a presence than a person. It moves without sound, its silhouette unnaturally still despite the distance between you. You don't know exactly what it is or who it might be, but a deep, primal instinct tells you that if it reaches you, it will be the end of you.";
        arr[i++] = "You are not ready.";
        arr[i++] = "The panic seizes you, harder and faster than before. Tears blur your vision as they spill down your cheeks, mixing with the cold sweat on your skin.";
        arr[i++] = "\"Please, stop!\"";
        arr[i++] = "You cry out, your voice breaking under the weight of your desperation. Your hands tremble as you raise them instinctively, as if you could hold off the approaching figure with sheer will alone.";
        arr[i++] = "\"Don't do this. I'm not ready yet! It's not my time!\"";
        arr[i++] = "But the figure continues its silent approach, unyielding, like death itself creeping closer with each passing second. The numbness of death begins to settle in, your heart pounding as though it knows the end is near. But then... the figure pauses.";
        arr[i++] = "It halts just within reach, but it does not vanish. Instead, the silence seems to stretch as the figure sits there, almost as if... considering you.";
        arr[i++] = "You can feel it watching you, though there are no visible eyes. Just the weight of its attention pressing down on you. ";
        arr[i++] = "Sensing your opportunity, you speak, your voice trembling but urgent.";
        arr[i++] = "\"I shouldn't be here! This wasn't supposed to happen! I don't know who killed me, or why. It's unfair. I didn't deserve this! I'm innocent!\"";
        arr[i++] = "The words come spilling out in a rush, each one more desperate than the last.";
        arr[i++] = "The figure shifts, as though weighing your plea. Then, finally, it speaks.";
        arr[i++] = "\"If I had to let you go, I'd be fired from my job. Haha.\"";
        arr[i++] = "The voice is casual-unexpectedly light, even sarcastic. A far cry from the menacing silhouette that stands before you. You blink, confusion cutting through the fog of fear.  Its voice resembled that of Mark Edward Fischbach which made you feel like you were losing your mind.";
        arr[i++] = "\"Is this some sick joke? Did I really die?\"";
        arr[i++] = "The figure laughs again, a low rumble of amusement.";
        arr[i++] = "\"Dead? Yeah, sorry to break it to you, but you're definitely not alive anymore.\"";
        arr[i++] = "Your mind races, your pulse still erratic as the figure's words sink in. “But... this isn't right. I don't even know why this happened. It wasn't my time. You can't just... take me!” You push back, your desperation returning in full force.";
        arr[i++] = "It leans in, voice dropping to a mock-serious tone. \"I'll let you go back, but you're not getting off scot-free. You want justice? Fine. Go find out who killed you. Track them down, and maybe I'll think about letting you go.\"";
        arr[i++] = "\"Is that-\"";
        arr[i++] = "Without warning, the figure vanishes, dissolving into the shadows like smoke in the wind. The oppressive darkness that had clung to you begins to thin, and the world around you shifts. The void melts away, revealing a ghostly cityscape-familiar yet warped.";
        arr[i++] = "The streets are empty, but whispers echo in the air, faint voices that seem to come from nowhere and everywhere at once. The sky above is a dark, and the air carries a strange feeling.";
        arr[i++] = "You stand at the edge of the city, your breath catching in your throat as you take in the eerie, lifeless surroundings. This is no place you have ever known, but somehow, it feels like a shadow of the world you once lived in.";
        arr[i++] = "You've been given a second chance, but the rules are unclear. The figure's taunts echo in your mind as you step forward into the ghostly city, unsure of what comes next. The streets stretch ahead of you, silent and ominous, and somewhere out there lies the answer to the mystery of your death.";
        arr[i++] = "However, the afterlife has its own dangers. You may not know the rules here, but you know one thing for certain: you are not alone. There are other lost souls here, and not all of them are friendly. Some might help you. Others... might try to keep you from ever finding the truth.";
        arr[i++] = "Your journey begins now.";
    }

        // Load the resolution
        public void loadResolutionA() {
            this.arr = new String[50];
            int i = 0;
            arr[i++] = "Your journey nears its ends. You can not begin to fathom the idea of how far you have come. The friends and foes you made along the way, the obstacles you had to take, and the painstakingly long walk to get here. You are finally given the decision to bring forth justice and finally claim your right to live again.";
            // Add the rest of the resolution here...
        }

        // Get a specific line of the story by index
        public String getStoryLine(int index) {
            if (index >= 0 && index < arr.length && arr[index] != null) {
                return "<html><center>" + arr[index] + "</center></html>"; // Format with HTML for better display
            }
            return null; // Return null if the index exceeds available text
        }
    }
}
