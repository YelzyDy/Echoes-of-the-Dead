package EOD.dialogues;

import EOD.gameInterfaces.Freeable;
import EOD.utils.SFXPlayer;

public class StoryLine implements Freeable{
        private String[] arr = new String[50];
        private SFXPlayer sfxPlayer = SFXPlayer.getInstance();
        private int size;

        public void free(){
                size = 0;
                arr = null;
        }

        public void exposition() {
                int i = 0;
                this.arr[i++] = "...";
                this.arr[i++] = "It has been a long day at CITU. After hours of scanning through countless lines of codes, all you can think about is the comfort of your bed and the peace of being home.";
                sfxPlayer.stopSFX();
                sfxPlayer.playSFX("src/audio_assets/sfx/exposition/parkinglot.wav");
                this.arr[i++] = "After stepping out of the university campus, you trudge toward the parking lot, exhaustion weighing heavily on your shoulders. The sun had long dipped beneath the horizon, leaving the streets bathed in the eerie glow of streetlights. You slid into the driver's seat, the familiar hum of the engine offering a slight sense of comfort as you started the car.";
                this.arr[i++] = "Pulling onto the road, you glanced in the rearview mirror and caught sight of a car idling just across the street, its headlights glaring. Shaking off the sensation, you turned onto the main road, eager to get home. But as you made each turn, the car followed. Left, right, another left-it mirrored every move. An uneasy feeling began to settle in, creeping up your spine. What was a coincidence at first now felt intentional.";
                this.arr[i++] = "\"Something is wrong.\"";
                this.arr[i++] = "Determined to shake the car off, you took an abrupt turn into a nthis.arrow side street, speeding up as you weaved through the backroads. You heart raced, and every sharp corner felt like a calculated move. You spotted a small alley up ahead, barely wide enough for a car. Without hesitation, you darted into it and killed the engines. You sat in the darkness, holding your breath. Moments later, the other car went past the alley, oblivious. You restarted the engine and quickly sped to the opposite direction.";
                this.arr[i++] = "You had to make a detour, taking the longer, less familiar route just to be safe. As you merged onto the Cebu-Cordova Link Expressway (CCLEX), the vast expanse of the bridge stretched ahead, disappearing into the fog. The bridge is lit by the occasional, flickering tail lights of the cars in front of you.";
                this.arr[i++] = "Thunder rumbles in the distance, you are just minutes away from home.";
                this.arr[i++] = "Then suddenly, from the corner of your eye, headlights blind you-the familiar vehicle from earlier veers recklessly into your lane. The screech of tires on wet pavement fills your ears, followed by the metallic crunch of impact. You got a glimpse of the driver of the car, but before you could properly identify who it is, your own car spins, losing all control. Glass shatters. You feel the weight of your vehicle tilt as the guardrail bursts. For a split second, there is a gut-curling freefall before both vehicles plunge into the cold, dark waters below.";
                this.arr[i++] = "...";
                
                this.arr[i++] = "You wake up gasping, your chest tight with panic. There is no water filling your lungs, no agony gripping your body, yet the terror is all-consuming. Your breath comes in shallow bursts, but something is wrong-the air is still. Too still. And there's no sound. No feeling. Just a cold, oppressive silence that wraps around you like a shroud.";
                sfxPlayer.stopSFX();
                sfxPlayer.playSFX("src/audio_assets/sfx/exposition/panting.wav");
                this.arr[i++] = "The world around you is dark and blurry, like a dream where nothing feels entirely real, but the fear coursing through your veins is far too tangible to be a nightmare. You try to focus, but it's like looking through a fog that refuses to lift.";
                this.arr[i++] = "Then, from the corner of your eye, a figure begins to take shape, emerging from the surrounding shadows. Cloaked in darkness, the figure's form is indistinct, more of a presence than a person. It moves without sound, its silhouette unnaturally still despite the distance between you. You don't know exactly what it is or who it might be, but a deep, primal instinct tells you that if it reaches you, it will be the end of you.";
                this.arr[i++] = "You are not ready.";
                this.arr[i++] = "The panic seizes you, harder and faster than before. Tears blur your vision as they spill down your cheeks, mixing with the cold sweat on your skin.";
                this.arr[i++] = "\"Please, stop!\"";
                this.arr[i++] = "You cry out, your voice breaking under the weight of your desperation. Your hands tremble as you raise them instinctively, as if you could hold off the approaching figure with sheer will alone.";
                this.arr[i++] = "\"Don't do this. I'm not ready yet! It's not my time!\"";
                this.arr[i++] = "But the figure continues its silent approach, unyielding, like death itself creeping closer with each passing second. The numbness of death begins to settle in, your heart pounding as though it knows the end is near. But then... the figure pauses.";
                this.arr[i++] = "It halts just within reach, but it does not vanish. Instead, the silence seems to stretch as the figure sits there, almost as if... considering you.";
                
                this.arr[i++] = "You can feel it watching you, though there are no visible eyes. Just the weight of its attention pressing down on you. ";
                this.arr[i++] = "Sensing your opportunity, you speak, your voice trembling but urgent.";
                this.arr[i++] = "\"I shouldn't be here! This wasn't supposed to happen! I don't know who killed me, or why. It's unfair. I didn't deserve this! I'm innocent!\"";
                this.arr[i++] = "The words come spilling out in a rush, each one more desperate than the last.";
                this.arr[i++] = "The figure shifts, as though weighing your plea. Then, finally, it speaks.";
                this.arr[i++] = "\"If I had to let you go, I'd be fired from my job. Haha.\"";
                this.arr[i++] = "The voice is casual-unexpectedly light, even sarcastic. A far cry from the menacing silhouette that stands before you. You blink, confusion cutting through the fog of fear.  Its voice resembled that of Mark Edward Fischbach which made you feel like you were losing your mind.";
                this.arr[i++] = "\"Is this some sick joke? Did I really die?\"";
                this.arr[i++] = "The figure laughs again, a low rumble of amusement.";
                this.arr[i++] = "\"Dead? Yeah, sorry to break it to you, but you're definitely not alive anymore.\"";
                
                this.arr[i++] = "Your mind races, your pulse still erratic as the figure's words sink in. “But... this isn't right. I don't even know why this happened. It wasn't my time. You can't just... take me!” You push back, your desperation returning in full force.";
                this.arr[i++] = "It leans in, voice dropping to a mock-serious tone. \"I'll let you go back, but you're not getting off scot-free. You want justice? Fine. Go find out who killed you. Track them down, and maybe I'll think about letting you go.\"";
                this.arr[i++] = "\"Is that-\"";
                this.arr[i++] = "Without warning, the figure vanishes, dissolving into the shadows like smoke in the wind. The oppressive darkness that had clung to you begins to thin, and the world around you shifts. The void melts away, revealing a ghostly cityscape-familiar yet warped.";
                this.arr[i++] = "The streets are empty, but whispers echo in the air, faint voices that seem to come from nowhere and everywhere at once. The sky above is a dark, and the air cthis.arries a strange feeling.";
                this.arr[i++] = "You stand at the edge of the city, your breath catching in your throat as you take in the eerie, lifeless surroundings. This is no place you have ever known, but somehow, it feels like a shadow of the world you once lived in.";
                this.arr[i++] = "You've been given a second chance, but the rules are unclear. The figure's taunts echo in your mind as you step forward into the ghostly city, unsure of what comes next. The streets stretch ahead of you, silent and ominous, and somewhere out there lies the answer to the mystery of your death.";
                this.arr[i++] = "However, the afterlife has its own dangers. You may not know the rules here, but you know one thing for certain: you are not alone. There are other lost souls here, and not all of them are friendly. Some might help you. Others... might try to keep you from ever finding the truth.";
                this.arr[i++] = "Your journey begins now.";
                this.arr[i++] = "...";

                this.size = i;
        }

