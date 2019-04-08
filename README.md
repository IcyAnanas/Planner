Use javac *.java to compile
And java Main < input.in to run (or java Main input.in).

Input file format:
    number of tasks (processes) demanding the processor
    
    one separate row for each task, consisting of 2 numbers
    separated by space  - the first is the moment of task's arrival,
    and the second is its time of execution. At least one task is going to arrive
    at time 0.

    number of variants of RR strategy (positive integer) - in a separate row, obviously.

    a corresponding number of integers separated by space, standing for coefficient q for subsequent variants of RR strategy (q = the time assigned periodically for each process in RR).
