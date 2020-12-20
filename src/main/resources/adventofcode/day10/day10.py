with open("data.txt") as file:
	data = [int(x) for x in file.read().split("\n")]

data.append(0)
data.append(max(data) + 3)
data.sort();

paths = [0] * (max(data) + 1)
paths[0] = 1

for index in range(1, max(data) + 1):
	for x in range(1, 4):
		if (index - x) in data:
			paths[index] += paths[index -x]

print(str(paths[-1]))