        public void missConstanceIntro(String playerType, String worldType) {
                int i = 0;

                if (worldType == "world1") {
                        this.arr[i++] = "[You enter a misty area of the underworld and spot an ordinary woman sitting on a rock, talking to herself. She looks up and her eyes brighten.]";
                        this.arr[i++] = "Miss Constance: \"Oh! A new face! You must be curious about who's who down here, right? Well, I know everything! Like, have you heard about Faithful? Total creep! He stalked everyone who came down here! I'm Miss Constance, by the way; you can call me Miss C!\"";
                        this.arr[i++] = "You: \"Actually, I—\"";           
                        this.arr[i++] = "Miss C (interrupting): \"And Yoo? Such a drama queen! He's been here for sometime now, I didn't keep track. Acts all mysterious, but it's just a show. And don't get me started on Miggins—he tried to sneak an advertisement into The Grand Mausoleum! Can you believe the nerve?\"";           
                        this.arr[i++] = "You (awkwardly): \"Yeah, well, it was nice to meet you, I might check out the rest of the city before going to-\"";            
                        this.arr[i++] = "Miss C: \"Leaving so soon? Oh, sure! But come back! I've got loads more to share—wait till you hear about Myself!\"";            
                        this.arr[i++] = "[She's a little too much to take in right now, you slowly back away, the woman's voice trailing after you.]";            
                        this.arr[i++] = "Miss C (calling out): \"Don't forget to visit! I know everything! Also, don't try to approach some of the monsters roaming around, unless you're prepared!\"";            
                        this.arr[i++] = "Miss C (continuing): \"...I have the juiciest stories about all the denizens here! You wouldn't believe what they're really like!\"";
                        this.arr[i++] = "Miss C (shouting): \"Also, after you're done with meeting up with everyone, can you slay the skeleton blocking the path out of the city? Just enter the green portal. Thanks!\"";
                }
            
                if (worldType == "world2") {
                        this.arr[i++] = "[Miss Constance suddenly stops mid-sentence, looking at you more closely.]";
                        this.arr[i++] = "Miss C: \"Wait a second... You made it to the city outskirts?! Congrats!\"";
                        this.arr[i++] = "You: \"Yeah, why?\"";
                        this.arr[i++] = "Miss C (wide-eyed): \"Oh my, that's amazing! How in the world did you manage that?\"";
                        this.arr[i++] = "You: \"A bit of skill, a lot of luck.\"";
                        this.arr[i++] = "Miss C: \"Skill and luck? Pfft, more like a guardian angel working overtime! Trust me, most folks don't even get past Mr. Bones over there!\"";
                        this.arr[i++] = "[She looks genuinely impressed, but her tone remains rapid-fire as ever.]";
                        this.arr[i++] = "You: \"Why are you here anyway?\"";
                        this.arr[i++] = "Miss C: \"If you lived (HA!) here as long as I did, you'd be surprised by how many shortcut portals there are. Hehehe.\"";
                        this.arr[i++] = "You: \"Ugh, you should've told me sooner. It would've saved me a lot of time.\"";
                        this.arr[i++] = "Miss C: \"Well, if you can handle that, maybe you'll be just fine down here even without shortcuts. But seriously, don't let it go to your head—I've seen tougher souls get crushed trying to go deeper! Also, we needed someone to get rid of those skeletons anyway.\"";
                        this.arr[i++] = "You: \"Good to know.\"";
                        this.arr[i++] = "Miss C: \"Anytime! Now go prove me right—or wrong! Just don't end up like Miggins. Long story.\"";
                        if (playerType == "knight") {
                                this.arr[i++] = "Miss C: \"P.S. I learned that your little murderer friend have " + "<font color='#FF0000'>" + "freckles" + "</font>" + ". How cute!\"";
                        }
                        if (playerType == "wizard") {
                                this.arr[i++] = "Miss C: \"P.S. I learned that your little murderer friend have " + "<font color='#FF0000'>" + "scars" + "</font>" + ". How scary!\"";
                        }
                        if (playerType == "priest") {
                                this.arr[i++] = "Miss C: \"P.S. I learned that your little murderer friend have " + "<font color='#FF0000'>" + "freckles" + "</font>" + ". How cute!\"";
                        }
                        this.arr[i++] = "You: \"Well that's just g r e a t.\"";
                        this.arr[i++] = "Miss C: \"Thanks! Chea told me, she already left.\"";
                }
            
                if (worldType == "world3") {
                    this.arr[i++] = "[You notice Miss Constance, her usual chatter replaced by a tense silence as she looks around nervously.]";
                    this.arr[i++] = "Miss C: \"Oh, it's you... You're here already? I thought... never mind. You probably shouldn't stay here long.\"";
                    this.arr[i++] = "You: \"What's going on? You seem startled.\"";
                    this.arr[i++] = "Miss C (whispering): \"Something's wrong. Really wrong. The forest... there's this dark aura, suffocating. It's like the whole place is holding its breath.\"";
                    this.arr[i++] = "[Her usual confidence is replaced with fear as she glances over her shoulder.]";
                    this.arr[i++] = "Miss C: \"Someone's waiting for you. Whoever they are, they're not friendly. They must know you're here.\"";
                    this.arr[i++] = "You: \"Who are you talking about?\"";
                    if (playerType == "knight") {
                        this.arr[i++] = "Miss C (shaking her head): \"I don't know. But whatever it is, even the monsters are steering clear of the forest now. Please, just... Be careful. I also heard that they always wear a " + "<font color='#FF0000'>" + "frightening frown" + "</font>" + ", no matter where they go.\"";
                    }
                    if (playerType == "wizard") {
                        this.arr[i++] = "Miss C (shaking her head): \"I don't know. But whatever it is, even the monsters are steering clear of the forest now. Please, just... Be careful. I also heard that they wear an " + "<font color='#FF0000'>" + "empty expession" + "</font>" + ", one that seems to suck the soul right out of you (v053).\"";
                    }
                    if (playerType == "priest") {
                        this.arr[i++] = "Miss C (shaking her head): \"I don't know. But whatever it is, even the monsters are steering clear of the forest now. Please, just... Be careful. I also heard that they always wear a " + "<font color='#FF0000'>" + "sinister smile" + "</font>" + ".\"";
                    }
                    this.arr[i++] = "You: \"Thanks for the warning. I guess I'll find out soon enough.\"";
                    this.arr[i++] = "[She gives you a weak smile but doesn't say anything more as you step into the ominous forest.]";
                }

                this.size = i;
        }
            

        public void missConstanceLines(String playerType, String worldType) {
                int i = 0;

                if (worldType == "world3") {
                        this.arr[i++] = "<font color='#00FFFF'>" + "You: \"Which way leads deeper into the forest?\"" + "</font>";
                        this.arr[i++] = "Miss C: \"I suggest talking to the others here in the forest and gather clues of your killer's whereabouts, it may help you later. Then enter the green portal, it leads directly to a secluded section in the forest.\"";

                        this.arr[i++] = "You: \"Do you have any clue who could be here?\"";
                        this.arr[i++] = "Miss C: \"I alread told you, it's best not to go deeper unprepared.\"";
                        
                        this.arr[i++] = "You: \"The others seem not to care.\"";
                        this.arr[i++] = "Miss C: \"I haven't told them yet. They are oblivious. I'm too scared to approach, it's too risky.\"";
                
                        this.size = i;
                        return;
                }        

                if (worldType == "world2") {   
                        this.arr[i++] = "<font color='#00FFFF'>" + "Where to now?" + "</font>";
                        this.arr[i++] = "Walk straight ahead, talk to the others, a portal shortcut will pop up at the end of the trail. More minions of Mr. Bones will be waiting for you though. I heard they were furious. HAHAHA!";
                        
                        this.arr[i++] = "You: \"Everyone is avoiding you, they said-\"";
                        this.arr[i++] = "Miss C: \"I know! Tried chatting with another soul earlier. They're all so cranky! It's like, 'Hello! We're stuck here for eternity together! Could we at least *pretend* to like each other?' I swear, they all think being moody is a personality trait.\"";
                
                        this.arr[i++] = "You: \"Are you close with Yoo?\"";
                        this.arr[i++] = "Miss C: \"Yoo? Like Yoo the dude over there, not you? Don't even get me started on that guy. Always acting so mysterious, sitting under a tree, staring dramatically into the mist. We get it, Yoo, you're emo. Join the club, honey! We're all brooding down here.\"";
                        
                        this.arr[i++] = "You: \"Have you visited the shop over there?\"";
                        this.arr[i++] = "Miss C: \"Miggins, right? Yeah, she's been trying to impress everyone with her so-called 'Homemade Pies.' Please! We all know she just heats up store-bought ones and calls it a day. It's honestly kind of sad.\"";
                        
                        this.arr[i++] = "You: \"Are you happy here?\"";
                        this.arr[i++] = "Miss C: \"Nope. 'Death is peaceful'? Lies! It's boring down here. No one talks. Except me, of course. I keep talking because if I don't, I'll probably go mad. Or maybe I already have. Who's to say?\"";
                        
                        this.arr[i++] = "You: \"Why are you giggling?\'";
                        this.arr[i++] = "Miss C: \"Oh my gosh, like, if the sun emitted Wi-Fi, trees would be like cell towers, you know? I mean, cellphones are basically fruits from those trees or whatever. Don't even get me started on how apples are used to make smartphones, like, it's science, right?";
                }

                this.arr[i++] = "<font color='#00FFFF'>" + "You: \"Can you help me out? I need to find someone, this place is way too big for me to search alone.\'" + "</font>";
                this.arr[i++] = "Miss C: \"Well, you can ask around the cetizens here! They probably know who you're talking about. I can't really remember faces. Anyway, you can also check the green portal at the end of the street.\"";
                
                this.arr[i++] = "You: \"What are you waiting for? You look impatient.\"";
                this.arr[i++] = "Miss C: \"I keep waiting for something exciting to happen down here, but nope, just the same old spirit world. I thought maybe a spirit would swing by or something. Do you think they're avoiding me? Honestly, I'm starting to take it personally.\"";
                
                this.arr[i++] = "You: \"Do you know anyone that is still alive?\"";
                this.arr[i++] = "Miss C: \"Do you think the living still remember me? Probably not, right? I wasn't exactly a celebrity. Just your average city girl. But, oh, how I like to imagine there's a statue somewhere with my name on it! A girl can dream, even in the afterlife.\"";
                
                this.arr[i++] = "You: \"Do you have any friends?\"";
                this.arr[i++] = "Miss C: \"I tried to make friends with the other souls, but they're all so… distant. Like, 'Hello! We're stuck here together for eternity, can we at least pretend to like each other?' No one ever laughs at my jokes either. Rude, right?\"";
                
                this.arr[i++] = "You: \"Have you been here for a while?\"";
                this.arr[i++] = "Miss C: \"How long have I been dead, you ask? Oh wait, you did. Well, too bad! Time is a blur down here. I feel like I've been dead for a thousand years… or maybe just ten? You lose track after a century or two. By the way, what year is it?\"";
                
                this.arr[i++] = "You: \"Do you like it here? What do you think of this place?\"";
                this.arr[i++] = "Miss C: \"The spirit world, ugh, just as bleak as you'd imagine. No sun, no moon, no stars. Just endless dark skies. And the fog! Don't even get me started on the fog. It's like a wet blanket draped over your soul 24/7.\"";

                this.size = i;
        }

