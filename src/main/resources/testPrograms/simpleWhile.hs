module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 0) regA
           , Store regA (DirAddr 0)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 10) regA
           , Pop regB
           , Compute Lt regB regA regA
           , Branch regA (Rel (2))
           , Jump (Rel (12))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , WriteInstr regA numberIO
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Add regB regA regA
           , Store regA (DirAddr 0)
           , Jump (Rel (-19))
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 78) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 68) regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , EndProg ]

main = run [thread_0]
