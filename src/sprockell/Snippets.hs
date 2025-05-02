module Snippets where

import Sprockell
import Data.Char


lit_snippet :: Int -> [Instruction]
lit_snippet val =
    [ Load (ImmValue val) regA
    , Push regA
    ]

var_snippet :: Int -> [Instruction]
var_snippet addr =
    [ Load (DirAddr addr) regA
    , Push regA
    ]

arr_snippet :: Int -> Int -> [Instruction]
arr_snippet addr off =
    [ Load (ImmValue addr) regA
    , Load (ImmValue off) regB
    , Compute Add regA regB regB
    , Load (IndAddr regB) regA
    , Push regA
    ]

assign_var_snippet :: Int -> [Instruction]
assign_var_snippet addr =
    [ Pop regA
    , Store regA (DirAddr addr)
    ]

assign_arr_snippet :: Int -> Int -> [Instruction]
assign_arr_snippet addr off =
    [ Pop regA
    , Load (ImmValue addr) regB
    , Load (ImmValue off) regC
    , Compute Add regB regC regC
    , Store regA (IndAddr regC)
    ]

not_snippet :: [Instruction]
not_snippet =
    [ Pop regA
    , Load (ImmValue 1) regB
    , Compute Xor regA regB regA    -- regA ^ 0x0001
    , Push regA
    ]

negation_snippet :: [Instruction]
negation_snippet =
    [ Pop regA
    , Compute Sub reg0 regA regA
    , Push regA
    ]

incr_snippet :: Int -> [Instruction]
incr_snippet addr =
    [ Load (DirAddr addr) regA
    , Compute Incr regA regA regA
    , Store regA (DirAddr addr)
    ]

bin_op_snippet :: Operator -> [Instruction]
bin_op_snippet op =
    [ Pop regA                      -- right operand
    , Pop regB                      -- left operand
    , Compute op regB regA regA
    , Push regA
    ]

print_num_snippet :: [Instruction]
print_num_snippet =
    [ Pop regA
    , WriteInstr regA numberIO
    ]

if_snippet :: [Instruction] -> [Instruction] -> [Instruction] -> [Instruction]
if_snippet cond true false =
    cond
    ++ [Pop regA, Branch regA (Rel $ (length false) + 2)]
    ++ false
    ++ [Jump (Rel $ (length true + 1))]
    ++ true

while_snippet :: [Instruction] -> [Instruction] -> [Instruction]
while_snippet cond loop =
    cond
    ++ [Pop regA, Branch regA (Rel 2)]
    ++ [Jump (Rel $ (length loop) + 2)]
    ++ loop
    ++ [Jump (Rel $ -(length loop) - (length cond) - 3)]