        public void nattyIntro() {
                int i = 0;
            
                this.arr[i++] = "You wander through a shadowy, eerie part of the underworld. A woman stands alone by the edge of a dark river, her eyes darting nervously. You approach cautiously, sensing the tension in the air.";
                this.arr[i++] = "Natty: \"Oh... it's you? I-I didn't expect to see you again, I'm Natty. I thought maybe... maybe you'd forgotten me. It's so dark here, and I'm not sure I can do this... I'm scared.\"";
                this.arr[i++] = "You: \"Have we really met before?. I have my fair share with souls like you, I think?\"";
                this.arr[i++] = "Natty: \"Before? Ha! Of course you did! Also, I'm pretty new here. This place kind of spooky, right? I've been through worse! They can't hurt me then, and this place won't break me now!\"";
                this.arr[i++] = "Natty: \"But... what if I'm not strong enough this time? Last time, I barely made it. This place... it's worse than anything we've seen before. I-I don't think I can survive it... I'm too scared.\"";
                this.arr[i++] = "You: \"Just take a deep breath. You're not exactly alone here, Natty. We can handle this.\"";
                this.arr[i++] = "Natty: \"Alone? No! No! No! I'm never alone! That won't beat me, not now, not ever! I'm not the fragile soul you thought I was. I'm a unstoppable! I can handle anything! HAHAHA!\"";
                this.arr[i++] = "You: \"Are you... Okay? You're swinging between your moods way too fast.\"";
                this.arr[i++] = "Natty: \"No... No, I didn't! I... I can't!\"";
                this.arr[i++] = "You reach out, watching as Natty shifts between defiance and fear, her emotions like a pendulum. She seems caught in an internal struggle, and it might be best to leave her to calm down before speaking further.";
        
                this.size = i;
        }
        
        public void nattyLines() {
                int i = 0;

                this.arr[i++] = "You: \"This city looks really spooky. What do you think?\"";
                this.arr[i++] = "Natty: \"There's something about this place... it whispers to me, you know? Almost like it knows who I am.\"";

                this.arr[i++] = "You: \"Is there a way out of this city?\"";
                this.arr[i++] = "Natty: \"I guess... There's no escape, is there? Feels like I've been walking in circles for hours, days, years, oh no.\"";
                
                this.arr[i++] = "You: \"You look lonely, why are you avoiding the others?\"";
                this.arr[i++] = "Natty: \"They said I wouldn't last a day on my own. But look at me now, still breathing! This place isn't as bad as where I came from.\"";
                
                this.arr[i++] = "You: \"What was your life like before? Can you tell me about how things used to be?\"";
                this.arr[i++] = "Natty: \"It was great! I don't remember much, but I'm pretty sure it was full of happiness, I think? Honestly, though, everything feels like a blur now. Hahaha.\"";
                                
                this.arr[i++] = "You: \"No offense, but some souls here have mentioned that you're a bit mental. What happened?\"";
                this.arr[i++] = "Natty: \"You think I'm crazy? Oh, please! If I were, you wouldn't be here talking to me! You just need something from poor little Natty, don't you? Shame on you.\"";
                                
                this.arr[i++] = "You: \"Why are you giggling over there?\"";
                this.arr[i++] = "Natty: \"Wanna a hear a joke? Who sell fake pies and calls herself Miggins?\" [chuckles to herself]";
                               
                this.arr[i++] = "You: \"You seem happier than usual. What's up?\"";
                this.arr[i++] = "Natty: \"Why are asylums full of singles? Because none of them can keep their thoughts together!\" [laughs hysterically]";
                             
                this.arr[i++] = "You: \"You look anxious, am I scaring you?\"";
                this.arr[i++] = "Natty: \"You think I'm afraid of you? No. I'm not afraid of you...\"";
                               
                this.arr[i++] = "[Natty appears to panicking] You: \"Hey, is something wrong?\"";
                this.arr[i++] = "Natty: \"I can't do this! The voices—they're everywhere! Crawling under my skin, it tickles! HAHAHAHA!\"";

                this.arr[i++] = "You: \"Hey, I'm looking for someone. Have you met anyone here who might have died from drowning?\"";
                this.arr[i++] = "Natty: \"Yeah. I mean, no. I mean, I-I don't know. I don't ask souls about their death.\"";

                this.arr[i++] = "You: \"I died drowning from vehicular homicide. How about you?\"";
                this.arr[i++] = "Natty: \"I died trying to escape the asylum I'm from. Let's just say that they did an amazing job of keeping me in.\"";

                this.size = i;
        }

        public void yooIntro() {
                int i = 0;

                this.arr[i++] = "You: \"Do I know you from somewhere? You seem... familiar, somehow.\"";
                this.arr[i++] = "Yoo: \"...\"";
                this.arr[i++] = "You: \"Um, it's strange. I can't quite place you, but it feels like we've crossed paths before.\"";
                this.arr[i++] = "Yoo: \"...\"";
                this.arr[i++] = "You: \"Um, hello? Ah, I see. You're not like others, I guess. Are you the quiet type?\"";
                this.arr[i++] = "Yoo: \"What'd ya expect? You think souls just talk with strangers down here?\"";
                this.arr[i++] = "You: \"Sorry. It's just, are you...\"";
                this.arr[i++] = "Yoo: \"Single? Sorry, you're a little too late for that.\"";
                this.arr[i++] = "You: \"O-oh, I mean. Are you new here?\"";
                this.arr[i++] = "Yoo: \"I'm just someone passing through. Like everyone else. I'm here for a while now.\"";
                this.arr[i++] = "You: \"Why are you all the way here, anyway? You don't like the souls over there?\"";
                this.arr[i++] = "Yoo: \"The reason doesn't matter.\"";
                this.arr[i++] = "You: \"Sorry for prying, I was just curious.\"";
                this.arr[i++] = "Yoo: \"You should mind your own business. You seem to be the nosy type, like Constance.\"";
                this.arr[i++] = "You: \"Excuse me? I'm just trying to get to know you better.\"";
                this.arr[i++] = "Yoo: \"My story isn't worth telling. Beat it.\"";

                this.size = i;
        }
           
        public void yooLines() {
                int i = 0;

                this.arr[i++] = "You: \"Hey, what's up?\"";
                this.arr[i++] = "Yoo: \"...\"";    

                this.arr[i++] = "You: \"Hey, you look familar. Do you attend CITU?\"";
                this.arr[i++] = "Yoo: \"Don't look at me, creep.\"";

                this.arr[i++] = "You: \"Look, I'm looking for this soul. I don't exactly remember what they look like, but they might have came by here. They died too, I think?\"";
                this.arr[i++] = "Yoo: \"I see souls come here and go everyday. I don't care enough to remember faces, go ask somebody else.\"";
                
                this.arr[i++] = "You: \"Have you tried out that shop over there?\"";
                this.arr[i++] = "Yoo: \"I hate it, Miggins' pies taste like medicine.\"";
                
                this.arr[i++] = "You: \"I died like hours ago, I think? Some psycho killed me. They rammed my car, it's so unfair.\"";
                this.arr[i++] = "Yoo: \"You died unexpectedly? Damn, like everyone else here did? It's not my fault you're a bad driver.\"";
                
                this.arr[i++] = "You: \"Hi-\"";
                this.arr[i++] = "Yoo: \"Go away.\"";

                this.arr[i++] = "You: \"Hey, which way leads to the exit of the city?\"";
                this.arr[i++] = "Yoo: \"I don't know, why are you asking me? You're so annoying...\"";
                
                this.arr[i++] = "You: \"You look disgusted. Is something wrong?\"";
                this.arr[i++] = "Yoo: \"You reek of seafood. Gross...\"";
                
                this.arr[i++] = "You: \"Is it me or is it always raining around this part of the city?\"";
                this.arr[i++] = "Yoo: \"Jeez. I wonder why...\" [eye rolls]";
                
                this.arr[i++] = "You: \"It's so foggy, there's no way it has to be this THICK. Why is it so foggy?\"";
                this.arr[i++] = "Yoo: \"The weather here corresponds with the emotions of those who live under it, now shut it and leave me alone.\"";
        
                this.size = i;
        }

