module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Store regA (DirAddr 0)
           , Pop regA
           , Store regA (DirAddr 1)
           , Pop regA
           , Store regA (DirAddr 2)
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Load (ImmValue 0) regB
           , Compute Add regA regB regB
           , Pop regA
           , Store regA (IndAddr regB)
           , EndProg ]

main = run [thread_0]
