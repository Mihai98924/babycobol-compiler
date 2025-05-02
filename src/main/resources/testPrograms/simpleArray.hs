module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 5) regA
           , Push regA
           , Load (ImmValue 4) regA
           , Push regA
           , Load (ImmValue 3) regA
           , Push regA
           , Load (ImmValue 2) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Push regA
           , Load (ImmValue 0) regA
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
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
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
           , Store regA (DirAddr 6)
           , Pop regA
           , Store regA (DirAddr 7)
           , Pop regA
           , Store regA (DirAddr 8)
           , Pop regA
           , Store regA (DirAddr 9)
           , Pop regA
           , Store regA (DirAddr 10)
           , Pop regA
           , Store regA (DirAddr 11)
           , Load (DirAddr 11) regA
           , Push regA
           , Load (DirAddr 10) regA
           , Push regA
           , Load (DirAddr 9) regA
           , Push regA
           , Load (DirAddr 8) regA
           , Push regA
           , Load (DirAddr 7) regA
           , Push regA
           , Load (DirAddr 6) regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Pop regA
           , WriteInstr regA numberIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , EndProg ]

main = run [thread_0]
