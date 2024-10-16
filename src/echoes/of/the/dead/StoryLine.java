package echoes.of.the.dead;

import javax.swing.*;
import java.util.Random;

public class StoryLine extends JFrame {
    Random r = new Random();
    private final String[] arr = new String[80];

    StoryLine() {
            // Exposition / ID 0 1 2 3
            this.arr[0] = "...";
            this.arr[1] = "It has been a long day at CITU. After hours of scanning through countless lines of codes, all you can think about is the comfort of your bed and the peace of being home.";
            this.arr[2] = "After stepping out of the university campus, you trudge toward the parking lot, exhaustion weighing heavily on your shoulders. The sun had long dipped beneath the horizon, leaving the streets bathed in the eerie glow of streetlights. You slid into the driver's seat, the familiar hum of the engine offering a slight sense of comfort as you started the car.";
            this.arr[3] = "Pulling onto the road, you glanced in the rearview mirror and caught sight of a car idling just across the street, its headlights glaring. Shaking off the sensation, you turned onto the main road, eager to get home. But as you made each turn, the car followed. Left, right, another left-it mirrored every move. An uneasy feeling began to settle in, creeping up your spine. What was a coincidence at first now felt intentional.";
            this.arr[4] = "\"Something is wrong.\"";
            this.arr[5] = "Determined to shake the car off, you took an abrupt turn into a nthis.arrow side street, speeding up as you weaved through the backroads. You heart raced, and every sharp corner felt like a calculated move. You spotted a small alley up ahead, barely wide enough for a car. Without hesitation, you darted into it and killed the engines. You sat in the darkness, holding your breath. Moments later, the other car went past the alley, oblivious. You restarted the engine and quickly sped to the opposite direction.";
            this.arr[6] = "You had to make a detour, taking the longer, less familiar route just to be safe. As you merged onto the Cebu-Cordova Link Expressway (CCLEX), the vast expanse of the bridge stretched ahead, disappearing into the fog. The bridge is lit by the occasional, flickering tail lights of the cars in front of you.";
            this.arr[7] = "Thunder rumbles in the distance, you are just minutes away from home.";
            this.arr[8] = "Then suddenly, from the corner of your eye, headlights blind you-the familiar vehicle from earlier veers recklessly into your lane. The screech of tires on wet pavement fills your ears, followed by the metallic crunch of impact. You got a glimpse of the driver of the car, but before you could properly identify who it is, your own car spins, losing all control. Glass shatters. You feel the weight of your vehicle tilt as the guardrail bursts. For a split second, there is a gut-curling freefall before both vehicles plunge into the cold, dark waters below.";
            this.arr[9] = "...";
            
            this.arr[10] = "You wake up gasping, your chest tight with panic. There is no water filling your lungs, no agony gripping your body, yet the terror is all-consuming. Your breath comes in shallow bursts, but something is wrong-the air is still. Too still. And there's no sound. No feeling. Just a cold, oppressive silence that wraps around you like a shroud.";
            this.arr[11] = "The world around you is dark and blurry, like a dream where nothing feels entirely real, but the fear coursing through your veins is far too tangible to be a nightmare. You try to focus, but it's like looking through a fog that refuses to lift.";
            this.arr[12] = "Then, from the corner of your eye, a figure begins to take shape, emerging from the surrounding shadows. Cloaked in darkness, the figure's form is indistinct, more of a presence than a person. It moves without sound, its silhouette unnaturally still despite the distance between you. You don't know exactly what it is or who it might be, but a deep, primal instinct tells you that if it reaches you, it will be the end of you.";
            this.arr[13] = "You are not ready.";
            this.arr[14] = "The panic seizes you, harder and faster than before. Tears blur your vision as they spill down your cheeks, mixing with the cold sweat on your skin.";
            this.arr[15] = "\"Please, stop!\"";
            this.arr[16] = "You cry out, your voice breaking under the weight of your desperation. Your hands tremble as you raise them instinctively, as if you could hold off the approaching figure with sheer will alone.";
            this.arr[17] = "\"Don't do this. I'm not ready yet! It's not my time!\"";
            this.arr[18] = "But the figure continues its silent approach, unyielding, like death itself creeping closer with each passing second. The numbness of death begins to settle in, your heart pounding as though it knows the end is near. But then... the figure pauses.";
            this.arr[19] = "It halts just within reach, but it does not vanish. Instead, the silence seems to stretch as the figure sits there, almost as if... considering you.";
            
            this.arr[20] = "You can feel it watching you, though there are no visible eyes. Just the weight of its attention pressing down on you. ";
            this.arr[21] = "Sensing your opportunity, you speak, your voice trembling but urgent.";
            this.arr[22] = "\"I shouldn't be here! This wasn't supposed to happen! I don't know who killed me, or why. It's unfair. I didn't deserve this! I'm innocent!\"";
            this.arr[23] = "The words come spilling out in a rush, each one more desperate than the last.";
            this.arr[24] = "The figure shifts, as though weighing your plea. Then, finally, it speaks.";
            this.arr[25] = "\"If I had to let you go, I'd be fired from my job. Haha.\"";
            this.arr[26] = "The voice is casual-unexpectedly light, even sarcastic. A far cry from the menacing silhouette that stands before you. You blink, confusion cutting through the fog of fear.  Its voice resembled that of Mark Edward Fischbach which made you feel like you were losing your mind.";
            this.arr[27] = "\"Is this some sick joke? Did I really die?\"";
            this.arr[28] = "The figure laughs again, a low rumble of amusement.";
            this.arr[29] = "\"Dead? Yeah, sorry to break it to you, but you're definitely not alive anymore.\"";
            
            this.arr[30] = "Your mind races, your pulse still erratic as the figure's words sink in. “But... this isn't right. I don't even know why this happened. It wasn't my time. You can't just... take me!” You push back, your desperation returning in full force.";
            this.arr[31] = "It leans in, voice dropping to a mock-serious tone. \"I'll let you go back, but you're not getting off scot-free. You want justice? Fine. Go find out who killed you. Track them down, and maybe I'll think about letting you go.\"";
            this.arr[32] = "\"Is that-\"";
            this.arr[33] = "Without warning, the figure vanishes, dissolving into the shadows like smoke in the wind. The oppressive darkness that had clung to you begins to thin, and the world around you shifts. The void melts away, revealing a ghostly cityscape-familiar yet warped.";
            this.arr[34] = "The streets are empty, but whispers echo in the air, faint voices that seem to come from nowhere and everywhere at once. The sky above is a dark, and the air cthis.arries a strange feeling.";
            this.arr[35] = "You stand at the edge of the city, your breath catching in your throat as you take in the eerie, lifeless surroundings. This is no place you have ever known, but somehow, it feels like a shadow of the world you once lived in.";
            this.arr[36] = "You've been given a second chance, but the rules are unclear. The figure's taunts echo in your mind as you step forward into the ghostly city, unsure of what comes next. The streets stretch ahead of you, silent and ominous, and somewhere out there lies the answer to the mystery of your death.";
            this.arr[37] = "However, the afterlife has its own dangers. You may not know the rules here, but you know one thing for certain: you are not alone. There are other lost souls here, and not all of them are friendly. Some might help you. Others... might try to keep you from ever finding the truth.";
            this.arr[38] = "Your journey begins now.";
            this.arr[39] = "...";

            // Miss Constance Dialogues / ID 4
            this.arr[40] = "[You walk into a misty area of the underworld and spots an ordinary woman sitting on a rock, talking to herself. She notices you and her eyes light up.]";
            this.arr[41] = "Miss Constance: \"Oh! A new face! You're probably wondering who's who down here, right? Well, I know everything! Like, did you hear about Faithful? Total creep! He stalked everyone that came down here and asking about their love life, like that is so none of your business! My name's Miss Constance by the way, you can call me Miss C!\"";
            this.arr[42] = "You: \"Actually, I—\"";
            this.arr[43] = "Miss C (cutting them off): \"And Yoo? Such a drama queen! Acts all mysterious, but it's just an act. Oh, and don't get me started on Miggins—tried to sneak an advertisement in The Grand Mausoleum! Can you believe the nerve?\"";
            this.arr[44] = "You (awkward): \"Yeah, I'm just—\"";
            this.arr[45] = "Miss C: \"Leaving so early? Oh, sure! But come back! I've got loads more to tell—wait till you hear about Myself!\"";
            this.arr[46] = "[You walk away quickly, the woman's voice trailing behind them.]";
            this.arr[47] = "Miss C (yelling):\"Don't forget to visit! I know everything! Also, don't try to-\"";
            this.arr[48] = "...";
            this.arr[49] = "...";
        
            // Miss Constance Lines / ID 5
            this.arr[50] = "\"I keep waiting for something exciting to happen down here, but nope, just the same old spirit world. I thought maybe a demon would pass by or something. Do you think they're avoiding me? I'm starting to think they are.\"";
            this.arr[51] = "\"Do you think the living still remember me? I bet they don't. I wasn't famous or anything. Just your average city girl. But I like to think there's a statue somewhere with my name on it. Wishful thinking, right?\"";
            this.arr[52] = "\"I tried making friends with the other souls, but they're so… distant. It's like, 'Hello! We're stuck here for eternity together! Can we at least pretend to like each other?' No one ever laughs at my jokes, either. Rude!\"";
            this.arr[53] = "\"Do you know how long I've been dead? Of course, you don't! Time is weird down here. But I feel like I've been dead for… maybe a thousand years? Or was it ten? You lose track after the first century. What year is it, anyway?\"";
            this.arr[54] = "\"The spirit world, right? It's just as gloomy as you'd expect. No sun. No moon. No stars. Just endless dark skies. Oh, and the fog. Don't get me started on the fog. It's like a constant wet blanket on your soul.\"";
            this.arr[55] = "\"I tried making friends with this other soul earlier, but they're so cranky. It's like, 'Hello! We're stuck here for eternity together! Can we at least pretend to like each other?\"";
            this.arr[56] = "\"Oh, and don't even get me started on Yoo. He's always acting like he's so mysterious, sitting by the river and staring into the abyss. Please, he's just being dramatic. We get it, you're moody. Join the club, honey.\"";
            this.arr[57] = "\"You know Miggins, right? She's been trying to impress everyone by selling her \"Homemade Pies\". As if we're stupid enough to believe that! I 'm pretty sure she just heated up 'ready to eat' mall pies and called it a day. It's sad, really.\"";
            this.arr[58] = "\"You ever hear that saying, 'Death is peaceful'? Lies! It's so boring down here. No one talks. Except me, of course. I keep talking because if I don't, I'll go mad. Or maybe I already have.\"";
            this.arr[59] = "...";
            
            // Natty Introduction / ID 6
            this.arr[60] = "You wander through a shadowy, eerie part of the underworld. A woman stands alone by the edge of a dark river, looking anxious. You approach carefully.";
            this.arr[61] = "Natty: \"Oh no, it's you… I-I wasn't expecting to see you again. I thought maybe… maybe you'd forgotten about me. It's so dark here, and I don't know if I can handle this… I'm scared…\"";
            this.arr[62] = "You: \"It's alright. We're both stuck here, but we'll figure it out.\"";
            this.arr[63] = "Natty: \"Figure it out? Of course we will! I'm not afraid of this place! I've faced worse before. I wasn't going to let them control me, and I'm not letting this place beat me either!\"";
            this.arr[64] = "Natty: \"But what if I'm not strong enough? I barely made it through last time. What if I'm too weak now? This place… it's worse than anything we went through before. I can't do it… I'm too scared…\"";
            this.arr[65] = "You: \"Take it easy. We aren't exactly alone here.\"";
            this.arr[66] = "Natty: \"Alone? I'm never alone! I'm too strong to be beaten by fear! I'm not the same fragile person they thought I was. I'm a fighter! I can handle anything!\"";
            this.arr[67] = "You: \"I'm not sure how to respond to that...\"";
            this.arr[68] = "Natty: \"No. Not that. I… I can't.\"";
            this.arr[69] = "You reach out as Natty alternates between fear and courage, her emotions like a pendulum, trapped in a constant internal struggle. It may be better to return to her later.";         
        
            // Natty Lines / ID 7
            this.arr[70] = "\"I've been through worse, but this place... it whispers to me, you know? It knows my name! They even know my birthday! It's October 16.\"";
            this.arr[71] = "\"There's no way out, and I'm just wandering in circles. This place sucks.\"";
            this.arr[72] = "\"They said I won't be able to survive out on my own, but I'm still here, just barely getting by! This place isn't as bad as the last one I've been in.\"";
            this.arr[73] = "\"I remember my last Chrstmas with my family, I was a nice girl they said. Too bad Santa disagrees.\"";
            this.arr[74] = "\"Me? Crazy? Okay. If I was crazy, you wouldn't be talking to me! That's right, you only NEED something from me! From this poor mentally handicapped lady! Shame on you!\"";
            this.arr[75] = "\"Wanna hear a joke? Who sells fake pies and calls herself Miggins?\"";
            this.arr[76] = "\"Nothing scares me more than pretty people, I'm so insecure I'd die again if I ever see one!\"";
            this.arr[77] = "\"Why are asylums full of singles? Because they couldn't keep their thoughts together!\"";
            this.arr[78] = "\"Me? Afraid? I'm not afraid of you...\"";
            this.arr[79] = "\"I can't do this again! The voices are everywhere! They're crawling under my skin, it tickles!\"";
        }

    // Get a specific line of the story by index
    public String getLine(int i) {
            return "<html><center>" + this.arr[i] + "</center></html>";
    }
}