module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 3) regA
           , Push regA
           , Load (ImmValue 2) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Store regA (DirAddr 0)
           , Pop regA
           , Store regA (DirAddr 1)
           , Pop regA
           , Store regA (DirAddr 2)
           , Load (DirAddr 2) regA
           , Push regA
           , Load (DirAddr 1) regA
           , Push regA
           , Load (DirAddr 0) regA
           , Store regA (DirAddr 3)
           , Pop regA
           , Store regA (DirAddr 4)
           , Pop regA
           , Store regA (DirAddr 5)
           , Load (ImmValue 0) regA
           , Load (ImmValue 3) regB
           , Compute Add regA regB regB
           , Load (IndAddr regB) regA
           , Store regA (DirAddr 6)
           , Load (ImmValue 97) regA
           , Store regA (DirAddr 7)
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 68) regA
           , Push regA
           , Load (ImmValue 67) regA
           , Push regA
           , Load (ImmValue 66) regA
           , Push regA
           , Load (ImmValue 65) regA
           , Store regA (DirAddr 8)
           , Pop regA
           , Store regA (DirAddr 9)
           , Pop regA
           , Store regA (DirAddr 10)
           , Pop regA
           , Store regA (DirAddr 11)
           , Pop regA
           , Store regA (DirAddr 12)
           , Load (ImmValue 106) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 103) regA
           , Push regA
           , Load (ImmValue 102) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 100) regA
           , Push regA
           , Load (ImmValue 99) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Store regA (DirAddr 13)
           , Pop regA
           , Store regA (DirAddr 14)
           , Pop regA
           , Store regA (DirAddr 15)
           , Pop regA
           , Store regA (DirAddr 16)
           , Pop regA
           , Store regA (DirAddr 17)
           , Pop regA
           , Store regA (DirAddr 18)
           , Pop regA
           , Store regA (DirAddr 19)
           , Pop regA
           , Store regA (DirAddr 20)
           , Pop regA
           , Store regA (DirAddr 21)
           , Pop regA
           , Store regA (DirAddr 22)
           , Load (ImmValue 3) regA
           , Push regA
           , Load (ImmValue 3) regA
           , Pop regB
           , Compute Mul regB regA regA
           , Push regA
           , Load (ImmValue 4) regA
           , Push regA
           , Load (ImmValue 5) regA
           , Pop regB
           , Compute Add regB regA regA
           , Pop regB
           , Compute Equal regB regA regA
           , Branch regA (Rel (3))
           , WriteInstr regF charIO
           , Jump (Rel (2))
           , WriteInstr regE charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , EndProg ]

main = run [thread_0]
