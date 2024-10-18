package echoes.of.the.dead;

import javax.swing.*;
import java.util.Random;

public class StoryLine extends JFrame {
    Random r = new Random();
    private final String[] arr = new String[200];

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

            // Miss Constance Intoduction / ID 4
            this.arr[40] = "[You enter a misty area of the underworld and spot an ordinary woman sitting on a rock, talking to herself. She looks up and her eyes brighten.]";

            this.arr[41] = "Miss Constance: \"Oh! A new face! You must be curious about who's who down here, right? Well, I know everything! Like, have you heard about Faithful? Total creep! He stalked everyone who came down here! I'm Miss Constance, by the way; you can call me Miss C!\"";
            this.arr[42] = "You: \"Actually, I—\"";
            
            this.arr[43] = "Miss C (interrupting): \"And Yoo? Such a drama queen! Acts all mysterious, but it's just a show. And don't get me started on Miggins—he tried to sneak an advertisement into The Grand Mausoleum! Can you believe the nerve?\"";           
            this.arr[44] = "You (awkwardly): \"Yeah, well, it was nice to meet you, I might check out the rest of the forest before going to-\"";
            
            this.arr[45] = "Miss C: \"Leaving so soon? Oh, sure! But come back! I've got loads more to share—wait till you hear about Myself!\"";
            
            this.arr[46] = "[She's a little to much to take in right now, you slowly back away, the woman's voice trailing after you.]";
            
            this.arr[47] = "Miss C (calling out): \"Don't forget to visit! I know everything! Also, don't try to approach some of the monsters roaming around, unless you're prepared!\"";
            
            this.arr[48] = "Miss C (continuing): \"...I have the juiciest stories about all the denizens here! You wouldn't believe what they're really like!\"";
            
            this.arr[49] = "Miss C (shouting): \"Also, I'm always here if you need to-!\"";
        
            // Miss Constance Lines / ID 5
            this.arr[50] = "\"I keep waiting for something exciting to happen down here, but nope, just the same old spirit world. I thought maybe a spirit would swing by or something. Do you think they're avoiding me? Honestly, I'm starting to take it personally.\"";

            this.arr[51] = "\"Do you think the living still remember me? Probably not, right? I wasn't exactly a celebrity. Just your average city girl. But, oh, how I like to imagine there's a statue somewhere with my name on it! A girl can dream, even in the afterlife.\"";

            this.arr[52] = "\"I tried to make friends with the other souls, but they're all so… distant. Like, 'Hello! We're stuck here together for eternity, can we at least pretend to like each other?' No one ever laughs at my jokes either. Rude, right?\"";

            this.arr[53] = "\"How long have I been dead, you ask? Oh wait, you didn't ask. Well, too bad! Time is a blur down here. I feel like I've been dead for a thousand years… or maybe just ten? You lose track after a century or two. By the way, what year is it?\"";

            this.arr[54] = "\"The spirit world, ugh, just as bleak as you'd imagine. No sun, no moon, no stars. Just endless dark skies. And the fog! Don't even get me started on the fog. It's like a wet blanket draped over your soul 24/7.\"";

            this.arr[55] = "\"Tried chatting with another soul earlier. They're all so cranky! It's like, 'Hello! We're stuck here for eternity together! Could we at least *pretend* to like each other?' I swear, they all think being moody is a personality trait.\"";

            this.arr[56] = "\"Oh, and Yoo? Don't even get me started on that one. Always acting so mysterious, sitting by the river, staring dramatically into the abyss. We get it, Yoo, you're deep. Join the club, honey! We're all brooding down here.\"";

            this.arr[57] = "\"You know Miggins, right? Yeah, she's been trying to impress everyone with her so-called 'Homemade Pies.' Please! We all know she just heats up store-bought ones and calls it a day. It's honestly kind of sad.\"";

            this.arr[58] = "\"You ever hear that saying, 'Death is peaceful'? Lies! It's boring down here. No one talks. Except me, of course. I keep talking because if I don't, I'll probably go mad. Or maybe I already have. Who's to say?\"";

