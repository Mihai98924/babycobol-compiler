module Main where
import Sprockell
thread_0 :: [Instruction]
thread_0 = [ Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (ImmValue 122) regA
           , Push regA
           , Load (ImmValue 121) regA
           , Push regA
           , Load (ImmValue 120) regA
           , Push regA
           , Load (ImmValue 119) regA
           , Push regA
           , Load (ImmValue 118) regA
           , Push regA
           , Load (ImmValue 117) regA
           , Push regA
           , Load (ImmValue 116) regA
           , Push regA
           , Load (ImmValue 115) regA
           , Push regA
           , Load (ImmValue 114) regA
           , Push regA
           , Load (ImmValue 113) regA
           , Push regA
           , Load (ImmValue 112) regA
           , Push regA
           , Load (ImmValue 111) regA
           , Push regA
           , Load (ImmValue 110) regA
           , Push regA
           , Load (ImmValue 109) regA
           , Push regA
           , Load (ImmValue 108) regA
           , Push regA
           , Load (ImmValue 107) regA
           , Push regA
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
           , Pop regA
           , Store regA (DirAddr 9)
           , Pop regA
           , Store regA (DirAddr 10)
           , Pop regA
           , Store regA (DirAddr 11)
           , Pop regA
           , Store regA (DirAddr 12)
           , Pop regA
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
           , Pop regA
           , Store regA (DirAddr 23)
           , Pop regA
           , Store regA (DirAddr 24)
           , Pop regA
           , Store regA (DirAddr 25)
           , Load (ImmValue 26) regA
           , Store regA (DirAddr 26)
           , Load (ImmValue 0) regA
           , Store regA (DirAddr 27)
           , Load (ImmValue 26) regA
           , Store regA (DirAddr 28)
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Store regA (DirAddr 29)
           , Pop regA
           , Store regA (DirAddr 30)
           , Pop regA
           , Store regA (DirAddr 31)
           , Pop regA
           , Store regA (DirAddr 32)
           , Pop regA
           , Store regA (DirAddr 33)
           , Pop regA
           , Store regA (DirAddr 34)
           , Pop regA
           , Store regA (DirAddr 35)
           , Pop regA
           , Store regA (DirAddr 36)
           , Pop regA
           , Store regA (DirAddr 37)
           , Pop regA
           , Store regA (DirAddr 38)
           , Pop regA
           , Store regA (DirAddr 39)
           , Pop regA
           , Store regA (DirAddr 40)
           , Pop regA
           , Store regA (DirAddr 41)
           , Pop regA
           , Store regA (DirAddr 42)
           , Pop regA
           , Store regA (DirAddr 43)
           , Pop regA
           , Store regA (DirAddr 44)
           , Pop regA
           , Store regA (DirAddr 45)
           , Pop regA
           , Store regA (DirAddr 46)
           , Pop regA
           , Store regA (DirAddr 47)
           , Pop regA
           , Store regA (DirAddr 48)
           , Pop regA
           , Store regA (DirAddr 49)
           , Pop regA
           , Store regA (DirAddr 50)
           , Pop regA
           , Store regA (DirAddr 51)
           , Pop regA
           , Store regA (DirAddr 52)
           , Pop regA
           , Store regA (DirAddr 53)
           , Pop regA
           , Store regA (DirAddr 54)
           , Load (ImmValue 0) regA
           , Store regA (DirAddr 55)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 27) regA
           , Push regA
           , Load (DirAddr 28) regA
           , Pop regB
           , Compute Lt regB regA regA
           , Branch regA (Rel (2))
           , Jump (Rel (43))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 27) regA
           , Load (ImmValue 0) regB
           , Compute Add regA regB regB
           , Load (IndAddr regB) regA
           , Push regA
           , Load (DirAddr 27) regA
           , Load (ImmValue 29) regB
           , Compute Add regA regB regB
           , Pop regA
           , Store regA (IndAddr regB)
           , Load (DirAddr 27) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Add regB regA regA
           , Store regA (DirAddr 27)
           , Load (DirAddr 55) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Add regB regA regA
           , Store regA (DirAddr 55)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 55) regA
           , Push regA
           , Load (DirAddr 26) regA
           , Pop regB
           , Compute Equal regB regA regA
           , Branch regA (Rel (2))
           , Jump (Rel (9))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 55) regA
           , Push regA
           , Load (DirAddr 26) regA
           , Pop regB
           , Compute Sub regB regA regA
           , Store regA (DirAddr 55)
           , Jump (Rel (-50))
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Store regA (DirAddr 56)
           , Pop regA
           , Store regA (DirAddr 57)
           , Pop regA
           , Store regA (DirAddr 58)
           , Pop regA
           , Store regA (DirAddr 59)
           , Pop regA
           , Store regA (DirAddr 60)
           , Pop regA
           , Store regA (DirAddr 61)
           , Pop regA
           , Store regA (DirAddr 62)
           , Pop regA
           , Store regA (DirAddr 63)
           , Pop regA
           , Store regA (DirAddr 64)
           , Pop regA
           , Store regA (DirAddr 65)
           , Pop regA
           , Store regA (DirAddr 66)
           , Pop regA
           , Store regA (DirAddr 67)
           , Pop regA
           , Store regA (DirAddr 68)
           , Pop regA
           , Store regA (DirAddr 69)
           , Pop regA
           , Store regA (DirAddr 70)
           , Pop regA
           , Store regA (DirAddr 71)
           , Pop regA
           , Store regA (DirAddr 72)
           , Pop regA
           , Store regA (DirAddr 73)
           , Pop regA
           , Store regA (DirAddr 74)
           , Pop regA
           , Store regA (DirAddr 75)
           , Pop regA
           , Store regA (DirAddr 76)
           , Pop regA
           , Store regA (DirAddr 77)
           , Pop regA
           , Store regA (DirAddr 78)
           , Pop regA
           , Store regA (DirAddr 79)
           , Pop regA
           , Store regA (DirAddr 80)
           , Pop regA
           , Store regA (DirAddr 81)
           , Load (ImmValue 0) regA
           , Store regA (DirAddr 82)
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 82) regA
           , Push regA
           , Load (ImmValue 26) regA
           , Pop regB
           , Compute Lt regB regA regA
           , Branch regA (Rel (2))
           , Jump (Rel (28))
           , Load (ImmValue 84) regE
           , Load (ImmValue 70) regF
           , Load (DirAddr 26) regA
           , Push regA
           , Load (DirAddr 82) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Add regB regA regA
           , Pop regB
           , Compute Sub regB regA regA
           , Load (ImmValue 29) regB
           , Compute Add regA regB regB
           , Load (IndAddr regB) regA
           , Push regA
           , Load (DirAddr 82) regA
           , Load (ImmValue 56) regB
           , Compute Add regA regB regB
           , Pop regA
           , Store regA (IndAddr regB)
           , Load (DirAddr 82) regA
           , Push regA
           , Load (ImmValue 1) regA
           , Pop regB
           , Compute Sub regB regA regA
           , Store regA (DirAddr 82)
           , Jump (Rel (-35))
           , Load (DirAddr 81) regA
           , Push regA
           , Load (DirAddr 80) regA
           , Push regA
           , Load (DirAddr 79) regA
           , Push regA
           , Load (DirAddr 78) regA
           , Push regA
           , Load (DirAddr 77) regA
           , Push regA
           , Load (DirAddr 76) regA
           , Push regA
           , Load (DirAddr 75) regA
           , Push regA
           , Load (DirAddr 74) regA
           , Push regA
           , Load (DirAddr 73) regA
           , Push regA
           , Load (DirAddr 72) regA
           , Push regA
           , Load (DirAddr 71) regA
           , Push regA
           , Load (DirAddr 70) regA
           , Push regA
           , Load (DirAddr 69) regA
           , Push regA
           , Load (DirAddr 68) regA
           , Push regA
           , Load (DirAddr 67) regA
           , Push regA
           , Load (DirAddr 66) regA
           , Push regA
           , Load (DirAddr 65) regA
           , Push regA
           , Load (DirAddr 64) regA
           , Push regA
           , Load (DirAddr 63) regA
           , Push regA
           , Load (DirAddr 62) regA
           , Push regA
           , Load (DirAddr 61) regA
           , Push regA
           , Load (DirAddr 60) regA
           , Push regA
           , Load (DirAddr 59) regA
           , Push regA
           , Load (DirAddr 58) regA
           , Push regA
           , Load (DirAddr 57) regA
           , Push regA
           , Load (DirAddr 56) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (ImmValue 124) regA
           , Push regA
           , Load (ImmValue 32) regA
           , Push regA
           , Load (DirAddr 25) regA
           , Push regA
           , Load (DirAddr 24) regA
           , Push regA
           , Load (DirAddr 23) regA
           , Push regA
           , Load (DirAddr 22) regA
           , Push regA
           , Load (DirAddr 21) regA
           , Push regA
           , Load (DirAddr 20) regA
           , Push regA
           , Load (DirAddr 19) regA
           , Push regA
           , Load (DirAddr 18) regA
           , Push regA
           , Load (DirAddr 17) regA
           , Push regA
           , Load (DirAddr 16) regA
           , Push regA
           , Load (DirAddr 15) regA
           , Push regA
           , Load (DirAddr 14) regA
           , Push regA
           , Load (DirAddr 13) regA
           , Push regA
           , Load (DirAddr 12) regA
           , Push regA
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
           , Store regA (DirAddr 83)
           , Pop regA
           , Store regA (DirAddr 84)
           , Pop regA
           , Store regA (DirAddr 85)
           , Pop regA
           , Store regA (DirAddr 86)
           , Pop regA
           , Store regA (DirAddr 87)
           , Pop regA
           , Store regA (DirAddr 88)
           , Pop regA
           , Store regA (DirAddr 89)
           , Pop regA
           , Store regA (DirAddr 90)
           , Pop regA
           , Store regA (DirAddr 91)
           , Pop regA
           , Store regA (DirAddr 92)
           , Pop regA
           , Store regA (DirAddr 93)
           , Pop regA
           , Store regA (DirAddr 94)
           , Pop regA
           , Store regA (DirAddr 95)
           , Pop regA
           , Store regA (DirAddr 96)
           , Pop regA
           , Store regA (DirAddr 97)
           , Pop regA
           , Store regA (DirAddr 98)
           , Pop regA
           , Store regA (DirAddr 99)
           , Pop regA
           , Store regA (DirAddr 100)
           , Pop regA
           , Store regA (DirAddr 101)
           , Pop regA
           , Store regA (DirAddr 102)
           , Pop regA
           , Store regA (DirAddr 103)
           , Pop regA
           , Store regA (DirAddr 104)
           , Pop regA
           , Store regA (DirAddr 105)
           , Pop regA
           , Store regA (DirAddr 106)
           , Pop regA
           , Store regA (DirAddr 107)
           , Pop regA
           , Store regA (DirAddr 108)
           , Pop regA
           , Store regA (DirAddr 109)
           , Pop regA
           , Store regA (DirAddr 110)
           , Pop regA
           , Store regA (DirAddr 111)
           , Pop regA
           , Store regA (DirAddr 112)
           , Pop regA
           , Store regA (DirAddr 113)
           , Pop regA
           , Store regA (DirAddr 114)
           , Pop regA
           , Store regA (DirAddr 115)
           , Pop regA
           , Store regA (DirAddr 116)
           , Pop regA
           , Store regA (DirAddr 117)
           , Pop regA
           , Store regA (DirAddr 118)
           , Pop regA
           , Store regA (DirAddr 119)
           , Pop regA
           , Store regA (DirAddr 120)
           , Pop regA
           , Store regA (DirAddr 121)
           , Pop regA
           , Store regA (DirAddr 122)
           , Pop regA
           , Store regA (DirAddr 123)
           , Pop regA
           , Store regA (DirAddr 124)
           , Pop regA
           , Store regA (DirAddr 125)
           , Pop regA
           , Store regA (DirAddr 126)
           , Pop regA
           , Store regA (DirAddr 127)
           , Pop regA
           , Store regA (DirAddr 128)
           , Pop regA
           , Store regA (DirAddr 129)
           , Pop regA
           , Store regA (DirAddr 130)
           , Pop regA
           , Store regA (DirAddr 131)
           , Pop regA
           , Store regA (DirAddr 132)
           , Pop regA
           , Store regA (DirAddr 133)
           , Pop regA
           , Store regA (DirAddr 134)
           , Pop regA
           , Store regA (DirAddr 135)
           , Pop regA
           , Store regA (DirAddr 136)
           , Pop regA
           , Store regA (DirAddr 137)
           , Load (DirAddr 137) regA
           , Push regA
           , Load (DirAddr 136) regA
           , Push regA
           , Load (DirAddr 135) regA
           , Push regA
           , Load (DirAddr 134) regA
           , Push regA
           , Load (DirAddr 133) regA
           , Push regA
           , Load (DirAddr 132) regA
           , Push regA
           , Load (DirAddr 131) regA
           , Push regA
           , Load (DirAddr 130) regA
           , Push regA
           , Load (DirAddr 129) regA
           , Push regA
           , Load (DirAddr 128) regA
           , Push regA
           , Load (DirAddr 127) regA
           , Push regA
           , Load (DirAddr 126) regA
           , Push regA
           , Load (DirAddr 125) regA
           , Push regA
           , Load (DirAddr 124) regA
           , Push regA
           , Load (DirAddr 123) regA
           , Push regA
           , Load (DirAddr 122) regA
           , Push regA
           , Load (DirAddr 121) regA
           , Push regA
           , Load (DirAddr 120) regA
           , Push regA
           , Load (DirAddr 119) regA
           , Push regA
           , Load (DirAddr 118) regA
           , Push regA
           , Load (DirAddr 117) regA
           , Push regA
           , Load (DirAddr 116) regA
           , Push regA
           , Load (DirAddr 115) regA
           , Push regA
           , Load (DirAddr 114) regA
           , Push regA
           , Load (DirAddr 113) regA
           , Push regA
           , Load (DirAddr 112) regA
           , Push regA
           , Load (DirAddr 111) regA
           , Push regA
           , Load (DirAddr 110) regA
           , Push regA
           , Load (DirAddr 109) regA
           , Push regA
           , Load (DirAddr 108) regA
           , Push regA
           , Load (DirAddr 107) regA
           , Push regA
           , Load (DirAddr 106) regA
           , Push regA
           , Load (DirAddr 105) regA
           , Push regA
           , Load (DirAddr 104) regA
           , Push regA
           , Load (DirAddr 103) regA
           , Push regA
           , Load (DirAddr 102) regA
           , Push regA
           , Load (DirAddr 101) regA
           , Push regA
           , Load (DirAddr 100) regA
           , Push regA
           , Load (DirAddr 99) regA
           , Push regA
           , Load (DirAddr 98) regA
           , Push regA
           , Load (DirAddr 97) regA
           , Push regA
           , Load (DirAddr 96) regA
           , Push regA
           , Load (DirAddr 95) regA
           , Push regA
           , Load (DirAddr 94) regA
           , Push regA
           , Load (DirAddr 93) regA
           , Push regA
           , Load (DirAddr 92) regA
           , Push regA
           , Load (DirAddr 91) regA
           , Push regA
           , Load (DirAddr 90) regA
           , Push regA
           , Load (DirAddr 89) regA
           , Push regA
           , Load (DirAddr 88) regA
           , Push regA
           , Load (DirAddr 87) regA
           , Push regA
           , Load (DirAddr 86) regA
           , Push regA
           , Load (DirAddr 85) regA
           , Push regA
           , Load (DirAddr 84) regA
           , Push regA
           , Load (DirAddr 83) regA
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
