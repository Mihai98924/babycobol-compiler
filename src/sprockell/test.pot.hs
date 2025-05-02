module Main where

import Sprockell
import Data.Char

prog :: [Instruction]
prog = [ Load (ImmValue 97) regA
       , Load (ImmValue $ ord '\n') regF
       , WriteInstr regA numberIO
       , WriteInstr regA charIO
       , WriteInstr regA charIO
       , WriteInstr regF charIO
       , WriteInstr regA numberIO
       , WriteInstr regA numberIO
       , EndProg ]

main = run [prog]