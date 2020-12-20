import fileinput
import re
import itertools

p1 = 0
p2 = 0

GOAL = 21806024
PRE = 25

lines = list([int(x) for x in fileinput.input()])

for i in range (len(lines)):
	sum_ = lines[i]
	min_ = lines[i]
	max_ = lines[i]
	for j in range(i + 1, len(lines)):
		sum_ += lines[j]
		min_ = min(lines[j], min_)
		max_ = max(lines[j], max_)
		if sum_ == GOAL:
			print(i, j, lines[i:j], sum(lines[i:j]) - GOAL)
			print(min_ + max_)