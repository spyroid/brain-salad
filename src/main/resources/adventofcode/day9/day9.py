import fileinput
import re
import itertools

p1 = 0
p2 = 0

GOAL = 21806024
PRE = 25
lines = list([int(x) for x in fileinput.input()])
lines.append('');
for i in range (PRE, len(lines)):
	ok = True
	prev = lines[i-25:i]
	for z,y in itertools.combinations(prev, 2):
		if z + y == lines[i]:
			print(y, z)
			ok = False
	print(lines[i], prev)
	if ok:
		print(lines[i])
		break