        public void migginsIntro(String playerType, String worldType) {
                int i = 0;
            
                if (worldType == "world1") {
                        this.arr[i++] = "You: \"I feel like I know you. Have we met before?\"";
                        this.arr[i++] = "Miggins: \"Oh, my dear, I doubt it. But then again, faces blur after a while down here, don't they?\"";
                        this.arr[i++] = "You: \"Something smells good.\"";
                        this.arr[i++] = "Miggins: \"Could be the scent of my pies. I sell it to almost everyone who wanders through. Hard to resist a good snack, even in the afterlife.\"";
                        this.arr[i++] = "You: \"That sounds lovely, I'd do anything for a slice of pi- wait. Do I need to eat? I'm already dead.\"";
                        this.arr[i++] = "Miggins: \"Well, sweetheart, the thing is... Souls do get hungry, they just don't die from it.\"";
                        this.arr[i++] = "You: \"Oh...\"";
                        this.arr[i++] = "Miggins: \"Still hungry? Not for long. My store is still very much alive! Are you interested in some apple pies or apple potions?\"";
                        this.arr[i++] = "You: \"So, how much would that be?\"";
                        this.arr[i++] = "Miggins: \"Oh, cheaper than most, I assure you.\"";
                        this.arr[i++] = "You: \"Why set up shop all the way over here?\"";
                        this.arr[i++] = "Miggins: \"Oh, I decided to set up shop here since souls like you arrive here often after death. It's also pretty peaceful around these parts.\"";
                        this.arr[i++] = "You: \"Well, that would also mean that 'they' are also here...\"";
                        this.arr[i++] = "Miggins: \"Who?\"";
                        this.arr[i++] = "You: \"Someone that I was searching for. It's important.\"";
                        this.arr[i++] = "Miggins: \"Oh, then I hope you find them soon. You're more than welcome to come back if you wish to purchase something, dear.\"";
                }
            
                if (worldType == "world2") {
                        this.arr[i++] = "Miggins: \"Well, look who it is! I was worried sick!\"";
                        this.arr[i++] = "You: \"Why?\"";
                        this.arr[i++] = "Miggins: \"The city outskirts are no joke. That creature entity out there has been tormenting everyone who tries to get close!\"";
                        this.arr[i++] = "You: \"I noticed... It wasn't easy getting here.\"";
                        this.arr[i++] = "Miggins: \"You're tougher than you look, then! Maybe you can do something about it—beat that thing, would you?\"";
                        this.arr[i++] = "You: \"It's not on my to-do list, but I'll keep it in mind.\"";
                        this.arr[i++] = "Miggins: \"Keep it in mind? Darling, if you don't, that thing'll keep scaring off my customers! Think of my livelihood!\"";
                        this.arr[i++] = "You: \"Well, I guess I have to now.\"";
                        this.arr[i++] = "Miggins: \"That's the spirit! You're a hero already. Don't let me down, sweetheart!\"";
                }
            
                if (worldType == "world3") {
                        this.arr[i++] = "[Miggins glances at you nervously, wringing his hands as you approach.]";
                        this.arr[i++] = "Miggins: \"Oh, no, no, no... This isn't good at all.\"";
                        this.arr[i++] = "You: \"What's wrong?\"";
                        this.arr[i++] = "Miggins: \"What's wrong?! That killer of yours sent something truly awful this time, a demon born of pure malice. It's out there hunting for you!\"";
                        this.arr[i++] = "You: \"I figured something was up. Miss Constance mentioned a dark aura in the forest.\"";
                        this.arr[i++] = "Miggins: \"A dark aura? Try sheer terror! That thing is relentless. You can't possibly take it on alone!\"";
                        this.arr[i++] = "You: \"I don't have much of a choice, do I?\"";
                        this.arr[i++] = "[Miggins shakes his head, her face pale with worry.]";
                        this.arr[i++] = "Miggins: \"Just promise me you'll be careful. If something happens to you... Well, I don't even want to think about it.\"";
                        this.arr[i++] = "You: \"I'll handle it. Don't worry.\"";
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
            

        public void migginsLines(String playerType, String worldType) {
                        int i = 0;

                        this.arr[i++] = "<font color='#00FFFF'>" + "You: \"How can I leave the city?\"" + "</font>";
                        this.arr[i++] = "Miggins: \"Oh! Well luckily there is a shortcut right over there, the red portal. Unfortunately, The Necromancer is guarding the otherside. So, it's best to prepare before diving right in. They operate an undercity mafia, it's ridiculous.\"";

                        this.arr[i++] = "You: \"What are those red bottles near the window of your shop?\"";
                        this.arr[i++] = "Miggins: \"Those red potions are apple flavored, it may taste like medicine since it is one.\"";

                        this.arr[i++] = "You: \"What are those red bottles near the window of your shop?\"";
                        this.arr[i++] = "Miggins: \"Those red potions are apple flavored, it may taste like medicine since it is one.\"";
                        
                        this.arr[i++] = "You: \"Why do the other souls dislike your pies?\"";
                        this.arr[i++] = "Miggins: \"Some of the denizens here just don't like me at all.\"";
                        
                        this.arr[i++] = "You: \"How can apple trees grow here in total darkness?\"";
                        this.arr[i++] = "Miggins: \"Plants grow here without the need for sunlight, the faint glow of the underworld is more than enough.\"";
                        
                        this.arr[i++] = "You: \"Is it okay if I come in and take a look?\"";
                        this.arr[i++] = "Miggins: \"Of course, my shop is open for all!\"";
                        
                        this.arr[i++] = "You: \"I died an unjust death. I take it that you died peacefully at an old age?\"";
                        this.arr[i++] = "Miggins: \"Yep, 88. It's a shame that you died that way, perhaps I could cheer you up with some of my cookings?\"";

                if (worldType == "world2") {
                        this.arr[i++] = "<font color='#00FFFF'>" + "You: \"I'm so glad to see you again, is there a shortcut that cuts right through the outskirts?\"" + "</font>";
                        this.arr[i++] = "Miggins: \"Of course! Another red portal should appear anytime now. However, is that what you want? It's best to take the longer route, since it's safer, but I understand that you're in a hurry.\"";

                        this.arr[i++] = "You: \"So, I can't starve to death?\"";
                        this.arr[i++] = "Miggins: \"Of course. Some souls could even go months without food, but the hunger is way too unbearable that most wouldn't last a year without it.\"";
                        
                        this.arr[i++] = "You: \"Do souls visit here often?\"";
                        this.arr[i++] = "Miggins: \"You'd be surprised by how many souls crave a little comfort food.\"";
                        
                        this.arr[i++] = "You: \"Do you sell anything other than pies?\"";
                        this.arr[i++] = "Miggins: \"I used to sell seafood. Unfortunately, most fishes are gobbled up by slimes.\"";
                        
                        this.arr[i++] = "You: \"Where are the customers?\"";
                        this.arr[i++] = "Miggins: \"The weather here recently is terrible. It's scaring them away!\"";
                        
                        this.arr[i++] = "You: \"You're quite energetic despite having died of old age. I guess death makes you feel younger?\"";
                        this.arr[i++] = "Miggins: \"Oh, sweetie, the grave doesn't stop me from whipping up a batch of pies!\"";
                }

                if (worldType == "world3") {
                        this.arr[i++] = "<font color='#00FFFF'>" + "You: \"Where's the red portal? I swear I just saw one right here.\"" + "</font>";
                        this.arr[i++] = "Miggins: \"I casted a temporary removal spell on it, there was too much negative energy, I don't think it will last long. Here, I'll undo it for you. Be careful what you find in the otherside, dear. I wish you the best.\"";

                        this.arr[i++] = "You: \"Do you have any more clues about them?\"";
                        this.arr[i++] = "Miggins: \"No, I'm sorry. They seem to have shrouded themselves in a deep dark aura while conjuring spells. It's like trying too see through smoke.\"";
                
                        this.arr[i++] = "You: \"Do you have any tips to defeat this demon?\"";
                        this.arr[i++] = "Miggins: \"Sorry if this comes across as a bit insensitive, but you might need to stock up on potions.\"";
                        
                        this.size = i;
                        return;
                }

                this.size = i;
        }

        public void faithfulIntro() {
                int i = 0;
            
                this.arr[i++] = "You: \"Hello. Hold on, have we met before?\"";
                this.arr[i++] = "Faithful: \"Maybe. Or maybe I just have one of those unforgettable faces.\"";
                this.arr[i++] = "You: \"I don't know, but something about you feels... familiar. Maybe it's just déjà vu.\"";
                this.arr[i++] = "Faithful: \"Déjà vu? Or maybe you've just run out of interesting things to say.\"";
                this.arr[i++] = "You: \"Huh? What's your deal?\"";
                this.arr[i++] = "Faithful: \"Deal? I prefer 'complex.' But sure, we'll go with mysterious for now.\"";
                this.arr[i++] = "You: \"You don't seem like someone who mingles much. Did I hit a nerve?\"";
                this.arr[i++] = "Faithful: \"Let's just say, I prefer quality over quantity when it comes to people.\"";
                this.arr[i++] = "You: \"Well, I mean... you don't exactly look thrilled to be here either.\"";
                this.arr[i++] = "Faithful: \"Oh, I'm thrilled. Can't you tell? This is my 'thrilled' face.\"";
                this.arr[i++] = "You: \"There's a whole crowd over there. Don't you want to hang with them?\"";
                this.arr[i++] = "Faithful: \"Crowds? Not really my thing. Too many opinions, not enough sense.\"";
                this.arr[i++] = "You: \"Okay, okay, no need to get all defensive. Just asking.\"";
                this.arr[i++] = "Faithful: \"No harm done. Just don't expect a social butterfly over here.\"";
                this.arr[i++] = "You: \"Hey, I'm just trying to be friendly here.\"";
                this.arr[i++] = "Faithful: \"I'll give you credit for effort. Not many stick around this long.\"";
            
                this.size = i;
            }
            
        public void faithfulLines() {
                int i = 0;
            
                this.arr[i++] = "You: \"Hey, you seem like the type that knows their way around.\"";
                this.arr[i++] = "Faithful: \"Depends. What's the second worth to you?\"";
            
                this.arr[i++] = "You: \"You've got a familiar look. Have we met before, like in CITU?\"";
                this.arr[i++] = "Faithful: \"Ah, CITU.. No, I don't think you'd have noticed me there.\"";
            
                this.arr[i++] = "You: \"I'm looking for someone, but I can't remember their face. Ever get that feeling?\"";
                this.arr[i++] = "Faithful: \"All the time. Faces blend together after a while. What's their story?\"";
            
                this.arr[i++] = "You: \"Have you been to that little place across the way? The one with the pies?\"";
                this.arr[i++] = "Faithful: \"Once. Let's just say, I've had better culinary adventures.\"";
            
                this.arr[i++] = "You: \"I'm still getting used to the whole being dead thing. My car got rammed out of nowhere.\"";
                this.arr[i++] = "Faithful: \"Rough way to go. But hey, now you get to join the club.\"";
            
                this.arr[i++] = "You: \"Hi there, I was wondering if—\"";
                this.arr[i++] = "Faithful: \"Careful, wondering tends to lead to trouble. What's on your mind?\"";
            
                this.arr[i++] = "You: \"I'm trying to get out of this place. Any idea which way to go?\"";
                this.arr[i++] = "Faithful: \"Ah, the eternal question. If I had an answer, I wouldn't be standing here.\"";
            
                this.arr[i++] = "You: \"You don't look too happy. Something bothering you?\"";
                this.arr[i++] = "Faithful: \"Not at all. Just my resting face. You get used to it.\"";
            
                this.arr[i++] = "You: \"Why does it always seem like it's pouring rain in this city?\"";
                this.arr[i++] = "Faithful: \"Could be worse. At least it's not snowing. Yet.\"";
            
                this.arr[i++] = "You: \"It's so foggy, it's like walking through a cloud. Is it always like this?\"";
                this.arr[i++] = "Faithful: \"Fog's the least of your worries around here. Keep your eyes sharp.\"";
            
                this.size = i;
        }

        //

        public void rubyIntro() {
                int i = 0;
            
                this.arr[i++] = "You stumble upon the edge of the city behind you. You approach the woman ahead, she's full of energy.";
                this.arr[i++] = "Ruby: \"Well, look who showed up. I'm Ruby, by the way. Not that it matters—I'm not here to make friends.\"";
                this.arr[i++] = "You: \"Have we met before? I deal with so many souls... I lose track.\"";
                this.arr[i++] = "Ruby: \"Oh, trust me, you'd remember me if you had. I'm not like the rest of these lost souls. Although...\"";
                this.arr[i++] = "Ruby: \"Just 'cause I look tough doesn't mean I don't feel the weight of this place. It's... intense.\"";
                this.arr[i++] = "You: \"I feel the same, this place is a bit too much sometimes.\"";
                this.arr[i++] = "Ruby: \"Ha! That's how I like it. I like a challenge. I've fought my own battles, and I'm still here.\"";
                this.arr[i++] = "You: \"You do seem to have gone through a lot.\"";
                this.arr[i++] = "Ruby: \"I've been through worse. Just... get out of my way, alright? I've got my own problems to deal with.\"";
            
                this.size = i;
        }
            
        public void rubyLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"The city looks really unsettling from afar. What do you think?\"";
                this.arr[i++] = "Ruby: \"Unsettling? You could say that. It does look menacing from here.\"";
            
                this.arr[i++] = "You: \"Which way leads to the forest?\"";
                this.arr[i++] = "Ruby: \"Just go straight ahead. But where's the fun in leaving? You can't do anything if you're always running away.\"";
            
                this.arr[i++] = "You: \"What do you think of my outfit?\"";
                if (playerType.equals("knight")) {
                        this.arr[i++] = "Ruby: \"Looks bulky. It makes you look slow.\"";
                } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Ruby: \"Gosh. That nurse outfit takes me back. I remember seeing someone wearing that here way too many times.\"";
                } else {
                        this.arr[i++] = "Ruby: \"Ew, are you cosplaying an anime character?\"";
                }

                this.arr[i++] = "You: \"Can you tell me about what your past life was like?\"";
                this.arr[i++] = "Ruby: \"Life was great, I hoped it stayed that way. Now, everything's gone just like that. I'm not here to reminisce.\"";
            
                this.arr[i++] = "You: \"Why are you laughing by yourself?\"";
                this.arr[i++] = "Ruby: \"Just thought of something funny. Guess you'd have to be me to get it.\"";
            
                this.arr[i++] = "You: \"You seem a bit more upbeat today. What's up?\"";
                this.arr[i++] = "Ruby: \"I'm laughing at how scared everyone here is. They should toughen up.\"";
            
                this.arr[i++] = "You: \"You look tense. Am I bothering you?\"";
                this.arr[i++] = "Ruby: \"You? Nah. I don't get scared of people, alright? Not even you.\"";
            
                this.arr[i++] = "[Ruby starts pacing] You: \"Is something wrong?\"";
                this.arr[i++] = "Ruby: \"Nothing's wrong. Just... too much silence. Puts me on edge.\"";
            
                this.arr[i++] = "You: \"I'm looking for someone. Ever met any souls here who drowned just recently?\"";
                if (playerType.equals("knight")) {
                        this.arr[i++] = "Ruby: \"You and some other guy. He was really cute, that says a lot from what I've seen around here, hahaha.\"";
                } else if (playerType.equals("priest")) {
                        this.arr[i++] = "Ruby: \"No, I mean yes. She's been around here somewhere. I guess she's a bit chatty and unpredictable?\"";
                } else {
                        this.arr[i++] = "Ruby: \"Yep, some dude talking about how pissed he was at dying like that. He got a hair thing growing out his chin.\"";
                }
            
                this.arr[i++] = "You: \"I drowned. How about you?\"";
                this.arr[i++] = "Ruby: \"Died escaping from... a situation that held me down too long, just like you did. Anyway, guess I didn't make it. But I'm here now, so no use crying over it.\"";
            
                this.size = i;
        }

