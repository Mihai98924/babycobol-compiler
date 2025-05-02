module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 107) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 102) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 107) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 100) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 100) regA
           , Push regA
           , Load (ImmValue 101) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 97) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 103) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 105) regA
           , Push regA
           , Load (ImmValue 104) regA
           , Push regA
           , Load (ImmValue 116) regA
           , WriteInstr regA (DirAddr 16)
           , Pop regA
           , WriteInstr regA (DirAddr 17)
           , Pop regA
           , WriteInstr regA (DirAddr 18)
           , Pop regA
           , WriteInstr regA (DirAddr 19)
           , Pop regA
           , WriteInstr regA (DirAddr 20)
           , Pop regA
           , WriteInstr regA (DirAddr 21)
           , Pop regA
           , WriteInstr regA (DirAddr 22)
           , Pop regA
           , WriteInstr regA (DirAddr 23)
           , Pop regA
           , WriteInstr regA (DirAddr 24)
           , Pop regA
           , WriteInstr regA (DirAddr 25)
           , Pop regA
           , WriteInstr regA (DirAddr 26)
           , Pop regA
           , WriteInstr regA (DirAddr 27)
           , Pop regA
           , WriteInstr regA (DirAddr 28)
           , Pop regA
           , WriteInstr regA (DirAddr 29)
           , Pop regA
           , WriteInstr regA (DirAddr 30)
           , Pop regA
           , WriteInstr regA (DirAddr 31)
           , Pop regA
           , WriteInstr regA (DirAddr 32)
           , Pop regA
           , WriteInstr regA (DirAddr 33)
           , Pop regA
           , WriteInstr regA (DirAddr 34)
           , Pop regA
           , WriteInstr regA (DirAddr 35)
           , Pop regA
           , WriteInstr regA (DirAddr 36)
           , Pop regA
           , WriteInstr regA (DirAddr 37)
           , Pop regA
           , WriteInstr regA (DirAddr 38)
           , Pop regA
           , WriteInstr regA (DirAddr 39)
           , Pop regA
           , WriteInstr regA (DirAddr 40)
           , Pop regA
           , WriteInstr regA (DirAddr 41)
           , Pop regA
           , WriteInstr regA (DirAddr 42)
           , Pop regA
           , WriteInstr regA (DirAddr 43)
           , Pop regA
           , WriteInstr regA (DirAddr 44)
           , Pop regA
           , WriteInstr regA (DirAddr 45)
           , Pop regA
           , WriteInstr regA (DirAddr 46)
           , Pop regA
           , WriteInstr regA (DirAddr 47)
           , Pop regA
           , WriteInstr regA (DirAddr 48)
           , Pop regA
           , WriteInstr regA (DirAddr 49)
           , Pop regA
           , WriteInstr regA (DirAddr 50)
           , Pop regA
           , WriteInstr regA (DirAddr 51)
           , Pop regA
           , WriteInstr regA (DirAddr 52)
           , Pop regA
           , WriteInstr regA (DirAddr 53)
           , Pop regA
           , WriteInstr regA (DirAddr 54)
           , Pop regA
           , WriteInstr regA (DirAddr 55)
           , Pop regA
           , WriteInstr regA (DirAddr 56)
           , Pop regA
           , WriteInstr regA (DirAddr 57)
           , Pop regA
           , WriteInstr regA (DirAddr 58)
           , Pop regA
           , WriteInstr regA (DirAddr 59)
           , Pop regA
           , WriteInstr regA (DirAddr 60)
           , Pop regA
           , WriteInstr regA (DirAddr 61)
           , Pop regA
           , WriteInstr regA (DirAddr 62)
           , Pop regA
           , WriteInstr regA (DirAddr 63)
           , Load (ImmValue 1) regA
           , WriteInstr regA (DirAddr 1)
           , ReadInstr (DirAddr 1)
           , Receive regA
           , Branch regA (Rel (-2))
           , EndProg ]

thread_1 :: [Instruction]
thread_1 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , ReadInstr (IndAddr regSprID)
           , Receive regA
           , Branch regA (Rel (2))
           , Jump (Rel (-3))
           , ReadInstr (DirAddr 63)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 62)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 61)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 60)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 59)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 58)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 57)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 56)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 55)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 54)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 53)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 52)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 51)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 50)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 49)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 48)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 47)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 46)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 45)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 44)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 43)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 42)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 41)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 40)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 39)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 38)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 37)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 36)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 35)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 34)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 33)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 32)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 31)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 30)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 29)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 28)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 27)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 26)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 25)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 24)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 23)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 22)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 21)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 20)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 19)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 18)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 17)
           , Receive regA
           , Push regA
           , ReadInstr (DirAddr 16)
           , Receive regA
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
           , WriteInstr reg0 (IndAddr regSprID)
           , EndProg ]

main = run [thread_0, thread_1]
