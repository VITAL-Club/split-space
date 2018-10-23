# split-space
Create a game like "Galaga" but you can control the spaceship and the bullet as well. Code in Java for consistancy. Hopefully we can get this done by Tuesdday for debugging.

A brief walkthrough on how the game works:

Notes: => functions needed

       * objects that appear on screen

                   |           0 0 0 0
     Level         |           *Score
     Setup         |                       ______                  ______     *Enemy
    Manager        |                      ( @  @ )                ( @  @ )   => Spawn AI 
                   |                        VVVV                    VVVV     => Collisions
                   |                                
                   |                         ▼                        ▼      *Enemy's bullet 
       Sounds      |                                                        => Spawn, collisions & despawn
                   |                                                  
                   |                                      (~)               * Spaceship's bullet
                   |                                       :                => Bullet Control
    Particles      |                                       :                => Collisions
                   |                                       :                => Timer to spawn and despawn
                   |                                 - - - -
                   |                                 : 
                   |                                 :
                   |                                 :
                   |                                 /\                          *Spaceship
                   |                                (  )                         => PLayer control
                   |                               /|/\|\                        => Collisions
                   |                              /_||||_\
                   |
                   |
                   |      /\   /\   /\
                   |     (  ) (  ) (  )       *Lives      => Bonus lives
                   |                          => Life Manager
                   
Particles are for explosion from collision with the bullet to the Enemy or Spaceship


To run the game, you need to add jinput, jogg, jorbis, lwjgl, slick into the eclipse build path, click on Project, build settings
=> Build Path
Here is the instruction on stuff: http://slick.ninjacave.com/wiki/index.php?title=Setting_up_Slick2D_with_Eclipse