        public void reginaldIntro() {
                int i = 0;
            
                this.arr[i++] = "You approach the edge of the city, where you see a well-dressed gentleman standing alone, adjusting his suit with exaggerated care.";
                this.arr[i++] = "Reginald: \"Ah! A visitor, splendid! I am Reginald. A pleasure, indeed.\"";
                this.arr[i++] = "You: \"I'm sure you're a pleasure and all that. Have we met before?\"";
                this.arr[i++] = "Reginald: \"Oh, dear, I do believe not. I would recall a face such as yours. It would be improper to forget an acquaintance.\"";
                this.arr[i++] = "You: \"Well, that's just fantastic.\"";
                this.arr[i++] = "Reginald: \"Although I must admit, this place, as they call it... the underworld? It's rather lacking in refinement, wouldn't you agree?\"";
                this.arr[i++] = "You: \"Not exactly a vacation spot, but you get used to it.\"";
                this.arr[i++] = "Reginald: \"Indeed, but one must rise above it! My attire may not be entirely suitable for these surroundings, but one must maintain a sense of dignity.\"";
                this.arr[i++] = "You: \"Sure, because dignity's so helpful down here.\"";
                this.arr[i++] = "Reginald: \"Ah, sarcasm. I do find it rather charming.\"";
            
                this.size = i;
        }
            
