#!/bin/bash

# Usage: ./filetree.sh [directory]
# Default directory is current directory

DIR="${1:-.}"

find "$DIR" | awk '
BEGIN {
  FS="/"
}
{
  indent = ""
  for (i = 2; i <= NF; i++) {
    indent = indent "│   "
  }
  if (NF > 1) {
    print indent "├── " $NF
  } else {
    print $NF
  }
}
'
