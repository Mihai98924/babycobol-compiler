module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
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
           , Branch regA (Rel (2))
           , Jump (Rel (19))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 83) regA
           , Push regA
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 89) regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
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
           , Branch regA (Rel (16))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 78) regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (19))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 83) regA
           , Push regA
           , Load (ImmValue 69) regA
           , Push regA
           , Load (ImmValue 89) regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Load (ImmValue 67) regA
           , Push regA
           , Load (ImmValue 66) regA
           , Push regA
           , Load (ImmValue 65) regA
           , Pop regB
           , Compute Equal regB regA regA
           , Store regA (DirAddr 0)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Branch regA (Rel (2))
           , Jump (Rel (23))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 83) regA
           , Push regA
           , Load (ImmValue 80) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 79) regA
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
           , Load (ImmValue 112) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 107) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 112) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 112) regA
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
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Branch regA (Rel (14))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 16) regA
           , Push regA
           , Load (ImmValue 3) regA
           , Push regA
           , Load (ImmValue 9) regA
           , Pop regB
           , Compute Mul regB regA regA
           , Pop regB
           , Compute Add regB regA regA
           , WriteInstr regA numberIO
           , Jump (Rel (19))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 83) regA
           , Push regA
           , Load (ImmValue 80) regA
           , Push regA
           , Load (ImmValue 79) regA
           , Push regA
           , Load (ImmValue 79) regA
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
