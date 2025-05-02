module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
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
           , Store regA (DirAddr 0)
           , Pop regA
           , Store regA (DirAddr 1)
           , Pop regA
           , Store regA (DirAddr 2)
           , Pop regA
           , Store regA (DirAddr 3)
           , Pop regA
           , Store regA (DirAddr 4)
           , Pop regA
           , Store regA (DirAddr 5)
           , Pop regA
           , Store regA (DirAddr 6)
           , Pop regA
           , Store regA (DirAddr 7)
           , Pop regA
           , Store regA (DirAddr 8)
           , Load (ImmValue 108) regA
           , Push regA
           , Load (ImmValue 107) regA
           , Push regA
           , Load (ImmValue 106) regA
           , Push regA
           , Load (DirAddr 8) regA
           , Push regA
           , Load (DirAddr 7) regA
           , Push regA
           , Load (DirAddr 6) regA
           , Push regA
           , Load (DirAddr 5) regA
           , Push regA
           , Load (DirAddr 4) regA
           , Push regA
           , Load (DirAddr 3) regA
           , Push regA
           , Load (DirAddr 2) regA
           , Push regA
           , Load (DirAddr 1) regA
           , Push regA
           , Load (DirAddr 0) regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
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