            this.arr[59] = "\"Oh my gosh, like, if the sun emitted Wi-Fi, trees would be like cell towers, you know? I mean, cellphones are basically fruits from those trees or whatever. Don't even get me started on how apples are used to make smartphones, like, it's science, right?";

            
            // Natty Introduction / ID 6
            this.arr[60] = "You wander through a shadowy, eerie part of the underworld. A woman stands alone by the edge of a dark river, her eyes darting nervously. You approach cautiously, sensing the tension in the air.";

            this.arr[61] = "Natty: \"Oh... it's you? I-I didn't expect to see you again, I'm Natty. I thought maybe... maybe you'd forgotten me. It's so dark here, and I'm not sure I can do this... I'm scared.\"";
            this.arr[62] = "You: \"Have we really met before?. I have my fair share with people like you, I think?\"";

            this.arr[63] = "Natty: \"Before? Ha! Of course you did! Also, I'm pretty new here. This place kind of spooky, right? I've been through worse! They can't hurt me then, and this place won't break me now!\"";
            this.arr[64] = "Natty: \"But... what if I'm not strong enough this time? Last time, I barely made it. This place... it's worse than anything we've seen before. I-I don't think I can survive it... I'm too scared.\"";

            this.arr[65] = "You: \"Just take a deep breath. You're not exactly alone here, Natty. We can handle this.\"";
            this.arr[66] = "Natty: \"Alone? No! No! No! I'm never alone! That won't beat me, not now, not ever! I'm not the fragile person you thought I was. I'm a unstoppable! I can handle anything! HAHAHA!\"";

            this.arr[67] = "You: \"Are you... Okay? You're swinging between your moods way too fast.\"";
            this.arr[68] = "Natty: \"No... No, I didn't! I... I can't!\"";

            this.arr[69] = "You reach out, watching as Natty shifts between defiance and fear, her emotions like a pendulum. She seems caught in an internal struggle, and it might be best to leave her to calm down before speaking further.";
        
            // Natty Lines / ID 7
            this.arr[70] = "\"I've seen worse, but there's something about this place... it whispers to me, you know? Almost like it knows who I am.\"";
            this.arr[71] = "\"There's no escape, is there? Feels like I've been walking in circles for hours.\"";

            this.arr[72] = "\"They said I wouldn't last a day on my own. But look at me now, still breathing! This place isn't as bad as where I came from.\"";
            this.arr[73] = "\"Last Christmas, I remember sitting with my family. They called me the 'good girl.' Too bad Santa didn't agree.\"";

            this.arr[74] = "\"You think I'm crazy? Oh, please! If I were, you wouldn't be here talking to me! You just need something from poor little Natty, don't you? Shame on you.\"";
            this.arr[75] = "\"Want to hear a joke? Who sells fake pies and calls herself Miggins?\" *[chuckles to herself]*";

            this.arr[76] = "\"Pretty people terrify me. I swear, if I see another one, I might just drop dead again!\"";
            this.arr[77] = "\"Why are asylums full of singles? Because none of them can keep their thoughts together!\" *[laughs hysterically]*";

            this.arr[78] = "\"You think I'm terrified? No. I'm not afraid of you... hehe.\"";
            this.arr[79] = "\"I can't do this! The voices—they're everywhere! Crawling under my skin, it tickles! HAHAHAHA!\"";

            // Yoo Introduction / ID 10 11
            this.arr[100] = "You: \"Do I know you from somewhere? You seem... familiar, somehow.\"";
            this.arr[101] = "Yoo: \"...\"";

            this.arr[102] = "You: \"Um, it's strange. I can't quite place you, but it feels like we've crossed paths before.\"";
            this.arr[103] = "Yoo: \"...\"";

            this.arr[104] = "You: \"Um, hello? Ah, I see. You're not like others, I guess. Are you the quiet type?\"";
            this.arr[105] = "Yoo: \"What'd ya expect? You think people just talk with strangers down here?\"";

            this.arr[106] = "You: \"Sorry. It's just, are you...\"";
            this.arr[107] = "Yoo: \"Single? Sorry, you're a little too late for that.\"";

            this.arr[108] = "You: \"O-oh, I mean. Are you new here?\"";
            this.arr[109] = "Yoo: \"I'm just someone passing through. Like everyone else. I'm here for a while now.\"";

