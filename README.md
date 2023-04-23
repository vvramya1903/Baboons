A canyon cuts through the territory of a colony of baboons.  The baboons like to do baboon-stuff on a side of the canyon and then cross over to the other side where they do more baboon-stuff and then cross back over, etc., etc. etc.   The baboons cross by means of a single rope that stretches from one side to the other.  The rope is strong so that it can hold any number of baboons but they must all be going the same direction (the rope is too narrow for baboons to cross each other on the rope).  It takes several seconds for the baboon to cross the rope.

1)	Write a solution to the above program using semaphores.  Use the Java or Ada semaphore implementations on my web.  Your Java or Ada solution should have a single command-line argument representing the number of baboons.

2)	Write a solution to the above program without using the semaphore implementation.  This program can use a separate class or protected record for synchronization but it should be one especially developed for the program (rather than a generic semaphore).  It should not be exactly equivalent to the solution of. Again, use a single command-line argument representing the number of partiers.

Each baboon should be it’s own thread (each an instance of the baboon class).

Output enough information so that the user can fully see that the simulation is working.   
