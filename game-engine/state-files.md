[← back to game rules](game-rules.md "The readme file that explain the game rules")

### Json File [*example*](assets/example-state/state.json "An example of the JSON state file")

##### A file called "state.json", containing the following game details:
* currentRound → *The current round number*
* maxRounds → *The maximum number of allowed rounds for this match*
* pushbackDamage → *The amount of damage that worms will sustain when moving into the same cell on the same round*
* lavaDamage → *The amount of damage that worms will sustain when standing on a lava cell*
* mapSize → *The number of rows or columns in the map*
* currentWormId → *The Id number of your worm that is currently in play*
* consecutiveDoNothingCount → *The number of consecutive **do nothing** commands that you have submitted in this match*
* myPlayer → *Your player's details*
  * id → *The Id number of your player* 
  * score → *Your score points acquired*
  * health → *The sum of all your worms' hitpoints*
  * currentWormId  → *The Id number of your worm that is currently in play. This is the same as the currentWormId above*
  * remainingWormSelections → *The amount of custom selections via the Select command that is available to the player*
  * previousCommand → *The command which this player supplied to the engine on the last round as a string.  e.g. 'move 20 9'.  Note that it doesn't contain 'C;\<number\>;'*
  * worms → *The list of all your worms. Worms in this list look like a **[default worm](#default-worm-properties)**, and in addition you can also see these extra attributes of your own worms:*
    * weapon → *The weapon that this worm is capable of using*
      * damage → *The number of hitpoints that this weapon can remove per shot*
      * range → *The distance to which this weapon can affect opponents*
    * bananaBombs → *The Banana Bombs that this worm has at it's disposal. Only applicable to the Agent worm*
      * damage → *The peak damage inflicted by this weapon, at the center cell*
      * range → *The distance to which this can be thrown*
      * count → *The amount of Banana Bombs in this worm's inventory*
      * damageRadius → *The radius to which the impact will destroy dirt blocks and damage worms*
    * snowballs → *The Snowballs that this worm has at it's disposal. Only applicable to the Technologist worm*
      * freezeDuration → *The amount of rounds that target worms will be frozen for*
      * range → *The distance to which this can be thrown*
      * count → *The amount of Snowballs in this worm's inventory*
      * freezeRadius → *The radius to which targets will be frozen*
      
      
* opponents → *The player details of your opponents. An example of an opponent looks like the following:*
  * id → *The Id number of this player*
  * score → *This player's score points currently acquired*
  * currentWormId  → *The Id number of the opponent player's worm that is currently in play*
  * previousCommand → *The command which this player supplied to the engine on the last round as a string.  e.g. 'move 20 9'.  Note that it doesn't contain 'C;\<number\>;'*
  * worms → *A list of all the worms under this player's control. Worms in this list look like a **[default worm](#default-worm-properties)***
  
   
* map → *A list of lists of cells (2d array) describing the map world. Each cell looks like this:*
  * x → *An x coordinate number between 0-32 (inclusive)*
  * y → *A y coordinate number between 0-32 (inclusive)*
  * type → *The surface type of this cell (AIR, DIRT, DEEP_SPACE, or LAVA)*
  * occupier → *Any worm occupying this cell will have it's details displayed here, and looks like a **[default worm](#default-worm-properties)***, and in addition this property:
    * playerId → *The Id number of the player in control of this worm* 
    * *If this is your worm, you will also see the extra worm details here*
  * powerup → *The powerup placed in this cell (if any)*
    * type → *The type of powerup (HEALTH_PACK)*
    * value → *The strength of this powerup*

##### Default worm properties 
* id → *The Id number of this worm*
* health → *The number of remaining hitpoints for this worm*
* position → *The current position of this worm*
  * x → *An x coordinate number between 0-32 (inclusive)*
  * y → *A y coordinate number between 0-32 (inclusive)*
* diggingRange → *A number describing the range that this worm can dig around itself*
* movementRange → *A number describing the range that this worm can move around itself*
* roundsUntilUnfrozen → *A number stating that this worm will be frozen for a number of rounds*
* profession → *A string describing the worm type. This could be*
  * Commando
  * Agent
  * Technologist
 
### Text File [*example*](assets/example-state/state.txt "An example of the Text state file")

This file is encoded using UTF-8

##### A file called "state.txt", containing the following game details:
* @&#8203;01 Match Details → *Section denoting the overview details*
  * Section lines count → *The number of lines in this section*
  * Current round → *The current round number*
  * Max rounds → *The maximum number of allowed rounds for this match*
  * Map size → *The number of rows or columns in the map*
  * Current worm id → *The Id number of your worm that is currently in play*
  * Consecutive do nothing count → *The number of consecutive **do nothing** commands that you have submitted in this match*
  * Players count → *The total number of players stated in this file*
  * Worms per player → *The number of worms denoted to each player stated in this file*
  * Pushback damage → *The amount of damage that worms will sustain when moving into the same cell on the same round*
  * Lava damage → *The amount of damage that worms will sustain when standing on a lava cell*


* @&#8203;02 My Player → *Section denoting your details*
  * Section lines count → *The number of lines in this section*
  * Player id → *The Id number of this player*
  * Score → *This player's score points currently acquired*
  * Selection Tokens → *The amount of custom selections via the Select command that is available to the player*
  * Previous Command → *The command which this player supplied to the engine on the last round as a string.  e.g. 'move 20 9'.  Note that it doesn't contain 'C;\<number\>;'*
  * Health → *The sum of all your worms' hitpoints*
  * Current worm id → *The Id number of your worm that is currently in play. This is the same as the currentWormId above*
  * Worms → *The list of all your worms. Worms in this list look like a **[default worm](#default-text-worm-properties)**, except that in addition you can also see these extra attributes of your own worms:* 
    * Weapon damage → *The number of hitpoints that this weapon can remove per shot*
    * Weapon range → *The distance to which this weapon can affect opponents* 
    * Banana bomb damage → *The peak damage inflicted by this weapon, at the center cell* 
    * Banana bomb range → *The distance to which this can be thrown* 
    * Banana bombs count → *The amount of Banana Bombs in this worm's inventory* 
    * Banana bomb damage radius → *The radius to which the impact will destroy dirt blocks and damage worms*
    * Snowballs freeze duration → *The amount of rounds that target worms will be frozen for*
    * Snowballs range → *The distance to which this can be thrown*
    * Snowballs count → *The amount of Snowballs in this worm's inventory*
    * Snowballs freeze radius → *The radius to which targets will be frozen*


* @&#8203;03 Opponents → *Section denoting the list of opponents in this match*
  * Section lines count → *The number of lines in this section*
  * Player id → *The Id number of this player*
  * Score → *This player's score points currently acquired*
  * Previous Command → *The command which this player supplied to the engine on the last round as a string.  e.g. 'move 20 9'.  Note that it doesn't contain 'C;\<number\>;'*
  * Current worm id → *The Id number of the opponent player's worm that is currently in play*
  * Worms → *A list of all the worms under this player's control. Worms in this list look like a **[default worm](#default-text-worm-properties)***


* @&#8203;04 Special Items → *Section denoting the special items in this match*
  * Section lines count → *The number of lines in this section*
  * HEALTH_PACK → *The amount of hitpoints that this will replenish*


* @&#8203;05 Legend → *Section denoting the characters used to draw the map*
  * Section lines count → *The number of lines in this section*
  * DEEP_SPACE: ██ *ASCII:219*
  * DIRT: ▓▓ *ASCII:178*
  * AIR: ░░ *ASCII:176*
  * LAVA: **XX**
  * HEALTH_PACK: ╠╣ *ASCII:204, 185*
  * WORM_MARKER: 13 *Example for:Player1, Worm3*

 
* @&#8203;06 World Map → *Section denoting the current map state*
  * Section lines count → *The number of lines in this section*
  * The map is 32 lines long
  * Each cell in the map is made of double characters, thus each line is 64 characters long

##### Default text worm properties 
* Worm id → *The Id number of this worm*
* Health → *The number of remaining hitpoints for this worm*
* Position x → *An x coordinate number between 0-32 (inclusive)*
* Position y → *A y coordinate number between 0-32 (inclusive)*
* Digging range → *A number describing the range that this worm can dig around itself*
* Movement range → *A number describing the range that this worm can move around itself*
* Rounds until unfrozen → *A number stating that this worm will be frozen for a number of rounds*
* Profession → *A string describing the worm type. This could be*
  * Commando
  * Agent
  * Technologist

### Console [*example*](assets/example-state/console.txt "An example of the console file")

* My Player → *The attributes of your player*
  * H → *The sum of all your worms' hitpoints*
  * S → *Your score points currently acquired*
  * W → *The Id number of the worm you are currently commanding*
  * X,Y → *The x,y coordinates of the currently selected worm*
* Player {Id} → *A list of opponents and their attributes*
  * H → *The sum of all their worms' hitpoints*
  * S → *This player's score points currently acquired*  
* *A map drawn with double characters per map cell, to describe where entities are*
