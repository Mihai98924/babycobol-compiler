module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 1) regA
           , WriteInstr regA (DirAddr 1)
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 106) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 102) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
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
           , ReadInstr (DirAddr 1)
           , Receive regA
           , Branch regA (Rel (-2))
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 106) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 102) regA
           , Push regA
           , Load (ImmValue 97) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , EndProg ]

thread_1 :: [Instruction]
thread_1 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , ReadInstr (IndAddr regSprID)
           , Receive regA
           , Branch regA (Rel (2))
           , Jump (Rel (-3))
           , Load (ImmValue 1) regA
           , WriteInstr regA (DirAddr 2)
           , Load (ImmValue 49) regA
           , Push regA
           , Load (ImmValue 100) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
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
           , ReadInstr (DirAddr 2)
           , Receive regA
           , Branch regA (Rel (-2))
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 78) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 68) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 49) regA
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
           , WriteInstr reg0 (IndAddr regSprID)
           , EndProg ]

thread_2 :: [Instruction]
thread_2 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , ReadInstr (IndAddr regSprID)
           , Receive regA
           , Branch regA (Rel (2))
           , Jump (Rel (-3))
           , Load (ImmValue 50) regA
           , Push regA
           , Load (ImmValue 100) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
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
           , Load (ImmValue 0) regA
           , Store regA (DirAddr 0)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 3000) regA
           , Pop regB
           , Compute Lt regB regA regA
           , Branch regA (Rel (2))
           , Jump (Rel (10))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Add regB regA regA
           , Store regA (DirAddr 0)
           , Jump (Rel (-17))
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 78) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 68) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 50) regA
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
           , WriteInstr reg0 (IndAddr regSprID)
           , EndProg ]

main = run [thread_0, thread_1, thread_2]