        public void reginaldLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"The city looks quite imposing from here. What do you think?\"";
                this.arr[i++] = "Reginald: \"Imposing, perhaps, but I find it rather... gauche. Could use a touch of elegance, wouldn't you agree?\"";
            
                this.arr[i++] = "You: \"Which way leads to the forest?\"";
                this.arr[i++] = "Reginald: \"Ah, through the archway, I believe. Though I must say, wandering into forests is hardly the proper thing to do.\"";
            
                this.arr[i++] = "You: \"What do you think of my outfit?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Reginald: \"Ah, very... sturdy. Though, it does lack a bland.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Reginald: \"Ah, quite dignified! We need more nurses in the world. If so, I would still be alive right now.\"";
                } else {
                    this.arr[i++] = "Reginald: \"Oh, my! It's, um, quite... unique, isn't it?\"";
                }
            
                this.arr[i++] = "You: \"Can you tell me about your past life?\"";
                this.arr[i++] = "Reginald: \"Ah, splendid days of grandeur, indeed! Fine dining, splendid attire, and the most impeccable gatherings. Those were the days.\"";
            
                this.arr[i++] = "You: \"Why are you smiling like that?\"";
                this.arr[i++] = "Reginald: \"Ah, just recalling the time I hosted the most extravagant soirée. Such splendid company!\"";
            
                this.arr[i++] = "You: \"You seem in high spirits today. Any particular reason?\"";
                this.arr[i++] = "Reginald: \"Ah, one must remain buoyant, regardless of one's circumstances. It is, after all, the hallmark of a true gentleman.\"";
            
                this.arr[i++] = "You: \"You look tense. Is something bothering you?\"";
                this.arr[i++] = "Reginald: \"Oh, no, not at all! I merely find it rather challenging to maintain one's dignity amid such disorder. Look at this place, quite, um... messy?\"";
            
                this.arr[i++] = "You: \"I'm looking for someone. Ever met any souls here who drowned?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Reginald: \"Why yes. Quite dashing, I must say, if a bit rough around the edges.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Reginald: \"Ah, yes, a most peculiar lady in attire much like yours. She was quite... unwell, to say the least.\"";
                } else {
                    this.arr[i++] = "Reginald: \"Hmm, yes, there was a chap who seemed rather perturbed about his unfortunate... departure.\"";
                }
            
                this.arr[i++] = "You: \"I drowned. How about you?\"";
                this.arr[i++] = "Reginald: \"Oh, well, my departure was rather less dramatic, I fear. Passed away quietly, but with all the dignity befitting a gentleman.\"";
            
                this.size = i;
        }
        
        public void asrielIntro() {
                int i = 0;
            
                this.arr[i++] = "You approach the edge of the city and spot a figure brimming with energy, his voice booming as he greets you.";
                this.arr[i++] = "Asriel: \"Hey! Finally, some company! I'm Asriel, and I'm ready to bring some action around here!\"";
                this.arr[i++] = "You: \"Have we crossed paths before? Can't say I remember everyone I meet.\"";
                this.arr[i++] = "Asriel: \"Oh, trust me, you'd remember me! I'm not one of those quiet, lost souls milling around.\"";
                this.arr[i++] = "Asriel: \"Just because I'm all fired up doesn't mean I'm clueless about this place. It's rough, but I love a good thrill!\"";
                this.arr[i++] = "You: \"Not everyone sees it that way. Guess you're one of a kind here.\"";
                this.arr[i++] = "Asriel: \"You bet I am! Challenges don't scare me; they make me feel alive!\"";
                this.arr[i++] = "You: \"Seems like you've fought your fair share of battles.\"";
                this.arr[i++] = "Asriel: \"You bet! Now, enough talk—if you're with me, let's move. If not, stay out of my way!\"";
            
                this.size = i;
        }
            
            public void asrielLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"From here, the city has this intense vibe. What's your take on it?\"";
                this.arr[i++] = "Asriel: \"Intense? Nah, it's practically calling us for a showdown!\"";
            
                this.arr[i++] = "You: \"Which path leads to the forest?\"";
                this.arr[i++] = "Asriel: \"Straight ahead! But honestly, what's the rush? Running off isn't my thing.\"";
            
                this.arr[i++] = "You: \"So, what do you think of this outfit?\"";
                if (playerType.equals("knight")) {
                    this.arr[i++] = "Asriel: \"Looks bulky! Can you even move in that?\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Asriel: \"Haha, it's got this old-school nurse vibe! Retro cool, I guess.\"";
                } else {
                    this.arr[i++] = "Asriel: \"You look like you're ready for an anime convention. Nice!\"";
                }
            
                this.arr[i++] = "You: \"Care to tell me a bit about your past life?\"";
                this.arr[i++] = "Asriel: \"Eh, life was a rush, but who cares about the past? I'm here to face whatever's next!\"";
            
                this.arr[i++] = "You: \"What's got you laughing to yourself?\"";
                this.arr[i++] = "Asriel: \"Ha! Just remembered something hilarious. Doubt you'd get it unless you were me.\"";
            
                this.size = i;
        }
            
            public void akifayIntro() {
                int i = 0;
            
                this.arr[i++] = "You approach the edge of the city, noticing a reserved figure standing alone, looking down as if lost in thought.";
                this.arr[i++] = "Akifay: \"Oh… hello. I didn't expect anyone to stop by. I'm… Akifay.\"";
                this.arr[i++] = "You: \"I don't think we've met before, right?\"";
                this.arr[i++] = "Akifay: \"No, I'd remember… I think. I… tend to keep to myself around here.\"";
                this.arr[i++] = "You: \"It's alright. This place has a way of making anyone feel out of sorts.\"";
                this.arr[i++] = "Akifay: \"Yes… it's so strange, so… heavy. Hard to find any comfort here.\"";
                this.arr[i++] = "You: \"It takes some getting used to. Most just try to stay grounded however they can.\"";
                this.arr[i++] = "Akifay: \"I suppose… I just try to stay unnoticed. I don't mean to be a bother.\"";
                this.arr[i++] = "You: \"Don't worry, you're not a bother. I'm glad we crossed paths.\"";
                this.arr[i++] = "Akifay: \"Oh… thank you. That's… very kind of you to say.\"";
            
                this.size = i;
        }
            
            public void akifayLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"From here, the city seems pretty overwhelming, doesn't it?\"";
                this.arr[i++] = "Akifay: \"Yes… it's so vast, so overpowering. Makes me feel so small.\"";
            
                this.arr[i++] = "You: \"Which path takes me to the forest?\"";
                this.arr[i++] = "Akifay: \"Oh, um… straight ahead. But… if you don't have to, I wouldn't go there.\"";
            
                this.arr[i++] = "You: \"What do you think of this outfit?\"";
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
            
                this.arr[i++] = "You: \"Hey! You need to get out of here!\"";
                this.arr[i++] = "Chea: \"Oh, and who might you be? Don't even think about sneaking past me.\"";
                this.arr[i++] = "You: \"I don't have time for this. There's something dangerous out there.\"";
                this.arr[i++] = "Chea: \"Dangerous? Oh, honey, you've got to be more specific. I've lived here long enough to know there's always danger. Now spill—what is it?\"";
                this.arr[i++] = "You: \"A demon. It's roaming the forest, hunting anything that moves. You need to be careful.\"";
                this.arr[i++] = "Chea: \"A demon, you say? What does it look like? Big? Small? Does it have wings? Horns? And why are you so worried about it?\"";
                this.arr[i++] = "You: \"I don't have time to answer your questions. Just stay away from the forest!\"";
                this.arr[i++] = "Chea: \"Stay away? Oh, please. I can take care of myself. But now you've got me curious—what were YOU doing near the forest anyway?\"";
                this.arr[i++] = "You: \"Well, I tried to warn you. Just stay out of trouble.\"";
                this.arr[i++] = "Chea: \"Oh, please...\"";

                this.size = i;
            }
            
            public void cheaLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"You really shouldn't be wandering around here. That demon could attack at any moment.\"";
                this.arr[i++] = "Chea: \"A demon attack? Oh, dear. And where exactly was this, hmm? Don't leave out any details!\"";
            
                this.arr[i++] = "You: \"I'm serious. The forest isn't safe. Why aren't you listening?\"";
                this.arr[i++] = "Chea: \"Listening? Oh, I'm listening. I just want to know why someone like you seems so worked up. Have you actually seen it? What does it DO?\"";
            
                this.arr[i++] = "You: \"Why are you persistent on staying?\"";
                this.arr[i++] = "Chea: \"Because you're acting all mysterious about it! What's a demon doing out here anyway? The suspense is killing, I need to see this go down!\"";
            
                this.arr[i++] = "You: \"Look, I don't want anyone else to get hurt. Please, take my warning seriously.\"";
                this.arr[i++] = "Chea: \"Oh, aren't you sweet? But tell me—did the demon say anything? Does it talk? I always imagined demons would be scary. I'm pretty sure they're not supposed here unless a wicked soul made a deal with the devil. Oh, the tea!\"";
            
                this.arr[i++] = "You: \"You're not taking this seriously. It's a real threat.\"";
                this.arr[i++] = "Chea: \"I'll take it seriously once I know more about it. Do you think it's coming here? Should I get ready? What do you think it wants?\"";
            
                this.arr[i++] = "You: \"If you keep asking questions, you might just find out the hard way.\"";
                this.arr[i++] = "Chea: \"Oh, don't be dramatic. I've dealt with plenty of problems in my time. But a demon, now that's exciting! Tell me everything! A hellish creature could only be summoned through malicious drama, now that's entertainment!\"";

                if (playerType == "knight") {
                        this.arr[i++] = "You: \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "blood" + "</font>" + " all over them.\"";
                }
                if (playerType == "wizard") {
                        this.arr[i++] = "You: \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "scars" + "</font>" + " all over them.\"";
                } 
                if (playerType == "priest") {
                        this.arr[i++] = "You: \"Can you atleast tell me if you know anything about a-\"";
                        this.arr[i++] = "Chea: \"Murderer? Oh yes! I told Constance, she visits here all the time. I swear they had " + "<font color='#FF0000'>" + "blood" + "</font>" + ".\"";
                }

                this.size = i;
        }
            

        public void wizardIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Wizard: \"*coughing* Freedom... finally! Thank you, fellow wanderer. That void-spawn had me trapped in its pocket dimension for what felt like eternities.\"";
                
                this.arr[i++] = "You: \"Another soul in this purgatory? Who are you?\"";
                
                this.arr[i++] = "Wizard: \"*adjusts crooked glasses* They used to call me the Lord of Leaky Abstractions back at CITU. Ironic, considering I couldn't debug my way out of that prison.\"";
                

                if (playerType.equals("priest")) {
                        this.arr[i++] = "You: \"Computer science? I was in nursing before... all this happened.\"";
                        
                        this.arr[i++] = "Wizard: \"*genuine interest* A nursing student! Finally, someone who might understand what I mean when I say my code needs healing. Though I guess we're both dealing with a different kind of debugging now.\"";
                        
                        this.arr[i++] = "You: \"Different field, same destination, huh? How did you end up here?\"";
                        
                        this.arr[i++] = "Wizard: \"*smile fades slightly* Funny how life works. There I was, stressed about a memory leak in my code, then suddenly dealing with a much more literal version of that problem. One distracted driver later, and... well, you know how that story ends.\"";
                } 

                else if (playerType.equals("knight")) {
                    this.arr[i++] = "You: \"Computer science? Quite different from the law books I used to study.\"";
                    
                    this.arr[i++] = "Wizard: \"*perks up* A law student! Well, I guess we both got a crash course in mortality laws, huh? No objection there, your honor. *adjusts glasses awkwardly*\"";
                    
                    this.arr[i++] = "You: \"I'll let that pun slide. But what brought you to this place?\"";
                    
                    this.arr[i++] = "Wizard: \"Same case as yours, different verdict. One distracted driver later, and suddenly a future lawyer and a budding programmer find themselves in the ultimate court of appeals.\"";
                }
            
                this.arr[i++] = "Wizard: \"*becomes serious* That entity that imprisoned me... It feeds on the memories of the deceased, leaving them hollow.\"";
                
                this.arr[i++] = "You: \"Is that why you couldn't escape?\"";
                
                this.arr[i++] = "Wizard: \"*nods grimly* It kept consuming my memories, bit by bit. I almost forgot who I was. The only thing it couldn't take was my terrible puns. Even it had standards.\"";
                
                if (playerType.equals("priest")) {
                    this.arr[i++] = "Wizard: \"It probably thought my jokes would cause terminal errors in its system. Get it? Terminal? Because... oh nevermind.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "Wizard: \"It probably found my jokes in contempt of court. That one was for you, future lawyer.\"";
                }
                
                this.arr[i++] = "You: \"And now you're free...\"";
                
                this.arr[i++] = "Wizard: \"Thanks to you. I heard you're hunting for your killer too. Two heads are better than one, especially when one of them can code... albeit with questionable quality.\"";
                
                if (playerType.equals("priest")) {
                    this.arr[i++] = "You: \"I suppose having someone who can 'debug' our situation couldn't hurt.\"";
                    this.arr[i++] = "Wizard: \"That's the spirit! Though I warn you, my debugging usually creates more bugs than it fixes.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "You: \"I suppose having a tech expert as a witness couldn't hurt our case.\"";
                    this.arr[i++] = "Wizard: \"Exactly! Though I should warn you, my testimony tends to crash and burn... much like my code.\"";
                }
            
                this.arr[i++] = "Wizard: \"Together, we might actually have a shot at finding peace... and revenge. What do you say, partner?\"";

                this.size = i;
        }

        public void wizardLines(String playerType) {
                int i = 0;
            
                
                this.size = i;
        }

        public void knightIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Knight: \"*stretches stiff muscles* By the scales of justice... I'm free! Your timing is impeccable, stranger.\"";
                
                this.arr[i++] = "You: \"Another trapped soul? Who are you?\"";
                
                this.arr[i++] = "Knight: \"*straightens tie* Former law student. Top of my class at CITU, if that still means anything in purgatory. Though I suppose my mock trial experience didn't help much against that... thing.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "You: \"A law student? Must be quite a change from debugging code to interpreting laws.\"";
                    
                    this.arr[i++] = "Knight: \"*chuckles* And here I thought I'd seen every type of syntax error. Turns out supernatural law is a whole different jurisdiction. You're a CS student then?\"";
                    
                    this.arr[i++] = "You: \"Was. Until a crash that had nothing to do with computers.\"";
                    
                    this.arr[i++] = "Knight: \"*grimly* Seems we both got force-quit from life's program, didn't we? At least you've kept your sense of humor about it.\"";
                } 
                else if (playerType.equals("priest")) {
                    this.arr[i++] = "You: \"From law school to purgatory. Quite a career change.\"";
                    
                    this.arr[i++] = "Knight: \"*wry smile* Well, I went from studying cases to becoming one. Though I suspect your nursing background might be more useful here than my knowledge of tort law.\"";
                    
                    this.arr[i++] = "You: \"Healing the living is one thing. This place... it's something else entirely.\"";
                    
                    this.arr[i++] = "Knight: \"*thoughtfully* At least we both dedicated our studies to helping others. Fat lot of good that did us in the end.\"";
                }
            
                this.arr[i++] = "You: \"What was holding you prisoner?\"";
                
                this.arr[i++] = "Knight: \"*expression darkens* That entity that imprisoned me... It traps them in endless trials of their worst regrets. It... it made me relive my final case over and over.\"";
                
                this.arr[i++] = "You: \"Your final case?\"";
                
                this.arr[i++] = "Knight: \"*adjusts collar* I was heading to the courthouse for my first real trial. Pro bono case, defending someone who couldn't afford representation. Never made it there. The driver had other plans.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Knight: \"Now that's what I call a fatal exception. *sees your expression* Sorry, picked that up from my CS roommate.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Knight: \"The ultimate mistrial, you could say. Though I doubt any amount of healthcare could've helped at that point.\"";
                }
            
                this.arr[i++] = "You: \"And the Verdict Reaper kept you reliving that moment?\"";
                
                this.arr[i++] = "Knight: \"*nods* Every time I'd try to change the outcome, find a different route, prepare earlier... but the verdict was always the same. Until you showed up.\"";
                
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Knight: \"I don't suppose you could debug this whole afterlife situation? *small smile* No? Well, I still owe you one. And a good lawyer always pays their debts.\"";
                } else if (playerType.equals("priest")) {
                    this.arr[i++] = "Knight: \"I may not be able to heal like you, but I know how to build a case. And right now, we've got a pretty strong one for getting out of here together.\"";
                }
            
                this.arr[i++] = "Knight: \"I hear you're searching for answers too. Perhaps we can help each other appeal this... situation we've found ourselves in. What do you say?\"";
                
                this.size = i;
        }

        public void knightLines(String playerType) {
                int i = 0;
                
                
                this.size = i;
        }

        public void priestIntro(String playerType) {
                int i = 0;
                
                this.arr[i++] = "Priest: \"*stretching* Oh thank god, real company! The last conversation I had was with a wall. The wall won.\"";
                
                this.arr[i++] = "You: \"Another trapped soul?\"";
                
                this.arr[i++] = "Priest: \"*brushing off spectral dust* Nursing student. Well, was. Got the 'eternal rest' part of the job description a bit early.\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "You: \"Computer science here. Or was, until...\"";
                    
                    this.arr[i++] = "Priest: \"Until life threw a blue screen of death at you? Yeah, same boat. Different crash.\"";
                    
                    this.arr[i++] = "You: \"That was actually... pretty good.\"";
                    
                    this.arr[i++] = "Priest: \"*grins* Three years of dealing with my CS roommate's tech jokes. Now I'm stuck with them forever. Talk about eternal punishment.\"";
                } 
                else if (playerType.equals("knight")) {
                    this.arr[i++] = "You: \"Law student. Before all this.\"";
                    
                    this.arr[i++] = "Priest: \"*amused* A lawyer and a nurse walk into purgatory... sounds like the start of a really bad joke.\"";
                    
                    this.arr[i++] = "You: \"And here we are at the punchline.\"";
                    
                    this.arr[i++] = "Priest: \"At least we're killing it with the gallows humor. Too soon?\"";
                }
            
                this.arr[i++] = "You: \"What was keeping you here?\"";
                
                this.arr[i++] = "Priest: \"*expression darkens*  That entity that imprisoned me... Nasty piece of work. Trapped me in an endless shift of worst-case scenarios. And I thought night rotation was bad.\"";
                
                this.arr[i++] = "You: \"How did you end up here?\"";
                
                this.arr[i++] = "Priest: \"Rushing to cover an emergency shift. Plot twist - became the emergency instead. Universe has a weird sense of humor, doesn't it?\"";
            
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "You: \"No respawns in this game, huh?\"";
                    
                    this.arr[i++] = "Priest: \"*laughs* Nope. And the graphics here are terrible. Though the ghost effects are pretty realistic.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "You: \"Talk about a mistrial of fate.\"";
                    
                    this.arr[i++] = "Priest: \"*smirks* Objection! Actually, no, that's fair.\"";
                }
            
                this.arr[i++] = "Priest: \"That thing kept me trapped in there for... well, time's weird here. Long enough to memorize every ceiling tile in my phantom hospital.\"";
                
                if (playerType.equals("wizard")) {
                    this.arr[i++] = "Priest: \"Thanks for the rescue. Your debug skills just saved my afterlife.\"";
                } else if (playerType.equals("knight")) {
                    this.arr[i++] = "Priest: \"Thanks for the jailbreak. First time being happy to see a lawyer show up.\"";
                }
            
                this.arr[i++] = "You: \"What now?\"";
                
                this.arr[i++] = "Priest: \"*straightens up* Well, I hear you're hunting down your own tragic backstory. Could use a hand? I promise my bedside manner is way better than my death-side manner.\"";
                System.out.println("size = " + i);
                this.size = i;
        }

        public void priestLines(String playerType) {
                int i = 0;
            
                this.arr[i++] = "You: \"Hey, you seem like the type that knows their way around.\"";
                this.arr[i++] = "Faithful: \"Depends. What's the second worth to you?\"";
            
                this.arr[i++] = "You: \"You've got a familiar look. Have we met before, like in CITU?\"";
                this.arr[i++] = "Faithful: \"Ah, CITU.. No, I don't think you'd have noticed me there.\"";
            
                this.arr[i++] = "You: \"I'm looking for someone, but I can't remember their face. Ever get that feeling?\"";
                this.arr[i++] = "Faithful: \"All the time. Faces blend together after a while. What's their story?\"";
            
                this.arr[i++] = "You: \"Have you been to that little place across the way? The one with the pies?\"";
                this.arr[i++] = "Faithful: \"Once. Let's just say, I've had better culinary adventures.\"";
            
                this.arr[i++] = "You: \"I'm still getting used to the whole being dead thing. My car got rammed out of nowhere.\"";
                this.arr[i++] = "Faithful: \"Rough way to go. But hey, now you get to join the club.\"";
            
                this.arr[i++] = "You: \"Hi there, I was wondering if—\"";
                this.arr[i++] = "Faithful: \"Careful, wondering tends to lead to trouble. What's on your mind?\"";
            
                this.arr[i++] = "You: \"I'm trying to get out of this place. Any idea which way to go?\"";
                this.arr[i++] = "Faithful: \"Ah, the eternal question. If I had an answer, I wouldn't be standing here.\"";
            
                this.arr[i++] = "You: \"You don't look too happy. Something bothering you?\"";
                this.arr[i++] = "Faithful: \"Not at all. Just my resting face. You get used to it.\"";
            
                this.arr[i++] = "You: \"Why does it always seem like it's pouring rain in this city?\"";
                this.arr[i++] = "Faithful: \"Could be worse. At least it's not snowing. Yet.\"";
            
                this.arr[i++] = "You: \"It's so foggy, it's like walking through a cloud. Is it always like this?\"";
                this.arr[i++] = "Faithful: \"Fog's the least of your worries around here. Keep your eyes sharp.\"";
            
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
                                                "You've been awarded a temporary increase of +10 base hp for your dedication to poking the dead. The devs were going to patch this out, but decided it was too funny to remove.";
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
                this.arr[i++] = "You know, kicking a skeleton while it's down is just rude.";
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
                "We're not sure why you felt the need to click on the Gorgon's corpse 15 times, but your commitment is impressive. As a reward, you've been granted a permanent boost to your Health. Try not to stare at it too long, or you might end up like the Gorgon.";
                this.size = i;
        }

        public void questNotComplete(){
                int i = 0;
                this.arr[i++] = "Complete the current quest!";
        }

        public void skillDetails(String playerType) {
                int i = 0;
                
                this.arr[i++] = "You have approached an enemy!";
                this.arr[i++] = "Basic Skill: A basic attack, will deal little damage for little mana.";
                
                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Objection Surge: Sacrifice 15 Soul shards to buff attack by 15 for 2 turns";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Overclock: Sacrifice 15 Mana to buff attack by 15 for 2 turns";
                }else{
                        this.arr[i++] = "Vital Rush: Sacrifice 15 HP to buff attack by 15 for 2 turns";
                }
                
                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Ethereal Shield of Logic: Defends against the next damage taken by 40%. If damage taken is greater than 20% of soul energy left, gain 30 Soul Shards this round.";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Quantum Shift: Has a 45% chance of evading the next attack. If successful, deal 35 damage and gain 90 mana.";
                }else{
                        this.arr[i++] = "Vital Strike: Deals 40% of the MC's Base HP as damage to the target.";
                }

                if(playerType.equals(("knight").toString())){
                        this.arr[i++] = "Truthbinding: Deal 200% Attack + 15% Soul Shards damage and the enemy can't attack this turn.";
                }else if(playerType.equals(("wizard").toString())){
                        this.arr[i++] = "Azure Inferno: Incenerates enemy in blue flames, dealing 30 + 30% Base Mana";
                }else{
                        this.arr[i++] = "Vengeful Vitality: Deals 60% of missing HP to opponent and heals 40% of base HP to the party";
                }
                
                this.size = i;
        }

        public void preEnding() {
                int i = 0;

                this.arr[i++] = "Your journey nears its ends. You can't begin to fathom the idea of how far you've come. The friends and foes you made along the way, the obstacles you had to take, and the painstaking long walk to get here. You are finally given the decision to bring forth justice and finally claim your right to live again.";
                this.arr[i++] = "The darkness around you thickens, as if waiting for your next move, feeding off your hesitation. The hollowed voice of Death echoes again, its ethereal presence sending a chill down your spine.";
                this.arr[i++] = "\"Your time is slipping away. Have you made a decision yet?\"";
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
                this.arr[i++] = "You took a look again, this time, you looked closely at this petite looking girl. Then, something hit you. These eyes, this face, and this conversation! You've heard a hundred times. Hell, you've even heard it today!";
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
                this.arr[i++] = "\"YOU TOTALLY ARE CRAZY. FINE. LET'S END THIS AND SEE IF YOU REALLY STILL CAN LIVE.\"";

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
                this.arr[i++] = "Your fists clench at your sides, nails digging into your palms. You can feel the rage bubbling inside you, a molten fury that threatens to consume you.";
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
                this.arr[i++] = "You're back. Back in the world of the living.";               
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
                return "<html><center>" + this.arr[i] + "</center></html>";
        }

        public int getSize() {
                return this.size;
        }

        public String[] getArr() {
                return arr;
        }
}