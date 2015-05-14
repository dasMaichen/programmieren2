#!/usr/bin/env python3
import fileinput

import os
from shutil import rmtree, copytree, copy2
import sys

OUTPUT_DIR = 'abgabe'

try:
    author = ' '.join(sys.argv[1:])
except:
    sys.exit('Author must be given as argument!')

def copy_java_files_only(src, dst, *, follow_symlinks=True):
    if src.endswith('.java'):
        print('Copying ' + src)
        dst_file = os.path.join(OUTPUT_DIR, os.path.basename(src))
        copy2(src=src, dst=dst_file, follow_symlinks=follow_symlinks)

try:
    copytree(src='.', dst=OUTPUT_DIR, copy_function=copy_java_files_only)
except FileExistsError:
    sys.exit('Please delete output directory "' + OUTPUT_DIR + '"!')

# remove empty directories in output folder
for dir, subdirs, files in os.walk(top=OUTPUT_DIR, topdown=False):
    if dir != OUTPUT_DIR:
        os.rmdir(dir)

for item in os.listdir(OUTPUT_DIR):
    java_file = os.path.join(OUTPUT_DIR, item)
    is_first_javadoc = True
    for line in fileinput.input(files=java_file, inplace=True):
        print(line, end='')

        if line.strip() == '/**' and is_first_javadoc:
            print(' * @author ' + author)
            print(' *')
            is_first_javadoc = False
