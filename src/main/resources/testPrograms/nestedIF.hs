module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , Load (ImmValue 80) regA
           , WriteInstr regA charIO
           , Load (ImmValue 108) regA
           , WriteInstr regA charIO
           , Load (ImmValue 101) regA
           , WriteInstr regA charIO
           , Load (ImmValue 97) regA
           , WriteInstr regA charIO
           , Load (ImmValue 115) regA
           , WriteInstr regA charIO
           , Load (ImmValue 101) regA
           , WriteInstr regA charIO
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , Load (ImmValue 112) regA
           , WriteInstr regA charIO
           , Load (ImmValue 114) regA
           , WriteInstr regA charIO
           , Load (ImmValue 111) regA
           , WriteInstr regA charIO
           , Load (ImmValue 118) regA
           , WriteInstr regA charIO
           , Load (ImmValue 105) regA
           , WriteInstr regA charIO
           , Load (ImmValue 100) regA
           , WriteInstr regA charIO
           , Load (ImmValue 101) regA
           , WriteInstr regA charIO
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , Load (ImmValue 97) regA
           , WriteInstr regA charIO
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , Load (ImmValue 110) regA
           , WriteInstr regA charIO
           , Load (ImmValue 117) regA
           , WriteInstr regA charIO
           , Load (ImmValue 109) regA
           , WriteInstr regA charIO
           , Load (ImmValue 98) regA
           , WriteInstr regA charIO
           , Load (ImmValue 101) regA
           , WriteInstr regA charIO
           , Load (ImmValue 114) regA
           , WriteInstr regA charIO
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , Load (ImmValue 58) regA
           , WriteInstr regA charIO
           , Load (ImmValue 41) regA
           , WriteInstr regA charIO
           , Load (ImmValue 32) regA
           , WriteInstr regA charIO
           , ReadInstr numberIO
           , Receive regA
           , Store regA (DirAddr 0)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 3) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (383))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (213))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 0) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (128))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 49) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 108) regA
           , Push regA
           , Load (ImmValue 108) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Jump (Rel (75))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 49) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (160))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 2) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (76))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 50) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (75))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 51) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (378))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 5) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (161))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 4) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (76))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 52) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (75))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 53) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (208))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 0) regA
           , Push regA
           , Load (ImmValue 6) regA
           , Pop regB
           , Compute Gt regB regA regA
           , Branch regA (Rel (76))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 54) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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
           , Pop regA
           , WriteInstr regA charIO
           , Pop regA
           , WriteInstr regA charIO
           , Load (ImmValue 10) regA
           , WriteInstr regA charIO
           , Jump (Rel (123))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 33) regA
           , Push regA
           , Load (ImmValue 55) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 103) regA
           , Push regA
           , Load (ImmValue 103) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 98) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 89) regA
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

main = run [thread_0]