            this.arr[110] = "You: \"Why are you all the way here, anyway? You don't like the people over there?\"";
            this.arr[111] = "Yoo: \"The reason doesn't matter.\"";

            this.arr[112] = "You: \"Sorry for prying, I was just curious.\"";
            this.arr[113] = "Yoo: \"You should mind your own business. You seem to be the nosy type, like Constance.\"";

            this.arr[114] = "You: \"Excuse me? I'm just trying to get to know you better.\"";
            this.arr[115] = "Yoo: \"My story isn't worth telling. Beat it.\"";

            // Yoo lines / ID 12
            this.arr[120] = "\"...\"";
            
            this.arr[121] = "\"Don't look at me.\"";

            this.arr[122] = "\"Don't you have better things to do?\"";

            this.arr[123] = "\"Miggins' pies taste like medicine.\"";

            this.arr[124] = "\"You died unexpectedly? Damn, like everyone else here did? It's not my fault you're a bad driver.\"";

            this.arr[125] = "\"Go away.\"";

            this.arr[126] = "\"You're so annoying...\"";

            this.arr[127] = "\"You reek of seafood.\"";

            this.arr[128] = "\"The moment you step foot on this forest, it was raining ever since.\"";

            this.arr[129] = "\"Did you know that the weather here corresponds with the emotions of those who live under it?\"";

            // Miggins Introduction / ID 14
            this.arr[140] = "You: \"I feel like I know you. Have we met before?\"";
            this.arr[141] = "Miggins: \"Oh, my dear, I doubt it. But then again, faces blur after a while down here, don't they?\"";

            this.arr[142] = "You: \"Something smells good.\"";
            this.arr[143] = "Miggins: \"Could be the scent of my pies. I sell it to almost everyone who wanders through. Hard to resist a good snack, even in the afterlife.\"";

            this.arr[144] = "You: \"That sounds lovely, I'd do anything for a slice of pi- wait. Do I need to eat? I'm already dead.\"";
            this.arr[145] = "Miggins: \"Well, sweetheart, the thing is... Souls do get hungry, they just don't die from it.\"";

            this.arr[146] = "You: \"Oh...\"";
            this.arr[147] = "Miggins: \"Still hungry? Not for a long. My store is still very much alive! Are you interested in some apple pies or apple potions?\"";

            this.arr[148] = "You: \"So, how much would that be?\"";
            this.arr[149] = "Miggins: \"Oh, cheaper than most, I assure you.\"";

            this.arr[150] = "You: \"Why set up shop all the way over here?\"";
            this.arr[151] = "Miggins: \"Oh, I decided to set a shop here since souls like you arrive here often after death. It's also pretty peaceful around these parts.\"";

            this.arr[152] = "You: \"Well, that would also mean that 'they' are also here...\"";
            this.arr[153] = "Miggins: \"Who?\"";

            this.arr[154] = "You: \"Someone that I was searching for, it's important.\"";
            this.arr[155] = "Miggins: \"Oh, then I hope you find them soon. You're more than welcome to come back if you wish to purchase something, dear.\"";


            // Miggins lines / ID 16
            this.arr[160] = "\"The red potions are apple flavored, it may taste like medicine since it is one.\"";
            
            this.arr[161] = "\"Some of the denizens here don't like me at all, why is that?\"";

            this.arr[162] = "\"Plants grow here without the need for sunlight, the faint glow of the underworld is more than enough.\"";

            this.arr[163] = "\"My shop is open for all!\"";

            this.arr[164] = "\"It's a shame that you died that way, perhaps I could cheer you up with some of my cookings?\"";

            this.arr[165] = "\"Some souls could go months without food, but the hunger is way too unbearable that most wouldn't last a year without it.\"";

            this.arr[166] = "\"You'd be surprised by how many souls crave a little comfort food.\"";

            this.arr[167] = "\"Do you like seafood? Unfortunately, most of them are gobbled up by slimes.\"";

            this.arr[168] = "\"The weather here recently is terrible. It's scaring away the customers!\"";

            this.arr[169] = "\"Oh, sweetie, the grave doesn't stop me from whipping up a batch of cookies!\"";
    }

    // Get a specific line of the story by index
    public String getLine(int i) {
            return "<html><center>" + this.arr[i] + "</center></html>";
    }
}