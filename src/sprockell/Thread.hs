module Main where

import Sprockell

threads :: [Instruction]
threads = 
    [ Branch regSprID (Abs 2)
    , Jump (Abs 6)
    , ReadInstr (IndAddr regSprID)
    , Receive regA
    , Jump (Ind regA)
    
    -- Terminate all sprockell instances 
    , Load (ImmValue lastLine) regA
    , WriteInstr (DirAddr 1) regA
    , WriteInstr (DirAddr 2) regA
    -- '' repear for total num threads.
    , EndProg
    ]

lock :: [Instruction]
lock =
    [ TestAndSet (ImmValue mutexAddr)
    , Receive regD
    , Branch regD (Rel 2)
    , Jump (Rel -3)
    ]

unlock :: [Instruction]
unlock =
    [ WriteInstr reg0 (ImmValue mutexAddr) 
    ]