module Main where

import Sprockell

prog :: [Instruction]
prog = 
    [ ReadInstr numberIO            -- Ask for number from user. 
    , Receive regE                  -- Store received number in regE.
    , Load (ImmValue 0) regA        -- 0                    -> total (regA)
    , Load (ImmValue 1) regB        -- 1                    -> currentNum (regB)

-- begin loop
    , Compute Decr regE regE regE   -- received number--
    , Compute Lt regE reg0 regC     -- received number < 0  -> regC
    , Branch regC (Abs 11)          -- jump to write.
    , Compute Mul regB regB regC    -- currentNum^2         -> regC
    , Compute Add regA regC regA    -- total + currentNum^2 -> regA
    , Compute Incr regB regB regB   -- currentNum++
    , Jump (Rel (-6))               -- jump to begin loop       

    , WriteInstr regA numberIO       
    , EndProg
    ]

main = run [prog]