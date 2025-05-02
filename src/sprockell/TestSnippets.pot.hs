module TestSnippets where

import Snippets
import Sprockell

-- 1 + 2 = 3
simpleAdd :: [Instruction]
simpleAdd = (lit_snippet 1) ++ (lit_snippet 2) ++ (bin_op_snippet Add) ++ print_num_snippet ++ [EndProg] 

-- -2
simpleNeg :: [Instruction]
simpleNeg = (lit_snippet 2) ++ negation_snippet ++ print_num_snippet ++ [EndProg]

-- !true (1)
-- !false (0)
simpleNot :: [Instruction]
simpleNot = (lit_snippet 1) ++ not_snippet ++ print_num_snippet
            ++ (lit_snippet 0) ++ not_snippet ++ print_num_snippet
            ++ [EndProg]

-- int a = 4
-- int b = 3
-- a + a + b + b + a = 18    
simpleAssign :: [Instruction]
simpleAssign = (lit_snippet 4) ++ (assign_var_snippet 0)
                ++ (lit_snippet 3) ++ (assign_var_snippet 1)
                ++ (var_snippet 0) ++ (var_snippet 0) ++ (bin_op_snippet Add)
                ++ (var_snippet 1) ++ (bin_op_snippet Add)
                ++ (var_snippet 1) ++ (bin_op_snippet Add)
                ++ (var_snippet 0) ++ (bin_op_snippet Add)
                ++ print_num_snippet ++ [EndProg]

-- n = <- read)
-- if (n)
-- then print 1
-- else print 2
simpleIf :: [Instruction]
simpleIf = [ReadInstr numberIO, Receive regA, Push regA] ++ (assign_var_snippet 0)          -- n = <- read
            ++ (if_snippet (var_snippet 0)                                                  -- if (n)
                           ((lit_snippet 1) ++ print_num_snippet)                           -- then print 1
                           ((lit_snippet 2) ++ print_num_snippet))                          -- else print 2
            ++ [EndProg]

-- n = <- read
-- i = 0
-- while (i < n)
-- print i
-- i++
simpleWhile :: [Instruction]
simpleWhile = [ReadInstr numberIO, Receive regA, Push regA] ++ (assign_var_snippet 0)       -- n = <- read
            ++ (lit_snippet 0) ++ (assign_var_snippet 1)                                    -- i = 0
            ++ (while_snippet ((var_snippet 1) ++ (var_snippet 0) ++ (bin_op_snippet Lt))   -- while (i < n)
                              ((var_snippet 1) ++ print_num_snippet                         -- print i
                              ++ (incr_snippet 1)))                                         -- i++
            ++ [EndProg]


program :: [Instruction]
program = [ Load (ImmValue 3) regA
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
          , WriteInstr regA numberIO
          , EndProg ]

